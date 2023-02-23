package project.server;
import javax.ejb.*;
import java.rmi.*;
import java.util.*;

public interface IAccount extends EJBObject
{
public int getCrBal() throws RemoteException;
public int getDbBal() throws RemoteException;
public Date getDOJ()throws RemoteException;

}