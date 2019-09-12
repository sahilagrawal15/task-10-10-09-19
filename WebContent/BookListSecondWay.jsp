<%@page import="java.sql.*" %>
<html>
<body>
<table style="width:100%">
  <tr>
    <th>Bcode</th>
    <th>Btitle</th>
   
  </tr>

<%

		//String[] subject=request.getParameterValues("cbox");

String[] outerArray=request.getParameterValues("cbox");

String[] innerArray=outerArray[0].split(",");
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata","root","root");
		String sql="SELECT bcode,btitle from books where subject=?";
		PreparedStatement ps=con.prepareStatement(sql);
		
		
		for (int i = 0; i < outerArray.length; i++) {

	           innerArray=outerArray[i].split(",");         
	        }
		
		 for(int i=0;i<innerArray.length;i++)
		{
			ps.setString(1, innerArray[i]);
		} 
		
		ResultSet rs=ps.executeQuery();
		
	%>




<h2>Select the desired title</h2>

<%
while(rs.next())
{
%>
  <tr>
  <td><%=rs.getString(1) %></td>
  <td><%=rs.getString(2) %></td>
  
  
  </tr>
  
</table>
<%

}
%>
</body>
</html>