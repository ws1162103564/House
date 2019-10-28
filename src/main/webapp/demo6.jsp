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
        //加载显示类型
        $(function(){  //加载事件
            //使用datagrid绑异服务器数据展示
            $('#dg').datagrid({
                title:"出租房信息",
                url:'/page/findallExhousebyispass1.do',
                toolbar:"#tb",  //绑定工具栏
                pagination:true,
                pageSize:10,
                pageList:[10,20,30,40],
                columns:[[
                    {field:'id',title:'编号',width:100},
                    {field:'title',title:'标题',width:100},
                    {field:'dname',title:'区域名称',width:100},
                    {field:'sname',title:'街道名称',width:100},
                    {field:'tname',title:'类型名称',width:100},
                    {field:'floorage',title:'面积',width:100},
                    {field:'price',title:'价格',width:100},
                    {field:'contact',title:'电话',width:100},
                    {field:'dd',title:'操作',width:100,
                        formatter: function(value,row,index){
                            //同步
                            return "<a href='javascript:delDistrict("+row.id+");'>取消审核</a>";
                        }
                    },
                ]]
            });
        });

        function delDistrict(id){

            $.post("/page/goHousePass1.do",{"id":id},function(data){
                if(data.result>=1){
                    $("#dg").datagrid('reload'); //刷新
                }else{
                    $.messager.alert('提示信息','审批失败!','error');
                }
            },"json");
        }




    </script>
</head>
<body>
<table id="dg"></table>
</body>
</html>
