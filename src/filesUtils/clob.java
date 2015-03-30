package filesUtils;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class clob {

    private static String url = "jdbc:oracle:thin:@devpsdb1.lacitec.on.ca:1521:SAUPG";

    // private static String url =
    // "jdbc:oracle:thin:@devpsdb1.lacitec.on.ca:1521:SATST";

    private static String username = "sysadm";
    private static String password = "sysadm";

    public static void main(String[] args) {
	try {

	    // Load the driver. This code is not needed if you are using
	    // JDK 6, because in that environment the driver is loaded
	    // automatically when the application requests a connection.
	    Class.forName("oracle.jdbc.driver.OracleDriver");

	    Connection conn = DriverManager.getConnection(url, username,
		    password);
	    conn.setAutoCommit(false);

	    OutputStreamWriter source = getSourcePeoplesoftCode(conn);
	    // System.out.print(source);

	} catch (Exception e) {
	    System.out.println("Error! " + e);
	}

    }

    private static OutputStreamWriter getSourcePeoplesoftCode(Connection conn) {

	try {
	    Statement s = conn.createStatement();
	    // --- add a file
	    String dirAndFileName = "G:\\serge\\git\\SergeWorkplace\\MesTest\\PCTEXT.txt";

	    // --- reading the columns
	    ResultSet rs = s.executeQuery("select PCTEXT From PSPCMTXT P "
		    + "where objectid1=1 " + "and objectvalue1='DERIVED_SF' "
		    + "and objectid2=2	" + "and objectvalue2='CMNT_CATEGORY'	"
		    + "and objectid3=12	" + "and objectvalue3='FieldChange'");

	    try (OutputStreamWriter outWriter = new OutputStreamWriter(
		    new FileOutputStream(dirAndFileName), "utf-8")) {

		while (rs.next()) {
		    java.io.InputStream ip = rs.getAsciiStream(1);
		    int c = ip.read();
		    while (c > 0) {
			System.out.print((char) c);
			outWriter.write((char) c);
			// out.write(c);
			c = ip.read();
		    }
		    System.out.print("Exiting...\n");
		    // ...
		}

		return outWriter;

	    } catch (Exception e) {
		System.out.println("Error! " + e);
	    }

	} catch (Exception e) {
	    System.out.println("Error! " + e);
	}
	return null;
    }
}