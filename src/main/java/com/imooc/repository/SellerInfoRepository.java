package com.imooc.repository;

import com.imooc.dataobject.SellerInfo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: SiqiTang
 * @Date: 2018-12-24
 */

public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {
    SellerInfo findByOpenid(String openid);
}