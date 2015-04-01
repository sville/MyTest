package filesUtils;

/*
 Copyright: (C) 2002 Brigham Young University

 This program is free software; you can redistribute it and/or modify it
 under the terms of the Open Source Software License Agreement for JHDL.

 You should have received a copy of the Open Source Software License for
 JHDL along with this program.  If you have not, download the License
 Agreement at http://www.jhdl.org/license.html or write to:

 Brad Hutchings
 459 Clyde Bldg
 Brigham Young University
 Provo, UT 84602


 */

/** This is a simple bare-bones class that will open up two files and
 * compare them, line-by-line.  The static methods will return the
 * number of lines found to be different. For now, the diffing is not
 * very smart (like the diff utility in Linux or Unix).
 * @author Anthony Slade <aslade@ee.byu.edu> */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FileDiff {

    public static final String USAGE_STRING = "FileDiff file1 file2";
    public static final int ERROR_CODE_INT = -1;

    /**
     * This method can be used to pass the names of two files to be diffed. If
     * there is a difference, the number of different lines is printed to
     * standard error.
     * 
     * @param args
     *            The arguments must consist of exactly two strings,
     *            corresponding to two filenames (including paths, if needed).
     */
    public static void main(String[] args) {
	if (2 != args.length) {
	    System.err.println("USAGE: " + USAGE_STRING);
	    // This could call System.exit( non-zero-value );
	    // but I chose not to do that, in case this method is ever
	    // called while running Ant. (That would potentially kill Ant.)
	    return;
	}
	int numLinesDiff = diff(args[0], args[1]);
	if (0 > numLinesDiff) {
	    String errorMessage = getLastErrorMessage();
	    if (null == errorMessage) {
		errorMessage = "FileDiff ERROR: An error occured while diffing the files "
			+ args[0] + " and " + args[1];
	    }
	    System.err.println(errorMessage);
	} else if (0 < numLinesDiff) {
	    System.err.println("Found " + numLinesDiff + " different line"
		    + (numLinesDiff > 1 ? "s" : ""));
	} else {
	    ; // All is well.
	}
	return; // That's it!
    }

    /**
     * Return last error message
     * 
     * @return a message that explains the last error encountered, or null if no
     *         error has occurred in diffing.
     */
    public static String getLastErrorMessage() {
	return lastErrorMessage;
    }

    /**
     * Return the number of different lines in fileA and fileB
     * 
     * @param fileA
     *            File to compare to fileB
     * @param fileB
     *            File to compare to fileA
     * @return Zero if files identical, {@link #ERROR_CODE_INT} if an error
     *         occurred, or the number of different lines if the files differ
     */
    public static int diff(String fileA, String fileB) {
	return diff(new File(fileA.trim()), new File(fileB.trim()));
    }

    /**
     * Return the number of different lines in fileA and fileB
     * 
     * @param fileA
     *            File to compare to fileB
     * @param fileB
     *            File to compare to fileA
     * @return Zero if files identical, {@link #ERROR_CODE_INT} if an error
     *         occurred, or the number of different lines if the files differ
     */
    public static int diff(File fileA, File fileB) {
	try {
	    return diff((FileReader) new FileReader(fileA.getAbsoluteFile()),
		    (FileReader) new FileReader(fileB.getAbsoluteFile()));
	} catch (FileNotFoundException fnfe) {
	    lastErrorMessage = "FileDiff ERROR: " + fnfe.getMessage();
	    return ERROR_CODE_INT;
	}
    }

    /**
     * Return the number of different lines in fileA and fileB
     * 
     * @param fileA
     *            File to compare to fileB
     * @param fileB
     *            File to compare to fileA
     * @return Zero if files identical, {@link #ERROR_CODE_INT} if an error
     *         occurred, or the number of different lines if the files differ
     */
    public static int diff(FileReader fileA, FileReader fileB) {
	return diff(new BufferedReader(fileA), new BufferedReader(fileB));
    }

    /**
     * Return the number of different lines in fileA and fileB
     * 
     * @param fileA
     *            File to compare to fileB
     * @param fileB
     *            File to compare to fileA
     * @return Zero if files identical, {@link #ERROR_CODE_INT} if an error
     *         occurred, or the number of different lines if the files differ
     */
    public static int diff(Reader fileA, Reader fileB) {
	return diff(new BufferedReader(fileA), new BufferedReader(fileB));
    }

    /**
     * Return the number of different lines in fileA and fileB
     * 
     * @param fileA
     *            File to compare to fileB
     * @param fileB
     *            File to compare to fileA
     * @return Zero if files identical, {@link #ERROR_CODE_INT} if an error
     *         occurred, or the number of different lines if the files differ
     */
    public static int diff(BufferedReader fileA, BufferedReader fileB) {
	int numLinesDiff = 0;
	try {
	    while (fileA.ready() && fileB.ready()) {
		if (!fileA.readLine().equals(fileB.readLine())) {
		    ++numLinesDiff;
		}
	    }
	    while (fileA.ready()) {
		fileA.readLine();
		++numLinesDiff;
	    }
	    while (fileB.ready()) {
		fileB.readLine();
		++numLinesDiff;
	    }
	} catch (IOException ioe) {
	    lastErrorMessage = "FileDiff ERROR: " + ioe.getMessage();
	    return ERROR_CODE_INT;
	}
	return numLinesDiff;
    }

    private static String lastErrorMessage = null;
}
