<%@ page language="java" contentType="text/html; charset=utf-8"	 pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.EMS.Model.EqpModel" %>
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
			<div class= "span9 mt10 pb10">
				<%@include file="Information.jsp" %>
				<%
					//获取Servlet传递过来的eqpList
					ArrayList<EqpModel> eqpList = (ArrayList<EqpModel>)request.getAttribute("eqpList");
					//通过iterator遍历eqpList
					Iterator<EqpModel> eqpIterator = eqpList.iterator();
					
					if(eqpIterator.hasNext()){
						
						//不为空
						%>
							<table class="table talble-hover">
								<thead>
									<tr>
										<th>设备RFID</th><th>设备名称</th><th>设备ID</th><th>规格型号</th><th>生产厂家</th><th>开始使用日期</th><th>使用年限</th><th>使用部门</th><th>使用人ID</th><th>编辑</th><th>删除</th>
									</tr>
								</thead>
								<tbody>
						<%
						
						while(eqpIterator.hasNext()){
							
							//从ArrayList中取出一个值并显示
							EqpModel eqp = eqpIterator.next();
							%>
								<tr>
									<td><%=eqp.getEqpRFID() %></td>
									<td><%=eqp.getEqpName() %></td>
									<td><%=eqp.getEqpID() %></td>
									<td><%=eqp.getEqpModel() %></td>									
									<td><%=eqp.getEqpManufacturer() %></td>
									<td><%=eqp.getEqpStartUsingDate() %></td>		
									<td><%=eqp.getEqpAvailableTime() %></td>	
									<td><%=eqp.getEqpDepartmentID() %></td>														
									<td><%=eqp.getEqpUserID()%></td>
									
									<td><a class="btn btn-primary" href="EqpServlet?action=edit&eqpID=<%=eqp.getEqpID()%>">编辑</a></td>
									<td><a class="btn btn-primary" href="EqpServlet?action=delete&eqpID=<%=eqp.getEqpID()%>">删除</a></td>
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
			<a class="btn btn-primary" href="EqpServlet?action=add">添加设备信息</a>
			<!-- 导航右侧正文部分 End -->

		</div>
		<!-- 中部主体部分 End -->

		<!--  底部Foot Start  -->
		<%@include file="Foot.jsp"%><!--   底部Foot End  -->


</body>
</html>