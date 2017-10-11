package components.student.gui;

import components.misc.DatabaseBridge;
import components.misc.DatabaseVerifier;
import structures.Colors;
import structures.Zone;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class ClientMapPanel extends JPanel implements MouseListener{
    BufferedImage mapImage;
	ArrayList<Zone> zones;
	String studentID;
	Font zoneFont = new Font("Cambria", Font.BOLD, 20);
	int imageX=0;
	int imageY=0;



	public ClientMapPanel(int w, int h, String studentID){
        super();
		setSize(w,h);
		setLayout(null);
        this.studentID = studentID;

		try {
			mapImage = ImageIO.read(new File("images/Full School Map.png"));


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMouseListener(this);
		imageX=getWidth()/2 - mapImage.getWidth()/2;
		imageY=getHeight()/2 - mapImage.getHeight()/2-10;
		initPolygon();

		setVisible(true);
		repaint();


	}
	@Override
	public void paint(Graphics g){
		g.setColor(Colors.BUTTON_COLOR_2);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		g.setFont(ClientLoginPanel.titleFont);

        g.drawImage(mapImage, getWidth()/2 - mapImage.getWidth()/2, getHeight()/2 - mapImage.getHeight()/2, null);

        g.setColor(Colors.DARK_BACKGROUND_COLOR);
        g.fillRect(0, 50, getWidth(), 40);

        g.setColor(Color.WHITE);
        String message = "Please Choose The Zone You Would Like To Park In";
        Font titleFont = new Font("Cambria", Font.BOLD, 30);
        g.setFont(titleFont);
        FontMetrics metrics = g.getFontMetrics(titleFont);
        int x = (getWidth() - metrics.stringWidth(message)) / 2;
        g.drawString(message, x, 80);

        for(Zone zone: zones) {
            Polygon zonePolygon = new Polygon(arrayValueAdder(zone.getShape().xpoints,(getWidth()/2 - mapImage.getWidth()/2)),
                    arrayValueAdder(zone.getShape().ypoints,(getHeight()/2 - mapImage.getHeight()/2)), zone.getShape().npoints);

            String zoneName = "Zone "+zone.getName();

            g.setColor(Colors.MEDIUM_BACKGROUND_COLOR);
            g.fill3DRect((int)(zonePolygon.getBounds().getX() + zonePolygon.getBounds().getWidth()/2.75),
                    (int)(zonePolygon.getBounds().getY() + zonePolygon.getBounds().getHeight()/2.75),
                    zoneName.length() * 13, 50, true);

            g.setColor(Color.WHITE);
            g.setFont(zoneFont);
            g.drawString(zoneName,
                    (int)(zonePolygon.getBounds().getX() + zonePolygon.getBounds().getWidth()/2.75) + 10,
                    (int)(zonePolygon.getBounds().getY() + zonePolygon.getBounds().getHeight()/2.75) + 30);

        }
	}

    private int[] arrayValueAdder(int[] original, int valToAdd){
        int[] toReturn = new int[original.length];
        for(int i= 0 ; i<original.length; i++){
            toReturn[i] = original[i] + valToAdd;
        }
        return toReturn;
    }


	public void initPolygon(){
        try {
            zones = DatabaseBridge.getZonePolygonInformation();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		Polygon rep = zoneCheck(e);

		if(rep != null) {
            int response = JOptionPane.showConfirmDialog(ClientMapPanel.this,"Are you sure you would like to choose this zone?",
                    "Confirm your selection", JOptionPane.YES_NO_OPTION);

            if(response == JOptionPane.NO_OPTION)
                return;

            for (int i = 0; i < zones.size(); i++) {
                if (rep == zones.get(i).getShape())
                    selectSpot(i + 1);
            }
        }


	}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}


	public Polygon zoneCheck(MouseEvent e){
		for(Zone zone: zones){
            if(zone.getShape().contains(e.getX() - (getWidth()/2 - mapImage.getWidth()/2), e.getY() - (getHeight()/2 - mapImage.getHeight()/2)))
                return zone.getShape();
        }

        return null;
	}

    public void selectSpot(int zoneID){
        int previousSpotNumber = DatabaseVerifier.getStudentSpotNumber(DatabaseBridge.CONNECTION, studentID);
        if(previousSpotNumber > 0){
            try {
                JOptionPane.showMessageDialog(ClientMapPanel.this,
                        "It seems that you have already chosen a Parking Spot in the "+ DatabaseVerifier.convertSpotToZone(previousSpotNumber)+" zone!\n" +
                                "If you feel information is incorrect, please contact the administrator.","Error: Your Request Was Denied",JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return;
        }

        if(DatabaseBridge.writeParkingSpotRequest(studentID, DatabaseBridge.getZoneName(zoneID))){
            new WaitingFrame(studentID);

        }else{
            JOptionPane.showMessageDialog(ClientMapPanel.this,
                    "Sorry, we are not able to process your request.\n\nPlease try again.","Error: Process Cannot Be Processed",JOptionPane.ERROR_MESSAGE);
        }

    }



}


