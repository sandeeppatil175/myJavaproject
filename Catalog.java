package project.server;

import javax.ejb.*;
import java.rmi.*;
import java.sql.*;
public interface Catalog extends  EJBObject
{
public ResultSet getProducts() throws RemoteException;
}