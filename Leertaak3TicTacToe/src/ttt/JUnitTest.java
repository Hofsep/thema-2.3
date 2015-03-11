package ttt;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * JUnit test to check if TicTacToe is written correctly.
 * The methods to be checked are positionValue(), isAWin() & chooseMove().
 * chooseMove() has to give back the best result expected.
 *
 */

public class JUnitTest {
	
	//initialize a position where we can calculate if the best move will be reacted to
	private static final int H = 0; 
	private static final int C = 1; 
	public  static final int E = 2;
	
	int c = 10;
	
	int[][] temp = new int[][] {{C,C,H},
								{E,H,E},
								{E,E,E}};
	
	int[][] bestMove = new int[][] {{C,C,H},
									{E,H,E},
									{C,E,E}};
	
	TicTacToe ttt;
	

	private void doTests(){
		
	//	assertTrue(ttt.positionValue() == H);
		
	//	assertTrue(ttt.isAWin(H));
		
		Assert.assertArrayEquals(bestMove, temp);
		
		
	}
	
}
