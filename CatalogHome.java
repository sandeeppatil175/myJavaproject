package project.server;

import javax.ejb.*;
import java.rmi.*;

public interface CatalogHome extends EJBHome
{
public Catalog create() throws RemoteException,CreateException;
}