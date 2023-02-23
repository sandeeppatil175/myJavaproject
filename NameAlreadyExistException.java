package project.server;
public class NameAlreadyExistException extends Exception
{
    public NameAlreadyExistException() { }
    public NameAlreadyExistException(String msg)
    {
          super(msg);
      }
 }