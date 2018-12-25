package com.imooc.util;

import com.imooc.enums.CodeEnum;

/**
 * @author: SiqiTang
 * @Date: 2018-12-17
 */
public class EnumUtil{
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for(T each: enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}