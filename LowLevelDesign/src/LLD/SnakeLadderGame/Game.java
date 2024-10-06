package LLD.SnakeLadderGame;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Game {

	private Queue<Player> players;  // disadvantage: players can't be removed in between 
	private Board board;
	private Dice dice;
	private Player winner;
	
	private Scanner scanner;
	
	public Game() {
		this.scanner = new Scanner(System.in);
	}
	
	private void createPlayers() {
		int numOfPlayers;
		this.players = new LinkedList<>();
		// Take input from console
		System.out.println("Enter the number of players (should be atleast 2): ");
		numOfPlayers = Integer.parseInt(this.scanner.nextLine());
		
		// Add players to the queue
		for( int i= 1; i<=numOfPlayers; i++) {
			String playerName;
			System.out.println("Enter Player "+ i + " name: " );
			playerName = this.scanner.nextLine();
			
			// Create the player and keep them in players queue
			Player plr = new Player(i, playerName);
			this.players.add(plr);
		}
	}
	
	private void setUpBoard() {
		int boardSize, diceCount, snakeCount, ladderCount;
		
		System.out.println("Enter the size of the board (should be atleast 3): ");
		boardSize = Integer.parseInt(this.scanner.nextLine());
		
		this.board = new Board(boardSize);
		
		System.out.println("Enter number of snakes (should be atleast 1):");
		snakeCount = Integer.parseInt(this.scanner.nextLine());
		this.board.createJumps(snakeCount, -1); // -1 for snake
		
		System.out.println("Enter number of ladder (should be atleast 1):");
		ladderCount = Integer.parseInt(this.scanner.nextLine());		
		this.board.createJumps(ladderCount, 1); // 1 for ladder
		
		System.out.println("Enter number of dices to be used (should be atleast 1):");
		diceCount = Integer.parseInt(this.scanner.nextLine());
		
		this.dice = new Dice(diceCount);
		
	}
	
	private void setUpGame() {
		this.createPlayers();
		this.setUpBoard();
	}
	
	private void announceTheWinner() {
		if( this.winner != null ) {
			System.out.println("The winner of the game is: "+ this.winner.getName());
		}else {
			System.out.println("There is no winner!");
		}
		
		this.scanner.close();
	}

	private void startGame() {

		this.winner = null;

		while(this.winner == null) {
			
			System.out.println("***********************************************************");
			
			// get the first player from the queue
			Player currentPlayer = players.remove();
			// put the player at the last
			players.add(currentPlayer);
			
			int currentPosition = currentPlayer.getCurrentPosition();
			
			System.out.println("Current player is -> "+ currentPlayer.getName() + " and position is "+ currentPosition );
			System.out.println("I will roll dice for you :)");
			
			int diceScore = this.dice.rollDice();
			
			System.out.println("Your dice point is -> "+ diceScore);
			
			// If the player has not scored 6 yet then he can't go to the board and will be at the 0th position
//			if( currentPosition == 0 && diceScore%6 != 0 ) {
//				System.out.println("You are still at the 0th position. One 6 may help you next time!");
//				continue;
//			}
			
			int nextPosition = this.board.makeMove(currentPosition, diceScore);
			
			currentPlayer.setCurrentPosition(nextPosition);				
			
			int destination = this.board.getBoardSize()*this.board.getBoardSize();

			if( nextPosition == destination ) {
				this.winner = currentPlayer;
			}
		}
	}
	
	// Dummy method
	public void test(int pos) {
		this.board.test(pos);
	}
	
	public static void main(String[] args) {
		
		// Just one note: I have tested this and it works pretty well.

		Game game = new Game();

		// set up the game
			// create the players
			// set up the board
		game.setUpGame();
		
		// start the game
		game.startGame();	
		// winner
		game.announceTheWinner();
	}
}
