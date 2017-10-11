/*
 * Created by JFormDesigner on Sun Apr 03 00:46:49 CDT 2016
 */

package components.administrator.gui.management;

import components.misc.DatabaseBridge;
import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstraints;
import structures.Colors;
import structures.Student;
import structures.TapListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * @author Sudarshan Gopalakrishnan
 */
public class StudentSearchupPanel extends JPanel{
    public StudentSearchupPanel(JFrame parent) {
        initComponents();
        setBackground(Colors.DARK_BACKGROUND_COLOR);

        TapListener fieldChange = new TapListener() {
            @Override
            public void tap(KeyEvent e) {
                ArrayList<String> filters = new ArrayList<>();
                ArrayList<String> keysToFilter = new ArrayList<>();

                if(fn.getText().trim().length() > 0) {
                    filters.add(fn.getText().trim());
                    keysToFilter.add("FirstName");
                }
                if(ln.getText().trim().length() > 0) {
                    filters.add(ln.getText().trim());
                    keysToFilter.add("LastName");
                }
                if(studentID.getText().trim().length() > 0) {
                    filters.add(studentID.getText().trim());
                    keysToFilter.add("StudentID");
                }
                if(make.getText().trim().length() > 0) {
                    filters.add(make.getText().trim());
                    keysToFilter.add("CarMake");
                }
                if(model.getText().trim().length() > 0) {
                    filters.add(model.getText().trim());
                    keysToFilter.add("CarModel");
                }
                if(color.getText().trim().length() > 0) {
                    filters.add(color.getText().trim());
                    keysToFilter.add("CarColor");
                }
                if(year.getText().trim().length() > 0) {
                    filters.add(year.getText().trim());
                    keysToFilter.add("CarYear");
                }
                if(lpn.getText().trim().length() > 0) {
                    filters.add(lpn.getText().trim());
                    keysToFilter.add("LicensePlateNumber");
                }
                if(sticker.getText().trim().length() > 0) {
                    filters.add(sticker.getText().trim());
                    keysToFilter.add("StickerNumber");
                }

                ArrayList<Student> studentToAdd = DatabaseBridge.getAllStudents(keysToFilter.toArray(),filters.toArray());
                list1.setListData(studentToAdd.toArray());
                return;
            }
        };

        fn.addKeyListener(fieldChange);
        ln.addKeyListener(fieldChange);
        studentID.addKeyListener(fieldChange);

        make.addKeyListener(fieldChange);
        model.addKeyListener(fieldChange);
        color.addKeyListener(fieldChange);
        year.addKeyListener(fieldChange);

        lpn.addKeyListener(fieldChange);
        sticker.addKeyListener(fieldChange);

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(list1.getSelectedValue() != null)
                    panel1.displayData((Student)list1.getSelectedValue());
            }
        });

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - - -
        label1 = new JLabel();
        label2 = new JLabel();
        panel1 = new DataDisplaySubpanel();
        label3 = new JLabel();
        fn = new JTextField();
        label4 = new JLabel();
        ln = new JTextField();
        label5 = new JLabel();
        studentID = new JTextField();
        label6 = new JLabel();
        make = new JTextField();
        label7 = new JLabel();
        model = new JTextField();
        label8 = new JLabel();
        color = new JTextField();
        label9 = new JLabel();
        year = new JTextField();
        label10 = new JLabel();
        lpn = new JTextField();
        label11 = new JLabel();
        sticker = new JTextField();
        scrollPane1 = new JScrollPane();
        list1 = new JList();

        //======== this ========
        setPreferredSize(new Dimension(500, 527));
        setMinimumSize(new Dimension(500, 401));
        setFont(this.getFont().deriveFont(15f));

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new TableLayout(new double[][] {
            {0.05, 0.2, 0.2, 20, TableLayout.FILL, 0.05},
            {30, TableLayout.PREFERRED, 20, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, 25, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, 30, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.FILL, 50}}));
        ((TableLayout)getLayout()).setHGap(5);
        ((TableLayout)getLayout()).setVGap(5);

        //---- label1 ----
        label1.setText("Student Lookup");
        label1.setFont(new Font("Cambria", Font.BOLD, 30));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setForeground(Color.white);
        add(label1, new TableLayoutConstraints(1, 1, 4, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label2 ----
        label2.setText("FILL ANY DETAILS AVAILABLE TO LOOKUP STUDENT");
        label2.setHorizontalTextPosition(SwingConstants.CENTER);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Cambria", Font.BOLD, 20));
        label2.setForeground(Color.white);
        add(label2, new TableLayoutConstraints(1, 3, 2, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        add(panel1, new TableLayoutConstraints(4, 3, 4, 17, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label3 ----
        label3.setText("First Name");
        label3.setForeground(Color.white);
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setFont(label3.getFont().deriveFont(15f));
        add(label3, new TableLayoutConstraints(1, 5, 1, 5, TableLayoutConstraints.CENTER, TableLayoutConstraints.FULL));
        add(fn, new TableLayoutConstraints(2, 5, 2, 5, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label4 ----
        label4.setText("Last Name");
        label4.setForeground(Color.white);
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        label4.setFont(label4.getFont().deriveFont(15f));
        add(label4, new TableLayoutConstraints(1, 6, 1, 6, TableLayoutConstraints.CENTER, TableLayoutConstraints.FULL));
        add(ln, new TableLayoutConstraints(2, 6, 2, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label5 ----
        label5.setText("Student ID");
        label5.setForeground(Color.white);
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        label5.setFont(label5.getFont().deriveFont(15f));
        add(label5, new TableLayoutConstraints(1, 7, 1, 7, TableLayoutConstraints.CENTER, TableLayoutConstraints.FULL));
        add(studentID, new TableLayoutConstraints(2, 7, 2, 7, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label6 ----
        label6.setText("Car Make");
        label6.setForeground(Color.white);
        label6.setHorizontalAlignment(SwingConstants.CENTER);
        label6.setFont(label6.getFont().deriveFont(15f));
        add(label6, new TableLayoutConstraints(1, 9, 1, 9, TableLayoutConstraints.CENTER, TableLayoutConstraints.FULL));
        add(make, new TableLayoutConstraints(2, 9, 2, 9, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label7 ----
        label7.setText("Car Model");
        label7.setForeground(Color.white);
        label7.setHorizontalAlignment(SwingConstants.CENTER);
        label7.setFont(label7.getFont().deriveFont(15f));
        add(label7, new TableLayoutConstraints(1, 10, 1, 10, TableLayoutConstraints.CENTER, TableLayoutConstraints.FULL));
        add(model, new TableLayoutConstraints(2, 10, 2, 10, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label8 ----
        label8.setText("Car Color");
        label8.setForeground(Color.white);
        label8.setHorizontalAlignment(SwingConstants.CENTER);
        label8.setFont(label8.getFont().deriveFont(15f));
        add(label8, new TableLayoutConstraints(1, 11, 1, 11, TableLayoutConstraints.CENTER, TableLayoutConstraints.FULL));
        add(color, new TableLayoutConstraints(2, 11, 2, 11, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label9 ----
        label9.setText("Car Year");
        label9.setForeground(Color.white);
        label9.setHorizontalAlignment(SwingConstants.CENTER);
        label9.setFont(label9.getFont().deriveFont(15f));
        add(label9, new TableLayoutConstraints(1, 12, 1, 12, TableLayoutConstraints.CENTER, TableLayoutConstraints.FULL));
        add(year, new TableLayoutConstraints(2, 12, 2, 12, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label10 ----
        label10.setText("Car License Plate Number");
        label10.setForeground(Color.white);
        label10.setHorizontalAlignment(SwingConstants.CENTER);
        label10.setFont(label10.getFont().deriveFont(15f));
        add(label10, new TableLayoutConstraints(1, 13, 1, 13, TableLayoutConstraints.CENTER, TableLayoutConstraints.FULL));
        add(lpn, new TableLayoutConstraints(2, 13, 2, 13, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label11 ----
        label11.setText("Sticker Number");
        label11.setForeground(Color.white);
        label11.setHorizontalAlignment(SwingConstants.CENTER);
        label11.setFont(label11.getFont().deriveFont(15f));
        add(label11, new TableLayoutConstraints(1, 15, 1, 15, TableLayoutConstraints.CENTER, TableLayoutConstraints.FULL));
        add(sticker, new TableLayoutConstraints(2, 15, 2, 15, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(list1);
        }
        add(scrollPane1, new TableLayoutConstraints(1, 17, 2, 17, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - - -
    private JLabel label1;
    private JLabel label2;
    private DataDisplaySubpanel panel1;
    private JLabel label3;
    private JTextField fn;
    private JLabel label4;
    private JTextField ln;
    private JLabel label5;
    private JTextField studentID;
    private JLabel label6;
    private JTextField make;
    private JLabel label7;
    private JTextField model;
    private JLabel label8;
    private JTextField color;
    private JLabel label9;
    private JTextField year;
    private JLabel label10;
    private JTextField lpn;
    private JLabel label11;
    private JTextField sticker;
    private JScrollPane scrollPane1;
    private JList list1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
