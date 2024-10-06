package LLD.SnakeLadderGame;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
	private ArrayList<ArrayList<Cell>> cells;
	
	// So for n*n board size, we are maintaining (n+1)*(n+1) size board
	// So that we use 1 indexing cells
	// But the user should feel like it is a board of n*n
	public Board(int boardSize) {
		boardSize++;		
		// Create the board with cells
		this.cells = new ArrayList<ArrayList<Cell>>();
		
		for(int i=0; i< boardSize; i++) { 
			// create the ith row
			ArrayList<Cell> ithCells = new ArrayList<Cell>( );
			for( int j=0; j< boardSize; j++) {
				ithCells.add(new Cell());
			}
			// push it to the board
			this.cells.add( ithCells );
		}
		this.sendSuccessMessage();
	}
	
	private void sendSuccessMessage() {
		System.out.println("The board of size "+this.getBoardSize()+" is created.");
		System.out.println("The path is from 1 to "+this.getBoardSize()*this.getBoardSize()+".");		
	}
	
	// Converts number between 1 to n*n to coordinate on snake ladder board following the zig zag pattern
	// (1,1) => 100   (1,2) => 99  ... (1,10) => 91
	// (2,1) => 81   ...               (2,10) => 90
	// ...
	// (10,1) => 1   (10,2) => 2  ...  (10,10) => 10
	private Point convertNumberToPoint(int num) { // number in game to coordinates
		int n = this.getBoardSize();  
		
		// Get the x coordinate on a normal board where rows are 1, 2, 3, ..., 11, 12, 13,...
		int xCoorNorm = (num/n) + ( num%n == 0 ? 0 : 1 ); // on a board where 1 is at position (1,1) and n*n is at position (n,n)
		// Since on snake board starts from the last row so normal x coordinates will have to be converted
		int xCoorSLBoard = ( n - ( xCoorNorm - 1 )); // this will convert 1 to n, 2 to n-1. Basically reverse the x coordinates to match it like board
		
		// Get the normal board coordinate like the above
		int yCoorNorm = (num%n) == 0 ? n : (num%n); // on a normal board
		// Now reverse as needed
		int yCoorSLBoard = (xCoorNorm&1) == 1 ? yCoorNorm : ( n - (yCoorNorm - 1) ); // for even normal x coordinates reverse the y coordinates
		
		return new Point( xCoorSLBoard, yCoorSLBoard );
	}
	
	private int convertPointToNumber(Point p){
		int n = this.getBoardSize();
		
		int xCoorNorm = n - (p.x - 1); // convert the snake board coordinate to normal coordinate
		int yCoorNorm = (xCoorNorm & 1) == 1 ? p.y : ( n - ( p.y - 1 ) ); // convert the snake board coordinate to normal coordinate
		
		return (xCoorNorm-1)*n + yCoorNorm; // take example of 10*10 for better understanding
	}
	
	private Cell getCellAtPoint(Point p){
		return this.cells.get(p.x).get(p.y);
	}
	
	public void createJumps(int count, int jp) {
		// jp -> -1 for snake
		// jp ->  1 for ladder
		
		String jumpName = jp == 1 ? "Ladder" : "Snake";
		int n = this.getBoardSize(); // for 10*10 game it will return 10 not 11
		
		while( count > 0 ) {
			
			int jumpStart = ThreadLocalRandom.current().nextInt(1, n*n - 1); // generate randomly 
			int jumpEnd = ThreadLocalRandom.current().nextInt(1, n*n - 1);
			
			if( jp == 1 && jumpStart > jumpEnd ) {
				continue;
			}else if( jp == -1 && jumpStart < jumpEnd ) {
				continue;
			}
			
			// Generate Jump
			Point jumpStartPoint = this.convertNumberToPoint(jumpStart); // convert number to Point 
			Point jumpEndPoint = this.convertNumberToPoint(jumpEnd); // convert number to Point
			
			Jump oJump = new Jump(jumpStartPoint, jumpEndPoint);
			
			Cell jumpCell = this.getCellAtPoint(jumpStartPoint); // get the jump cell at jump start point from the board 
			
			jumpCell.setJump(oJump);
			
			count--;
			
			System.out.println( jumpName + " created from position "+ jumpStart + " to "+ jumpEnd + ".");
		}
		
	}
	
	public int makeMove(int currentPosition, int diceScore) {
		int n = this.getBoardSize();
		// calculates the next position
		int newPosition = currentPosition+ diceScore;
		// if out of board then stay at the same position and inform
		if( newPosition > n*n ) {
			System.out.println("Your new position "+newPosition+" is beyond the board. So better luck next time :(");
			return currentPosition;
		}
		
		Point newPoint = this.convertNumberToPoint(newPosition);
		Jump oJump = this.getCellAtPoint( newPoint ).getJump();
		// if no ladder and snake then return
		if( oJump == null ) {
			System.out.println("You are moving from "+currentPosition+" to "+ newPosition+".");
			return newPosition;
		}

		// There is a snake or ladder
		Point newPointAfterJump = oJump.getEnd();
		int newPositionAfterJump = this.convertPointToNumber(newPointAfterJump); // get the new position after jump 
		
		String jumpThing = newPosition < newPositionAfterJump ? "Ladder" : "Snake";
		System.out.println("Your new position "+newPosition+ " has one "+jumpThing+" which is taking you to position "+ newPositionAfterJump+".");
		
		return newPositionAfterJump;
	}
	
	// For 10*10 board it will return 10
	// Technically we maintain 11*11 board
	public int getBoardSize() {
		return this.cells.size()-1; // since we maintain the size of the board as boardSize+1
	}
	
	
	// Dummy method
	public void test( int position ) {
		
		Point p = this.convertNumberToPoint(position);
		
		System.out.println("The point of position "+ position+ " is ("+p.x+", "+p.y+")");
		
		int revPos = this.convertPointToNumber(p);
		
		System.out.println("The reverse position is: "+revPos);
		
		
	}
}
