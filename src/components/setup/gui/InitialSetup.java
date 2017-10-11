/*
 * Created by JFormDesigner on Tue Feb 07 13:32:33 CST 2017
 */

package components.setup.gui;

import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstraints;
import main_files.SetupMain;
import org.apache.commons.io.FileUtils;
import structures.Colors;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.JFileChooser.OPEN_DIALOG;

/**
 * @author Sudarshan Gopalakrishnan
 */
public class InitialSetup extends JPanel {
    File mapDestination = null;

    public InitialSetup() {
        initComponents();
        setBackground(Colors.MEDIUM_BACKGROUND_COLOR);

        Toolkit tk = this.getToolkit();
        setLocation(0, (int)tk.getScreenSize().getHeight()/3);
        setSize(tk.getScreenSize());
        checkBox1.setEnabled(false);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("PNG Images","png"));
                fileChooser.setDialogTitle("Choose School Parking Lot Map");
                fileChooser.setDialogType(OPEN_DIALOG);
                fileChooser.setMultiSelectionEnabled(false);
                if(fileChooser.showOpenDialog(InitialSetup.this) == APPROVE_OPTION){
                    mapDestination = fileChooser.getSelectedFile();
                    if(mapDestination != null)
                        checkBox1.setSelected(true);
                }
            }
        });
        button1.setBackground(Colors.BUTTON_COLOR_1);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numberOfZones = Integer.parseInt(textField3.getText());
                    if(numberOfZones <= 0){
                        JOptionPane.showMessageDialog(InitialSetup.this, "Sorry, you need atleast one zone to continue.", "Error: Input Incorrect", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    File dest = new File("images/Full School Map.png");
                    dest.createNewFile();

                    FileUtils.copyFile(mapDestination, dest);

                    SetupMain.FRAME.getPanelHolder().add(new MapSetup(numberOfZones, mapDestination));
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(InitialSetup.this, "Please enter a number for the field 'Number of Zones.'", "Error: Input is incorrect",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        button2.setBackground(Colors.BUTTON_COLOR_1);

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - - -
        label4 = new JLabel();
        label2 = new JLabel();
        textField3 = new JTextField();
        label3 = new JLabel();
        button1 = new JButton();
        checkBox1 = new JCheckBox();
        button2 = new JButton();

        //======== this ========
        setBackground(new Color(103, 86, 130));

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new TableLayout(new double[][] {
            {TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.PREFERRED, TableLayout.FILL},
            {30, TableLayout.PREFERRED, 30, TableLayout.PREFERRED, 20, TableLayout.PREFERRED, 20, TableLayout.PREFERRED}}));
        ((TableLayout)getLayout()).setHGap(5);
        ((TableLayout)getLayout()).setVGap(5);

        //---- label4 ----
        label4.setText("Welcome to Parking Lot Management Software - Setup");
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        label4.setForeground(Color.white);
        label4.setFont(new Font("Cambria", label4.getFont().getStyle(), label4.getFont().getSize() + 11));
        add(label4, new TableLayoutConstraints(0, 1, 4, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label2 ----
        label2.setText("Number of Zones");
        label2.setFont(new Font("Cambria", Font.PLAIN, 20));
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setForeground(Color.white);
        add(label2, new TableLayoutConstraints(1, 3, 1, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        add(textField3, new TableLayoutConstraints(2, 3, 3, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label3 ----
        label3.setText("Upload Parking Lot Map");
        label3.setFont(new Font("Cambria", Font.PLAIN, 20));
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setForeground(Color.white);
        add(label3, new TableLayoutConstraints(1, 5, 1, 5, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- button1 ----
        button1.setText("Upload");
        button1.setForeground(Color.white);
        button1.setFont(new Font("Cambria", Font.PLAIN, 16));
        button1.setBackground(new Color(170, 71, 86));
        add(button1, new TableLayoutConstraints(2, 5, 2, 5, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- checkBox1 ----
        checkBox1.setText("File Selected");
        checkBox1.setHorizontalAlignment(SwingConstants.CENTER);
        checkBox1.setOpaque(false);
        checkBox1.setForeground(Color.white);
        add(checkBox1, new TableLayoutConstraints(3, 5, 3, 5, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- button2 ----
        button2.setText("Next");
        button2.setForeground(Color.white);
        button2.setFont(new Font("Cambria", Font.PLAIN, 16));
        button2.setBackground(new Color(170, 71, 86));
        add(button2, new TableLayoutConstraints(1, 7, 3, 7, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - - -
    private JLabel label4;
    private JLabel label2;
    private JTextField textField3;
    private JLabel label3;
    private JButton button1;
    private JCheckBox checkBox1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
