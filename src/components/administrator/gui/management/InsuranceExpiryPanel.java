/*
 * Created by JFormDesigner on Sat Feb 04 11:11:17 CST 2017
 */

package components.administrator.gui.management;

import components.misc.DatabaseBridge;
import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstraints;
import main_files.AdminMain;
import structures.Colors;
import structures.Student;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Sudarshan Gopalakrishnan
 */
public class InsuranceExpiryPanel extends JPanel implements Runnable{
    public InsuranceExpiryPanel() {
        initComponents();
        setBackground(Colors.DARK_BACKGROUND_COLOR);

        expiredList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(expiredList.getSelectedValue() != null)
                    panel1.displayData((Student)expiredList.getSelectedValue());
            }
        });

        weekList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(weekList.getSelectedValue() != null)
                    panel1.displayData((Student)weekList.getSelectedValue());
            }
        });

        monthList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(monthList.getSelectedValue() != null)
                    panel1.displayData((Student)monthList.getSelectedValue());
            }
        });

        Thread t = new Thread(this, "InsuranceExpiryPanel");
        t.start();
        AdminMain.THREAD_MANAGER.addThread("InsuranceExpiryPanel", t);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - - -
        label4 = new JLabel();
        panel1 = new DataDisplaySubpanel();
        label1 = new JLabel();
        label3 = new JLabel();
        label2 = new JLabel();
        scrollPane1 = new JScrollPane();
        expiredList = new JList();
        scrollPane3 = new JScrollPane();
        weekList = new JList();
        scrollPane2 = new JScrollPane();
        monthList = new JList();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new TableLayout(new double[][] {
            {0.05, 0.1, 20, 0.1, 20, 0.1, 20, TableLayout.FILL, 0.05},
            {30, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.FILL, 50}}));
        ((TableLayout)getLayout()).setHGap(10);
        ((TableLayout)getLayout()).setVGap(10);

        //---- label4 ----
        label4.setText("Insurance");
        label4.setFont(new Font("Cambria", Font.BOLD, 30));
        label4.setForeground(Color.white);
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        add(label4, new TableLayoutConstraints(1, 1, 7, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        add(panel1, new TableLayoutConstraints(7, 4, 7, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label1 ----
        label1.setText("Already Expired");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Cambria", Font.PLAIN, 15));
        label1.setForeground(Color.white);
        add(label1, new TableLayoutConstraints(1, 3, 1, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label3 ----
        label3.setText("Expiring This Week");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setFont(new Font("Cambria", Font.PLAIN, 15));
        label3.setForeground(Color.white);
        add(label3, new TableLayoutConstraints(3, 3, 3, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label2 ----
        label2.setText(" Expiring This Month");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Cambria", Font.PLAIN, 15));
        label2.setForeground(Color.white);
        add(label2, new TableLayoutConstraints(5, 3, 5, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(expiredList);
        }
        add(scrollPane1, new TableLayoutConstraints(1, 4, 1, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //======== scrollPane3 ========
        {
            scrollPane3.setViewportView(weekList);
        }
        add(scrollPane3, new TableLayoutConstraints(3, 4, 3, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(monthList);
        }
        add(scrollPane2, new TableLayoutConstraints(5, 4, 5, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - - -
    private JLabel label4;
    private DataDisplaySubpanel panel1;
    private JLabel label1;
    private JLabel label3;
    private JLabel label2;
    private JScrollPane scrollPane1;
    private JList expiredList;
    private JScrollPane scrollPane3;
    private JList weekList;
    private JScrollPane scrollPane2;
    private JList monthList;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void run() {
        while(true) {
            ArrayList<Student> expired = new ArrayList<>();
            ArrayList<Student> expireInWeek = new ArrayList<>();
            ArrayList<Student> expireInMonth = new ArrayList<>();

            Calendar cal = Calendar.getInstance();
            cal.set(cal.HOUR_OF_DAY, 0);
            cal.set(cal.MINUTE, 0);
            cal.set(cal.SECOND, 0);
            cal.set(cal.MILLISECOND, 0);

            java.sql.Date today = new Date(cal.getTime().getTime());

            cal.add(Calendar.DATE, 7);
            java.sql.Date week = new Date(cal.getTime().getTime());

            cal.add(Calendar.DATE, -7);
            cal.add(Calendar.MONTH, 1);
            java.sql.Date month = new Date(cal.getTime().getTime());


            for (Student currentStudent : DatabaseBridge.getAllStudents("Approved", 1)) {
                if (currentStudent.getInsuranceExpiryDate() != null) {
                    if (currentStudent.getInsuranceExpiryDate().compareTo(today) < 0)
                        expired.add(currentStudent);
                    else if (currentStudent.getInsuranceExpiryDate().compareTo(week) <= 0)
                        expireInWeek.add(currentStudent);
                    else if (currentStudent.getInsuranceExpiryDate().compareTo(month) < 0)
                        expireInMonth.add(currentStudent);
                }
            }
            sortByDate(expired);
            sortByDate(expireInWeek);
            sortByDate(expireInMonth);

            expiredList.setListData(expired.toArray());
            weekList.setListData(expireInWeek.toArray());
            monthList.setListData(expireInMonth.toArray());

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sortByDate(ArrayList<Student> studentsList){
        Collections.sort(studentsList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getInsuranceExpiryDate().compareTo(s2.getInsuranceExpiryDate());
            }
        });
    }
}
