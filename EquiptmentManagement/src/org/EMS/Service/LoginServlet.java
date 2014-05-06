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
	 * �����¼��Ϣ
	 */
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Long staffID = Long.valueOf(request.getParameter("userName"));
		String password = (String)request.getParameter("password");
		
		if(staffID == null || password == null || password == ""){
			
			//���Ƚ��������жϣ������ſͻ���
			//��ת����¼ҳ��
			request.setAttribute("infoType", "error");
			request.setAttribute("infoContext", "�������û���������");
		    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		    
		    rd.forward(request, response);
			
			
		}
		else{
			
			//�û��������û��������룬���д������ж�
			StaffModel staff = StaffBLL.GetModel(staffID);
			
			if(staff == null){
				
				//Ա����Ϣ������
				request.setAttribute("infoType", "error");
				request.setAttribute("infoContext", "�û���Ϣ������");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
			else{
				
				//�ж������Ƿ���ȷ
				if(password.equals(staff.getStaffPassword())){
					//������ȷ
					//����ϵͳ�����й���
					
					//TODO �����ת
					System.out.println("��¼�ɹ�");
				}
				else{
					//�������
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "�������");
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
				}
				
			}
		}
	}
}
