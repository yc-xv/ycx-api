package com.ycx.project.model.dto.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderRequest {
    private Long userId;

    /**
     * 接口 id
     */
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
