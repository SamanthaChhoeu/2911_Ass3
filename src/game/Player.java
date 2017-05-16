package game;

public class Player {

    private int xPos;
    private int yPos;
    private int xPosStart;
    private int yPosStart;
    private int xPevPos;
    private int yPevPos;
    
    public Player(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xPosStart = xPos;
        this.yPosStart = yPos;
        setPrev(xPos, yPos);
    }
    
    public void resetPlayer() {
        xPos = xPosStart;
        yPos = yPosStart;
    }

    public void setPrev(int xPevPos, int yPevPos){
        this.xPevPos = xPevPos;
        this.yPevPos = yPevPos;
    }

    public int getPrevX(){
     return this.xPevPos;
    }

    public int getPrevY(){
        return this.yPevPos;
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
