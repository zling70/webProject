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
		      $("#bindparam").bind("click",function(){
		    	  alert("按钮绑定单击事件$.ajax()提交");
		    	  $.ajax({ 
						url: "/spmvc/spmvc/finduser",
						dataType:"json", 
						type:"POST",
						async:true,
						context: document.body, //指明回调函数所在上下文元素是body
						success: function(data){
				        	//var dv=$(this).children("#dataview");
				        	//dv.html(data.name);
				        	alert("ajax返回的是用户信息是："+data.username);
				        	var vd=$("div[name='dataviwe']");
				        	vd.html(vd.html()+data.username);
				      	}
					});
		      });
		      var user=JSON.stringify({id:"002",username:"关羽",password: "123",email:"abc@163.com",alist:[{"uid":"002","addr":"黄山路205"},{"uid":"002","addr":"泰山路"}]});
		      $("#createuser").bind("click",function(){
		    	  //先定义要传递到后台的数据结构（真实项目需要从表单动态获取）：
		    	  //根据通常思考考虑用下面方法进行定义，但是springmvc后台解析时会报错（springmvc以后版本会不会支持有待考察）
		    	  var user={id:"002",
		    			    username:"关羽",
		    			    password: "123",
		    			    email:"abc@163.com",
		    			    alist:[{"uid":"002","addr":"黄山路205"},{"uid":"002","addr":"泰山路"}]
		    	  };
		    	  //正确应当下面方法
		    	  var user={id:"002",
			    			username:"关羽",
			    			password: "123",
			    			email:"abc@163.com",
			    			birthday:"2015-05-20",
			    			"alist[0].uid":"002",
			    			"alist[0].addr":"泰山路",
			    			"alist[1].uid":"002",
			    			"alist[1].addr":"黄山路205"
		    			  };
		    	  alert("按钮绑定单击事件$.post()提交3");
		    	  
		    	  $.post("/spmvc/spmvc/createuser", 
		    			 user,
		    		     function(data){process(data);}, 
		    		     "json"
		    	   );
		    	  /* 
		    	  $.ajax({ 
						url: "/spmvc/spmvc/createuser",
						dataType:"json", 
						data:user,
						type:"POST",
						async:true,
						context: document.body, //指明回调函数所在上下文元素是body
						success: function(data){
				        	alert("ajax返回的是用户信息是："+data.username);
				        	console.log(data);
				      	}
					});
		    	    */
		      });
		      function process(data){
		    	  alert("ajax方法post()回调函数返回的json数据，编号："+data.id+",用户名"+data.username);
		    	  console.log("编号："+data.id+",用户名:"+data.username+"电子邮件:"+data.email);
		    	  console.log(data);
		      }
		})
	</script>
</head>
<body>
	<h1>测试页面转发</h1>
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
	<button id="bindparam">ajax提交邦定参数</button>
	<button id="createuser">post提交邦定参数</button>
	<br>
	<a href="/spmvc/spmvc/forward?account=abcdef">由控制器转发</a>
	<br>
	<a href="/spmvc/spmvc/redirectone">由控制器重定向</a>
	<form action="/spmvc/spmvc/getuser" method="post">
		用户名：<input name="username" value="scott"><br>
		邮箱地址：<input name="email" value="scott@163.com">
		<br>
		<input type="submit" name="submit" value="表单数据提交">
	</form>
	<div id="dataviwe" name="dataviwe">
		此处显示ajax结果数据：
	</div>
	<div id="other">
		其它div
	</div>
</body>
</html>