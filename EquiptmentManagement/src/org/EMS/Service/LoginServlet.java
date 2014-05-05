package org.EMS.Service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.EMS.BLL.StaffBLL;
import org.EMS.Model.StaffModel;

public class LoginServlet extends HttpServlet {
	
	/*
	 * 处理登录信息
	 */
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	public void service(HttpServletRequest request, HttpServletResponse response){
		
		Long staffID = Long.valueOf(request.getParameter("userName"));
		String password = (String)request.getParameter("password");
		
		if(staffID == null || password == null || password == ""){
			
			//首先进行输入判断，不相信客户端
			//跳转到登录页面
			request.setAttribute("infoType", "error");
			request.setAttribute("infoContext", "请输入用户名和密码");
		    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		    try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			
		}
		else{
			
			//用户输入了用户名和密码，进行存在性判断
			StaffModel staff = StaffBLL.GetModel(staffID);
			
			if(staff == null){
				
				//员工信息不存在
				request.setAttribute("infoType", "error");
				request.setAttribute("infoContext", "用户信息不存在");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			else{
				
				//判断密码是否正确
				if(password.equals(staff.getStaffPassword())){
					//密码正确
					//进入系统，进行管理
					
					//TODO 添加跳转
					System.out.println("登录成功");
				}
				else{
					//密码错误
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "密码错误");
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					try {
						rd.forward(request, response);
					} catch (ServletException | IOException e) {
						e.printStackTrace();
					}
				}
				
			}
		}
	}
}
