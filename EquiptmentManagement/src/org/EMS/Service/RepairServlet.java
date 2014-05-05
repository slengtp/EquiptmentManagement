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
		
		
		//��������Ϊ�г�����ά����Ϣ
		if(action.toLowerCase().equals("list")){
		
			//�����ݿ��л�ȡ���е�ά����Ŀ
			
			//�ѹ���Ա��Ϣ���ݵ�RepairManagement.jspҳ��
			ArrayList<RepairModel> repairList = RepairBLL.GetAllArrayList();
			if(repairList.size() == 0){
				request.setAttribute("infoType", "warning");
				request.setAttribute("infoContext", "û��ά����Ϣ");
			}
			//��ά����Ϣ�б��ݵ�ǰ̨jspҳ��
			request.setAttribute("repairList", repairList);
			
			RequestDispatcher rd = request.getRequestDispatcher("RepairManagement.jsp");
				
			rd.forward(request, response);
			
		}
		
		//*********************************************************************************************************
		//���ά����Ϣ
		else if(action.toLowerCase().equals("add")){
			
			String save = request.getParameter("save");
			if(save == null || save == ""){
				
				//���Ǳ�����Ĺ���
				
				//�������ά����Ϣ��ҳ��
				RequestDispatcher rd = request.getRequestDispatcher("RepairAdd.jsp");
				
				rd.forward(request, response);
			
			}
			else{
				
				//�Ǳ�����Ĺ���
				String rpEqpRFID = request.getParameter("rpEqpRFID");
				String rpDate = request.getParameter("rpDate");
				String rpPeople = request.getParameter("rpPeople");
				String rpDescribe = request.getParameter("rpDescribe");
				String rpCondition = request.getParameter("rpCondition");
				
				
				if(rpEqpRFID == null || rpCondition == null){
					
					//�û�������û������
					
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "������Ϣ������");
					RequestDispatcher rd = request.getRequestDispatcher("RepairAdd.jsp");
					rd.forward(request, response);
				}
				else{
					
					//�û���������������Ϣ
					
					//����RepairModel���ڲ��뵽���ݿ���
					RepairModel repair = new RepairModel();
					repair.setRpEqpRFID(rpEqpRFID);
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					// String����ת����Date����
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

					
					//ִ�в������
					if(RepairBLL.Insert(repair) > 0){
						
						//����ɹ�
						request.setAttribute("infoType", "success");
						request.setAttribute("infoContext", "����ɹ�");
						
						//��ת������Ա����ҳ��
						RequestDispatcher rd = request.getRequestDispatcher("RepairServlet?action=list");
						rd.forward(request, response);
					}
					else{
						
						//����ʧ��
						request.setAttribute("infoType", "error");
						request.setAttribute("infoContext", "����ʧ��");
						RequestDispatcher rd = request.getRequestDispatcher("RepairAdd.jsp");
						rd.forward(request, response);
					}
				}
			}
		}
		
		
		//*************************************************************************************
		//�༭ά����Ϣ
		else if(action.toLowerCase().equals("edit")){
			
			String save =  request.getParameter("save");
			if(save == null || save == ""){
				
				//���Ǳ�����̣�����������Ҫ�༭��ά�޵���Ϣ�����Ҵ��ݵ�ǰ̨
				
				//���ά��ID
				String rpID = request.getParameter("rpID");
				
				if(rpID == null || rpID == null){
					
					//�û�������û������
					
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "ά����Ϣ������");
					RequestDispatcher rd = request.getRequestDispatcher("RepairServlet?action=list");
					rd.forward(request, response);
				}
				else{
					
					//��ȡ����RepairID
					RepairModel repair = RepairBLL.GetModel(Long.parseLong(rpID));
					if(repair == null){
						
						//û�������ݿ���ȡ��RepairModel��Ϣ
						request.setAttribute("infoType", "error");
						request.setAttribute("infoCotnext", "ά����Ϣ������");
						RequestDispatcher rd = request.getRequestDispatcher("RepairServlet?action=list");
						rd.forward(request, response);
						
					}
					else{
						
						//��ȡ������Ӧ�Ĺ���Ա��Ϣ
						//����Ϣ���ݵ�RepairEdit.jspҳ�棬���и���
						request.setAttribute("RepairModel", repair);
						RequestDispatcher rd = request.getRequestDispatcher("PepairEdit.jsp");
						rd.forward(request, response);
						
					}				
				}
				
			}
			else{
				
				//�Ǳ������
				//�Ǳ�����Ĺ���
				String rpEqpRFID = request.getParameter("rpEqpRFID");
				String rpDate = request.getParameter("rpDate");
				String rpPeople = request.getParameter("rpPeople");
				String rpDescribe = request.getParameter("rpDescribe");
				String rpCondition = request.getParameter("rpCondition");
				
				
				if(rpEqpRFID == null || rpCondition == null){
					
					//�û�������û������
					request.setAttribute("infoType", "error");
					request.setAttribute("infoContext", "������Ϣ������");
					RequestDispatcher rd = request.getRequestDispatcher("RepairServlet?action=list");
					rd.forward(request, response);
				}
				else{
					
					//�û���������������Ϣ
					
					//����RepairModel���ڲ��뵽���ݿ���
					RepairModel repair = new RepairModel();
					repair.setRpEqpRFID(rpEqpRFID);
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					// String����ת����Date����
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

					
					//ִ�в������
					if(RepairBLL.Insert(repair) > 0){
						
						//����ɹ�
						request.setAttribute("infoType", "success");
						request.setAttribute("infoContext", "���³ɹ�");
						
						//��ת������Ա����ҳ��
						RequestDispatcher rd = request.getRequestDispatcher("RepairServlet?action=list");
						rd.forward(request, response);
					}
					else{
						
						//����ʧ��
						request.setAttribute("infoType", "error");
						request.setAttribute("infoContext", "����ʧ��");
						RequestDispatcher rd = request.getRequestDispatcher("RepairServlet?action=list");
						rd.forward(request, response);
					}
				}
			}
						
		}
		
		
		//**************************************************************************************
		
				//ɾ��ά����Ϣ
				else if(action.toLowerCase().equals("delete")){
					
					String rpID = request.getParameter("rpID");
					RepairModel repair = RepairBLL.GetModel(Long.parseLong(rpID));
					if(rpID == null){
						
						//û�л�ȡ������ԱID
						request.setAttribute("infoType", "error");
						request.setAttribute("infoContext", "ɾ��ʧ��");
						
						//ҳ����ת
						RequestDispatcher rd = request.getRequestDispatcher("RepairServlet?action=list");
					
						rd.forward(request, response);
					}
					else{
						
						if(RepairBLL.Delete(repair) > 0){
							
							//ɾ���ɹ�
							request.setAttribute("infoType", "success");
							request.setAttribute("infoContext", "ɾ���ɹ�");
							
							//ҳ����ת
							RequestDispatcher rd = request.getRequestDispatcher("RepairServlet?action=list");
						
							rd.forward(request, response);
						}
						else{
							
							//ɾ��ʧ��
							//û�л�ȡ������ԱID
							request.setAttribute("infoType", "error");
							request.setAttribute("infoContext", "ɾ��ʧ��");
							
							//ҳ����ת
							RequestDispatcher rd = request.getRequestDispatcher("RepairServlet?action=list");
						
							rd.forward(request, response);
						}
					}			
				}
				
				
				
				else{
					
					//û�л�ȡ����Ӧ��action��Ϣ������
					response.sendRedirect("RepairServlet?action=list");
					
				}			
			}

		}
