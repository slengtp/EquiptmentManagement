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

import org.EMS.BLL.EqpBLL;
import org.EMS.BLL.EqpBLL;
import org.EMS.BLL.StaffBLL;
import org.EMS.Model.EqpModel;
import org.EMS.Model.StaffModel;

public class EqpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EqpServlet() {
		super();

	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		// 操作内容为列出所有员工信息
		if (action.toLowerCase().equals("list")) {

			// 从数据库中获取所有的员工条目

			// 把管理员信息传递到eqpManagement.jsp页面
			ArrayList<EqpModel> eqpList = EqpBLL.GetAllArrayList();
			if (eqpList.size() == 0) {
				request.setAttribute("infoType", "warning");
				request.setAttribute("infoContext", "没有员工信息");
			}
			// 将员工列表传递到前台jsp页面
			request.setAttribute("eqpList", eqpList);

			RequestDispatcher rd = request
					.getRequestDispatcher("EqpManagement.jsp");

			rd.forward(request, response);

		}

		// *********************************************************************************************************
		// 添加设备信息
		else if (action.toLowerCase().equals("add")) {

			String save = request.getParameter("save");
			if (save == null || save == "") {

				//不是保存表单的过程
				
				//给出添加设备信息的页面
				RequestDispatcher rd = request.getRequestDispatcher("EqpAdd.jsp");
				
				rd.forward(request, response);
				
			} else {

				// 是保存表单的过程
				String eqpRFID = request.getParameter("eqpRFID");
				String eqpName = request.getParameter("eqpName");
				String eqpID = request.getParameter("eqpID");
				String eqpModel = request.getParameter("eqpModel");
				String eqpManufacturer = request.getParameter("eqpManufacturer");
				String eqpStartUsingDate = request.getParameter("eqpStartUsingDate");
				String eqpAvailableTime = request.getParameter("eqpAvailableTime");
				String eqpDepartmentID = request.getParameter("eqpDepartmentID");
				String eqpUserID = request.getParameter("eqpUserID");

				if (eqpRFID == null || eqpName == null || eqpID == null) {

					// 用户有数据没有输入
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "输入信息不完整");
					RequestDispatcher rd = request.getRequestDispatcher("EqpAdd.jsp");
					rd.forward(request, response);
				} else {

					// 用户输入了完整的信息

					// 构造eqpModel用于插入到数据库中
					EqpModel eqp = new EqpModel();
					eqp.setEqpRFID(eqpRFID);
					eqp.setEqpName(eqpName);
					eqp.setEqpID(Integer.parseInt(eqpID));
					eqp.setEqpModel(eqpModel);
					eqp.setEqpManufacturer(eqpManufacturer);

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					// String类型转换成Date类型
					Date startDate = null;
					try {
						System.out.println("Date ====> " + eqpStartUsingDate);
						startDate = sdf.parse(eqpStartUsingDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					eqp.setEqpStartUsingDate(startDate);

					Date AvailDate = new Date();
					try {
						AvailDate = sdf.parse(eqpAvailableTime);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					eqp.setEqpAvailableTime(AvailDate);

					eqp.setEqpDepartmentID(Integer.parseInt(eqpDepartmentID));
					eqp.setEqpUserID(Integer.parseInt(eqpUserID));

					// 执行插入过程
					if (EqpBLL.Insert(eqp) > 0) {

						// 插入成功
						request.setAttribute("infoType", "success");
						request.setAttribute("infoContext", "插入成功");

						// 跳转到管理员管理页面
						RequestDispatcher rd = request.getRequestDispatcher("EqpServlet?action=list");
						rd.forward(request, response);
					} else {

						// 插入失败
						request.setAttribute("infoType", "error");
						request.setAttribute("infoContext", "插入失败，此设备号已存在");
						RequestDispatcher rd = request.getRequestDispatcher("EqpAdd.jsp");
						rd.forward(request, response);
					}
				}
			}
		}

		// *************************************************************************************
		// 编辑设备信息
		else if (action.toLowerCase().equals("edit")) {

			String save = request.getParameter("save");
			if (save == null || save == "") {

				//不是保存过程，给出现在需要编辑的员工的信息，并且传递到前台
				
				//获得员工ID
				String eqpID = request.getParameter("eqpID");
				
				if(eqpID == null || eqpID == ""){
					
					//没有获取到AdminID，跳转到管理员管理列表界面
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "设备信息不存在");
					RequestDispatcher rd = request.getRequestDispatcher("EqpServlet?action=list");
					rd.forward(request, response);
					
				}
				else{
					
					//获取到了eqpID
					EqpModel eqp = EqpBLL.GetModelByID(eqpID);
					if(eqp == null){
						
						//没有在数据库中取到AdminModel信息
						request.setAttribute("infoType", "error");
						request.setAttribute("infoContext", "设备信息不存在");
						RequestDispatcher rd = request.getRequestDispatcher("EqpServlet?action=list");
						rd.forward(request, response);
						
					}
					else{
						
						//获取到了相应的管理员信息
						//将信息传递到AdminEdit.jsp页面，进行更新
						request.setAttribute("EqpModel", eqp);
						RequestDispatcher rd = request.getRequestDispatcher("EqpEdit.jsp");
						rd.forward(request, response);
						
					}				
				}
				
			}
			else {

				// 是保存表单的过程
				String eqpRFID = request.getParameter("eqpRFID");
				String eqpName = request.getParameter("eqpName");
				String eqpID = request.getParameter("eqpID");
				String eqpModel = request.getParameter("eqpModel");
				String eqpManufacturer = request.getParameter("eqpManufacturer");
				String eqpStartUsingDate = request.getParameter("eqpStartUsingDate");
				String eqpAvailableTime = request.getParameter("eqpAvailableTime");
				String eqpDepartmentID = request.getParameter("eqpDepartmentID");
				String eqpUserID = request.getParameter("eqpUserID");

				if (eqpRFID == null || eqpName == null || eqpID == null) {

					// 用户有数据没有输入
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "输入信息不完整");
					RequestDispatcher rd = request.getRequestDispatcher("EqpServlet?action=edit&eqpID= "+ eqpID);
					rd.forward(request, response);
				} else {

					// 用户输入了完整的信息

					// 构造eqpModel用于插入到数据库中
					EqpModel eqp = new EqpModel();
					eqp.setEqpRFID(eqpRFID);
					eqp.setEqpName(eqpName);
					eqp.setEqpID(Integer.parseInt(eqpID));
					eqp.setEqpModel(eqpModel);
					eqp.setEqpManufacturer(eqpManufacturer);

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					// String类型转换成Date类型
					Date startDate = new Date();
					try {
						startDate = sdf.parse(eqpStartUsingDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					eqp.setEqpStartUsingDate(startDate);
					
					System.out.println("Date ====> " + eqpAvailableTime);

					Date AvailDate = new Date();
					try {
						AvailDate = sdf.parse(eqpAvailableTime);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					eqp.setEqpAvailableTime(AvailDate);

					eqp.setEqpDepartmentID(Integer.parseInt(eqpDepartmentID));
					eqp.setEqpUserID(Integer.parseInt(eqpUserID));

					// 执行插入过程
					if (EqpBLL.Update(eqp) > 0) {

						// 插入成功
						request.setAttribute("infoType", "success");
						request.setAttribute("infoContext", "更新成功");

						// 跳转到管理员管理页面
						RequestDispatcher rd = request
								.getRequestDispatcher("EqpServlet?action=list");
						rd.forward(request, response);
					} else {

						// 插入失败
						request.setAttribute("infoType", "error");
						request.setAttribute("infoContext", "更新失败");
						RequestDispatcher rd = request
								.getRequestDispatcher("EqpServlet?action=list");
						rd.forward(request, response);
					}
				}
			}
		}

		// **************************************************************************************

		// 删除设备信息
		else if (action.toLowerCase().equals("delete")) {

			String eqpID = request.getParameter("eqpID");
			EqpModel eqp = EqpBLL.GetModelByID(eqpID);

			if (eqpID == null) {

				// 没有获取到管理员ID
				request.setAttribute("infoType", "error");
				request.setAttribute("infoContext", "删除失败");

				// 页面跳转
				RequestDispatcher rd = request
						.getRequestDispatcher("eqpServlet?action=list");

				rd.forward(request, response);
			} else {

				if (EqpBLL.Delete(eqp) > 0) {

					// 删除成功
					request.setAttribute("infoType", "success");
					request.setAttribute("infoContext", "删除成功");

					// 页面跳转
					RequestDispatcher rd = request
							.getRequestDispatcher("EqpServlet?action=list");

					rd.forward(request, response);
				} else {

					// 删除失败
					// 没有获取到设备ID
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "删除失败");

					// 页面跳转
					RequestDispatcher rd = request
							.getRequestDispatcher("EqpServlet?action=list");

					rd.forward(request, response);
				}
			}
		}

		else {

			// 没有获取到相应的action信息，错误
			response.sendRedirect("EqpServlet?action=list");

		}
	}

}
