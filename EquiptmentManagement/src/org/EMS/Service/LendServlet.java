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

import org.EMS.BLL.LendBLL;
import org.EMS.Model.LendModel;

public class LendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LendServlet() {
		super();

	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		// 操作内容为列出所有借出信息
		if (action.toLowerCase().equals("list")) {

			// 从数据库中获取所有的借出条目

			// 把管理员信息传递到lendManagement.jsp页面
			ArrayList<LendModel> lendList = LendBLL.GetAllArrayList();
			if (lendList.size() == 0) {
				request.setAttribute("infoType", "warning");
				request.setAttribute("infoContext", "没有借出信息");
			}
			// 将借出列表传递到前台jsp页面
			request.setAttribute("lendList", lendList);

			RequestDispatcher rd = request.getRequestDispatcher("LendManagement.jsp");

			rd.forward(request, response);

		}

		// *********************************************************************************************************
		// 添加设备信息
		else if (action.toLowerCase().equals("add")) {

			String save = request.getParameter("save");
			if (save == null || save == "") {

				//不是保存表单的过程
				
				//给出添加设备信息的页面
				RequestDispatcher rd = request.getRequestDispatcher("LendAdd.jsp");
				
				rd.forward(request, response);
				
			} else {

				// 是保存表单的过程
				String lendEqpRFID = request.getParameter("lendEqpRFID");
				String lendDepartmentID = request.getParameter("lendDepartmentID");
				String lendDate = request.getParameter("lendDate");
				String lendTime = request.getParameter("lendTime");
				String lendDeadline = request.getParameter("lendDeadline");
				String lendToPeople = request.getParameter("lendToPeople");
				String lendFromCharge = request.getParameter("lendFromCharge");
				String lendFlag = request.getParameter("lendFlag");

				if (lendEqpRFID == null || lendDepartmentID == null ) {

					// 用户有数据没有输入
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "输入信息不完整");
					RequestDispatcher rd = request.getRequestDispatcher("LendAdd.jsp");
					rd.forward(request, response);
				} else {

					// 用户输入了完整的信息

					// 构造LendModel用于插入到数据库中
					LendModel lend = new LendModel();
					lend.setLendEqpRFID(lendEqpRFID);
					lend.setLendDepartmentID(Integer.parseInt(lendDepartmentID));
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					// String类型转换成Date类型
					
					//借出时间
					Date startDate = new Date();
					try {
						startDate = sdf.parse(lendDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}		
					lend.setLendDate(startDate);
					
					//归还时间
					Date stayDate = new Date();
					try {
						stayDate = sdf.parse(lendTime);
					} catch (ParseException e) {
						e.printStackTrace();
					}							
					lend.setLendTime(stayDate);
					
					//借出截止时间
					Date deadDate = new Date();
					try {
						deadDate = sdf.parse(lendDeadline);
					} catch (ParseException e) {
						e.printStackTrace();
					}											
					lend.setLendDeadline(deadDate);
					
					lend.setLendToPeople(Long.parseLong(lendToPeople));
					lend.setLendFromCharge(Long.parseLong(lendFromCharge));
					lend.setLendFlag(lendFlag.equals("true")  ? true : false);

					

					// 执行插入过程
					if (LendBLL.Insert(lend) > 0) {

						// 插入成功
						request.setAttribute("infoType", "success");
						request.setAttribute("infoContext", "插入成功");

						// 跳转到借出管理页面
						RequestDispatcher rd = request.getRequestDispatcher("LendServlet?action=list");
						rd.forward(request, response);
					} else {

						// 插入失败
						request.setAttribute("infoType", "error");
						request.setAttribute("infoContext", "插入失败，此设备号已存在");
						RequestDispatcher rd = request.getRequestDispatcher("LendAdd.jsp");
						rd.forward(request, response);
					}
				}
			}
		}

