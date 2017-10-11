package components.administrator.process;

import components.misc.DatabaseBridge;
import structures.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sudarshan on 3/20/2017.
 */
public class DataBackupProcess implements Runnable{
    @Override
    public void run() {
        final File backupFolder = new File("backups"+File.separatorChar+
                new SimpleDateFormat("MMM-dd-yyyy").format(new Date()));
        backupFolder.mkdirs();

        Timer timer = new Timer(300000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
                    String fileName = "Data Backup at Time "+ dateFormat.format(new Date())+".csv";
                    File destination = new File(backupFolder.getPath()+File.separatorChar+fileName);
                    boolean backupCreated = destination.createNewFile();

                    if(backupCreated) {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(destination));

                        bw.write(Student.STUDENT_EXPORT_HEADER);
                        bw.newLine();
                        bw.flush();

                        ArrayList<Student> students = DatabaseBridge.getAllStudents();
                        for (Student student : students) {
                            bw.write(student.getExportString());
                            bw.newLine();
                            bw.flush();
                        }

                        bw.close();
                    }
                }catch (IOException | SQLException e){e.printStackTrace();}
            }
        });
        timer.setRepeats(true);
        timer.start();
    }
}
