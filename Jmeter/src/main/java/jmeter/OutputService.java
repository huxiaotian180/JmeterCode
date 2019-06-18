package jmeter;

import java.io.File;
import java.io.PrintWriter;

public class OutputService {
    public static void output(String filename, String a, String b) throws Exception {
        PrintWriter out = new PrintWriter(new File(filename));
        out.write(a + ":" + b);
        out.close();
    }
}