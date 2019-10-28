<%--
  Created by IntelliJ IDEA.
  User: xiemin
  Date: 2019/10/14
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
    <script src="js/jquery-1.8.3.js"></script>
    <!--jquery.easyui.min.js包含了easyUI中的所有插件-->
    <script src="js/jquery.easyui.min.js"></script>
    <script language="JavaScript">
       $(function () {
           $('#dg').datagrid({
               title:"区域信息",
               toolbar:"#tb",
               url:'gettypeFenye.do',
               pagination:true,
               pageNumber:1,
               pageSize:3,
               pageList:[3,6,9],
               columns:[[
                   {field:'opt',checkbox:"true",title:'编号',width:100},
                   {field:'id',title:'id',width:100},
                   {field:'name',title:'name',width:100},
                   {field:'caozuo',title:'操作',width:100}
               ]]
           });
       });
       function add() {

           $("#AddDialog").dialog("open");
       }

       <%-------------------------------添加-----------------------------------%>
        /*添加*/
       function SaveDialog() {
           $('#ff').form("submit",{
               url:"inserttypeByName.do",
               success:function(data){
                   data=$.parseJSON(data);
                   if (data.result == 1){
                       $.messager.alert('提示信息','插入成功','pageUtils');
                       $("#dg").datagrid("reload");
                       $("#AddDialog").dialog("close");
                   }else {
                       $.messager.alert('提示信息','插入失败','pageUtils');
                       $("#dg").datagrid("reload");
                       $("#AddDialog").dialog("close");
                   }
               }
           });
       }
       <%-------------------------------修改-----------------------------------%>
       /*修改*/
       function ModifyBySelect() {
           //判断用户是否选择行
           //获取dagagrid选中的行
           var SelectRows = $("#dg").datagrid('getSelections');
           if(SelectRows.length==1){
               //获取当前行的编号--》查询当前记录-->还原表单
               //1.获取当前的编号
               var bh=SelectRows[0].id;
               //2.发送异步请求获取服务器数据
               $.post("gettypeid.do",{"id":bh},function(data){
                   //3.还原加载表单数据  //data的格式:{"id":1002,"name":"西城东区"}
                   $("#dd").form('load',data);
               },"json");
               //获得行对象的数据加载到表单中显示
               //$("#upForm").form('load',{"名秒":值,"名称":值});

               $("#xiugai").dialog("setTitle","修改区域");
               $("#xiugai").dialog("open"); //打开  close 关闭
           }else{
               //消息框 alert
               $.messager.alert('提示信息','你没有选中行或者选中多行','warning');
           }
       }

       function xgDialog(){
           //使用easuy提交表单
           $('#dd').form('submit',{   //提交按钮
               url:"uptype.do",
               success:function(data){ //获得是json字符串
                   //将json字符串转化为json对象
                   data=$.parseJSON(data);
                   if(data.result==1){

                       $("#dg").datagrid('reload'); //刷新
                       $("#xiugai").dialog("close");//关闭窗口
                   }else{
                       $.messager.alert('提示信息','修改失败!','error');
                   }
               }
           });
       }


       <%-------------------------------批量删除-----------------------------------%>
       /*删除区域*/
       /*批量删除区域*/
       function DeleteByFruitName(){
           //获取dagagrid选中的行
           var SelectRows = $("#dg").datagrid('getSelections');
           if(SelectRows.length==0){
               $.messager.alert('提示信息', '你还没有选择删除项',"pageUtils");
           }else
           {
               $.messager.confirm('删除区域', '真的想删除吗?', function(r){
                   if (r) {
                       //拼接删除的字符串
                       var ids = "";
                       for (var i = 0; i < SelectRows.length; i++) {
                           ids = ids + SelectRows[i].id + ",";
                       }
                       ids = ids.substring(0, ids.length - 1);

                       //发送异步请求删除
                       //删除   ids值的格式是:1006,1007..
                       $.post("delMoretype.do",{"ids":ids},function(data){
                           if(data.result>=1){
                               $("#dg").datagrid('reload'); //刷新
                           }else{
                               $.messager.alert('提示信息','删除失败!','error');
                           }
                       },"json");

                   }
               });
           }
       }





    </script>
</head>
<body>
<table id="dg"></table>


<div id="tb">
    <a href="javascript:add()" class="easyui-linkbutton"
       iconCls="icon-add" plain="true">添加</a>
    <a href="javascript:ModifyBySelect()" class="easyui-linkbutton"
       iconCls="icon-edit" plain="true">修改</a>
    <a href="javascript:DeleteByFruitName()" class="easyui-linkbutton"
       iconCls="icon-remove" plain="true">删除</a>
</div>

<%-------------------------------添加-----------------------------------%>

<!--制作添加区域的对话框-->
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 200px; padding: 50px 20px;" closed="true">
    <form action="" id="ff" name="add" method="post">
        区域名称:<input type="text" name="name"><br/>

    </form>
</div>

<!--对话框的按钮-->
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a>
    <a href="javascript:CloseDialog(AddDialog)"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

<%-------------------------------修改-----------------------------------%>

<!--制作修改区域的对话框-->
<div id="xiugai" class="easyui-dialog" buttons="#xiugaiDialogButtons"
     style="width: 280px; height: 200px; padding: 50px 20px;" closed="true">
    <form action="" id="dd" name="add" method="post">
        区域名称:<input type="text"  name="name"><br/>
        <input type="hidden" name="id" id="id">
    </form>
</div>


<!--修改对话框的按钮-->
<div id="xiugaiDialogButtons">
    <a href="javascript:xgDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">修改</a>
    <a href="javascript:CloseDialog(xiugai)"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

<%-------------------------------删除-----------------------------------%>


</body>
</html>
