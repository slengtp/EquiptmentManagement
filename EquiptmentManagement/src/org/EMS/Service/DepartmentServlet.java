package org.EMS.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.EMS.BLL.DepartmentBLL;
import org.EMS.Model.DepartmentModel;

public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		if(action.toLowerCase().equals("list")){
			
			//������еĲ�����Ϣ
			ArrayList<DepartmentModel> departmentList = DepartmentBLL.GetAllArrayList();
			request.setAttribute("departmentList", departmentList);
			
			//ҳ�洫��
			RequestDispatcher rd = request.getRequestDispatcher("DepartmentManagement.jsp");
			
			rd.forward(request, response);
			
		}
		else if(action.toLowerCase().equals("add")){
			
			String save = request.getParameter("save");
			if(save == null || save == ""){
				
				
				
			}
			else{
				//ͨ��ajax���д���
				String level = request.getParameter("level");
				String departmentName = request.getParameter("departmentName");
				if(level.equals("1")){
					
					//���һ������
					DepartmentModel dpt  = new DepartmentModel();
					dpt.setDepartmentName(departmentName);
					dpt.setDepartmentParentID(0);
					if(DepartmentBLL.Insert(dpt) > 0){
						
						//����ɹ�
						
					}
					else{
						
						//����ʧ��
						
					}
					
					
				}
				else if(level.equals("2")){
					
					//��Ӷ�������
					String departmentParentID = request.getParameter("departmentParentID");
					DepartmentModel dpt  = new DepartmentModel();
					dpt.setDepartmentName(departmentName);
					dpt.setDepartmentParentID(Integer.valueOf(departmentParentID));
					if(DepartmentBLL.Insert(dpt) > 0){
						
						//����ɹ�
						
					}
					else{
						
						//����ʧ��
						
					}
				}
				else{
				
				//û�л�ȡ��Level
				}
			}
					
			
		}
		else if(action.toLowerCase().equals("edit")){
			
			
			
		}
		else if(action.toLowerCase().equals("delete")){
			
			
			
		}
		
	}


}
