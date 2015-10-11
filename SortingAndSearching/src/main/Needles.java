package main;

import java.util.Scanner;

public class Needles {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)){
			String[] firstLine = scanner.nextLine().split("\\s+");
			int c = Integer.parseInt(firstLine[0]);
			int n = Integer.parseInt(firstLine[1]);
			String[] sequence = scanner.nextLine().split("\\s+");
			String[] needles = scanner.nextLine().split("\\s+");
			int needle;
			int seqEl;
			int index = -1;
			for (int i = 0; i < n; i++) {
				needle = Integer.parseInt(needles[i]);
				for (int j = 0; j < c; j++) {
					seqEl = Integer.parseInt(sequence[j]);
					if (seqEl == 0 && index == -1)
						index = j;
					
					if (seqEl != 0 && needle <= seqEl) {
						if (index != -1) {
							System.out.print(index + " ");
							index = -1;
						} else {
							System.out.print(j + " ");
						}
						
						break;
					} else if (seqEl != 0 && j == c - 1) {
						System.out.print(c + " ");
						break;
					}
					
					if (seqEl != 0 && index != -1)
						index = -1;
				}
			}
		}
	}
}
