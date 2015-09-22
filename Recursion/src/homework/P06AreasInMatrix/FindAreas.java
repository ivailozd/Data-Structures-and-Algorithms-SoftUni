package homework.P06AreasInMatrix;

import java.util.Set;
import java.util.TreeSet;

public class FindAreas {
	private static final char visited = 'x';
	private static final char empty = ' ';
	private static final Set<Area> areas = new TreeSet<>();
	private static final Character[][] matrix = {
		{'*', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' '},
		{'*', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' '},
		{'*', ' ', ' ', '*', '*', '*', '*', '*', ' ', ' '},
		{'*', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' '},
		{'*', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' '}
	};

	public static void main(String[] args) {
		Cell startingCell = findNextEmptyCell();
		while (startingCell != null) {
			Area area = new Area(startingCell.getX(), startingCell.getY());
			fillArea(startingCell, area);
			areas.add(area);
			startingCell = findNextEmptyCell();
		}
		
		System.out.println("Total areas found: " + areas.size());
		int counter = 0;
		for (Area area : areas) {
			System.out.println("Area #" + ++counter + " at (" +
					area.getX() + "," + area.getY() + "), size: " + area.getSize());
		}
	}

	private static void fillArea(Cell cell, Area area) {
		int row = cell.getX();
		int col = cell.getY();
		if (inRange(row, col)) {
			if (matrix[row][col].equals(empty)) {
				matrix[row][col] = visited;
				area.setSize(area.getSize() + 1);
				fillArea(new Cell(row, col + 1), area);
				fillArea(new Cell(row + 1, col), area);
				fillArea(new Cell(row, col - 1), area);
				fillArea(new Cell(row - 1, col), area);
			}
		}
	}

	private static Cell findNextEmptyCell() {
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				if (matrix[row][col].equals(empty)) {
					return new Cell(row, col);
				}
			}
		}
		
		return null;
	}

	private static boolean inRange(int row, int col) {
		boolean rowInRange = row >= 0 && row < matrix.length;
		boolean colInRange = col >= 0 && col < matrix[0].length;
        return rowInRange && colInRange;
	}
	
}
