<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="userMain" style="width: 600px;height: 400px;"></div>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('userMain'));
    var option = {
        titl: {
            text: '持名法州用户分布'
        },
        tooltip: {},
        legend: {
            data:['柱状','折线']
        },
        xAxis: {
            data:['aa','bb','cc','dd','ee']
        },
        yAxis: {},
        series: [{
            name: '柱状',
            type: 'bar',
            data: [2,4,6,8,10]
        },{
            name: '折线',
            type: 'line',
            data: [2,4,6,8,10]
        }]
    };
    /*$.post('/showSort',function(data){
        console.log(data);
        var counts=[];
        var prov=[];
        for(var i=0;i<data.length;i++){
           counts[i]=data[i].counts;
           prov[i]=data[i].province;
        }
        myChart.setOption({
            xAxis: {
                data:['aa','bb','cc','dd','ee']
            },
            series: [{
                    name: '柱状',
                    data:[2,4,6,8,10]
                },{
                    name: '折线',
                    data:[2,4,6,8,10]
            }]
        });
    },"json");*/
</script>





























