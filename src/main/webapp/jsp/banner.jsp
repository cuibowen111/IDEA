<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<style>
    .h1, .h2, .h3, h1, h2, h3 {
        margin-top: -40px;
        margin-bottom: 10px;
    }
</style>
<script>
    $(function () {
        $("#list").jqGrid({
            url: "${app}/banner/queryByPage",
            datatype: "json",
            colNames: ["Id", "标题", "状态", "图片", "创建时间"],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {
                    name: "status", editable: true, edittype: "select",
                    editoptions: {
                        value: "正常:正常;冻结:冻结"
                    }
                },
                {
                    name: "img", editable: true, edittype: "file",
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img src='${app}/upload/" + cellvalue + "' style='height:100px;width:100%'>"
                    }
                },
                {name: "create_Date"}
            ],
            autowidth: true,
            pager: "#pager",//定义导航栏
            styleUI: "Bootstrap",
            viewrecords: true,//定义是否要显示总记录数
            editurl: "${app}/banner/edit",//定义对form编辑时的url
            rowNum: 2,//每页展示的条数
            rowList: [2, 4, 6],
            height: "550px",
            multiselect: true//定义是否可以多选
        }).jqGrid("navGrid", "#pager", {search: false, addtext: "添加", edittext: "修改", deltext: "删除"},
            {//修改
                closeAfterEdit: true,
                afterSubmit: function (response) {
                    var id = response.responseJSON.bannerId;
                    if (id != null) {
                        $.ajaxFileUpload({
                            url: "${pageContext.request.contextPath}/banner/upload",
                            fileElementId: 'img',
                            data: {bannerId: id},
                            type: "post",
                            success: function () {
                                $("#list").jqGrid().trigger("reloadGrid");
                                $("#aaaDiv").show();
                                setTimeout(function () {
                                    $("#aaaDiv").hide();
                                }, 3000)
                            }
                        })
                    }
                    return response;
                }
            },
            {//添加
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var id = response.responseJSON.bannerId;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/banner/upload",
                        fileElementId: 'img',
                        data: {bannerId: id},
                        type: "post",
                        success: function () {
                            $("#list").jqGrid().trigger("reloadGrid");
                            $("#msgDiv").show();
                            setTimeout(function () {
                                $("#msgDiv").hide();
                            }, 5000)
                        }
                    })
                    return response;
                }
            },
            {//删除

            }
        )
    })
</script>
</head>
<body>
<div class="page-header">
    <h1>轮播图管理</h1>
</div>
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">轮播图列表</a>
    </li>
</ul>
<div class="tab-content"></div>
<table id="list"></table>
<div id="pager" style="height: 50px"></div>
<div class="alert alert-danger" style="display:none" id="msgDiv">
    添加成功
</div>
<div class="alert alert-danger" style="display:none" id="aaaDiv">
    修改成功
</div>
</body>
</html>