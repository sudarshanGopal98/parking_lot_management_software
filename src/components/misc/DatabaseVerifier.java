package components.misc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static components.misc.DatabaseBridge.CONNECTION;

/**
 * Created by Sudarshan on 1/26/2017.
 */
public class DatabaseVerifier {
    public static final boolean checkStudentExists(Connection connection, String studentID) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "SELECT StudentID FROM data_pool";

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            if(resultSet.getString(1).equalsIgnoreCase(studentID)) {
                resultSet.close();
                return true;
            }
        }

        resultSet.close();
        return false;
    }

    public static final boolean checkStudentExists(Connection connection, String lastName, String firstName) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "SELECT (LastName,FirstName) FROM data_pool";

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            if(resultSet.getString(1).equalsIgnoreCase(firstName) &&  resultSet.getString(2).equalsIgnoreCase(lastName))
                return true;
        }

        return false;
    }

    public static final boolean checkStudentExists(Connection connection, String lastName, String firstName, String studentID) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "SELECT (LastName,FirstName,StudentID) FROM data_pool";

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            if(resultSet.getString(1).equalsIgnoreCase(firstName) &&  resultSet.getString(2).equalsIgnoreCase(lastName)   &&  resultSet.getString(3).equalsIgnoreCase(studentID))
                return true;
        }

        return false;
    }

    public static final boolean checkIfStudentInRoom(Connection connection, String studentID) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "SELECT StudentID FROM data_pool\n" +
                "where InRoom = '1'";

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            if(resultSet.getString(1).equalsIgnoreCase(studentID)) {
                statement.closeOnCompletion();
                resultSet.close();

                return true;
            }
        }
        statement.closeOnCompletion();
        resultSet.close();
        return false;
    }

    public static final boolean checkIfStudentApproved(Connection connection, String studentID) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "SELECT StudentID FROM data_pool\n" +
                "where Approved = '1'";

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            if(resultSet.getString(1).equalsIgnoreCase(studentID)) {
                statement.closeOnCompletion();
                resultSet.close();
                return true;
            }
        }
        statement.closeOnCompletion();
        resultSet.close();

        return false;
    }

    public static final boolean checkIfStudentWaiting(Connection connection, String studentID) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "SELECT StudentID FROM data_pool\n" +
                "where WaitingForApproval = '1'";

        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            if(resultSet.getString(1).equalsIgnoreCase(studentID)) {
                statement.closeOnCompletion();
                resultSet.close();

                return true;
            }
        }
        statement.closeOnCompletion();
        resultSet.close();
        return false;
    }

    public static final int getStudentSpotNumber(Connection connection, String studentID){
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT SpotNumber FROM parking_spot_data\n" +
                    "where StudentIDAssignedTo = \'" + studentID + "\'";

            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next()) {
                statement.closeOnCompletion();
                int toReturn = resultSet.getInt(1);
                resultSet.close();
                return toReturn;
            }
            else {
                statement.closeOnCompletion();
                resultSet.close();
                return -1;
            }

        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public static String convertSpotToZone(int spotNumber) throws SQLException {
        Statement statement = CONNECTION.createStatement();
        String query = "SELECT Zone FROM parking_spot_data where SpotNumber = "+spotNumber+";";

        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.next()){
            statement.closeOnCompletion();
            String toReturn = resultSet.getString(1);
            resultSet.close();
            return toReturn;
        }

        statement.closeOnCompletion();
        resultSet.close();
        return "(Error: ZoneNotFound)";
    }

}
