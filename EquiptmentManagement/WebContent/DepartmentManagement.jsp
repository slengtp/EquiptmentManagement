<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="org.EMS.Model.DepartmentModel"%>
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
			<%@include file="Information.jsp"%>

			<%
				ArrayList<DepartmentModel> departmentList = (ArrayList<DepartmentModel>)request.getAttribute("departmentList");
						Iterator<DepartmentModel> departmentIterator = departmentList.iterator();
						if(departmentIterator.hasNext()){
			%>
			<div class="row-fluid">
				<div class="span4">
					<!-- 一级部门 -->
					<h3>一级部门</h3>
					<ul id="departmentL1" class="list-group">
						<%
							while(departmentIterator.hasNext()){
											
												DepartmentModel department = departmentIterator.next();
						%>
						<li style="cursor: pointer" class="list-group-item"
							value='<%=department.getDepartmentID()%>'
							onclick='getDepartmentL2(<%=department.getDepartmentID()%>)'><%=department.getDepartmentName()%></li>
						<%
							}
						%>
					</ul>
					<!-- 添加一级部门 -->
					<button class="btn btn-primary" onclick="addDepartment(1)">添加一级部门</button>
				</div>
				<div class="span4">
					<!-- 二级部门 -->
					<h3 id='lbDepartment2'>二级部门</h3>
					<div id="departmentLabel2"></div>
					<ul class="list-group" id="departmentL2" style="display: none">
					</ul>
					<button class="btn btn-primary" id="btnL2" style="display:none" onclick="addDepartment(2)">添加二级部门</button>
				</div>
				<div class="span2"></div>
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
				//全局变量，存储当前选定的一级目录
				parentID = departmentParentID;
				
				//更改二级部门标题
				$("#lbDepartment2").
                var addressStr = "DepartmentServlet?action=getlevel2department&departmentParentID=" + departmentParentID;
                ajax(addressStr, function(resText){
                	
                	//调用JSON库转换JSON字符串
                	var d2List = JSON.parse(resText);
                	var dsD2List = d2List.department2List;
                	if(dsD2List == ""){
                		//列表为空
						$("#departmentL2>ul").remove();
						$("#departmentLabel2 div").remove();
						$("#departmentL2").hide();
                		$("#departmentLabel2").append("<div class='alert alert-info'>无二级部门</div>");
                		$("#btnL2").show();
                	}else{
                		
						//循环遍历输出
						//删除先前已经输出的元素
						$("#departmentL2 li").remove();
						for(var d2 in dsD2List){
							
							$("#departmentLabel2 div").remove();
							$("#departmentL2").append("<li style='cursor:pointer' class='list-group-item' value='" + dsD2List[d2].departmentID + "''>" + dsD2List[d2].departmentName + "</li>");
						};
						$("#departmentL2").show();
						$("#btnL2").show();
                	};
                });
			};
			
			
			function addDepartment(level){
				if(level == "1"){
					//添加一级部门
					do{
						var flag = true;
						name = prompt("请输入名称");
						if(name == ""){
							flag = false;
						};
					}while(!flag);
					window.location.href="DepartmentServlet?action=add&level=1&departmentName="+name;
				}else{
					//添加二级部门
					do{
						var flag = true;
						name = prompt("请输入名称");
						if(name == ""){
							flag = false;
						};
					}while(!flag);
					window.location.href="DepartmentServlet?action=add&level=2&departmentName="+name+"&departmentParentID="+parentID;
				};
			};
		
		</script>

</body>
</html>