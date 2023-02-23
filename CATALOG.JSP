<html>
<body>
<%@ page  import="javax.ejb.*,javax.naming.*,java.util.*,java.io.*,project.server.*,java.sql.*" %>

<%! Catalog catalog; %>
<%! CatalogHome catalogHome; %>
<%! ResultSet resultSet;%>
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
catalogHome=(CatalogHome)context.lookup("server.CatalogHome");
catalog=catalogHome.create();
resultSet=catalog.getProducts();

while (resultSet.next())		
{
String pid=resultSet.getString(1);
String pname=resultSet.getString(2);
int price=resultSet.getInt(3);
String  unitofmea=resultSet.getString(5);

out.println("<html><body>");
out.println("<from name=f1>");
out.println("<a href=\"http://localhost:7001/products.jsp?pid="+pid+" \">"+pname+" </a> ");
out.println(" "+price);
out.println(" "+unitofmea);
out.println(" <input type=\"text\" name=\"qty\"  >");

out.println("<a href=\"http://localhost:7001/quote.jsp?pid="+pid+"&price="+price+"&pname="+pname+"\"> Add to The Quote </a> <br>");
}
}
catch(Exception e)
{
e.printStackTrace();
}

out.println("<a href=\" http://localhost:7001/entirequotation.jsp\" > All Products in the Quotation</a>");
out.println("</form></body></html>");
%>
</body>	
</html>