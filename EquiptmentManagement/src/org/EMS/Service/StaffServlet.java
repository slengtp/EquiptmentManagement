package org.EMS.Service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.EMS.BLL.StaffBLL;
import org.EMS.Model.StaffModel;

public class StaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffServlet() {
        super();

    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String action = request.getParameter("action");
		
		
		//操作内容为列出所有员工信息
		if(action.toLowerCase().equals("list")){
		
			//从数据库中获取所有的员工条目
			
			//把管理员信息传递到StaffManagement.jsp页面
			ArrayList<StaffModel> staffList = StaffBLL.GetAllArrayList();
			if(staffList.size() == 0){
				request.setAttribute("infoType", "warning");
				request.setAttribute("infoContext", "没有员工信息");
			}
			//将员工列表传递到前台jsp页面
			request.setAttribute("staffList", staffList);
			
			RequestDispatcher rd = request.getRequestDispatcher("StaffManagement.jsp");
				
			rd.forward(request, response);
			
		}
		
		//*********************************************************************************************************
		//添加员工信息
		else if(action.toLowerCase().equals("add")){
			
			String save = request.getParameter("save");
			if(save == null || save == ""){
				
				//不是保存表单的过程
				
				//给出添加员工信息的页面
				RequestDispatcher rd = request.getRequestDispatcher("StaffAdd.jsp");
				
				rd.forward(request, response);
			
			}
			else{
				
				//是保存表单的过程
				String staffId = request.getParameter("staffID");
				String staffName = request.getParameter("staffName");
				String staffPassword = request.getParameter("staffPassword");
				String staffGender = request.getParameter("staffGender");
				String staffDepartmentID = request.getParameter("staffDepartment");
				String staffTitle = request.getParameter("staffTitle");
				String staffProfession = request.getParameter("staffProfession");
				String staffLimit = request.getParameter("staffLimit");
				
				
				
				if(staffId == null || staffName == null || staffPassword == null || staffGender == null){
					
					//用户有数据没有输入
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "输入信息不完整");
					RequestDispatcher rd = request.getRequestDispatcher("StaffAdd.jsp");
					rd.forward(request, response);
				}
				else{
					
					//用户输入了完整的信息
					
					//构造StaffModel用于插入到数据库中
					StaffModel staff = new StaffModel();
					staff.setStaffId(Long.parseLong(staffId));
					staff.setStaffDepartmentID(Integer.parseInt(staffDepartmentID));
					staff.setStaffGender(staffGender.equals("true")  ? true : false);
					staff.setStaffLimit(Integer.parseInt(staffLimit));
					staff.setStaffName(staffName);
					staff.setStaffPassword(staffPassword);
					staff.setStaffProfession(staffProfession);
					staff.setStaffTitle(staffTitle);

					
					//执行插入过程
					if(StaffBLL.Insert(staff) > 0){
						
						//插入成功
						request.setAttribute("infoType", "success");
						request.setAttribute("infoContext", "插入成功");
						
						//跳转到管理员管理页面
						RequestDispatcher rd = request.getRequestDispatcher("StaffServlet?action=list");
						rd.forward(request, response);
					}
					else{
						
						//插入失败
						request.setAttribute("infoType", "error");
						request.setAttribute("infoContext", "插入失败，用户名已存在");
						RequestDispatcher rd = request.getRequestDispatcher("StaffAdd.jsp");
						rd.forward(request, response);
					}
				}
			}
		}
		
		
		//*************************************************************************************
		//编辑员工信息
		else if(action.toLowerCase().equals("edit")){
			
			String save =  request.getParameter("save");
			if(save == null || save == ""){
				
				//不是保存过程，给出现在需要编辑的员工的信息，并且传递到前台
				
				//获得员工ID
				String staffId = request.getParameter("staffId");
				
				if(staffId == null || staffId == ""){
					
					//没有获取到AdminID，跳转到管理员管理列表界面
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "员工信息不存在");
					RequestDispatcher rd = request.getRequestDispatcher("StaffServlet?action=list");
					rd.forward(request, response);
					
				}
				else{
					
					//获取到了StaffID
					StaffModel staff = StaffBLL.GetModel(Long.parseLong(staffId));
					if(staff == null){
						
						//没有在数据库中取到AdminModel信息
						request.setAttribute("infoType", "error");
						request.setAttribute("infoContext", "员工信息不存在");
						RequestDispatcher rd = request.getRequestDispatcher("StaffServlet?action=list");
						rd.forward(request, response);
						
					}
					else{
						
						//获取到了相应的管理员信息
						//将信息传递到AdminEdit.jsp页面，进行更新
						request.setAttribute("StaffModel", staff);
						RequestDispatcher rd = request.getRequestDispatcher("StaffEdit.jsp");
						rd.forward(request, response);
						
					}				
				}
				
			}
			else{
				
				//是保存过程
				//是保存表单的过程
				String staffId = request.getParameter("staffID");
				String staffName = request.getParameter("staffName");
				String staffPassword = request.getParameter("staffPassword");
				String staffGender = request.getParameter("staffGender");
				String staffDepartmentID = request.getParameter("staffDepartment");
				String staffTitle = request.getParameter("staffTitle");
				String staffProfession = request.getParameter("staffProfession");
				String staffLimit = request.getParameter("staffLimit");
				
				if(staffId == null || staffName == null || staffPassword == null || staffGender == null){
					
					//用户有数据没有输入
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "输入信息不完整");
					RequestDispatcher rd = request.getRequestDispatcher("StaffServlet?action=edit&staffId= " + staffId);
					rd.forward(request, response);
				}
				else{
					
					//用户输入了完整的信息
					System.out.println("123");
					
					//构造StaffModel用于插入到数据库中
					StaffModel staff = new StaffModel();
					staff.setStaffId(Long.parseLong(staffId));
					staff.setStaffDepartmentID(Integer.parseInt(staffDepartmentID));
					staff.setStaffGender(staffGender.equals("true")  ? true : false);
					staff.setStaffLimit(Integer.parseInt(staffLimit));
					staff.setStaffName(staffName);
					staff.setStaffPassword(staffPassword);
					staff.setStaffProfession(staffProfession);
					staff.setStaffTitle(staffTitle);

					
					//执行插入过程
					if(StaffBLL.Update(staff) > 0){
						
						//更新成功
						request.setAttribute("infoType", "success");
						request.setAttribute("infoContext", "更新成功");
						
						//跳转到管理员管理页面
						RequestDispatcher rd = request.getRequestDispatcher("StaffServlet?action=list");
						rd.forward(request, response);
					}
					else{
						
						//更新失败
						request.setAttribute("infoType", "error");
						request.setAttribute("infoContext", "更新失败");
						RequestDispatcher rd = request.getRequestDispatcher("StaffServlet?action=list");
						rd.forward(request, response);
					}
				}
			}
						
		}
		
		
		//**************************************************************************************
		
				//删除员工信息
				else if(action.toLowerCase().equals("delete")){
					
					String staffId = request.getParameter("staffId");
					if(staffId == null){
						
						//没有获取到管理员ID
						request.setAttribute("infoType", "error");
						request.setAttribute("infoContext", "删除失败");
						
						//页面跳转
						RequestDispatcher rd = request.getRequestDispatcher("staffServlet?action=list");
					
						rd.forward(request, response);
					}
					else{
						
						if(StaffBLL.Delete(Long.parseLong(staffId)) > 0){
							
							//删除成功
							request.setAttribute("infoType", "success");
							request.setAttribute("infoContext", "删除成功");
							
							//页面跳转
							RequestDispatcher rd = request.getRequestDispatcher("StaffServlet?action=list");
						
							rd.forward(request, response);
						}
						else{
							
							//删除失败
							//没有获取到管理员ID
							request.setAttribute("infoType", "error");
							request.setAttribute("infoContext", "删除失败");
							
							//页面跳转
							RequestDispatcher rd = request.getRequestDispatcher("StaffServlet?action=list");
						
							rd.forward(request, response);
						}
					}			
				}
				
				
				
				else{
					
					//没有获取到相应的action信息，错误
					response.sendRedirect("StaffServlet?action=list");
					
				}			
			}

		}
