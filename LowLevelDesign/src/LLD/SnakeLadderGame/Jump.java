package LLD.SnakeLadderGame;

// Coordinate
class Point{
	public final int x;
	public final int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

// It represents snake or ladder depending upon start and end points
public class Jump {
	private Point start; 
	private Point end;
	
	public Jump(Point start, Point end) {
		this.start = start;
		this.end = end;
	}

	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}
}
