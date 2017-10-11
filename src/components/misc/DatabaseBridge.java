package components.misc;

import structures.Log;
import structures.PaintField;
import structures.Student;
import structures.Zone;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Sudarshan on 1/30/2017.
 */
public class DatabaseBridge {

    public static Connection CONNECTION;

//    Receiving Functions
    public static final ArrayList<Student> getAllStudents(){
        ArrayList<Student> students = new ArrayList<>();
        try {
            ResultSet rs = null;
            Statement stmt = CONNECTION.createStatement();

            rs = stmt.executeQuery("select * from data_pool");


            if(rs == null)
                return null;

            while (rs.next()) {
                Student s = new Student(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getDate(11), new PaintField(rs.getString(12)));

                students.add(s);
            }
            stmt.closeOnCompletion();
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            return students;
        }
    }

    public static final ArrayList<Student> getAllStudents(String keyToFilter, String filter){
        ArrayList<Student> students = new ArrayList<>();
        try {
            ResultSet rs = null;
            Statement stmt = CONNECTION.createStatement();
            if(keyToFilter != null  &&  (filter  != null    &&  filter.trim().length()> 0)){
                rs = stmt.executeQuery("select * from data_pool\n" +
                        "where "+keyToFilter+" like \'"+filter+"%';");
            }else {
                rs = stmt.executeQuery("select * from data_pool");
            }

            if(rs == null)
                return null;

            while (rs.next()) {
                Student s = new Student(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getDate(11), new PaintField(rs.getString(12)));

                students.add(s);
            }
            stmt.closeOnCompletion();
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            return students;
        }
    }

    public static final ArrayList<Student> getAllStudents(String keyToFilter, int filter){
        ArrayList<Student> students = new ArrayList<>();
        try {
            ResultSet rs = null;
            Statement stmt = CONNECTION.createStatement();
            if(keyToFilter != null){
                rs = stmt.executeQuery("select * from data_pool\n" +
                        "where "+keyToFilter+" like \'"+filter+"%';");
            }else {
                rs = stmt.executeQuery("select * from data_pool");
            }

            if(rs == null)
                return null;

            while (rs.next()) {
                Student s = new Student(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getDate(11), new PaintField(rs.getString(12)));

                students.add(s);
            }
            stmt.closeOnCompletion();
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            return students;
        }
    }

    public static final ArrayList<Student> getAllStudents(Object[] keysToFilter, Object[] filters){
        ArrayList<Student> students = new ArrayList<>();
        try {
            ResultSet rs = null;
            Statement stmt = CONNECTION.createStatement();
            String query = "select * from data_pool where\n";
            for(int i = 0; i<filters.length; i++) {
                query += keysToFilter[i]+" like \'"+filters[i]+"%'";
                if(i != (filters.length - 1)){
                    query += " AND ";
                }
            }
//            Log.print(query);
            rs = stmt.executeQuery(query+";");

            if(rs == null)
                return null;

            while (rs.next()) {
                Student s = new Student(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getDate(11), new PaintField(rs.getString(12)));

                students.add(s);
            }
            stmt.closeOnCompletion();
            rs.close();
        }catch (SQLException e){
//            e.printStackTrace();
        } finally {
            return students;
        }
    }

    public static final ArrayList<Student> getStudentInRoom(){
        ArrayList<Student> students = new ArrayList<>();
        try {
            Statement stmt = CONNECTION.createStatement();
            ResultSet rs = stmt.executeQuery("select * from data_pool where inRoom = 1");

            while (rs.next()) {
                Student s = new Student(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getDate(11), new PaintField(rs.getString(12)));

                students.add(s);
            }
            stmt.closeOnCompletion();
            rs.close();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return students;
        }
    }

    public static final  ArrayList<Student> getStudentsPendingApproval(){
        ArrayList<Student> students = new ArrayList<>();
        try {
            Statement stmt = CONNECTION.createStatement();
            ResultSet rs = stmt.executeQuery("select * from data_pool where WaitingForApproval = 1");

            while (rs.next()) {
                Student s = new Student(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getDate(11), new PaintField(rs.getString(12)));

                students.add(s);
            }
            stmt.closeOnCompletion();
            rs.close();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return students;
        }
    }

