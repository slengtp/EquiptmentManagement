<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
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
			<div class="span9 mt10 pb40">
				<%@include file="Information.jsp" %>
				
				<%
					ArrayList<DepartmentModel> departmentList = (ArrayList<DepartmentModel>)request.getAttribute("departmentList");
					Iterator<DepartmentModel> departmentIterator = departmentList.iterator();
					if(departmentIterator.hasNext()){
						%>
						<div class="row-fluid">
							<div class="span6">
								<!-- 一级部门 -->
								<select size=10 id="departmentL1">
							<%
							while(departmentIterator.hasNext()){
							
								DepartmentModel department = departmentIterator.next();
								if(department.getDepartmentParentID() == 0){
									%>
										<option value='<%=department.getDepartmentID()%>' onclick='getDepartmentL2(<%=department.getDepartmentID() %>)'><%=department.getDepartmentName() %></option>
									<%
								}
							}
							%>
								</select>
							</div>
							<div class="span6">
								<!-- 二级部门 -->
								<select id="departmentL2" style="display:none">
								</select>
							</div>
						</div>
						<%
						
						
					}
					else{
						
						//没有信息
						%>
							<div class="alert alert-info">没有部门信息</div>
						<%
						
					}
				
				%>
			</div>
			<!-- 导航右侧正文部分 End -->

		</div>
		<!-- 中部主体部分 End -->

		<!--  底部Foot Start  -->
		<%@include file="Foot.jsp"%><!--   底部Foot End  -->

		<script>
			//获得二级部门列表
			function getDepartmentL2(departmentParentID){
				
				alert(departmentParentID);
                var addressStr = "DepartmentServlet?action=getlevel2department&departmentParentID=" + departmentParentID;
                ajax(addressStr, function(resText){
                	
                	var d2List = JSON.parse(resText);
                	alert(d2List);
                	
                });

			}
		
		</script>

</body>
</html>