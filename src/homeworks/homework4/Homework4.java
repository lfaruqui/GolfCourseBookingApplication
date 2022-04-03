package homeworks.homework4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import csis4463.PuzzleSolution;
import csis4463.SlidingTilePuzzle;
import csis4463.SlidingTilePuzzleSolver;

public class Homework4 {

	// The number of total puzzles to solve
	public int numPuzzles;
	
	//1-D array of puzzles to solve
	public ArrayList<SlidingTilePuzzle> puzzles;
	
	//2-D array of doubles where each element is a "row" in the output table for the number of expanded states
	public ArrayList<Double[]> expanded;
	
	//2-D array of doubles where each element is a "row" in the output table for the number of states in memory
	public ArrayList<Double[]> memory;
	
	//2-D array of doubles where each element is a "row" in the output table for the number of generated states
	public ArrayList<Double[]> generated;

	/**
	 * Constructor. 
	 * @param numRuns - number of different optimal path lengths to solve
	 * @param numInstances - number of puzzles of each optimal path length
	 */
	public Homework4(int numRuns, int numInstances) {
		
		this.numPuzzles = numRuns * numInstances;
		puzzles = new ArrayList<SlidingTilePuzzle>();
		generatePuzzles(numInstances);

		expanded = new ArrayList<Double[]>();
		memory = new ArrayList<Double[]>();
		generated = new ArrayList<Double[]>();
	}

	/**
	 * This creates the ArrayList of SlidingTilePuzzles.
	 * @param instances - number of puzzles of a specific optimal path length
	 */
	private void generatePuzzles(int instances) {
		int optimalPathLength = 0;
	
		for (int i = 0; i < numPuzzles; i++) {
			
			if ((i) % 100 == 0) {
				
				optimalPathLength += 2;

			}
			puzzles.add(new SlidingTilePuzzle(3, 3, optimalPathLength));

		}

	}

	/**
	 * This runs all the necessary searches to gather data on
	 */
	public void runSearches() {

		//initialize the running totals for solution 1 of expanded states, generated states, and states in memory
		int s1Exp = 0;
		int s1Gen = 0;
		int s1Mem = 0;
		
		//initialize the running totals for solution 2 of expanded states, generated states, and states in memory
		int s2Exp = 0;
		int s2Gen = 0;
		int s2Mem = 0;
		
		//initialize the running totals for solution 3 of expanded states, generated states, and states in memory
		int s3Exp = 0;
		int s3Gen = 0;
		int s3Mem = 0;
		
		//initialize the running totals for solution 4 of expanded states, generated states, and states in memory
		int s4Exp = 0;
		int s4Gen = 0;
		int s4Mem = 0;
		
		//initialize the running totals for solution 5 of expanded states, generated states, and states in memory
		int s5Exp = 0;
		int s5Gen = 0;
		int s5Mem = 0;
		
		//initialize the running totals for solution 6 of expanded states, generated states, and states in memory
		int s6Exp = 0;
		int s6Gen = 0;
		int s6Mem = 0;
		
		int n = 0;
		int currentLength = 2;
		
		for (SlidingTilePuzzle puzzle : puzzles) {
			
			n++;
			
			PuzzleSolution s1 = SlidingTilePuzzleSolver.uniformCostSearch(puzzle);
			PuzzleSolution s2 = SlidingTilePuzzleSolver.AStarSearchMisplacedTiles(puzzle);
			PuzzleSolution s3 = SlidingTilePuzzleSolver.AStarSearchManhattanDistance(puzzle);
			PuzzleSolution s4 = SlidingTilePuzzleSolver.iterativeDeepening(puzzle);
			PuzzleSolution s5 = SlidingTilePuzzleSolver.idaStarMisplacedTiles(puzzle);
			PuzzleSolution s6 = SlidingTilePuzzleSolver.idaStarManhattanDistance(puzzle);

			// every 100 puzzles is a new optimal path length, and the analysis needs to restart and be stored
			if (n % 100 == 0) {
				
				Double[] e = new Double[] { (double)currentLength, ((double) s1Exp) / 100.0, ((double) s2Exp) / 100.0,
						((double) s3Exp) / 100.0, ((double) s4Exp) / 100.0, ((double) s5Exp) / 100.0,
						((double) s6Exp) / 100.0 };
				
				Double[] g = new Double[] { (double)currentLength, ((double) s1Gen) / 100.0, ((double) s2Gen) / 100.0,
						((double) s3Gen) / 100.0, ((double) s4Gen) / 100.0, ((double) s5Gen) / 100.0,
						((double) s6Gen) / 100.0 };
				
				Double[] m = new Double[] { (double)currentLength, ((double) s1Mem) / 100.0, ((double) s2Mem) / 100.0,
						((double) s3Mem) / 100.0, ((double) s4Mem) / 100.0, ((double) s5Mem) / 100.0,
						((double) s6Mem) / 100.0 };
				
				expanded.add(e);
				generated.add(g);
				memory.add(m);

				currentLength += 2;
				
				s1Exp = 0;
				s2Exp = 0;
				s3Exp = 0;
				s4Exp = 0;
				s5Exp = 0;
				s6Exp = 0;
				
				s1Mem = 0;
				s2Mem = 0;
				s3Mem = 0;
				s4Mem = 0;
				s5Mem = 0;
				s6Mem = 0;
				
				s1Gen = 0;
				s2Gen = 0;
				s3Gen = 0;
				s4Gen = 0;
				s5Gen = 0;
				s6Gen = 0;
			} else {
				s1Exp += s1.getNumberOfStatesExpanded();
				s2Exp += s2.getNumberOfStatesExpanded();
				s3Exp += s3.getNumberOfStatesExpanded();
				s4Exp += s4.getNumberOfStatesExpanded();
				s5Exp += s5.getNumberOfStatesExpanded();
				s6Exp += s6.getNumberOfStatesExpanded();
				
				s1Mem += s1.getNumberOfStatesInMemory();
				s2Mem += s2.getNumberOfStatesInMemory();
				s3Mem += s3.getNumberOfStatesInMemory();
				s4Mem += s4.getNumberOfStatesInMemory();
				s5Mem += s5.getNumberOfStatesInMemory();
				s6Mem += s6.getNumberOfStatesInMemory();
				
				s1Gen += s1.getNumGenerated();
				s2Gen += s2.getNumGenerated();
				s3Gen += s3.getNumGenerated();
				s4Gen += s4.getNumGenerated();
				s5Gen += s5.getNumGenerated();
				s6Gen += s6.getNumGenerated();
//				System.out.println(s1Exp);
//				System.out.println(currentLength);
			}

		}

	}
	
