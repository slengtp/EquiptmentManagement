<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.EMS.Model.StaffModel" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="org.EMS.BLL.DepartmentBLL" %>
<%@ page import="org.EMS.Model.DepartmentModel" %>
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
			<div class="span9 mt10">
				<%@include file="Information.jsp" %>
				
				<%
					//获取Servlet传递过来的staffList
					ArrayList<StaffModel> staffList = (ArrayList<StaffModel>)request.getAttribute("staffList");
					//通过iterator遍历staffList
					Iterator<StaffModel> staffIterator = staffList.iterator();
					
					if(staffIterator.hasNext()){
						
						//不为空
						%>
							<table class="table talble-hover">
								<thead>
									<tr>
										<th>员工ID</th><th>姓名</th><th>性别</th><th>部门</th><th>职务</th><th>工种</th><th>权限</th><th>编辑</th><th>删除</th>
									</tr>
								</thead>
								<tbody>
						<%
						
						while(staffIterator.hasNext()){
							
							//从ArrayList中取出一个值并显示
							StaffModel staff = staffIterator.next();
							DepartmentModel department = DepartmentBLL.GetModel(staff.getStaffDepartmentID());
							%>
								<tr>
									<td><%=staff.getStaffId()%></td>
									<td><%=staff.getStaffName() %></td>
									<td><%=staff.getStaffGender()?"男":"女" %></td>
									<td><%=department==null?"无部门信息":department.getDepartmentName()%></td>
									<td><%=staff.getStaffTitle() %></td>
									<td><%=staff.getStaffProfession()%></td>
									<td><%=staff.getStaffLimit()%></td>
									<td><a class="btn btn-primary" href="StaffServlet?action=edit&staffId=<%=staff.getStaffId()%>">编辑</a></td>
									<td><a class="btn btn-primary" href=" StaffServlet?action=delete&staffId=<%=staff.getStaffId()%>">删除</a></td>
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