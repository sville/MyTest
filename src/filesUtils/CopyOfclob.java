package filesUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import difflib.Delta;
import difflib.DiffUtils;

public class CopyOfclob {

    private static String originalUrl = "jdbc:oracle:thin:@devpsdb1.lacitec.on.ca:1521:SATST";
    private static String revisedUrl = "jdbc:oracle:thin:@devpsdb1.lacitec.on.ca:1521:SAUPG";
    private static String username = "sysadm";
    private static String password = "sysadm";
    private static List<String> original = null;
    private static List<String> revised = null;

    public static void main(String[] args) {

	try {
	    // Load the driver. This code is not needed if you are using
	    // JDK 6, because in that environment the driver is loaded
	    // automatically when the application requests a connection.

	    Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (ClassNotFoundException e1) {
	    e1.printStackTrace();
	}

	// //////////////////////////////////////////////////////////////
	try {

	    List<PsPcmTxt> psPcmTxtListOriginal = getPsObjectsCodeEvents(originalUrl);
	    for (PsPcmTxt psPcmTxt : psPcmTxtListOriginal) {
		setOriginal(getPsObjectCodeEventsSource(originalUrl, psPcmTxt));
		System.out.print("originalUrl" + "|"
			+ psPcmTxt.getObjectvalue1() + "|"
			+ psPcmTxt.getObjectvalue2() + "|"
			+ psPcmTxt.getObjectvalue3() + "|");

		System.out.print("original=" + original);
		try {
		    save("originalUrl" + "-" + psPcmTxt.getObjectvalue1() + "-"
			    + psPcmTxt.getObjectvalue2() + "-"
			    + psPcmTxt.getObjectvalue3());
		} catch (Exception e) {
		    System.out.println("Error 4212! " + e);
		}
	    }

	} catch (Exception e) {
	    System.out.println("Error 4! " + e);
	}

	// //////////////////////////////////////////////////////////////
	try {

	    System.out.println();
	    List<PsPcmTxt> psPcmTxtList = getPsObjectsCodeEvents(revisedUrl);
	    for (PsPcmTxt psPcmTxt : psPcmTxtList) {
		setRevised(getPsObjectCodeEventsSource(revisedUrl, psPcmTxt));
		System.out.print("revisedUrl" + "|"
			+ psPcmTxt.getObjectvalue1() + "|"
			+ psPcmTxt.getObjectvalue2() + "|"
			+ psPcmTxt.getObjectvalue3() + "|");
		System.out.print("revised=  " + revised);
		try {
		    save("revisedUrl" + "-" + psPcmTxt.getObjectvalue1() + "-"
			    + psPcmTxt.getObjectvalue2() + "-"
			    + psPcmTxt.getObjectvalue3());
		} catch (Exception e) {
		    System.out.println("Error 4212333! " + e);
		}

	    }

	} catch (Exception e) {
	    System.out.println("Error 4! " + e);
	}

	// //////////////////////////////////////////////////////////////

	// //////////////////////////////////////////////////////////////

	// //////////////////////////////////////////////////////////////
	// diffWrapper(original, revised);

    }

    public static void save(String fileName) throws FileNotFoundException {
	System.out.println("fileName = " + fileName);
	String tmp = ""; // original.toString();
	for (String buff : original) {
	    tmp += buff + "\n";
	}
	PrintWriter pw = new PrintWriter(
		new FileOutputStream(fileName + ".txt"));
	pw.write(tmp);
	pw.close();
    }

    public static void diffWrapper(List<String> original, List<String> revised) {

	try {
	    // ////////////////////////////////////////////////////////////// //
	    // Compute diff. Get the Patch object. Patch is the container for //
	    // computed deltas. System.out.println("");

	    difflib.Patch patch = DiffUtils.diff(original, revised);

	    for (Delta delta : patch.getDeltas()) {
		System.out.println(delta);
	    }
	    // ////////////////////////////////////////////////////////////////

	    original.clear();
	    revised.clear();

	} catch (Exception e) {
	    System.out.println("Error 3! " + e);
	}

    }

    public void tryGetPsObjectEventCode() {
	// //////////////////////////////////////////////////////////////
	try {

	    List<PsPcmTxt> psPcmTxtListOriginal = getPsObjectsCodeEvents(originalUrl);
	    for (PsPcmTxt psPcmTxt : psPcmTxtListOriginal) {
		setOriginal(getPsObjectCodeEventsSource(originalUrl, psPcmTxt));
		System.out.print("originalUrl" + "|"
			+ psPcmTxt.getObjectvalue1() + "|"
			+ psPcmTxt.getObjectvalue2() + "|"
			+ psPcmTxt.getObjectvalue3() + "|");

		System.out.print("original=" + original);
	    }

	} catch (Exception e) {
	    System.out.println("Error 4! " + e);
	}
    }

    private static List<String> getPsObjectCodeEventsSource(String url,
	    PsPcmTxt psPcmTxt) {

	// System.out.println("getSourcePeoplesoftCode(" + url + ")");
	// ////////////////////////////////////////////////////////////////
	// get origine code from database 'SAPRD'
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	List<String> lines = new LinkedList<String>();

	try {
	    conn = DriverManager.getConnection(url, username, password);
	} catch (SQLException e) {
	    System.out.println("Error 11! " + e);
	}
	try {
	    conn.setAutoCommit(false);
	} catch (SQLException e) {
	    System.out.println("Error 12! " + e);
	}

	try {
	    conn.createStatement();
	} catch (SQLException e) {
	    System.out.println("Error 13! " + e);
	}
	String selectSQL = "select OBJECTID1" + ",OBJECTVALUE1" + ",OBJECTID2"
		+ ",OBJECTVALUE2" + ",OBJECTID3" + ",OBJECTVALUE3"
		+ ",OBJECTID4" + ",OBJECTVALUE4" + ",OBJECTID5"
		+ ",OBJECTVALUE5" + ",OBJECTID6" + ",OBJECTVALUE6"
		+ ",OBJECTID7" + ",OBJECTVALUE7" + ",PROGSEQ"
		+ ",HASH_SIGNATURE" + ",PCTEXT " + " From PSPCMTXT P "
		+ " where objectid1=?" + " and objectvalue1=?"
		+ " and objectid2=?" + " and objectvalue2=?"
		+ " and objectid3=?" + " and objectvalue3=?"
		+ " and objectid4=?" + " and objectvalue4=?";
	// + " and objectid5=?" + " and objectvalue5=?"
	// + " and objectid6=?" + " and objectvalue6=?"
	// + " and objectid7=?" + " and objectvalue7=?";

	try {
	    preparedStatement = conn.prepareStatement(selectSQL);
	} catch (SQLException e) {
	    System.out.println("Error 113! " + e);
	}
	try {
	    preparedStatement.setInt(1, psPcmTxt.getObjectid1());
	    preparedStatement.setString(2, psPcmTxt.getObjectvalue1());
	    preparedStatement.setInt(3, psPcmTxt.getObjectid2());
	    preparedStatement.setString(4, psPcmTxt.getObjectvalue2());
	    preparedStatement.setInt(5, psPcmTxt.getObjectid3());
	    preparedStatement.setString(6, psPcmTxt.getObjectvalue3());
	    preparedStatement.setInt(7, psPcmTxt.getObjectid4());
	    preparedStatement.setString(8, psPcmTxt.getObjectvalue4());
	    // preparedStatement.setInt(9, psPcmTxt.getObjectid5());
	    // preparedStatement.setString(10, psPcmTxt.getObjectvalue5());
	    // preparedStatement.setInt(11, psPcmTxt.getObjectid6());
	    // preparedStatement.setString(12, psPcmTxt.getObjectvalue6());
	    // preparedStatement.setInt(13, psPcmTxt.getObjectid7());
	    // preparedStatement.setString(14, psPcmTxt.getObjectvalue7());
	} catch (SQLException e) {
	    System.out.println("Error 114! " + e);
	}

	try {
	    rs = preparedStatement.executeQuery();
	} catch (SQLException e) {
	    System.out.println("Error 115! " + e);
	}

	// try (OutputStreamWriter outWriter = new OutputStreamWriter(
	// new FileOutputStream(dirAndFileName), "utf-8")) {

	try {
	    while (rs.next()) {
		InputStream ip = rs.getAsciiStream("PCTEXT");

		{

		    String line = "";
		    try {
			BufferedReader in = new BufferedReader(
				new InputStreamReader(ip, "UTF-8"));
			while ((line = in.readLine()) != null) {
			    lines.add(line);
			    // System.out.print(line);
			}
		    } catch (IOException e) {
			System.out.println("Error 11555! " + e);
		    }

		}

		// System.out.print("Exiting...\n");
		// ...
	    }
	} catch (SQLException e) {
	    System.out.println("Error 1151! " + e);
	}

	try {
	    conn.close();
	} catch (SQLException e) {
	    System.out.println("Error 1152! " + e);
	}

	// System.out.println("\nlines.size()=" + lines.size());

	return lines;
    }

    private static List<PsPcmTxt> getPsObjectsCodeEvents(String url) {

	// System.out.println("getAllPeoplesoftEventsCode(" + url + ")");

	// ////////////////////////////////////////////////////////////////
	// get origine code from database 'SAPRD'
	Connection conn = null;
	ResultSet rs = null;
	Statement s = null;

	try {
	    conn = DriverManager.getConnection(url, username, password);
	} catch (SQLException e) {
	    System.out.println("Error 11! " + e);
	}
	try {
	    conn.setAutoCommit(false);
	} catch (SQLException e) {
	    System.out.println("Error 12! " + e);
	}

	try {
	    s = conn.createStatement();
	} catch (SQLException e) {
	    System.out.println("Error 13! " + e);
	}

	try {
	    rs = s.executeQuery("select OBJECTID1" + ",OBJECTVALUE1"
		    + ",OBJECTID2" + ",OBJECTVALUE2" + ",OBJECTID3"
		    + ",OBJECTVALUE3" + ",OBJECTID4" + ",OBJECTVALUE4"
		    + ",OBJECTID5" + ",OBJECTVALUE5" + ",OBJECTID6"
		    + ",OBJECTVALUE6" + ",OBJECTID7" + ",OBJECTVALUE7"
		    + ",PROGSEQ" + ",HASH_SIGNATURE"
		    + ",PCTEXT From PSPCMTXT P " + " where objectid1=104 "
		    + " and objectvalue1='HRS_JOB_OPENING_MANAGER' "
		    // + " and objectid2=2"
		    + " and objectvalue2='OBJ_HRS_JO_MISC'"
		    // + " and objectvalue3='FieldChange'"
		    + " and objectvalue3='BUS'"

	    );
	} catch (SQLException e) {
	    System.out.println("Error 14! " + e);
	}
	// + "and objectid3=12	" + "and objectvalue3='FieldChange'");

	List<PsPcmTxt> listPsPcmTxt = new ArrayList<PsPcmTxt>();
	try {
	    while (rs.next()) {
		PsPcmTxt ps = new PsPcmTxt();
		ps.setObjectid1(rs.getInt("OBJECTID1"));
		ps.setObjectvalue1(rs.getString("OBJECTVALUE1"));
		ps.setObjectid2(rs.getInt("OBJECTID2"));
		ps.setObjectvalue2(rs.getString("OBJECTVALUE2"));
		ps.setObjectid3(rs.getInt("OBJECTID3"));
		ps.setObjectvalue3(rs.getString("OBJECTVALUE3"));
		ps.setObjectid4(rs.getInt("OBJECTID4"));
		ps.setObjectvalue4(rs.getString("OBJECTVALUE4"));
		ps.setObjectid5(rs.getInt("OBJECTID5"));
		ps.setObjectvalue5(rs.getString("OBJECTVALUE5"));
		ps.setObjectid6(rs.getInt("OBJECTID6"));
		ps.setObjectvalue6(rs.getString("OBJECTVALUE6"));
		ps.setObjectid7(rs.getInt("OBJECTID7"));
		ps.setObjectvalue7(rs.getString("OBJECTVALUE7"));

		listPsPcmTxt.add(ps);
	    }
	} catch (SQLException e) {
	    System.out.println("Error 15! " + e);
	}
	try {
	    conn.close();
	} catch (SQLException e) {
	    System.out.println("Error 16! " + e);
	}
	return listPsPcmTxt;

    }

    public static List<String> getRevised() {
	return revised;
    }

    public static void setRevised(List<String> revised) {
	CopyOfclob.revised = revised;
    }

    public static List<String> getOriginal() {
	return original;
    }

    public static void setOriginal(List<String> original) {
	CopyOfclob.original = original;
    }
}
/*
 * String sqlString = "select OBJECTID1" + ",OBJECTVALUE1" + ",OBJECTID2" +
 * ",OBJECTVALUE2" + ",OBJECTID3" + ",OBJECTVALUE3" + ",OBJECTID4" +
 * ",OBJECTVALUE4" + ",OBJECTID5" + ",OBJECTVALUE5" + ",OBJECTID6" +
 * ",OBJECTVALUE6" + ",OBJECTID7" + ",OBJECTVALUE7" + ",PROGSEQ" +
 * ",HASH_SIGNATURE" + ",PCTEXT From PSPCMTXT P " + " where objectid1=1 " +
 * "and objectvalue1='DERIVED_SF' " + "and objectid2=2" +
 * "and objectvalue2='CMNT_CATEGORY'";
 */