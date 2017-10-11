/*
 * Created by JFormDesigner on Wed Feb 01 07:59:44 CST 2017
 */

package components.administrator.gui.maps;

import components.administrator.gui.management.DataDisplaySubpanel;
import components.administrator.process.MessageBoxDisplay;
import components.misc.DatabaseBridge;
import components.misc.DatabaseVerifier;
import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstraints;
import main_files.AdminMain;
import org.jdesktop.swingx.HorizontalLayout;
import structures.Colors;
import structures.Student;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Sudarshan Gopalakrishnan
 */
public class MapZone extends Panel implements Runnable{
    String zoneName = "";

    public MapZone(String zoneName, int width, int height) {
        this.zoneName = zoneName;
        initComponents();
        setBackground(Colors.MEDIUM_BACKGROUND_COLOR);
        setSize(width, height);

        panel1.setSize(3*getWidth()/5 ,getHeight());
        panel1.setBackground(Colors.DARK_BACKGROUND_COLOR);
        scrollPane1.setSize(getWidth()/5 ,getHeight());
        scrollPane1.setPreferredSize(new Dimension(getWidth()/5 ,getHeight()));
        list1.setSize(getWidth()/5 ,getHeight());
        list1.setPreferredSize(new Dimension(getWidth()/5 ,getHeight()));




        final DataDisplaySubpanel dataDisplaySubpanel = new DataDisplaySubpanel();
        dataDisplaySubpanel.setBackground(Colors.MEDIUM_BACKGROUND_COLOR);
        dataDisplaySubpanel.setPreferredWidth(3*getWidth()/5);
        dataDisplaySubpanel.setSize(3*getWidth()/5, getHeight());
        dataDisplaySubpanel.setBorder(null);
        add(dataDisplaySubpanel);

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(list1.getSelectedValue() != null)
                    dataDisplaySubpanel.displayData((Student)list1.getSelectedValue());
            }
        });

        list1.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Student) {
                    Student s = (Student)(value);
                    if(s.getStudentStatus().equals("The Student is Approved")){
                        setBackground(Colors.GREEN);
                    }else if(s.getStudentStatus().equals("The Student is Waiting for Approval"))
                        setBackground(Colors.YELLOW);

                } else {
                    setText("");
                }
                return c;
            }

        });

        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentID = JOptionPane.showInputDialog(AdminMain.FRAME, "Please Enter Student ID \nof the Student To Be Added:", "Add Student To Zone", JOptionPane.QUESTION_MESSAGE);
                if(studentID == null    ||  studentID.length() != 8)
                    return;

                try {
                    if(!DatabaseVerifier.checkStudentExists(DatabaseBridge.CONNECTION, studentID)){
                        MessageBoxDisplay.showStudentNotFoundError(MapZone.this, studentID);
                        return;
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                int previousSpotNumber = DatabaseVerifier.getStudentSpotNumber(DatabaseBridge.CONNECTION, studentID);
                if(previousSpotNumber > 0){
                    try {
                        JOptionPane.showMessageDialog(MapZone.this, "The Student with Student ID "+studentID+" has already " +
                                "chosen a spot, which is located in Zone "+ DatabaseVerifier.convertSpotToZone(previousSpotNumber),"Student Not Added",JOptionPane.ERROR_MESSAGE);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    return;
                }

                if(DatabaseBridge.writeParkingSpotRequest(studentID, MapZone.this.zoneName)){
                    JOptionPane.showMessageDialog(MapZone.this, "The Student with Student ID "+studentID+" has " +
                            "been added to Zone "+MapZone.this.zoneName+".","Student Successfully Added",JOptionPane.INFORMATION_MESSAGE);

                }else JOptionPane.showMessageDialog(MapZone.this, "The Student with Student ID "+studentID+" was " +
                        "not added.","Student Not Added",JOptionPane.ERROR_MESSAGE);
            }
        });
        addStudent.setBackground(Colors.BUTTON_COLOR_2);

        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapZone.this.setVisible(false);
            }
        });
        goBack.setBackground(Colors.BUTTON_COLOR_2);

        addNewStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewStudentDialog();
            }
        });
        addNewStudent.setBackground(Colors.BUTTON_COLOR_2);

        Thread t = new Thread(this, "MapZone"+zoneName);
        t.start();
        AdminMain.THREAD_MANAGER.addThread("MapZone"+zoneName,t);
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - - -
        panel1 = new JPanel();
        vSpacer1 = new JPanel(null);
        goBack = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        spotsAlloted = new JLabel();
        label5 = new JLabel();
        spotsBlocked = new JLabel();
        label3 = new JLabel();
        spotsAvailable = new JLabel();
        label4 = new JLabel();
        totalSpots = new JLabel();
        label8 = new JLabel();
        percentFull = new JLabel();
        label9 = new JLabel();
        percentAvail = new JLabel();
        label12 = new JLabel();
        addStudent = new JButton();
        addNewStudent = new JButton();
        scrollPane1 = new JScrollPane();
        list1 = new JList();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new HorizontalLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new TableLayout(new double[][] {
                {12, 183, 227, TableLayout.PREFERRED},
                {TableLayout.PREFERRED, 23, 26, TableLayout.PREFERRED, 28, 28, 28, 28, 35, 28, 28, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
            ((TableLayout)panel1.getLayout()).setHGap(5);
            ((TableLayout)panel1.getLayout()).setVGap(5);
            panel1.add(vSpacer1, new TableLayoutConstraints(1, 0, 1, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- goBack ----
            goBack.setText("Go Back");
            goBack.setBackground(new Color(61, 18, 86));
            goBack.setForeground(Color.white);
            goBack.setFont(new Font("Cambria", Font.PLAIN, 14));
            panel1.add(goBack, new TableLayoutConstraints(1, 1, 2, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label1 ----
            label1.setText("Key Statistics");
            label1.setFont(new Font("Cambria", Font.BOLD, 18));
            label1.setForeground(Color.white);
            panel1.add(label1, new TableLayoutConstraints(1, 3, 1, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label2 ----
            label2.setText("   Spots Alloted");
            label2.setFont(new Font("Cambria", Font.PLAIN, 14));
            label2.setForeground(Color.white);
            panel1.add(label2, new TableLayoutConstraints(1, 4, 1, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- spotsAlloted ----
            spotsAlloted.setText("Please Wait...");
            spotsAlloted.setHorizontalAlignment(SwingConstants.CENTER);
            spotsAlloted.setFont(new Font("Cambria", Font.PLAIN, 14));
            spotsAlloted.setForeground(Color.white);
            panel1.add(spotsAlloted, new TableLayoutConstraints(2, 4, 2, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label5 ----
            label5.setText("   Spots Blocked");
            label5.setFont(new Font("Cambria", Font.PLAIN, 14));
            label5.setForeground(Color.white);
            panel1.add(label5, new TableLayoutConstraints(1, 5, 1, 5, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- spotsBlocked ----
            spotsBlocked.setText("Please Wait...");
            spotsBlocked.setHorizontalAlignment(SwingConstants.CENTER);
            spotsBlocked.setFont(new Font("Cambria", Font.PLAIN, 14));
            spotsBlocked.setForeground(Color.white);
            panel1.add(spotsBlocked, new TableLayoutConstraints(2, 5, 2, 5, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label3 ----
            label3.setText("   Spots Available");
            label3.setFont(new Font("Cambria", Font.PLAIN, 14));
            label3.setForeground(Color.white);
            panel1.add(label3, new TableLayoutConstraints(1, 6, 1, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- spotsAvailable ----
            spotsAvailable.setText("Please Wait...");
            spotsAvailable.setHorizontalAlignment(SwingConstants.CENTER);
            spotsAvailable.setFont(new Font("Cambria", Font.PLAIN, 14));
            spotsAvailable.setForeground(Color.white);
            panel1.add(spotsAvailable, new TableLayoutConstraints(2, 6, 2, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label4 ----
            label4.setText("   Total Spots in Zone");
            label4.setFont(new Font("Cambria", Font.PLAIN, 14));
            label4.setForeground(Color.white);
            panel1.add(label4, new TableLayoutConstraints(1, 7, 1, 7, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- totalSpots ----
            totalSpots.setText("Please Wait...");
            totalSpots.setHorizontalAlignment(SwingConstants.CENTER);
            totalSpots.setFont(new Font("Cambria", Font.PLAIN, 14));
            totalSpots.setForeground(Color.white);
            panel1.add(totalSpots, new TableLayoutConstraints(2, 7, 2, 7, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label8 ----
            label8.setText("   Percentage of Zone Full");
            label8.setFont(new Font("Cambria", Font.PLAIN, 14));
            label8.setForeground(Color.white);
            panel1.add(label8, new TableLayoutConstraints(1, 9, 1, 9, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- percentFull ----
            percentFull.setText("Please Wait...");
            percentFull.setHorizontalAlignment(SwingConstants.CENTER);
            percentFull.setFont(new Font("Cambria", Font.PLAIN, 14));
            percentFull.setForeground(Color.white);
            panel1.add(percentFull, new TableLayoutConstraints(2, 9, 2, 9, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label9 ----
            label9.setText("   Percentage of Zone Avail.");
            label9.setFont(new Font("Cambria", Font.PLAIN, 14));
            label9.setForeground(Color.white);
            panel1.add(label9, new TableLayoutConstraints(1, 10, 1, 10, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- percentAvail ----
            percentAvail.setText("Please Wait...");
            percentAvail.setHorizontalAlignment(SwingConstants.CENTER);
            percentAvail.setFont(new Font("Cambria", Font.PLAIN, 14));
            percentAvail.setForeground(Color.white);
            panel1.add(percentAvail, new TableLayoutConstraints(2, 10, 2, 10, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label12 ----
            label12.setText("Tools");
            label12.setFont(new Font("Cambria", Font.BOLD, 18));
            label12.setForeground(Color.white);
            panel1.add(label12, new TableLayoutConstraints(1, 12, 1, 12, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- addStudent ----
            addStudent.setText("Add Student to Zone");
            addStudent.setBackground(new Color(44, 0, 70));
            addStudent.setForeground(Color.white);
            addStudent.setFont(new Font("Cambria", Font.PLAIN, 14));
            panel1.add(addStudent, new TableLayoutConstraints(1, 13, 2, 13, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- addNewStudent ----
            addNewStudent.setText("Add NEW Student");
            addNewStudent.setBackground(new Color(44, 0, 70));
            addNewStudent.setForeground(Color.white);
            addNewStudent.setFont(new Font("Cambria", Font.PLAIN, 14));
            panel1.add(addNewStudent, new TableLayoutConstraints(1, 14, 2, 14, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        }
        add(panel1);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(list1);
        }
        add(scrollPane1);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void setBorder(Border border){}
    private Border getBorder(){
        return new JPanel().getBorder();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - - -
    private JPanel panel1;
    private JPanel vSpacer1;
    private JButton goBack;
    private JLabel label1;
    private JLabel label2;
    private JLabel spotsAlloted;
    private JLabel label5;
    private JLabel spotsBlocked;
    private JLabel label3;
    private JLabel spotsAvailable;
    private JLabel label4;
    private JLabel totalSpots;
    private JLabel label8;
    private JLabel percentFull;
    private JLabel label9;
    private JLabel percentAvail;
    private JLabel label12;
    private JButton addStudent;
    private JButton addNewStudent;
    private JScrollPane scrollPane1;
    private JList list1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void run() {
        while(true){
            ArrayList<Student> students = DatabaseBridge.getStudentsInZone(zoneName);
            list1.setListData(students.toArray());
            list1.repaint();
            try {
                double[] parkingZoneStats = DatabaseBridge.getMapZoneStatistics(zoneName);
                spotsAlloted.setText((int)(parkingZoneStats[0])+"");
                spotsBlocked.setText((int)(parkingZoneStats[1])+"");
                spotsAvailable.setText((int)(parkingZoneStats[2])+"");
                totalSpots.setText((int)(parkingZoneStats[3])+"");

                percentFull.setText((double)(Math.round(parkingZoneStats[4]*100))/100+"");
                percentAvail.setText((double)(Math.round(parkingZoneStats[5]*100))/100+"");

            } catch (SQLException e) {
                e.printStackTrace();
            }


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
