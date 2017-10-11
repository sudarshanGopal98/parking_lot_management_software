package structures;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Sudarshan on 1/28/2017.
 */
abstract public class TapListener implements KeyListener {
    public void keyTyped(KeyEvent e) {
        tap(e);
    }

    public void keyPressed(KeyEvent e) {
        tap(e);
    }

    public void keyReleased(KeyEvent e) {
        tap(e);
    }

    abstract public void tap(KeyEvent e);
}
