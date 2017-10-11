package components.misc;


import structures.Colors;

import javax.swing.*;
import java.awt.*;

public class SlideContainer extends JLayeredPane {
    private static final int PREF_W = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int PREF_H = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final int SLIDE_DELAY = 1;
    protected static final int DELTA_Y = 50;
    Component oldComponent;

    public SlideContainer() {
        setBackground(Colors.BUTTON_COLOR_2);
        setLayout(null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    @Override
    public Component add(Component comp) {
        this.removeAll();
        return add(comp, 0, 0);
    }

    public Component add(Component comp, int xLoc, int yLoc) {
        this.removeAll();
        /*Component[] comps = getComponents();
        if (comps.length > 0) {
            oldComponent = comps[0];
        }
        if (oldComponent == comp) {
            return super.add(comp);
        }
        if (oldComponent != null) {
            putLayer((JComponent) oldComponent, JLayeredPane.DEFAULT_LAYER);
        }
        Component returnResult = super.add(comp);
        putLayer((JComponent) comp, JLayeredPane.DRAG_LAYER);
        comp.setSize(getPreferredSize());
        comp.setVisible(true);
        slideFromRight(comp, oldComponent, xLoc, yLoc);
        return returnResult;*/

        comp.setLocation(xLoc, yLoc+ 25);
        return super.add(comp);
    }

    /*private void slideFromRight(final Component comp,
                                final Component oldComponent2, final int xLoc, final int yLoc) {
        new Timer(SLIDE_DELAY, new ActionListener() {
            int y = comp.getHeight();

            @Override
            public void actionPerformed(ActionEvent aEvt) {


                if (y <= 0) {
                    comp.setLocation(xLoc, yLoc);
                    putLayer((JComponent) comp, JLayeredPane.DEFAULT_LAYER);
                    if (oldComponent2 != null) {
                        remove(oldComponent2);
                    }
                    ((Timer) aEvt.getSource()).stop();
                } else {
                    y -= DELTA_Y;
                    comp.setLocation(0, y);
                }
                repaint();
            }
        }).start();
    }*/
}