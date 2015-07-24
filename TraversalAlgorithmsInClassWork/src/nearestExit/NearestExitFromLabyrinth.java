package nearestExit;

import java.util.LinkedList;
import java.util.Queue;

public class NearestExitFromLabyrinth {
	
	private static final char STARTING_POINT = 's';
	private static final char PATH_CELL = '-';
	private static int width = 9;
	private static int height = 7;
	private static boolean[] visited = new boolean[width * height];
	private static char[][] labirinth = {
		{'*', '*', '*', '*', '*', '*', '*', '*', '*',},
		{'*', '-', '-', '-', '-', '*', '*', '-', '-',},
		{'*', '*', '-', '*', '-', '-', '-', '-', '*',},
		{'*', '-', '-', '*', '-', '*', '-', '*', '*',},
		{'*', 's', '*', '*', '-', '*', '-', '*', '*',},
		{'*', '*', '-', '-', '-', '-', '-', '-', '*',},
		{'*', '*', '*', '*', '*', '*', '*', '-', '*',}
	};
	
	public static void main(String[] args) {
		
		String shortestPathToExit = findShortestPathToExit();
		if (shortestPathToExit == null) {
			System.out.println("No exit!");
		} else if (shortestPathToExit == "") {
			System.out.println("Start is at the exit!");
		} else {
			System.out.println("Shortest path: " + shortestPathToExit);
		}
	}

	private static String findShortestPathToExit() {
		
		Queue<Point> queue = new LinkedList<>();
		Point startPosition = findStartPosition();
		if (startPosition == null) {
			return null;
		}
		
		queue.add(startPosition);
		while (! queue.isEmpty()) {
			Point currentCell = queue.poll();
			if (isExit(currentCell)) {
				return tracePathBack(currentCell);
			}
			
			tryDirection(queue, currentCell, "U", 0, -1);
			tryDirection(queue, currentCell, "R", 1, 0);
			tryDirection(queue, currentCell, "D", 0, 1);
			tryDirection(queue, currentCell, "L", -1, 0);
		}
		
		return null;
	}

	private static String tracePathBack(Point currentCell) {
		
		StringBuilder path = new StringBuilder();
		while (currentCell.getPreviousPoint() != null) {
			path.append(currentCell.getDirection());
			currentCell = currentCell.getPreviousPoint();
		}
		
		return path.reverse().toString();
	}

	private static void tryDirection(Queue<Point> queue, Point currentCell,
			String direction, int deltaX, int deltaY) {
		
		int newX = currentCell.getX() + deltaX;
		int newY = currentCell.getY() + deltaY;
		if (newX >= 0 && newX < width && newY >= 0 && newY < height && 
				labirinth[newY][newX] == PATH_CELL && ! isVisited(newX, newY)) {
			
			Point nextCell = new Point(newX, newY);
			nextCell.setDirection(direction);
			nextCell.setPreviousPoint(currentCell);
			markCellAsVisited(nextCell);
			queue.add(nextCell);
		}
		
	}

	private static boolean isExit(Point currentCell) {
		
		boolean isOnBorderX = currentCell.getX() == 0 || currentCell.getX() == width - 1;
		boolean isOnBorderY = currentCell.getY() == 0 || currentCell.getY() == height - 1;
		
		return isOnBorderX || isOnBorderY;
	}

	private static Point findStartPosition() {
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (labirinth[y][x] == STARTING_POINT) {
					Point startPoint = new Point(x, y);
					markCellAsVisited(startPoint);
					
					return startPoint;
				}
			}
		}
		
		return null;
	}

	private static void markCellAsVisited(Point cell) {
		visited[width * cell.getY() + cell.getX()] = true;
	}
	
	private static boolean isVisited(int x, int y) {
		return visited[width * y + x];
	}
}
