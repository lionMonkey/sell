package com.imooc.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.imooc.dataobject.OrderDetail;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.OrderForm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: SiqiTang
 * @Date: 2018-12-13
 */
public class OrderForm2OrderDTOConverter {
    final static Logger logger = LoggerFactory.getLogger(OrderForm2OrderDTOConverter.class);
    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        //应为字段名不同,不能用beanutil
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try{
            orderDetailList = gson.fromJson(orderForm.getItems(),
                new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            logger.error("[对象转换] 错误,string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;



    }
}