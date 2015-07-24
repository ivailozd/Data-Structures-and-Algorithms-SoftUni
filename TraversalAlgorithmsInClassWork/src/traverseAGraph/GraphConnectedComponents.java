package traverseAGraph;

import java.util.Arrays;
import java.util.List;

public class GraphConnectedComponents {

	/*The graph:         0
	 *                  / \
	 *                 6   3    7
	 *                 |\ /|
	 *                 | 1 |    8
	 *                 |/ \|     \
	 *                 4   5      2
	 */
	private static List<List<Integer>> graph = Arrays.asList(
				Arrays.asList(3, 6),
				Arrays.asList(3, 4, 5, 6),
				Arrays.asList(8),
				Arrays.asList(0, 1, 5),
				Arrays.asList(1, 6),
				Arrays.asList(1, 3),
				Arrays.asList(0, 1, 4),
				Arrays.asList(),
				Arrays.asList(2)
			);
	private static boolean[] visited;
	
	public static void main(String[] args) {
		
		findGraphConnectedComponents();
	}
	
	private static void findGraphConnectedComponents() {
		visited = new boolean[graph.size()];
		for (int startNode = 0; startNode < graph.size(); startNode++) {
			if (! visited[startNode]) {
				System.out.print("Connected component:");
				depthFirstSearch(startNode);
				System.out.println();
			}
		}
	}
	
	private static void depthFirstSearch(Integer node){
		if (! visited[node]) {
			visited[node] = true;
			for (Integer child : graph.get(node)) {
				depthFirstSearch(child);
			}
			
			System.out.print(" " + node);
		}
	}

}
