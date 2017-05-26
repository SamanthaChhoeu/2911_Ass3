package game;

/**
 * Box Class Used to create and store boxes as the current box state
 */
public class Box {


    private int xPos;
    private int yPos;
    private int xPosStart;
    private int yPosStart;
    private boolean atGoal;
    private Box prev;
    private int move;

    /**
     * Costructor to create a Box given its postions when it starts
     * @param xPos
     * @param yPos
     */
    public Box(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xPosStart = xPos;
        this.yPosStart = yPos;
        this.atGoal = false;
        this.prev = null;
        this.move = 0;
    }

    /**
     * Alternate Constructor to make a box given a string with all the needed data in it
     * @param line
     */
    public Box(String line){
            //int xPos, int yPos, int xPosStart, int yPosStart, boolean atGoal) {
        String[] l = line.split("/");
        this.xPos = Integer.parseInt(l[0]);
        this.yPos = Integer.parseInt(l[1]);
        this.xPosStart = Integer.parseInt(l[2]);
        this.yPosStart = Integer.parseInt(l[3]);
        this.atGoal = Boolean.parseBoolean(l[0]);
        this.prev = null;
        this.move = 0;
    }

    /**
     * setter for the boxes move number
     * @param move
     */
    public void setMove(int move){
        this.move = move;
    }

    /**
     * getter to get the current move number
     * @return
     */
    public int getMove(){
        return this.move;
    }

    /**
     * resets boxes start positions
     */
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
        copy.setMove(this.move);
        return copy;
    }

    public String PrintLine(){
        String line = xPos + "/" + yPos + "/" + xPosStart + "/" + yPosStart + "/" + atGoal;
        return line;
    }
    
}
