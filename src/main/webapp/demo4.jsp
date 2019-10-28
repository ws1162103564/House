<%--
  Created by IntelliJ IDEA.
  User: 王森
  Date: 2019/10/18
  Time: 14:50
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
    <script>
        //加载显示类型
        $(function(){  //加载事件
            //使用datagrid绑异服务器数据展示
            $('#dg').datagrid({
                title:"类型信息",
                url:'getUserByPage.do',
                toolbar:"#dddd",  //绑定工具栏
                pagination:true,
                pageSize:5,
                pageList:[5,10,15,20],
                columns:[[
                    {field:'opt',checkbox:"true",title:'编号',width:100},
                    {field:'id',title:'编号',width:100},
                    {field:'name',title:'用户名',width:100},
                    {field:'telephone',title:'电话',width:100},
                   /* {field:'dd',title:'操作',width:100,
                        formatter: function(value,row,index){
                            //同步
                            return "<a href='javascript:delDistrict("+row.id+");'>删除</a> | <a href=''>修改</a>";
                        }
                    }*/
                ]]
            });
        });

        //搜索
        function search(){
            //$("#dg").datagrid("load",查询条件格式:{"名称":值,"名称":值..});
            var name=$("#sname").val();
            var telephone=$("#stelephone").val();
            $("#dg").datagrid("load",{"name":name,"telephone":telephone});
        }

    </script>
</head>
<body>
<table id="dg"></table>

<div id="dddd">
   姓名: <input type="text" name="" id="sname">&nbsp;&nbsp;&nbsp;
   电话: <input type="text" name="" id="stelephone">&nbsp;&nbsp;&nbsp;
    <a href="javascript:search()" class="easyui-linkbutton"
       iconCls="icon-search" plain="true">搜索</a>
</div>
</body>
</html>