		// *************************************************************************************
		// 编辑设备信息
		else if(action.toLowerCase().equals("edit")){
				
				String save =  request.getParameter("save");
				if(save == null || save == ""){
					
					//不是保存过程，给出现在需要编辑的故障的信息，并且传递到前台
					
					//获得故障ID
					String lendID = request.getParameter("lendID");
					
					if(lendID == null || lendID == ""){
						
						//没有获取到lendID，跳转到管理员管理列表界面
						request.setAttribute("infoType", "error");
						request.setAttribute("infoCotnext", "故障信息不存在");
						RequestDispatcher rd = request.getRequestDispatcher("LendServlet?action=list");
						rd.forward(request, response);
						
					}
					else{
						
						//获取到了lendID
						LendModel lend = LendBLL.GetModel(Long.parseLong(lendID));
						if(lend == null){
							
							//没有在数据库中取到LendModel信息
							request.setAttribute("infoType", "error");
							request.setAttribute("infoCotnext", "故障信息不存在");
							RequestDispatcher rd = request.getRequestDispatcher("LendServlet?action=list");
							rd.forward(request, response);
							
						}
						else{
							
							//获取到了相应的管理员信息
							//将信息传递到LendEdit.jsp页面，进行更新
							request.setAttribute("LendModel", lend);
							RequestDispatcher rd = request.getRequestDispatcher("LendEdit.jsp");
							rd.forward(request, response);
							
						}				
					}
					
				}
				else{
					
					//是保存过程
					//是保存表单的过程
					String lendID = request.getParameter("lendID");
					String lendEqpRFID = request.getParameter("lendEqpRFID");
					String lendDepartmentID = request.getParameter("lendDepartmentID");
					String lendDate = request.getParameter("lendDate");
					String lendTime = request.getParameter("lendTime");
					String lendDeadline = request.getParameter("lendDeadline");
					String lendToPeople = request.getParameter("lendToPeople");
					String lendFromCharge = request.getParameter("lendFromCharge");
					String lendFlag = request.getParameter("lendFlag");

					
					if (lendEqpRFID == null || lendDepartmentID == null ) {

						// 用户有数据没有输入
						request.setAttribute("infoType", "error");
						request.setAttribute("infoContext", "输入信息不完整");
						RequestDispatcher rd = request.getRequestDispatcher("LendAdd.jsp");
						rd.forward(request, response);
					}
					else{
						
						//用户输入了完整的信息
						
						//构造LendModel用于插入到数据库中
						
								LendModel lend = new LendModel();
								lend.setLendID(Long.parseLong(lendID));
								lend.setLendEqpRFID(lendEqpRFID);
								lend.setLendDepartmentID(Integer.parseInt(lendDepartmentID));
								
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								// String类型转换成Date类型
								
								//借出时间
								Date startDate = new Date();
								try {
									startDate = sdf.parse(lendDate);
								} catch (ParseException e) {
									e.printStackTrace();
								}		
								lend.setLendDate(startDate);
								
								//归还时间
								Date stayDate = new Date();
								try {
									stayDate = sdf.parse(lendTime);
								} catch (ParseException e) {
									e.printStackTrace();
								}							
								lend.setLendTime(stayDate);
								
								//借出截止时间
								Date deadDate = new Date();
								try {
									deadDate = sdf.parse(lendDeadline);
								} catch (ParseException e) {
									e.printStackTrace();
								}											
								lend.setLendDeadline(deadDate);
								
								lend.setLendToPeople(Long.parseLong(lendToPeople));
								lend.setLendFromCharge(Long.parseLong(lendFromCharge));
								lend.setLendFlag(lendFlag.equals("true")  ? true : false);
	
						
						//执行插入过程
						if(LendBLL.Update(lend) > 0){
							
							//更新成功
							request.setAttribute("infoType", "success");
							request.setAttribute("infoContext", "更新成功");
							
							//跳转到管理员管理页面
							RequestDispatcher rd = request.getRequestDispatcher("LendServlet?action=list");
							rd.forward(request, response);
						}
						else{
							
							//更新失败
							request.setAttribute("infoType", "error");
							request.setAttribute("infoContext", "更新失败");
							RequestDispatcher rd = request.getRequestDispatcher("LendServlet?action=list");
							rd.forward(request, response);
						}
					}
				}
							
			}

		// **************************************************************************************

		// 删除设备信息
		else if (action.toLowerCase().equals("delete")) {

			String lendID = request.getParameter("lendID");
			LendModel lend = LendBLL.GetModel(Long.parseLong(lendID));


			if (lendID == null) {

				// 没有获取到管理员ID
				request.setAttribute("infoType", "error");
				request.setAttribute("infoContext", "删除失败");

				// 页面跳转
				RequestDispatcher rd = request.getRequestDispatcher("lendServlet?action=list");

				rd.forward(request, response);
			} else {

				if (LendBLL.Delete(lend) > 0) {

					// 删除成功
					request.setAttribute("infoType", "success");
					request.setAttribute("infoContext", "删除成功");

					// 页面跳转
					RequestDispatcher rd = request.getRequestDispatcher("LendServlet?action=list");

					rd.forward(request, response);
				} else {

					// 删除失败
					// 没有获取到借出ID
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "删除失败");

					// 页面跳转
					RequestDispatcher rd = request.getRequestDispatcher("LendServlet?action=list");

					rd.forward(request, response);
				}
			}
		}

		else {

			// 没有获取到相应的action信息，错误
			response.sendRedirect("LendServlet?action=list");

		}
	}

}
