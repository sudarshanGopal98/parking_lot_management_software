/*
 * Created by JFormDesigner on Mon Feb 27 22:03:04 CST 2017
 */

package components.student.gui;

import components.misc.DatabaseBridge;
import components.misc.DatabaseVerifier;
import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstraints;
import main_files.ClientMain;
import structures.Colors;
import structures.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.TimerTask;

/**
 * @author - -
 */
public class WaitingPanel extends JPanel implements Runnable{
    String studentID;
    WaitingFrame frame;

    public WaitingPanel(String studentID, WaitingFrame frame) {
        initComponents();
        this.studentID = studentID;
        this.frame = frame;

        logOutTimer.setVisible(false);

        closeButton.setBackground(Colors.BUTTON_COLOR_2);
        closeButton.setVisible(false);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WaitingPanel.this.frame.dispose();
            }
        });

        new Thread(this, "WaitingPanelThread").start();

    }

    @Override
    public void run() {
        while(true){
            try {
                Student student = DatabaseBridge.getStudent(studentID);

                lotColorChosen.setText("You have chosen the \'"+
                        DatabaseVerifier.convertSpotToZone(
                                DatabaseVerifier.getStudentSpotNumber(DatabaseBridge.CONNECTION, studentID))+"\'");

                if(student.getStudentStatus().equals("The Student is Waiting for Approval")){
                    setBackground(Colors.DARK_NAVY);
                    currentStatus.setText("Your request is being processed!");
                    closeButton.setVisible(false);

                }
                else if(student.getStudentStatus().equals("The Student is Approved")){
                    setBackground(Colors.DARK_GREEN);
                    logOutTimer.setVisible(true);
                    currentStatus.setText("Congratulations! You are approved!");
                    closeButton.setVisible(false);

                    java.util.Timer timer = new java.util.Timer("Logout Countdown", false);
                    LogoutTimer timerTask = new LogoutTimer(this, 10);

                    long delay = 1000L;
                    timer.scheduleAtFixedRate(timerTask, delay, delay);
                    break;

                }else{
                    currentStatus.setText("Your Spot Was Denied. Please Click the Button below and Reselect a Spot.");
                    setBackground(Colors.MEDIUM_BACKGROUND_COLOR);
                    lotColorChosen.setVisible(false);
                    closeButton.setVisible(true);
                }

                Thread.sleep(100);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e){

            }


        }
    }

    public JLabel getLogOutTimer(){
        return logOutTimer;
    }

    public void initLogoutProcess(){
        frame.dispose();
        ClientMain.FRAME.dispose();
        ClientMain.FRAME = new ClientFrame();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - - -
        label2 = new JLabel();
        lotColorChosen = new JLabel();
        label6 = new JLabel();
        currentStatus = new JLabel();
        logOutTimer = new JLabel();
        closeButton = new JButton();

        //======== this ========

        /*// JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});
*/
        setLayout(new TableLayout(new double[][] {
            {25, TableLayout.FILL, 25},
            {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
        ((TableLayout)getLayout()).setHGap(5);
        ((TableLayout)getLayout()).setVGap(5);

        //---- label2 ----
        label2.setText("Please Wait While We Approve Your Spot!");
        label2.setHorizontalTextPosition(SwingConstants.CENTER);
        label2.setFont(new Font("Cambria", Font.PLAIN, 20));
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setForeground(Color.white);
        add(label2, new TableLayoutConstraints(1, 1, 1, 1, TableLayoutConstraints.CENTER, TableLayoutConstraints.FULL));

        //---- lotColorChosen ----
        lotColorChosen.setText("Some details on the lot you chose");
        lotColorChosen.setFont(new Font("Cambria", Font.PLAIN, 18));
        lotColorChosen.setHorizontalAlignment(SwingConstants.CENTER);
        lotColorChosen.setForeground(Color.white);
        add(lotColorChosen, new TableLayoutConstraints(1, 2, 1, 2, TableLayoutConstraints.CENTER, TableLayoutConstraints.FULL));

        //---- label6 ----
        label6.setText("Current Status");
        label6.setFont(new Font("Cambria", Font.PLAIN, 18));
        label6.setHorizontalAlignment(SwingConstants.CENTER);
        label6.setForeground(Color.white);
        add(label6, new TableLayoutConstraints(1, 6, 1, 6, TableLayoutConstraints.CENTER, TableLayoutConstraints.FULL));

        //---- currentStatus ----
        currentStatus.setText("status Goes Here");
        currentStatus.setFont(new Font("Cambria", Font.PLAIN, 16));
        currentStatus.setHorizontalAlignment(SwingConstants.CENTER);
        currentStatus.setForeground(Color.white);
        add(currentStatus, new TableLayoutConstraints(1, 7, 1, 7, TableLayoutConstraints.CENTER, TableLayoutConstraints.FULL));

        //---- logOutTimer ----
        logOutTimer.setText("      ");
        logOutTimer.setFont(new Font("Cambria", Font.PLAIN, 16));
        logOutTimer.setHorizontalAlignment(SwingConstants.CENTER);
        logOutTimer.setForeground(Color.white);
        add(logOutTimer, new TableLayoutConstraints(1, 8, 1, 8, TableLayoutConstraints.CENTER, TableLayoutConstraints.FULL));

        //---- closeButton ----
        closeButton.setText("Close and Pick Another Spot");
        closeButton.setFont(new Font("Cambria", Font.PLAIN, 16));
        closeButton.setForeground(Color.white);
        add(closeButton, new TableLayoutConstraints(1, 9, 1, 9, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }



    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - - -
    private JLabel label2;
    private JLabel lotColorChosen;
    private JLabel label6;
    private JLabel currentStatus;
    private JLabel logOutTimer;
    private JButton closeButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

class LogoutTimer  extends TimerTask {
    WaitingPanel waitingPanel;
    double duration;
    boolean completed = false;


    public LogoutTimer(WaitingPanel waitingPanel, double duration){
        this.duration = duration;
        this.waitingPanel = waitingPanel;
    }

    @Override
    public void run() {
        duration--;
        waitingPanel.getLogOutTimer().setText("Logging out in "+ (int)(duration) +" seconds");

        if(duration == 0){
            waitingPanel.initLogoutProcess();
        }
    }

}