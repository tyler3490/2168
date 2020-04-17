package graphs;

import edu.uci.ics.jung.graph.SparseGraph;

import java.util.*;

import edu.uci.ics.jung.graph.Graph;

public class GraphSearch {
	public static List<Integer> bfs(Graph<Integer, String> g, int root) {
//		Set<Integer> visited = new LinkedHashSet<Integer>();
		List<Integer> visited = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(root);
		visited.add(root);
		while (!queue.isEmpty()) {
			int vertex = queue.poll();
			for (int v : g.getNeighbors(vertex)) {
				if (!visited.contains(v)) {
					visited.add(v);
					queue.add(v);
				}
			}
		}
		return visited;
	}
	
	public static List<Integer> dfs(Graph<Integer, String> g, int root) {
//		Set<Integer> visited = new LinkedHashSet<Integer>();
		List<Integer> visited = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			int vertex = stack.pop();
			if (!visited.contains(vertex)) {
				visited.add(vertex);
				for (int v : g.getNeighbors(vertex)) {
					stack.push(v);
				}
			}
		}
		return visited;
	}
	
	public static void main(String args[]) {
		Graph<Integer, String> gbfs = new SparseGraph<>();
		gbfs.addEdge("01", 0, 1);
		gbfs.addEdge("03", 0, 3);
		gbfs.addEdge("32", 3, 2);
		gbfs.addEdge("29", 2, 9);
		gbfs.addEdge("28", 2, 8);
		gbfs.addEdge("12", 1, 2);
		gbfs.addEdge("17", 1, 7);
		gbfs.addEdge("16", 1, 6);
		gbfs.addEdge("14", 1, 4);
		gbfs.addEdge("74", 7, 4);
		gbfs.addEdge("76", 7, 6);
		gbfs.addEdge("64", 6, 4);
		gbfs.addEdge("45", 4, 5);
		
		Graph<Integer, String> gdfs = new SparseGraph<>();
		gdfs.addEdge("01", 0, 1);
		gdfs.addEdge("02", 0, 2);
		gdfs.addEdge("03", 0, 3);
		gdfs.addEdge("04", 0, 4);
		gdfs.addEdge("13", 1, 3);
		gdfs.addEdge("14", 1, 4);
		gdfs.addEdge("25", 2, 5);
		gdfs.addEdge("26", 2, 6);
		gdfs.addEdge("34", 3, 4);
		gdfs.addEdge("56", 5, 6);

		System.out.println(dfs(gdfs, 0));

		System.out.println(bfs(gbfs, 0));		
	}
}
