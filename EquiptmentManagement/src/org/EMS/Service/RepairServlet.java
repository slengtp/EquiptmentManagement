package org.EMS.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.EMS.BLL.RepairBLL;
import org.EMS.Model.RepairModel;

public class RepairServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RepairServlet() {
        super();

    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String action = request.getParameter("action");
		
		
		//操作内容为列出所有维修信息
		if(action.toLowerCase().equals("list")){
		
			//从数据库中获取所有的维修条目
			
			//把管理员信息传递到RepairManagement.jsp页面
			ArrayList<RepairModel> repairList = RepairBLL.GetAllArrayList();
			if(repairList.size() == 0){
				request.setAttribute("infoType", "warning");
				request.setAttribute("infoContext", "没有维修信息");
			}
			//将维修信息列表传递到前台jsp页面
			request.setAttribute("repairList", repairList);
			
			RequestDispatcher rd = request.getRequestDispatcher("RepairManagement.jsp");
				
			rd.forward(request, response);
			
		}
		
		//*********************************************************************************************************
		//添加维修信息
		else if(action.toLowerCase().equals("add")){
			
			String save = request.getParameter("save");
			if(save == null || save == ""){
				
				//不是保存表单的过程
				
				//给出添加维修信息的页面
				RequestDispatcher rd = request.getRequestDispatcher("RepairAdd.jsp");
				
				rd.forward(request, response);
			
			}
			else{
				
				//是保存表单的过程
				String rpEqpRFID = request.getParameter("rpEqpRFID");
				String rpDate = request.getParameter("rpDate");
				String rpPeople = request.getParameter("rpPeople");
				String rpDescribe = request.getParameter("rpDescribe");
				String rpCondition = request.getParameter("rpCondition");
				
				
				if(rpEqpRFID == null || rpCondition == null){
					
					//用户有数据没有输入
					
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "输入信息不完整");
					RequestDispatcher rd = request.getRequestDispatcher("RepairAdd.jsp");
					rd.forward(request, response);
				}
				else{
					
					//用户输入了完整的信息
					
					//构造RepairModel用于插入到数据库中
					RepairModel repair = new RepairModel();
					repair.setRpEqpRFID(rpEqpRFID);
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					// String类型转换成Date类型
					Date ddate = null;
					try {
						ddate = sdf.parse(rpDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}					
					repair.setRpDate(ddate);
					
					repair.setRpPeople(Long.parseLong(rpPeople));
					repair.setRpDescribe(rpDescribe);
					repair.setRpCondition(Integer.parseInt(rpCondition));					

					
					//执行插入过程
					if(RepairBLL.Insert(repair) > 0){
						
						//插入成功
						request.setAttribute("infoType", "success");
						request.setAttribute("infoContext", "插入成功");
						
						//跳转到管理员管理页面
						RequestDispatcher rd = request.getRequestDispatcher("RepairServlet?action=list");
						rd.forward(request, response);
					}
					else{
						
						//插入失败
						request.setAttribute("infoType", "error");
						request.setAttribute("infoContext", "插入失败");
						RequestDispatcher rd = request.getRequestDispatcher("RepairAdd.jsp");
						rd.forward(request, response);
					}
				}
			}
		}
		
		
		//*************************************************************************************
		//编辑维修信息
		else if(action.toLowerCase().equals("edit")){
			
			String save =  request.getParameter("save");
			if(save == null || save == ""){
				
				//不是保存过程，给出现在需要编辑的维修的信息，并且传递到前台
				
				//获得维修ID
				String rpID = request.getParameter("rpID");
				
				if(rpID == null || rpID == null){
					
					//用户有数据没有输入
					
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "维修信息不存在");
					RequestDispatcher rd = request.getRequestDispatcher("RepairServlet?action=list");
					rd.forward(request, response);
				}
				else{
					
					//获取到了RepairID
					RepairModel repair = RepairBLL.GetModel(Long.parseLong(rpID));
					if(repair == null){
						
						//没有在数据库中取到RepairModel信息
						request.setAttribute("infoType", "error");
						request.setAttribute("infoCotnext", "维修信息不存在");
						RequestDispatcher rd = request.getRequestDispatcher("RepairServlet?action=list");
						rd.forward(request, response);
						
					}
					else{
						
						//获取到了相应的管理员信息
						//将信息传递到RepairEdit.jsp页面，进行更新
						request.setAttribute("RepairModel", repair);
						RequestDispatcher rd = request.getRequestDispatcher("PepairEdit.jsp");
						rd.forward(request, response);
						
					}				
				}
				
			}
			else{
				
				//是保存过程
				//是保存表单的过程
				String rpEqpRFID = request.getParameter("rpEqpRFID");
				String rpDate = request.getParameter("rpDate");
				String rpPeople = request.getParameter("rpPeople");
				String rpDescribe = request.getParameter("rpDescribe");
				String rpCondition = request.getParameter("rpCondition");
				
				
				if(rpEqpRFID == null || rpCondition == null){
					
					//用户有数据没有输入
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "输入信息不完整");
					RequestDispatcher rd = request.getRequestDispatcher("RepairServlet?action=list");
					rd.forward(request, response);
				}
				else{
					
					//用户输入了完整的信息
					
					//构造RepairModel用于插入到数据库中
					RepairModel repair = new RepairModel();
					repair.setRpEqpRFID(rpEqpRFID);
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					// String类型转换成Date类型
					Date ddate = null;
					try {
						ddate = sdf.parse(rpDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}					
					repair.setRpDate(ddate);
					
					repair.setRpPeople(Long.parseLong(rpPeople));
					repair.setRpDescribe(rpDescribe);
					repair.setRpCondition(Integer.parseInt(rpCondition));					

					
					//执行插入过程
					if(RepairBLL.Insert(repair) > 0){
						
						//插入成功
						request.setAttribute("infoType", "success");
						request.setAttribute("infoContext", "更新成功");
						
						//跳转到管理员管理页面
						RequestDispatcher rd = request.getRequestDispatcher("RepairServlet?action=list");
						rd.forward(request, response);
					}
					else{
						
						//更新失败
						request.setAttribute("infoType", "error");
						request.setAttribute("infoContext", "更新失败");
						RequestDispatcher rd = request.getRequestDispatcher("RepairServlet?action=list");
						rd.forward(request, response);
					}
				}
			}
						
		}
		
		
		//**************************************************************************************
		
				//删除维修信息
				else if(action.toLowerCase().equals("delete")){
					
					String rpID = request.getParameter("rpID");
					RepairModel repair = RepairBLL.GetModel(Long.parseLong(rpID));
					if(rpID == null){
						
						//没有获取到管理员ID
						request.setAttribute("infoType", "error");
						request.setAttribute("infoContext", "删除失败");
						
						//页面跳转
						RequestDispatcher rd = request.getRequestDispatcher("RepairServlet?action=list");
					
						rd.forward(request, response);
					}
					else{
						
						if(RepairBLL.Delete(repair) > 0){
							
							//删除成功
							request.setAttribute("infoType", "success");
							request.setAttribute("infoContext", "删除成功");
							
							//页面跳转
							RequestDispatcher rd = request.getRequestDispatcher("RepairServlet?action=list");
						
							rd.forward(request, response);
						}
						else{
							
							//删除失败
							//没有获取到管理员ID
							request.setAttribute("infoType", "error");
							request.setAttribute("infoContext", "删除失败");
							
							//页面跳转
							RequestDispatcher rd = request.getRequestDispatcher("RepairServlet?action=list");
						
							rd.forward(request, response);
						}
					}			
				}
				
				
				
				else{
					
					//没有获取到相应的action信息，错误
					response.sendRedirect("RepairServlet?action=list");
					
				}			
			}

		}
