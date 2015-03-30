/**
 * 2015 sville
 * PipedInputStreamDemo.java 2015-03-18
 *
 * filesUtils
 */
package filesUtils;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedInputStreamDemo {

    public static void main(String[] args) {

	// create a new Piped input and Output Stream
	PipedOutputStream out = new PipedOutputStream();
	PipedInputStream in = new PipedInputStream();

	try {
	    // connect input and output
	    in.connect(out);

	    // write something
	    out.write(70);
	    out.write(71);

	    // read what we wrote
	    for (int i = 0; i < 2; i++) {
		System.out.println("i=" + i + " " + (char) in.read());
	    }

	} catch (IOException ex) {
	    ex.printStackTrace();
	}

    }
}
