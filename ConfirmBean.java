package project.client;


import javax.ejb.*;
import java.rmi.*;
import javax.naming.*;
import java.util.*;
import javax.jts.*;
import java.io.*;
import java.sql.*;
import project.server.*;
import javax.jts.*;



public class ConfirmBean implements SessionBean,SessionSynchronization
{
Quotation quotation;
QuotationHome quotationHome;

SessionContext scontext;
Context context;

FileInputStream fis;
ObjectInputStream ois;
Handle myhandle;

Object key;
String value;
int totalPrice;
StringTokenizer tok;

StringBuffer pnames;

Connection con;
String uid;
int crbal;
UserTransaction utx;
Hashtable htab;


public Context 	 getInitialContext()  throws Exception
{
 Hashtable htab=new Hashtable();
 htab.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
 //htab.put(Context.PROVIDER_URL,"t3://localhost:7001");
 return new InitialContext(htab);
}

public void ejbCreate() throws CreateException
{
try
{

Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
}
catch(Exception e)
{
e.printStackTrace();
}
System.out.println(" in the create of the ConfirmBean ");
}

public void setSessionContext(SessionContext con)
{
scontext=con;
}

public void ejbActivate()
{
System.out.println(" in the ejb activate of the Confirm bean");
}

public void ejbPassivate()
{
System.out.println(" in the ejb passiavate of the Confirm bean");
}

public void ejbRemove()
{
System.out.println(" in the ejb remove of the Confirm bean");
}

public String  doTransaction(String lid,String log,String pas) 
{

 // context=getInitialContext();
	try
	 {
 	fis=new FileInputStream("equota.ser");
		ois=new ObjectInputStream(fis);
		myhandle=(Handle)ois.readObject();
		quotation=(project.server.Quotation)myhandle.getEJBObject();
		ois.close();
		fis.close();
	htab=quotation.getAllProducts();
	Enumeration keys=htab.keys();
	System.out.println(" got the hatab enum the  --- size is "+htab.size());	
	pnames=new StringBuffer();
	while(keys.hasMoreElements())
	{
	key=keys.nextElement();
	value=(String)htab.get(key);
	tok=new StringTokenizer(value,":");
	tok.hasMoreElements();


	pnames.append(tok.nextToken());
	pnames.append(",");
	totalPrice+=Integer.parseInt(tok.nextToken());
	}
	uid=uniqueID();
	System.out.println(" after the uniquid ");
	crbal=getACBalance(lid);
		System.out.println(" after the acbalance ");
	utx=(javax.jts.UserTransaction)context.lookup("javax.jts.UserTransaction");

	con=DriverManager.getConnection("jdbc:odbc:ejb","scott","tiger");
	//utx.begin();

	PreparedStatement pstmt1=con.prepareStatement("update account1 set crbal=crbal-"+totalPrice+" where logid=?");
	pstmt1.setString(1,lid);
	pstmt1.executeUpdate();

			System.out.println(" after  stmt 1 ");

	PreparedStatement pstmt2=con.prepareStatement("update cash set cashinhand=cashinhand+"+totalPrice);
	pstmt2.executeUpdate();

		System.out.println(" after  stmt 2 ");
	PreparedStatement pstmt3=con.prepareStatement("insert into trans_products values (?,?,?,?,sysdate)");
	pstmt3.setString(1,uid);	
	pstmt3.setString(2,lid);
	pstmt3.setString(3,pnames.toString());
	pstmt3.setInt(4,totalPrice);
	pstmt3.executeUpdate();
		System.out.println(" after  stmt 3 ");
	
	PreparedStatement pstmt4=con.prepareStatement("insert into trans values(?,sysdate,?)");
	pstmt4.setString(1,lid);
	pstmt4.setInt(2,totalPrice);
	pstmt4.executeUpdate();	
	
		System.out.println(" after  stmt 4 ");
	con.close();

	//utx.commit();
	 
	 //		System.out.println(" after  the commit ");

	

	}

	catch(Exception e)
	{e.printStackTrace();
		if (utx!=null)

			utx.rollback();
	}
	return uid;

}

public String uniqueID()
{
String strid=null;
try
{
con=DriverManager.getConnection("jdbc:odbc:ejb","scott","tiger");
PreparedStatement stmt=con.prepareStatement("select count(*)+100 from trans");
ResultSet res=stmt.executeQuery();
res.next();
int id=res.getInt(1);
strid="qt"+String.valueOf(id);
con.close();
}
catch(Exception e)
{
e.printStackTrace();
}
return strid;
}



public int getACBalance(String lid)
{
int bal=0;
try
{
System.out.println(" in the get acbal ");
context=getInitialContext();
IAccountHome iah=(IAccountHome)context.lookup("server.IAccountHome");
if ( iah==null)
{
System.out.println("  the home  is nulll  --- in the get acbal ");
}

IAccount ia=(IAccount)iah.findByPrimaryKey(lid);

if ( ia==null)
{
System.out.println("  the remote is nulll  --- in the get acbal ");
}
bal=ia.getCrBal();
System.out.println(" after getCrBal   --- in the get acbal ");
}
catch(Exception ex)
{
ex.printStackTrace();
}
return bal;
}


//  synchronized transaction methods 

public void afterBegin()
{
System.out.println(" after begin of the  transactions ");
}
public void beforeCompletion()
{
System.out.println(" before compeletion of the transaction in the confirm bean");
}

public void afterCompletion(boolean flag)
{
if (flag==true)
System.out.println(" after compeletion  --  the transaction  is successful ");
else
System.out.println(" after compeletion  --  the transaction is not successful ");
}
}