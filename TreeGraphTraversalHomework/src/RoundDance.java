import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class RoundDance {

	private static Map<Integer, List<Integer>> graph = new HashMap<>();
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Number of friendships: ");
		int edges = scanner.nextInt();
		System.out.print("Dance leader: ");
		int startNode = scanner.nextInt();
		Integer parent, child;
		for (int i = 0; i < edges; i++) {
			System.out.print("Friendship: ");
			parent = scanner.nextInt();
			child = scanner.nextInt();
			addToGraph(parent, child);
		}
		
		scanner.close();

		List<Integer> longestRoundDance = findLongestPath(startNode);
		System.out.println("The round dance with the most people, starting from " + 
				startNode + " and everyone are friends is:");
		for (Integer dancer : longestRoundDance) {
			System.out.print(dancer + " ");
		}

	}
	
	/**
	 * Finds longest path in the graph from given node.
	 * @param startNode - starting node.
	 * @return - list of the longest path.
	 */
	private static List<Integer> findLongestPath(Integer startNode) {
		List<Integer> longestPath = new LinkedList<>();
		List<Integer> currentPath;
		List<Integer> children = graph.get(startNode);
		if (children != null) {
			for (Integer child : children) {
				currentPath = findLongestPath(child);
				if (longestPath.size() < currentPath.size()) {
					longestPath = currentPath;
				}
			}
		}
		
		longestPath.add(0, startNode);
		
		return longestPath;
	}

	/**
	 * Creates and adds parent and child node to graph, if some don't exist
	 * and connects them in a parent-child relationship.
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

}
