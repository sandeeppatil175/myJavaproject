package project.server;
import java.rmi.RemoteException;
import javax.ejb.EJBHome;
import javax.ejb.CreateException;
public interface QuotationHome extends EJBHome
{
   public Quotation create() throws RemoteException,CreateException;
}