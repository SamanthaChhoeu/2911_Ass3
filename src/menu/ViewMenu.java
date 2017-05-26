package menu;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class ViewMenu extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    private ModelInterface mi;
    private JPanel mainPanel;
    private TranslucentButton playButton;
    private TranslucentButton settingsButton;
    //private JButton signupButton;
    private TranslucentButton quitButton;
	private TranslucentButton guideButton;
	private JTable highScoreTable;
	private JTextField userNameField;
	private TranslucentButton saveUserName;
	private TranslucentButton loadingButton;

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
        // sets the window to show in the middle of the screen
        this.setLocationRelativeTo(null);
        // the frame is no longer resizable
        this.setResizable(false);

        // create a panel on top of the frame which holds all the UI elements
        mainPanel = new PicturePanel();
        // manually place all the elements
        mainPanel.setLayout(null);
        //mainPanel.setVisible(true);
        // sets the panel to a colour if picture not working TODO delete this once complete
        mainPanel.setBackground(Color.DARK_GRAY);
        // add this panel to the frame
        this.add(mainPanel);
        //this.getContentPane().add(mainPanel);
        
        /*signupButton= new TranslucentButton("Sign up");
        signupButton.setBounds(100, 150, 500, 50);
        //signupButton.setBorderPainted(false);
        mainPanel.add(signupButton);*/
	    
        // creates a new button to start playing the game
        playButton = new TranslucentButton("Play");
        // sets the size and the position of the buttons (only works for null layout)
        playButton.setBounds(100, 300, 500, 50);
        // adds the play button to the current frame
        mainPanel.add(playButton);

        guideButton = new TranslucentButton("Guide");
        guideButton.setBounds(100, 375, 500, 50);
        mainPanel.add(guideButton);

        // creates a new button to access the settings of the game
        settingsButton = new TranslucentButton("Settings");
        settingsButton.setBounds(100, 450, 500, 50);
        mainPanel.add(settingsButton);
        
        // creates a new button to quit the game
        quitButton = new TranslucentButton("Quit");
        quitButton.setBounds(100, 525, 500, 50);
        mainPanel.add(quitButton);
        
        // creates a new button to load the saved files
        loadingButton = new TranslucentButton("Load");
        loadingButton.setBounds(100, 150, 500, 50);
        mainPanel.add(loadingButton);

        String[] columnNames = {"Rank", "Name", "Score"};
        Object[][] data = mi.populateTable();

        userNameField = new JTextField(20);
        userNameField.setBounds(700, 300, 400, 25);
        mainPanel.add(userNameField);

        saveUserName = new TranslucentButton("Save");
        saveUserName.setBounds(1125, 300, 75, 25);
        mainPanel.add(saveUserName);

        highScoreTable = new JTable(data, columnNames);
        highScoreTable.setBounds(700, 350, 500, 225);
        highScoreTable.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        highScoreTable.setRowHeight(40);

        highScoreTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(highScoreTable);
        scrollPane.setBounds(700, 350, 500, 225);
        mainPanel.add(scrollPane);
        //mainPanel.add(highScoreTable);

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
    
    /*public JButton getSignupButton(){
    	return signupButton;
    }*/
	
	public JButton getguideButton(){
    	return guideButton;
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
    
    public JButton getLoadingButton() {
    	return loadingButton;
    }
    
    public String getUserName() {
    	return userNameField.getText();
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
