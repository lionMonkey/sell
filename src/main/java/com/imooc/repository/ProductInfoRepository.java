package com.imooc.repository;

import com.imooc.dataobject.ProductInfo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: SiqiTang
 * @Date: 2018-12-08
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);

}