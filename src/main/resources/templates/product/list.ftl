<html>
   <#include "../common/head.ftl">
    <body>
    <div id="wrapper" class="toggled">
    <#--边栏sidebar-->
        <#include "../common/nav.ftl">

    <#--主要类容content-->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered table-hover table-condensed">
                            <thead>
                            <tr>
                                <th>商品id</th>
                                <th>名称</th>
                                <th>图片</th>
                                <th>单价</th>
                                <th>库存</th>
                                <th>描述</th>
                                <th>类目</th>
                                <th>创建时间</th>
                                <th>修改时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                    <#list productInfoPage.content as productInfo>
                    <tr>
                        <td>${productInfo.productId}</td>
                        <td>${productInfo.productName}</td>
                        <td><img src="${productInfo.productIcon}" alt="" width="100" height="100"></td>
                        <td>${productInfo.productPrice}</td>
                        <td>${productInfo.productStock}</td>
                        <td>${productInfo.productDescription}</td>
                        <td>${productInfo.categoryType}</td>
                        <td>${productInfo.createTime}</td>
                        <td>${productInfo.updateTime}</td>
                        <td><a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a></td>
                        <td>
                            <#if productInfo.getProductStatusEnum().message == "在架">
                            <a href="/sell/seller/product/offSale?productId=${productInfo.productId}">下架</a>
                                <#else>
                                <a href="/sell/seller/product/onSale?productId=${productInfo.productId}">在架</a>
                            </#if>
                        </td>


                    </tr>
                    </#list>

                            </tbody>
                        </table>

                    <#--分页-->
                        <div class="col-md-12 column">
                            <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class = "disable">
                                <a href="#">上一页</a>
                            </li>
                        <#else>
                        <li>
                            <a href="/sell/seller/order/list?page=${currentPage-1}&size=10">${index}</a>
                        </li>

                        </#if>
                            <#--1到几-->
                        <#list 1..productInfoPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="disabled">
                                    <a href="#">${index}</a>
                                </li>
                            <#else>
                                <li>
                                    <a href="/sell/seller/order/list?page=${index}&size=10">${index}</a>
                                </li>
                            </#if>
                        </#list>


                        <#if currentPage gte productInfoPage.getTotalPages()>
                            <li class = "disable">
                                <a href="#">下一页</a>
                            </li>
                        <#else>
                        <li>
                            <a href="/sell/seller/order/list?page=${currentPage+1}&size=10">${index}</a>
                        </li>

                        </#if>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    </body>
</html>