<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>人气书籍</title>
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
        $.getJSON('<%=basePath%>'+'admin/report/views/pie', function (data) {
            Highcharts.chart('container', {
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    type: 'pie'
                },
                title: {
                    text: '书籍访问量前8名',
                    style:{"fontSize": "22px"}
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        }
                    }
                },
                series: [{
                    name: 'Brands',
                    colorByPoint: true,
                    data : data
                    /*data: [{
                        name: 'Chrome',
                        y: 61.41,
                        sliced: true,
                        selected: true
                    }, {
                        name: 'Internet Explorer',
                        y: 11.84
                    }, {
                        name: 'Firefox',
                        y: 10.85
                    }, {
                        name: 'Edge',
                        y: 4.67
                    }, {
                        name: 'Safari',
                        y: 4.18
                    }, {
                        name: 'Sogou Explorer',
                        y: 1.64
                    }, {
                        name: 'Opera',
                        y: 1.6
                    }, {
                        name: 'QQ',
                        y: 1.2
                    }, {
                        name: 'Other',
                        y: 2.61
                    }]*/
                }]
            });
        })

    </script>
</body>
</html>
