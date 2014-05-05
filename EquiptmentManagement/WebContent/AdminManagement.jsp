<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.EMS.Model.AdminModel" %>
<%@ page import="java.util.Iterator" %>
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
			<div class= "span9 mt10">
				<%@include file="Information.jsp" %>
				<%
					//获取Servlet传递过来的adminList
					ArrayList<AdminModel> adminList = (ArrayList<AdminModel>)request.getAttribute("adminList");
					//通过iterator遍历adminList
					Iterator<AdminModel> adminIterator = adminList.iterator();
					
					if(adminIterator.hasNext()){
						
						//不为空
						%>
							<table class="table talble-hover">
								<thead>
									<tr>
										<th>管理员ID</th><th>姓名</th><th>是否是超级管理员</th><th>编辑</th><th>删除</th>
									</tr>
								</thead>
								<tbody>
						<%
						
						while(adminIterator.hasNext()){
							
							//从ArrayList中取出一个值并显示
							AdminModel admin = adminIterator.next();
							%>
								<tr>
									<td><%=admin.getAdminID() %></td>
									<td><%=admin.getAdminName() %></td>
									<td><%=admin.getAdminIsSuper()?"是":"否" %></td>
									<td><a class="btn btn-primary" href="AdminServlet?action=edit&adminId=<%=admin.getAdminID()%>">编辑</a></td>
									<td><a class="btn btn-primary" href="AdminServlet?action=delete&adminId=<%=admin.getAdminID()%>">删除</a></td>
								</tr>
							<%
						
						}
						
						%>
							</tbody>
							</table>
						<%
					}
				%>
			</div>
			<!-- 导航右侧正文部分 End -->

		</div>
		<!-- 中部主体部分 End -->

		<!--  底部Foot Start  -->
		<%@include file="Foot.jsp"%><!--   底部Foot End  -->


</body>
</html>