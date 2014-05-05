<%@ page language="java" contentType="text/html; charset=utf-8"	 pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.EMS.Model.BreakModel" %>
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
					//获取Servlet传递过来的breakList
					ArrayList<BreakModel> breakList = (ArrayList<BreakModel>)request.getAttribute("breakList");
					//通过iterator遍历breakList
					Iterator<BreakModel> breakIterator = breakList.iterator();
					
					if(breakIterator.hasNext()){
						
						//不为空
						%>
							<table class="table talble-hover">
								<thead>
									<tr>
										<th>故障ID</th><th>故障设备RFID</th><th>故障设备ID</th><th>故障描述</th><th>故障报修人</th><th>故障报修时间</th><th>编辑</th><th>删除</th>
									</tr>
								</thead>
								<tbody>
						<%
						
						while(breakIterator.hasNext()){
							
							//从ArrayList中取出一个值并显示
							BreakModel brk = breakIterator.next();
							%>
								<tr>
									<td><%=brk.getBreakID() %></td>
									<td><%=brk.getBreakEqpRFID() %></td>
									<td><%=brk.getBreakEqpID() %></td>
									<td><%=brk.getBreakDescribe() %></td>
									<td><%=brk.getBreakFixman() %></td>									
									<td><%=brk.getBreakReportDate() %></td>
									
									<td><a class="btn btn-primary" href="BreakServlet?action=edit&breakID=<%=brk.getBreakID()%>">编辑</a></td>
									<td><a class="btn btn-primary" href="BreakServlet?action=delete&breakID=<%=brk.getBreakID()%>">删除</a></td>
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
			<a class="btn btn-primary" href="BreakServlet?action=add">添加故障信息</a>
			<!-- 导航右侧正文部分 End -->

		</div>
		<!-- 中部主体部分 End -->

		<!--  底部Foot Start  -->
		<%@include file="Foot.jsp"%><!--   底部Foot End  -->


</body>
</html>