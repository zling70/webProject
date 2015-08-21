<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css"	href="<%=path %>/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/icon.css">
	<script type="text/javascript" src="<%=path %>/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path %>/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
		$(function(){
		      alert("hello,kitty");
		})
	</script>
</head>
<body>
	<h1>测试页面转发ss</h1>
	<h3>请求路径与springmvc中xml文件、或controller的元注释@RequestMapping进行匹配</h3>
	<br>
	<a href="/spmvc/spmvc/test">没有带后缀.do</a>
	<br>
	<a href="/spmvc/spmvc/test.do">带后缀.do</a>
	<br>
	<a href="/spmvc/spmvc/testContext?user={userid:1,username='helloworld'}">带简单请求参数</a>
	<br>
	<a href="/spmvc/spmvc/finduser">请求返回用户json</a>
	<br>
	<a href="/spmvc/spmvc/findlist">返回列表用户json</a>
	<br>
	<form action="/spmvc/spmvc/getuser" method="post">
		用户名：<input name="username" value="scott"><br>
		邮箱地址：<input name="email" value="scott@163.com">
		<br>
		<input type="submit" name="submit" value="表单数据提交">
	</form>
	
</body>
</html>