/*
 * Created by JFormDesigner on Thu Feb 02 21:23:37 CST 2017
 */

package components.administrator.gui.maps;

import components.misc.DatabaseBridge;
import components.administrator.process.MessageBoxDisplay;
import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstraints;
import structures.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * @author Sudarshan Gopalakrishnan
 */
public class NewStudentDialog extends JDialog {
    public NewStudentDialog() {
        initComponents();
        getContentPane().setBackground(Colors.DARK_BACKGROUND_COLOR);
        setResizable(false);
        setVisible(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(textField3.getText().length() == 8) {
                        if (DatabaseBridge.addStudentToDatabase(DatabaseBridge.CONNECTION, textField2.getText(), textField1.getText(), textField3.getText(), 0)) {
                            MessageBoxDisplay.showStudentAddedMessage(NewStudentDialog.this);
                            NewStudentDialog.this.dispose();
                        } else
                            MessageBoxDisplay.showStudentAlreadyExistsMessage(NewStudentDialog.this);
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - - -
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        textField3 = new JTextField();
        button1 = new JButton();

        //======== this ========
        setTitle("Parking Lot Management Software");
        setBackground(new Color(103, 86, 130));
        setAutoRequestFocus(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new TableLayout(new double[][] {
            {25, 79, 272, TableLayout.PREFERRED},
            {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
        ((TableLayout)contentPane.getLayout()).setHGap(5);
        ((TableLayout)contentPane.getLayout()).setVGap(5);

        //---- label1 ----
        label1.setText("First Name");
        label1.setFont(new Font("Cambria", Font.PLAIN, 15));
        label1.setForeground(Color.white);
        contentPane.add(label1, new TableLayoutConstraints(1, 1, 1, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        contentPane.add(textField1, new TableLayoutConstraints(2, 1, 2, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label2 ----
        label2.setText("Last Name");
        label2.setFont(new Font("Cambria", Font.PLAIN, 15));
        label2.setForeground(Color.white);
        contentPane.add(label2, new TableLayoutConstraints(1, 2, 1, 2, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        contentPane.add(textField2, new TableLayoutConstraints(2, 2, 2, 2, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label3 ----
        label3.setText("Student ID");
        label3.setFont(new Font("Cambria", Font.PLAIN, 15));
        label3.setForeground(Color.white);
        contentPane.add(label3, new TableLayoutConstraints(1, 3, 1, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        contentPane.add(textField3, new TableLayoutConstraints(2, 3, 2, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- button1 ----
        button1.setText("ADD  STUDENT");
        button1.setBackground(new Color(170, 71, 86));
        button1.setForeground(Color.white);
        contentPane.add(button1, new TableLayoutConstraints(1, 5, 2, 5, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - - -
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JTextField textField3;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
