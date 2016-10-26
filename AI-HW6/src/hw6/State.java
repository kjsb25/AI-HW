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
		char[][] newValidBoard=new char[BoardLength][BoardLength];
		for(int i=0;i<BoardLength;i++){
			for(int j=0;j<BoardLength;j++){
				newValidBoard[i][j]=copy.board[i][j];
			}
		}
		this.valid=newBoard;
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
	 * Prints out the valid board (for error checking)
	 */
	public void printValidBoard(){
		System.out.print("Valid Board:");
		for(int i=0;i<BoardLength;i++){
			System.out.print("\n");
			for(int j=0;j<BoardLength;j++){
				System.out.print("["+valid[i][j]+"]");
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
	public boolean markPosition(char[][] array, int x, int y, char symbol){
		//error checking
		if(x>BoardLength || x<=0 || y>BoardLength || y<=0){
			return false;
		}
		if(!Character.isDigit(symbol)){
			return false;
		}
		//TODO if there are errors, look here
		if(array==this.board){
			markPosition(valid,x,y,symbol);
		}
		//decrement to match array indexes
		x--;
		y--;
		if(' '!=array[y][x]){
			return false;
		}
		array[y][x]=symbol;
		return true;
	}

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	
}
