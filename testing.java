import java.sql.*;
import java.util.*;
public class testing
{
public static void main(String str[])
{
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con=DriverManager.getConnection("jdbc:odbc:raja","rajendra","sankara");
Statement stmt=con.createStatement();
ResultSet res=stmt.executeQuery("select hiredate from emp where empno=7788");
res.next();
System.out.println(" the firedate is "+res.getDate(1));
}
catch(Exception ed)
{
ed.printStackTrace();
}
}
}

