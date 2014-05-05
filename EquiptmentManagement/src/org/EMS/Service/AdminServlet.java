package org.EMS.Service;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.EMS.BLL.AdminBLL;
import org.EMS.Model.AdminModel;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminServlet() {
        super();
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		String action = request.getParameter("action");
		
		//操作内容为列出所有管理员信息
		if(action.toLowerCase().equals("list")){
		
			//从数据库中获取所有的管理员条目
			
			//把管理员信息传递到AdminManagement.jsp页面
			ArrayList<AdminModel> adminList = AdminBLL.GetAllArrayList();
			if(adminList.size() == 0){
				request.setAttribute("infoType", "warning");
				request.setAttribute("infoContext", "没有管理员信息");
			}
			//将管理员列表传递到前台jsp页面
			request.setAttribute("adminList", adminList);
			
			RequestDispatcher rd = request.getRequestDispatcher("AdminManagement.jsp");
				
			rd.forward(request, response);
			
		}
		
		//***********************************************************************
		//添加管理员信息
		else if(action.toLowerCase().equals("add")){
						
			String save = request.getParameter("save");
			if(save == null || save == ""){
				
				//不是保存表单的过程
				
				//给出添加管理员信息的页面
				RequestDispatcher rd = request.getRequestDispatcher("AdminAdd.jsp");
				
				rd.forward(request, response);
			
			}
			else{
				
				//是保存表单的过程
				String adminID = request.getParameter("adminID");
				String adminName = request.getParameter("adminName");
				String adminPassword = request.getParameter("adminPassword");
				String adminIsSuper = request.getParameter("adminIsSuper");
				
				if(adminID == null || adminName == null || adminPassword == null){
					
					//用户有数据没有输入
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "输入信息不完整");
					RequestDispatcher rd = request.getRequestDispatcher("AdminAdd.jsp");
					rd.forward(request, response);
				}
				else{
					
					//用户输入了完整的信息
					
					//构造AdminModel用于插入到数据库中
					AdminModel admin = new AdminModel();
					admin.setAdminID(adminID);
					admin.setAdminName(adminName);
					admin.setAdminPassword(adminPassword);
					if(adminIsSuper == null){
						admin.setAdminIsSuper(false);
					}
					else{
						admin.setAdminIsSuper(true);
					}
					
					//执行插入过程
					if(AdminBLL.Insert(admin) > 0){
						
						//插入成功
						request.setAttribute("infoType", "success");
						request.setAttribute("infoContext", "插入成功");
						
						//跳转到管理员管理页面
						RequestDispatcher rd = request.getRequestDispatcher("AdminServlet?action=list");
						rd.forward(request, response);
					}
					else{
						
						//插入失败
						request.setAttribute("infoType", "error");
						request.setAttribute("infoContext", "插入失败，用户名已存在");
						RequestDispatcher rd = request.getRequestDispatcher("AdminAdd.jsp");
						rd.forward(request, response);
					}
				}
			}
		}
		
		//*******************************************************************************************
		//编辑管理员信息
		else if(action.toLowerCase().equals("edit")){
						
			String save =  request.getParameter("save");
			if(save == null || save == ""){
				
				//不是保存过程，给出现在需要编辑的管理员的信息，并且传递到前台
				
				//获得管理员ID
				String adminId = request.getParameter("adminId");
				
				if(adminId == null || adminId == ""){
					
					//没有获取到AdminID，跳转到管理员管理列表界面
					request.setAttribute("infoType", "error");
					request.setAttribute("infoCotnext", "管理员信息不存在");
					RequestDispatcher rd = request.getRequestDispatcher("AdminServlet?action=list");
					rd.forward(request, response);
					
				}
				else{
					
					//获取到了AdminID
					AdminModel admin = AdminBLL.GetModel(adminId);
					if(admin == null){
						
						//没有在数据库中取到AdminModel信息
						request.setAttribute("infoType", "error");
						request.setAttribute("infoCotnext", "管理员信息不存在");
						RequestDispatcher rd = request.getRequestDispatcher("AdminServlet?action=list");
						rd.forward(request, response);
						
					}
					else{
						
						//获取到了相应的管理员信息
						//将信息传递到AdminEdit.jsp页面，进行更新
						request.setAttribute("adminModel", admin);
						RequestDispatcher rd = request.getRequestDispatcher("AdminEdit.jsp");
						rd.forward(request, response);
						
					}				
				}
				
			}
			else{
				
				//是保存过程
				String adminID = request.getParameter("adminID");
				String adminName = request.getParameter("adminName");
				String adminPassword = request.getParameter("adminPassword");
				String adminIsSuper = request.getParameter("adminIsSuper");
				
				if(adminID == null || adminName == null || adminPassword == null){
					
					//用户有数据没有输入
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "输入信息不完整");
					RequestDispatcher rd = request.getRequestDispatcher("AdminServlet?action=edit&adminId= " + adminID);
					rd.forward(request, response);
				}
				else{
					
					//用户输入了完整的信息
					
					//构造AdminModel用于插入到数据库中
										
					AdminModel admin = new AdminModel();
					admin.setAdminID(adminID);
					admin.setAdminName(adminName);
					admin.setAdminPassword(adminPassword);
					if(adminIsSuper == null){
						admin.setAdminIsSuper(false);
					}
					else{
						admin.setAdminIsSuper(true);
					}
					
					//执行插入过程
					if(AdminBLL.Update(admin) > 0){
						
						//更新成功
						request.setAttribute("infoType", "success");
						request.setAttribute("infoContext", "更新成功");
						
						//跳转到管理员管理页面
						RequestDispatcher rd = request.getRequestDispatcher("AdminServlet?action=list");
						rd.forward(request, response);
					}
					else{
						
						//更新失败
						request.setAttribute("infoType", "error");
						request.setAttribute("infoContext", "更新失败");
						RequestDispatcher rd = request.getRequestDispatcher("AdminServlet?action=list");
						rd.forward(request, response);
					}
				}				
			}
						
		}
		
		//**************************************************************************************
		
		//删除管理员信息
		else if(action.toLowerCase().equals("delete")){
			
			String adminId = request.getParameter("adminId");
			if(adminId == null){
				
				//没有获取到管理员ID
				request.setAttribute("infoType", "error");
				request.setAttribute("infoContext", "删除失败");
				
				//页面跳转
				RequestDispatcher rd = request.getRequestDispatcher("AdminServlet?action=list");
			
				rd.forward(request, response);
			}
			else{
				
				if(AdminBLL.Delete(adminId) > 0){
					
					//删除成功
					request.setAttribute("infoType", "success");
					request.setAttribute("infoContext", "删除成功");
					
					//页面跳转
					RequestDispatcher rd = request.getRequestDispatcher("AdminServlet?action=list");
				
					rd.forward(request, response);
				}
				else{
					
					//删除失败
					//没有获取到管理员ID
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "删除失败");
					
					//页面跳转
					RequestDispatcher rd = request.getRequestDispatcher("AdminServlet?action=list");
				
					rd.forward(request, response);
				}
			}			
		}
		
		
		
		else{
			
			//没有获取到相应的action信息，错误
			response.sendRedirect("AdminServlet?action=list");
			
		}			
	}

}
