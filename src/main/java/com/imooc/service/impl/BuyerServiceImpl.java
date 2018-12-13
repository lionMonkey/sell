package com.imooc.service.impl;

import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.BuyerService;
import com.imooc.service.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: SiqiTang
 * @Date: 2018-12-13
 */
@Service
public class BuyerServiceImpl implements BuyerService {

    private final static Logger logger = LoggerFactory.getLogger(BuyerServiceImpl.class);
    @Autowired
    private OrderService orderService;
    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwer(openid,orderId);
    }
    @Override
    public OrderDTO cancelOrder(String openid,String orderId){
        OrderDTO orderDTO = checkOrderOwer(openid,orderId);
        if(orderDTO == null){
            logger.error("[取消订单]无此订单,orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    @Override
    public OrderDTO checkOrderOwer(String openid,String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null){
            return null;
        }
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            logger.error("[查询订单]订单的openId不一致,openid={},orderDTO={}",openid,orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}