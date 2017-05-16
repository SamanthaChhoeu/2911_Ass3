package game;

public class Box {

    private int xPos;
    private int yPos;
    private int xPosStart;
    private int yPosStart;
    private boolean atGoal;
    private Box prev;
    
    public Box(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xPosStart = xPos;
        this.yPosStart = yPos;
        this.atGoal = false;
        this.prev = null;
    }
    
    public void resetBox() {
        xPos = xPosStart;
        yPos = yPosStart;
    }

    public void setStartPos(int x, int y){
        this.xPosStart = x;
        this.yPosStart = y;
    }

    public void setPrevBox(Box prev){
        this.prev = prev;
    }
    public Box getPrevBox(){
        return this.prev;
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

    public boolean isAtGoal() {
        return atGoal;
    }

    public void setAtGoal(boolean atGoal) {
        this.atGoal = atGoal;
    }

    public Box clone(){
        Box copy = new Box(this.xPos, this.yPos);
        copy.setPrevBox(this.prev);
        copy.setStartPos(this.xPosStart, this.yPosStart);
        return copy;
    }
    
}
