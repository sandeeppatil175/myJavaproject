<html>
<body>

<% if ((request.getParameter("uname").equals("rajendra")) && (request.getParameter("passw").equals("sankara") ))
		{
			
			out.println("<html><body>");
			out.println("<a href=\"http://localhost:7001/catalog.jsp\" > The catalog </a> ");
		
		}
	else 
	{
	out.println(" the user or password entered is not correct enter again");
	}
	out.println("</body></html>");

	%>


</body>
</html>