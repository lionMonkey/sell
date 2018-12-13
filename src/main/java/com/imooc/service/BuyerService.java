package com.imooc.service;

import com.imooc.dto.OrderDTO;

/**
 * @author: SiqiTang
 * @Date: 2018-12-13
 */
public interface BuyerService {
    //查询订单
    OrderDTO findOrderOne(String openid,String orderId);


    //取消订单
    OrderDTO cancelOrder(String openid,String orderId);

    OrderDTO checkOrderOwer(String openid,String orderId);
}