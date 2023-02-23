<html>
<body>
<%@ page import="javax.ejb.*,java.rmi.*,project.server.*,javax.naming.*,java.util.*" %>
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

	initial=getInitialContext();
	String pid=request.getParameter("pid");
	String pname=request.getParameter("pname");
	int price=Integer.parseInt(request.getParameter("price"));
	
<!-- try
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
--!>
			quotationHome=(QuotationHome) initial.lookup("server.QuotationHome");
			quotation=quotationHome.create();


	quotation.addProduct(pid,price,pname);
	out.println("added to the quotation ");
	}
		catch(Exception e)
		{
		e.printStackTrace();
		}
%>
</body>
</html>