package project.server;

import javax.ejb.*;
import java.rmi.*;

public interface IProductHome extends  EJBHome
{
public IProduct create(String pid,String pname,int pprice,String capacity,String UM,String producer) throws RemoteException,CreateException;
public IProduct findByPrimaryKey(String pid)  throws RemoteException,FinderException;
//public java.util.Enumeration findNullId() throws RemoteException,FinderException;
}

