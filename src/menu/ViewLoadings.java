package menu;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ViewLoadings extends JFrame implements Observer{
	private ModelInterface mi;
	private JPanel mainPanel;
	private JButton One, Two, Three, Four, Five;
	private JButton quitButton;
	
	public ViewLoadings(ModelInterface mi){
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
        
        
        One= new TranslucentButton("User 1");
        One.setBounds(100, 150, 500, 50);
        //signupButton.setBorderPainted(false);
        mainPanel.add(One);
        
        Two= new TranslucentButton("User 2");
        Two.setBounds(100, 250, 500, 50);
        //signupButton.setBorderPainted(false);
        mainPanel.add(Two);
        
        Three= new TranslucentButton("User 3");
        Three.setBounds(100, 350, 500, 50);
        //signupButton.setBorderPainted(false);
        mainPanel.add(Three);
        
        Four= new TranslucentButton("User 4");
        Four.setBounds(100, 450, 500, 50);
        //signupButton.setBorderPainted(false);
        mainPanel.add(Four);
        
        Five= new TranslucentButton("User 5");
        Five.setBounds(100, 550, 500, 50);
        //signupButton.setBorderPainted(false);
        mainPanel.add(Five);
        
        
        // creates a new button to quit the game
        quitButton = new TranslucentButton("Back");
        quitButton.setBounds(700, 550, 500, 50);
        mainPanel.add(quitButton);

	}
	
	
	
	public JButton getOne() {
		return One;
	}
	
	public JButton getTwo() {
		return Two;
	}
	
	public JButton getThree() {
		return Three;
	}
	
	public JButton getFour() {
		return Four;
	}
	
	public JButton getFive() {
		return Five;
	}
	
	public JButton getQuitButton(){
		return quitButton;
	}
	
	@Override
    public void update(Observable o, Object arg) {
        String command = ((String) arg);
        // check whether or not to show this screen
        if (command.equals("ChangeScreenLoadings")) {
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }
        
    }

}
