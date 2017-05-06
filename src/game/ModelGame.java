package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ModelGame extends Observable {

    private int xSizeOfBoard;
    private int ySizeOfBoard;
    private String[][] sobokanBoard;
    private Player p;
    private List<Box> boxes;
    
    public ModelGame() {
        
        this.xSizeOfBoard = 10;
        this.ySizeOfBoard = 10;
        this.sobokanBoard = new String[ySizeOfBoard][xSizeOfBoard];
        this.boxes = new ArrayList<Box>();
        generateBoard();
        
    }
    
    private void generateBoard() {
        
        // generate the board
        // @Sam @Jath Do the board generation here
        // "0"  = free space
        // "p" = player
        // "w"  = wall
        // "b"  = box
        // "g"  = goal
        // "bg" = box at a goal
        String[][] sobokanBoardTest = new String[][]{
        {"w", "w", "w", "w", "w", "w", "w", "w", "w", "w"},
        {"w", "0", "0", "0", "0", "0", "0", "0", "0", "w"},
        {"w", "0", "0", "0", "0", "0", "0", "0", "0", "w"},
        {"w", "0", "0", "0", "0", "0", "0", "0", "0", "w"},
        {"w", "0", "0", "0", "0", "0", "0", "0", "0", "w"},
        {"w", "0", "0", "0", "0", "0", "0", "0", "0", "w"},
        {"w", "0", "0", "0", "0", "0", "0", "0", "0", "w"},
        {"w", "0", "0", "0", "0", "0", "0", "0", "0", "w"},
        {"w", "0", "0", "0", "0", "0", "0", "0", "0", "w"},
        {"w", "w", "w", "w", "w", "w", "w", "w", "w", "w"}};
        sobokanBoard = sobokanBoardTest;
        
        // place the player
        p = new Player(1, 1);
        sobokanBoard[p.getYPos()][p.getXPos()] = "p";
        
        // place 2 boxes
        int noOfBoxes = 2;
        for (int i = 0; i < noOfBoxes; i++) {
            Box newBox = new Box(noOfBoxes + i, noOfBoxes);
            sobokanBoard[newBox.getYPos()][newBox.getXPos()] = "b";
            boxes.add(newBox);
        }
        
        // set the goal for the boxes
        sobokanBoard[7][7] = "g";
        sobokanBoard[7][6] = "g";
        
    }
    
    public int getXSizeOfBoard() {
        return xSizeOfBoard;
    }
    
    public int getYSizeOfBoard() {
        return ySizeOfBoard;
    }
    
    public String getSobokanBoardAtXY(int xPos, int yPos) {
        return sobokanBoard[yPos][xPos];
    }
    
    public int getPlayerXPos() {
        return p.getXPos();
    }
    
    public int getPlayerYPos() {
        return p.getYPos();
    }
}
