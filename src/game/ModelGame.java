package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class ModelGame extends Observable {

    private int xSizeOfBoard;
    private int ySizeOfBoard;
    private String[][] sobokanBoard;
    private Player p;
    private List<Box> boxes;
    
    public ModelGame() {
        
        generateBoard();
        
    }
    
    public void generateBoard() {
        
        // change this so it changes based on difficulty
        // keep difficulty on a 3:2 aspect ratio
        this.xSizeOfBoard = 15;
        this.ySizeOfBoard = 10;
        this.sobokanBoard = new String[ySizeOfBoard][xSizeOfBoard];
        this.boxes = new ArrayList<Box>();
        
        // fill the 2d array with walls
        for (String[] row : sobokanBoard)
            Arrays.fill(row, "w");
        
        // generate the board
        // TODO @Sam @Jath Do the board generation here
        // Good memes --> Make meme generator here
        // "0"  = free space
        // "p" = player
        // "w"  = wall
        // "b"  = box
        // "g"  = goal
        // "bg" = box at a goal
        // "pg" = player at a goal
        // This is just a basic board for testing purposes, change this function here
        String[][] sobokanBoardTest = new String[][]{
        {"w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w"},
        {"w", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "w"},
        {"w", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "w"},
        {"w", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "w"},
        {"w", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "w"},
        {"w", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "w"},
        {"w", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "w"},
        {"w", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "w"},
        {"w", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "w"},
        {"w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w"}};
        sobokanBoard = sobokanBoardTest;
        
        // place the player
        // TODO good starting point: randomly place where the player starts
        Random rand = new Random(); //randomization
        int Px = rand.nextInt(8)+1;
        int Py = rand.nextInt(8)+1;
        p = new Player(Px, Py);
        sobokanBoard[p.getYPos()][p.getXPos()] = "p";
        
        // place 2 boxes
        // TODO good starting point: randomly place where the boxes are placed
        int noOfBoxes = 3;
        for (int i = 0; i < noOfBoxes; i++) {
            Box newBox = new Box(noOfBoxes + i, noOfBoxes);
            sobokanBoard[newBox.getYPos()][newBox.getXPos()] = "b";
            boxes.add(newBox);
        }
        
        // set the goal for the boxes
        // TODO good starting point: randomly place where the goals for the boxes are
        int EndX = rand.nextInt(8)+1;
        int EndY = rand.nextInt(8)+1;
        sobokanBoard[EndX][EndY] = "g";
        
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
    
    public void movePlayerLeft() {
        if(checkMovable(p.getXPos() - 1, p.getYPos()).equals("Free")) {
            movePlayer(p.getXPos(), p.getYPos(), "Left");
        } else if (checkMovable(p.getXPos() - 1, p.getYPos()).equals("Box")) {
            if (checkMovableBox(p.getXPos() - 2, p.getYPos())) {
                moveBox(p.getXPos() - 1, p.getYPos(), "Left");
                movePlayer(p.getXPos(), p.getYPos(), "Left");
            }
        }
    }

    public void movePlayerRight() {
        if(checkMovable(p.getXPos() + 1, p.getYPos()).equals("Free")) {
            movePlayer(p.getXPos(), p.getYPos(), "Right");
        } else if (checkMovable(p.getXPos() + 1, p.getYPos()).equals("Box")) {
            if (checkMovableBox(p.getXPos() + 2, p.getYPos())) {
                moveBox(p.getXPos() + 1, p.getYPos(), "Right");
                movePlayer(p.getXPos(), p.getYPos(), "Right");
            }
        }
    }
    
    public void movePlayerUp() {
        if(checkMovable(p.getXPos(), p.getYPos() - 1).equals("Free")) {
            movePlayer(p.getXPos(), p.getYPos(), "Up");
        } else if (checkMovable(p.getXPos(), p.getYPos() - 1).equals("Box")) {
            if (checkMovableBox(p.getXPos(), p.getYPos() - 2)) {
                moveBox(p.getXPos(), p.getYPos() - 1, "Up");
                movePlayer(p.getXPos(), p.getYPos(), "Up");
            }
        }
    }
    
    public void movePlayerDown() {
        if(checkMovable(p.getXPos(), p.getYPos() + 1).equals("Free")) {
            movePlayer(p.getXPos(), p.getYPos(), "Down");
        } else if (checkMovable(p.getXPos(), p.getYPos() + 1).equals("Box")) {
            if (checkMovableBox(p.getXPos(), p.getYPos() + 2)) {
                moveBox(p.getXPos(), p.getYPos() + 1, "Down");
                movePlayer(p.getXPos(), p.getYPos(), "Down");
            }
        }
    }
    
    private String checkMovable(int xPos, int yPos) {
        
        if (sobokanBoard[yPos][xPos] == "w") {
            return "Wall";
        } else if (sobokanBoard[yPos][xPos] == "b" || sobokanBoard[yPos][xPos] == "bg") {
            return "Box";
        } else {
            return "Free";
        }
        
    }
    
    private boolean checkMovableBox(int xPos, int yPos) {
        if (sobokanBoard[yPos][xPos] == "0" || sobokanBoard[yPos][xPos] == "g") {
            return true;
        } else {
            return false;
        }
    }
    
    private void movePlayer(int xPos, int yPos, String direction) {
        
        // return to just goal if player leaves a goal square
        if (sobokanBoard[yPos][xPos] == "pg") {
            sobokanBoard[yPos][xPos] = "g";
        } else {
            sobokanBoard[yPos][xPos] = "0";
        }
        
        if (direction == "Left") {
            p.setXPos(xPos - 1);
        } else if (direction == "Right") {
            p.setXPos(xPos + 1);
        } else if (direction == "Up") {
            p.setYPos(yPos - 1);
        } else {
            p.setYPos(yPos + 1);
        }
        
        // go to player in goal square if player is entering a goal square
        if (sobokanBoard[p.getYPos()][p.getXPos()] == "g") {
            sobokanBoard[p.getYPos()][p.getXPos()] = "pg";
        } else {
            sobokanBoard[p.getYPos()][p.getXPos()] = "p";
        }
        
        boolean foundBoxNotAtGoal = false;
        // check if all boxes are in the goal
        for (Box checkBox : boxes) {
            if (!checkBox.isAtGoal()) foundBoxNotAtGoal = true;
        }
        setChanged();
        if (foundBoxNotAtGoal) {
            notifyObservers("MovePlayer");
        } else {
            disposeGame();
            notifyObservers("ChangeScreenWin");
        }
        
    }

    private void moveBox(int xPos, int yPos, String direction) {
        
        for (Box checkBox : boxes) {
            if (checkBox.getXPos() == xPos && checkBox.getYPos() == yPos) {
                if (sobokanBoard[yPos][xPos] == "bg") {
                    checkBox.setAtGoal(false);
                    sobokanBoard[yPos][xPos] = "g";
                } else {
                    sobokanBoard[yPos][xPos] = "0";
                }
                if (direction == "Left") {
                    checkBox.setXPos(xPos - 1);
                } else if (direction == "Right") {
                    checkBox.setXPos(xPos + 1);
                } else if (direction == "Up") {
                    checkBox.setYPos(yPos - 1);
                } else {
                    checkBox.setYPos(yPos + 1);
                }
                if (sobokanBoard[checkBox.getYPos()][checkBox.getXPos()] == "g") {
                    checkBox.setAtGoal(true);
                    sobokanBoard[checkBox.getYPos()][checkBox.getXPos()] = "bg";
                } else {
                    sobokanBoard[checkBox.getYPos()][checkBox.getXPos()] = "b";
                }
                return;
            }
        }
        
    }
    
    private void disposeGame() {
        
    }
    
    public int getPlayerXPos() {
        return p.getXPos();
    }
    
    public int getPlayerYPos() {
        return p.getYPos();
    }
    
}
