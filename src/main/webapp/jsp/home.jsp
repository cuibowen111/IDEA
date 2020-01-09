<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>首页</title>
    <%-- bootstrap的核心css --%>
    <link rel="stylesheet" type="text/css" href="${app}/jsp/assets/bootstrap/css/bootstrap.min.css"/>
    <%--jquery.js--%>
    <script src="${app}/boot/js/jquery-2.2.1.min.js"></script>
    <%--bootstrap.js--%>
    <script src="${app}/jsp/assets/bootstrap/js/bootstrap.min.js"></script>
    <%--jqgrid主题样式--%>
    <link rel="stylesheet" href="${app}/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <%-- 国际化 --%>
    <script src="${app}/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <%-- jqGird核心js --%>
    <script src="${app}/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <%--ajaxFileUpdate--%>
    <script src="${app}/boot/js/ajaxfileupload.js"></script>
    <%--echarts核心js 汉化--%>
    <script src="${app}/echarts/echarts.min.js"></script>
    <script src="${app}/echarts/china.js"></script>
    <script type="text/javascript">
        function logout() {
            $.ajax({
                url: "${app}/admin/logOut",
                datatype: "json",
                success: function () {
                    window.location.href = '${app}/jsp/login.jsp';
                }
            })
        }
    </script>
</head>
<body>
<!--导航条-->
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#aaa">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">持明法州管理系统</a>
        </div>

        <div class="collapse navbar-collapse" id="aaa">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <p class="navbar-text navbar-right">欢迎：<shiro:principal/>&nbsp;&nbsp;&nbsp;&nbsp;</p>
                </li>
                <li class="nav navbar-nav navbar-right">
                    <a href="" onclick="logout()" class="dropdown-toggle" data-toggle="dropdown">
                        <span>退出登录 </span>
                        <span class="glyphicon glyphicon-log-out"></span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!--页面布局-->
<div class="container-fluid">
    <div class="row">
        <!--菜单-->
        <div class="col-sm-2">
            <div class="panel-group" id="accordion">
                <!--手风琴-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="#collapseOne" data-toggle="collapse" data-parent="#accordion">
                                <center>用户管理</center>
                            </a>
                        </h4>
                    </div>

                    <div class="panel-collapse collapse" id="collapseOne">
                        <div class="panel-body">
                            <a href="javascript:void(0)" onclick="$('#myContent').load('jqgridAndUser.jsp')"
                               class="btn btn-danger btn-block" type="submit">
                                用户列表
                            </a>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="#collapseTwo" data-toggle="collapse" data-parent="#accordion">
                                <center>上师管理</center>
                            </a>
                        </h4>
                    </div>

                    <div class="panel-collapse collapse" id="collapseTwo">
                        <div class="panel-body">
                            <button class="btn btn-danger btn-block" type="submit">
                                1111
                            </button>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="#collapseThree" data-toggle="collapse" data-parent="#accordion">
                                <center>文章管理</center>
                            </a>
                        </h4>
                    </div>

                    <div class="panel-collapse collapse" id="collapseThree">
                        <div class="panel-body">
                            <a href="javascript:$('#myContent').load('article.jsp')" class="btn btn-danger btn-block"
                               type="submit">
                                文章列表
                            </a>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="#collapseFour" data-toggle="collapse" data-parent="#accordion">
                                <center>专辑管理</center>
                            </a>
                        </h4>
                    </div>

                    <div class="panel-collapse collapse" id="collapseFour">
                        <div class="panel-body">
                            <a href="javascript:$('#myContent').load('album.jsp')" class="btn btn-danger btn-block"
                               type="submit">
                                专辑列表
                            </a>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="#collapseFive" data-toggle="collapse" data-parent="#accordion">
                                <center>轮播图管理</center>
                            </a>
                        </h4>
                    </div>

                    <div class="panel-collapse collapse" id="collapseFive">
                        <div class="panel-body">
                            <a href="javascript:$('#myContent').load('banner.jsp')" class="btn btn-danger btn-block"
                               type="submit">
                                轮播图列表
                            </a>
                        </div>
                    </div>
                </div>


                <shiro:hasRole name="superAdmin">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a href="#collapseSix" data-toggle="collapse" data-parent="#accordion">
                                    <center>导入导出</center>
                                </a>
                            </h4>
                        </div>

                        <div class="panel-collapse collapse" id="collapseSix">
                            <div class="panel-body">
                                <a href="${app}/poi/poiExport" class="btn btn-danger btn-block" type="submit">
                                    poi导出Admin
                                </a>
                                <a href="${app}/poi/bannerEasypoi" class="btn btn-danger btn-block" type="submit">
                                    easypoi导出轮播图
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a href="#collapseSeven" data-toggle="collapse" data-parent="#accordion">
                                    <center>echarts</center>
                                </a>
                            </h4>
                        </div>

                        <div class="panel-collapse collapse" id="collapseSeven">
                            <div class="panel-body">
                                <a href="javascript:$('#myContent').load('echarts.jsp')"
                                   class="btn btn-danger btn-block" type="submit">
                                    最近七天的注册量
                                </a>
                                <a href="javascript:$('#myContent').load('monthEcharts.jsp')"
                                   class="btn btn-danger btn-block" type="submit">
                                    统计1-12月的注册量
                                </a>
                                <a href="javascript:$('#myContent').load('mapEcharts.jsp')"
                                   class="btn btn-danger btn-block" type="submit">
                                    用户地理分布图
                                </a>
                            </div>
                        </div>
                    </div>
                </shiro:hasRole>

            </div>
        </div>


        <!--菜单内容-->
        <div class="col-sm-10" id="myContent">
            <!--巨幕-->
            <div class="jumbotron">
                <p>欢迎来到持明法洲后台管理系统</p>
            </div>

            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <a href="javascript:void(0);" rel="external nofollow" rel="external nofollow"
                           rel="external nofollow" rel="external nofollow">
                            <img src="${app}/img/shouye.jpg" title="巨幕轮播图1" style="width: 100%;height: 630px;"/>
                        </a>
                    </div>
                    <div class="item">
                        <a href="javascript:void(0);" rel="external nofollow" rel="external nofollow"
                           rel="external nofollow" rel="external nofollow">
                            <img src="${app}/img/shouye.jpg" title="巨幕轮播图2" style="width: 100%;height: 630px;"/>
                        </a>
                    </div>
                    <div class="item">
                        <a href="javascript:void(0);" rel="external nofollow" rel="external nofollow"
                           rel="external nofollow" rel="external nofollow">
                            <img src="${app}/img/shouye.jpg" title="巨幕轮播图3" style="width: 100%;height: 630px;"/>
                        </a>
                    </div>
                </div>

                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>
</div>

<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" style="margin: 15px">
            <center>&copy;百知教育</center>
        </div>
    </div>
</nav>


</body>
</html>
