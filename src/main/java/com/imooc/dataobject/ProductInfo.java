package com.imooc.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.util.EnumUtil;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;

/**
 * @author: SiqiTang
 * @Date: 2018-12-08
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {
    @Id
    private String productId;
    /**
     * 名字
     */
    private String productName;
    /**单价*/
    private BigDecimal productPrice;
    /**库存*/
    private Integer productStock;
    /**描述*/
    private String productDescription;
    /**小图*/
    private String productIcon;

    /**状态,0正常1下架*/
    private Integer productStatus = ProductStatusEnum.UP.getCode();
    /**类目编号*/
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }
}