    public static final String getZoneName(int zoneID){
        try {
            Statement stmt = CONNECTION.createStatement();
            ResultSet rs = stmt.executeQuery("select ZoneName from parking_zones where ZoneID = "+zoneID+"");

            if (rs.next())
                return rs.getString(1);
            stmt.closeOnCompletion();
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "ZoneNotFound!";
    }

    public static final int getStudentSpotNumber(Connection connection, String studentID){
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT SpotNumber FROM parking_spot_data\n" +
                    "where StudentIDAssignedTo = \'" + studentID + "\'";

            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next()) {
                statement.closeOnCompletion();
                return resultSet.getInt(1);
            }
            else return -1;

        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public static String spotToZoneConverter(int spotNumber) throws SQLException {
        Statement statement = CONNECTION.createStatement();
        String query = "SELECT Zone FROM parking_spot_data where SpotNumber = "+spotNumber+";";

        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.next()){
            statement.closeOnCompletion();
            return resultSet.getString(1);
        }

        statement.closeOnCompletion();
        resultSet.close();
        return "(Error: ZoneNotFound)";
    }

    public static final ArrayList<Student> getStudentsInZone(String zone){
        ArrayList<Student> studentInZone = new ArrayList<>();
        try {
            Statement stmt = CONNECTION.createStatement();
            ResultSet rs = stmt.executeQuery("select StudentIDAssignedTo from parking_spot_data where Zone = \'"+zone+"\'");

            while (rs.next()) {
                if(rs.getString(1) != null)
                    studentInZone.add(DatabaseBridge.getStudent(rs.getString(1)));
            }
            stmt.closeOnCompletion();
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return studentInZone;
        }
    }

    public static final Student getStudent(String studentID) throws SQLException {
        Statement stmt = CONNECTION.createStatement();
        ResultSet rs = stmt.executeQuery("select * from data_pool where StudentID = \'"+studentID+"\'");
        Student s = null;
        try {
            if(rs.next()) {
                s = new Student(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getDate(11), new PaintField(rs.getString(12)));
            }
        }catch (Exception e){ e.printStackTrace();}

        stmt.closeOnCompletion();
        rs.close();

        return s;
    }

    public static final double[] getMapZoneStatistics(String zoneName) throws SQLException {
        Statement statement = CONNECTION.createStatement();
        String query = "select * from parking_spot_data where Zone = \'"+zoneName+"\';";
        ResultSet resultSet = statement.executeQuery(query);

        double spotsAlloted = 0;
        double spotsBlocked = 0;
        double spotsAvailable = 0;
        double totalSpots = 0;
        double percentageFull = 0;
        double percentageAvailable = 0;

        while(resultSet.next()){
            if((resultSet.getString(3)!= null   &&  resultSet.getString(3).length() > 0)  &&
                resultSet.getInt(4) == 1){
                spotsAlloted++;
            } else if((resultSet.getString(3)!= null   &&  resultSet.getString(3).length() > 0)  &&
                    resultSet.getInt(4) == 0){
                spotsBlocked++;
            } else spotsAvailable++;
        }

        totalSpots = spotsAlloted + spotsBlocked + spotsAvailable;
        percentageAvailable = (spotsAvailable / totalSpots)*100;
        percentageFull = 100 - percentageAvailable;

        statement.closeOnCompletion();
        resultSet.close();

        return new double[]{spotsAlloted, spotsBlocked, spotsAvailable, totalSpots, percentageFull, percentageAvailable};
    }

