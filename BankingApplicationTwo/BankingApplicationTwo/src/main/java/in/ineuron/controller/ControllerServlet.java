package in.ineuron.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.dto.Person;
import in.ineuron.factory.PersonServiceFactory;
import in.ineuron.service.IPersonService;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI=request.getRequestURI();
		System.out.println(requestURI);
		IPersonService perService=PersonServiceFactory.getPersonService();
		RequestDispatcher rd=null;
		
		if(requestURI.endsWith("index"))
		{
			rd=request.getRequestDispatcher("../index.html");
			rd.forward(request, response);
		}
		if(requestURI.endsWith("register"))
		{
			String pname=request.getParameter("pname");
			String mname=request.getParameter("mname");
			String fname=request.getParameter("fname");
			String page=request.getParameter("page");
			String mno=request.getParameter("mno");
			String atype=request.getParameter("atype");
			String addr=request.getParameter("addr");
			String pwd=request.getParameter("pwd");
			
			Person person=new Person();
			person.setPname(pname);
			person.setMname(mname);
			person.setMname(mname);
			person.setPage(Integer.parseInt(page));
			person.setMno(Double.parseDouble(mno));
			person.setAtype(atype);
			person.setPaddr(addr);
			person.setPwd(pwd);
			
			String status=perService.register(person);
			System.out.println(status);
			if(status.equals("success"))
			{
				//System.out.println("");
				rd=request.getRequestDispatcher("../loginAfterReg.html");
				//rd=request.getRequestDispatcher("../success.html");
				rd.forward(request, response);
			}
			else
			{
				rd=request.getRequestDispatcher("../failure.html");
				rd.forward(request, response);
			}
		}
		if(requestURI.endsWith("login"))
		{
			
			String ano=request.getParameter("ano");
			System.out.println("controller"+ano);
			String pname=request.getParameter("pname");
			System.out.println("controller"+pname);
			String pwd=request.getParameter("pwd");
			System.out.println("controller"+pwd);
			
			Person person= new Person();
			person.setaNo(Integer.parseInt(ano));
			person.setPname(pname);
			person.setPwd(pwd);
			
			String status=perService.login(person);
			System.out.println(status);
			
			if(status.equals("success"))
			{
				rd=request.getRequestDispatcher("../accountPage.html");
				rd.forward(request, response);
				if(requestURI.endsWith("deposit"))
				{
					String amount=request.getParameter("amount");
					person.setaNo(Integer.parseInt(ano));
					
					status=perService.deposit(person);
					System.out.println(status);
				}
			}
			else
			{
				rd=request.getRequestDispatcher("../failureAfterLogin.html");
				rd.forward(request, response);
			}
			//String ano=request.getParameter("ano");
			//System.out.println(ano);
			//String pname=request.getParameter("pname");
			//System.out.println(pname);
			//String pwd=request.getParameter("pwd");
			//System.out.println(pwd);
			
			
//			String pname1=person.getPname();
//			System.out.println(pname1);
//			String pwd1=person.getPwd();
//			System.out.println(pwd);
//			
//			if(pname.equals(pname1) && pwd.equals(pwd1))
//			{
//				rd=request.getRequestDispatcher("../accountPage.html");
//			}
		}
//		if(requestURI.endsWith("deposit"))
//		{
//			String amount=request.getParameter("amount");
//			person.setaNo(Integer.parseInt(ano));
//			
//			status=perService.deposit(person);
//			System.out.println(status);
//		}
	}
}
