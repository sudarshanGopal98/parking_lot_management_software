package components.administrator.gui;

import components.administrator.gui.management.*;
import components.administrator.gui.maps.MapPanel;
import structures.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminFrame extends JFrame{

	/**
	 *
	 */
	private static final long serialVersionUID = 6874389715821712553L;


	JLabel topBar = new JLabel("The Parking Lot Management Software by Sudarshan Gopalakrishnan | Administrator");
	JButton close, min;
	JTabbedPane panes;
	Font f1 = new Font("Cambria",Font.BOLD,20);
	Font f2 = new Font("Cambria",Font.BOLD,7);

	public AdminFrame(){
		super("The Parking Lot Management Software by Sudarshan Gopalakrishnan | Administrator");
		setResizable(false);
		setUndecorated(true);
		setLayout(null);

		
		Toolkit tk = this.getToolkit();
		setSize(tk.getScreenSize());

        UIManager.put("TabbedPane.selected",Colors.TEXT_TOOLBAR_COLOR);
        UIManager.put("TabbedPane.opaque",false);

        close = new JButton("X");
		close.setBounds(getWidth()-25,0,25,25);
		close.setFont(f2);
		close.setIcon(new ImageIcon("images/close.png"));
		close.setBorder(null);
		close.setMargin(new Insets(0, 0, 0, 0));
		close.setBackground(Color.BLACK);
		close.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		min = new JButton("_");
		min.setBounds(getWidth()-60,0,25,25);
		min.setFont(f2);
		min.setIcon(new ImageIcon("images/minimize.png"));
		min.setMargin(new Insets(0, 0, 0, 0));
		min.setBackground(Color.BLACK);
		min.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				setState(Frame.ICONIFIED);
				
			}

		});

		panes = new JTabbedPane();
		panes.setBounds(0,25,getWidth(),getHeight() - 25);
		panes.setFont(new Font("Calibri", Font.PLAIN, 15));
		panes.setBackground(Colors.MEDIUM_BACKGROUND_COLOR);
        panes.setForeground(Color.white);
		panes.add(new AllStudentsDataPanel(), "List of All Students");
		panes.add(new StudentSearchupPanel(this), "Student Search Up");
		panes.add(new InsuranceExpiryPanel(), "Insurance");
        panes.add(new InRoomStudentsDataPanel(), "List of In Room Students");
        panes.add(new MapPanel(panes.getWidth(), panes.getHeight()), "Map View");

        panes.add(new DataManagementPanel(), "Data Management");



		topBar.setFont(f1);
		topBar.setBounds(0,0,getWidth(),25);
		topBar.setHorizontalAlignment(SwingConstants.CENTER);
		topBar.setForeground(Color.WHITE);
		topBar.setBackground(Colors.TEXT_TOOLBAR_COLOR);
		topBar.setOpaque(true);
		topBar.setVisible(true);

		add(close);
		add(min);
		add(topBar);
		add(panes);


        repaint();
		setVisible(true);
    }
}
