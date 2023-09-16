 package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Person;
import factory.PersonServiceFactory;
import service.IPersonService;


@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static
	{
		System.out.println("ControllerServlet class loaded..");
	}
   
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}

	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI=request.getRequestURI();
		System.out.println(requestURI+"...");
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
			System.out.println(atype);
			String addr=request.getParameter("paddr");
			System.out.println(addr);
			String pwd=request.getParameter("pwd");
			
			Person person=new Person();
			person.setPname(pname);
			person.setFname(fname);
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
				rd=request.getRequestDispatcher("../update.html");
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
			System.out.println("controller.."+ano);
			String pname=request.getParameter("pname");
			System.out.println("controller.."+pname);
			String pwd=request.getParameter("pwd");
			System.out.println("controller.."+pwd);
			
			Integer aNo = Integer.parseInt(ano);
			Person person= perService.getPerson(aNo);
			
			System.out.println("Person received successfully..");
			if (person != null) 
			{
				
				String actualPassword= person.getPwd();
				String actualName= person.getPname();
				
				System.out.println(actualName + " " + actualPassword+"....");
				if(actualPassword.equalsIgnoreCase(pwd) && actualName.equalsIgnoreCase(pname))
                {
					System.out.println(actualName + " " + actualPassword);
					HttpSession session = request.getSession();
					session.setAttribute("person", person);

					System.out.println(actualName + " " + actualPassword);
					System.out.println("Sending to accountPage.html");
					rd = request.getRequestDispatcher("../accountPage.html");
					rd.forward(request, response);
				} 
				else
				{
					rd=request.getRequestDispatcher("../failureAfterLogin.html");
					rd.forward(request, response);
				}
		      }
			 else
			 {
				System.out.println("Person is null");
			 }
		}
			
		if(requestURI.endsWith("deposit"))
		{
			String amount=request.getParameter("amount");
			HttpSession session = request.getSession(false);
			
			Person person= (Person) session.getAttribute("person");
			Integer aNo = person.getaNo();
			Double aBalance = person.getAmount();
		
			Double updatedBalance = aBalance +  Double.valueOf(amount);
			
			String status=perService.deposit(person, updatedBalance);
					
			System.out.println(status);
			
			if(status.equals("success"))
			{
				rd=request.getRequestDispatcher("../success.html");
				rd.forward(request, response);
			}
			else
			{
				rd=request.getRequestDispatcher("../failureAfterLogin.html");
				rd.forward(request, response);
			}
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
//		}
		
	}
}
