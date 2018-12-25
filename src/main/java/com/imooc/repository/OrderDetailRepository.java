package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: SiqiTang
 * @Date: 2018-12-11
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    /**一个订单id可以得到多个订单明细*/
    List<OrderDetail> findByOrderId(String orderId);
}