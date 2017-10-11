package components.administrator.process;

import components.misc.DatabaseBridge;
import components.misc.DatabaseVerifier;
import main_files.AdminMain;
import structures.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Sudarshan on 1/31/2017.
 */
public class PendingApprovalGUIHandler implements Runnable{
    ArrayList<PendingApprovalGUIPack> guiPack = new ArrayList<>();

    public PendingApprovalGUIHandler() {
        Thread t = new Thread(this);
        t.start();
        AdminMain.THREAD_MANAGER.addThread("PendingApprovalGUIHandler", t);
    }

    public void addApprovalPack(JLabel studentName, JButton approve, JButton deny){
        guiPack.add(new PendingApprovalGUIPack(studentName, approve, deny));
    }


    @Override
    public void run() {
        while(true){
            try {
                ArrayList<Student> studentsPendingApproval = DatabaseBridge.getStudentsPendingApproval();
                for(int i = 0; i<guiPack.size(); i++){
                    if(i < studentsPendingApproval.size()){
                        guiPack.get(i).updateStudent(studentsPendingApproval.get(i));
                    }else guiPack.get(i).updateStudent(null);
                }

                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class PendingApprovalGUIPack{
    Student currentStudent;
    JLabel studentName;
    JButton approve, deny;

    public PendingApprovalGUIPack(JLabel studentName, JButton approve, JButton deny) {
        this.studentName = studentName;
        this.approve = approve;
        this.deny = deny;

        approve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentStudent == null) {
                    return;
                }

                DatabaseBridge.writeUpdateQuery(0, "InRoom", currentStudent.getStudentID());
                DatabaseBridge.writeUpdateQuery(0, "WaitingForApproval", currentStudent.getStudentID());
                DatabaseBridge.writeUpdateQuery(1, "Approved", currentStudent.getStudentID());

                DatabaseBridge.writeSpotApprovalQuery(DatabaseVerifier.getStudentSpotNumber(DatabaseBridge.CONNECTION, currentStudent.getStudentID()));
            }
        });

        deny.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentStudent == null) {
                    return;
                }

                DatabaseBridge.writeUpdateQuery(0, "WaitingForApproval", currentStudent.getStudentID());
                DatabaseBridge.writeUpdateQuery(0, "Approved", currentStudent.getStudentID());

                DatabaseBridge.writeSpotDenialQuery(DatabaseVerifier.getStudentSpotNumber(DatabaseBridge.CONNECTION, currentStudent.getStudentID()));
            }
        });
    }

    public void updateStudent(Student student){
        if(student == null){
            studentName.setVisible(false);
            approve.setVisible(false);
            deny.setVisible(false);

        } else {
            studentName.setVisible(true);
            approve.setVisible(true);
            deny.setVisible(true);

            this.currentStudent = student;
            studentName.setText(student.toString());
        }

    }


}
