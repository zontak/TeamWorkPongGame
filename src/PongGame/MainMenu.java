package PongGame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;

	int screenWidth = 1000;
	int screenHeight = 700;

	int buttonWidth = 300;
	int buttonHeight = 75;

	JButton Play, Quit, Multy, About;

	public MainMenu() {

		addButtons();
		addActions();

		getContentPane().setLayout(null);

		Play.setBounds(362, 300, buttonWidth, buttonHeight); // Position the Play button
		Multy.setBounds(362, 390, buttonWidth, buttonHeight); // Position the Play button
		About.setBounds(362, 480, buttonWidth, buttonHeight); // Position the Play button
		Quit.setBounds(362, 570, buttonWidth, buttonHeight); // Position the Quit button
//		twoPlayer.setBounds((screenWidth - buttonWidth) / 2, 95, buttonWidth * 2, buttonHeight); // Position the towPlayer checkbox

		// Adding buttons
		getContentPane().add(Play); // Add the button to the JFrame
		getContentPane().add(Multy); // Add the button to the JFrame
		getContentPane().add(About); // Add the button to the JFrame
		getContentPane().add(Quit); // Add the button to the JFrame

		// add label as bacêground
		ImageIcon background = new ImageIcon(ImageIcon.class.getResource("/IMAGES/background_title_1024_768.png"));
        
        JLabel lblBackground = new JLabel();
        lblBackground.setIcon(background);
        lblBackground.setBounds(0, 0, 1024, 768);
        
        getContentPane().add(lblBackground); //Add the label to the Jframe
		// JFrame stuff
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(screenWidth, screenHeight);
		setTitle("Pong Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2 - 20 -this.getSize().height/2);
	}

	private void addButtons() {
		Play = new JButton();
		ImageIcon playImg = new ImageIcon(ImageIcon.class.getResource("/IMAGES/button_single_player.png")); //Get image from IMAGES
		Play.setOpaque(false);					//make button invisible
		Play.setContentAreaFilled(false);		//make button invisible
		Play.setBorderPainted(false);			//make button invisible
		Play.setIcon(playImg);					//Add img to the button
		
		Multy = new JButton();
		ImageIcon mulryImg = new ImageIcon(ImageIcon.class.getResource("/IMAGES/button_multy_player.png"));
		Multy.setOpaque(false);
		Multy.setContentAreaFilled(false);
		Multy.setBorderPainted(false);
		Multy.setIcon(mulryImg);
		
		About = new JButton();
		ImageIcon aboutImg = new ImageIcon(ImageIcon.class.getResource("/IMAGES/button_about.png"));
		About.setOpaque(false);
		About.setContentAreaFilled(false);
		About.setBorderPainted(false);
		About.setIcon(aboutImg);
		
		Quit = new JButton();
		ImageIcon quitImg = new ImageIcon(ImageIcon.class.getResource("/IMAGES/button_exit.png"));
		Quit.setOpaque(false);
		Quit.setContentAreaFilled(false);
		Quit.setBorderPainted(false);
		Quit.setIcon(quitImg);
		
//		twoPlayer = new JCheckBox("Two Players?");
	}

	private void addActions() {

		About.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Turn the action performed into a variable for usage
				dispose();
				
				new About();
			}
		});
		
		Play.addActionListener(new ActionListener() { // Take Play button, add new actionlistener
					public void actionPerformed(ActionEvent e) { // Turn the action performed into a variable for usage
						dispose();

						Game game = new Game();
						
						game.start();
					}
				});// Play button
		
		Multy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Turn the action performed into a variable for usage
				dispose();
				
				Game game = new Game();
				
				game.ai.isTwoPlayer = true;
				
				game.start();
			}
		});

		Quit.addActionListener(new ActionListener() { // Take Quit button, add new actionlistener
					public void actionPerformed(ActionEvent e) { // Turn the action performed into a variable for usage
						System.exit(0); // Shut down the program
					}
				});// Quit button
	}
}