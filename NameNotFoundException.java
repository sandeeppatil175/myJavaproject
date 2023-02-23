package project.server;
public class NameNotFoundException extends Exception
{
   public NameNotFoundException() { }
   public NameNotFoundException(String msg) 
   {
        super(msg);
     }
} 