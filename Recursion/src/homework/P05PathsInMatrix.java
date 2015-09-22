package homework;

import java.util.LinkedList;
import java.util.List;

public class P05PathsInMatrix {
	private static final char visited = 's';
	private static final char empty = ' ';
	private static final char exit = 'e';
	private static final Character[][] matrix = {
		{' ', ' ', ' ', ' '},
		{' ', '*', '*', ' '},
		{' ', '*', '*', ' '},
		{' ', '*', 'e', ' '},
		{' ', ' ', ' ', ' '}
	};
	private static final List<Character> path = new LinkedList<>();
	private static int pathsCount = 0;
	
	public static void main(String[] args) {
		findPathToExit(0, 0, 'S');
		System.out.println("Total paths found: " + pathsCount);
	}

	private static void findPathToExit(int row, int col, char direction) {
		if(inRange(row, col)){
			path.add(direction);
			if (matrix[row][col].equals(exit)) {
				printPath(path);
				pathsCount++;
			}
			
			if (matrix[row][col].equals(empty)) {
				matrix[row][col] = visited;
				findPathToExit(row, col - 1, 'L');
				findPathToExit(row - 1, col, 'U');
				findPathToExit(row, col + 1, 'R');
				findPathToExit(row + 1, col, 'D');
				matrix[row][col] = empty;
			}
			
			path.remove(path.size() - 1);
		}
	}

	private static void printPath(List<Character> path) {
		for (Character dir : path) {
			System.out.print(dir);
		}
		
		System.out.println();
	}

	private static boolean inRange(int row, int col) {
		boolean rowInRange = row >= 0 && row < matrix.length;
		boolean colInRange = col >= 0 && col < matrix[0].length;
        return rowInRange && colInRange;
	}

}
