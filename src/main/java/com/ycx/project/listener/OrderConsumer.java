package com.ycx.project.listener;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ycx.project.config.QueueConfig;
import com.ycx.project.model.enums.OrderStatus;
import com.ycx.project.model.vo.ApiOrder;
import com.ycx.project.service.ApiOrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

@Component
public class OrderConsumer {
    @Autowired
    private ApiOrderService apiOrderService;
    @RabbitListener(queues = QueueConfig.ORDER_QUEUE)
    public void receive(ApiOrder apiOrder,
                        Message message,
                        Channel channel) throws Exception {

        try {
            System.out.println("收到延迟订单：" + apiOrder.getOrderSn());


            String orderSn = apiOrder.getOrderSn();
            QueryWrapper<ApiOrder> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("order_sn", orderSn);
            ApiOrder order = apiOrderService.getOne(queryWrapper);
            if(order.getStatus()== OrderStatus.TO_PAY.getValue()){
                order.setStatus(OrderStatus.CANCEL.getValue());
                apiOrderService.updateById(order);
            }
            channel.basicAck(
                    message.getMessageProperties().getDeliveryTag(),
                    false
            );
        } catch (Exception e) {

            channel.basicNack(
                    message.getMessageProperties().getDeliveryTag(),
                    false,
                    false
            );
        }
    }
}
