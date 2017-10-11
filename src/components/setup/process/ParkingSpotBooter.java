package components.setup.process;

import structures.Log;

import java.sql.*;

/**
 * Created by Sudarshan on 1/31/2017.
 */
public class ParkingSpotBooter {
    public static Connection CONNECTION;


    public static void loadData() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");                 // loads driver to connect to server
        CONNECTION = DriverManager.getConnection(
                "jdbc:mysql://localhost:2329/parkinglotmanagementsoftware","root","oths2329");         // hostname:port/database name, user name, password

        deleteAllData(CONNECTION);

        PreparedStatement ps;
        try {
            ps = CONNECTION.prepareStatement("create table parking_spot_data\n" +
                    "(SpotNumber INT(255),\n" +
                    " Zone VARCHAR(500),\n" +
                    " StudentIDAssignedTo VARCHAR(500),\n" +
                    " IsApproved TINYINT(4));");
            ps.execute();
            ps.closeOnCompletion();

        }catch (Exception e){e.printStackTrace();}

        Statement statement = CONNECTION.createStatement();
        String query = "SELECT * FROM parking_zones;";
        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next()) {
            for (int i = resultSet.getInt(3); i <= resultSet.getInt(4); i++) {
                Log.print("Adding Spot:" + (i));
                ps = CONNECTION.prepareStatement("insert into parking_spot_data\n" +
                        "(SpotNumber, Zone, IsApproved)\n" +
                        "  values (" + (i) + ", \'"+resultSet.getString(2)+"\', '0');");
                ps.execute();
                ps.closeOnCompletion();

            }
        }

        statement.closeOnCompletion();
        resultSet.close();
    }


    public static void deleteAllData(Connection connection) throws SQLException {
        PreparedStatement ps=connection.prepareStatement("drop table if exists parking_spot_data;");
        ps.execute();
        ps.closeOnCompletion();

    }

}
