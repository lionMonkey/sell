package com.imooc.controller;

import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.util.ResultVOUtil;
import com.imooc.vo.ProductInfoVO;
import com.imooc.vo.ProductVO;
import com.imooc.vo.ResultVO;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 * @author: SiqiTang
 * @Date: 2018-12-09
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(){
        //1.查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2.查询类目(一次性查询类目,不能说根据上面查到的商品再一个个根据类目id查询商品类目)
        List<Integer> categoryTypeList = new ArrayList<>();
            //传统做法
        for(ProductInfo productInfo : productInfoList){
            //得到种类序号列表
            categoryTypeList.add(productInfo.getCategoryType());
        }
            //精简做法(java8,lambda)
        /*List<Integer> categoryTypeList = productInfoList.stream()
            .map(e->e.getCategoryType())
            .collect(Collectors.toList());*/
        //得到种类bean列表
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据拼装,ProductVO商品类别类包含同类型商品

        //不同类别商品list
        List<ProductVO> productVOList = new ArrayList<>();

        //遍历商品种类bean list,根据商品类别取出对应的商品
        for(ProductCategory productCategory: productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }

            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }


        ResultVO resultVO = new ResultVO();

       /*
       太繁琐每次都要new然后封装相同数据,封装成ResultVOUtil类
        resultVO.setData(productVOList);

        resultVO.setCode(0);
        resultVO.setMsg("success");
        return resultVO;*/
       return ResultVOUtil.success(productVOList);
    }
}