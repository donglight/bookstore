<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>热销书籍</title>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script src="js/highcharts/highcharts.js"></script>
    <script src="js/highcharts/modules/exporting.js"></script>
    <link rel="stylesheet" href="css/bs.css"/>
    <script src="js/base-loading.js"></script>
</head>
<body>
    <div class="row" id="center_header" style="height: 100px;border-bottom: 1px solid #CCCCCC;">
    </div>
    <div id="container" style="min-width:400px;height:400px"></div>
    <script>
        var date = new Date();
        $.getJSON('<%=basePath%>'+'admin/report/sales/bar', function (data) {
            Highcharts.chart('container', {
                chart: {
                    type: 'bar'
                },
                title: {
                    text: '图书总销售额条形图'
                },
                subtitle: {
                    text: '数据来源: dd.com'
                },
                xAxis: {
                    categories: data.bookNames,
                    title: {
                        text: null
                    }
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: '销售量 (本)',
                        align: 'high'
                    },
                    labels: {
                        overflow: 'justify'
                    }
                },
                tooltip: {
                    valueSuffix: ' 本'
                },
                plotOptions: {
                    bar: {
                        dataLabels: {
                            enabled: true,
                            allowOverlap: true // 允许数据标签重叠
                        }
                    }
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'top',
                    x: -40,
                    y: 100,
                    floating: true,
                    borderWidth: 1,
                    backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
                    shadow: true
                },
                series: [{
                    name: date.getFullYear(),
                    data: data.sales
                }]
            });
        })

    </script>
</body>
</html>
