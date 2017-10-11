package components.setup.process;

import components.administrator.process.MessageBoxDisplay;
import components.misc.DatabaseVerifier;
import structures.Log;
import structures.PaintField;
import structures.Student;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static javax.swing.JFileChooser.APPROVE_OPTION;

/**
 * Created by Sudarshan on 1/25/2017.
 */
public class StudentDataBooter {
    public static Connection CONNECTION;

    public static void loadData() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");                 // loads driver to connect to server
        StudentDataBooter.CONNECTION =DriverManager.getConnection(
        "jdbc:mysql://localhost:2329/parkinglotmanagementsoftware","root","oths2329");         // hostname:port/database name, user name, password

        deleteAllData(CONNECTION);

        PreparedStatement ps;
        try {
            ps = CONNECTION.prepareStatement("create table data_pool\n" +
                    "(" +
                    " LastName  VARCHAR(500),\n" +
                    " FirstName VARCHAR(500),\n" +
                    " StudentID VARCHAR(500),\n" +
                    " Grade INT(11),\n" +
                    " CarMake VARCHAR(500),\n" +
                    " CarModel VARCHAR(500),\n" +
                    " CarColor VARCHAR(500),\n" +
                    " CarYear VARCHAR(500),\n" +
                    " LicensePlateNumber VARCHAR(500),\n" +
                    " StickerNumber VARCHAR(500),\n" +
                    " InsuranceExpiry DATE,\n" +
                    " PaintField VARCHAR(500),\n" +
                    " InRoom TINYINT(4),\n" +
                    " WaitingForApproval TINYINT(4),\n" +
                    " Approved TINYINT(4));");
            ps.execute();
            ps.closeOnCompletion();

        }catch (Exception e){e.printStackTrace();}

        ArrayList<Student> students = loadStudentsFromFile();
        for(Student s: students) {
            Log.print(s);

            addImportedStudent(CONNECTION, s.getLastName(), s.getFirstName(), s.getStudentID(), s.getGrade(), s.getCarMake(), s.getCarModel(), s.getCarColor(), s.getCarYear(), s.getLicensePlateNumber(), s.getInsuranceExpiryDate(), s.getStickerNumber(), s.getPaintField());
        }

        ParkingSpotBooter.loadData();
    }

    public static ArrayList<Student> loadStudentsFromFile(){
        ArrayList<Student> students = new ArrayList<>();

        try{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose Data File Location");
            fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
            fileChooser.setFileFilter(new FileNameExtensionFilter("CSV File","csv"));
            fileChooser.setMultiSelectionEnabled(false);
            if(fileChooser.showOpenDialog(null) == APPROVE_OPTION) {
                Scanner fromFile = new Scanner(fileChooser.getSelectedFile());
                int numberColumns = fromFile.nextLine().split(",").length;
                while (fromFile.hasNextLine()) {
                    String[] rowItems = fromFile.nextLine().split(",");
                    if((rowItems.length == numberColumns)   &&  (numberColumns == 12)){
                        int i = 0;
                        Student student = new Student(rowItems[i++], rowItems[i++], rowItems[i++], Integer.parseInt(rowItems[i++]), rowItems[i++],
                                rowItems[i++], rowItems[i++], rowItems[i++], rowItems[i++], rowItems[i++],
                                new java.sql.Date(Long.parseLong(rowItems[i++])), new PaintField(rowItems[i++]));

                        students.add(student);
                    }else if((rowItems.length == numberColumns)   &&  (numberColumns == 4)){
                        int i = 0;
                        Student student = new Student(rowItems[i++], rowItems[i++], rowItems[i++], Integer.parseInt(rowItems[i++]),
                                "", "", "", "", "", "",
                                null, new PaintField(""));

                        students.add(student);
                    }else if(rowItems.length == (numberColumns - 1)){
                        int i = 0;
                        Student student = new Student(rowItems[i++], rowItems[i++], rowItems[i++], Integer.parseInt(rowItems[i++]), rowItems[i++],
                                rowItems[i++], rowItems[i++], rowItems[i++], rowItems[i++], rowItems[i++],
                                new java.sql.Date(Long.parseLong(rowItems[i++])), new PaintField(""));

                        students.add(student);
                    }else{
                        MessageBoxDisplay.showDataMismatchError(null);
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "After this window closes, please restart the setup process.");
                System.exit(0);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return students;
    }
    public static final void addStudentToDatabase(Connection connection, String ln, String fn, String studentID, int grade) throws Exception{

        if(DatabaseVerifier.checkStudentExists(connection, studentID)) {
            return;
        }

        PreparedStatement ps=connection.prepareStatement("insert into data_pool (LastName,FirstName,StudentID,Grade) values (?, ?, ?, ?);");
        ps.setString(1, ln);
        ps.setString(2, fn);
        ps.setString(3, studentID);
        ps.setInt(4, grade);
        ps.execute();
        ps.closeOnCompletion();
    }


    public static final void addImportedStudent(Connection connection, String ln, String fn, String studentID, int grade,
                                                String make, String model, String color, String year, String license,
                                                Date insurance, String stickerNumber, PaintField paintField) throws Exception{

        if(DatabaseVerifier.checkStudentExists(connection, studentID)) {
            return;
        }

        PreparedStatement ps=connection.prepareStatement("insert into data_pool (LastName,FirstName,StudentID,Grade,CarMake,CarModel,CarColor,CarYear,LicensePlateNumber,InsuranceExpiry,PaintField,StickerNumber) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        ps.setString(1, ln);
        ps.setString(2, fn);
        ps.setString(3, studentID);
        ps.setInt(4, grade);
        ps.setString(5, make);
        ps.setString(6, model);
        ps.setString(7, color);
        ps.setString(8, year);
        ps.setString(9, license);
        ps.setDate(10, insurance);
        ps.setString(11, paintField.getFieldData());
        ps.setString(12, stickerNumber);
        ps.execute();
        ps.closeOnCompletion();
    }

    public static void deleteAllData(Connection connection) throws SQLException {
        PreparedStatement ps=connection.prepareStatement("drop table if exists data_pool;");
        ps.execute();
        ps.closeOnCompletion();
    }

    public static void clearAllData(Connection connection) throws SQLException {
        PreparedStatement ps=connection.prepareStatement("delete from data_pool;");
        ps.execute();
        ps.closeOnCompletion();
    }
}
