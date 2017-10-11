package main_files;

import components.misc.DatabaseBridge;
import components.student.gui.ClientFrame;
import structures.ErrorCaptureFrame;
import structures.Student;

import javax.swing.*;

/**
 * Created by Sudarshan on 1/28/2017.
 */
public class ClientMain {
    public static ClientFrame FRAME;
    public static Student currentStudent;

    public static void main(String[] args){
        new ErrorCaptureFrame(true, "Student");


        DatabaseBridge.initDatabaseConnection();
        FRAME = new ClientFrame();
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
