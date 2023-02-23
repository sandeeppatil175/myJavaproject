<html>
<body>
<%@  page import=" java.io.*, javax.naming.*, javax.ejb.*,java.util.*,
							pack7.client.*,pack7.server.*" %>
<% ! int multiplier ; %>
<% ! CalcHome home ; %>
<% ! String socSec; %>
<% !  double bonus; %>
<% ! 


public Context getInitialContext() throws Exception {
    Hashtable h = new Hashtable();
    h.put(Context.INITIAL_CONTEXT_FACTORY,
        "weblogic.jndi.WLInitialContextFactory");
    h.put(Context.PROVIDER_URL, "t3://localhost:7001");
//      h.put(Context.SECURITY_PRINCIPAL, "system");//user
//      h.put(Context.SECURITY_CREDENTIALS, "peersjava"); //password
    return new InitialContext(h);
}
      %>

      <% try
		{ Context initial=getInitialContext();

		Object ref=initial.lookup("server.CalcHome");
		home=(CalcHome)ref;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
	%>

	<% multiplier=request.getParameter("MULTIPLIER");
	socSec=request.getParameter("SOCSEC");
	bonus=500;

	try
	{
	Calc theCalculator=home.create();
	Bonus b1=theCalculator.calcBonus(multiplier,bonus,socsec);

	Bonus record=theCalculator.getRecord(socsec);
	out.println("  <h3> the social sercurity is </h3 > "+record.getSocSec()+"<br>");
		out.println("  <h3> the total bonus is  </h3 > "+record.getBonus()+"<br>");
	}

	catch(Exception e)
	{
	e.printStackTrace();
	}
%>
</body>
</html> 