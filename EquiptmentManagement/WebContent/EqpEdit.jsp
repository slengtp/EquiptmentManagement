<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="org.EMS.Model.EqpModel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="org.EMS.BLL.DepartmentBLL" %>	
<%@ page import="org.EMS.Model.DepartmentModel" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="ScriptAndStyle.jsp"%>
<title>添加管理员</title>
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
					EqpModel eqp = (EqpModel)request.getAttribute("EqpModel");
				%>
			
				<form role="form" action="EqpServlet?action=edit" method="post">
					<div class="form-group">
						<label for="eqpRFID">设备RFID</label>
						<input type="text" name="eqpRFID" id="eqpRFID" class="form-control"  value='<%=eqp.getEqpRFID() %>'  readonly/>
					</div>
					
					<div class="form-group">
						<label for="eqpName">设备名称</label>
						<input type="text" name="eqpName" id="eqpName" class="form-control"  value='<%=eqp.getEqpName() %>' />
					</div>
					
					<div class="form-group">
						<label for="eqpID">设备ID</label>
						<input type="text" name="eqpID" id="eqpID" class="form-control"  value='<%=eqp.getEqpID() %>'  />
					</div>
					
					<div class="form-group">
						<label for="eqpModel">规格型号</label>
						<input type="text" name="eqpModel" id="eqpModel" class="form-control"  value='<%=eqp.getEqpModel() %>'  />
					</div>
					
					<div class="form-group">
						<label for="eqpManufacturer">生产厂家</label>
						<input type="text" name="eqpManufacturer" id="eqpManufacturer" class="form-control" value='<%=eqp.getEqpManufacturer() %>' />
					</div>
					
					<div class="form-group">
						<label for="eqpStartUsingDate">开始使用日期</label>
						<input type="text" name="eqpStartUsingDate" id="eqpStartUsingDate" class="form-control" value='<%=eqp.getEqpStartUsingDate() %>' />
					</div>
					
					<div class="form-group">
						<label for="eqpAvailableTime">使用年限</label>
						<input type="text" name="eqpAvailableTime" id="eqpAvailableTime" class="form-control" value='<%=eqp.getEqpAvailableTime() %>' />
					</div>
					
					<div class="form-group">
						<label for="eqpDepartmentID">使用部门</label>
						<select name="eqpDepartmentID" id="eqpDepartmentID" class="form-control" />
						<%
							ArrayList<DepartmentModel> departmentList = DepartmentBLL.GetAllArrayList();
							Iterator<DepartmentModel> iteratorDepartment = departmentList.iterator();
							
							while(iteratorDepartment.hasNext()){
								
								DepartmentModel department = iteratorDepartment.next();
								if(eqp.getEqpDepartmentID() == department.getDepartmentID()){
									%>
										<option value='<%=department.getDepartmentID()%>'  selected><%=department.getDepartmentName() %></option>								
									<%
								}
								else{
									%>
										<option value='<%=department.getDepartmentID()%>' ><%=department.getDepartmentName() %></option>								
									<%
								}
							}						
						%>
						</select>
					</div>
					
					<div class="form-group">
						<label for="eqpUserID">使用人ID</label>
						<input type="text" name="eqpUserID" id="eqpUserID" class="form-control" value='<%=eqp.getEqpUserID() %>'  />
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