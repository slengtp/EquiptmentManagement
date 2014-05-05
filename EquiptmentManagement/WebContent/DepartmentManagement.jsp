<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="ScriptAndStyle.jsp"%>
<title></title>
</head>
<body>


		<!--  头部Logo Start   -->
		<%@include file="TopBanner.jsp"%><!--     头部Logo End    -->

		<!-- 中部主体部分 Start -->
		<div class="row-fluid">

			<!-- 导航部分 Start -->
			<div class="span3">
				<%@include file="Nav.jsp"%>
			</div>
			<!-- 导航部分 End -->

			<!-- 导航右侧正文部分 Start -->
			<div class="span9 mt10 pb40">
				<%@include file="Information.jsp" %>
			</div>
			<!-- 导航右侧正文部分 End -->

		</div>
		<!-- 中部主体部分 End -->

		<!--  底部Foot Start  -->
		<%@include file="Foot.jsp"%><!--   底部Foot End  -->



</body>
</html>