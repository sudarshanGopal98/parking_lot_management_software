package components.setup.gui;

import components.misc.CircularProgressBar;
import components.setup.process.StudentDataBooter;
import org.apache.commons.io.FileUtils;
import structures.Colors;
import structures.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Sudarshan on 2/23/2017.
 */
public class FinalSetup extends JPanel {
    JButton screenButton;
    CircularProgressBarGUI circularProgressBarGUI;

    public FinalSetup() {
        Toolkit tk = this.getToolkit();
        setLocation(0, 0);
        setSize(tk.getScreenSize());
        setLayout(null);
        setBackground(Colors.MEDIUM_BACKGROUND_COLOR);

        final JLabel message = new JLabel("Please Click on Initialize Final Setup to Finish");
        message.setFont(new Font("Cambria", Font.BOLD, 40));
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setBounds(0,80,getWidth(),50);
        message.setForeground(Color.white);

        screenButton = new JButton("Initialize Final Setup (This Process May Take 5 - 15 Minutes)");
        screenButton.setBackground(Colors.BUTTON_COLOR_1);
        screenButton.setForeground(Color.white);
        screenButton.setBounds(getWidth()/2 - 300, 150, 600, 40);
        screenButton.setFont(new Font("Cambria", Font.BOLD, 20));
        screenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    final JProgressBar progress = new JProgressBar();
                    // use JProgressBar#setUI(...) method
                    progress.setUI(new CircularProgressBar());
                    progress.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
                    progress.setStringPainted(true);
                    progress.setFont(progress.getFont().deriveFont(24f));
                    progress.setForeground(Color.ORANGE);

                    new Timer(50, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int iv = Math.min(100, progress.getValue() + 1);
                            progress.setValue(iv);
                        }
                    });

                    JFrame f = new JFrame();
                    f.setUndecorated(true);
                    f.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
                    f.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
                    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    circularProgressBarGUI = new CircularProgressBarGUI(f);
                    f.getContentPane().add(circularProgressBarGUI.makeUI());
                    f.getContentPane().setBackground(Colors.DARK_BACKGROUND_COLOR);
                    f.setSize(320, 240);
                    f.setLocation(getWidth()/2 - 160, screenButton.getY()+ 200);
                    f.setResizable(false);
                    f.setType(javax.swing.JFrame.Type.UTILITY);
                    f.setFocusable(true);
                    f.setVisible(true);


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //  This is as soon as transfer process is started
                                screenButton.setEnabled(false);
                                message.setText("Please Wait As We Load All Data To Server");
                                StudentDataBooter.loadData();


                                // This leads to the folder creation for admin and student
                                makeAdminDirectory();
                                makeStudentDirectory();


                                // This is as soon as transfer is ended
                                screenButton.removeActionListener(screenButton.getActionListeners()[0]);
                                screenButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        System.exit(0);
                                    }
                                });
                                circularProgressBarGUI.getProgress().setValue(100);
                                circularProgressBarGUI.getProgressBarTimer().stop();
                                message.setText("Congratulations! The Setup Is Complete.");
                                screenButton.setText("Close Setup");
                                screenButton.setEnabled(true);

                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    }, "StudentDataBooter Thread").start();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        screenButton.setBackground(Colors.BUTTON_COLOR_1);

        add(message);
        add(screenButton);
    }

    private void makeAdminDirectory(){
        File adminDirectory = new File(System.getProperty("user.home")+"/Desktop/Parking Lot Management Software - Admin");
        Log.print(adminDirectory.getAbsolutePath());
        Log.print("Admin dir made:"+adminDirectory.mkdir());

        //copy exe files
        File source = new File("AdminExecutable");
        File dest = adminDirectory;
        dest.mkdir();
        try {
            FileUtils.copyDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        source = new File("images");
        dest = new File(adminDirectory.getPath()+"/images");
        Log.print("Admin Images Dir Made:"+dest.mkdir());
        try {
            FileUtils.copyDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        source = new File("data");
        dest = new File(adminDirectory.getPath()+"/data");
        Log.print("Admin data Dir Made:"+dest.mkdir());
        try {
            FileUtils.copyDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeStudentDirectory(){
        File studentDirectory = new File(System.getProperty("user.home")+"/Desktop/Parking Lot Management Software - Student");
        Log.print("Student Directory Made:"+studentDirectory.mkdir());

        //copy exe files
        File source = new File("StudentExecutable");
        File dest = studentDirectory;
        dest.mkdir();
        try {
            FileUtils.copyDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        source = new File("images");
        dest = new File(studentDirectory.getPath()+"/images");
        dest.mkdir();
        try {
            FileUtils.copyDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        source = new File("data");
        dest = new File(studentDirectory.getPath()+"/data");
        dest.mkdir();
        try {
            FileUtils.copyDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
