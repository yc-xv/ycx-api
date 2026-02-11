package com.ycx.project.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 接口订单表
 * @TableName api_order
 */
@TableName(value ="api_order")
@Data
public class ApiOrder implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 接口 id
     */
    private Long interfaceInfoId;

    /**
     * 订单状态 TO_PAY-0 / PAID-1 / CANCEL-2
     */
    private Integer status;

    /**
     * 接口数量
     */
    private Integer orderNum;

    /**
     * 单价
     */
    private BigDecimal charging;



    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 更新时间
     */
    private Date update_time;

    /**
     * 逻辑删除
     */
    private Integer is_delete;
}