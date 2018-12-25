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
                    <form role="form" method="post" action="/sell/seller/category/save">
                        <div class="form-group">
                            <label >类目名称</label>
                            <input name="categoryName" type="text" class="form-control"  value="${(productCategory.categoryName)!""}"/>
                        </div>

                        <div class="form-group">
                            <label >类目编号</label>
                            <input name="categoryType" type="number" class="form-control"  value="${(productCategory.categoryType)!""}"/>
                        </div>
                        <input type="hidden" name="categoryId" value="${(productCategory.categoryId)!""}">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>