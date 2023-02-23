package project.client;

import javax.ejb.*;
import java.rmi.*;

public interface ConfirmHome extends EJBHome
{
public Confirm create() throws RemoteException,CreateException;
}