package structures;

import components.misc.DatabaseBridge;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Sudarshan on 1/30/2017.
 */
public class KeyboardListener implements KeyListener{
    DataDisplayInterface dataDisplaySubpanel;
    JTextField fieldUpdated = null;
    String fieldKey = "";

    public KeyboardListener(DataDisplayInterface dataDisplaySubpanel, JTextField fieldUpdated, String fieldKey) {
        this.dataDisplaySubpanel = dataDisplaySubpanel;
        this.fieldUpdated = fieldUpdated;
        this.fieldKey = fieldKey;
    }

    @Override
    public void keyTyped(KeyEvent e) {return;}

    @Override
    public void keyPressed(KeyEvent e) {return;}

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            DatabaseBridge.writeUpdateQuery(fieldUpdated.getText(), fieldKey, dataDisplaySubpanel.getCurrentStudent().getStudentID());
        }catch (NullPointerException e2){dataDisplaySubpanel.clearData();}
    }
}
