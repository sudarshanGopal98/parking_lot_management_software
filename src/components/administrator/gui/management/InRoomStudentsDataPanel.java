/*
 * Created by JFormDesigner on Thu Mar 24 14:33:04 CDT 2016
 */

package components.administrator.gui.management;

import components.administrator.process.MessageBoxDisplay;
import components.administrator.process.PendingApprovalGUIHandler;
import components.misc.DatabaseBridge;
import components.misc.DatabaseVerifier;
import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstraints;
import main_files.AdminMain;
import structures.Colors;
import structures.Student;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author sud gop
 */
public class InRoomStudentsDataPanel extends JPanel implements Runnable{
    PendingApprovalGUIHandler handler = new PendingApprovalGUIHandler();

    public InRoomStudentsDataPanel() {
        initComponents();
        setBackground(Colors.DARK_BACKGROUND_COLOR);
        topSplit.setBackground(Colors.DARK_BACKGROUND_COLOR);
        panel3.setBackground(Colors.DARK_BACKGROUND_COLOR);

        Thread t = new Thread(this);
        t.start();
        AdminMain.THREAD_MANAGER.addThread("InRoomStudentDataPanel", t);


        handler.addApprovalPack(pending1T, pending1A, pending1D);
        handler.addApprovalPack(pending2T, pending2A, pending2D);
        handler.addApprovalPack(pending3T, pending3A, pending3D);
        handler.addApprovalPack(pending4T, pending4A, pending4D);
        handler.addApprovalPack(pending5T, pending5A, pending5D);

        textField1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                if(textField1.getText().length() == 8){
                    try {
                        if(!DatabaseVerifier.checkStudentExists(DatabaseBridge.CONNECTION, textField1.getText())){
                            MessageBoxDisplay.showStudentNotFoundError(InRoomStudentsDataPanel.this, textField1.getText());

                            int response = JOptionPane.showConfirmDialog(InRoomStudentsDataPanel.this,
                                    "Would you like to add this student to the system?",
                                    "Add new student?",
                                    JOptionPane.YES_NO_OPTION);

                            if(response == JOptionPane.YES_OPTION){
                                new components.administrator.gui.maps.NewStudentDialog();
                            }

                            return;
                        }

                        DatabaseBridge.checkInStudent(textField1.getText());
                        textField1.setText("");
                    }catch (Exception e2){
                        MessageBoxDisplay.showDatabaseErrorMessage(InRoomStudentsDataPanel.this);
                        textField1.setText("");
                        e2.printStackTrace();
                    }
                }
            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(list1.getSelectedValue() != null) {
                    panel1.displayData((Student) list1.getSelectedValue());
                }
            }
        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - - -
        label1 = new JLabel();
        splitPane2 = new JSplitPane();
        topSplit = new JSplitPane();
        label4 = new JLabel();
        panel3 = new JPanel();
        vSpacer2 = new JPanel(null);
        label5 = new JLabel();
        textField1 = new JTextField();
        vSpacer1 = new JPanel(null);
        splitPane3 = new JSplitPane();
        panel2 = new JPanel();
        label2 = new JLabel();
        pending1T = new JLabel();
        pending1A = new JButton();
        pending1D = new JButton();
        pending2T = new JLabel();
        pending2A = new JButton();
        pending2D = new JButton();
        pending3T = new JLabel();
        pending3A = new JButton();
        pending3D = new JButton();
        pending4T = new JLabel();
        pending4A = new JButton();
        pending4D = new JButton();
        pending5T = new JLabel();
        pending5A = new JButton();
        pending5D = new JButton();
        splitPane4 = new JSplitPane();
        scrollPane1 = new JScrollPane();
        list1 = new JList();
        panel1 = new DataDisplaySubpanel();

