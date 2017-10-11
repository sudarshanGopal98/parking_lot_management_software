package main_files;

import components.setup.gui.SetupFrame;
import components.misc.DatabaseBridge;
import structures.ErrorCaptureFrame;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;

/**®
 * Created by Sudarshan on 1/28/2017.
 */
public class SetupMain {
    public static SetupFrame FRAME;

    public static void main(String[] args) throws IOException {
        new ErrorCaptureFrame(true, "Software Setup");

        File dataFolder = new File("data");
        dataFolder.mkdir();

        File ipAddress = new File("data/serverIPAddress.plp");
        BufferedWriter bw = new BufferedWriter(new FileWriter(ipAddress));
        String serverIPAddress = InetAddress.getLocalHost().getHostAddress();
        bw.write(serverIPAddress);
        bw.flush();
        bw.close();

        DatabaseBridge.initDatabaseConnection();

        FRAME = new SetupFrame();
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
