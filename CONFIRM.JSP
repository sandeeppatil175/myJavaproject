<html>
<body>
<%@ page  import="javax.ejb.*,javax.naming.*,java.util.*,java.io.*,project.client.*,java.sql.*" %>

<%! Confirm confirm; %>
<%! ConfirmHome confirmHome; %>
<%! Context context; %>
<%!

public Context getInitialContext() throws Exception 
{
Hashtable h=new Hashtable();
h.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
h.put(Context.PROVIDER_URL,"t3://localhost:7001");
return new InitialContext(h);
}

%>
<% 
try
{
context=getInitialContext();
confirmHome=(ConfirmHome)context.lookup("client.ConfirmHome");
confirm=confirmHome.create();
String uid=confirm.doTransaction(request.getParameter("loginid"),request.getParameter("login"),request.getParameter("passwd"));
out.println("<br><center><h3> You Have ordered the products</h3>");
out.println("<h2> Thanks for purchasing from us</h2>");
out.println(" your Unique id of transaction is <h4>"+uid+"</h4>");
}
catch(Exception e)
{		  e.printStackTrace();
}

%>
</body>	
</html>