<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <!-- 引入 ECharts 文件 -->
<script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
<script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main1" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    $.ajax({
        url:'/loginShowUserSort'
    })
    var myChart = echarts.init(document.getElementById('main1'));

    var goEasy = new GoEasy({
        appkey: "BS-0c79fbe4ec164de796a754e421258b28"
    });
    goEasy.subscribe({
        channel: "my_channel",
        onMessage: function (message) {
            var data = JSON.parse(message.content)
            console.log(data);
            var option = {
                title: {
                    text: '持名法州用户分布'
                },
                tooltip: {},
                legend: {
                    data:['柱状','折线']
                },
                xAxis: {
                    data: data.prov
                },
                yAxis: {},
                series: [{
                    name: '柱状',
                    type: 'bar',
                    data: data.counts
                },{
                    name: '折线',
                    type: 'line',
                    data: data.counts
                }]
            };

            myChart.setOption(option);
        }
    })

    // 使用刚指定的配置项和数据显示图表。


    // 指定图表的配置项和数据


</script>
</html>
