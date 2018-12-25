package com.imooc.dataobject;



import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 类目
 * @author: SiqiTang
 * @Date: 2018-12-07
 */
/*
CREATE TABLE `product__category` (
    `category_id` int(11) NOT NULL AUTO_INCREMENT,
    `category_name` varchar(64) NOT NULL COMMENT '类目名称',
    `category_type` int(11) NOT NULL COMMENT '类目编号',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`category_id`),
    UNIQUE KEY `uqe_category_type` (`category_type`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类目表';

*/
@Entity
@DynamicUpdate
@Table(name="product_category")
@Data
public class ProductCategory {
    /**
     *类目id
     */
    @Id
    @GeneratedValue
    private Integer categoryId;

    /**
     * 类目名字
     */
    private String categoryName;

    /**
     *类目编号
     */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    public ProductCategory() {
    }


    @Override
    public String toString() {
        return "ProductCategory{" +
            "categoryId=" + categoryId +
            ", categoryName='" + categoryName + '\'' +
            ", categoryType=" + categoryType +
            '}';
    }
}