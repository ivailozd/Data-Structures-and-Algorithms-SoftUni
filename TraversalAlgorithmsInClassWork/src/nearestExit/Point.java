package nearestExit;

public class Point {
	
	private int x;
	private int y;
	private String direction;
	private Point previousPoint;
	
	public Point(int x, int y) {
		setX(x);
		setY(y);
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public String getDirection() {
		return direction;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public Point getPreviousPoint() {
		return previousPoint;
	}
	
	public void setPreviousPoint(Point previousPoint) {
		this.previousPoint = previousPoint;
	}
}
