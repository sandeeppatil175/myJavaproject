package project.server;

import javax.ejb.*;
import java.rmi.*;
import java.sql.*;

public class CatalogBean implements SessionBean
{
SessionContext context;
public void ejbCreate() throws CreateException
{
System.out.println(" in the create of CatalogBean ");
}

public ResultSet getProducts()
{
ResultSet res=null;
try {
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con=DriverManager.getConnection("jdbc:odbc:ejb","scott","tiger");

Statement stmt=con.createStatement();
res=stmt.executeQuery("select * from product");
}
catch(Exception e)
{
System.out.println(" an error while executing the business mehtod of catalog bean");
e.printStackTrace();
}
return res;
}

public void setSessionContext(SessionContext ctx) 
{
context=ctx;
}

public void ejbActivate()
{
System.out.println(" in the catalog bean's Activate");
}

public void ejbPassivate()
{
System.out.println(" in the catalog bean's passivate");
}

public void ejbRemove()
{
System.out.println(" in the catalog bean's remove");
}

}
