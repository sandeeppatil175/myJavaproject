package project.server;

import javax.ejb.*;
import java.rmi.*;
import java.util.*;

public interface IAccountHome extends  EJBHome
{
public IAccount create(String logid,String login,String passwd,int crbal,int dbbal,Date doj) throws RemoteException,CreateException;
public IAccount findByPrimaryKey(String loginid)  throws RemoteException,FinderException;
//public java.util.Enumeration findNullId() throws RemoteException,FinderException;
}

