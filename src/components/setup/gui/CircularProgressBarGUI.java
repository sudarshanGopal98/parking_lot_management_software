package components.setup.gui;

/**
 * Created by Sudarshan on 2/23/2017.
 */
import components.misc.CircularProgressBar;
import main_files.AdminMain;
import main_files.SetupMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CircularProgressBarGUI {
    Timer progressBarTimer;
    JProgressBar progress;
    JFrame frameOverlay;

    public CircularProgressBarGUI(JFrame frameOverlay) {
        this.frameOverlay = frameOverlay;
    }

    public JComponent makeUI() {
        progress = new JProgressBar();
        // use JProgressBar#setUI(...) method
        progress.setUI(new CircularProgressBar());
        progress.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        progress.setStringPainted(true);
        progress.setFont(progress.getFont().deriveFont(24f));
        progress.setForeground(Color.ORANGE);

        progressBarTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int iv = Math.min(100, progress.getValue() + 1);

                if((SetupMain.FRAME != null && SetupMain.FRAME.getState() != JFrame.ICONIFIED)  ||
                        (AdminMain.FRAME != null    && AdminMain.FRAME.getState() != JFrame.ICONIFIED))
                    frameOverlay.requestFocus();
                progress.setValue(iv);
                if(progress.getValue() == 100)
                    progress.setValue(0);
            }
        });

        progressBarTimer.start();

        JPanel p = new JPanel();
        p.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
        p.add(progress);
        return p;
    }

    public Timer getProgressBarTimer() {
        return progressBarTimer;
    }

    public JProgressBar getProgress() {
        return progress;
    }
}