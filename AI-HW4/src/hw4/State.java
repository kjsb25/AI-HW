package hw4;


public class State {
	public char[][] board;
	static final int BoardLength=4;

	public State() {
		super();
		this.board = buildStartBoard();
	}
	
	public State(char[][] board) {
		super();
		this.board = board;
	}
	
	public char[][] buildStartBoard(){
		char[][] board=new char[BoardLength][BoardLength];
		for(int i=0;i<BoardLength;i++){
			for(int j=0;j<BoardLength;j++){
				board[i][j]=' ';
			}
		}
		return board;
	}
	
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
	
	public char valueAtPos(int x, int y){
		if(x>BoardLength || x<=0 || y>BoardLength || y<=0){
			System.out.print("Invalid position passed to isPosMarked");
		}
		x--;
		y--;
		return board[x][y];
	}

	public boolean markPosition(int x, int y,char symbol){
		if(x>BoardLength || x<=0 || y>BoardLength || y<=0){
			System.out.print("Invalid position passed to markPosition");
			return false;
		}
		if(symbol!='X' && symbol!='O'){
			System.out.print("Invalid symbol passed to markPosition");
			return false;
		}
		x--;
		y--;
		if(' '!=board[x][y]){
			return false;
		}
		board[x][y]=symbol;
		return true;
	}
	
	/**
	 * Find if there is an open end for two in a row for the symbol, and returns coordinates of that position
	 * 
	 * @param symbol
	 * @param board
	 */
	public static void isTwoInARowOpen(char symbol, State board){
		
	}
	
	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}
	
}
