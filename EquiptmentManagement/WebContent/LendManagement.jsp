<%@ page language="java" contentType="text/html; charset=utf-8"	 pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.EMS.Model.LendModel" %>
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
					//获取Servlet传递过来的lendList
					ArrayList<LendModel> lendList = (ArrayList<LendModel>)request.getAttribute("lendList");
					//通过iterator遍历lendList
					Iterator<LendModel> lendIterator = lendList.iterator();
					
					if(lendIterator.hasNext()){
						
						//不为空
						%>
							<table class="table talble-hover">
								<thead>
									<tr>
										<th>借出归还单编号</th><th>设备RFID</th><th>借出日期</th><th>归还日期</th><th>归还期限</th><th>借出人</th><th>归还人</th><th>编辑</th><th>删除</th>
									</tr>
								</thead>
								<tbody>
						<%
						
						while(lendIterator.hasNext()){
							
							//从ArrayList中取出一个值并显示
							LendModel lend = lendIterator.next();
							%>
								<tr>
									<td><%=lend.getLendID() %></td>
									<td><%=lend.getLendEqpRFID() %></td>
									<td><%=lend.getLendDate() %></td>
									<td><%=lend.getLendTime() %></td>
									<td><%=lend.getLendDeadline() %></td>									
									<td><%=lend.getLendFromCharge() %></td>
									<td><%=lend.getLendToPeople() %></td>
									<td><a class="btn btn-primary" href="LendServlet?action=edit&lendID=<%=lend.getLendID()%>">编辑</a></td>
									<td><a class="btn btn-primary" href="LendServlet?action=delete&lendID=<%=lend.getLendID()%>">删除</a></td>
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
			<a class="btn btn-primary" href="LendServlet?action=add">添加借出归还信息信息</a>
			<!-- 导航右侧正文部分 End -->

		</div>
		<!-- 中部主体部分 End -->

		<!--  底部Foot Start  -->
		<%@include file="Foot.jsp"%><!--   底部Foot End  -->


</body>
</html>