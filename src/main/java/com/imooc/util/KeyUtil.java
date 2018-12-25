package com.imooc.util;

import java.util.Random;

/**
 * @author: SiqiTang
 * @Date: 2018-12-11
 */
public class KeyUtil {
    /**
     *生成唯一的主键
     *格式:时间+随机数,避免多线程下重复
     * @param
     * @return
     */
    public static synchronized  String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;
        return  System.currentTimeMillis()+String.valueOf(number);
    }
}