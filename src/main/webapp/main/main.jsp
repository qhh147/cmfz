<%@ page isELIgnored="false" language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>持名法州主页</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.edatagrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/china.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
<script type="text/javascript">
	<!--菜单处理-->
    $(function () {
        $.ajax({
            type:'post',
            url:'/show',
            success:function (result) {
                for(var i=0;i<result.length;i++){
                    var chr="";
                    for(var j=0;j<result[i].child.length;j++){
                        var child=result[i].child[j];
                        chr += "<p><a style='width:100%' href='#' class='easyui-linkbutton' onclick='javascript:(addTabs(\""+child.title+"\",\""+child.hrefPath+"\",\""+child.iconCls+"\"))'>"+child.title+"</a></p>";
                    }
                    $('#aa').accordion('add', {
                        id: "accordion" + result[i].id,
                        title: result[i].title,
                        content:chr,
                        selected: false,
                        iconCls: result[i].iconCls
                    });
                }
            }
        })
        $("#updatePass").click(function () {
            $('#up').dialog({
                title: '密码修改',
                width: 400,
                height: 200,
                closed: false,
                cache: false,
                href:'/updatePass.jsp',
                modal: true,
                buttons:[{
                    text:'保存',
                    iconCls:'icon-ok',
                    handler:function(){
                        $("#form1").form("submit",{
                            url:'/updatePass',
                            iframe:false,
                            ajax:true,
                            contentType:'application/json',
                            onSubmit:function(){
                                return $("#form1").form("validate");
                            },
                            success:function(data){
                                $("#up").dialog("close");
                                $.messager.alert("提示",data);
                            }

                        });
                    }
                },{
                    text:'关闭',
                    iconCls:'icon-remove',
                    handler:function(){
                        $('#up').dialog("close");
                    }
                }]
            });
        })
        
    })
    function addTabs(title,hrefPath,iconCls) {
        /*
         *存在选中  不存在添加
         * */
        var flag=$("#tt").tabs("exists",title);
        if (flag){
            $("#tt").tabs("select",title);
        }else {
            $('#tt').tabs('add', {
                title: title,
                selected: true,
                iconCls: iconCls,
                href:hrefPath,
                closable: true
            });

        }


    }
    
</script>

</head>
<body class="easyui-layout">   
    <div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    	<div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px" >持名法州后台管理系统</div>
        <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px"><c:if test="${sessionScope.Aname!=null}">欢迎您:${sessionScope.Aname} </c:if>&nbsp;<a href="#" id="updatePass" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="/exists" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a></div>
    </div>   
    <div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    	<div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体" >&copy;百知教育 htf@zparkhr.com.cn</div>
    </div>   
       
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    	<div id="aa" class="easyui-accordion" data-options="fit:true">
    		
		</div>  
    </div>

    <div data-options="region:'center'">
    	<div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">   
		    <div title="主页" data-options="iconCls:'icon-neighbourhood',"  style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
		</div>
        <div id="up"></div>
    </div>   
</body> 
</html>