package sodoku;

import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

public class sodoku {

	public static void main(String[] args) throws FileNotFoundException {
//		File file = new File("C:\\Users\\Tyler\\Desktop\\sodoku.txt");
//		Scanner s = new Scanner(file);
//		int[][] grid = new int[9][9];
//		String puzzle = "";
//		while(s.hasNextLine()){
//			s.nextLine();
//			for (int i = 0; i < 9; i++) {
//				puzzle += s.nextInt();
//			}
//			System.out.println(puzzle);
//		}
//		printGrid(grid);

		int[][] grid = {
			{5, 3, 0, 0, 7, 0, 0, 0, 0},
			{6, 0, 0, 1, 9, 5, 0, 0, 0},
			{0, 9, 8, 0, 0, 0, 0, 6, 0},
			{8, 0, 0, 0, 6, 0, 0, 0, 3},
			{4, 0, 0, 8, 0, 3, 0, 0, 1},
			{7, 0, 0, 0, 2, 0, 0, 0, 6},
			{0, 6, 0, 0, 0, 0, 2, 8, 0},
			{0, 0, 0, 4, 1, 9, 0, 0, 5},
			{0, 0, 0, 0, 8, 0, 0, 7, 9}
		};
//		int[][] grid = { 
//            {3, 0, 6, 5, 0, 8, 4, 0, 0}, 
//            {5, 2, 0, 0, 0, 0, 0, 0, 0}, 
//            {0, 8, 7, 0, 0, 0, 0, 3, 1}, 
//            {0, 0, 3, 0, 1, 0, 0, 8, 0}, 
//            {9, 0, 0, 8, 6, 3, 0, 0, 5}, 
//            {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
//            {1, 3, 0, 0, 0, 0, 2, 5, 0}, 
//            {0, 0, 0, 0, 0, 0, 0, 7, 4}, 
//            {0, 0, 5, 2, 0, 6, 3, 0, 0} 
//	    };
		printGrid(grid);
		System.out.println();
		solve(grid);
		printGrid(grid);
	}
	

	public static void printGrid(int[][] grid) {
		for(int[] row : grid) {
			System.out.println(Arrays.toString(row));
		}
	}
	
	public static boolean solve(int[][] grid){
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
//				go through each space and look for 0's, those are empty spots that need to be filled
				if(grid[row][col] == 0) {
//					once we find one we need to go through 1-9 and see if anything is a valid number
					for (int n = 1; n < 10; n++) {						
						if(valid(grid, row, col, n)){
//							if it is valid then mark it as such
							grid[row][col] = n;
//							and try to solve the next square (solve will automatically bypass the number we just did since its no longer a 0 and that is our trigger to stop and solve)
							if(solve(grid) == true){
//								if you are able to solve the next square, GREAT, keep going until...
								return true;
							}
//							...you run into a square that you cannot solve, mark it as 0...
							grid[row][col] = 0;
						}
					}
//					...and return false. this will continue to go backwards through the puzzle until it finds a new solution to the square, then it will go forward again...
					return false;
				}
			}
		} 
//		...until all the squares have been solved so you will never trigger the 0 condition and return true 
		return true;
	}
	
	public static boolean valid(int grid[][], int row, int col, int n) {
//		3 functions:  
//		check the row
		for(int i = 0; i < 9; i++) {
			if(grid[row][i] == n) {
				return false;
			}
		}
//		check the col
		for(int i = 0; i < 9; i++) {
			if(grid[i][col] == n) {
				return false;
			}
		}
//		check the local 3x3
//		find the corner
		/*take in any number, think of the grid as a big 3x3
		 * to get the top "corner" of any square in this 3x3 we can divide by 3 resulting in either a 0, 1, 2 
		 * so that will get us to whatever big square we want but we still need to put it in terms of a length of 9x9 
		 * so we multiply it by 3. 
		 * for example col 0, 1, 2 divided by 3 results in 0 
		 * times 3 its 0 so we get grid[0][0] as our starting point and we can do a 3 by 3 search to find a matching number in that square
		 * we will have to add i and j to x and y as well to get a meaningful answer
		 * 	ex. start at grid 0,0 start your search at grid 0+0, 0+0 then move to 0+0, 0+1, etc etc
		 * col 3, 4, 5 divided by 3 results in 1 
		 * times 3 we get 3 so our starting point is grid[0][3] and we can search again*/
		int x = (row/3) * 3;
		int y = (col/3) * 3;
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++)
				if(grid[x + i][y + j] == n) {
					return false;
				}
		}
		return true;
	}

}
