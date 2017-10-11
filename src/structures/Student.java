package structures;

import components.misc.DatabaseBridge;
import components.misc.DatabaseVerifier;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Sudarshan on 3/22/2016.
 */
public class Student implements Comparable{
    public static final String STUDENT_EXPORT_HEADER = "Last Name, First Name, Student ID, Grade, " +
            "Car Make, Car Model, Car Color, Car Year, License Plate Number, " +
            "Sticker Number, Insurance Expiry Date, Student Spot Number, Student Spot Zone, Paint Field (if applicable)";

    private String firstName;
    private String lastName;
    private String studentID;
    private int grade;

    private String carMake;
    private String carModel;
    private String carColor;
    private String carYear;
    private String licensePlateNumber;

    private String stickerNumber;

    private PaintField paintField;

    private Date insuranceExpiryDate;

//    private boolean isInRoom=false;
//    private boolean isWaiting=false;
//    private boolean isApproved=false;


    public Student(String lastName, String firstName, String studentID, int grade,
                   String carMake, String carModel, String carColor, String carYear, String licensePlateNumber,
                   String stickerNumber, Date insuranceExpiryDate, PaintField paintField) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.studentID = studentID;
        this.grade = grade;


        this.carMake = carMake;
        this.carModel = carModel;
        this.carColor = carColor;
        this.carYear = carYear;
        this.licensePlateNumber = licensePlateNumber;


        this.stickerNumber = stickerNumber;
        this.paintField = paintField;
        this.insuranceExpiryDate = insuranceExpiryDate;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStudentID() {
        return studentID;
    }

    public int getGrade() {
        return grade;
    }

    public String getCarMake() {
        return carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarColor() {
        return carColor;
    }

    public String getCarYear() {
        return carYear;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public String getStickerNumber() {
        return stickerNumber;
    }

    public PaintField getPaintField() {
        return paintField;
    }

    public Date getInsuranceExpiryDate() {
        return insuranceExpiryDate;
    }

    public String getInsuranceExpiryDay (){
        if(insuranceExpiryDate == null)
            return "";

        DateFormat dateFormat = new SimpleDateFormat("dd");
        return dateFormat.format(insuranceExpiryDate);
    }

    public String getInsuranceExpiryMonth(){
        if(insuranceExpiryDate == null)
            return "";

        DateFormat dateFormat = new SimpleDateFormat("MM");
        return dateFormat.format(insuranceExpiryDate);
    }

    public String getInsuranceExpiryYear(){
        if(insuranceExpiryDate == null)
            return "";

        DateFormat dateFormat = new SimpleDateFormat("YYYY");
        return dateFormat.format(insuranceExpiryDate);
    }

    public String getStudentStatus(){
        try {
            if (DatabaseVerifier.checkIfStudentApproved(DatabaseBridge.CONNECTION, studentID))
                return "The Student is Approved";
            if (DatabaseVerifier.checkIfStudentWaiting(DatabaseBridge.CONNECTION, studentID))
                return "The Student is Waiting for Approval";
            if (DatabaseVerifier.checkIfStudentInRoom(DatabaseBridge.CONNECTION, studentID))
                return "The Student has Checked In";
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "The Student has not started the process";
    }

    @Override
    public String toString(){
        return lastName+", "+firstName+"  ["+studentID+"]";
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Student) {
            Student compared = (Student) (o);
            return lastName.compareTo(compared.getLastName());
        } else return 0;
    }

    public String getExportString() throws SQLException {
        String date = "";
        if(insuranceExpiryDate != null) {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            date = df.format(insuranceExpiryDate);
        }

        String toReturn = "";
        toReturn += lastName + ",";
        toReturn += firstName + ",";
        toReturn += studentID + ",";
        toReturn += grade + ",";
        toReturn += carMake + ",";
        toReturn += carModel + ",";
        toReturn += carColor + ",";
        toReturn += carYear + ",";
        toReturn += licensePlateNumber + ",";
        toReturn += stickerNumber + ",";
        toReturn += date + ",";

        if(DatabaseVerifier.getStudentSpotNumber(DatabaseBridge.CONNECTION, studentID) > 0) {
            toReturn += DatabaseVerifier.getStudentSpotNumber(DatabaseBridge.CONNECTION, studentID) + ",";

            toReturn += DatabaseVerifier.convertSpotToZone(DatabaseVerifier.getStudentSpotNumber(DatabaseBridge.CONNECTION, studentID))+ ",";

        }else toReturn += ",,";

        toReturn += paintField;

        return toReturn;
    }


}
