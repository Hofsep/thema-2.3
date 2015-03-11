package ttt;

import java.util.ArrayList;
import java.util.Random;
class TicTacToe {
	
	private static final int HUMAN        = 0; 
	private static final int COMPUTER     = 1; 
	public  static final int EMPTY        = 2;

	public  static final int HUMAN_WIN    = 0;
	public  static final int DRAW         = 1;
	public  static final int UNCLEAR      = 2;
	public  static final int COMPUTER_WIN = 3;

	private int [ ] [ ] board = new int[ 3 ][ 3 ];
    private Random random=new Random();  
	private int side=random.nextInt(2);  
	private int position=UNCLEAR;
	private char computerChar,humanChar;

	// Constructor
	public TicTacToe( )
	{
		clearBoard( );
		initSide();
	}
	
	private void initSide()
	{
	    if (this.side==COMPUTER) { computerChar='X'; humanChar='O'; }
		else                     { computerChar='O'; humanChar='X'; }
    }
    
    public void setComputerPlays()
    {
        this.side=COMPUTER;
        initSide();
    }
    
    public void setHumanPlays()
    {
        this.side=HUMAN;
        initSide();
    }

	public boolean computerPlays()
	{
	    return side==COMPUTER;
	}

	public int chooseMove()
	{
	    Best best=chooseMove(COMPUTER);
	    return best.row*3+best.column;
	    
    }
    
    // Find optimal move
	private Best chooseMove( int side ) {
		
		int opponent;              	// The other side
		Best reply;           		// Opponent's best reply
		int simpleEval;       		// Result of an immediate evaluation
		int bestRow = 0;
		int bestColumn = 0;
		int value;

		if( ( simpleEval = positionValue( ) ) != UNCLEAR )
			return new Best( simpleEval );

		//implementeren m.b.v. recursie/backtracking
		if(side == COMPUTER){
			opponent = HUMAN;
			value = HUMAN_WIN; 
		}else{
			opponent = COMPUTER;
			value = COMPUTER_WIN;
		}
		
		for (int row = 0; row < board.length; row++){
			for (int col = 0; col < board.length; col++){
				if(squareIsEmpty(row, col)){
					place(row, col, side);			//perform move
					// check reply of opponents move in recursive way
					reply = chooseMove(opponent); 	
					place(row, col, EMPTY); // undo move
					if( (side == COMPUTER && reply.val > value) || (side == HUMAN && reply.val < value) ){ 
						bestRow = row;
						bestColumn = col;
						value = reply.val;
					}
				}
			}
		}
	    return new Best(value, bestRow, bestColumn);
    }

   
    //check if move ok
    public boolean moveOk(int move) {
    	
    	return ( move>=0 && move <=8 && board[move/3 ][ move%3 ] == EMPTY );
    }
    
    // play move
    public void playMove(int move) {
    	
		board[move/3][ move%3] = this.side;
		if (side==COMPUTER) this.side=HUMAN;  else this.side=COMPUTER;
	}


	// Simple supporting routines
	private void clearBoard( ) {
		//go through each block and make them equals EMPTY ==2
		
		for(int row=0; row<board.length; row++){
			for(int col=0; col<board.length; col++){
				board[row][col] = EMPTY;
			}
		}
	}


	private boolean boardIsFull( )
	{
		for(int r =0; r<board.length; r++){ //go through each row
			for(int c=0; c<board.length; c++){ //go through each column
				if(squareIsEmpty(r,c)){ //check each square if they are empty
					return false;
				}
			}
		}
	
		return true;
	}

	// Returns whether 'side' has won in this position
	public boolean isAWin( int side ) {
		//checks if side has 3 in a row and return true for the winning side.
		//Diagonal 		= 0,0  1,1  2,2 	
		//				  0,2  1,1  2,0		
		//Horizontal 	= 0,0  0,1  0,2		
		//				  1,0  1,1  1,2		
		//				  2,0  2,1  2,2		
		//Vertical		= 0,0  1,0  2,0		
		//				  0,1  1,1  2,1		
		//				  0,2  1,2  2,2	

		for(int i = 0; i<board.length; i++){
			//array for 3 of a kind horizontal
			ArrayList<Boolean> horizontal = new ArrayList<Boolean>();
			horizontal.add(board[i][0]==side);
			horizontal.add(board[i][1]==side);
			horizontal.add(board[i][2]==side);
			//array for 3 of a kind vertical
			ArrayList<Boolean> vertical = new ArrayList<Boolean>();
			vertical.add(board[0][i]==side);
			vertical.add(board[1][i]==side);
			vertical.add(board[2][i]==side);
			//check if horizontal or vertical has a filled array & contains no False
			if( horizontal.size()==3 && !(horizontal.contains(Boolean.FALSE)) ||
					vertical.size()==3 && !(vertical.contains(Boolean.FALSE)) ){
				return true;
			}
				
		}
		//checking 3 of a kind diagonally.. had to hardcode
		if(board[1][1] == side){
			if(board[0][0] == side && board[2][2] == side){	return true;}
			else if(board[2][0] == side && board[0][2] == side){ return true;}
		}

		return false;
    }

	// Play a move, possibly clearing a square
	private void place( int row, int column, int piece )
	{
		board[ row ][ column ] = piece;
	}

	private boolean squareIsEmpty( int row, int column )
	{
		return board[ row ][ column ] == EMPTY;
	}

	// Compute static value of current position (win, draw, etc.)
	public int positionValue( )
	{
		if(isAWin(COMPUTER)){
			return COMPUTER_WIN;
		} else if(isAWin(HUMAN)){
			return HUMAN_WIN;
		} else if(boardIsFull()){
			return DRAW;
		} else {
			return UNCLEAR;
		}
	}
	
	
	public String toString()
	{
	    String result = "";
		
		for(int i=0; i<9; i++){
				// selected the square and print out what is in it..
				if(board[i/3][i%3] == COMPUTER){
					result += "|" + computerChar + "|";
				} else if(board[i/3][i%3] == HUMAN){
					result += "|" + humanChar + "|";
				} else {
					result += "| |";
				}
			
			if(i%3 == 2)  result += "\n";
		//	System.out.println(i%3);
		}
		
		return result;   
	}  
	
	public boolean gameOver()
	{
	    this.position=positionValue();
	    return this.position!=UNCLEAR;
    }
    
    public String winner()
    {
        if      (this.position==COMPUTER_WIN) return "computer";
        else if (this.position==HUMAN_WIN   ) return "human";
        else                                  return "nobody";
    }
    
	
    private class Best {
       int row;
       int column;
       int val;

       public Best( int v )
         { this( v, 0, 0 ); }
      
       public Best( int v, int r, int c )
        { val = v; row = r; column = c; }
    } 
	
	
}

