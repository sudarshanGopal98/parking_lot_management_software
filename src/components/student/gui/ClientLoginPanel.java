package components.student.gui;

import components.misc.DatabaseBridge;
import components.misc.DatabaseVerifier;
import components.administrator.process.MessageBoxDisplay;
import main_files.ClientMain;
import structures.Colors;
import structures.Log;
import structures.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;


public class ClientLoginPanel extends JPanel{

	/**
	 *
	 */
	private static final long serialVersionUID = 6437634615486135679L;


	JTextField studentID, lastName;
	JLabel introduction, studentIDL, lastNameL, trademark;
	JButton loginButton;
	public static final Font titleFont = new Font("Cambria",Font.BOLD,25);


	
	public ClientLoginPanel(Dimension frameSize){
		setSize(frameSize);
		setLayout(null);
		setVisible(true);
        setBackground(Colors.BUTTON_COLOR_2);

        Font textFont = new Font("Cambria", Font.PLAIN, 14);

		introduction = new JLabel("Please Enter Your Student ID and Last Name");
		introduction.setBounds(getWidth()/2 - 250, getHeight()/2 - 250, getWidth(), 30);
		introduction.setForeground(Color.WHITE);
		introduction.setFont(titleFont);


		studentIDL = new JLabel("Student ID");
		studentIDL.setBounds(getWidth()/2 - 300, getHeight()/2 - 200, 300, 30);
		studentIDL.setForeground(Color.WHITE);
		studentIDL.setFont(titleFont);

		lastNameL = new JLabel("Last Name");
		lastNameL.setBounds(getWidth()/2 - 300, getHeight()/2 - 150, 300, 30);
		lastNameL.setForeground(Color.WHITE);
		lastNameL.setFont(titleFont);


		studentID = new JTextField("");
		studentID.setBounds(getWidth()/2, getHeight()/2 - 200, 300, 30);
        studentID.setFont(textFont);


		lastName = new JTextField("");
		lastName.setBounds(getWidth()/2, getHeight()/2 - 150, 300, 30);
        lastName.setFont(textFont);
		
		trademark = new JLabel("The Parking Lot Management Software by Sudarshan Gopalakrishnan");
		trademark.setBounds(0, getHeight()/2 - 300, getWidth(), 30);
		trademark.setHorizontalAlignment(JLabel.CENTER);
		trademark.setForeground(Color.WHITE);
		trademark.setFont(titleFont);


		loginButton = new JButton("Login");
		loginButton.setBounds(getWidth()/2 - 300, getHeight()/2 - 100, 600, 50);
		loginButton.setBackground(Colors.BUTTON_COLOR_1);
		loginButton.setFont(textFont);
		loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(studentID.getText().length() == 8) {
                        ArrayList<Student> students = DatabaseBridge.getAllStudents("StudentID", studentID.getText().toUpperCase());
                        if(students.size() == 1){
                            studentHandlingProcess(students);
                        }else if(students.size() == 0){
                            students = DatabaseBridge.getAllStudents("StudentID", studentID.getText().toUpperCase());
                            if(students.size() == 1){
                                studentHandlingProcess(students);
                            }else if (students.size() == 0)
                                MessageBoxDisplay.showStudentNotFoundError(ClientMain.FRAME);
                            else{
                                Log.print("Students:"+students);
                                MessageBoxDisplay.showStudentIDAmbiguityErrorMessage(ClientMain.FRAME);
                            }
                        }else {
                            Log.print("Students:"+students);
                            MessageBoxDisplay.showStudentIDAmbiguityErrorMessage(ClientMain.FRAME);
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });


        add(introduction);
		add(studentIDL);
		add(lastNameL);
		add(studentID);
		add(lastName);
		add(loginButton);
		add(trademark);
		
		repaint();
    }

    private void studentHandlingProcess(ArrayList<Student> students) throws SQLException {
        Student studentToHandle = students.get(0);

        if(DatabaseVerifier.checkIfStudentWaiting(DatabaseBridge.CONNECTION, studentID.getText())    ||
                DatabaseVerifier.checkIfStudentApproved(DatabaseBridge.CONNECTION, studentID.getText())){
            MessageBoxDisplay.showStudentAlreadyHasSpot(ClientMain.FRAME);
            return;
        }


        if(!DatabaseVerifier.checkIfStudentInRoom(DatabaseBridge.CONNECTION, studentID.getText())){
            MessageBoxDisplay.showStudentNotInRoom(ClientMain.FRAME);
            return;
        }



        if(studentToHandle.getLastName().equalsIgnoreCase(lastName.getText())){
            ClientMain.currentStudent = studentToHandle;

            ClientDataDisplaySubpanel panel = new ClientDataDisplaySubpanel();
            panel.setSize(ClientMain.FRAME.getWidth(),ClientMain.FRAME.getHeight());
            panel.displayData(ClientMain.currentStudent);

            ClientMain.FRAME.getRootPane().setDefaultButton(null);

            ClientMain.FRAME.getPanelHolder().add(panel);

        }else MessageBoxDisplay.showStudentNotFoundError(ClientMain.FRAME);
    }
}
