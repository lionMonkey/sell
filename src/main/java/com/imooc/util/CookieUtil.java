package com.imooc.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie工具类
 * @author: SiqiTang
 * @Date: 2018-12-25
 */
public class CookieUtil {
    public static void set(HttpServletResponse response,
                           String name,String value,int maxAge){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    public static void get(){

    }
}