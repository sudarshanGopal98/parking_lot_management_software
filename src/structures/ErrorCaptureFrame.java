package structures;

import javax.swing.*;
import java.io.*;

public class ErrorCaptureFrame extends JFrame {
    JTextArea aTextArea = new JTextArea();
    PrintStream aPrintStream = new PrintStream(new FilteredStream(new ByteArrayOutputStream()));
    String whoIsRunning;

    public ErrorCaptureFrame(boolean displayOnScreen, String whoIsRunning) {
        super("Parking Lot Management Software by Sudarshan Gopalakrishnan | Testing Console | "+whoIsRunning);
        setSize(300, 300);
        add("Center", new JScrollPane(aTextArea));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(displayOnScreen);

        this.whoIsRunning = whoIsRunning;

        System.setOut(aPrintStream); // catches System.out messages
        System.setErr(aPrintStream); // catches error messages

        try {
            String aString = "-----------------------------";
            FileWriter aWriter = new FileWriter("OutputLog - "+whoIsRunning+".plp", true);
            aWriter.write(aString);
            aWriter.write("\n");
            aWriter.close();

        }catch (Exception e){}
    }

    class FilteredStream extends FilterOutputStream {
        public FilteredStream(OutputStream aStream) {
            super(aStream);
        }

        public void write(byte b[]) throws IOException {
            String aString = new String(b);
            aTextArea.append(aString);
        }

        public void write(byte b[], int off, int len) throws IOException {
            File debugOutputFile = new File("OutputLog - "+whoIsRunning+".plp");
            debugOutputFile.createNewFile();


            String aString = new String(b, off, len);
            aTextArea.append(aString);
            FileWriter aWriter = new FileWriter("OutputLog - "+whoIsRunning+".plp", true);
            aWriter.write(aString);
            aWriter.write("\n");
            aWriter.close();
        }
    }
}