package com.imooc.controller;

import com.imooc.converter.OrderForm2OrderDTOConverter;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.OrderForm;
import com.imooc.service.BuyerService;
import com.imooc.service.OrderService;
import com.imooc.util.ResultVOUtil;
import com.imooc.vo.ResultVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: SiqiTang
 * @Date: 2018-12-13
 */
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    private static final Logger logger = LoggerFactory.getLogger(BuyerOrderController.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;
    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            logger.error("[创建订单] 参数不正确,orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            logger.error("[创建订单] 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());
        return ResultVOUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid" )String openid, @RequestParam(value = "page",defaultValue = "0") Integer page,@RequestParam(value = "size",defaultValue = "10")Integer size){
        if(StringUtils.isEmpty(openid)){
            logger.error("[查询订单列表]openid为空 ");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid,request);
        //Date->long低效
        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId){

        OrderDTO orderDTO = buyerService.checkOrderOwer(openid,orderId);
        return ResultVOUtil.success(orderDTO);
    }

    //取消订单
    @GetMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid")String openid,@RequestParam("orderId")String orderId){
//        //TODO 不安全改进
//        OrderDTO orderDTO = orderService.findOne(orderId);
//        orderService.cancel((orderDTO));
        buyerService.cancelOrder(openid,orderId);
        return ResultVOUtil.success();
    }
}