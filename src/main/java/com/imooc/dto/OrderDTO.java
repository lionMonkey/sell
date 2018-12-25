package com.imooc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.dataobject.OrderDetail;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.util.EnumUtil;
import com.imooc.util.serializer.Date2LongSerializer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import lombok.Data;

/**
 * order数据传输层对象
 * @author: SiqiTang
 * @Date: 2018-12-11
 */
@Data
//不返回json空串
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    /**订单id*/
    private String orderId;
    /**买家名字*/
    private String buyerName;
    /**买家手机号*/
    private String buyerPhone;
    /**买家地址*/
    private String buyerAddress;
    /**买家微信Openid*/
    private String buyerOpenid;
    /**订单总金额*/
    private BigDecimal orderAmount;
    /**订单状态,用于数据传输,不用设置默认值*/
    private Integer orderStatus;
    /**默认是等待支付0*/
    private Integer payStatus;

    /**创建时间,到时需要按时间来排序*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    /**更新时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    List<OrderDetail> orderDetailList;
    //以后若通过json拿数据会多出orderStatusEnum,@JsonIgnore可以使其被忽略
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);

    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }

}