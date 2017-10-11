package components.setup.gui;

import components.misc.SlideContainer;
import structures.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SetupFrame extends JFrame{

	/**
	 *
	 */
	private static final long serialVersionUID = 8417174413325190658L;

    JLabel topBar = new JLabel("The Parking Lot Management Software by Sudarshan Gopalakrishnan | Software Setup");
    JButton close, min;
	Font f1 = new Font("Cambria",Font.BOLD,20);
	Font f2 = new Font("Cambria",Font.BOLD,7);

	SlideContainer PANEL_HOLDER;

	public SetupFrame(){
        super("The Parking Lot Management Software by Sudarshan Gopalakrishnan | Client");
        initComponents();
        InitialSetup panel = new InitialSetup();
        PANEL_HOLDER.add(panel, 0, 100);
//        PANEL_HOLDER.add(new FinalSetup(), 0, 100);

        setVisible(true);
        setLayout(null);
        getContentPane().setBackground(Colors.MEDIUM_BACKGROUND_COLOR);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents(){
        setResizable(false);
        setUndecorated(true);
        setLayout(null);
        Toolkit tk = this.getToolkit();
        setSize(tk.getScreenSize());

        PANEL_HOLDER = new SlideContainer();
        PANEL_HOLDER.setBounds(0, 25, getWidth(), getHeight()-25);

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
                dispose();
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

            @Override
            public void actionPerformed(ActionEvent arg0) {
                setState(Frame.ICONIFIED);

            }

        });

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
        add(PANEL_HOLDER);

        repaint();
    }


    public SlideContainer getPanelHolder(){
        return PANEL_HOLDER;
    }


}
