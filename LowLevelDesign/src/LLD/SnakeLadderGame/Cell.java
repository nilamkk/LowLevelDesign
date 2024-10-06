package LLD.SnakeLadderGame;

// This is the cell of the board. It has jumps.
public class Cell {
	private Jump jump;
	
	public Cell() {}
	public Cell(Jump jump) {
		this.jump = jump;
	}
	
	public boolean hasJump() {
		return this.jump != null;
	}
	
	public Jump getJump() {
		return jump;
	}
	public void setJump(Jump jump) {
		this.jump = jump;
	}
}
