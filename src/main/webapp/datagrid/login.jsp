<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<title>持名法州后台管理中心</title>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="icon" href="../img/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="../css/common.css" type="text/css"></link>
	<link rel="stylesheet" href="../css/login.css" type="text/css"></link>
	<script type="text/javascript" src="../script/jquery.js"></script>
	<script type="text/javascript" src="../script/common.js"></script>
	<script type="text/javascript">
		var flag=false;
		$(function(){
			//点击更换验证码：
			$("#captchaImage").click(function(){//点击更换验证码
				$(this).prop("src","/code?time="+new Date());

			});
			
			//  form 表单提交
            $("#loginForm").bind("submit",function(){
                return flag;
            });
            $("#name").blur(function(){checkName();});
            $("#password").blur(function(){checkPass();});
            $("#enCode").blur(function(){checkCode();});
		});
        function checkName(){
            if($("#name").val()==""){
                alert("用户名不能为空");
                flag=false;
                return;
            }else if($("#name").val().length < 6){
                alert("用户名长度不够");
                flag=false;
                return;
            }else{
                var name=$("#name").val();
                $.ajax({
                    type:"POST",
                    url:"/checkName",
                    data:"name="+name,
                    dataType:"json",
                    success:function(result){
                        if(result.length != 3){
                           alert(result);
                            flag=false;
                        }else{
                            flag=true;
                        }

                    }
                });
            }
        }
        function checkPass(){
            if($("#password").val()==""){
                alert("密码不能为空");
                flag=false;
                return;
            }else if($("#password").val().length < 6){
                alert.html("密码长度不够");
                flag=false;
                return;
            }else{
                flag=true;
            }
        }
        function checkCode(){
            if($("#enCode").val()==""){
                alert("验证码不能为空");
                flag=false;
                return;
            }else if($("#code").val().length != 4){
               alert("验证码长度错误");
                flag=false;
                return;
            }else{
                var code=$("#enCode").val();
                $.ajax({
                    type:"POST",
                    url:"/checkCode",
                    data:"code="+code,
                    dataType:"text",
                    success:function(result){
                        if(result.length !=3){
                            alert(result);
                            flag=false;
                        }else{
                            flag=true;
                        }
                    }
                });
            }
        };
	</script>
		<div class="login">
			<form id="loginForm" action="/loginAdmin" method="post" >
				
				<table>
					<tbody>
						<tr>
							<td width="190" rowspan="2" align="center" valign="bottom">
								<img src="img/header_logo.gif" />
							</td>
							<th>
								用户名:
							</th>
							<td>
								<input type="text" id="name" name="name" class="text" maxlength="20"/>
							</td>
					  </tr>
					  <tr>
							<th>
								密&nbsp;&nbsp;&nbsp;码:
							</th>
							<td>
								<input type="password" id="password" name="password" class="text" maxlength="20" autocomplete="off"/>
							</td>
					  </tr>
					
						<tr>
							<td>&nbsp;</td>
							<th>验证码:</th>
							<td>
								<input type="text" id="enCode" name="enCode" class="text captcha" maxlength="4" autocomplete="off"/>
								<img id="captchaImage" class="captchaImage" src="/code" title="点击更换验证码"/>
							</td>
						</tr>					
					<tr>
						<td>
							&nbsp;
						</td>
						<th>
							&nbsp;
						</th>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<th>&nbsp;</th>
						<td>
							<input type="button" class="homeButton" value="" onclick="location.href='/'"><input type="submit" class="loginButton" value="登录">
						</td>
					</tr>
				</tbody></table>
				<div class="powered">COPYRIGHT © 2008-2017.</div>
				<div class="link">
					<a href="http://www.chimingfowang.com/">持名佛网首页</a> |
					<a href="http://www.chimingbbs.com/">交流论坛</a> |
					<a href="">关于我们</a> |
					<a href="">联系我们</a> |
					<a href="">授权查询</a>
				</div>
			</form>
		</div>