package project.server;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.util.*;
import javax.ejb.CreateException;
public class QuotationBean implements SessionBean
{ 
  public Hashtable products;
    private  SessionContext context;
     public void addProduct(String spid,int price,String pname) throws NameAlreadyExistException
	      {	  Object pid=(Object)spid;
			     String nampri;
		       nampri=pname+":"+String.valueOf(price);
		 System.out.println(" in the addition of the product - "+pid +" having"+nampri);
         if(products.containsKey(pid))
			{
             throw new NameAlreadyExistException("produt you want to buy is Already in quotation ");
            }
         else
             {
			 products.put(pid,(Object)nampri);
			 System.out.println(" added the  item to the quotation < -- > no of products are"+products.size());
             }
    }
     public void removeProduct(String pid) throws NameNotFoundException
     {
         Object flag=null;
         flag=products.remove((Object)pid);
         if(flag==null)
         {
             throw new NameNotFoundException("Name of the product Not Found");
           }
       }
     public Hashtable getAllProducts()
      {
            return products;
       }
      public void ejbCreate() throws CreateException
      {
          products=new Hashtable();
       }
      public void setSessionContext(SessionContext sc)
      {
            context=sc;
        }
      public void ejbActivate()
      { 
          System.out.println("in ejb activate of the quotation.....");
        }
      public void ejbPassivate()
       {
           System.out.println("in ejb passivate of the quotation....");
         }
       public void ejbRemove()
        {
           System.out.println("in ejb remove of the quotation.....");
          }
    } 	 // class 