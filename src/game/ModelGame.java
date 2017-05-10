package game;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class ModelGame extends Observable {

    private int xSizeOfBoard;
    private int ySizeOfBoard;
    private String[][] sobokanBoard;
    private Player p;
    private List<Box> boxes;
    private String currTime;
    private long start;
    
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
        start = System.currentTimeMillis();
        
        // samantha's generator
        String[][] generatedBoard = randomGenerate(xSizeOfBoard,ySizeOfBoard);
       
        sobokanBoard = generatedBoard;
        printBoard(sobokanBoard);
        
    }
    
    private String[][] randomGenerate(int xSizeOfBoard, int ySizeOfBoard){
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
        String[][] board = new String[ySizeOfBoard][xSizeOfBoard];
        // put in the walls
        int rowcount = 0;
        for (String[] row: board){
        	if (rowcount == 0 || rowcount == ySizeOfBoard-1){
        		Arrays.fill(row, "w");
        	} else {
        		Arrays.fill(row, "0");
        	}
        	rowcount++;
        	row [0] = "w";
        	row [xSizeOfBoard-1] = "w";
        } 
        
        Random rand = new Random();
        
        // place the player
        // TODO good starting point: randomly place where the player starts

        
        int xPlayer = rand.nextInt(xSizeOfBoard-3)+1;
        int yPlayer = rand.nextInt(ySizeOfBoard-3)+1;
        p = new Player(xPlayer, yPlayer);
        board[yPlayer][xPlayer] = "p";


        
        // place 2 boxes
        // TODO good starting point: randomly place where the boxes are placed
        int noOfBoxes = 3;
        for (int i = 0; i < noOfBoxes; i++) {
        	// randomly place box - x range(2,x-3) & y range (2,y-3)       	
        	int xBox = rand.nextInt(xSizeOfBoard-5)+2;
        	int yBox = rand.nextInt(ySizeOfBoard-5)+2;
        	while (getSobokanBoardAtXY(xBox, yBox) == "b"|| getSobokanBoardAtXY(xBox, yBox) == "p"){	
        		xBox = rand.nextInt(xSizeOfBoard-5)+2;
                yBox = rand.nextInt(ySizeOfBoard-5)+2;
        	}   
            Box newBox = new Box(xBox, yBox);
            board[yBox][xBox] = "b";
            boxes.add(newBox);
            
        }
        
        // set the goal for the boxes
        // TODO good starting point: randomly place where the goals for the boxes are

        for (int i = 0; i < noOfBoxes; i++) {
        	// randomly place box - x range(2,x-2) & y range (2,y-2)       	
        	int xBox = rand.nextInt(xSizeOfBoard-3)+1;
        	int yBox = rand.nextInt(ySizeOfBoard-3)+1;
        	while (getSobokanBoardAtXY(xBox, yBox) == "b"|| getSobokanBoardAtXY(xBox, yBox) == "p"||getSobokanBoardAtXY(xBox, yBox) == "g"){	
        		xBox = rand.nextInt(xSizeOfBoard-3)+1;
                yBox = rand.nextInt(ySizeOfBoard-3)+1;
        	}   

            board[yBox][xBox] = "g";

 
        }

        
        // randomly put in walls
        int difficulty = 15; // number of walls
        for (int i = 0; i < difficulty; i++){
        	int xWall = rand.nextInt(xSizeOfBoard-3)+1;
        	int yWall = rand.nextInt(ySizeOfBoard-3)+1;
        	// if its not an important block (player, goal, box) or if it doesnt form a corner. 
        	while (board[yWall][xWall] == "b"|| board[yWall][xWall] == "p"||board[yWall][xWall]== "g"
        	// makes sure they dont form corners	
        	|| board[yWall+1][xWall+1] == "w" || board[yWall-1][xWall-1] == "w"|| board[yWall-1][xWall+1] == "w"|| board[yWall+1][xWall-1] == "w"
        	// make sure the goal isnt fully surrounded
        	|| board[yWall+1][xWall] == "g" || board[yWall-1][xWall] == "g"
        	){	
        		
        		xWall = rand.nextInt(xSizeOfBoard-3)+1;
                yWall = rand.nextInt(ySizeOfBoard-3)+1;
        	}
        	board[yWall][xWall] = "w";
        }
        
        
        return board;
	}
    
    private String[][] treeGenerate(int xSizeOfBoard, int ySizeOfBoard){
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
        String[][] board = new String[ySizeOfBoard][xSizeOfBoard];
        // put in the walls
        int rowcount = 0;
        for (String[] row: board){
        	if (rowcount == 0 || rowcount == ySizeOfBoard-1){
        		Arrays.fill(row, "w");
        	} else {
        		Arrays.fill(row, "0");
        	}
        	rowcount++;
        	row [0] = "w";
        	row [xSizeOfBoard-1] = "w";
        } 
        
        // fills in all places with a wall
        /*
        for (String[] row: board){
        	
        		Arrays.fill(row, "w");
        }
        */
        Random rand = new Random();
        
        // place the player
        // TODO good starting point: randomly place where the player starts
        
        int xPlayer = rand.nextInt(xSizeOfBoard-3)+1;
        int yPlayer = rand.nextInt(ySizeOfBoard-3)+1;
        p = new Player(xPlayer, yPlayer);
        board[yPlayer][xPlayer] = "p";

        
        // place 2 boxes
        // TODO good starting point: randomly place where the boxes are placed
        int noOfBoxes = 3;
        for (int i = 0; i < noOfBoxes; i++) {
        	// randomly place box - x range(2,x-3) & y range (2,y-3)       	
        	int xBox = rand.nextInt(xSizeOfBoard-5)+2;
        	int yBox = rand.nextInt(ySizeOfBoard-5)+2;
        	while (getSobokanBoardAtXY(xBox, yBox) == "b"|| getSobokanBoardAtXY(xBox, yBox) == "p"){	
        		xBox = rand.nextInt(xSizeOfBoard-5)+2;
                yBox = rand.nextInt(ySizeOfBoard-5)+2;
        	}   
            Box newBox = new Box(xBox, yBox);
            board[newBox.getYPos()][newBox.getXPos()] = "b";
            boxes.add(newBox);
         // boxes cant be cornered so either put two vertically(0), two horizontally(1), 1 randomly(2), or no walls(3)
            int surround = rand.nextInt(4); // randomly choose how many walls around the block
            System.out.println(surround);
            /*
            for (int j = 0; j< surround; j++){
            	
            	if (surround == 0){
            		//left
            		if (board[yBox][xBox+1] != "p" || board[yBox][xBox-1] != "b"){
            			board[yBox][xBox-1]= "0";
            		}
            		//right
            		if (board[yBox][xBox+1] != "p" || board[yBox][xBox+1] != "b"){
            			board[yBox][xBox+1]= "0";
            		}
            		
            	} else if (surround == 1){
            		// above
            		if (board[yBox-1][xBox] != "p" || board[yBox-1][xBox] != "b"){
            			board[yBox-1][xBox]= "0";
            		}
            		// below
            		if (board[yBox+1][xBox] != "p" || board[yBox+1][xBox] != "b"){
            			board[yBox+1][xBox]= "0";
            		}
            	} else if (surround == 2){
            		// above
            		if (board[yBox-1][xBox] != "p" || board[yBox-1][xBox] != "b"){
            			board[yBox-1][xBox]= "0";
            		}
            		// below
            		if (board[yBox+1][xBox] != "p" || board[yBox+1][xBox] != "b"){
            			board[yBox+1][xBox]= "0";
            		}
            		//left
            		if (board[yBox][xBox+1] != "p" || board[yBox][xBox-1] != "b"){
            			board[yBox][xBox-1]= "0";
            		}
            		//right
            		if (board[yBox][xBox+1] != "p" || board[yBox][xBox+1] != "b"){
            			board[yBox][xBox+1]= "0";
            		}
            		
            	} else {
            		// above
            		if (board[yBox-1][xBox] != "p" || board[yBox-1][xBox] != "b"){
            			board[yBox-1][xBox]= "0";
            		}
            		// below
            		if (board[yBox+1][xBox] != "p" || board[yBox+1][xBox] != "b"){
            			board[yBox+1][xBox]= "0";
            		}
            		//left
            		if (board[yBox][xBox+1] != "p" || board[yBox][xBox-1] != "b"){
            			board[yBox][xBox-1]= "0";
            		}
            		//right
            		if (board[yBox][xBox+1] != "p" || board[yBox][xBox+1] != "b"){
            			board[yBox][xBox+1]= "0";
            		}
            	}
            }
            */
        }
        
        // set the goal for the boxes
        // TODO good starting point: randomly place where the goals for the boxes are
        for (int i = 0; i < noOfBoxes; i++) {
        	// randomly place box - x range(2,x-2) & y range (2,y-2)       	
        	int xBox = rand.nextInt(xSizeOfBoard-3)+1;
        	int yBox = rand.nextInt(ySizeOfBoard-3)+1;
        	while (getSobokanBoardAtXY(xBox, yBox) == "b"|| getSobokanBoardAtXY(xBox, yBox) == "p"||getSobokanBoardAtXY(xBox, yBox) == "g"){	
        		xBox = rand.nextInt(xSizeOfBoard-3)+2;
                yBox = rand.nextInt(ySizeOfBoard-3)+2;
        	}   
            Box newBox = new Box(xBox, yBox);
            board[newBox.getYPos()][newBox.getXPos()] = "g";
            boxes.add(newBox);
            
            
            
            
        }
        
        // find a random path
        // TODO figure out the tree search
        
        
        return board;
    }
    // print board for debugging purposes
    private void printBoard(String[][] board){
        for (int i = 0; i < ySizeOfBoard; i++){
        	for (int j = 0; j<xSizeOfBoard; j++){
        		System.out.print(board[i][j]);
        	}
        	System.out.print("\n");
        }
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
            System.out.println("There are " + boxes.size() + " boxes and is box at (" + checkBox.getXPos() + "," + checkBox.getYPos() + ") at the goal? " + checkBox.isAtGoal());
        }
        setChanged();
        if (foundBoxNotAtGoal) {
            notifyObservers("MovePlayer");
        } else {
        	//TODO stop the timer
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
    
    public void updateTimer() {

        long sub = System.currentTimeMillis() - start;
        if(sub<0) return;
        int h = (int) (sub / 1000 / 60 / 60);
        int m = (int) (sub / 1000 / 60 % 60);
        int s = (int) (sub / 1000 % 60);
        String str = h + ":" + m + ":" + s;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        try{
            date = sdf.parse(str);
        }catch(Exception e){
            e.printStackTrace();
        }
        currTime = sdf.format(date);
        setChanged();
        notifyObservers("UpdateTimer");
        //return sdf.format(date);
        
    }
    
    public void resetGame() {
        
        for (Box checkBox : boxes) {
            
            if (sobokanBoard[checkBox.getYPos()][checkBox.getXPos()] == "bg") {
                checkBox.setAtGoal(false);
                sobokanBoard[checkBox.getYPos()][checkBox.getXPos()] = "g";
            } else {
                sobokanBoard[checkBox.getYPos()][checkBox.getXPos()] = "0";
            }
            checkBox.resetBox();
            sobokanBoard[checkBox.getYPos()][checkBox.getXPos()] = "b";
            
        }
        
        if (sobokanBoard[p.getYPos()][p.getXPos()] == "pg") {
            sobokanBoard[p.getYPos()][p.getXPos()] = "g";
        } else {
            sobokanBoard[p.getYPos()][p.getXPos()] = "0";
        }
        p.resetPlayer();
        sobokanBoard[p.getYPos()][p.getXPos()] = "p";
        setChanged();
        notifyObservers("ResetGame");
        
    }
    
    public void undoMove() {
        // TODO @Sam @Jath build a function so that the user can undo their last move (no of moves that's saved is up to you)
        // HINT Maybe consider using states to save the last position the player and boxes were in
        
    }
    
    public int getPlayerXPos() {
        return p.getXPos();
    }
    
    public int getPlayerYPos() {
        return p.getYPos();
    }
    
    public String getCurrTime() {
        return this.currTime;
    }
    
}
