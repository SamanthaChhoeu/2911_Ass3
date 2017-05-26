package game;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ModelGame extends Observable {

    private int xSizeOfBoard;               //save
    private int ySizeOfBoard;               //save
    private String[][] sobokanBoard;        //save
    private Player p;                       //save
    private List<Box> boxes;                //save
    private String currTime;
    private long start;

    // coords to generate map 
    private int x;
    private int y;

    private int moveCounter;
    private int scoreCounter = 1000;
    private String Name;

    private Timer gameTimer;
    
    private int xGoal;
	private int yGoal;
	
	private String[][] board;
    
    public ModelGame() {
        /*boxpics = new Image[2];
    	for(int i=0; i<2; i++) {
    		boxpics[i] = Toolkit.getDefaultToolkit().getImage("Boxes\box"+i+".GIF");
    		//System.out.println(Toolkit.getDefaultToolkit().getImage("Boxes\box"+i+".GIF"));
    	}

    	paint(boxpics[0]);*/
        generateBoard();
    }
    
    
    /*public void paint(Image g){System.out.println("image drawnnnnnnnnnnnnnnnnnnn");
		//according to the map of the level, draw the image
    	for (int i = 0; i < ySizeOfBoard; i++){
        	for (int j = 0; j<xSizeOfBoard; j++){
        		if(sobokanBoard[i][j].equals("w"))
        			//g.
			}		
    		}
	}*/
    
    public void generateBoard() {
        
        // change this so it changes based on difficulty
        // keep difficulty on a 3:2 aspect ratio
        this.xSizeOfBoard = 15;
        this.ySizeOfBoard = 10;
        this.moveCounter = 0;
        this.scoreCounter = 1000;
        this.sobokanBoard = new String[ySizeOfBoard][xSizeOfBoard];
        this.boxes = new ArrayList<Box>();
        start = System.currentTimeMillis();
        
        // samantha's generator
        board = reverseGenerate(xSizeOfBoard,ySizeOfBoard);
        
        sobokanBoard = board;
        printBoard(sobokanBoard);
        //printPathToGoal();
        
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
        	while (board[yBox][xBox]  == "b"|| board[yBox][xBox] == "p"){	
        		xBox = rand.nextInt(xSizeOfBoard-5)+2;
                yBox = rand.nextInt(ySizeOfBoard-5)+2;
        	}   
            Box newBox = new Box(xBox, yBox);
            board[yBox][xBox] = "b";
            boxes.add(newBox);
            
        }
        
        // set the goal for the boxes
        // TODO good starting point: randomly place where the goals for the boxes are
        int goal = 0;
        for (int i = 0; i < noOfBoxes; i++) {
        	// randomly place box - x range(2,x-2) & y range (2,y-2)       	
        	int xBox = rand.nextInt(xSizeOfBoard-3)+1;
        	int yBox = rand.nextInt(ySizeOfBoard-3)+1;
        	while (board[yBox][xBox]  == "b"|| board[yBox][xBox] == "p"|| board[yBox][xBox] == "g"){	
        		xBox = rand.nextInt(xSizeOfBoard-3)+1;
                yBox = rand.nextInt(ySizeOfBoard-3)+1;
        	}   

            board[yBox][xBox] = "g";
            goal++;

 
        }
        System.out.println(goal);

        
        // randomly put in walls
        // i upped the number of walls to see where theres errors
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
    
    
    private String[][] reverseGenerate(int xSizeOfBoard, int ySizeOfBoard){
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
        board = new String[ySizeOfBoard][xSizeOfBoard];
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
        
        // randomly put in walls. 
        int difficulty = 20; // difficulty affects number of walls
        for (int i = 0; i < difficulty; i++){
        	int xWall = rand.nextInt(xSizeOfBoard-3)+1;
        	int yWall = rand.nextInt(ySizeOfBoard-3)+1;
        	board[yWall][xWall] = "w";
        	System.out.println("============================== BEFORE FILL ==============================");
        	printBoard(board);
        	System.out.println("============================== BEFORE FILL ==============================");
        	fill(); 
        	
        	
        }
        
        // add player
        int xPlayer = rand.nextInt(xSizeOfBoard-3)+1;
        int yPlayer = rand.nextInt(ySizeOfBoard-3)+1;
        // make sure we arent placing player on a wall, goal or box, also make sure we can move the player around. 
        while (board[yPlayer][xPlayer] == "w"){
        	xPlayer = rand.nextInt(xSizeOfBoard-3)+1;
            yPlayer = rand.nextInt(ySizeOfBoard-3)+1;
        }
        p = new Player(xPlayer, yPlayer);
        board[yPlayer][xPlayer] = "p";

        // first box
        board = boxPull();
        board = boxPull();
        board = boxPull();
        // second box
        
        // board = boxTurn(board);
        //third box
        
        return board;
     
    }
    
    
    // TODO algorithm to fill in useless space
    private String[][] fill(){

    	
    	// go through each coordinate
    	for (int y = 1; y<ySizeOfBoard-1; y++){
    		for (int x = 1; x<xSizeOfBoard-1; x++){
    			// check space above
    	    	if (board[y][x] == "0"){
    	    		// make the space in the middle a wall
    	    		if (countAround(x, y, board)==4){
    	    			//board[y][x] = "w";
    	    		}
    	    	// checkerboard
    	    	} else if (board[y][x] == "w"){
    	    		if (board[y-1][x-1] == "w" /*&& board[y][x+1] == "0" && board[y+1][x] == "0"*/){
        	    		board[y][x-1] = "w";
        	    		board[y-1][x] = "w";
        	    	} else if (board[y-1][x+1] == "w"){
    	    			board[y-1][x] = "w";
    	    			//board[y][x+1] = "w";
    	    		}
    	    	}
    	     	
    		}
    	}
    	
		return board;
    	
    }
    
    
    
    // count the number of walls around the current space
    private int countAround(int x, int y, String[][] board){
    	int count=0;
    	
    	// above
    	if (board[y-1][x] == "w"){
    		count ++;
    	}
    	// right
    	if (board[y][x+1] == "w"){
    		count ++;
    	}
    	// below
    	if (board[y+1][x] == "w"){
    		count ++;
    	}
    	// left
    	if (board[y][x-1] == "w"){
    		count ++;
    	}
    	
    	return count;
    }
    
    
    // function for going straight until you can't and then turning
    // x coord, y coord
    // direction up-0, right-1, down-2, left-3
    private String[][] goStraight(int direction){
    	// up
    	if (direction == 0){
  		
    		while (goUp()){
    	
    		// if can't access y-1,x (then iterate down)
    			
	    		System.out.println("up");
	    		y--;
    		}
    		//board[y][x] = "x";
    	// right
    	} else if (direction == 1){

    		while (goRight()){

    			System.out.println("right");
    			x++;
    		}
    		//board[y][x] = "x";
        	// down
        		
        	} else if (direction == 2){
        		while (goDown()){
        			System.out.println("down");
        			//System.out.println(board[y+2][x] + board[y+3][x] + board[y+2][x-1] + board[y+2][x+1]);
        			y++;
        		}
        		//board[y][x] = "x";
        	// left
        	} else if (direction == 3){
        		while (goLeft()){
           			System.out.println("left");
        			x--;
    		}
    		//board[y][x] = "x";
    	}
    	System.out.println("calc coords"+x+"and"+y);
		return board;
    	
    }
    
    

    private boolean goRight(){
    	// avoid this situation
		// w0
    	// 0w
    	// w0
    	if (!clear(y,x+1) && board[y][x+2] != "w"  &&  !(board[y][x+2] == "w"  && !(board[y+1][x+1] == "w" && board[y-1][x+1] == "w"))){
    		return true;
    	}
    	return false;
    }
    
    private boolean goLeft(){
    	// avoid this situation

    	// 0w
    	// w0
    	// 0w
    	if (clear(y,x-1) && board[y][x-2] != "w" && !(board[y][x-2] == "w"&& (board[y+1][x-1] == "w"&& board[y-1][x-1] == "w" ))){
    		return true;
    	}
    	return false;
    }
    
    private boolean goUp(){
    	// avoid this situation
    	// owo
    	// wow
    	if (!clear(y-1,x) && board[y-2][x] != "w" && !(board[y-2][x] == "w" && (board[y-1][x+1] == "w" && board[y-1][x-1] ==  "w"))){
    		return true;
    	}
    	return false;
    }
    
    private boolean goDown(){
    	if (!clear(y+1,x) && board[y+2][x] != "w" && !(board[y+2][x] == "w" && (board[y+1][x-1] == "w" && board[y+1][x+1] == "w"))){
    		return true;
    	}
    	
    	return false;
    }
    
    // TODO Algorithm to generate the path of the first box
    // generates walls randomly
    // then puts a goal at a random locations.
    // pretends to put a block there (2x1 to represent block and player) and then reverse to a random location
    // the new location is now where the block starts.
    // this works because we reverse which is the opposite of pushing
    private String[][] boxPull(){
    	Random rand = new Random();
    	// randomly place box - x range(2,x-2) & y range (2,y-2)       	
    	xGoal = rand.nextInt(xSizeOfBoard-3)+1;
    	yGoal = rand.nextInt(ySizeOfBoard-3)+1;
    	
    	while (board[yGoal][xGoal]  == "b"|| board[yGoal][xGoal] == "g" || board[yGoal][xGoal] == "p"||board[yGoal][xGoal] == "w"){	
    		xGoal = rand.nextInt(xSizeOfBoard-3)+1;
            yGoal = rand.nextInt(ySizeOfBoard-3)+1;
            
    	}   

        
        
        // move in random directions with a 2x1 block and 
        // can make it so that each block gets a random algorithm
        // 	 box1 is pulled until it reaches a wall and then turns -- trying to implement this first
        //	 box2 is pulled and always tries to turn
        //   box3 is pulled and uses a mix of both?
        
        
        this.x = xGoal;
        this.y = yGoal;
        int turns = 0;
        int vert = -1; // records if last move was vertical or horizontal (-1 initially, 0 if horizontal, 1 if vertical)
        
        while (turns < 3){
        	// randomises direction up/down left/right
        	int dir = rand.nextInt(2);
        	System.out.println("random direction is:"+dir);
        	
        	// up/down
        	if (vert != 1){
        		// up first
        		if (dir == 1){
        			// if box is free to be pulled upwards
        			if (board[y-1][x] != "w" && board[y-2][x] != "w" ){
                    	//board[y-1][x] = "x";
                    	board = goStraight(0);
                    	// if end of straight is unreachable then iterate back down one
                    	if (!clear(y-1,x) && (!clear(y,x-1) || !clear(y,x+1))){
                    		y++;
                    	}
                    	turns ++;
                    	vert = 1;
  
                   	
                    // if box is free to be pulled downwards
                    } else if (board[y+1][x] != "w" && board[y+2][x] != "w" ){
                    	//board[y+1][x] = "x";
                    	board = goStraight(2);
                    	// if end of straight is unreachable then iterate upwards
                    	if (!clear(y+1,x) && (!clear(y,x-1) || !clear(y,x+1))){
                    		y--;
                    	}
                    	vert = 1;
                    	turns++;
     
                    }else {
                    	turns = 3;
                    }
        		}
        		
        		// down first
        		else if (dir == 0){
        			// if box is free to be pulled downwards
                    if (board[y+1][x] != "w" && board[y+2][x] != "w" ){
                    	//board[y+1][x] = "x";
                    	board = goStraight(2);
                    	// if end of straight is unreachable then iterate upwards
                    	if (!clear(y+1,x) && (!clear(y,x-1) || !clear(y,x+1))){
                    		y--;
                    	}
                    	vert = 1;
                    	turns++;
          
                    	
                    // if box is free to be pulled upwards
                    } else if (board[y-1][x] != "w" && board[y-2][x] != "w"   ){
                    	//board[y-1][x] = "x";
                    	board = goStraight(0);
                    	// if end of straight is unreachable then iterate back down one
                    	if (!clear(y-1,x) && (!clear(y,x-1) || !clear(y,x+1))){
                    		y++;
                    	}
                    	turns ++;
                    	vert = 1;
                    	
                    }else {
                    	turns = 3;
                    }
                    
        		}
        		dir = rand.nextInt(1);
        	// randomiser for left/right
        		
        	} else {
        		// left first
        		if(dir == 1){
        			// if box is free to be pulled left
        			if (board[y][x-1] != "w" && board[y][x-2] != "w" ){
                    	//board[y+1][x-1] = "x";
                    	board = goStraight(3);
                    	// if end of straight is unreachable then iterate back to te right
                    	if ((!clear(y-1,x) || !clear(y+1,x)) && !clear(y,x-1)){
                    		x++;
                    	}
                    	vert = 0;
                    	turns++;
   
                    //if box is free to be pulled right
        			} else if (board[y][x+1] != "w" && board[y][x+2] != "w"  ){
                    	//board[y][x+1] = "x";
                    	board = goStraight(1);
                    	// if end of straight is unreachable then iterate back to the left
                    	if ((!clear(y-1,x) || !clear(y+1,x))&& !clear(y,x+1) ){
                    		x--;
                    	}
                    	vert = 0;
                    	turns++;
                    	
                    
                    }else {
                    	turns = 3;
                    }
        			
        		
        	
        		// right first
        		} else {
        			// if box is free to be pulled right
                    if (board[y][x+1] != "w" && board[y][x+2] != "w" ){
                    	//board[y][x+1] = "x";
                    	board = goStraight(1);
                    	// if end of straight is unreachable then iterate back to the left
                    	if ((!clear(y-1,x) || !clear(y+1,x))&& !clear(y,x+1) ){
                    		x--;
                    	}
                    	vert = 0;
                    	turns++;
                    	dir = rand.nextInt(1);
                    // if box is free to be pulled left
                    } else if (board[y][x-1] != "w" && board[y][x-2] != "w" ){
                    	//board[y+1][x-1] = "x";
                    	board = goStraight(3);
                    	// if end of straight is unreachable then iterate back to te right
                    	if ((!clear(y-1,x) || !clear(y+1,x)) && !clear(y,x-1) ){
                    		x++;
                    	}
                    	vert = 0;
                    	turns++;
                    
            		}else {
                    	turns = 3;
                    }
                    dir = rand.nextInt(1);
        		}
   
        	}

        }
        System.out.println("TEST goal coords"+"x: "+xGoal+" y: "+yGoal);
    	System.out.println("TEST box coords"+"x: "+x+" y: "+y);
        // if the new box is on an existing goal then ignore and try to find a new goal plus start box location
        if (board[y][x] == "b" || board[y][x] == "g"||board[y][x] == "p" || board[yGoal][xGoal]=="b"|| board[yGoal][xGoal]=="g"||board[yGoal][xGoal]=="p"){
        	boxPull();
        } else if (y == yGoal && x==xGoal){	
        	boxPull();
    	} else {
        	System.out.println("goal coords"+"x: "+xGoal+" y: "+yGoal);
        	System.out.println("box coords"+"x: "+x+" y: "+y);
        	board[yGoal][xGoal] = "g";
        	Box newBox = new Box(x, y);
            board[y][x] = "b";
            boxes.add(newBox);
            
        }
		return board;
    }
    
    // function that checks if a coordinate is a wall or a box
    private boolean clear(int y, int x){
    	if (board[y][x] == "w" || board[y][x] == "b"||board[y][x] == "p"){ // should maybe comment player out
    		return false;
    	} 
    	
    	return true;
    }
    
    
    
    // TODO algorithm to generate the path of the second box
    private String[][] boxTurn(){
    	
    	Random rand = new Random();
    	// randomly place box - x range(2,x-2) & y range (2,y-2)       	
    	int xGoal = rand.nextInt(xSizeOfBoard-3)+1;
    	int yGoal = rand.nextInt(ySizeOfBoard-3)+1;
    	
    	while (board[yGoal][xGoal]  == "b"|| board[yGoal][xGoal] == "g" || board[yGoal][xGoal] == "p" ||board[yGoal][xGoal] == "w"){	
    		xGoal = rand.nextInt(xSizeOfBoard-3)+1;
            yGoal = rand.nextInt(ySizeOfBoard-3)+1;

            
    	}  
        
        
        x = xGoal;
        y = yGoal;
    	
    	int turn;
    	int prev = -1;
    	for (int i = 0; i < 10; i++){
    		turn = turn(prev);
    		prev = turn;
    		// turn - is the previous move where 0 up; 1 down; 2 left; 3 right
    		if (turn == 0){
    			System.out.println("up");
    			y--;
    		} else if (turn == 1){
    			System.out.println("down");
    			y++;
    		} else if (turn == 2){
    			System.out.println("left");
    			x--;
    		} else if (turn == 3){
    			System.out.println("right");
    			x++;
    		}
    	}
    	if (board[y][x] == "b" || board[y][x] == "g"||board[yGoal][xGoal]=="b"||board[yGoal][xGoal]=="p"){
        	boxTurn();
        } else if (y == yGoal && x==xGoal){	
        	boxTurn();
    	} else {
        	System.out.println("goal coords"+"x: "+xGoal+" y: "+yGoal);
        	System.out.println("box coords"+"x: "+x+" y: "+y);
        	board[yGoal][xGoal] = "g";
        	Box newBox = new Box(x, y);
            board[y][x] = "b";
            boxes.add(newBox);
            
        }
		return board;

    }
    
    
    private int count(int dir){
    	
    	// store x and y
    	int xco = x;
    	int yco = y;
    	
    	
    	// dir 0 up; 1 down; 2 left; 3 right
    	int count = 0;
    	
    	if (dir == 0){
    		while (goUp()){
    			
    		}
    	} else if (dir == 1){
    		
    	} else if (dir == 2){
    		
    	} else if (dir == 3){
    		
    	}
    	
    	return count;
    }

    // TODO function for always try to turn
    // return the next move that the generator will make to create a path betwen the goal and box
    // last - is the previous move where 0 up; 1 down; 2 left; 3 right
    private int turn(int last){
    	Random rand = new Random();
    	
    	int next = 0;
    	
    	
    	// if last move was vertical
    	if (last == 0 || last == 1){
    		// try to go left or right
    		// if you can go left and right
    		if (goLeft() && goRight()){
				int rng = rand.nextInt(2);
				if (rng == 0){
					// go left
					return 2;
				} else {
					// go right
					return 3;
				}
				
    		// if you can only go left
    		} else if(goLeft()){
    			return 2;
    		// if you can only go right
    		} else if (goRight()){
    			return 3;
    		// otherwise continue in the direction you were going
    		} else {
    			return last;
    		}
    		
    		
    	// if last move was horizontal
    	} else if (last == 2 || last == 3){
    		
    		// try to go up or down
    		// if you can go up and down
    		if (goUp() && goDown()){
    			int rng = rand.nextInt(2);
				if (rng == 0){
					// go up
					return 0;
				} else {
					// go down
					return 1;
				}
    		// if you can only go up
    		} else if(goUp()){
    			return 0;
    		// if you can only go down
    		} else if (goDown()){
    			return 1;
    		// otherwise continue in the direction you were going	
    		} else {
    			return last;
    		}
    		
    	// if it is the first move
    	} else{
    	
    	}
    	
    	return next;
    }
    
    
    // TODO function that gets the x and y range of a space and then checks if the goal lies within it. 
    private void canReach(){
    	
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
        
        if (sobokanBoard[yPos][xPos] == "w" ) {
            return "Wall";
        } else if (sobokanBoard[yPos][xPos] == "b" || sobokanBoard[yPos][xPos] == "bg") {
            return "Box";
        } else if (sobokanBoard[yPos][xPos] == "0"){
            return "Free";
        } else {
        	return "Free";
        }
        
    }
    
    private boolean checkMovableBox(int xPos, int yPos) {
        if (sobokanBoard[yPos][xPos] == "0" || sobokanBoard[yPos][xPos] == "g" ) {
            return true;
        } else {
            return false;
        }
    }
    
    private void movePlayer(int xPos, int yPos, String direction) {

        moveCounter++;
        scoreCounter--;
        // storing current location of player before moving.
        Player current = p.clone();
        Player previous = p.clone();
        current.setPrevPlayer(previous);

        // return to just goal if player leaves a goal square
        if (sobokanBoard[yPos][xPos] == "pg") {
            sobokanBoard[yPos][xPos] = "g";
        } else {
            sobokanBoard[yPos][xPos] = "0";
        }
        
        if (direction == "Left") {
            current.setXPos(xPos - 1);
        } else if (direction == "Right") {
            current.setXPos(xPos + 1);
        } else if (direction == "Up") {
            current.setYPos(yPos - 1);
        } else if (direction == "Down"){
            current.setYPos(yPos + 1);
        }
        
        // go to player in goal square if player is entering a goal square
        if (sobokanBoard[current.getYPos()][current.getXPos()] == "g") {
            sobokanBoard[current.getYPos()][current.getXPos()] = "pg";
        } else {
            sobokanBoard[current.getYPos()][current.getXPos()] = "p";
        }
        current.setMove(moveCounter);
        p = current;
        boolean foundBoxNotAtGoal = false;
        // check if all boxes are in the goal
        for (Box checkBox : boxes) {
            if (!checkBox.isAtGoal()) foundBoxNotAtGoal = true;
            //System.out.println("There are " + boxes.size() + " boxes and is box at (" + checkBox.getXPos() + "," + checkBox.getYPos() + ") at the goal? " + checkBox.isAtGoal());
        }
        setChanged();
        if (foundBoxNotAtGoal) {
            notifyObservers("MovePlayer");
        } else {
            gameTimer.cancel();
            storeScore();
            notifyObservers("ChangeScreenWin");
        }
    }

    private void storeScore(){
        int lines = getAmountOfLines("leaderBoard.txt");

        if(this.Name == null){
            this.Name = ("UnNamed" + lines);
        }

        //writes appends to file
        try(FileWriter fw = new FileWriter("leaderBoard.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(lines + "//" +this.Name + "//" +this.scoreCounter);
        } catch (IOException e) {
            System.out.println("Issue with writing out talk to ---> Jathurson");

        }

        //order score
        orderFile("leaderBoard.txt");
    }

    private void orderFile(String filename){
        ArrayList<User> users = new ArrayList<>();
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(filename));
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] l = line.split("//");
                User curr = new User(l[1], Integer.parseInt(l[2]));
                users.add(curr);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Issue with reading and or sorting out talk to ---> Jathurson");
        } finally {
            if (sc != null) sc.close();
        }
        Collections.sort(users);
        try {
            PrintWriter writer = new PrintWriter(filename);
            writer.print("");
            writer.close();
        }catch (FileNotFoundException e){
            System.out.println("Issue with reading and or sorting out talk to ---> Jathurson");
        }
        int i =0;
        for(User u : users) {
            i++;
            try (FileWriter fw = new FileWriter("leaderBoard.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(i + "//" + u.getName() + "//" + u.getScore());
            } catch (IOException e) {
                System.out.println("Issue with writing out talk to ---> Jathurson");
            }
        }
    }

    private int getAmountOfLines(String filename){
        try(FileReader fr = new FileReader(filename))
        {
            LineNumberReader lnr = new LineNumberReader(fr);
            lnr.skip(Long.MAX_VALUE);
            int length = lnr.getLineNumber()+1;
            lnr.close();
            return length;
        }catch (IOException e){
            System.out.println("Issue with writing out talk to ---> Jathurson");
            return 0;
        }
    }

    private void moveBox(int xPos, int yPos, String direction) {
        
        for (Box checkBox : boxes) {
            if (checkBox.getXPos() == xPos && checkBox.getYPos() == yPos) {
                Box current  = checkBox.clone();
                Box previous = checkBox.clone();
                current.setPrevBox(previous);
                current.setMove(moveCounter);

                if (sobokanBoard[yPos][xPos] == "bg") {
                    current.setAtGoal(false);
                    sobokanBoard[yPos][xPos] = "g";
                } else {
                    sobokanBoard[yPos][xPos] = "0";
                }
                if (direction == "Left") {
                    current.setXPos(xPos - 1);
                } else if (direction == "Right") {
                    current.setXPos(xPos + 1);
                } else if (direction == "Up") {
                    current.setYPos(yPos - 1);
                } else {
                    current.setYPos(yPos + 1);
                }

                if (sobokanBoard[current.getYPos()][current.getXPos()] == "g") {
                    current.setAtGoal(true);
                    sobokanBoard[current.getYPos()][current.getXPos()] = "bg";
                } else {
                    sobokanBoard[current.getYPos()][current.getXPos()] = "b";
                }
                boxes.remove(checkBox);
                boxes.add(current);
                return;
            }
        }
        
    }
    
    private String Mode;
    
    public void setMode (String input){
    	Mode = input;
    }
    
    public void startTimer() {
        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
            	long current = System.currentTimeMillis();
                long sub = current - start;
                //System.out.println(sub);
                //System.out.println(start);
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
                    System.out.println("Issue with Timer");
                }
                currTime = sdf.format(date);
                setChanged();
                notifyObservers("UpdateTimer");
            	if(Mode.equals("Resume")) {
                    start += 500;
                }
            }
        },0,500);//refresh every 1/2 second with no delay.
    }
    
    
    //cancel the timer when we don't need it.
    //interesting to know that if we do not have this function invoked, the timer will go BACKWARDS?
    public void cancelTimer() {
    	gameTimer.cancel();
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

        // undo player position
        if(p.getPrevPlayer() != null) {
            moveCounter--;
            if (sobokanBoard[p.getYPos()][p.getXPos()] == "pg") {
                sobokanBoard[p.getYPos()][p.getXPos()] = "g";
            } else {
                sobokanBoard[p.getYPos()][p.getXPos()] = "0";
            }
            p = p.getPrevPlayer();
            if (sobokanBoard[p.getYPos()][p.getXPos()] == "g") {
                sobokanBoard[p.getYPos()][p.getXPos()] = "pg";
            } else {
                sobokanBoard[p.getYPos()][p.getXPos()] = "p";
            }

            for (Box checkBox : boxes) {
                if (checkBox.getMove() == p.getMove() ) {
                    if (checkBox.getPrevBox() != null) {

                        if (sobokanBoard[checkBox.getYPos()][checkBox.getXPos()] == "bg") {
                            checkBox.setAtGoal(false);
                            sobokanBoard[checkBox.getYPos()][checkBox.getXPos()] = "g";
                        } else {
                            sobokanBoard[checkBox.getYPos()][checkBox.getXPos()] = "0";
                        }

                        Box previous = checkBox.getPrevBox();

                        if (sobokanBoard[previous.getYPos()][previous.getXPos()] == "g") {
                            previous.setAtGoal(true);
                            sobokanBoard[previous.getYPos()][previous.getXPos()] = "bg";
                        } else {
                            sobokanBoard[previous.getYPos()][previous.getXPos()] = "b";
                        }

                        boxes.remove(checkBox);
                        boxes.add(previous);
                        break;
                    }
                }
            }
        }
        scoreCounter = scoreCounter - 4;
        setChanged();
        notifyObservers("UndoMove"); // don't know much about this line so change it thanks
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

    public void setName(String n){
        this.Name = n;
    }

    //give goal points for the player to reach
    //when i figure it out it will return some sort of path
    private ArrayList<Node> Path (int xStart, int yStart, int xGoal, int yGoal){

        ArrayList<Node> spaces= new ArrayList<Node>();
        int x,y;

        for(y=1;y<ySizeOfBoard;y++) {
            for (x = 1; x < xSizeOfBoard; x++) {
                if (sobokanBoard[y][x] == "0" || sobokanBoard[y][x] == "p") {
                    Node n = new Node(x, y);
                    spaces.add(n);
                }
            }
        }
        for(Node temp : spaces){
            if (sobokanBoard[temp.getY()+1][temp.getX()] == "0" || sobokanBoard[temp.getY() + 1][temp.getX()] == "p") {
                Node t = new Node(temp.getX(),  temp.getY()+ 1);
                for(Node curr : spaces){
                    if(curr.equals(t)){
                        Edge e = new Edge(curr);
                        temp.addEdge(e);
                    }
                }
            }
            if (sobokanBoard[temp.getY()][temp.getX() + 1] == "0" || sobokanBoard[temp.getY()][temp.getX() + 1] == "p") {
                Node t = new Node(temp.getX()+1,  temp.getY());
                for(Node curr : spaces){
                    if(curr.equals(t)){
                        Edge e = new Edge(curr);
                        temp.addEdge(e);
                    }
                }
            }
            if (sobokanBoard[temp.getY() - 1][temp.getX()] == "0" || sobokanBoard[temp.getY() - 1][temp.getX()] == "p") {
                Node t = new Node(temp.getX(), temp.getY() - 1);
                for(Node curr : spaces){
                    if(curr.equals(t)){
                        Edge e = new Edge(curr);
                        temp.addEdge(e);
                    }
                }
            }
            if (sobokanBoard[temp.getY()][temp.getX() - 1] == "0" || sobokanBoard[temp.getY()][temp.getX() - 1] == "p") {
                Node t = new Node(temp.getX()-1, temp.getY());
                for(Node curr : spaces){
                    if(curr.equals(t)){
                        Edge e = new Edge(curr);
                        temp.addEdge(e);
                    }
                }
            }
        }

        //printList(spaces);
            //basically now i have a list of nodes with a list of edges
            //right now the edges are nodes since i haven't added a cost
            //but i'm using edges incest i an in the future

            //so now
            //best solution the cheapest
        Node pl = new Node (xStart, yStart);
        Node temp = new Node();
        for(Node s : spaces){
            if(s.equals(pl)){
                temp = s;
                System.out.println("Start at " + temp.getX() + "," + temp.getY());
                System.out.println("Goal at " + xGoal + "," + yGoal);
                break;
            }
        }
        /* testing if map is stored properly
        for(Node n : spaces){
            ArrayList<Edge> edges = n.getEdges();
            System.out.println("(" + n.getX() + "," + n.getY() + ")");
            for(Edge e : edges){
                System.out.println("--> (" + e.getOtherNode().getX() + "," + e.getOtherNode().getY() + ")");
            }
        }
        */
        ArrayList<Node> been = new ArrayList<>();
        Queue<State> pq = new PriorityQueue<State>();
        State startState = new State(temp, null);
        pq.add(startState);
        ArrayList<Node> path = new ArrayList<>() ;


        while (!pq.isEmpty()) {
            State currentState = pq.poll();
            been.add(currentState.getCurrent());
            if (currentState.isAtGoal(xGoal, yGoal)) {
                printList(currentState.makePath()); //prints in terminal to see can be removed
                return currentState.makePath();
            }
            //System.out.println("test");
            ArrayList<State> nextStates = currentState.generateSuccessors(been);

            for (State next : nextStates) {
                next.calculateGCost();
                next.calculateHCost(xGoal, yGoal);
                pq.add(next);
            }
        }
        System.out.println("No solution");
        return null;
    }

    private void printList(ArrayList<Node> path){
        for(Node n : path){
            System.out.println(n.getX() + " " + n.getY());
        }
    }

    //funtion i wrote to test my path search
    private void printPathToGoal(){

        int x = 0;
        int y;
        boolean broke = false;
        for(y=1;y<ySizeOfBoard;y++) {
            for (x = 1; x < xSizeOfBoard; x++) {
                if (sobokanBoard[y][x] == "0" || sobokanBoard[y][x] == "p") {
                    broke = true;
                    break;
                }
            }
            if(broke){
                break;
            }
        }
        System.out.println("("+x+","+y+")");
        ArrayList<Node> path = Path(p.getXPos(),p.getYPos(),x,y);
    }


    //Saving The board of the player
    public void SaveGame(){
        int lines = getAmountOfLines("leaderBoard.txt");

        if(this.Name == null){
            this.Name = ("UnNamed" + lines);
        }

        String filename = "saves/" + this.Name;
        try(FileWriter fw = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            //this is for printing the Board
            String[][] board = this.sobokanBoard;
            for (int i = 0; i < ySizeOfBoard; i++){
                for (int j = 0; j<xSizeOfBoard; j++){
                    out.print(board[i][j]);
                }
                out.print("\n");
            }
            out.print("//");
            //last thin
        } catch (IOException e) {
            System.out.println("Issue with writing out talk to ---> Jathurson");

        }

    }
}
