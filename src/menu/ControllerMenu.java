package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

import game.ModelGame;
import game.ViewGame;
import game.User;

public class ControllerMenu {

    private ModelInterface mi;
    private ViewMenu vm;
    private ModelGame mg;
    private ActionListener playGame;
    private ActionListener signUp;
    private ActionListener goToSettings;
    private ActionListener quit;
    private ArrayList<User> rank = new ArrayList<>(5);
    
    public ControllerMenu(ViewGame vg, ModelInterface mi, ModelGame mg, ViewMenu vm) {
        
        // reference the model to allow the controller to alter settings
        this.mi = mi;
        // reference the view to allow the controller to change things in the view
        this.vm = vm;
        // reference to the game model to reset the game every time play is clicked on
        this.mg = mg;
        //setting the top 5 ranks of users in rank array list
        setRankList();
        
    }

    public void setRankList(){
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader("leaderBoard.txt"));
            int i = 0;
            while (sc.hasNext() && i<5) {
                i++;
                String line = sc.nextLine();
                String[] l = line.split("//");
                User curr = new User(l[1], Integer.parseInt(l[2]));
                curr.setRank(Integer.parseInt(l[0]));
                rank.add(curr);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Issue with reading leaderboard file");
        } finally {
            if (sc != null) sc.close();
        }

        // use something like this to print out into the frame somewhere
        /*for (User u : rank){
            System.out.println(u.getString());
        }*/
    }
    
    public void setupController() {
        // creates the action when the play button is pressed
        for (User u : rank){
            System.out.println(u.getString());
        }

        signUp = new ActionListener() {
    		public void actionPerformed(ActionEvent event) {
    			String name = (String)JOptionPane.showInputDialog(null,"Enter your Username:","Sign up",JOptionPane.PLAIN_MESSAGE,null,null,null);
    		    mg.setName(name);
    		}
    	};
    	vm.getSignupButton().addActionListener(signUp);
        
        playGame = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                mg.generateBoard();
                //mg.startTimer();
                // when the play button is pressed, sets the current screen being viewed to the game screen
                mi.setCurrScreen("Play");
            }
        };
        // adds a listener to the play button so that the action is performed when the play button is pressed
        vm.getPlayButton().addActionListener(playGame);
        
        // creates the action when the settings button is pressed
        goToSettings = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                mi.setCurrScreen("Settings");
            }
        };
        vm.getSettingsButton().addActionListener(goToSettings);
        
        // creates the action when the quit button is pressed
        quit = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        };
        vm.getQuitButton().addActionListener(quit);
    
    }
    
}
