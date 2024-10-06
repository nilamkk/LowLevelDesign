package LLD.SnakeLadderGame;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
	// number of dice used in the game
	private int diceCount;
	private int minNumber = 1; // dice minimum
	private int maxNumber = 6; // dice maximum
	
	public Dice(int diceCount) {
		this.diceCount = diceCount;
	}
	
	// Rolls all dices and returns the total number
	public int rollDice() {
		int result = 0;
		for(int i=0; i< diceCount; i++) {
			result += ThreadLocalRandom.current().nextInt(this.minNumber, this.maxNumber+1);
		}
		return result;
	}

	public int getDiceCount() {
		return diceCount;
	}

	public void setDiceCount(int diceCount) {
		this.diceCount = diceCount;
	}
}
