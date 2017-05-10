package game;

public class Player {

    private int xPos;
    private int yPos;
    private int xPosStart;
    private int yPosStart;
    
    public Player(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xPosStart = xPos;
        this.yPosStart = yPos;
    }
    
    public void resetPlayer() {
        xPos = xPosStart;
        yPos = yPosStart;
    }

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }
    
}
