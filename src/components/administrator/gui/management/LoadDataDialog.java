/*
 * Created by JFormDesigner on Sat Apr 22 23:46:56 CDT 2017
 */

package components.administrator.gui.management;

import components.setup.process.ParkingSpotBooter;
import components.setup.process.StudentDataBooter;
import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstraints;
import structures.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.PreparedStatement;
import java.util.*;

import static components.misc.DatabaseBridge.CONNECTION;
import static javax.swing.JFileChooser.APPROVE_OPTION;

/**
 * @author - -
 */
public class LoadDataDialog extends JDialog {


    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;

    /*
    int TYPE_1 = 1 is loading data from backup
    int TYPE_2 = 2 is loading data from external file

     */
    public LoadDataDialog(Frame owner, int type, int xLoc, int yLoc) {
        super(owner);
        setUndecorated(true);

        initComponents();
        setVisible(false);

        button1.setBackground(Colors.BUTTON_COLOR_1);
        button1.setForeground(Color.WHITE);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoadDataDialog.this.dispose();
            }
        });

        getContentPane().setBackground(Colors.MEDIUM_BACKGROUND_COLOR);
        setLocation(xLoc, yLoc);
        setVisible(true);

        if (type == TYPE_1) {
            try {
                loadDataFromBackupFiles();

                label1.setText("The data has been successfully transferred.");
                label2.setText("You may continue to use the software.");
                button1.setEnabled(true);

            } catch (final Exception e) {
                label1.setText("Sorry, the data transfer process has failed.");
                label2.setText("Please restart the system and try again.");

                button1.setText("Close System");
                button1.removeActionListener(button1.getActionListeners()[0]);
                button1.setEnabled(true);
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e2) {
                        e.printStackTrace();
                        System.exit(0);
                    }
                });
            }



        } else if (type == TYPE_2) {
            try {
                loadDataFromExportedFiles();

                label1.setText("The data has been successfully transferred.");
                label2.setText("You may continue to use the software.");
                button1.setEnabled(true);

            } catch (final Exception e) {
                label1.setText("Sorry, the data transfer process has failed.");
                label2.setText("Please restart the system and try again.");

                button1.setText("Close System");
                button1.removeActionListener(button1.getActionListeners()[0]);
                button1.setEnabled(true);
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e2) {
                        e.printStackTrace();
                        System.exit(0);
                    }
                });
            }


        }
    }

    private void loadDataFromBackupFiles() throws Exception {
        FileSystemView fileSystemView = new DirectoryRestrictedFileSystemView(new File("backups/"));
        JFileChooser fileChooser = new JFileChooser(fileSystemView.getHomeDirectory(), fileSystemView);
        fileChooser.setCurrentDirectory(new File("backups/"));
        fileChooser.setDialogTitle("Choose Backup File");
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV File","csv"));
        fileChooser.setMultiSelectionEnabled(false);

        ArrayList<Student> students = new ArrayList<>();
        HashMap<String, Integer> zoneDetails = new HashMap<>();


        if(fileChooser.showOpenDialog(null) == APPROVE_OPTION) {
            Log.print("Loading Data From Files");


            Scanner fromFile = new Scanner(fileChooser.getSelectedFile());
            int numberColumns = fromFile.nextLine().split(",").length;
            while (fromFile.hasNextLine()) {
                String[] rowItems = fromFile.nextLine().split(",");
                if(rowItems.length == numberColumns){
                    int i = 0;

                    Student student = new Student(rowItems[i++], rowItems[i++], rowItems[i++], Integer.parseInt(rowItems[i++]), rowItems[i++],
                            rowItems[i++], rowItems[i++], rowItems[i++], rowItems[i++], rowItems[i++],
                            (stringToDate(rowItems[i++].split("/"))), new PaintField(rowItems[i++]));

                    students.add(student);

                    zoneDetails.put(student.getStudentID(), Integer.parseInt(rowItems[rowItems.length - 3]));

                }else if(rowItems.length == (numberColumns - 1)){
                    int i = 0;
                    Student student = new Student(rowItems[i++], rowItems[i++], rowItems[i++], Integer.parseInt(rowItems[i++]), rowItems[i++],
                            rowItems[i++], rowItems[i++], rowItems[i++], rowItems[i++], rowItems[i++],
                            (stringToDate(rowItems[i++].split("/"))), new PaintField(""));

                    // process same changes in DataImporter
                    students.add(student);

                    zoneDetails.put(student.getStudentID(), Integer.parseInt(rowItems[rowItems.length - 2]));

                }else{
                    ArrayList<String> rowItemBuilder = new ArrayList<>();
                    for(int i = 0; i < numberColumns; i++){
                        if(i < rowItems.length)
                            rowItemBuilder.add(rowItems[i]);
                        else
                            rowItemBuilder.add("");
                    }

                    try
                    {Integer.parseInt(rowItemBuilder.get(3));}
                    catch (NumberFormatException e2)
                    {rowItemBuilder.set(3,"0");}

                    int i = 0;
                    Student student = new Student(rowItemBuilder.get(i++), rowItemBuilder.get(i++), rowItemBuilder.get(i++),
                            Integer.parseInt(rowItemBuilder.get(i++)), rowItemBuilder.get(i++), rowItemBuilder.get(i++),
                            rowItemBuilder.get(i++), rowItemBuilder.get(i++), rowItemBuilder.get(i++), rowItemBuilder.get(i++), null, new PaintField(rowItemBuilder.get(i++)));

                    students.add(student);
                }
            }
            fromFile.close();

            StudentDataBooter.clearAllData(CONNECTION);


            for(Student s: students) {
                Log.print("Adding Imported Student: "+s);
                StudentDataBooter.addImportedStudent(CONNECTION,
                        s.getLastName(),
                        s.getFirstName(),
                        s.getStudentID(),
                        s.getGrade(),
                        s.getCarMake(),
                        s.getCarModel(),
                        s.getCarColor(),
                        s.getCarYear(),
                        s.getLicensePlateNumber(),
                        s.getInsuranceExpiryDate(),
                        s.getStickerNumber(),
                        s.getPaintField());
            }

            Log.print("Updating Parking Spot Details");
            ParkingSpotBooter.loadData();
            for(String studentID: zoneDetails.keySet()){
                Log.print("Updating Parking Spot of: "+studentID);
                PreparedStatement ps = CONNECTION.prepareStatement("update parking_spot_data set StudentIDAssignedTo = ? where SpotNumber = ?");
                ps.setString(1, studentID);
                ps.setInt(2, zoneDetails.get(studentID));
                ps.execute();

                ps = CONNECTION.prepareStatement("update parking_spot_data set IsApproved = ? where StudentIDAssignedTo = ?");
                ps.setInt(1, 1);
                ps.setString(2, studentID);
                ps.execute();

                ps = CONNECTION.prepareStatement("update data_pool set Approved = ? where StudentID = ?");
                ps.setInt(1, 1);
                ps.setString(2, studentID);
                ps.execute();
                ps.closeOnCompletion();
            }
        }else{
            this.dispose();
        }
    }

    private void loadDataFromExportedFiles() throws Exception {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose Data File");
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
        fileChooser.setMultiSelectionEnabled(false);
        int response = fileChooser.showOpenDialog(null);

        ArrayList<Student> students = new ArrayList<>();
        HashMap<String, Integer> zoneDetails = new HashMap<>();

        if (response == APPROVE_OPTION) {
            setAlwaysOnTop(true);


            Log.print("Loading Data From Files");
            Scanner fromFile = new Scanner(fileChooser.getSelectedFile());
            int numberColumns = fromFile.nextLine().split(",").length;
            while (fromFile.hasNextLine()) {
                String[] rowItems = fromFile.nextLine().split(",");
                Log.print("Student:"+Arrays.toString(rowItems));

                if (rowItems.length == numberColumns) {
                    int i = 0;

                    Student student = new Student(rowItems[i++], rowItems[i++], rowItems[i++], Integer.parseInt(rowItems[i++]), rowItems[i++],
                            rowItems[i++], rowItems[i++], rowItems[i++], rowItems[i++], rowItems[i++],
                            (stringToDate(rowItems[i++].split("/"))), new PaintField(rowItems[i++]));

                    students.add(student);

                    zoneDetails.put(student.getStudentID(), Integer.parseInt(rowItems[rowItems.length - 3]));

                } else if (rowItems.length == (numberColumns - 1)) {
                    int i = 0;
                    Student student = new Student(rowItems[i++], rowItems[i++], rowItems[i++], Integer.parseInt(rowItems[i++]), rowItems[i++],
                            rowItems[i++], rowItems[i++], rowItems[i++], rowItems[i++], rowItems[i++],
                            (stringToDate(rowItems[i++].split("/"))), new PaintField(""));

                    // process same changes in DataImporter
                    students.add(student);

                    zoneDetails.put(student.getStudentID(), Integer.parseInt(rowItems[rowItems.length - 2]));

                } else {
                    ArrayList<String> rowItemBuilder = new ArrayList<>();
                    for (int i = 0; i < numberColumns; i++) {
                        if (i < rowItems.length)
                            rowItemBuilder.add(rowItems[i]);
                        else
                            rowItemBuilder.add("");
                    }

                    try {
                        Integer.parseInt(rowItemBuilder.get(3));
                    } catch (NumberFormatException e2) {
                        rowItemBuilder.set(3, "0");
                    }

                    int i = 0;
                    Student student = new Student(rowItemBuilder.get(i++), rowItemBuilder.get(i++), rowItemBuilder.get(i++),
                            Integer.parseInt(rowItemBuilder.get(i++)), rowItemBuilder.get(i++), rowItemBuilder.get(i++),
                            rowItemBuilder.get(i++), rowItemBuilder.get(i++), rowItemBuilder.get(i++), rowItemBuilder.get(i++), null, new PaintField(rowItemBuilder.get(i++)));

                    students.add(student);
                }
            }
            fromFile.close();

            StudentDataBooter.clearAllData(CONNECTION);

            for(Student s: students) {
                Log.print("Adding Imported Student: "+s);
                StudentDataBooter.addImportedStudent(CONNECTION,
                        s.getLastName(),
                        s.getFirstName(),
                        s.getStudentID(),
                        s.getGrade(),
                        s.getCarMake(),
                        s.getCarModel(),
                        s.getCarColor(),
                        s.getCarYear(),
                        s.getLicensePlateNumber(),
                        s.getInsuranceExpiryDate(),
                        s.getStickerNumber(),
                        s.getPaintField());
            }

            Log.print("Updating Parking Spot Details");
            ParkingSpotBooter.loadData();
            for(String studentID: zoneDetails.keySet()){
                Log.print("Updating Parking Spot of: "+studentID);
                PreparedStatement ps = CONNECTION.prepareStatement("update parking_spot_data set StudentIDAssignedTo = ? where SpotNumber = ?");
                ps.setString(1, studentID);
                ps.setInt(2, zoneDetails.get(studentID));
                ps.execute();

                ps = CONNECTION.prepareStatement("update parking_spot_data set IsApproved = ? where StudentIDAssignedTo = ?");
                ps.setInt(1, 1);
                ps.setString(2, studentID);
                ps.execute();

                ps = CONNECTION.prepareStatement("update data_pool set Approved = ? where StudentID = ?");
                ps.setInt(1, 1);
                ps.setString(2, studentID);
                ps.execute();
                ps.closeOnCompletion();

            }

        }else{
            this.dispose();
        }
    }

    private java.sql.Date stringToDate(String[] dateComponents){
        Calendar cal = Calendar.getInstance();

        Log.print("Date Components:"+Arrays.toString(dateComponents));

        if(dateComponents.length != 3)
            return null;

        // set Date portion to January 1, 1970
        cal.set(cal.YEAR, Integer.parseInt(dateComponents[2]));
        cal.set(cal.MONTH, Integer.parseInt(dateComponents[0])-1);
        cal.set(cal.DATE, Integer.parseInt(dateComponents[1]));

        cal.set(cal.HOUR_OF_DAY, 0);
        cal.set(cal.MINUTE, 0);
        cal.set(cal.SECOND, 0);
        cal.set(cal.MILLISECOND, 0);

        return new java.sql.Date(cal.getTime().getTime());
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - - -
        label1 = new JLabel();
        label2 = new JLabel();
        button1 = new JButton();

        //======== this ========
        setForeground(Color.white);
        Container contentPane = getContentPane();
        contentPane.setLayout(new TableLayout(new double[][] {
            {50, TableLayout.FILL, 50},
            {30, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, 30}}));
        ((TableLayout)contentPane.getLayout()).setHGap(5);
        ((TableLayout)contentPane.getLayout()).setVGap(5);

        //---- label1 ----
        label1.setText("Please wait. The System is loading and uploading data to server.");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Cambria", Font.PLAIN, 17));
        label1.setForeground(Color.white);
        contentPane.add(label1, new TableLayoutConstraints(1, 1, 1, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label2 ----
        label2.setText("The \"Close Window\" button will activate when system is ready.");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Cambria", Font.PLAIN, 15));
        label2.setForeground(Color.white);
        contentPane.add(label2, new TableLayoutConstraints(1, 2, 1, 2, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- button1 ----
        button1.setText("Close Window");
        button1.setFont(new Font("Cambria", Font.PLAIN, 12));
        contentPane.add(button1, new TableLayoutConstraints(1, 4, 1, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - - -
    private JLabel label1;
    private JLabel label2;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
