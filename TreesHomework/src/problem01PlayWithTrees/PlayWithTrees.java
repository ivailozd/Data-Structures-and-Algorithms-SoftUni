package problem01PlayWithTrees;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PlayWithTrees {
	
	private static Map<Integer, Tree<Integer>> nodeByValue = new HashMap<>();
	private static int pathSum;

	public static void main(String[] args) {
		
		parseInput();
		
		System.out.println("Root value:");
		Tree<Integer> root = findRootNode(nodeByValue);
		System.out.println(root.getValue());
		
		System.out.println("Middle node's values:");
		findMiddleNodes(nodeByValue).forEach(n -> System.out.print(n.getValue() + " "));
		System.out.println();
		
		System.out.println("The longest path:");
		findLongestPath(root).forEach(n -> System.out.print(n + " "));
		System.out.println();
		
		System.out.println("Paths with sum " + pathSum + ":");
		List<List<Tree<Integer>>> paths = findPathsWithSum(root, pathSum);
		for (List<Tree<Integer>> path : paths) {
			for (Tree<Integer> node : path) {
				System.out.print(node.getValue() + " ");
			}
			
			System.out.println();
		}
	}

	/**
	 * Finds paths in a tree of integers with given sum by given root. 
	 * @param root - tree's root.
	 * @param pathSum - path's sum.
	 * @return - a list of paths.
	 */
	private static List<List<Tree<Integer>>> findPathsWithSum(Tree<Integer> root, int pathSum) {
		List<Tree<Integer>> endNodes = new LinkedList<>();
		findEndNodesForPathsWithSum(root, pathSum, endNodes);
		
		List<List<Tree<Integer>>> paths = new LinkedList<>();
		for (int i = 0; i < endNodes.size(); i++) {
			List<Tree<Integer>> currentPath = new LinkedList<>();
			Tree<Integer> currentNode = endNodes.get(i);
			while (currentNode.getParent() != null) {
				currentPath.add(0, currentNode);
				currentNode = currentNode.getParent();
			}
			
			currentPath.add(0, root);
			paths.add(currentPath);
		}
		
		return paths;
	}
	
	/**
	 * Helper method to find the path's leafs for findPathsWithSum(...);
	 * @param node - a node from the tree.
	 * @param pathSum - path's sum.
	 * @param endNodes - a list where paths to be stored.
	 */
	private static void findEndNodesForPathsWithSum(
			Tree<Integer> node, int pathSum, List<Tree<Integer>> endNodes) {
		
		pathSum -= node.getValue();
		for (Tree<Integer> child : node.getChildren()) {
			findEndNodesForPathsWithSum(child, pathSum, endNodes);
		}
		
		if (pathSum == 0) {
			endNodes.add(node);
		}
	}

	/**
	 * Finds left-most longest path in a tree or a subtree. 
	 * @param node - node from a tree.
	 * @return - a list of trees from the path.
	 */
	private static <T> List<T> findLongestPath(Tree<T> node) {
		List<T> longestPath = new LinkedList<>();
		List<T> currentPath;
		
		for (Tree<T> child : node.getChildren()) {
			currentPath = findLongestPath(child);
			if (longestPath.size() < currentPath.size()) {
				longestPath = currentPath;
			}
		}
		
		longestPath.add(0, node.getValue());
		
		return longestPath;
	}

	/**
	 * Finds middle trees in given tree's map.
	 * @param map - the tree's map.
	 * @return - a list of trees.
	 */
	private static <T> List<Tree<T>> findMiddleNodes(Map<T, Tree<T>> map) {
		List<Tree<T>> result = new LinkedList<>();
		for (Tree<T> node : map.values()) {
			if (! node.getChildren().isEmpty() && node.getParent() != null) {
				result.add(node);
			}
		}
		
		return result;
	}

	/**
	 * Finds the root node in given tree's map.
	 * @param map - the tree's map.
	 * @return - the root tree.
	 */
	private static <T> Tree<T> findRootNode(Map<T, Tree<T>> map) {
		Tree<T> result = null;
		for (Tree<T> node : map.values()) {
			if (node.getParent() == null) {
				result = (Tree<T>) node;
			}
		}
		
		return result;
	}

	/**
	 * Helper method for creating tree's map. Adds the tree if not exist.
	 * @param value - the tree to be added.
	 * @return - the tree.
	 */
	@SuppressWarnings("unchecked")
	private static <T> Tree<T> getTreeNodeByValue(int value) {
		
		if (! nodeByValue.containsKey(value)) {
			nodeByValue.put(value, new Tree<>(value));
		}
		
		return (Tree<T>) nodeByValue.get(value);
		
	}
	
	/**
	 * Reads the input from the console. 
	 */
	private static void parseInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Nodes count: ");
		int nodesCount = scanner.nextInt();
		for (int i = 0; i < nodesCount - 1; i++) {
			System.out.println("Next node:");
			int parentValue = scanner.nextInt();
			Tree<Integer> parent = getTreeNodeByValue(parentValue);
			int childValue = scanner.nextInt();
			Tree<Integer> child = getTreeNodeByValue(childValue);
			parent.getChildren().add(child);
			child.setParent(parent);
		}
		
		System.out.println("Path sum: ");
		pathSum = scanner.nextInt();

		scanner.close();
	}

}
