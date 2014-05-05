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





import org.EMS.BLL.BreakBLL;
import org.EMS.Model.BreakModel;

public class BreakServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BreakServlet() {
        super();

    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String action = request.getParameter("action");
		
		
		//操作内容为列出所有故障信息
		if(action.toLowerCase().equals("list")){
		
			//从数据库中获取所有的故障条目
			
			//把管理员信息传递到BreakManagement.jsp页面
			ArrayList<BreakModel> breakList = BreakBLL.GetAllArrayList();
			if(breakList.size() == 0){
				request.setAttribute("infoType", "warning");
				request.setAttribute("infoContext", "没有故障信息");
			}
			//将故障信息列表传递到前台jsp页面
			request.setAttribute("breakList", breakList);
			
			RequestDispatcher rd = request.getRequestDispatcher("BreakManagement.jsp");
				
			rd.forward(request, response);
			
		}
		
		//*********************************************************************************************************
		//添加故障信息
		else if(action.toLowerCase().equals("add")){
			
			String save = request.getParameter("save");
			if(save == null || save == ""){
				
				//不是保存表单的过程
				
				//给出添加故障信息的页面
				RequestDispatcher rd = request.getRequestDispatcher("BreakAdd.jsp");
				
				rd.forward(request, response);
			
			}
			else{
				
				//是保存表单的过程
				String breakEqpID = request.getParameter("breakEqpID");
				String breakEqpRFID = request.getParameter("breakEqpRFID");
				String breakDescribe = request.getParameter("breakDescribe");
				String breakFixman = request.getParameter("breakFixman");
				String breakReportDate = request.getParameter("breakReportDate");
				
				
				if(breakEqpID == null || breakEqpRFID == null){
					
					//用户有数据没有输入
					
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "输入信息不完整");
					RequestDispatcher rd = request.getRequestDispatcher("BreakAdd.jsp");
					rd.forward(request, response);
				}
				else{
					
					//用户输入了完整的信息
					
					//构造BreakModel用于插入到数据库中
					BreakModel brk = new BreakModel();
					brk.setBreakEqpID(Long.parseLong(breakEqpID));
					brk.setBreakEqpRFID(breakEqpRFID);
					brk.setBreakDescribe(breakDescribe);
					brk.setBreakFixman(Long.parseLong(breakFixman));
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					// String类型转换成Date类型
					Date startDate = null;
					try {
						startDate = sdf.parse(breakReportDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					brk.setBreakReportDate(startDate);

					
					//执行插入过程
					if(BreakBLL.Insert(brk) > 0){
						
						//插入成功
						request.setAttribute("infoType", "success");
						request.setAttribute("infoContext", "插入成功");
						
						//跳转到管理员管理页面
						RequestDispatcher rd = request.getRequestDispatcher("BreakServlet?action=list");
						rd.forward(request, response);
					}
					else{
						
						//插入失败
						request.setAttribute("infoType", "error");
						request.setAttribute("infoContext", "插入失败");
						RequestDispatcher rd = request.getRequestDispatcher("BreakAdd.jsp");
						rd.forward(request, response);
					}
				}
			}
		}
		
		
		//*************************************************************************************
		//编辑故障信息
		else if(action.toLowerCase().equals("edit")){
			
			String save =  request.getParameter("save");
			if(save == null || save == ""){
				
				//不是保存过程，给出现在需要编辑的故障的信息，并且传递到前台
				
				//获得故障ID
				String breakID = request.getParameter("breakID");
				
				if(breakID == null || breakID == ""){
					
					//没有获取到AdminID，跳转到管理员管理列表界面
					request.setAttribute("infoType", "error");
					request.setAttribute("infoCotnext", "故障信息不存在");
					RequestDispatcher rd = request.getRequestDispatcher("BreakServlet?action=list");
					rd.forward(request, response);
					
				}
				else{
					
					//获取到了BreakID
					BreakModel brk = BreakBLL.GetModel(Long.parseLong(breakID));
					if(brk == null){
						
						//没有在数据库中取到BreakModel信息
						request.setAttribute("infoType", "error");
						request.setAttribute("infoCotnext", "故障信息不存在");
						RequestDispatcher rd = request.getRequestDispatcher("BreakServlet?action=list");
						rd.forward(request, response);
						
					}
					else{
						
						//获取到了相应的管理员信息
						//将信息传递到BreakEdit.jsp页面，进行更新
						request.setAttribute("BreakModel", brk);
						RequestDispatcher rd = request.getRequestDispatcher("BreakEdit.jsp");
						rd.forward(request, response);
						
					}				
				}
				
			}
			else{
				
				//是保存过程
				//是保存表单的过程
				String breakID = request.getParameter("breakID");
				String breakEqpID = request.getParameter("breakEqpID");
				String breakEqpRFID = request.getParameter("breakEqpRFID");
				String breakDescribe = request.getParameter("breakDescribe");
				String breakFixman = request.getParameter("breakFixman");
				String breakReportDate = request.getParameter("breakReportDate");
				
				if(breakID == null || breakEqpID == null || breakEqpRFID == null){
					
					//用户有数据没有输入
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "输入信息不完整");
					RequestDispatcher rd = request.getRequestDispatcher("BreakServlet?action=list");
					rd.forward(request, response);
				}
				else{
					
					//用户输入了完整的信息
					
					//构造BreakModel用于插入到数据库中
					BreakModel brk = new BreakModel();
					brk.setBreakID(Long.parseLong(breakID));
					brk.setBreakEqpID(Long.parseLong(breakEqpID));
					brk.setBreakEqpRFID(breakEqpRFID);
					brk.setBreakDescribe(breakDescribe);
					brk.setBreakFixman(Long.parseLong(breakFixman));

					
					//执行插入过程
					if(BreakBLL.Update(brk) > 0){
						
						//更新成功
						request.setAttribute("infoType", "success");
						request.setAttribute("infoContext", "更新成功");
						
						//跳转到管理员管理页面
						RequestDispatcher rd = request.getRequestDispatcher("BreakServlet?action=list");
						rd.forward(request, response);
					}
					else{
						
						//更新失败
						request.setAttribute("infoType", "error");
						request.setAttribute("infoContext", "更新失败");
						RequestDispatcher rd = request.getRequestDispatcher("BreakServlet?action=list");
						rd.forward(request, response);
					}
				}
			}
						
		}
		
		
		//**************************************************************************************
		
