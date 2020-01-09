<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<style>
    .ui-jqgrid .ui-userdata {
        padding: 13px 40%;
        overflow: hidden;
        min-height: 32px;
    }

    .modal-body {
        position: relative;
        padding: 15px 130px;
    }

    .h1, .h2, .h3, h1, h2, h3 {
        margin-top: -40px;
        margin-bottom: 10px;
    }
</style>
<script>
    $(function () {
        $("#album").jqGrid({
            url: "${app}/album/queryByPage",
            datatype: "json",
            colNames: ["Id", "专辑", "图片", "分数", "作者", "播音人", "数量", "简介", "创建时间", "状态"],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {
                    name: "img", editable: true, edittype: "file",
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img src='${app}/upload/" + cellvalue + "' style='height:100px;width:100%'>"
                    }
                },
                {name: "score", editable: true},
                {name: "author", editable: true},
                {name: "broadcaster", editable: true},
                {name: "count"},
                {name: "brief", editable: true},
                {name: "create_Date", editable: true, edittype: "date"},
                {
                    name: "status", editable: true, edittype: "select",
                    editoptions: {
                        value: "正常:正常;冻结:冻结"
                    }
                }
            ],
            autowidth: true,
            pager: "#pager",//定义导航栏
            styleUI: "Bootstrap",
            viewrecords: true,//定义是否要显示总记录数
            rowNum: 3,//每页展示的条数
            rowList: [2, 4, 6],
            height: "550px",
            subGrid: true,         //开启子表格
            editurl: "${app}/album/edit",
            subGridRowExpanded: function (subGridId, albumId) {
                //添加子表格的方法
                addSubGrid(subGridId, albumId);
            }
        }).jqGrid("navGrid", "#pager", {search: false, addtext: "添加", edittext: "修改", deltext: "删除"},
            {//修改
                closeAfterEdit: true,
                afterSubmit: function (response) {
                    var id = response.responseJSON.albumId;
                    $.ajaxFileUpload({
                        url: "${app}/album/upload",
                        fileElementId: 'img',
                        data: {albumId: id},
                        type: "post",
                        success: function () {
                            $("#album").jqGrid().trigger("reloadGrid");
                            $("#msgDiv").show();
                            setTimeout(function () {
                                $("#msgDiv").hide();
                            }, 5000)
                        }
                    })
                    return response;
                }
            },
            {//添加
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var id = response.responseJSON.albumId;
                    $.ajaxFileUpload({
                        url: "${app}/album/upload",
                        fileElementId: 'img',
                        data: {albumId: id},
                        type: "post",
                        success: function () {
                            $("#album").jqGrid().trigger("reloadGrid");
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

    //添加子表格
    function addSubGrid(subGridId, albumId) {
        //动态table  id
        var subGridTableId = subGridId + "table";
        //动态div id
        var subGridDivId = subGridId + "div";
        //动态添加子表格
        $("#" + subGridId).html("<table id='" + subGridTableId + "'></table>" +
            "<div id='" + subGridDivId + "' style='height: 50px'></div>"
        )
        $("#" + subGridTableId).jqGrid({
            url: "${app}/chapter/queryByPage?id=" + albumId + "",
            styleUI: "Bootstrap",
            datatype: "json",
            autowidth: true,
            records: true,
            viewrecords: true,//定义是否要显示总记录数
            rowNum: 3,
            height: "220px",
            caption: "章节",
            toolbar: [true, "top"],
            pager: "#" + subGridDivId,
            multiselect: true,//定义是否可以多选
            rowList: [3, 6, 9],
            colNames: ["Id", "歌曲", "专辑id", "文件大小", "时长", "音频", "状态"],
            colModel: [
                {name: "id", width: "280px"},
                {name: "title", editable: true},
                {name: "album_Id", width: "280px"},
                {name: "size"},
                {name: "duration"},
                {
                    name: "src", editable: true, edittype: "file", width: "300px",
                },
                {
                    name: "status", editable: true, edittype: "select",
                    editoptions: {
                        value: "正常:正常;冻结:冻结"
                    }
                }
            ],
            editurl: "${app}/chapter/edit?albumId=" + albumId + ""
        }).jqGrid("navGrid", "#" + subGridDivId, {search: false, addtext: "添加", edittext: "修改", deltext: "删除"},
            {//修改
                closeAfterEdit: true,
                afterSubmit: function (response) {
                    var id = response.responseJSON.chapterId;
                    if (id != null) {
                        $.ajaxFileUpload({
                            url: "${app}/chapter/upload",
                            fileElementId: 'src',
                            data: {chapterId: id},
                            type: "post",
                            success: function () {
                                $("#album").jqGrid().trigger("reloadGrid");
                                $("#msgDiv").show();
                                setTimeout(function () {
                                    $("#msgDiv").hide();
                                }, 5000)
                            }
                        })
                    }
                    return response;
                }
            },
            {//添加
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var id = response.responseJSON.chapterId;
                    $.ajaxFileUpload({
                        url: "${app}/chapter/upload",
                        fileElementId: 'src',
                        data: {chapterId: id},
                        type: "post",
                        success: function () {
                            $("#album").jqGrid().trigger("reloadGrid");
                            $("#msgDiv").show();
                            setTimeout(function () {
                                $("#msgDiv").hide();
                            }, 5000)
                        }
                    })
                    return response;
                }
            },
            {
                closeAfterdel: true
            }
        )

        //添加按钮
        $("#t_" + subGridTableId).html("<button class='btn btn-danger' onclick=\"play('" + subGridTableId + "')\">播放 <span class='glyphicon glyphicon-play'></span></button>" +
            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
            "<button class='btn btn-danger' onclick=\"downLoad('" + subGridTableId + "')\">下载 <span class='glyphicon glyphicon-arrow-down'></span></button>"
        )
    }

    //播放音频
    function play(subGridTableId) {
        //获取标签行id
        var gr = $("#" + subGridTableId).jqGrid('getGridParam', 'selrow')
        if (gr == null) {
            alert("请选中要播放的音频")
        } else {
            //根据id拿到对应的值
            var data = $("#" + subGridTableId).jqGrid("getRowData", gr)
            $('#myModel').modal('show')
            $("#myAudio").attr("src", "${app}/mp3/" + data.src)
        }
    }

    //下载
    function downLoad(subGridTableId) {
        //获取标签行id
        var gr = $("#" + subGridTableId).jqGrid('getGridParam', 'selrow')
        if (gr == null) {
            alert("请选中要下载的音频")
        } else {
            //根据id拿到对应的值
            var data = $("#" + subGridTableId).jqGrid("getRowData", gr)
            //获得值
            var src = data.src;
            location.href = '${app}/chapter/downLoad?src=' + src;
        }
    }
</script>


</head>
<body>

<div class="modal fade" tabindex="-1" role="dialog" id="myModel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"
                                                                                                  onclick="document.getElementById('myAudio').pause()">&times;</span>
                </button>
                <h4 class="modal-title">播放器</h4>
            </div>
            <div class="modal-body">
                <audio src="" controls="controls" width="30px" id="myAudio"></audio>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"
                        onclick="document.getElementById('myAudio').pause()">Close
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="page-header">
    <h1>专辑管理</h1>
</div>
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">专辑列表</a>
    </li>
</ul>
<div class="tab-content"></div>

<table id="album"></table>
<div id="pager" style="height: 50px"></div>
<div class="alert alert-danger" style="display:none" id="msgDiv">
    添加成功
</div>
<div class="alert alert-danger" style="display:none" id="aaaDiv">
    修改成功
</div>
</body>
</html>