package game;

public class Box {

    private int xPos;
    private int yPos;
    private int xPosStart;
    private int yPosStart;
    private boolean atGoal;
    
    public Box(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xPosStart = xPos;
        this.yPosStart = yPos;
        this.atGoal = false;
    }
    
    public void resetBox() {
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

    public boolean isAtGoal() {
        return atGoal;
    }

    public void setAtGoal(boolean atGoal) {
        this.atGoal = atGoal;
    }
    
}
