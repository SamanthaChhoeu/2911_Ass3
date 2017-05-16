package game;

public class Player {

    private int xPos;
    private int yPos;
    private int xPosStart;
    private int yPosStart;
    private Player prev;

    public Player(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xPosStart = xPos;
        this.yPosStart = yPos;
        this.prev = null;
    }

    public void setStartPos(int x, int y){
        this.xPosStart = x;
        this.yPosStart = y;
    }

    public void resetPlayer() {
        xPos = xPosStart;
        yPos = yPosStart;
    }


    public void setPrevPlayer(Player prev){
        this.prev = prev;
    }
    public Player getPrevPlayer(){
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

    public Player clone(){
        Player copy = new Player(this.xPos, this.yPos);
        copy.setPrevPlayer(this.prev);
        copy.setStartPos(this.xPosStart, this.yPosStart);
        return copy;
    }

}
