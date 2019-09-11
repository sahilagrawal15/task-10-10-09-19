package com.Books;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Connection con;
	private PreparedStatement ps;
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		

		super.init(config);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata","root","root");
			String sql="insert into Books values(?,?,?,?,?)";
			ps=con.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	@Override
	public void destroy() {
		super.destroy();
		try {
		con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();

		String Code = request.getParameter("code");
		String Name = request.getParameter("name");
		String Subject = request.getParameter("subject");
		String Title = request.getParameter("title");
		String Author = request.getParameter("author");
		
		try { 
		ps.setString(1, Code);
		ps.setString(2, Name);
		ps.setString(3, Subject);
		ps.setString(4, Title);
		ps.setString(5, Author);
		ps.executeUpdate();
//		
		out.println("<html>");
		out.println("<body>");
		out.println("Record Added Successfully");
		out.println("<br><br>");
		out.println("<a href=adminpage.jsp>ADMIN PAGE</a>");
		out.println("</body></html>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	

}
