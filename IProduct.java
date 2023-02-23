package project.server;
import javax.ejb.*;
import java.rmi.*;

public interface IProduct extends EJBObject
{
public String getID() throws RemoteException;
public String getName() throws RemoteException;
public int getPrice()throws RemoteException;
public String getCapacity()throws RemoteException;
public String getUM()throws RemoteException;
public String getProducer()throws RemoteException;
}