package com.imooc.repository;

import com.imooc.dataobject.SellerInfo;
import com.imooc.util.KeyUtil;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository repository;
    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setOpenid(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        SellerInfo result = repository.save(sellerInfo);
        Assert.assertNotEquals(result,null);
    }
    @Test
    public void findByOpenid() {
        SellerInfo result = repository.findByOpenid("1545646775524639932");
        Assert.assertEquals("1545646775524639932",result.getOpenid());
    }
}