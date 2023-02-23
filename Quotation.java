package project.server;
import java.rmi.RemoteException;
import javax.ejb.EJBObject;
import java.util.*;
public interface Quotation extends EJBObject
{
    public void addProduct(String pid,int price,String pname) throws NameAlreadyExistException,RemoteException;
    public void removeProduct(String pid) throws RemoteException,NameNotFoundException;
    public Hashtable getAllProducts() throws RemoteException;
}
