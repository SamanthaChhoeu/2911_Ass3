package Main;


import java.awt.Dimension;
import java.util.Observable;

// Used to store the settings for the game before starting the game
public class ModelInterface extends Observable {

    private Dimension dimensions;
    private String prevScreen;
    private String currScreen;
    
    public ModelInterface () {
        // The default window size is 1280 x 720
        dimensions = new Dimension(1280, 720);
        // The default screen to show is the menu screen
        this.currScreen = "Menu";
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
        this.prevScreen = this.currScreen;
        this.currScreen = currScreen;
        setChanged();
        notifyObservers();
    }
    
}
