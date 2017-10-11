/*
 * Created by JFormDesigner on Thu Mar 24 14:21:14 CDT 2016
 */

package components.student.gui;

import components.administrator.process.MessageBoxDisplay;
import components.misc.DatabaseBridge;
import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstraints;
import main_files.ClientMain;
import structures.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

/**
 * @author sud gop
 */
public class ClientDataDisplaySubpanel extends JPanel implements DataDisplayInterface{
    public Student currentStudent = null;

    public ClientDataDisplaySubpanel() {
        initComponents();
        panel1.setBackground(Colors.DARK_BACKGROUND_COLOR);
        setBackground(Colors.DARK_BACKGROUND_COLOR);

        grade.addItem("");
        grade.addItem(9);
        grade.addItem(10);
        grade.addItem(11);
        grade.addItem(12);

        firstname.addKeyListener(new KeyboardListener(this, firstname, "FirstName"));
        lastname.addKeyListener(new KeyboardListener(this, lastname, "LastName"));
        studentid.addKeyListener(new KeyboardListener(this, studentid, "StudentID"));
        grade.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    DatabaseBridge.writeUpdateQuery(Integer.parseInt(grade.getSelectedItem().toString()), getCurrentStudent().getStudentID());
                }catch (NumberFormatException e2){}
            }
        });
        carmake.addKeyListener(new KeyboardListener(this, carmake, "CarMake"));
        carmodel.addKeyListener(new KeyboardListener(this, carmodel, "CarModel"));
        carcolor.addKeyListener(new KeyboardListener(this, carcolor, "CarColor"));
        caryear.addKeyListener(new KeyboardListener(this, caryear, "CarYear"));
        carplate.addKeyListener(new KeyboardListener(this, carplate, "LicensePlateNumber"));

        TapListener insuranceTapListener = new TapListener() {
            @Override
            public void tap(KeyEvent e) {
                try {
                    Calendar cal = Calendar.getInstance();

                    // set Date portion to January 1, 1970
                    cal.set(cal.YEAR, Integer.parseInt(insuranceYear.getText()));
                    cal.set(cal.MONTH, Integer.parseInt(insuranceMonth.getText()) - 1);
                    cal.set(cal.DATE, Integer.parseInt(insuranceDay.getText()));

                    cal.set(cal.HOUR_OF_DAY, 0);
                    cal.set(cal.MINUTE, 0);
                    cal.set(cal.SECOND, 0);
                    cal.set(cal.MILLISECOND, 0);

                    java.sql.Date updatedDate = new java.sql.Date(cal.getTime().getTime());

                    DatabaseBridge.writeUpdateQuery(updatedDate, currentStudent.getStudentID());
                }catch (NumberFormatException e1){}
            }
        };

        insuranceMonth.addKeyListener(insuranceTapListener);
        insuranceDay.addKeyListener(insuranceTapListener);
        insuranceYear.addKeyListener(insuranceTapListener);

        paintField.addKeyListener(new KeyboardListener(this, paintField, "PaintField"));

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(allFieldsComplete()) {
                    ClientMain.FRAME.getPanelHolder().add(new ClientMapPanel(getWidth(), getHeight(), studentid.getText()));
                }else MessageBoxDisplay.showDataIncompleteError(ClientDataDisplaySubpanel.this);

            }
        });

    }

    private boolean allFieldsComplete() {
        return firstname.getText().length()!=0  &&
                lastname.getText().length()!=0  &&
                studentid.getText().length()!=0 &&
                carmake.getText().length()!=0 &&
                carmodel.getText().length()!=0 &&
                carcolor.getText().length()!=0 &&
                caryear.getText().length()!=0 &&
                carplate.getText().length()!=0 &&
                insuranceMonth.getText().length()!=0 &&
                insuranceDay.getText().length()!=0 &&
                insuranceYear.getText().length()!=0 &&
                grade.getSelectedIndex() > 0;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - - -
        label10 = new JLabel();
        label1 = new JLabel();
        firstname = new JTextField();
        label2 = new JLabel();
        lastname = new JTextField();
        label3 = new JLabel();
        studentid = new JTextField();
        label15 = new JLabel();
        grade = new JComboBox();
        label4 = new JLabel();
        label16 = new JLabel();
        carmake = new JTextField();
        label17 = new JLabel();
        carmodel = new JTextField();
        label18 = new JLabel();
        carcolor = new JTextField();
        label19 = new JLabel();
        caryear = new JTextField();
        label20 = new JLabel();
        carplate = new JTextField();
        label5 = new JLabel();
        label14 = new JLabel();
        panel1 = new JPanel();
        insuranceMonth = new JTextField();
        insuranceDay = new JTextField();
        insuranceYear = new JTextField();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        label21 = new JLabel();
        label23 = new JLabel();
        paintField = new JTextField();
        nextButton = new JButton();

        //======== this ========
        setFont(new Font("Calibri", Font.PLAIN, 30));
        setBorder(null);
        setForeground(Color.white);
        setAlignmentX(1.0F);
        setBackground(new Color(103, 86, 130));

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new TableLayout(new double[][] {
            {0.15, 0.35, 0.35, 0.15},
            {32, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, 47, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
        ((TableLayout)getLayout()).setHGap(5);
        ((TableLayout)getLayout()).setVGap(7);

        //---- label10 ----
        label10.setText("Please Fill All Your Information Below");
        label10.setHorizontalAlignment(SwingConstants.CENTER);
        label10.setFont(new Font("Cambria", Font.BOLD, 25));
        label10.setForeground(Color.white);
        add(label10, new TableLayoutConstraints(0, 1, 3, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label1 ----
        label1.setText("First Name");
        label1.setFont(new Font("Calibri", Font.BOLD, 20));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setForeground(Color.white);
        label1.setAlignmentX(1.0F);
        add(label1, new TableLayoutConstraints(1, 3, 1, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- firstname ----
        firstname.setEditable(false);
        firstname.setFont(new Font("Cambria", Font.PLAIN, 14));
        add(firstname, new TableLayoutConstraints(2, 3, 2, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label2 ----
        label2.setText("Last Name");
        label2.setFont(new Font("Calibri", Font.BOLD, 20));
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setForeground(Color.white);
        label2.setAlignmentX(1.0F);
        add(label2, new TableLayoutConstraints(1, 4, 1, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- lastname ----
        lastname.setEditable(false);
        lastname.setFont(new Font("Cambria", Font.PLAIN, 14));
        add(lastname, new TableLayoutConstraints(2, 4, 2, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label3 ----
        label3.setText("Student ID");
        label3.setFont(new Font("Calibri", Font.BOLD, 20));
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setForeground(Color.white);
        label3.setAlignmentX(1.0F);
        add(label3, new TableLayoutConstraints(1, 5, 1, 5, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- studentid ----
        studentid.setEditable(false);
        studentid.setFont(new Font("Cambria", Font.PLAIN, 14));
        add(studentid, new TableLayoutConstraints(2, 5, 2, 5, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label15 ----
        label15.setText("Grade");
        label15.setFont(new Font("Calibri", Font.BOLD, 20));
        label15.setHorizontalAlignment(SwingConstants.CENTER);
        label15.setForeground(Color.white);
        label15.setAlignmentX(1.0F);
        add(label15, new TableLayoutConstraints(1, 6, 1, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- grade ----
        grade.setMaximumRowCount(4);
        grade.setFont(new Font("Cambria", Font.PLAIN, 14));
        add(grade, new TableLayoutConstraints(2, 6, 2, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label4 ----
        label4.setText(" ");
        label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 7f));
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        add(label4, new TableLayoutConstraints(1, 7, 1, 7, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label16 ----
        label16.setText("Car Make");
        label16.setFont(new Font("Calibri", Font.BOLD, 20));
        label16.setHorizontalAlignment(SwingConstants.CENTER);
        label16.setForeground(Color.white);
        label16.setAlignmentX(1.0F);
        add(label16, new TableLayoutConstraints(1, 8, 1, 8, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- carmake ----
        carmake.setFont(new Font("Cambria", Font.PLAIN, 14));
        add(carmake, new TableLayoutConstraints(2, 8, 2, 8, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label17 ----
        label17.setText("Car Model");
        label17.setFont(new Font("Calibri", Font.BOLD, 20));
        label17.setHorizontalAlignment(SwingConstants.CENTER);
        label17.setForeground(Color.white);
        label17.setAlignmentX(1.0F);
        add(label17, new TableLayoutConstraints(1, 9, 1, 9, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- carmodel ----
        carmodel.setFont(new Font("Cambria", Font.PLAIN, 14));
        add(carmodel, new TableLayoutConstraints(2, 9, 2, 9, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label18 ----
        label18.setText("Car Color");
        label18.setFont(new Font("Calibri", Font.BOLD, 20));
        label18.setHorizontalAlignment(SwingConstants.CENTER);
        label18.setForeground(Color.white);
        label18.setAlignmentX(1.0F);
        add(label18, new TableLayoutConstraints(1, 10, 1, 10, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- carcolor ----
        carcolor.setHorizontalAlignment(SwingConstants.LEFT);
        carcolor.setFont(new Font("Cambria", Font.PLAIN, 14));
        add(carcolor, new TableLayoutConstraints(2, 10, 2, 10, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label19 ----
        label19.setText("Car Year");
        label19.setFont(new Font("Calibri", Font.BOLD, 20));
        label19.setHorizontalAlignment(SwingConstants.CENTER);
        label19.setForeground(Color.white);
        label19.setAlignmentX(1.0F);
        add(label19, new TableLayoutConstraints(1, 11, 1, 11, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- caryear ----
        caryear.setFont(new Font("Cambria", Font.PLAIN, 14));
        add(caryear, new TableLayoutConstraints(2, 11, 2, 11, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label20 ----
        label20.setText("Car Licence Plate #");
        label20.setFont(new Font("Calibri", Font.BOLD, 20));
        label20.setHorizontalAlignment(SwingConstants.CENTER);
        label20.setForeground(Color.white);
        label20.setAlignmentX(1.0F);
        add(label20, new TableLayoutConstraints(1, 12, 1, 12, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- carplate ----
        carplate.setFont(new Font("Cambria", Font.PLAIN, 14));
        add(carplate, new TableLayoutConstraints(2, 12, 2, 12, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label5 ----
        label5.setText(" ");
        label5.setFont(label5.getFont().deriveFont(label5.getFont().getStyle() | Font.ITALIC, label5.getFont().getSize() + 7f));
        add(label5, new TableLayoutConstraints(1, 13, 1, 13, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label14 ----
        label14.setText("Insurance Expiry Date");
        label14.setFont(new Font("Calibri", Font.BOLD, 20));
        label14.setHorizontalAlignment(SwingConstants.CENTER);
        label14.setForeground(Color.white);
        label14.setAlignmentX(1.0F);
        add(label14, new TableLayoutConstraints(1, 14, 1, 14, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //======== panel1 ========
        {
            panel1.setLayout(new TableLayout(new double[][] {
                {TableLayout.FILL, TableLayout.FILL, TableLayout.FILL},
                {27, TableLayout.PREFERRED, 8, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
            ((TableLayout)panel1.getLayout()).setHGap(10);
            ((TableLayout)panel1.getLayout()).setVGap(10);

            //---- insuranceMonth ----
            insuranceMonth.setHorizontalAlignment(SwingConstants.CENTER);
            insuranceMonth.setFont(new Font("Cambria", Font.PLAIN, 14));
            panel1.add(insuranceMonth, new TableLayoutConstraints(0, 0, 0, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- insuranceDay ----
            insuranceDay.setHorizontalAlignment(SwingConstants.CENTER);
            insuranceDay.setFont(new Font("Cambria", Font.PLAIN, 14));
            panel1.add(insuranceDay, new TableLayoutConstraints(1, 0, 1, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- insuranceYear ----
            insuranceYear.setHorizontalAlignment(SwingConstants.CENTER);
            insuranceYear.setFont(new Font("Cambria", Font.PLAIN, 14));
            panel1.add(insuranceYear, new TableLayoutConstraints(2, 0, 2, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label7 ----
            label7.setText("Month");
            label7.setHorizontalAlignment(SwingConstants.CENTER);
            label7.setForeground(Color.white);
            panel1.add(label7, new TableLayoutConstraints(0, 1, 0, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label8 ----
            label8.setText("Day");
            label8.setHorizontalAlignment(SwingConstants.CENTER);
            label8.setForeground(Color.white);
            panel1.add(label8, new TableLayoutConstraints(1, 1, 1, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label9 ----
            label9.setText("Year");
            label9.setHorizontalAlignment(SwingConstants.CENTER);
            label9.setForeground(Color.white);
            panel1.add(label9, new TableLayoutConstraints(2, 1, 2, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label11 ----
            label11.setText("Example Date");
            label11.setHorizontalAlignment(SwingConstants.CENTER);
            label11.setForeground(Color.white);
            label11.setFont(label11.getFont().deriveFont(label11.getFont().getSize() + 1f));
            panel1.add(label11, new TableLayoutConstraints(0, 3, 2, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label12 ----
            label12.setText("02");
            label12.setHorizontalAlignment(SwingConstants.CENTER);
            label12.setForeground(Color.white);
            panel1.add(label12, new TableLayoutConstraints(0, 4, 0, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label13 ----
            label13.setText("28");
            label13.setHorizontalAlignment(SwingConstants.CENTER);
            label13.setForeground(Color.white);
            panel1.add(label13, new TableLayoutConstraints(1, 4, 1, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label21 ----
            label21.setText("2017");
            label21.setHorizontalAlignment(SwingConstants.CENTER);
            label21.setForeground(Color.white);
            panel1.add(label21, new TableLayoutConstraints(2, 4, 2, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        }
        add(panel1, new TableLayoutConstraints(2, 14, 2, 14, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label23 ----
        label23.setText("Paint Field");
        label23.setFont(new Font("Calibri", Font.PLAIN, 20));
        label23.setHorizontalAlignment(SwingConstants.CENTER);
        label23.setForeground(Color.white);
        label23.setVisible(false);
        label23.setAlignmentX(1.0F);
        add(label23, new TableLayoutConstraints(1, 15, 1, 15, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- paintField ----
        paintField.setVisible(false);
        paintField.setFont(new Font("Cambria", Font.PLAIN, 14));
        add(paintField, new TableLayoutConstraints(2, 15, 2, 15, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- nextButton ----
        nextButton.setText("Next");
        nextButton.setBackground(new Color(44, 0, 70));
        nextButton.setForeground(Color.white);
        nextButton.setFont(new Font("Cambria", Font.BOLD, 16));
        add(nextButton, new TableLayoutConstraints(1, 18, 2, 19, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - - -
    private JLabel label10;
    private JLabel label1;
    private JTextField firstname;
    private JLabel label2;
    private JTextField lastname;
    private JLabel label3;
    private JTextField studentid;
    private JLabel label15;
    private JComboBox grade;
    private JLabel label4;
    private JLabel label16;
    private JTextField carmake;
    private JLabel label17;
    private JTextField carmodel;
    private JLabel label18;
    private JTextField carcolor;
    private JLabel label19;
    private JTextField caryear;
    private JLabel label20;
    private JTextField carplate;
    private JLabel label5;
    private JLabel label14;
    private JPanel panel1;
    private JTextField insuranceMonth;
    private JTextField insuranceDay;
    private JTextField insuranceYear;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label21;
    private JLabel label23;
    private JTextField paintField;
    private JButton nextButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public void displayData(Student currentStudent){
        this.currentStudent = currentStudent;
        if(currentStudent == null) {
            clearData();
            return;
        }

        firstname.setText(currentStudent.getFirstName());
        lastname.setText(currentStudent.getLastName());
        studentid.setText(currentStudent.getStudentID());
        grade.setSelectedItem(currentStudent.getGrade()+"");
        carmake.setText(currentStudent.getCarMake());
        carmodel.setText(currentStudent.getCarModel());
        carcolor.setText(currentStudent.getCarColor());
        caryear.setText(currentStudent.getCarYear());
        carplate.setText(currentStudent.getLicensePlateNumber());

        insuranceMonth.setText(currentStudent.getInsuranceExpiryMonth());
        insuranceDay.setText(currentStudent.getInsuranceExpiryDay());
        insuranceYear.setText(currentStudent.getInsuranceExpiryYear());

        repaint();
    }

    public void clearData(){
        this.currentStudent = null;
        firstname.setText("");
        lastname.setText("");
        studentid.setText("");
        grade.setSelectedIndex(0);
        carmake.setText("");
        carmodel.setText("");
        carcolor.setText("");
        caryear.setText("");
        carplate.setText("");
        insuranceDay.setText("");
        insuranceMonth.setText("");
        insuranceYear.setText("");

    }

    public Student getCurrentStudent() {
        return currentStudent;
    }

    public void setPreferredWidth(int width){
        this.setPreferredSize(new Dimension(width, this.getHeight()));
    }
}