    public static final ArrayList<Zone> getZonePolygonInformation() throws SQLException {
        ArrayList<Zone> zones = new ArrayList<>();
        Statement stmt = CONNECTION.createStatement();
        ResultSet rs = stmt.executeQuery("select * from parking_zones");
        try {
            while(rs.next()){
                Polygon zoneShape = new Polygon();

                String xPointsTemp = rs.getString(5).trim();
                String[] xPoints = xPointsTemp.substring(1, xPointsTemp.length()-1).split(",");

                String yPointsTemp = rs.getString(6).trim();
                String[] yPoints = yPointsTemp.substring(1, yPointsTemp.length()-1).split(",");

                for(int i=0; i<yPoints.length; i++){
                    zoneShape.addPoint(Integer.parseInt(xPoints[i].trim()),    Integer.parseInt(yPoints[i].trim()));
                }

                Zone zone = new Zone(rs.getInt(1), zoneShape, rs.getString(2),
                        rs.getInt(3), rs.getInt(4));
                zones.add(zone);
            }
        }catch (Exception e){ e.printStackTrace();}

        stmt.closeOnCompletion();
        rs.close();

        return zones;
    }



//    Sending Functions - Student Data Table
    public static final void writeUpdateQuery(String textToWrite, String fieldToUpdate, String studentID) {
        PreparedStatement ps = null;
        try {

            ps = CONNECTION.prepareStatement("update data_pool\n" +
                    "set "+fieldToUpdate+" = \'"+textToWrite+"\' \n" +
                    "where studentID = \'"+studentID+"\';");

            ps.execute();
            ps.closeOnCompletion();

        } catch (SQLException e) {
            Log.print("Error when writing update query");
            e.printStackTrace();
        }
    }

