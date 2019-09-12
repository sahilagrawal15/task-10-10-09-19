package com.Books;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
		
		private Connection con;
		private PreparedStatement ps;
		
		
		public void init(){
			try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata","root","root");
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		public void destroy(){
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				
				String sql="update users set password=?,username=?,mobile=?,address=?,email=? where userid=?";
				ps=con.prepareStatement(sql);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			HttpSession session=request.getSession();
			
			String s=(String)session.getAttribute("user");

			PrintWriter out=response.getWriter();
			//reads-request
			String userid=s;
			String password=request.getParameter("password");
			String username=request.getParameter("username");
			String address=request.getParameter("address");
			String mobile=request.getParameter("mobile");
			String email=request.getParameter("email");
			//process
			try{
				ps.setString(6, s);
				ps.setString(1, password);
				ps.setString(2, username);
				ps.setString(3, address);
				ps.setString(4, mobile);
				ps.setString(5, email);
				int n=ps.executeUpdate();
				out.println("Updation Completed");
				
			}catch(Exception e){
				out.println(e);
			}
			//provides-response
			
		}

	}