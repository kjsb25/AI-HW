package hw6;


public class State {
	public char[][] board;
	public char[][] valid;
	public int BoardLength;


	public State(int boardlength) {
		super();
		this.BoardLength=boardlength;
		this.board = buildBoard();
		this.valid=buildBoard();
	}
	
	/**
	 * Build a state from a previous state
	 * @param copy
	 */
	public State(State copy, int boardlength) {
//		super();
		this.BoardLength=boardlength;
		char[][] newBoard=new char[BoardLength][BoardLength];
		for(int i=0;i<BoardLength;i++){
			for(int j=0;j<BoardLength;j++){
				newBoard[i][j]=copy.board[i][j];
			}
		}
		this.board=newBoard;
	}
	
	/**
	 * builds an empty board
	 * @return
	 */
	public char[][] buildBoard(){
		char[][] board=new char[BoardLength][BoardLength];
		for(int i=0;i<BoardLength;i++){
			for(int j=0;j<BoardLength;j++){
				board[i][j]=' ';
			}
		}
		return board;
	}
	
	/**
	 * Prints out the board
	 */
	public void printBoard(){
		System.out.print("Board:");
		for(int i=0;i<BoardLength;i++){
			System.out.print("\n");
			for(int j=0;j<BoardLength;j++){
				System.out.print("["+board[i][j]+"]");
			}
		}
		System.out.println("");
	}
	
	/**
	 * gets the value at the passed indices, if the indices are invalid, will return a 'F'
	 * @param x
	 * @param y
	 * @return value at indices
	 */
	public char valueAtPos(int x, int y){
		if(x>BoardLength || x<=0 || y>BoardLength || y<=0){
			return 'F';
		}
		x--;
		y--;
		return board[x][y];
	}

	/**
	 * Set the passed position to the passed symbol
	 * @param x
	 * @param y
	 * @param symbol
	 * @return
	 */
	public boolean markPosition(int x, int y){
		//error checking
		if(x>BoardLength || x<=0 || y>BoardLength || y<=0){
			return false;
		}
		//decrement to match array indexes
		x--;
		y--;
		if(' '!=board[x][y]){
			return false;
		}
		board[x][y]='X';
		return true;
	}

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	
}
