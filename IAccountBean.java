package project.server;

import javax.ejb.*;
import java.rmi.*;
import java.util.*;
	

public class IAccountBean  implements EntityBean
{
EntityContext context;

public String logid,login,passwd;
public int crbal,dbbal;
public Date doj;

public  String ejbCreate(String logid,String login,String passwd,int crbal,int dbbal,Date doj)throws CreateException
{
this.logid=logid;
this.login=login;
this.passwd=passwd;
this.crbal=crbal;
this.dbbal=dbbal;
this.doj = doj;
System.out.println(" in the ejb create of the Individual account");
return null;
}


public  void setEntityContext (EntityContext con)
{
context=con;
}
public  void unsetEntityContext ()
{
context=null;
}

public void ejbRemove()
{
System.out.println(" in the ejb remove of the iaccount ");
}
public void ejbLoad()
{
System.out.println(" in the ejb load of the iaccount ");
}
public void ejbStore()
{
System.out.println(" in the ejb store  of the iaccount ");
}
public void ejbActivate()
{
System.out.println(" in the ejb activate of the iaccount ");
}
public void ejbPassivate()
{
System.out.println(" in the ejb passivate  of the iaccount ");
}

public void ejbPostCreate(String logid,String login,String passwd,int crbal,int dbbal,Date doj)
{
System.out.println(" in the ejb post create  of the iaccount ");
}

public int getCrBal()
{
return crbal;
}
public int getDbBal() 
{
return dbbal;
}
public Date getDOJ()
{
return doj;
}

}


