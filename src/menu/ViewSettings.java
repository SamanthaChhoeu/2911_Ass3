package menu;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class ViewSettings extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    private ModelInterface mi;
    private JPanel mainPanel;
    private JButton backButton;
    private JButton btnSwitch1;
    private JButton btnSwitch2;
    //private JCheckBox btnMusic1;
    //private JCheckBox btnMusic2;
    private JLabel m1;
    private JLabel m2;
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
        //this.setLayout(null);
        // sets the window to show in the middle of the screen
        this.setLocationRelativeTo(null);
        // the frame is no longer resizable
        this.setResizable(false);

        // create a panel on top of the frame which holds all the UI elements
        mainPanel = new PicturePanel();
        // manually place all the elements
        mainPanel.setLayout(null);
        mainPanel.setVisible(true);
        // sets the panel to a colour if picture not working TODO delete this once complete
        mainPanel.setBackground(Color.DARK_GRAY);
        // add this panel to the frame
        this.add(mainPanel);
        //this.getContentPane().add(mainPanel);

        // creates a new button to go back to the main menu
        backButton = new TranslucentButton("Back");
        backButton.setBounds(1025, 600, 200, 50);
        mainPanel.add(backButton);
        
        //create a label "music setting" 
        lb = new JLabel("Music Settings",SwingConstants.CENTER);
        mainPanel.add(lb);
        lb.setBounds(470,100,200,100);
        lb.setFont(new Font (Font.DIALOG, Font.BOLD, 20));

        m1 = new JLabel("Track 1 :",SwingConstants.CENTER);
        mainPanel.add(m1);
        m1.setBounds(370,155,200,100);
        m1.setFont(new Font (Font.DIALOG, Font.BOLD, 20));

        m2 = new JLabel("Track 2 :",SwingConstants.CENTER);
        mainPanel.add(m2);
        m2.setBounds(370,255,200,100);
        m2.setFont(new Font (Font.DIALOG, Font.BOLD, 20));

        //create below buttons to control and select music
    	btnSwitch1 = new TranslucentButton("Off");
        btnSwitch1.setBounds(530, 180, 100, 50);
    	mainPanel.add(btnSwitch1);

        btnSwitch2 = new TranslucentButton("On");
        btnSwitch2.setBounds(530, 280, 100, 50);
        mainPanel.add(btnSwitch2);
    }

    public JButton getBackButton() {
        return backButton;
    }
    
    public JButton getbtnSwitch1() {
        return btnSwitch1;
    }

    public JButton getbtnSwitch2() {
        return btnSwitch2;
    }

    /*public JCheckBox getbtnMusic1() {
        return btnMusic1;
    }*/
    
    /*public JCheckBox getbtnMusic2() {
        return btnMusic2;
    }*/


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
