/*
 * Created by JFormDesigner on Mon Apr 04 16:41:12 CDT 2016
 */

package components.administrator.gui.management;

import org.jdesktop.swingx.VerticalLayout;
import structures.Colors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * @author Sudarshan Gopalakrishnan
 */
public class NewStudentDialog extends JDialog {

    public NewStudentDialog() {
        initComponents();
        setBackground(Colors.MEDIUM_BACKGROUND_COLOR);
        cancelButton.setBackground(Colors.BUTTON_COLOR_1);
        okButton.setBackground(Colors.BUTTON_COLOR_1);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - - -
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        textField1 = new JTextField();
        vSpacer1 = new JPanel(null);
        label2 = new JLabel();
        textField2 = new JTextField();
        vSpacer2 = new JPanel(null);
        label3 = new JLabel();
        textField3 = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Add New Student");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            // JFormDesigner evaluation mark
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new VerticalLayout());

                //---- label1 ----
                label1.setText("First Name");
                label1.setFont(new Font("Calibri", Font.BOLD, 18));
                label1.setForeground(Color.white);
                contentPanel.add(label1);
                contentPanel.add(textField1);
                contentPanel.add(vSpacer1);

                //---- label2 ----
                label2.setText("Last Name");
                label2.setFont(new Font("Calibri", Font.BOLD, 18));
                label2.setForeground(Color.white);
                contentPanel.add(label2);
                contentPanel.add(textField2);
                contentPanel.add(vSpacer2);

                //---- label3 ----
                label3.setText("Student ID");
                label3.setFont(new Font("Calibri", Font.BOLD, 18));
                label3.setForeground(Color.white);
                contentPanel.add(label3);
                contentPanel.add(textField3);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - - -
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JTextField textField1;
    private JPanel vSpacer1;
    private JLabel label2;
    private JTextField textField2;
    private JPanel vSpacer2;
    private JLabel label3;
    private JTextField textField3;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
