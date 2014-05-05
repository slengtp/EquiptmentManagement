<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="org.EMS.Model.AdminModel" %>	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="ScriptAndStyle.jsp"%>
<title>编辑管理员信息</title>
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
			<div class="span9 mt10">
				<%@include file="Information.jsp" %>
			
				<%
					AdminModel admin = (AdminModel)request.getAttribute("adminModel");
				%>
				
			
				<form role="form" action="AdminServlet?action=edit" method="post">
					<div class="form-group">
						<label for="adminID">管理员ID</label>
						<input type="text" name="adminID" id="adminID" class="form-control" placeholder="请输入管理员ID"  value='<%=admin.getAdminID() %>' readonly/>
					</div>
					
					<div class="form-group">
						<label for="adminName">姓名</label>
						<input type="text" name="adminName" id="adminName" class="form-control" placeholder="请输入姓名"  value=''<%=admin.getAdminName() %>' />
					</div>
					
					<div class="form-group">
						<label for="adminPassword">密码</label>
						<input type="password" name="adminPassword" id="adminPassword" class="form-control" placeholder="请输入密码" />
					</div>
					
					<div class="form-group">
						<label for="adminPasswordConfirm">确认密码</label>
						<input type="password" id="adminPasswordConfirm" class="form-control" placeholder="请确认密码" />
					</div>

					<div class="checkbox">
						<label> 
							<%if(admin.getAdminIsSuper()){
								
								%>
									<input type="checkbox" name="adminIsSuper" checked> 
								<%
							}
							else{
								%>
									<input type="checkbox" name="adminIsSuper"> 
								<%
							}
							%>							
							是否是超级管理员</label>
					</div>
					
					<button class="btn btn-default" type="submit" name="save" value="save">提交</button>
				</form>
			</div>
			<!-- 导航右侧正文部分 End -->

		</div>
		<!-- 中部主体部分 End -->

		<!--  底部Foot Start  -->
		<%@include file="Foot.jsp"%><!--   底部Foot End  -->



</body>
</html>