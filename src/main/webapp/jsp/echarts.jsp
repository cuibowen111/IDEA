<%@ page isELIgnored="false" pageEncoding="utf-8" contentType="text/html; utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="${app}/boot/js/jquery-2.2.1.min.js"></script>
    <script src="${app}/echarts/echarts.min.js"></script>
    <script src="${app}/echarts/china.js"></script>
    <script type="text/javascript">
        $(function () {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '折线图堆叠'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['最近七天的注册量']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: ['One', 'Two', 'Three', 'Four', 'Five', 'Six', 'Seven']
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: '最近七天的注册量',
                        type: 'line',
                        stack: '总量',
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);

            $.ajax({
                url: "${app}/UserEcharts/selelctUSerEcharts",
                datatype: "json",
                success: function (data) {
                    //数据追加到模态框中
                    console.log(data);
                    myChart.setOption({
                        series: [
                            {
                                data: data
                            }
                        ]
                    });
                }
            })
        })
    </script>
</head>
<body>
<div id="main" style="width: 1000px;height:500px;"></div>
</body>
</html>