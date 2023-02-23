<html>
<body>
<%@ page import="java.io.*,javax.ejb.*,java.rmi.*,project.server.*,javax.naming.*,java.util.*" %>
<%!Quotation quotation;%>
<%!QuotationHome quotationHome;%>
<%!Context initial;%>
<%!FileInputStream fis;%>
<%!FileOutputStream fos;%>
<%!ObjectInputStream ois;%>
<%!ObjectOutputStream oos;%>
<%!Handle myhandle;%>



<%!
	public Context getInitialContext()throws Exception
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
	Object key;
	String value;
	StringTokenizer tok;
	initial=getInitialContext();



	try
		{
		fis=new FileInputStream("equota.ser");
		ois=new ObjectInputStream(fis);
		myhandle=(Handle)ois.readObject();
		quotation=(project.server.Quotation)myhandle.getEJBObject();
		ois.close();
		fis.close();
		}
		catch(Exception ex)
		{
			if (ex instanceof FileNotFoundException )

			{
			quotationHome=(QuotationHome) initial.lookup("server.QuotationHome");
			quotation=quotationHome.create();
			myhandle = quotation.getHandle();
			fos=new FileOutputStream("equota.ser");
			oos=new ObjectOutputStream(fos);
			oos.writeObject(myhandle);
			}
		} 


	String pid=request.getParameter("pid");
	quotation.removeProduct(pid);

}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	out.println("<br>To add new item  <a href=\"http://localhost:7001/catalog.jsp\">Click Hear </a>"); 
	out.println("<form  name=\"f1\" action=\"http://localhost:7001/removecatalog.jsp\"   method=\"GET\" >");	
	out.println("<br> To remove another item Enter the Product ID <INPUT TYPE=\"text\"  name=\"pid\"   >");
	out.println(" <br> <INPUT TYPE=\"submit\" value=\"Remove\">");
	out.println("</form>");
	out.println("<br> To View All Products in The Quotation <a href=\"http://localhost:7001/entirequotation.jsp\"> Click </a>");
	out.println("</body></html>");



%>

</body>
</html>