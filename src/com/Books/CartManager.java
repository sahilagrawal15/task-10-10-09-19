package com.Books;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CartManager")
public class CartManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		PrintWriter out=response.getWriter();
		
		HttpSession session=request.getSession();
		String str=request.getParameter("code");
		List<String> list1 = new ArrayList<String>();
		if(session.getAttribute("name")!=null)
		{
			list1=(List<String>) session.getAttribute("name");
			list1.add(str);
			
		}
		
		else
		{
			
			list1.add(str);
			session.setAttribute("name", list1);
			
		}
		out.println(list1);
		
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata","root","root");
			String sql="SELECT * from books where bcode=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, str);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				 out.println(rs.getString(1));
				 out.println(rs.getString(2));
				 out.println(rs.getString(3));
				 out.println(rs.getString(4));
				 out.println(rs.getString(5));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	

}
