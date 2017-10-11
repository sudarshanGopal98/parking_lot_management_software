/*
 * Created by JFormDesigner on Thu Mar 24 14:33:04 CDT 2016
 */

package components.administrator.gui.management;

import components.misc.DatabaseBridge;
import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstraints;
import main_files.AdminMain;
import structures.Colors;
import structures.Student;

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author sud gop
 */
public class AllStudentsDataPanel extends JPanel implements Runnable{

    public AllStudentsDataPanel() {
        initComponents();
        setBackground(Colors.DARK_BACKGROUND_COLOR);
        splitPane2.setBackground(Colors.DARK_BACKGROUND_COLOR);

        Thread t = new Thread(this);
        t.start();
        AdminMain.THREAD_MANAGER.addThread("AllStudentDataPanel", t);

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(list1.getSelectedValue() != null)
                    panel1.displayData((Student)list1.getSelectedValue());
            }
        });
    }
    @Override
    public void run() {
        while(true){
            ArrayList<Student> students;
            if(searchbox.getText().length() != 0)
                students = DatabaseBridge.getAllStudents("LastName", searchbox.getText());
            else
                students = DatabaseBridge.getAllStudents();

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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - - -
        label1 = new JLabel();
        label4 = new JLabel();
        splitPane2 = new JPanel();
        label2 = new JLabel();
        searchbox = new JTextField();
        label3 = new JLabel();
        splitPane1 = new JSplitPane();
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
            {30, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.FILL, 50}}));

        //---- label1 ----
        label1.setText("List of All Students");
        label1.setFont(new Font("Cambria", Font.BOLD, 30));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setForeground(Color.white);
        add(label1, new TableLayoutConstraints(1, 1, 1, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label4 ----
        label4.setText(" ");
        label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 9f));
        add(label4, new TableLayoutConstraints(1, 2, 1, 2, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //======== splitPane2 ========
        {
            splitPane2.setBackground(new Color(103, 86, 130));
            splitPane2.setBorder(null);
            splitPane2.setAutoscrolls(true);
            splitPane2.setLayout(new TableLayout(new double[][] {
                {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.FILL},
                {TableLayout.PREFERRED}}));
            ((TableLayout)splitPane2.getLayout()).setHGap(5);
            ((TableLayout)splitPane2.getLayout()).setVGap(5);

            //---- label2 ----
            label2.setText("Search Student (by Last Name)");
            label2.setFont(new Font("Calibri", Font.BOLD, 18));
            label2.setHorizontalAlignment(SwingConstants.CENTER);
            label2.setForeground(Color.white);
            splitPane2.add(label2, new TableLayoutConstraints(0, 0, 0, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
            splitPane2.add(searchbox, new TableLayoutConstraints(2, 0, 2, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        }
        add(splitPane2, new TableLayoutConstraints(1, 3, 1, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label3 ----
        label3.setText(" ");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 9f));
        add(label3, new TableLayoutConstraints(1, 4, 1, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //======== splitPane1 ========
        {
            splitPane1.setDividerLocation(200);
            splitPane1.setLastDividerLocation(150);
            splitPane1.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

            //======== scrollPane1 ========
            {
                scrollPane1.setBorder(null);
                scrollPane1.setViewportView(list1);
            }
            splitPane1.setLeftComponent(scrollPane1);

            //---- panel1 ----
            panel1.setBorder(null);
            splitPane1.setRightComponent(panel1);
        }
        add(splitPane1, new TableLayoutConstraints(1, 5, 1, 5, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - - -
    private JLabel label1;
    private JLabel label4;
    private JPanel splitPane2;
    private JLabel label2;
    private JTextField searchbox;
    private JLabel label3;
    private JSplitPane splitPane1;
    private JScrollPane scrollPane1;
    private JList list1;
    private DataDisplaySubpanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
