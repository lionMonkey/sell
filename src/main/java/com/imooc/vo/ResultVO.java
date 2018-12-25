package com.imooc.vo;

import lombok.Data;

/**
 * http请求返回的最外成对象
 * @author: SiqiTang
 * @Date: 2018-12-09
 */
@Data
public class ResultVO<T> {

    /**错误码*/
    private Integer code;

    /**提示信息*/
    private String msg;

    /**具体类容*/
    private T data;
}