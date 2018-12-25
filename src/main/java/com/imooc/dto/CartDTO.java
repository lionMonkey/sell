package com.imooc.dto;

import lombok.Data;

/**
 * @author: SiqiTang
 * @Date: 2018-12-11
 */
@Data
public class CartDTO {
    /**商品Id*/
    private String prodcutId;
    /**数量*/
    private Integer productQuantity;

    public CartDTO(String prodcutId, Integer productQuantity) {
        this.prodcutId = prodcutId;
        this.productQuantity = productQuantity;
    }
}