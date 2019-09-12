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

/**
 * Servlet implementation class BookDetailsServlet
 */
@WebServlet("/BookDetailsServlet")
public class BookDetailsServlet extends HttpServlet {
	static int i=1;
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	                  
 
		
		String code=request.getParameter("code");
		PrintWriter out=response.getWriter();
		
		/*
		 * String s5=request.getParameter("Author"); double n=Double.parseDouble(s5);
		 */
		
		
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata","root","root");
		String sql="SELECT * from books where bcode=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, Integer.parseInt(code));
		ResultSet rs=ps.executeQuery();
		out.println("<html>");
		out.println("<html><body>");
		
		 String k=String.valueOf(i);
	     Cookie c = new Cookie("visit"+code,k);
	    
	     response.addCookie(c);
	     int j=Integer.parseInt(c.getValue());
	     if(j==1)
	     {
	         out.println("Welcome");
	     }
	     else
	     {
	         out.println("You visited "+i+" times");
	     }
	             i++;     
		
			/*
			 * if(i>5) { n=n*1.1; out.println(n); } else if(i>10) { n=n*1.2; out.println(n);
			 * } else if(i>20) { n=n*1.3; out.print(n); }
			 */
		out.println("<h3>Book-Details</h3>");
		out.println("<hr>");
		while(rs.next()){
			String bcode=rs.getString(1);
			String title=rs.getString(2);
			String author=rs.getString(3);
			String subject=rs.getString(4);
			String price=rs.getString(5);
			out.println("<table border=2>");
			out.println("<tr>");
			out.println("<td>BCode</td>");
			out.println("<td>"+bcode+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Title</td>");
			out.println("<td>"+title+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Author</td>");
			out.println("<td>"+author+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Subject</td>");
			out.println("<td>"+subject+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Price</td>");
			
			if(i<5)
			out.println("<td>"+price+"</td>");
			else if(i>5 &&i<10)
				out.println("<td>"+Integer.parseInt(price)*1.1+"</td>");
			else if(i>10 && i<20)
				out.println("<td>"+Integer.parseInt(price)*1.2+"</td>");
			out.println("</tr>");
			out.println("</table>");
		}
		out.println("<hr>");
		out.println("<a href=CartManager?code="+code+">Add-To-Cart</a><br>");
		out.println("<a href=SubjectPageServlet>Subject-Page</a><br>");
		out.println("<a href=buyerpage.jsp>Buyer-Page</a><br>");
		out.println("</body></html>");
		
		
	    
	    
		
		
		
		}catch(Exception e){
			out.println(e);
		}
	}
}
