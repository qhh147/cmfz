<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false" pageEncoding="utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    var toolbar = [{
        iconCls: 'icon-edit',
        text: "修改用户状态",
        handler: function () {
            /*将选中行变为可编辑*/
            var row = $("#dguser").datagrid("getSelections");
            if (row == null) {
                alert("请先选择要编辑的行！");
            }
            else if(row.length>1){
                alert("只能编辑一行！");
            }
            else {
                $("#dguser").edatagrid("editRow", $("#dguser").edatagrid("getRowIndex", row[0]))
            }
        }
    }, '-', {
        iconCls: 'icon-remove',
        text: "导出所有用户自定义数据",
        handler: function () {
            $("#custom_dialog").dialog("open")
        }
    },'-', {
        iconCls: 'icon-remove',
        text: "导入自定义用户数据",
        handler: function () {
            $("#xls_dialog").dialog("open")


        }
    }]

    $("#btn").click(function () {
//   提交form表单
        var titles = $("#customer_cc").combotree("getText");
        var value = $("#customer_cc").combotree("getValues");
        var c = "";
        $.each(value, function (index, title) {
            if (index != value.length - 1) {
                c += title + ",";
            } else {
                c += title;
            }
        })
        $('#customer_form').form('submit', {
            queryParams: {"titles": titles, "fileds": c},
            url:"/writeOutUser",
            onSubmit:function(){
                return $(this).form('validate');
            }
        });
        $("#custom_dialog").dialog("close");
    })

    $("#xls_btn").click(function (){
        $('#xls_form').form('submit',{
            url:"/inputUser",
            onSubmit: function(){
                return $(this).form('validate');
            },
            success:function(){
                $("#xls_dialog").dialog("close");
                $('#dgusers').datagrid('load');
            }
        });
    })

    $(function () {

        $('#customer_form').form({
            url:"/writeOutUser",
            onSubmit:function(){
                return $(this).form('validate');
            },
            success:function(){
                $("#custom_dialog").dialog("close");

            }
        });




        $("#dgusers").edatagrid({
            url: "/selectUser",
            updateUrl: "/updateUser",
            columns: [[
                {field: 'id', title: '编号', fit:true},
                {field: 'username', title: '名称', fit:true},
                {field: 'password', title: '密码', fit:true},
                {field: 'sex', title: '性别', fit:true},
                {
                    field: 'status', title: '状态',fit:true,
                    editor: {
                        type: 'text',
                        options: {required: true}
                    }
                },
                {field: 'bdate', title: '注册日期', fit:true},
                {field: 'farmington', title: '法号', fit:true},
                {field: 'nickname', title: '昵称', fit:true},
                {field: 'province', title: '所在省', fit:true},
                {field: 'city', title: '所在市', fit:true},
                {field: 'location', title: '详细地区',fit:true},
                {field: 'description', title: '个人签名',fit:true},
                {field: 'phone', title: '电话', fit:true},
                {field: 'shangshi', title: '上师', fit:true}
            ]],
            fit: true,
            fitColumns: true,
            pagination: true,
            pageList: [5, 10, 15, 20],
            pageSize: 10,
            toolbar: toolbar,
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="' + rowData.headPic + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>用户头像</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        })

    })

</script>


<table id="dgusers"></table>
<div id="custom_dialog" class="easyui-dialog" title="选项框" style="width:300px;height:200px;padding: 30px;"
     data-options="iconCls:'icon-man',resizable:true,modal:true,closed:true">
    <form id="customer_form" method="post">
        <select id="customer_cc" class="easyui-combotree" style="width:200px;"
                data-options="url:'${pageContext.request.contextPath}/json/comboTree.json',required:true,checkbox:true,onlyLeafCheck:true,multiple:true"></select>
    </form>

    <br/>
    <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">确定</a>

</div>
<div id="xls_dialog" class="easyui-dialog" title="选择文件" style="width:400px;height:200px;padding: 30px;"
     data-options="iconCls:'icon-add',resizable:true,modal:true,closed:true">
    <form id="xls_form" method="post" enctype="multipart/form-data">
       请选择用户的Excel文件:<input type="file" name="filed"/>
    </form>
    <br/>
    <a id="xls_btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">导入</a>
</div>



