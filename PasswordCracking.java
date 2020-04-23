package graphs;

import java.io.*;
import java.util.*;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;

public class PasswordCracking {
	public static void main(String args[]) {
		List<String> in = new ArrayList<>();
		Graph<String, Integer> graph = new DirectedSparseGraph<>();
		try {
			Scanner scanner = new Scanner(new File("p079_keylog.txt"));
			while(scanner.hasNext()){
				in.add(scanner.nextLine());
			}	
			scanner.close();		
		} 
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		buildGraph(in, graph);
		System.out.println("the password is " + solve(graph));
	}
	
	/*this will look at each vertex and see what its in degree is
	 * the lower the in degree the earlier it will come in the sequence 
	 * for example in the set of numbers we are given the in degree of 7 is 0
	 * meaning it only ever comes before something, never after so it MUST come before everything
	 * the in degree of 3 is 1 so it comes next and so on */
	private static String solve(Graph<String, Integer> graph) {
		String out = "";
		int i = 0;
		while (i < graph.getVertexCount()) {			
			for (String vertex : graph.getVertices()) {
				/*the in edges will return an array of all the things connected to this particular node 
				 * this arrays size corresponds to its in degree so we look for the smallest first (i.e. 0) 
				 * then increment and look for 1, etc., etc.*/
				Collection<Integer> indegree = graph.getInEdges(vertex);
				if(indegree.size() == i) {
					out += vertex;
					i++;
				}
			}
		}
		return out;
	}
	
	/*this will build the graph, each number comes in as a string ex. "319" and then is
	 * split into 3 digits, the edges are then added like this: 
	 * 	digit 1 comes before 2 and 3
	 *	digit 2 comes before 3 
	 * i is just there to give the edges unique names */
	private static void buildGraph(List<String> in, Graph<String, Integer> graph) {
		int i = 0;
		for (String num: in) {
			String digit1 = Character.toString(num.charAt(0));
			String digit2 = Character.toString(num.charAt(1));
			String digit3 = Character.toString(num.charAt(2));
			graph.addEdge(i, digit1, digit2);
			i++;
			graph.addEdge(i, digit1, digit3);
			i++;
			graph.addEdge(i, digit2, digit3);
			i++;
		}
	}
}

	

