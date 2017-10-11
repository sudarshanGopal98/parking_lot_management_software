package components.administrator.process;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sudarshan on 1/31/2017.
 */
public class MessageBoxDisplay {
    public static void showDatabaseErrorMessage(Component parent){
        JOptionPane.showMessageDialog(parent, "Sorry, we have encountered an error \n" +
                "contacting the database. Please retry.","Error",JOptionPane.ERROR_MESSAGE);
    }

    public static void showDataMismatchError(Component parent){
        JOptionPane.showMessageDialog(parent, "Sorry, the data file you have uploaded is incorrect. Please use a different file.","Error",JOptionPane.ERROR_MESSAGE);
    }

    public static void showDataIncompleteError(Component parent){
        JOptionPane.showMessageDialog(parent, "Sorry, you have not completed \n" +
                "all the required information. Please retry.","Error",JOptionPane.ERROR_MESSAGE);
    }

    public static void showStudentAlreadyHasSpot(Component parent){
        JOptionPane.showMessageDialog(parent, "Sorry, it seems that you have \n" +
                "already chosen a spot. Please contact the administrator.","Error",JOptionPane.ERROR_MESSAGE);
    }

    public static void showStudentIDAmbiguityErrorMessage(Component parent){
        JOptionPane.showMessageDialog(parent, "Sorry, there are multiple entries of the entered Student ID.\n" +
                "Please contact administrator.","Error",JOptionPane.ERROR_MESSAGE);
    }

    public static void showStudentAddedMessage(Component parent){
        JOptionPane.showMessageDialog(parent, "The new student has been added to the system!\n" +
                "If you are trying to check-in student OR adding the student to map, you may do so now.","Error",JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showStudentAlreadyExistsMessage(Component parent){
        JOptionPane.showMessageDialog(parent, "Sorry, we already have a student with the same Student ID!","Error",JOptionPane.ERROR_MESSAGE);
    }

    public static void showStudentCredentialError(Component parent){
        JOptionPane.showMessageDialog(parent, "Sorry, your Student ID and Last Name do not match the system!","Error",JOptionPane.ERROR_MESSAGE);
    }

    public static void showStudentNotFoundError(Component parent){
        JOptionPane.showMessageDialog(parent, "Sorry, this Student ID does not exist in our system!","Error",JOptionPane.ERROR_MESSAGE);
    }

    public static void showStudentNotFoundError(Component parent, String studentID){
        JOptionPane.showMessageDialog(parent, "The Student ID is invalid. There is no student with the entered ID.","Student Does Not Exist",JOptionPane.ERROR_MESSAGE);
    }


    public static void showStudentNotInRoom(Component parent){
        JOptionPane.showMessageDialog(parent, "Sorry, you are not checked into the system!","Error",JOptionPane.ERROR_MESSAGE);
    }

}
