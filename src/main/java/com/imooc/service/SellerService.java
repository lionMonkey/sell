package com.imooc.service;

import com.imooc.dataobject.SellerInfo;

/**
 * 卖家端
 * @author: SiqiTang
 * @Date: 2018-12-25
 */
public interface SellerService {
    /**
     *通过openid查询卖家端信息
     *
     * @param
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);


}