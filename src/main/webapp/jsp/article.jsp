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
<script charset="utf-8" src="${app}/kindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="${app}/kindeditor/lang/zh-CN.js"></script>
<script>
    $(function () {
        $("#list").jqGrid({
            url: "${app}/article/queryByPage",
            datatype: "json",
            colNames: ["Id", "标题", "作者", "创建时间", "状态", "操作"],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {name: "author", editable: true, edittype: "select"},
                {name: "create_Date"},
                {name: "status"},
                {
                    name: "option", formatter: function (cellvalue, options, rowObject) {
                        return "<button class='btn btn-primary' onclick=\"selectById('" + rowObject['id'] + "')\" data-toggle=\"modal\" data-target=\"#query\">查看详细 </button> &nbsp;&nbsp;" +
                            "<button class='btn btn-primary' onclick=\"select('" + rowObject['id'] + "')\" data-toggle=\"modal\" data-target=\"#myUpdateModal\">修改</button>"
                    }
                }
            ],
            autowidth: true,
            pager: "#pager",//定义导航栏
            styleUI: "Bootstrap",
            viewrecords: true,//定义是否要显示总记录数
            editurl: "${app}/article/edit",//定义对form编辑时的url
            rowNum: 3,//每页展示的条数
            rowList: [2, 4, 6],
            height: "550px",
            multiselect: true//定义是否可以多选
        }).jqGrid("navGrid", "#pager", {search: false, add: false, edit: false, deltext: "删除"})
    })

    /*富文本*/
    var newVar;
    $(function () {
        newVar = KindEditor.create('#editor_id', {
            minHeight: 400,
            resizeType: 0,
            allowFileManager: true,    //是否展示 图片空间
            filePostName: 'img',       //上传是后台接收的名字
            uploadJson: '${app}/kindeditor/uploadImg', //上传后台的路径
            fileManagerJson: "${app}/kindeditor/getAllImgs"
        });
    })


    /*清空数据添加过后的数据*/
    function removeadd() {
        $("#add")[0].reset();
        newVar.html("");
    }

    /*添加数据*/
    $(function () {
        $("#insert").click(function () {
            //刷新富文本
            newVar.sync();
            var serialize = $("#add").serialize();
            $.ajax({
                url: "${pageContext.request.contextPath}/article/edit?oper=add",
                datatype: "json",
                data: serialize,
                success: function () {
                    $("#list").jqGrid().trigger("reloadGrid");
                }
            })
            removeadd();
        })
    })


    /*查看详细*/
    function selectById(even) {
        $.ajax({
            url: "${pageContext.request.contextPath}/article/query",
            datatype: "json",
            data: {"id": even},
            success: function (data) {
                //数据追加到模态框中
                $("#content").append(data.content)
            }
        })
        $("#content").empty()
    }

    /*修改富文本*/
    var kind;
    $(function () {
        kind = KindEditor.create('#testarea_id', {
            minHeight: 400,
            resizeType: 0,
            allowFileManager: true,    //是否展示 图片空间
            filePostName: 'img',       //上传是后台接收的名字
            uploadJson: '${app}/kindeditor/uploadImg', //上传后台的路径
            fileManagerJson: "${app}/kindeditor/getAllImgs"
        });
    })

    /*修改回显*/
    function select(even) {
        $.ajax({
            url: "${pageContext.request.contextPath}/article/query",
            datatype: "json",
            data: {"id": even},
            success: function (data) {
                //数据追加到模态框中
                $("#id").val(data.id)
                $("#title_id").val(data.title)
                $("#author_id").val(data.author)
                $("#status_id").val(data.status)
                kind.html(data.content)
            }
        })
    }

    /*修改*/
    $(function () {
        $("#updateArticle").click(function () {
            //刷新富文本
            kind.sync()
            var serialize = $("#update").serialize();
            $.ajax({
                url: "${pageContext.request.contextPath}/article/edit?oper=edit",
                datatype: "json",
                data: serialize,
                success: function () {
                    $("#list").jqGrid().trigger("reloadGrid");
                }
            })
        })
    })

</script>
</head>
<body>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 730px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"
                                                                                                  onclick="removeadd()">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">文章的添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="add">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">标题:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" placeholder="请输入标题" name="title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">作者:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" placeholder="请输入作者" name="author">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">状态:</label>
                        <div class="col-sm-6">
                            <select name="status" class="form-control">
                                <option value="展示">展示</option>
                                <option value="不展示">不展示</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-6 control-label">内容</label>
                    </div>
                    <textarea id="editor_id" name="content" style="width:700px;height:300px;">
                    </textarea>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="insert">提交</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="removeadd()">关闭</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="query" role="dialog" aria-labelledby="aaaModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="aaaModalLabel">具体内容</h4>
            </div>
            <div class="modal-body" id="content"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myUpdateModal" role="dialog" aria-labelledby="myUpdateModalLabel">
    <div class="modal-dialog" role="document" style="width: 730px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"
                                                                                                  onclick="removeadd()">&times;</span>
                </button>
                <h4 class="modal-title" id="myUpdateModalLabel">文章的修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="update">
                    <div style="display:none">
                        <input type="text" class="form-control" placeholder="请输入标题" name="id" id="id">
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">标题:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" placeholder="请输入标题" name="title" id="title_id">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">作者:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" placeholder="请输入作者" name="author" id="author_id">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">状态:</label>
                        <div class="col-sm-6">
                            <select name="status" class="form-control" id="status_id">
                                <option value="展示">展示</option>
                                <option value="不展示">不展示</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-6 control-label">内容</label>
                    </div>
                    <textarea id="testarea_id" name="content" style="width:700px;height:300px;">
                    </textarea>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="updateArticle">提交</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="removeadd()">关闭</button>
            </div>
        </div>
    </div>
</div>


<div class="page-header">
    <h1>文章管理</h1>
</div>
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active">
        <a href="#home" aria-controls="home" role="tab" data-toggle="tab">文章列表</a>
    </li>
    <li role="presentation">
        <a data-toggle="modal" data-target="#myModal" id="bedata">添加文章 </a>
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