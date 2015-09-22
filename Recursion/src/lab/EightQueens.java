package lab;

import java.util.HashSet;
import java.util.Set;

public class EightQueens {
	
	private static final int size = 8;
	private static final boolean[][] chessboard = new boolean[size][size];
	private static final Set<Integer> attacketColumns = new HashSet<>(); 
	private static final Set<Integer> attacketLeftDiagonals = new HashSet<>(); 
	private static final Set<Integer> attacketRightDiagonals = new HashSet<>();
	private static int solutionsFound = 0;

	public static void main(String[] args) {
		putQueens(0);
		System.out.println("Solutions found: " + solutionsFound);
	}

	private static void putQueens(int row) {
		if (row == size) {
			printSolution();
		} else {
			for (int col = 0; col < size; col++) {
				if (canPlaceQueen(row, col)) {
					markAllAttackedPositions(row, col);
					putQueens(row + 1);
					unmarkAllAttackedPositions(row, col);
				}
			}
		}
	}

	private static void unmarkAllAttackedPositions(int row, int col) {
		attacketColumns.remove(col);
		attacketLeftDiagonals.remove(col - row);
		attacketRightDiagonals.remove(row + col);
		chessboard[row][col] = false;
	}

	private static void markAllAttackedPositions(int row, int col) {
		attacketColumns.add(col);
		attacketLeftDiagonals.add(col - row);
		attacketRightDiagonals.add(row + col);
		chessboard[row][col] = true;
	}

	private static boolean canPlaceQueen(int row, int col) {
		boolean positionOccupied = 
				attacketColumns.contains(col) ||
				attacketLeftDiagonals.contains(col - row) ||
				attacketRightDiagonals.contains(row + col);
		return !positionOccupied;
	}

	private static void printSolution() {
		for (int row = 0; row < size; row++) {
			for (int coll = 0; coll < size; coll++) {
				if (chessboard[row][coll]) {
					System.out.print("*");
				} else {
					System.out.print("-");
				}
			}
			
			System.out.println();
		}
		
		System.out.println();
		solutionsFound++;
	}

}
