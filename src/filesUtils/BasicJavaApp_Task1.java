package filesUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

public class BasicJavaApp_Task1 {

    // Helper method for get the file content
    private static List<String> fileToLines(String filename) {
	List<String> lines = new LinkedList<String>();
	String line = "";
	try {
	    BufferedReader in = new BufferedReader(new FileReader(filename));
	    while ((line = in.readLine()) != null) {
		lines.add(line);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return lines;
    }

    public static void main(String[] args) {
	List<String> original = fileToLines("N:\\Diff\\origine.txt");
	List<String> revised = fileToLines("N:\\Diff\\revised.txt");

	// Compute diff. Get the Patch object. Patch is the container for
	// computed deltas.
	Patch patch = DiffUtils.diff(original, revised);
	System.out.println("N:\\Diff\\origine.txt <> N:\\Diff\\revised.txt");
	for (Delta delta : patch.getDeltas()) {
	    System.out.println(delta);
	}
    }
}
