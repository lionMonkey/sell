package com.imooc.form;

import lombok.Data;

/**
 * @author: SiqiTang
 * @Date: 2018-12-24
 */
@Data
public class CategoryForm {

    /**类目id*/
    private Integer categoryId;

    /**类目名称*/
    private String categoryName;

    /**类目编号*/
    private Integer categoryType;
}