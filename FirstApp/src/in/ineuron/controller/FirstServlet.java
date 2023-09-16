package in.ineuron.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/first")
public class FirstServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	static
	{
		System.out.println("FirstServlet class loaded..");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter output = response.getWriter();
		
		output.println("<center>");
		output.println("<h1 style='color:red; ' ");
		output.println("Hello Everyone. My Name is Ashish Varshney");
		output.println("</h1></center>");
	}

}
