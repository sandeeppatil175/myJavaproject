<html>
<body>
<%@ page import="javax.ejb.*,java.rmi.*,project.server.*,javax.naming.*,java.util.*,java.io.*" %>
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
<%!--		try
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
			oos.close();
			fos.close();

			}
		} 
--!%>
	quotationHome=(QuotationHome) initial.lookup("server.QuotationHome");
	quotation=quotationHome.create();


	Hashtable htab=quotation.getAllProducts();
	 if (htab==null)
	 {
	 out.println(" the item is null");
	 }
	 else
	 {
	 out.println(" the no of  items in the quotation are "+htab.size());
	 }
	 Enumeration keys=htab.keys();

	while(keys.hasMoreElements())
	{
	key=keys.nextElement();
	value=(String)htab.get(key);
	tok=new StringTokenizer(value,":");
	tok.hasMoreElements();
	out.println("<br>"+key+" "+tok.nextToken()+" "+ tok.nextToken());
	}

        }
	catch(Exception e)
	{
	e.printStackTrace();
	}
	out.println("<br>To add new item  <a href=\"http://localhost:7001/catalog.jsp\">Click Hear </a>"); 
	out.println(" <br> <form   name=\"f1\"  ACTION=\"http://localhost:7001/removecatalog.jsp\"  METHOD=\"GET\"   >");
	out.println("<br> To remove an item Enter the Product ID <INPUT  TYPE=\"text\"  NAME=\"pid\"   >");
	out.println(" <br> <INPUT TYPE=\"submit\"  VALUE=\"Remove\"    >");
	out.println("</form>");
	out.println("<br><br> TO Buy Click <a href=\"http://localhost:7001/confirmation.jsp\">Confirm<a>");



%>

</body>
</html>