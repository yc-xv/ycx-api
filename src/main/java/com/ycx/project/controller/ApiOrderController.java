package com.ycx.project.controller;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ycx.model.entity.User;
import com.ycx.model.entity.UserInterfaceInfo;
import com.ycx.project.common.BaseResponse;
import com.ycx.project.common.ErrorCode;
import com.ycx.project.common.RedisConstant;
import com.ycx.project.common.ResultUtils;
import com.ycx.project.config.QueueConfig;
import com.ycx.project.exception.BusinessException;
import com.ycx.project.model.dto.order.OrderRequest;
import com.ycx.project.model.dto.order.OrderResponse;
import com.ycx.project.model.enums.OrderStatus;
import com.ycx.project.model.vo.ApiOrder;
import com.ycx.project.service.ApiOrderService;
import com.ycx.project.service.UserInterfaceInfoService;
import com.ycx.project.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.Duration;

@RestController
@RequestMapping("/order")
public class ApiOrderController {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private ApiOrderService apiOrderService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UserInterfaceInfoService interfaceInfoService;
    @PostMapping("/create")
    public BaseResponse<OrderResponse> createOrderSn(OrderRequest orderRequest, HttpServletRequest request) {
        //校验
        Integer orderNum = orderRequest.getOrderNum();
        BigDecimal charging = orderRequest.getCharging();
        Long interfaceInfoId = orderRequest.getInterfaceInfoId();
        User user = userService.getLoginUser(request);
        Long userId = orderRequest.getUserId();
        if(orderNum == null || charging == null || interfaceInfoId == null||userId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请完善订单信息");
        }
        if(!user.getId().equals(userId)) {
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR);
        }
        //redis防抖setnx原子操作
        Boolean success = redisTemplate.opsForValue().setIfAbsent(RedisConstant.CREATE_ORDER+userId+interfaceInfoId,"1", Duration.ofSeconds(10));
        if(Boolean.FALSE.equals(success)) {
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR,"提交过于频繁，请重试");
        }
        //创建订单
        ApiOrder apiOrder = new ApiOrder();
        apiOrder.setOrderSn(RandomUtil.randomString(10));
        apiOrder.setInterfaceInfoId(interfaceInfoId);
        apiOrder.setUserId(userId);
        apiOrder.setOrderNum(orderNum);
        apiOrder.setCharging(charging);
        apiOrderService.save(apiOrder);
        //发送延迟队列
        rabbitTemplate.convertAndSend("", QueueConfig.ORDER_DELAY_QUEUE, apiOrder);
        OrderResponse orderResponse = new OrderResponse();
        BeanUtils.copyProperties(apiOrder, orderResponse);
        return ResultUtils.success(orderResponse);
    }
    @PostMapping("/pay")
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse<String> pay(String orderSn, HttpServletRequest request) {
        //redis防抖
        Boolean success = redisTemplate.opsForValue().setIfAbsent(RedisConstant.PAY_ORDER+orderSn,"1", Duration.ofSeconds(10));
        if(Boolean.FALSE.equals(success)) {
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR,"请勿频繁点击");
        }
        //支付
        User user = userService.getLoginUser(request);
        QueryWrapper<ApiOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_sn", orderSn);
        ApiOrder apiOrder = apiOrderService.getOne(queryWrapper);
        Long userId = user.getId();
        Long interfaceInfoId = apiOrder.getInterfaceInfoId();
        QueryWrapper<UserInterfaceInfo> interfaceInfoQueryWrapper = new QueryWrapper<>();
        interfaceInfoQueryWrapper.eq("interfaceInfoId", interfaceInfoId);
        interfaceInfoQueryWrapper.eq("userId", userId);
        UserInterfaceInfo interfaceInfo = interfaceInfoService.getOne(interfaceInfoQueryWrapper);
        Integer status = apiOrder.getStatus();
        //幂等校验
        if(status==OrderStatus.PAID.getValue()) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"订单已支付");
        }
        if(status==OrderStatus.CANCEL.getValue()) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"订单已取消");
        }
        BigDecimal orderNum = BigDecimal.valueOf(apiOrder.getOrderNum());
        BigDecimal charging = apiOrder.getCharging();
        BigDecimal amount = orderNum.multiply(charging);
        BigDecimal balance = user.getBalance();
        if(balance.compareTo(amount) < 0) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"余额不足，当前余额为"+balance);
        }else {
            BigDecimal newBalance = balance.subtract(amount);
            user.setBalance(newBalance);
            userService.updateById(user);
            apiOrder.setStatus(OrderStatus.PAID.getValue());
            apiOrderService.updateById(apiOrder);
            interfaceInfo.setLeftNum(interfaceInfo.getLeftNum()+apiOrder.getOrderNum());
            interfaceInfoService.updateById(interfaceInfo);
        }
        return ResultUtils.success("支付成功，新增接口可用次数"+apiOrder.getOrderNum());
    }

}
