package menu;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ViewSettings extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    private ModelInterface mi;
    private JButton backButton;
    private JButton btnon;
    private JButton btnoff;
    private JButton btnMusic1;
    private JButton btnMusic2;
    private JLabel lb;

    public ViewSettings(ModelInterface mi) {
        
        this.mi = mi;
        setupSettings();
    
    }
    
    private void setupSettings() {
        
        // sets the size of the window
        this.setSize(mi.getDimensions());
        // sets what happens when the user closes the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // sets the layout to a manual layout to enable full customisation of where the buttons are
        this.setLayout(null);
        // sets the window to show in the middle of the screen
        this.setLocationRelativeTo(null);
        
        // creates a new button to go back to the main menu
        backButton = new JButton("Back");
        backButton.setBounds(1025, 600, 200, 50);
        this.add(backButton);
        
        //create a label "music setting" 
        lb = new JLabel("Music Setting",SwingConstants.CENTER);
        this.add(lb);
        lb.setBounds(98,100,100,100);
        
        //create below buttons to control and select music
    	btnon = new JButton("On");
    	btnoff = new JButton("Off");
    	btnMusic1 = new JButton("Music1");
    	btnMusic2 = new JButton("Music2");
    	btnon.setBounds(100, 180, 100, 50);
    	btnoff.setBounds(100, 280, 100, 50);
    	btnMusic1.setBounds(100, 380, 100, 50);
    	btnMusic2.setBounds(100, 480, 100, 50);
    	this.add(btnon);
    	this.add(btnoff);
    	this.add(btnMusic1);
    	this.add(btnMusic2);
        
    }

    public JButton getBackButton() {
        return backButton;
    }
    
    public JButton getbtnon() {
        return btnon;
    }
    
    public JButton getbtnoff() {
        return btnoff;
    }
    
    public JButton getbtnMusic1() {
        return btnMusic1;
    }
    
    public JButton getbtnMusic2() {
        return btnMusic2;
    }


    @Override
    public void update(Observable o, Object arg) {
        
        String command = ((String) arg);
        
        if (command.equals("ChangeScreenSettings")) {
                
            this.setVisible(true);

        } else {
            
            this.setVisible(false);
            
        }
        
    }

}
