<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="ScriptAndStyle.jsp"%>
<title>添加员工</title>
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
			
				<form role="form" action="StaffServlet?action=add" method="post">
					<div class="form-group">
						<label for="staffID">员工ID</label>
						<input type="text" name="staffID" id="staffID" class="form-control" placeholder="请输入员工ID" />
					</div>
					
					<div class="form-group">
						<label for="staffName">姓名</label>
						<input type="text" name="staffName" id="staffName" class="form-control" placeholder="请输入姓名" />
					</div>
					
					<div class="form-group">
						<label for="staffPassword">密码</label>
						<input type="password" name="staffPassword" id="staffPassword" class="form-control" placeholder="请输入密码" />
					</div>
					
					<div class="form-group">
						<label for="staffPasswordConfirm">确认密码</label>
						<input type="password" id="staffPasswordConfirm" class="form-control" placeholder="请确认密码" />
					</div>
					
					<div class="form-group">
						<label for="staffGender">性别</label>
					<select name="staffGender"  id="staffGender" class="form-control"  >
						<option value="true">男</option>
						<option value="false"  selected="selected">女</option>
					</select>
					</div>

				<div class="form-group">
					<label for="staffDepartment" >部门</label> 
					<select name="staffDepartment"  id="staffDepartment" class="form-control"  >
						<option value="1">部门1</option>
						<option value="2"  selected="selected">部门2</option>
						<option value="3">部门3</option>
					</select>

						<!-- <input type="text" name="staffName" id="staffName" class="form-control" placeholder="请输入姓名" /> -->
				</div>

				<div class="form-group">
						<label for="staffTitle">工种</label>
						<input type="text" name="staffTitle" id="staffTitle" class="form-control" placeholder="请输入工种" />
					</div>
					
					<div class="form-group">
						<label for="staffProfession">职位</label>
						<input type="text" name="staffProfession" id="staffProfession" class="form-control" placeholder="请输入职位" />
					</div>
					
					<div class="form-group">
					<label for="staffLimit" >权限</label> 
					<select name="staffLimit"  id="staffLimit" class="form-control"  >
						<option value="1">员工权限</option>
						<option value="2"  selected="selected">管理员权限</option>
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