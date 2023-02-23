<html>
<body>
<%@ page import="javax.ejb.*,javax.naming.*,java.util.*,java.io.*,project.server.*,java.sql.*" %>

<%! IProduct iproduct; %>
<%! IProductHome iproductHome; %>
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
iproductHome=(IProductHome)context.lookup("server.IProductHome");
IProductPK pk=new IProductPK();
pk.pid=request.getParameter("pid");
iproduct=iproductHome.findByPrimaryKey(pk);
out.println("<html> <body>");
out.println(" <br> The Product id is "+iproduct.getID());
out.println(" <br>  name  is :"+iproduct.getName());
out.println(" <br>  price   :"+iproduct.getPrice());
out.println(" <br> capacity is "+iproduct.getCapacity());
out.println(" <br>  the unit of measurement is "+iproduct.getUM());
out.println(" <br> the producer of the product is  "+iproduct.getProducer()+"<br>");
out.println (" <form name =\"f1\" > ");
out.println(" < input type=button name=b1 value=Back>");
out.println(" </form>");
out.println("</body></html>");
}
catch(Exception e)
{
e.printStackTrace();
}
%>
<body>
</html>	