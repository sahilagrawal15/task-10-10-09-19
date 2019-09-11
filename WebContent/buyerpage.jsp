<%
//In JSP, Session is an implicit object
//So we have to do one step less than the servlets
//Here we can directly call getAttribut without fetching or using setAttribute.....
String user=(String)session.getAttribute("user");

//authenticate
if(user==null)
{
	response.sendRedirect("index.jsp");
	}


%>

<html>
<body>
	<h3>DashBoard-For-<%=user %></h3>
	<hr>
	<pre>
		<a href="SubjectPageServlet">Explore-Store</a>
		<a href="">Search-Book</a>
		<a href="DisplayCart">View-Cart</a>
		<a href="">Trace-Order</a>
		<a href="">Logout</a>
	</pre>		
	<hr>
</body>
</html>