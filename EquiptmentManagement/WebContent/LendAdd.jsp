<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@page import="org.EMS.Model.BreakModel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="ScriptAndStyle.jsp"%>
<title>添加借出归还信息</title>
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
			
				<form role="form" action="LendServlet?action=add" method="post">

					<input type="hidden" name="lendID" id="lendID" class="form-control" placeholder="请输入借出归还ID" />
					
					<div class="form-group">
						<label for="lendEqpRFID">借出设备RFID</label>
						<input type="text" name="lendEqpRFID" id="lendEqpRFID" class="form-control" placeholder="请输入借出设备RFID" />
					</div>
					
					<div class="form-group">
						<label for="lendDepartmentID">借出设备ID</label>
						<input type="text" name="lendDepartmentID" id="lendDepartmentID" class="form-control" placeholder="请输入借出设备ID" />
					</div>
					
					<div class="form-group">
						<label for="lendDate">借出设备日期</label>
						<input type="text" name="lendDate" id="lendDate" class="form-control" placeholder="请输入借出设备描述" />
					</div>
					
					<div class="form-group">
						<label for="lendTime">归还日期</label>
						<input type="text" name="lendTime" id="lendTime" class="form-control" placeholder="请输入借出维修人" />
					</div>
					
					<div class="form-group">
						<label for="lendDeadline">归还期限</label>
						<input type="text" name="lendDeadline" id="lendDeadline" class="form-control" placeholder="请输入报修时间" />
					</div>	
					
					<div class="form-group">
						<label for="lendToPeople">归还人</label>
						<input type="text" name="lendDeadline" id="lendDeadline" class="form-control" placeholder="请输入报修时间" />
					</div>	
					
					<div class="form-group">
						<label for="lendFromCharge">借出人</label>
						<input type="text" name="lendDeadline" id="lendDeadline" class="form-control" placeholder="请输入报修时间" />
					</div>		
					
					<div class="form-group">
						<label for="lendFlag">是否归还</label>
						<input type="text" name="lendDeadline" id="lendDeadline" class="form-control" placeholder="请输入报修时间" />
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