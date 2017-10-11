/*
 * Created by JFormDesigner on Mon Mar 06 20:18:25 CST 2017
 */

package components.administrator.gui.management;

import components.misc.DatabaseBridge;
import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstraints;
import structures.Colors;
import structures.Log;
import structures.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * @author - -
 */
public class DataSaveDialog extends JDialog implements Runnable{
    private JFileChooser chooser;

    public DataSaveDialog(Frame owner) {
        super(owner);
        initComponents();
        getContentPane().setBackground(Colors.MEDIUM_BACKGROUND_COLOR);
        button1.setBackground(Colors.BUTTON_COLOR_1);
        button2.setBackground(Colors.BUTTON_COLOR_1);

        chooser = new JFileChooser();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setDialogTitle("Choose File Destination");
                chooser.showOpenDialog(DataSaveDialog.this);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    String locationName = chooser.getSelectedFile().getAbsolutePath()+ File.separatorChar+textField1.getText()+".csv";
                    Log.print(locationName);
                    File destination = new File(locationName);
                    Log.print(destination.createNewFile());

                    BufferedWriter bw = new BufferedWriter(new FileWriter(destination));

                    Log.print("Export Started");
                    bw.write(Student.STUDENT_EXPORT_HEADER);
                    bw.newLine();
                    bw.flush();

                    ArrayList<Student> students = DatabaseBridge.getAllStudents();
                    for(Student student: students){
                        Log.print(student.getExportString());
                        bw.write(student.getExportString());
                        bw.newLine();
                        bw.flush();
                    }
                    bw.close();


                    Log.print("Export Complete");
                    JOptionPane.showMessageDialog(DataSaveDialog.this, "The data has been successfully exported.", "Export Successful", JOptionPane.INFORMATION_MESSAGE);
                    dispose();

                }catch(Exception exp){
                    JOptionPane.showMessageDialog(DataSaveDialog.this, "ERROR: The data has NOT been completely exported. Please retry.", "Export Unsuccessful", JOptionPane.ERROR_MESSAGE);
                    dispose();
                }
            }
        });

        new Thread(this, "DataSaveThread").start();

        setVisible(true);
    }

    public DataSaveDialog(Dialog owner) {
        super(owner);
        initComponents();
    }


    @Override
    public void run(){
        while (true){
            if(chooser.getSelectedFile() == null)
                checkBox1.setSelected(false);
            else checkBox1.setSelected(true);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - - -
        label3 = new JLabel();
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        button1 = new JButton();
        checkBox1 = new JCheckBox();
        button2 = new JButton();

        //======== this ========
        setTitle("Parking Lot Management Software");
        Container contentPane = getContentPane();
        contentPane.setLayout(new TableLayout(new double[][] {
            {0.05, TableLayout.FILL, 213, TableLayout.PREFERRED, 0.05},
            {35, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
        ((TableLayout)contentPane.getLayout()).setHGap(5);
        ((TableLayout)contentPane.getLayout()).setVGap(5);

        //---- label3 ----
        label3.setText("Export Student Data to File");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setFont(new Font("Cambria", Font.BOLD, 16));
        label3.setForeground(Color.white);
        contentPane.add(label3, new TableLayoutConstraints(1, 0, 3, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label1 ----
        label1.setText("Name of File");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Cambria", Font.PLAIN, 14));
        label1.setForeground(Color.white);
        contentPane.add(label1, new TableLayoutConstraints(1, 1, 1, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        contentPane.add(textField1, new TableLayoutConstraints(2, 1, 3, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label2 ----
        label2.setText("Choose File Location");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Cambria", Font.PLAIN, 14));
        label2.setForeground(Color.white);
        contentPane.add(label2, new TableLayoutConstraints(1, 3, 1, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- button1 ----
        button1.setText("Choose");
        button1.setForeground(Color.white);
        contentPane.add(button1, new TableLayoutConstraints(2, 3, 2, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- checkBox1 ----
        checkBox1.setEnabled(false);
        checkBox1.setText("Folder Chosen?");
        checkBox1.setForeground(Color.white);
        checkBox1.setOpaque(false);
        contentPane.add(checkBox1, new TableLayoutConstraints(3, 3, 3, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- button2 ----
        button2.setText("Save Data");
        button2.setForeground(Color.white);
        contentPane.add(button2, new TableLayoutConstraints(1, 5, 3, 5, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - - -
    private JLabel label3;
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JButton button1;
    private JCheckBox checkBox1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
