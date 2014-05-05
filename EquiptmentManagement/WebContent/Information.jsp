<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <%
    	if(request.getAttribute("infoType") != null){
    		
    		String infoType = (String)request.getAttribute("infoType");
    		if(infoType.equals("success")){
    			
    			%>
    				<div class="alert alert-success"><%=request.getAttribute("infoContext") %></div>
    			<%
    			
    		}
    		else if(infoType.equals("error")){
    			
    			%>
    				<div class="alert alert-danger"><%=request.getAttribute("infoContext") %></div>
    			<%
    			
    		}
    		else if(infoType.equals("warning")){
    			
    			%>
    				<div class="alert alert-warning"><%=request.getAttribute("infoContext") %></div>
    			<%
    			
    		}
    		
    	}
    %>