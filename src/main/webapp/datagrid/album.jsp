<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    var toolbar = [{
        iconCls: 'icon-search',
        text: "专辑详情",
        handler: function () {
            var row = $("#album").treegrid("getSelected")
            if (row == null) {
                $.messager.alert("警告","请先选中专辑！");
            } else {
                if (row.size == null) {
                    $("#album_dialog").dialog("open")
                    /*填充内容*/
                    $("#album_ff").form("load",row);
                    $("#img").prop("src", row.imgPath);
                } else {
                    $.messager.alert("警告","请先选中专辑！");
                }
            }
        }
    }, '-', {
        iconCls: 'icon-add',
        text: "添加专辑",
        handler: function () {
            $("#album_dialog1").dialog("open");

        }
    }, '-', {
        iconCls: 'icon-add',
        text: "添加章节",
        handler: function () {
            var row = $("#album").treegrid("getSelected");
            if (row == null) {
                $.messager.alert("警告","请先选中专辑！");
            } else {
                if (row.size == null) {
                    $("#chapter_dialog").dialog("open");
                    $("#album_id").textbox("setValue",row.id);
                } else {
                    $.messager.alert("警告","请先选中专辑！");
                }
            }

        }
    }, '-', {
        iconCls: 'icon-save',
        text: "下载音频",
        handler: function () {
            var row = $("#album").treegrid("getSelected");
            location.href = "/down?downPath=" + row.downPath + "&title=" + row.title;
        }
    }]
    $(function () {
        $('#album').treegrid({
            collapsible:true,
            url: '/showZJ',
            idField:'id',
            treeField: 'title',
            animate:true,
            lines:true,
            onDblClickRow: function (row) {
                if(row.size!=null){
                    $("#album_paly").dialog("open");
                    $("#audio").prop("src",row.downPath)
                }
            },
            toolbar: toolbar,
            columns: [[
                {field: 'title', title: '名称', width:120},
                {field: 'size', title: '大小', width: 80},
                {field: 'duration', title: '时长', width: 80},
                {field: 'downPath', title: '路径', width: 80}
            ]],
            fit:true,
            fitColumns: true,
            pagination: true,
            pageList: [5, 10, 15,20,30,50,80,100],
            pageSize:100
        });

    })


</script>

<table id="album"></table>

<div id="album_dialog1" class="easyui-dialog" title="添加专辑" style="width:400px;height:410px;padding:40px"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'保存',
				handler:function(){
                    $('#add_album').form('submit',{
                         url:'/insertZJ',
                         success:function(){
                            $('#album_dialog1').dialog('close');
                            $('#album').treegrid('load');
                         }
                     })
                }
			},{
				text:'关闭',
				handler:function(){
				    $('#album_dialog1').dialog('close');
				}
			}]">
    <form id="add_album" method="post" enctype="multipart/form-data">
        <div>
            标题:
            <input class="easyui-textbox" type="text" name="title" data-options="required:true"/>
        </div><br/>
        <div>
            集数:
            <input class="easyui-textbox" type="text" name="setCount" data-options="required:true"/>
        </div><br/>
        <div>
            好评:
            <input class="easyui-textbox" type="text" name="score" data-options="required:true"/>
        </div><br/>
        <div>
            作者:
            <input class="easyui-textbox" type="text" name="author" data-options="required:true"/>
        </div><br/>
        <div>
            播音:
            <input class="easyui-textbox" type="text" name="broadcast" data-options="required:true"/>
        </div><br/>
        <div>
            内容简介:
            <input class="easyui-textbox" type="text" name="brief" data-options="required:true"/>
        </div><br/>
        <div>
            封面：
            <input class="easyui-filebox" name="imgPath" style="width:300px">
        </div>

    </form>
</div>

<div id="album_dialog" class="easyui-dialog" title="专辑详情" style="width:400px;height:540px;padding:40px"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <form id="album_ff" method="post">
        <div>
            <label for="title">标题:</label>
            <input class="easyui-textbox" id="title" type="text" name="title" />
        </div><br/>
        <div>
            <label for="setCount">集数:</label>
            <input class="easyui-textbox" id="setCount" type="text" name="setCount" />
        </div><br/>
        <div>
            <label for="createDate">日期:</label>
            <input class="easyui-textbox" id="createDate" type="text" name="createDate" />
        </div><br/>
        <div>
            <label for="score">好评:</label>
            <input class="easyui-textbox" id="score" type="text" name="score" />
        </div><br/>
        <div>
            <label for="author">作者:</label>
            <input class="easyui-textbox" id="author" type="text" name="author" />
        </div><br/>
        <div>
            <label for="broadcast">播音:</label>
            <input class="easyui-textbox" id="broadcast" type="text" name="broadcast" />
        </div><br/>
        <div>
            <label for="brief">内容简介:</label>
            <input class="easyui-textbox" id="brief" type="text" name="brief" />
        </div><br/>
        <div>
            <label for="img">封面:</label>
            <img id="img" src="">
        </div>

    </form>
</div>

<div id="chapter_dialog" class="easyui-dialog" title="添加章节" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'保存',
				handler:function(){
                  $('#chapter_ff').form('submit',{
                    url:'/insertZE',
                    success:function(){
                        $('#chapter_dialog').dialog('close');
                        $('#album').treegrid('load')

                    }
                  })
				}
			},{
				text:'关闭',
				handler:function(){
                    $('#chapter_dialog').dialog('close');
				}
			}]">

    <form id="chapter_ff" method="post" enctype="multipart/form-data">
        <div>
            <label for="album_id">id:</label>
            <input class="easyui-textbox" value="0" name="album_id" id="album_id"/>
        </div>
        <div>
            <label for="file">上传:</label>
            <input class="easyui-filebox" id="file" name="addFile"/>
        </div>

    </form>
</div>


<div id="album_paly" class="easyui-dialog" title="在线听歌" style="width:400px;height:100px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">

    <audio id="audio" src="" controls="controls" autoplay="autoplay"></audio>

</div>

