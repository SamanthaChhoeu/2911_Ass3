package menu;

import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import game.ModelGame;

public class ViewWinScreen extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    private ModelInterface mi;
    private TranslucentButton backToMainMenuButton;
    private PicturePanel mainPanel;
    private JLabel winning;//, score;
    private ModelGame mg;

    public ViewWinScreen(ModelInterface mi, ModelGame mg) {
        
        // the model interface is referenced to allow to view to get details from the model
        this.mi = mi;
        this.mg = mg;
        setupWinScreen();
        
    }
    
    private void setupWinScreen() {
        
        // sets the size of the window
        this.setSize(mi.getDimensions());
        // sets what happens when the user closes the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // sets the layout to a manual layout to enable full customisation of where the buttons are
        //this.setLayout(null);
        // sets the window to show in the middle of the screen
        this.setLocationRelativeTo(null);
        
        // create a panel on top of the frame which holds all the UI elements
        mainPanel = new PicturePanel();
        // manually place all the elements
        mainPanel.setLayout(null);
        //mainPanel.setVisible(true);
        // sets the panel to a colour if picture not working TODO delete this once complete
        mainPanel.setBackground(Color.DARK_GRAY);
        // add this panel to the frame
        this.add(mainPanel);
        
        winning = new JLabel("You are the winner!", JLabel.CENTER);
        winning.setBounds(0, 200, 1280, 50);
        winning.setFont(new Font("Default", Font.BOLD, 50));
        mainPanel.add(winning);
        

        /*String t = "" + mg.scoreCounter;
        score = new JLabel("Your score is: " + t, JLabel.CENTER);
        score.setBounds(0, 250, 1280, 50);
        score.setFont(new Font("Default", Font.BOLD, 35));
        mainPanel.add(score);*/
        
        // creates a new button to quit the game
        backToMainMenuButton = new TranslucentButton("Back to Main Menu");
        backToMainMenuButton.setBounds(390, 525, 500, 50);
        mainPanel.add(backToMainMenuButton);
        
        
    }

    public JButton getBackToMainMenuButton() {
        return backToMainMenuButton;
    }

    @Override
    public void update(Observable o, Object arg) {
        
        String command = ((String) arg);
        
        if (command.equals("ChangeScreenWin")) {
            
            this.setVisible(true);
            
        } else {
            
            this.setVisible(false);
            
        }
    }

}
