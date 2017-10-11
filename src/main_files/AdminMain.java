package main_files;

import components.administrator.gui.AdminFrame;
import components.administrator.process.DataBackupProcess;
import components.administrator.process.ThreadManager;
import components.misc.DatabaseBridge;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Sudarshan on 1/28/2017.
 */
public class AdminMain {
    public static final ThreadManager THREAD_MANAGER = new ThreadManager();
    public static JFrame FRAME;
    public static final ArrayList<String> zones = new ArrayList<>();

    public static void main(String[] args){
//        new ErrorCaptureFrame(true, "Administrator");

        DatabaseBridge.initDatabaseConnection();

        Thread t = new Thread(new DataBackupProcess(), "Data Backup Process");
        t.start();
        THREAD_MANAGER.addThread("Data Backup Thread", t);

        FRAME = new AdminFrame();
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
