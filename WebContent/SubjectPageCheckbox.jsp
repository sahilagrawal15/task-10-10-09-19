<%@page import="java.sql.*" %>
<%
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata","root","root");
	String sql="SELECT distinct subject from books";
	PreparedStatement ps=con.prepareStatement(sql);
	ResultSet rs=ps.executeQuery();
	
	String user=(String) session.getAttribute("user");
	if(user==null)
	{
		response.sendRedirect("index.jsp");
	}
	
%>
<html>
<body>
<h2>Welcome User</h2>
<h3>Select The Desired Subject</h3>
<hr><hr>

<%
while(rs.next())
{
	String sub=rs.getString(1);

%>
<form action="BookListSecondWay.jsp">
<input type="checkbox" name="cbox" value=<%=sub %>><%=sub %><br>



<%
}
%>

<input type="submit" value="Submit">
</form>

</body>
</html>