package homework.P06AreasInMatrix;

public class Area implements Comparable<Area>{
	private final int x;
	private final int y;
	private int size;
	
	public Area(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public int compareTo(Area other) {
		if (this.size == other.getSize()) {
			if (this.x > other.getX()) {
				return 1;
			} else if (this.x < other.getX()) {
				return -1;
			} else if (this.y > other.getY()){
				return 1;
			} else if (this.y < other.getY()){
				return -1;
			}
		} else {
			if (this.size < other.getSize()) {
				return 1;
			} else {
				return -1;
			}
		}
		
		return 0;
	}
}
