package components.administrator.gui.maps;

import components.misc.DatabaseBridge;
import components.student.gui.ClientLoginPanel;
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


public class MapPanel extends JPanel implements MouseListener{

	/**
	 *
	 */
	private static final long serialVersionUID = 2622634343787798341L;

    BufferedImage mapImage;
    ArrayList<Zone> zones;
    Font zoneFont = new Font("Cambria", Font.BOLD, 18);

    int imageX=0;
	int imageY=0;

	public MapPanel(int w,int h){
		super();
		setSize(w,h);
		setLayout(null);


		try {
			mapImage = ImageIO.read(new File("images/Full School Map.png"));


		} catch (IOException e) {
			e.printStackTrace();
		}
		addMouseListener(this);
		imageX=getWidth()/2 - mapImage.getWidth()/2;
		imageY=getHeight()/2 - mapImage.getHeight()/2-10;
		initPolygon();



	}
    @Override
    public void paint(Graphics g){
        g.setColor(Colors.DARK_BACKGROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(mapImage, getWidth()/2 - mapImage.getWidth()/2, getHeight()/2 - mapImage.getHeight()/2, null);
        g.setFont(ClientLoginPanel.titleFont);
        g.setColor(Colors.MEDIUM_BACKGROUND_COLOR);
        g.fillRect(0, 50, getWidth(), 40);

        g.setColor(Color.WHITE);
        String message = "School Parking Zones View";
        Font titleFont = new Font("Cambria", Font.BOLD, 30);
        g.setFont(titleFont);
        FontMetrics metrics = g.getFontMetrics(titleFont);
        int x = (getWidth() - metrics.stringWidth(message)) / 2;
        g.drawString(message, x, 80);

        for(Zone zone: zones) {
            Polygon zonePolygon = new Polygon(arrayValueAdder(zone.getShape().xpoints,(getWidth()/2 - mapImage.getWidth()/2)),
                    arrayValueAdder(zone.getShape().ypoints,(getHeight()/2 - mapImage.getHeight()/2)), zone.getShape().npoints);
            /*g.setColor(Colors.YELLOW);
            g.fillPolygon(zonePolygon);*/

            String zoneName = "Zone "+zone.getName();

            g.setColor(Colors.MEDIUM_BACKGROUND_COLOR);
            g.fill3DRect((int)zonePolygon.getBounds().getX(), (int)zonePolygon.getBounds().getY() + (int)zonePolygon.getBounds().getHeight()/2 - 20,
                    (int)zonePolygon.getBounds().getWidth(), 40, true);

            g.setColor(Color.WHITE);
            g.setFont(zoneFont);
            metrics = g.getFontMetrics(zoneFont);
            g.drawString(zoneName,
                    (int)zonePolygon.getBounds().getX() + ((int)(zonePolygon.getBounds().getWidth() - metrics.stringWidth(zoneName))/2),
                    (int)(zonePolygon.getBounds().getY()) + (int)zonePolygon.getBounds().getHeight()/2 + 5);

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
            for (int i = 0; i < zones.size(); i++) {
                if (rep == zones.get(i).getShape())
                    clickedZone(i + 1);
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

	public void clickedZone(int zoneID){
	    String zoneName = DatabaseBridge.getZoneName(zoneID);
	    add(new MapZone(zoneName,getWidth(), getHeight()));
	}



}