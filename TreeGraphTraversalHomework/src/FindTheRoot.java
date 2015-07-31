import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class FindTheRoot {

	/*
	 *Graph implementation. 
	 *It would be easier with Map<Integer, List<Integer>>, but this is a way too.
	 */
	private static List<LinkedList<Integer>> graph = new LinkedList<LinkedList<Integer>>();
	private static List<Integer> graphMap = new LinkedList<>();
	
	public static void main(String[] args) {
		
		parseInput();
		
		List<Integer> roots = findRoot();
		if (roots.isEmpty()) {
			System.out.println("No root!");
		} else if (roots.size() == 1) {
			System.out.println("The root: " + roots.get(0));
		} else {
			System.out.println("Forest is not a tree!");
		}

	}

	/**
	 * Finds the graph's roots nodes. Returns a list of nodes without parents.
	 * @return - list of roots.
	 */
	private static List<Integer> findRoot() {
		
		List<Integer> children = new LinkedList<>();
		for (LinkedList<Integer> neighbour : graph) {
			children.addAll(neighbour);
		}
		
		List<Integer> roots = new LinkedList<>();
		for (Integer node : graphMap) {
			if (! children.contains(node)) {
				roots.add(node);
			}
		}
		
		return roots;
	}
	
	/**
	 * Creates and adds parent and child node to graph, if some don't exist
	 * and connects them in a parent-child relationship.
	 * @param parentElement - the parent value.
	 * @param childElement - the child value.
	 */
	private static void addToGraph(Integer parentElement, Integer childElement) {
		
		if (! graphMap.contains(parentElement)) {
			graphMap.add(parentElement);
			LinkedList<Integer> newNode = new LinkedList<>();
			newNode.add(childElement);
			graph.add(newNode);
		} else {
			graph.get(graphMap.indexOf(parentElement)).add(childElement);
		}
		
		if (! graphMap.contains(childElement)) {
			graphMap.add(childElement);
			LinkedList<Integer> newNode = new LinkedList<>();
			graph.add(newNode);
		}
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
			
		}
		
		scanner.close();
		
	}

}