	/**
	 * This prints the data to the console
	 */
	public void printResults() {
		System.out.println("Num States Expanded");
		System.out.printf("%2s %9s %9s %9s %9s %9s %9s\n", "L", "UCS", "A*1", "A*2", "ID", "IDA*1", "IDA*2");
		
		for (Double[] e: expanded) {
			System.out.printf("%2.0f %9.2f %9.2f %9.2f %9.2f %9.2f %9.2f\n", e[0], e[1], e[2], e[3], e[4], e[5], e[6]);
		}
		
		System.out.println("\nNum States Generated");
		System.out.printf("%2s %9s %9s %9s %9s %9s %9s\n", "L", "UCS", "A*1", "A*2", "ID", "IDA*1", "IDA*2");
		
		for (Double[] e: generated) {
			System.out.printf("%2.0f %9.2f %9.2f %9.2f %9.2f %9.2f %9.2f\n", e[0], e[1], e[2], e[3], e[4], e[5], e[6]);
		}
		
		System.out.println("\nMax States in Memory");
		System.out.printf("%2s %9s %9s %9s %9s %9s %9s\n", "L", "UCS", "A*1", "A*2", "ID", "IDA*1", "IDA*2");
		
		for (Double[] e: memory) {
			System.out.printf("%2.0f %9.2f %9.2f %9.2f %9.2f %9.2f %9.2f\n", e[0], e[1], e[2], e[3], e[4], e[5], e[6]);
		}
		
	}
	
	/**
	 * This writes the data to a text file
	 */
	public void writeResults() {
		File output = new File("hw_data.txt");
		try {
			PrintWriter pw = new PrintWriter(output);
			
			pw.println("Num States Expanded");
			pw.printf("%2s %9s %9s %9s %9s %9s %9s\n", "L", "UCS", "A*1", "A*2", "ID", "IDA*1", "IDA*2");
			
			for (Double[] e: expanded) {
				pw.printf("%2.0f %9.2f %9.2f %9.2f %9.2f %9.2f %9.2f\n", e[0], e[1], e[2], e[3], e[4], e[5], e[6]);
			}
			
			pw.println("\nNum States Generated");
			pw.printf("%2s %9s %9s %9s %9s %9s %9s\n", "L", "UCS", "A*1", "A*2", "ID", "IDA*1", "IDA*2");
			
			for (Double[] e: generated) {
				pw.printf("%2.0f %9.2f %9.2f %9.2f %9.2f %9.2f %9.2f\n", e[0], e[1], e[2], e[3], e[4], e[5], e[6]);
			}
			
			pw.println("\nMax States in Memory");
			pw.printf("%2s %9s %9s %9s %9s %9s %9s\n", "L", "UCS", "A*1", "A*2", "ID", "IDA*1", "IDA*2");
			
			for (Double[] e: memory) {
				pw.printf("%2.0f %9.2f %9.2f %9.2f %9.2f %9.2f %9.2f\n", e[0], e[1], e[2], e[3], e[4], e[5], e[6]);
			}
			
			pw.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeCSVFile() {
		File output = new File("hw_data.csv");
		try {
			PrintWriter pw = new PrintWriter(output);
			
			pw.println("Num States Expanded");
			pw.println("L, UCS, A*1, A*2, ID, IDA*1, IDA*2");
			
			for (Double[] e: expanded) {
				pw.println(e[0] + ", " + e[1] + ", " + e[2] + ", " + e[3] + ", " + e[4] + ", " + e[5] + ", " + e[6]);
			}
			
			pw.println("\nNum States Generated");
			pw.println("L, UCS, A*1, A*2, ID, IDA*1, IDA*2");
			
			for (Double[] e: generated) {
				pw.println(e[0] + ", " + e[1] + ", " + e[2] + ", " + e[3] + ", " + e[4] + ", " + e[5] + ", " + e[6]);
			}
			
			pw.println("\nMax States in Memory");
			pw.println("L, UCS, A*1, A*2, ID, IDA*1, IDA*2");
			
			for (Double[] e: memory) {
				pw.println(e[0] + ", " + e[1] + ", " + e[2] + ", " + e[3] + ", " + e[4] + ", " + e[5] + ", " + e[6]);
			}
			
			pw.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Homework4 hw = new Homework4(6, 100);
		hw.runSearches();
		hw.printResults();
		hw.writeResults();
		hw.writeCSVFile();
	}

}
