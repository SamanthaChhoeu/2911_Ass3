package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Main.ModelFrame;

public class ControllerMenu {

    private ModelFrame mm;
    private ViewMenu vm;
    private ActionListener playGame;
    private ActionListener goToSettings;
    private ActionListener quit;
    
    public ControllerMenu(ModelFrame mm, ViewMenu vm) {
        this.mm = mm;
        this.vm = vm;
        
        playGame = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                mm.setCurrScreen("Play");
            }
        };
    
        vm.getPlayButton().addActionListener(playGame);
        
        /*goToSettings = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                
            }
        };
        
        vm.getSettingsButton().addActionListener(goToSettings);*/
        
        quit = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        };
        
        vm.getQuitButton().addActionListener(quit);
    
    }

    public void initController() {
        
    }
    
}
