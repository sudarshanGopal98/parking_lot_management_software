/*
 * Created by JFormDesigner on Sun Apr 17 17:20:40 CDT 2016
 */

package components.student.gui;

import javax.swing.*;
import java.awt.*;

/**
 * @author Sudarshan Gopalakrishnan
 */
public class WaitingFrame extends JFrame{
    WaitingPanel panel;

    public WaitingFrame(String studentID){
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setUndecorated(true);
        setBackground(new Color(0, 255, 0, 0));

        setContentPane(new ContentPane());
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);

        panel = new WaitingPanel(studentID, this);
        panel.setSize(600, 225);
        panel.setLocation(getWidth()/2 - panel.getWidth()/2, getHeight()/2 - panel.getHeight()/2);
        getContentPane().add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }
}

class ContentPane extends JPanel {

    public ContentPane() {

        setOpaque(false);

    }

    @Override
    protected void paintComponent(Graphics g) {

        // Allow super to paint
        super.paintComponent(g);

        // Apply our own painting effect
        Graphics2D g2d = (Graphics2D) g.create();
        // 50% transparent Alpha
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

        g2d.setColor(getBackground());
        g2d.fill(getBounds());

        g2d.dispose();

    }

}