				//删除故障信息
				else if(action.toLowerCase().equals("delete")){
					
					String breakID = request.getParameter("breakID");
					BreakModel brk = BreakBLL.GetModel(Long.parseLong(breakID));
					if(breakID == null){
						
						//没有获取到管理员ID
						request.setAttribute("infoType", "error");
						request.setAttribute("infoContext", "删除失败");
						
						//页面跳转
						RequestDispatcher rd = request.getRequestDispatcher("breakServlet?action=list");
					
						rd.forward(request, response);
					}
					else{
						
						if(BreakBLL.Delete(brk) > 0){
							
							//删除成功
							request.setAttribute("infoType", "success");
							request.setAttribute("infoContext", "删除成功");
							
							//页面跳转
							RequestDispatcher rd = request.getRequestDispatcher("BreakServlet?action=list");
						
							rd.forward(request, response);
						}
						else{
							
							//删除失败
							//没有获取到管理员ID
							request.setAttribute("infoType", "error");
							request.setAttribute("infoContext", "删除失败");
							
							//页面跳转
							RequestDispatcher rd = request.getRequestDispatcher("BreakServlet?action=list");
						
							rd.forward(request, response);
						}
					}			
				}
				
				
				
				else{
					
					//没有获取到相应的action信息，错误
					response.sendRedirect("BreakServlet?action=list");
					
				}			
			}

		}
