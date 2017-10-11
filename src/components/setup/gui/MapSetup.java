package components.setup.gui;

import components.misc.DatabaseBridge;
import main_files.SetupMain;
import structures.Colors;
import structures.Log;
import structures.Zone;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Sudarshan on 2/10/2017.
 */
public class MapSetup extends JPanel implements Runnable, MouseListener{
    int zoneCount;
    int currentZone = 1;
    BufferedImage mapImage;
    JButton nextZoneButton, help;

    Polygon zonePolygon;
    ArrayList<Zone> zoneDefinitions;

    String message = "";


    public MapSetup(final int zoneCount, File mapDestination) throws IOException {
        this.zoneCount = zoneCount;
        this.mapImage = ImageIO.read(mapDestination);
        Toolkit tk = this.getToolkit();
        setSize(tk.getScreenSize());
        setLayout(null);
        zoneDefinitions = new ArrayList<>();

        help = new JButton("Help!");
        help.setBounds(getWidth()-100, 50, 100, 40);
        help.setForeground(Color.white);
        help.setBackground(Colors.BUTTON_COLOR_1);
        help.setFont(new Font("Cambria", Font.BOLD, 15));
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MapSetup.this, "To set up a zone boundary,\n" +
                        "Using the Left Mouse Button, click around the area that you would like;\n" +
                        "      After you select three points, the system will start drawing\n" +
                        "      a yellow image to denote the selected area.\n\n" +
                        "Click on the Button 'Set Up This Zone and Next' and Fill in Details.\n\n" +
                        "NOTE: If you made an error, click the Right Mouse Button to reset\n" +
                        "the CURRENT zone's boundaries.");
            }
        });
        add(help);

        nextZoneButton = new JButton("Set Up This Zone and Next");
        if(currentZone == zoneCount)
            nextZoneButton.setText("Set Up This Zone and Finish Map Setup!");
        nextZoneButton.setBounds(getWidth() - getWidth()/5 - 100, 50, getWidth()/5,40);
        nextZoneButton.setForeground(Color.white);
        nextZoneButton.setBackground(Colors.TEXT_TOOLBAR_COLOR);
        nextZoneButton.setFont(new Font("Cambria", Font.BOLD, 15));
        nextZoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField zoneName = new JTextField();
                JTextField startIndex = new JTextField();
                JTextField endIndex = new JTextField();
                Object[] message = {
                        "Zone Name:", zoneName,
                        "Starting Spot Number:", startIndex,
                        "Ending Spot Number", endIndex
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Enter Details About New Zone", JOptionPane.OK_CANCEL_OPTION);

                if(option == JOptionPane.OK_OPTION) {
                    currentZone++;
                    Zone parkingZone = new Zone(currentZone, new Polygon(arrayValueSubtracter(zonePolygon.xpoints, (getWidth() / 2 - mapImage.getWidth(null) / 2)),
                            arrayValueSubtracter(zonePolygon.ypoints, (getHeight() / 2 - mapImage.getHeight(null) / 2)), zonePolygon.npoints), zoneName.getText(), Integer.parseInt(startIndex.getText()), Integer.parseInt(endIndex.getText()));

                    zoneDefinitions.add(parkingZone);
                    zonePolygon.reset();

                    if (currentZone > zoneCount) {
                        Log.print("NEXT PAGE");
                        try {
                            DatabaseBridge.setUpParkingZones(zoneDefinitions);
                            SetupMain.FRAME.PANEL_HOLDER.add(new FinalSetup());
                            repaint();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        if (currentZone == zoneCount)
                            nextZoneButton.setText("Enter Details and Finish Map Setup!");
                        MapSetup.this.message = "Please Select Boundaries for Zone " + currentZone;
                    }
                }
            }
        });
        add(nextZoneButton);


        zonePolygon = new Polygon();

        new Thread(this).start();
        message = "Please Select Boundaries for Zone "+currentZone;
        addMouseListener(this);
    }

    private int[] arrayValueSubtracter(int[] original, int valToSubract){
        int[] toReturn = new int[original.length];
        for(int i= 0 ; i<original.length; i++){
            toReturn[i] = original[i] - valToSubract;
        }
        return toReturn;
    }



    @Override
    public void paint(Graphics g){
        g.clearRect(0,0,getWidth(),getHeight());
        g.setColor(Colors.MEDIUM_BACKGROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(mapImage, getWidth()/2 - mapImage.getWidth(null)/2, getHeight()/2 - mapImage.getHeight(null)/2, null);

        g.setColor(Colors.DARK_BACKGROUND_COLOR);
        g.fillRect(0, 50, getWidth(), 40);
        g.setColor(Color.WHITE);
        Font titleFont = new Font("Cambria", Font.BOLD, 30);
        g.setFont(titleFont);
        FontMetrics metrics = g.getFontMetrics(titleFont);
        int x = (getWidth() - metrics.stringWidth(message)) / 2;
        g.drawString(message, x, 80);

        g.setColor(Color.YELLOW);
        g.fillPolygon(zonePolygon);

        super.paintComponents(g);

    }


    @Override
    public void run() {
        while(true){
            repaint();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            zonePolygon.addPoint(e.getX(), e.getY());
        } else if(e.getButton() == MouseEvent.BUTTON3){
            zonePolygon.reset();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
