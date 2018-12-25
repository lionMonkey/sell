package com.imooc;

import com.imooc.vo.ProductInfoVO;
import com.imooc.vo.ProductVO;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @author: SiqiTang
 * @Date: 2018-12-09
 */
public class AboutTest {
    public static void main(String[] args) {

        ProductVO productVO = new ProductVO();
        ProductInfoVO productInfoVO = new ProductInfoVO();
        productInfoVO.setProductId("1");
        List list= Arrays.asList(productInfoVO);
        System.out.println(list.size());
        File f = new File("");
    }
}