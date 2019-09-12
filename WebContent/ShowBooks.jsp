<%@include file="forIncludingData.jsp"%>
<!-- for including or loading a file data into another file -->

<%@page import="java.sql.*"%>


<%
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata", "root", "root");
	String sql = "select * from books";
	PreparedStatement ps = con.prepareStatement(sql);
	ResultSet rs = ps.executeQuery();
%>

<html>
<body>
	<h2>List of Books</h2>

	<%
		while (rs.next()) {
			String bcode = rs.getString(1);
			String title = rs.getString(2);
			String author = rs.getString(3);
			String subject = rs.getString(4);
			String price = rs.getString(5);
	%>
	<%=bcode%>
	<%=title%>
	<%=author%>
	<%=subject%>
	<%=price%>

	<%
		}
	%>






</body>
</html>