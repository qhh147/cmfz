<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <div style="margin:auto 100px">


    <form id="form1">
        <div>
            <label for="password1">旧密码:</label>
            <input class="easyui-validatebox" id="password1" type="text" name="password1" data-options="required:true" />
        </div>
        <div>
            <label for="password">新密码:</label>
            <input class="easyui-validatebox" id="password" type="text" name="password" data-options="required:true" />
        </div>
        <div>
            <label for="repass">再次输入新密码:</label>
            <input class="easyui-validatebox" id="repass" type="text" name="repass" data-options="required:true" />
        </div>
    </form>
    </div>
</html>
