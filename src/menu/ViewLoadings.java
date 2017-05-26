package menu;

import game.Box;
import game.Player;
import javafx.scene.text.Font;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewLoadings extends JFrame implements Observer{
	private ModelInterface mi;
	private JPanel mainPanel;
	private TranslucentButton One, Two, Three, Four, Five;
	private TranslucentButton quitButton;
	private JLabel header;
	
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

        //loop through slot files
        String name1 = getName(1);
        String name2 = getName(2);
        String name3 = getName(3);
        String name4 = getName(4);
        String name5 = getName(5);
        
        header = new JLabel("Choose which file to load!", JLabel.CENTER);
        header.setBounds(100, 150, 500, 50);
        mainPanel.add(header);
        
        One= new TranslucentButton(name1);
        One.setBounds(100, 230, 500, 50);
        //signupButton.setBorderPainted(false);
        mainPanel.add(One);


        Two= new TranslucentButton(name2);
        Two.setBounds(100, 310, 500, 50);
        //signupButton.setBorderPainted(false);
        mainPanel.add(Two);
        
        Three= new TranslucentButton(name3);
        Three.setBounds(100, 390, 500, 50);
        //signupButton.setBorderPainted(false);
        mainPanel.add(Three);
        
        Four= new TranslucentButton(name4);
        Four.setBounds(100, 470, 500, 50);
        //signupButton.setBorderPainted(false);
        mainPanel.add(Four);
        
        Five= new TranslucentButton(name5);
        Five.setBounds(100, 550, 500, 50);
        //signupButton.setBorderPainted(false);
        mainPanel.add(Five);
        
        
        // creates a new button to quit the game
        quitButton = new TranslucentButton("Back");
        quitButton.setBounds(700, 550, 500, 50);
        mainPanel.add(quitButton);

	}

	private String getName (int num){
        Scanner sc = null;
        String file1 = "saves/" + "slot" + num + ".txt";
        String name = "Empty Slot " +num;
        try {
            sc = new Scanner(new FileReader(file1));
            int count =0;
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if(count == 0) {
                    name = line;
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Issue with reading Save file");
        } finally {
            if (sc != null) sc.close();
        }
        return name;
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
