package menu;


import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

// Used to store the settings for the game before starting the game
public class ModelInterface extends Observable {

    private Dimension dimensions;
    private String prevScreen;
    private String currScreen;
    private ArrayList<User> rank;
    
    public ModelInterface () {
        // The default window size is 1280 x 720
        dimensions = new Dimension(1280, 720);
        // The default screen to show is the menu screen
        this.currScreen = "Menu";
        this.rank =  new ArrayList<>(5);
        //setting the top 5 ranks of users in rank array list
        setRankList();

    }

    public void setRankList(){
        //rank.clear();
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader("leaderBoard.txt"));
            int i = 0;
            rank.clear();
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
    }
    
    public Dimension getDimensions() {
        return dimensions;
    }
    
    public String getPrevScreen() {
        return prevScreen;
    }

    public String getCurrScreen() {
        return currScreen;
    }

    public void setCurrScreen(String currScreen) {
        //this.prevScreen = this.currScreen;
        //this.currScreen = currScreen;
        setRankList();
        String changeToScreen = "ChangeScreen" + currScreen;
        setChanged();
        notifyObservers(changeToScreen);
    }

    public Object[][] populateTable() {

        setRankList();
        //System.out.println("test");
        Object[][] data = new Object[5][3];
        int i = 0;
        
        for (User currUser : rank) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    data[i][j] = currUser.getRank();
                } else if (j == 1) {
                    data[i][j] = currUser.getName();
                } else {
                    data[i][j] = currUser.getScore();
                }
            }
            i++;
        }

        return data;

    }
}
