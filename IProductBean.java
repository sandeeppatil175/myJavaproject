package project.server;

import javax.ejb.*;
import java.rmi.*;

public class IProductBean  implements EntityBean
{
EntityContext context;


public String pid,pname,capacity,um,producer;
public int pprice;
public  void setEntityContext (EntityContext con)
{
context=con;
}
public  void unsetEntityContext ()
{
context=null;
}
public String ejbCreate(String pid,String pname,int pprice,String capacity,String UM,String producer) throws CreateException
{
System.out.println(" in ejb create of the iproduct ");
return null;
}
public void ejbRemove()
{
System.out.println(" in the ejb remove of the iproduct ");
}
public void ejbLoad()
{
System.out.println(" in the ejb load of the iproduct ");
}
public void ejbStore()
{
System.out.println(" in the ejb store  of the iproduct ");
}
public void ejbActivate()
{
System.out.println(" in the ejb activate of the iproduct ");
}
public void ejbPassivate()
{
System.out.println(" in the ejb passivate  of the iproduct ");
}

public void ejbPostCreate(String pid,String pname,int pprice,String capacity,String UM,String producer)
{
System.out.println(" in the ejb post create  of the iproduct ");
}

public String getID() 
{
return pid;
}
public String getName()
{
return pname;
}
public int getPrice()
{
return pprice;
}

public String getCapacity()
{
return capacity;
}
public String getUM()
{
return um;
}
public String getProducer()
{
return producer;
}

}

