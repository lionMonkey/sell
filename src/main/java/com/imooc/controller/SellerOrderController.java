package com.imooc.controller;

import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author: SiqiTang
 * @Date: 2018-12-17
 */
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {
    @Autowired
    private OrderService orderService;

    public static final Logger logger = LoggerFactory.getLogger(SellerOrderController.class);
    /**
     *
     *订单列表
     * @param page 第几页,从第一页开始
     * @param size 一页有多少数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "size",defaultValue = "10")Integer size,
                             Map<String,Object> map){
        //page是从0开始前端从1开始
        PageRequest request = new PageRequest(page-1,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        return new ModelAndView("/order/list",map);
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId")String orderId,Map<String,Object> map){
        OrderDTO orderDTO=null;
        try{
            orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            logger.error("[卖家端取消订单]发生异常{}",e);
            map.put("msg",e.getMessage() );
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success");
    }
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId")String orderId,Map<String ,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try {
           orderDTO = orderService.findOne(orderId);
        }catch (SellException e){
            logger.error("[卖家订单详情查询] 发生异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/detail",map);
    }

    @GetMapping("/finish")
    public ModelAndView finished(@RequestParam("orderId")String orderId,Map<String,Object>map){
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            logger.error("[卖家端完结订单]发生异常{}",e);
            map.put("msg",e.getMessage() );
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success");
    }

}
