package menu;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class ViewMenu extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    private ModelInterface mi;
    private JPanel mainPanel;
    private JButton playButton;
    private JButton settingsButton;
    private JButton signupButton;
    private JButton quitButton;
    private JButton rankingButton;
	private JButton guideButton;

    public ViewMenu (ModelInterface mi) {   
        
        // the model interface is referenced to allow to view to get details from the model
        this.mi = mi;
        setupMenu();
        
    }
    
    private void setupMenu() {

        // sets the size of the window
        this.setSize(mi.getDimensions());
        // sets what happens when the user closes the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // sets the layout to a manual layout to enable full customisation of where the buttons are
        //this.setLayout(null);
        // sets the window to show in the middle of the screen
        this.setLocationRelativeTo(null);

        mainPanel = new PicturePanel();
        mainPanel.setLayout(null);
        mainPanel.setVisible(true);
        mainPanel.setBackground(Color.DARK_GRAY);
        this.getContentPane().add(mainPanel);
		
		
		guideButton = new JButton("Guide");
        guideButton.setBounds(390, 150, 500, 50);
        this.add(guideButton);
		
		
		
        rankingButton = new TranslucentButton("Ranking list");
        rankingButton.setBounds(390, 225, 500, 50);
        //rankingButton.setOpaque(false);
        //rankingButton.setContentAreaFilled(false);
        //rankingButton.setBorderPainted(false);
        mainPanel.add(rankingButton);
        
        signupButton= new TranslucentButton("Sign up");
        signupButton.setBounds(390, 300, 500, 50);
        //signupButton.setBorderPainted(false);
        mainPanel.add(signupButton);
	    
        // creates a new button to start playing the game
        playButton = new TranslucentButton("Play");
        // sets the size and the position of the buttons (only works for null layout)
        playButton.setBounds(390, 375, 500, 50);
        //playButton.setBorderPainted(false);
        // adds the play button to the current frame
        mainPanel.add(playButton);
        
        // creates a new button to access the settings of the game
        settingsButton = new TranslucentButton("Settings");
        settingsButton.setBounds(390, 450, 500, 50);
        //settingsButton.setBorderPainted(false);
        mainPanel.add(settingsButton);
        
        // creates a new button to quit the game
        quitButton = new TranslucentButton("Quit");
        quitButton.setBounds(390, 525, 500, 50);
        //quitButton.setBorderPainted(false);
        mainPanel.add(quitButton);

        /* //Top menu bar.
        //Feel free to delete if we don't need at the end.
        MenuItem something = new MenuItem("Something");
        MenuItem somethingmore = new MenuItem("Something More");
        MenuItem Guide = new MenuItem("Guide");
        MenuItem About = new MenuItem("About");

    	Menu option = new Menu("Game Option");
		//option.addSeparator();//Do we even need a seperator?
		option.add(something);
		option.add(somethingmore);

		Menu help = new Menu("Help");
		help.add(Guide);
		help.add(About);

		MenuBar bar = new MenuBar();
		bar.add(option);
		bar.add(help);
		setMenuBar(bar);*/

        // set such that the main menu is the visible when this class is created
        this.setVisible(true);
        
    }
    public JButton getSignupButton(){
    	return signupButton;
    }
	
	public JButton getguideButton(){
    	return guideButton;
    }
    
    public JButton getRankingButton(){
    	return rankingButton;
    }
    
    public JButton getPlayButton() {
        return playButton;
    }
    
    public JButton getSettingsButton() {
        return settingsButton;
    }
    
    public JButton getQuitButton() {
        return quitButton;
    }

    @Override
    public void update(Observable o, Object arg) {
        
        String command = ((String) arg);
        
        // check whether or not to show this screen
        if (command.equals("ChangeScreenMenu")) {
                
            this.setVisible(true);

        } else {
            
            this.setVisible(false);
            
        }
        
    }
    
}
