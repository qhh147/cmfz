<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="../js/echarts.min.js"></script>
<script type="text/javascript" src="../js/china.js"></script>
<div id="main" style="width: 600px;height: 400px;"></div>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'));
    var option = {
        title : {
            text: '持名法州APP用户分布图',
            subtext: '2018年6月1日 最新数据',
            left: 'center'
        },
        tooltip : {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data:['省份']
        },
        visualMap: {
            min: 0,
            max: 2500,
            left: 'left',
            top: 'bottom',
            text:['高','低'],
            calculable : true
        },
        toolbox: {
            show: true,
            orient : 'vertical',
            left: 'right',
            top: 'center',
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },series: [
            {
                name: '省份',
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                data: []
            }]
    };
    myChart.setOption(option);
    $.post('/showUserLocation',function(data){
        console.log(data);
            myChart.setOption({
                series: [{
                    name: '省份',
                    data:[{name:data.key,value:data.value}]
                }]
            });
    },"json");
</script>





























