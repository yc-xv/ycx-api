package com.ycx.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycx.project.model.vo.ApiOrder;
import com.ycx.project.service.ApiOrderService;
import com.ycx.project.mapper.ApiOrderMapper;
import org.springframework.stereotype.Service;

/**
* @author ycx
* @description 针对表【api_order(接口订单表)】的数据库操作Service实现
* @createDate 2026-01-31 21:59:46
*/
@Service
public class ApiOrderServiceImpl extends ServiceImpl<ApiOrderMapper, ApiOrder>
    implements ApiOrderService{

}




