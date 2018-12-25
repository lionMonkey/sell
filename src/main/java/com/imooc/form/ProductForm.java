package com.imooc.form;

import com.imooc.enums.ProductStatusEnum;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @author: SiqiTang
 * @Date: 2018-12-21
 */
@Data
public class ProductForm {


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
}