<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <!-- Title and other stuffs -->
    <title>首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <jsp:include page="../commons/css.jsp"></jsp:include>





</head>
<body>
<!-- 头部 -->
<jsp:include page="../commons/stuhead.jsp"></jsp:include>


<div class="content">

    <!-- 左边菜单 -->
    <div class="sidebar">
        <div class="sidebar-dropdown">
            <a href="#">导航</a>
        </div>

        <ul id="nav">
            <li>
                <a href="#" class="open"><i class="icon-home"></i> 首页</a>

            </li>

            <li >
                <a href="http://localhost:8080/library/admin/findAllQishou"><i class="icon-building"></i> 骑手管理 </a>
            </li>
            <li >
                <a href="http://localhost:8080/library/admin/findAllQishouruzhang"><i class="icon-building"></i> 骑手入账管理 </a>
            </li>
            <li >
                <a href="http://localhost:8080/library/admin/findAllYonghuxinxi"><i class="icon-building"></i> 用户信息 </a>
            </li>
            <li >
                <a href="http://localhost:8080/library/admin/findAllYouhuiquanxinxi"><i class="icon-building"></i> 优惠券信息 </a>
            </li>
            <li >
                <a href="http://localhost:8080/library/admin/findAllPeisongdizhi"><i class="icon-building"></i> 配送地址管理 </a>
            </li>
        </ul>
    </div>


    <!-- 内容 -->
    <div class="mainbar">
        <div class="page-head">
            <h2 class="pull-left"><i class="icon-home"></i> 首页</h2>
            <div class="clearfix"></div>
        </div>

        <!-- Matter -->
        <div class="matter">
            <div class="container">

                <div class="row">
                    <div class="col-md-12">

                        <div class="widget">
                            <div class="widget-content">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th>优惠券编号</th>
                                        <th>优惠金额</th>
                                        <th>集单要求数</th>
                                        <th>起始日期</th>
                                        <th>结束日期</th>
                                        <th>操作</th>

                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${youhuiquanxinxilist }" var="youhuiquanxinxi">
                                        <tr>

                                            <td>${youhuiquanxinxi.yhqbh }</td>
                                            <td>${youhuiquanxinxi.yhje }</td>
                                            <td>${youhuiquanxinxi.jdyqs }</td>
                                            <td>${youhuiquanxinxi.qsrq }</td>
                                            <td>${youhuiquanxinxi.jsrq }</td>
                                            <td>
                                                <a  class="btn btn-active" href="http://localhost:8080/library/admin/borrowbook?bookname=${qishou.id}">借阅</a>
                                            </td>

                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="clearfix"></div>
</div>

<!-- 底部 -->
<jsp:include page="../commons/foot.jsp"></jsp:include>
<!-- 快速回到顶部 -->
<span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span>

</body>
<script>

</script>

</html>