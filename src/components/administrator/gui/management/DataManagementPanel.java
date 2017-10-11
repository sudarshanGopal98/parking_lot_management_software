/*
 * Created by JFormDesigner on Sat Feb 04 13:34:51 CST 2017
 */

package components.administrator.gui.management;

import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstraints;
import main_files.AdminMain;
import structures.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * @author Sudarshan Gopalakrishnan
 */
public class DataManagementPanel extends JPanel {
    public DataManagementPanel() {
        initComponents();
        setBackground(Colors.DARK_BACKGROUND_COLOR);

        exportToFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DataSaveDialog(AdminMain.FRAME);
            }
        });


        openBackupFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().open(new File("backups"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        loadFromBackup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoadDataDialog(null, LoadDataDialog.TYPE_1, getWidth()/2 - 310, getHeight()/2);

            }
        });


        loadFromAnotherFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoadDataDialog(null, LoadDataDialog.TYPE_2, getWidth()/2 - 310, getHeight()/2);
            }
        });
    }




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - - -
        label3 = new JLabel();
        label1 = new JLabel();
        label2 = new JLabel();
        exportToFile = new JButton();
        loadFromBackup = new JButton();
        openBackupFolder = new JButton();
        loadFromAnotherFile = new JButton();

        //======== this ========
        setFont(new Font("Cambria", Font.BOLD, 20));
        setForeground(Color.white);
        setAlignmentX(0.0F);

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new TableLayout(new double[][] {
            {0.05, TableLayout.FILL, 0.1, TableLayout.FILL, 0.05},
            {30, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, 50}}));
        ((TableLayout)getLayout()).setHGap(20);
        ((TableLayout)getLayout()).setVGap(20);

        //---- label3 ----
        label3.setText("Data Management");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setForeground(Color.white);
        label3.setFont(new Font("Cambria", Font.BOLD, 30));
        add(label3, new TableLayoutConstraints(1, 1, 3, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label1 ----
        label1.setText("Export Data");
        label1.setFont(new Font("Cambria", Font.BOLD, 20));
        label1.setForeground(Color.white);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        add(label1, new TableLayoutConstraints(1, 3, 1, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label2 ----
        label2.setText("Import Data");
        label2.setFont(new Font("Cambria", Font.BOLD, 20));
        label2.setForeground(Color.white);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        add(label2, new TableLayoutConstraints(3, 3, 3, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- exportToFile ----
        exportToFile.setText("Export Current Data To Excel File");
        exportToFile.setFont(new Font("Cambria", Font.PLAIN, 16));
        exportToFile.setBackground(new Color(170, 71, 86));
        exportToFile.setForeground(Color.white);
        add(exportToFile, new TableLayoutConstraints(1, 4, 1, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- loadFromBackup ----
        loadFromBackup.setText("Load Data From Backup");
        loadFromBackup.setFont(new Font("Cambria", Font.PLAIN, 16));
        loadFromBackup.setBackground(new Color(170, 71, 86));
        loadFromBackup.setForeground(Color.white);
        add(loadFromBackup, new TableLayoutConstraints(3, 4, 3, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- openBackupFolder ----
        openBackupFolder.setText("Open Backup Files Folder");
        openBackupFolder.setFont(new Font("Cambria", Font.PLAIN, 16));
        openBackupFolder.setBackground(new Color(170, 71, 86));
        openBackupFolder.setForeground(Color.white);
        add(openBackupFolder, new TableLayoutConstraints(1, 5, 1, 5, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- loadFromAnotherFile ----
        loadFromAnotherFile.setText("Load Data From Another File");
        loadFromAnotherFile.setFont(new Font("Cambria", Font.PLAIN, 16));
        loadFromAnotherFile.setBackground(new Color(170, 71, 86));
        loadFromAnotherFile.setForeground(Color.white);
        add(loadFromAnotherFile, new TableLayoutConstraints(3, 5, 3, 5, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - - -
    private JLabel label3;
    private JLabel label1;
    private JLabel label2;
    private JButton exportToFile;
    private JButton loadFromBackup;
    private JButton openBackupFolder;
    private JButton loadFromAnotherFile;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
