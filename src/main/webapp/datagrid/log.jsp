<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    $(function () {
        $("#log").edatagrid({
            url: "/showLog",
            columns: [[
                {field: 'id', title: '编号',width:100},
                {field: 'username', title: '名称',width:100},
                {field: 'dodate', title: '时间',width:100},
                {field: 'things', title: '行为',width:100},
                {field: 'result', title: '结果',width:100}
            ]],
            fit: true,
            fitColumns: true,
            pagination: true,
            pageList: [5, 10, 15, 20],
            pageSize: 10
        })

    })
</script>
<table id="log"></table>




