        //======== this ========
        setBackground(new Color(103, 86, 130));

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new TableLayout(new double[][] {
            {0.05, TableLayout.FILL, 0.05},
            {30, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.FILL, 50}}));
        ((TableLayout)getLayout()).setHGap(5);
        ((TableLayout)getLayout()).setVGap(5);

        //---- label1 ----
        label1.setText("Students In-Room");
        label1.setFont(new Font("Cambria", Font.BOLD, 30));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setBackground(new Color(103, 86, 130));
        label1.setForeground(Color.white);
        add(label1, new TableLayoutConstraints(1, 1, 1, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //======== splitPane2 ========
        {
            splitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
            splitPane2.setDividerLocation(100);
            splitPane2.setDividerSize(0);
            splitPane2.setBorder(null);
            splitPane2.setBackground(new Color(103, 86, 130));

            //======== topSplit ========
            {
                topSplit.setOrientation(JSplitPane.VERTICAL_SPLIT);
                topSplit.setDividerSize(0);
                topSplit.setBackground(new Color(103, 86, 130));
                topSplit.setBorder(null);

                //---- label4 ----
                label4.setText("Check In Students");
                label4.setBackground(new Color(103, 86, 130));
                label4.setFont(new Font("Cambria", Font.BOLD, 22));
                label4.setHorizontalAlignment(SwingConstants.CENTER);
                label4.setForeground(Color.white);
                topSplit.setTopComponent(label4);

                //======== panel3 ========
                {
                    panel3.setBackground(new Color(103, 86, 130));
                    panel3.setLayout(new TableLayout(new double[][] {
                        {159, TableLayout.FILL},
                        {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
                    ((TableLayout)panel3.getLayout()).setHGap(10);
                    ((TableLayout)panel3.getLayout()).setVGap(10);

                    //---- vSpacer2 ----
                    vSpacer2.setBackground(new Color(103, 86, 130));
                    panel3.add(vSpacer2, new TableLayoutConstraints(1, 0, 1, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                    //---- label5 ----
                    label5.setText("Enter Student ID");
                    label5.setFont(new Font("Cambria", Font.PLAIN, 16));
                    label5.setForeground(Color.white);
                    label5.setHorizontalAlignment(SwingConstants.CENTER);
                    label5.setBackground(new Color(103, 86, 130));
                    panel3.add(label5, new TableLayoutConstraints(0, 1, 0, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                    //---- textField1 ----
                    textField1.setFont(textField1.getFont().deriveFont(textField1.getFont().getSize() + 4f));
                    panel3.add(textField1, new TableLayoutConstraints(1, 1, 1, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                    //---- vSpacer1 ----
                    vSpacer1.setBackground(new Color(103, 86, 130));
                    panel3.add(vSpacer1, new TableLayoutConstraints(1, 3, 1, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
                }
                topSplit.setBottomComponent(panel3);
            }
            splitPane2.setTopComponent(topSplit);

            //======== splitPane3 ========
            {
                splitPane3.setDividerLocation(400);
                splitPane3.setLastDividerLocation(450);
                splitPane3.setDividerSize(0);
                splitPane3.setBorder(null);
                splitPane3.setBackground(new Color(103, 86, 130));

                //======== panel2 ========
                {
                    panel2.setBackground(new Color(170, 71, 86));
                    panel2.setLayout(new TableLayout(new double[][] {
                        {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED},
                        {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
                    ((TableLayout)panel2.getLayout()).setHGap(5);
                    ((TableLayout)panel2.getLayout()).setVGap(8);

                    //---- label2 ----
                    label2.setText("Students Waiting For Approval");
                    label2.setHorizontalAlignment(SwingConstants.CENTER);
                    label2.setFont(new Font("Cambria", Font.BOLD, 20));
                    label2.setForeground(Color.white);
                    panel2.add(label2, new TableLayoutConstraints(1, 0, 8, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                    //---- pending1T ----
                    pending1T.setText("text");
                    pending1T.setFont(new Font("Calibri", Font.PLAIN, 18));
                    pending1T.setHorizontalAlignment(SwingConstants.CENTER);
                    pending1T.setForeground(Color.white);
                    panel2.add(pending1T, new TableLayoutConstraints(1, 1, 8, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                    //---- pending1A ----
                    pending1A.setText("Approve");
                    pending1A.setBackground(new Color(60, 50, 86));
                    pending1A.setForeground(Color.white);
                    pending1A.setFont(pending1A.getFont().deriveFont(pending1A.getFont().getStyle() | Font.BOLD, pending1A.getFont().getSize() + 2f));
                    panel2.add(pending1A, new TableLayoutConstraints(2, 2, 3, 2, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                    //---- pending1D ----
                    pending1D.setText("Deny");
                    pending1D.setBackground(new Color(60, 50, 86));
                    pending1D.setForeground(Color.white);
                    pending1D.setFont(pending1D.getFont().deriveFont(pending1D.getFont().getStyle() | Font.BOLD, pending1D.getFont().getSize() + 2f));
                    panel2.add(pending1D, new TableLayoutConstraints(6, 2, 7, 2, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                    //---- pending2T ----
                    pending2T.setText("text");
                    pending2T.setFont(new Font("Calibri", Font.PLAIN, 18));
                    pending2T.setHorizontalAlignment(SwingConstants.CENTER);
                    pending2T.setForeground(Color.white);
                    panel2.add(pending2T, new TableLayoutConstraints(1, 3, 8, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                    //---- pending2A ----
                    pending2A.setText("Approve");
                    pending2A.setBackground(new Color(60, 50, 86));
                    pending2A.setForeground(Color.white);
                    pending2A.setFont(pending2A.getFont().deriveFont(pending2A.getFont().getStyle() | Font.BOLD, pending2A.getFont().getSize() + 2f));
                    panel2.add(pending2A, new TableLayoutConstraints(2, 4, 3, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                    //---- pending2D ----
                    pending2D.setText("Deny");
                    pending2D.setBackground(new Color(60, 50, 86));
                    pending2D.setForeground(Color.white);
                    pending2D.setFont(pending2D.getFont().deriveFont(pending2D.getFont().getStyle() | Font.BOLD, pending2D.getFont().getSize() + 2f));
                    panel2.add(pending2D, new TableLayoutConstraints(6, 4, 7, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                    //---- pending3T ----
                    pending3T.setText("text");
                    pending3T.setFont(new Font("Calibri", Font.PLAIN, 18));
                    pending3T.setHorizontalAlignment(SwingConstants.CENTER);
                    pending3T.setForeground(Color.white);
                    panel2.add(pending3T, new TableLayoutConstraints(1, 5, 8, 5, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                    //---- pending3A ----
                    pending3A.setText("Approve");
                    pending3A.setBackground(new Color(60, 50, 86));
                    pending3A.setForeground(Color.white);
                    pending3A.setFont(pending3A.getFont().deriveFont(pending3A.getFont().getStyle() | Font.BOLD, pending3A.getFont().getSize() + 2f));
                    panel2.add(pending3A, new TableLayoutConstraints(2, 6, 3, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                    //---- pending3D ----
                    pending3D.setText("Deny");
                    pending3D.setBackground(new Color(60, 50, 86));
                    pending3D.setForeground(Color.white);
                    pending3D.setFont(pending3D.getFont().deriveFont(pending3D.getFont().getStyle() | Font.BOLD, pending3D.getFont().getSize() + 2f));
                    panel2.add(pending3D, new TableLayoutConstraints(6, 6, 7, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                    //---- pending4T ----
                    pending4T.setText("text");
                    pending4T.setFont(new Font("Calibri", Font.PLAIN, 18));
                    pending4T.setHorizontalAlignment(SwingConstants.CENTER);
                    pending4T.setForeground(Color.white);
                    panel2.add(pending4T, new TableLayoutConstraints(1, 7, 8, 7, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                    //---- pending4A ----
                    pending4A.setText("Approve");
                    pending4A.setBackground(new Color(60, 50, 86));
                    pending4A.setForeground(Color.white);
                    pending4A.setFont(pending4A.getFont().deriveFont(pending4A.getFont().getStyle() | Font.BOLD, pending4A.getFont().getSize() + 2f));
                    panel2.add(pending4A, new TableLayoutConstraints(2, 8, 3, 8, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                    //---- pending4D ----
                    pending4D.setText("Deny");
                    pending4D.setBackground(new Color(60, 50, 86));
                    pending4D.setForeground(Color.white);
                    pending4D.setFont(pending4D.getFont().deriveFont(pending4D.getFont().getStyle() | Font.BOLD, pending4D.getFont().getSize() + 2f));
                    panel2.add(pending4D, new TableLayoutConstraints(6, 8, 7, 8, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                    //---- pending5T ----
                    pending5T.setText("text");
                    pending5T.setFont(new Font("Calibri", Font.PLAIN, 18));
                    pending5T.setHorizontalAlignment(SwingConstants.CENTER);
                    pending5T.setForeground(Color.white);
                    panel2.add(pending5T, new TableLayoutConstraints(1, 9, 8, 9, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                    //---- pending5A ----
                    pending5A.setText("Approve");
                    pending5A.setBackground(new Color(60, 50, 86));
                    pending5A.setForeground(Color.white);
                    pending5A.setFont(pending5A.getFont().deriveFont(pending5A.getFont().getStyle() | Font.BOLD, pending5A.getFont().getSize() + 2f));
                    panel2.add(pending5A, new TableLayoutConstraints(2, 10, 3, 10, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                    //---- pending5D ----
                    pending5D.setText("Deny");
                    pending5D.setBackground(new Color(60, 50, 86));
                    pending5D.setForeground(Color.white);
                    pending5D.setFont(pending5D.getFont().deriveFont(pending5D.getFont().getStyle() | Font.BOLD, pending5D.getFont().getSize() + 2f));
                    panel2.add(pending5D, new TableLayoutConstraints(6, 10, 7, 10, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
                }
                splitPane3.setLeftComponent(panel2);

                //======== splitPane4 ========
                {
                    splitPane4.setDividerLocation(175);
                    splitPane4.setDividerSize(0);

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setViewportView(list1);
                    }
                    splitPane4.setLeftComponent(scrollPane1);
                    splitPane4.setRightComponent(panel1);
                }
                splitPane3.setRightComponent(splitPane4);
            }
            splitPane2.setBottomComponent(splitPane3);
        }
        add(splitPane2, new TableLayoutConstraints(1, 3, 1, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    @Override
    public void run() {
        while(true){
            ArrayList<Student> students = DatabaseBridge.getStudentInRoom();
            Collections.sort(students);
            list1.setListData(students.toArray());
            list1.repaint();


            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - - -
    private JLabel label1;
    private JSplitPane splitPane2;
    private JSplitPane topSplit;
    private JLabel label4;
    private JPanel panel3;
    private JPanel vSpacer2;
    private JLabel label5;
    private JTextField textField1;
    private JPanel vSpacer1;
    private JSplitPane splitPane3;
    private JPanel panel2;
    private JLabel label2;
    private JLabel pending1T;
    private JButton pending1A;
    private JButton pending1D;
    private JLabel pending2T;
    private JButton pending2A;
    private JButton pending2D;
    private JLabel pending3T;
    private JButton pending3A;
    private JButton pending3D;
    private JLabel pending4T;
    private JButton pending4A;
    private JButton pending4D;
    private JLabel pending5T;
    private JButton pending5A;
    private JButton pending5D;
    private JSplitPane splitPane4;
    private JScrollPane scrollPane1;
    private JList list1;
    private DataDisplaySubpanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
