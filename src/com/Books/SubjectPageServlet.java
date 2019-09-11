package com.Books;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/SubjectPageServlet")
public class SubjectPageServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//fetch session
		HttpSession session=request.getSession();
		
		//read data
		String user=(String) session.getAttribute("user");
		
		//authenticate if user has logged in or not
		if(user==null)
		{
			response.sendRedirect("index.jsp");
		}
		/*
		 * String s="All Books"; Cookie ck[]=request.getCookies(); if(ck!=null) {
		 * for(Cookie c:cookie) { if(c.getName().equals("name")) { s=c.getValue(); } } }
		 */
		PrintWriter out=response.getWriter();
		try{
		//Connection and Driver load
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata","root","root");
		
		String sql="SELECT distinct subject from books";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		out.println("<html>");
		out.println("<html><body>");
		out.println("<marquee><h4>Attractive Offers On All Books</h4></marquee>");
		out.println("<h3>Welcome "+user+" </h3>");
		out.println("<h3>Select The Desired Subject</h3>");
		out.println("<hr>");
		while(rs.next()){
			String sub=rs.getString(1);
			out.println("<a href=BookListServlet?subject="+sub+">");
			out.println(sub);
			out.println("</a><br>");
		}
		out.println("<hr>");
		out.println("<a href=buyerpage.jsp>Buyer-Page</a>");
		out.println("</body></html>");
		
		
		
		
		}catch(Exception e){
			out.println(e);
		}
	}

}
