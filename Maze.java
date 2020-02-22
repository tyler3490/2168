package mazeAssignment;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Maze extends JFrame {

	public Maze() {
		
		this.add(new MazeGridPanel/*Solution*/(100,100));
		this.setSize(800, 800);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis(); 	
		new Maze();
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
}
