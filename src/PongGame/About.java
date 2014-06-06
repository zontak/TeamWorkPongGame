package PongGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class About extends JFrame {
	
	private static final long serialVersionUID = 5123464558655284856L;
	JButton back = new JButton("Back");
	JFrame frame;
	
	public About() {
		addActions();
		frame = new JFrame("About");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(1024, 768);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		ImageIcon backImg = new ImageIcon(ImageIcon.class.getResource("/IMAGES/button_back.png"));
		
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setIcon(backImg);
		back.setBounds(362, 600, 312, 75);
		
		ImageIcon background = new ImageIcon(ImageIcon.class.getResource("/IMAGES/about_background.png"));
		
		JLabel lblBackground = new JLabel();
        lblBackground.setIcon(background);
        lblBackground.setBounds(0, 0, 1024, 768);
		
		frame.add(back);
        frame.add(lblBackground);
        
	}
	
	public void addActions() {

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Launcher.call();
			}
		});
	}

}