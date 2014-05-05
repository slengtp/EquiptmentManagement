<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>登录</title>
	<%@include file="ScriptAndStyle.jsp" %>	
	<link rel="stylesheet" href="css/login.css" type="text/css">
</head>
<body>
	<div class="container-fluid signup">

    <!--     头部Logo开始    -->
    <%@include file="TopBanner.jsp" %>
    <!--     头部Logo结束    -->
    
    
    <!--     中间简要介绍开始       -->
    <div class="row-fluid container">
        <div class="span6">
            <div class="signup-sell">
                <h1>
                    Hello, world!
                </h1>

                <p>
                    企业设备管理系统
                </p>
                <%@include file="Information.jsp" %>

                <p>
                    <a class="btn btn-primary btn-large" href="#">参看更多 »</a>
                </p>
            </div>
        </div>
        <!--     中间登录开始       -->
        <div class="span6">
            <div class="signup-login">
            <form method="post" action="LoginServlet">
            	<div>
					<input type="text" name="userName" id="userName" class="nameText"/>
					<input type="password" name = "password" id="password" class="passwordText"/>
				</div>
				<div>
                	<button class="btn btn-primary btn-large button-login" type="submit">登录</button>
                	<a class="btn btn-primary btn-large button-resign" href="register.jsp">注册</a>
                </div>
              </form>
            </div>
        </div>
    </div>
    <!--     中间部分结束       -->
    <!--         底部              -->
    <%@include file="Foot.jsp" %>
    <!--         底部结束        -->
</div>
	
</body>
</html>