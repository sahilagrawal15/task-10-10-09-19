<%@page import="java.sql.*"%>

<%

String s1="";

	Cookie ck[]=request.getCookies();

if(ck!=null)
	for(Cookie c:ck){
		String name=c.getName();
		if(name.equals("id")){
			s1=c.getValue();
		
		}
	}







Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata", "root", "root");
PreparedStatement ps = con.prepareStatement("select password,username,address,mobile,email from users where userid=?");
ps.setString(1,s1);
ResultSet rs = ps.executeQuery(); 
%>




<html>
<body>

<h2>Update Profile for user</h2>

<%
while(rs.next())
{
	
String password=rs.getString(1);	
String username=rs.getString(2);	
String address=rs.getString(3);	
String mobile=rs.getString(4);	
String email=rs.getString(5);	


%>
<hr>
<form action="UpdateUser">

<input type="text" name="password" value="<%= password%>" ><br>
<input type="text" name="username" value="<%= username%>"><br>
<input type="text" name="address" value="<%= address%>"><br>
<input type="text" name="mobile" value="<%= mobile%>"><br>
<input type="text" name="email" value="<%= email%>"><br><br>
<input type="submit" value="Update Details">

<hr>


</form>
<%
}
%>
</body>
</html>