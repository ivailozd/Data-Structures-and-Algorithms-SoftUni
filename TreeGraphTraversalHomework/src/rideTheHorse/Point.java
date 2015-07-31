package rideTheHorse;

import java.util.LinkedList;
import java.util.List;

public class Point {
	
	private int x;
	private int y;
	private List<Point> neighbors = new LinkedList<>();

	public Point(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public int getX() {
		return x;
	}
	
	private void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	private void setY(int y) {
		this.y = y;
	}
	
	public List<Point> getNeighbors() {
		if (neighbors.isEmpty()) {
			setNeighbors();
		}
		
		return neighbors;
	}

	private void setNeighbors() {
		neighbors.add(new Point(x + 1, y - 2));
		neighbors.add(new Point(x + 2, y - 1));
		neighbors.add(new Point(x + 2, y + 1));
		neighbors.add(new Point(x + 1, y + 2));
		neighbors.add(new Point(x - 1, y + 2));
		neighbors.add(new Point(x - 2, y + 1));
		neighbors.add(new Point(x - 2, y - 1));
		neighbors.add(new Point(x - 1, y - 2));
	}
	
	@Override
	public String toString() {
		return "[" + getX() + "," + getY() + "]";
	}
}
