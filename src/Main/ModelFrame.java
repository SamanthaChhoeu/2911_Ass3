package Main;


import java.util.Observable;

// Used to store the settings for the game before starting the game
public class ModelFrame extends Observable {

    private int width;
    private int height;
    private String prevScreen;
    private String currScreen;
    
    public ModelFrame () {
        width = 1280;
        height = 720;
        this.currScreen = "Menu";
    }
    
    public int getWidth() {
        return width;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
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
