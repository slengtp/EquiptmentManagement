<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="org.EMS.Model.RepairModel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="org.EMS.BLL.RepairBLL" %>	
<%@ page import="org.EMS.Model.RepairModel" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="ScriptAndStyle.jsp"%>
<title>编辑维修信息</title>
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
				
				<%
					RepairModel repair = (RepairModel)request.getAttribute("RepairModel");
				%>
			
				<form role="form" action="RepairServlet?action=add" method="post">

					<input type="hidden" name="rpID" id="rpID" class="form-control" placeholder="请输入维修ID" />
					
					<div class="form-group">
						<label for="rpEqpRFID">维修设备RFID</label>
						<input type="text" name="rpEqpRFID" id="rpEqpRFID" class="form-control" placeholder="请输入维修设备RFID" />
					</div>
					
					<div class="form-group">
						<label for="rpDate">维修时间</label>
						<input type="text" name="rpDate" id="rpDate" class="form-control" placeholder="请输入维修时间" />
					</div>
					
					<div class="form-group">
						<label for="rpPeople">维修人</label>
						<input type="text" name="rpPeople" id="rpPeople" class="form-control" placeholder="请输入维修人" />
					</div>
					
					<div class="form-group">
						<label for="rpDescribe">维修描述</label>
						<input type="text" name="rpDescribe" id="rpDescribe" class="form-control" placeholder="请输入维修描述" />
					</div>
					
					<div class="form-group">
						<label for="rpCondition">维修状态</label>
						<select name="rpCondition" id="rpCondition" class="form-control" >
							<option value="1">需要维修</option>
							<option value="2">正在维修</option>							
							<option value="3">维修完成</option>
							<option value="4">维修不成功</option>					
						</select>
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