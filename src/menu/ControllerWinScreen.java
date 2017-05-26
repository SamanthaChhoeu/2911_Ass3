package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerWinScreen {
    
    private ModelInterface mi;
    private ViewWinScreen vws;
    private ActionListener backToMenu;
    private PicturePanel mainPanel;

    public ControllerWinScreen(ModelInterface mi, ViewWinScreen vws) {
        
        this.mi = mi;
        this.vws = vws;
        
    }

    public void setupController() {
        // sets the size of the window
        this.setSize(mi.getDimensions());
        // sets what happens when the user closes the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // sets the layout to a manual layout to enable full customisation of where the buttons are
        this.setLayout(null);
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
        
        // creates the action when the menu button is pressed
        backToMenu = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // when the menu button is pressed, sets the current screen being viewed to the menu screen
                mi.setRankList();
                mi.setCurrScreen("Menu");
            }
        };
        // adds a listener to the menu button so that the action is performed when the menu button is pressed
        vws.getBackToMainMenuButton().addActionListener(backToMenu);
        
    }
    
}
