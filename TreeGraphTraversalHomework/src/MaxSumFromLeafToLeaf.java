import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MaxSumFromLeafToLeaf {
	
	private static Map<Integer, List<Integer>> graph = new HashMap<>();
	private static Map<Integer, Integer> childParentMap = new HashMap<>();

	public static void main(String[] args) {
		
		parseInput();
		List<Integer> leafs = findLeafs();
		Map<Integer, Integer> pathSums = new HashMap<>();
		for (Integer leaf : leafs) {
			pathSums.put(leaf, findPathSum(leaf));
		}
		
		int maxPathSum = findMaxPathSum(pathSums);
		System.out.println("Max sum path:");
		System.out.println(maxPathSum);

	}

	/**
	 * Finds max sum of each couple of path's sums.
	 * @param pathSums - map holding leaf -> sum of all nodes to root.
	 * @return max sum of two paths.
	 */
	private static int findMaxPathSum(Map<Integer, Integer> pathSums) {
		int maxSum = Integer.MIN_VALUE;
		for (Integer sumA : pathSums.values()) {
			int currentSum = Integer.MIN_VALUE;
			for (Integer sumB : pathSums.values()) {
				if (sumA != sumB) {
					currentSum = sumA + sumB;
					if (currentSum > maxSum) {
						maxSum = currentSum;
					}
				}
			}
		}
		
		return maxSum;
	}

	/**
	 * Finds the sum of all nodes from the leaf to the root inclusive.
	 * @param leaf - starting leaf.
	 * @return - the sum of nodes values.
	 */
	private static Integer findPathSum(Integer leaf) {
		
		int sum = leaf;
		Integer current = childParentMap.get(leaf);
		while (current != null) {
			sum += current;
			current = childParentMap.get(current);
		}
		
		return sum;
	}

	/**
	 * Finds all tree's leafs.
	 * @return - list of leaf's values.
	 */
	private static List<Integer> findLeafs() {
		
		List<Integer> leafs = new LinkedList<>(childParentMap.keySet());
		leafs.removeAll(graph.keySet());
		
		return leafs;
	}

	/**
	 * Creates a Map parent -> list of children.
	 * @param parentElement - the parent value.
	 * @param childElement - the child value.
	 */
	private static void addToGraph(Integer parentElement, Integer childElement) {
		
		if (graph.containsKey(parentElement)) {
			graph.get(parentElement).add(childElement);
		} else {
			List<Integer> newNode = new LinkedList<>();
			newNode.add(childElement);
			graph.put(parentElement, newNode);
		}
	}
	
	/**
	 * Creates a Map child -> parent.
	 * @param parentElement - the parent value.
	 * @param childElement - the child value.
	 */
	private static void addToChildParentMap(Integer parent, Integer child) {
		if (! childParentMap.containsKey(parent)) {
			childParentMap.put(parent, null);
		}
		
		childParentMap.put(child, parent);
	}
	
	/**
	 * Parses the user input and creates the graph.
	 */
	private static void parseInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Number of edges: ");
		int edges = scanner.nextInt();
		Integer parent, child;
		for (int i = 0; i < edges; i++) {
			System.out.print("Edge: ");
			parent = scanner.nextInt();
			child = scanner.nextInt();
			addToGraph(parent, child);
			addToChildParentMap(parent, child);
		}
		
		scanner.close();
		
	}

}