    public static final void writeUpdateQuery(int gradeToWrite, String studentID) {
        PreparedStatement ps = null;
        try {
            ps = CONNECTION.prepareStatement("update data_pool\n" +
                    "set "+"grade"+" = "+gradeToWrite+"\n" +
                    "where StudentID = \'"+studentID+"\';");

            ps.execute();
            ps.closeOnCompletion();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static final void writeUpdateQuery(Date dateToWrite, String studentID) {
        PreparedStatement ps = null;
        try {

            ps = CONNECTION.prepareStatement("update data_pool\n" +
                    "set "+"InsuranceExpiry"+" = \'"+dateToWrite+"\' \n" +
                    "where studentID = \'"+studentID+"\';");

            ps.execute();
            ps.closeOnCompletion();

        } catch (SQLException e) {
            Log.print("Error when writing update query");
            e.printStackTrace();
        }
    }

    public static final void writeUpdateQuery(int trueOrFalse, String fieldToUpdate, String studentID) {
        PreparedStatement ps = null;
        try {
            ps = CONNECTION.prepareStatement("update data_pool\n" +
                    "set "+fieldToUpdate+" = "+trueOrFalse+"\n" +
                    "where StudentID = \'"+studentID+"\';");

            ps.execute();
            ps.closeOnCompletion();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static final void checkInStudent(String studentID) throws SQLException {
        PreparedStatement ps = CONNECTION.prepareStatement("update data_pool\n" +
                                                                "set InRoom = '1'\n" +
                                                                "where StudentID = \'"+studentID+"\';");
        ps.execute();
        ps.closeOnCompletion();
    }

    public static final boolean addStudentToDatabase(Connection connection, String ln, String fn, String studentID, int grade) throws SQLException{

        if(DatabaseVerifier.checkStudentExists(connection, studentID)) {
            return false;
        }

        PreparedStatement ps=connection.prepareStatement("insert into data_pool (LastName,FirstName,StudentID,Grade) values (?, ?, ?, ?);");
        ps.setString(1, ln);
        ps.setString(2, fn);
        ps.setString(3, studentID);
        ps.setInt(4, grade);
        ps.execute();
        ps.closeOnCompletion();
        return true;
    }

    public static final void addImportedStudent(Connection connection, String ln, String fn, String studentID, int grade,  String make, String model, String color, String year, String license,  Date insurance, String paintField, String stickerNumber) throws Exception{

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
        ps.setString(11, paintField);
        ps.setString(12, stickerNumber);
        ps.execute();
        ps.closeOnCompletion();
    }


//    Sending Functions - Parking Lot Table
    public static final boolean writeParkingSpotRequest(String studentID, String zoneName){
        try {
            Statement statement = DatabaseBridge.CONNECTION.createStatement();
            String query = "SELECT * FROM parking_spot_data\n" +
                    "where Zone = \'" + zoneName + "\';";

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                if (resultSet.getString(3) == null  ||  resultSet.getString(3).length() == 0) {
                    int spotNumber = resultSet.getInt(1);
                    PreparedStatement ps = DatabaseBridge.CONNECTION.prepareStatement("UPDATE parking_spot_data\n" +
                            "set StudentIDAssignedTo = \'" + studentID + "\'" +
                            "where SpotNumber = " + spotNumber + ";");
                    ps.execute();

                    ps = DatabaseBridge.CONNECTION.prepareStatement("UPDATE data_pool\n" +
                            "set WaitingForApproval = \'" + 1 + "\'" +
                            "where StudentID = \'" + studentID + "\';");
                    ps.execute();

                    ps.closeOnCompletion();


                    return true;
                }
            }

            statement.closeOnCompletion();
            resultSet.close();

            return false;
        }catch (Exception e){ e.printStackTrace(); return false;}

    }

    public static final void writeSpotApprovalQuery(int spotNumber) {
        PreparedStatement ps = null;
        try {
            ps = CONNECTION.prepareStatement("update parking_spot_data\n" +
                    "set "+"IsApproved"+" = "+"1"+"\n" +
                    "where SpotNumber = "+spotNumber+";");

            ps.execute();
            ps.closeOnCompletion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static final boolean writeSpotDenialQuery(int spotNumber){
       PreparedStatement ps = null;
        try {
            ps = CONNECTION.prepareStatement("update parking_spot_data\n" +
                    "set StudentIDAssignedTo = \'\'\n" +
                    "where SpotNumber = "+spotNumber+";");

            ps.execute();

            ps = CONNECTION.prepareStatement("update parking_spot_data\n" +
                    "set "+"IsApproved"+" = "+"0"+"\n" +
                    "where SpotNumber = "+spotNumber+";");

            ps.execute();
            ps.closeOnCompletion();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static final void setUpParkingZones(ArrayList<Zone> parkingZones) throws SQLException {
        PreparedStatement ps = CONNECTION.prepareStatement("drop table if exists parking_zones;");
        ps.execute();
        ps = CONNECTION.prepareStatement("create table parking_zones\n" +
                "(ZoneID INT(255),\n" +
                " ZoneName VARCHAR(500),\n" +
                " startSpot INT(255),\n" +
                " endSpot INT(255),\n" +
                " xPoints VARCHAR(1000), \n" +
                " yPoints VARCHAR(1000));");
        ps.execute();

        int zoneIDCount = 1;
        for(Zone zone: parkingZones){
            ps = CONNECTION.prepareStatement("insert into parking_zones" +
                    "(ZoneID, ZoneName, startSpot, endSpot, xPoints, yPoints) " +
                    "values ("+(zoneIDCount++)+",\'"+zone.getName()+"\', "+zone.getStartIndex()+", "+zone.getEndIndex()+", "+
                    "\'"+Arrays.toString(zone.getShape().xpoints)+"\'"+", "+"\'"+Arrays.toString(zone.getShape().ypoints)+"\'"+");");
            ps.execute();
        }

        ps.closeOnCompletion();
    }


//      Helper Methods
    public static void initDatabaseConnection(){
        try {
            Scanner fromFile = new Scanner(new File("data/serverIPAddress.plp"));
            String serverIPAddress = fromFile.nextLine();
            Class.forName("com.mysql.jdbc.Driver");                 // loads driver to connect to server
            DatabaseBridge.CONNECTION = DriverManager.getConnection("jdbc:mysql://" + serverIPAddress + ":3306/parkinglotmanagementsoftware", "root", "oths2329");         // hostname:port/database name, user name, password

            return;

        }catch (Exception e1) {
            e1.printStackTrace();
        }

        try{
            String serverIPAddress = JOptionPane.showInputDialog("Enter Server IP Address:");

            Class.forName("com.mysql.jdbc.Driver");                 // loads driver to connect to server
            DatabaseBridge.CONNECTION = DriverManager.getConnection("jdbc:mysql://" + serverIPAddress + ":2329/parkinglotmanagementsoftware", "root", "oths2329");         // hostname:port/database name, user name, password

        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "System Cannot Connect to Database!\nSystem shutting down.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(0);
        }
    }


}
