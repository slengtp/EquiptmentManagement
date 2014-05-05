<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="sidebar">
	<ul class="nav nav-pills nav-stacked ">
		<li class="active"><a href="EqpServlet?action=list" class="choose">设备基本信息管理</a></li>
		<li><a href="BreakServlet?action=list" >设备故障管理</a></li>
		<li><a href="#" >设备维修记录管理</a></li>
		<li><a href="#" >设备调拨管理</a></li>
		<li><a href="LendServlet?action=list" >设备借出归还管理</a></li>
		<li><a href="#" >设备信息统计查询</a></li>
		<li><a href="AdminServlet?action=list">管理员管理</a></li>
		<li><a href="AdminServlet?action=add">添加管理员</a></li>
		<li><a href="StaffServlet?action=list">员工管理</a></li>
		<li><a href="StaffServlet?action=add">添加员工</a></li>		
	</ul>
</div>