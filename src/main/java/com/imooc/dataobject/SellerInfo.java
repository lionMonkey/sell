package com.imooc.dataobject;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author: SiqiTang
 * @Date: 2018-12-24
 */
@Data
@Entity
public class SellerInfo {
    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;
}