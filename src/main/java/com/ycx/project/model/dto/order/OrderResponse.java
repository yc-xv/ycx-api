package com.ycx.project.model.dto.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderResponse {


    private String orderSn;


    private Long interfaceInfoId;

    /**
     * 接口数量
     */
    private Integer orderNum;

    /**
     * 单价
     */
    private BigDecimal charging;
}
