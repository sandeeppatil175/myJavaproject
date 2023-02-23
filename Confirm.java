package project.client;

import javax.ejb.*;
import java.rmi.*;
import java.sql.*;
public interface Confirm extends  EJBObject
{
public String doTransaction(String lid,String log,String pas) throws RemoteException;
}