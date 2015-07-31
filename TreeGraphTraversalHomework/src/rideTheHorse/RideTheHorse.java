package rideTheHorse;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class RideTheHorse {
	
	private static int width;
	private static int height;
	private static int[][] board;
	private static boolean[] visited;
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		Integer startCellX, startCellY;
		System.out.print("Width: ");
		width = scanner.nextInt();
		System.out.print("Height: ");
		height = scanner.nextInt();
		System.out.print("Starting cell row: ");
		startCellX = scanner.nextInt();
		System.out.print("Starting cell col: ");
		startCellY = scanner.nextInt();
		scanner.close();
		
		board = new int[width][height];
		visited = new boolean[width * height];
		
		rideTheHorse(new Point(startCellX, startCellY));
		printResult();
	}

	/**
	 * Prints the width/2-nth column to the console.
	 */
	private static void printResult() {
		int column = width / 2;
		for (int i = 0; i < board.length; i++) {
			System.out.println(board[i][column]);
		}
		
	}

	/**
	 * Gets the starting cell and assigns 1 to it,
	 * traverses the board via BFS and
	 * assigns values to reachable, non visited cells.
	 * @param point - starting cell.
	 */
	private static void rideTheHorse(Point point) {
		Queue<Point> queue = new LinkedList<>();
		board[point.getX()][point.getY()] = 1;
		queue.add(point);
		while (! queue.isEmpty()) {
			Point currentCell = queue.poll();
			int value = board[currentCell.getX()][currentCell.getY()];
			markAsVisited(currentCell);
			for (Point neighbor : currentCell.getNeighbors()) {
				if (isOnBoard(neighbor)) {
					if (! isVisited(neighbor)) {
						board[neighbor.getX()][neighbor.getY()] = value + 1;
						queue.add(neighbor);
					}
				}
			}
		}
		
	}
	
	/**
	 * Marks in visited[] the given cell as visited.
	 * @param point
	 */
	private static void markAsVisited(Point point) {
		visited[width * point.getY() + point.getX()] = true;
	}

	/**
	 * Checks in visited[] if the cell on the board is already visited.
	 * @param point
	 * @return - true or false
	 */
	private static boolean isVisited(Point point) {
		return visited[width * point.getY() + point.getX()];
	}

	/**
	 * Checks if the point is inside the board.
	 * @param point
	 * @return - true or false
	 */
	private static boolean isOnBoard(Point point) {
		
		if (point.getX() >= 0 && point.getX() < width && 
				point.getY() >= 0 && point.getY() < height) {
			return true;
		}
		
		return false;
	}
}
