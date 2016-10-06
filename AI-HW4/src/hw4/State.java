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
	 * 
	 * @param search
	 * @param placement
	 * @param board
	 * @return
	 */
	public static int isTwoInARowOpen(char search, char placement, State board){
		//loop through board
		for(int i=0;i<BoardLength;i++){
			for(int j=0;j<BoardLength;j++){
				//find the symbol we are searching for
				if(board.getBoard()[i][j]==search) {
					//check to see if symbol has a match in any possible direction
					//(downleft,left,upleft, and up are not possible because of how we are searching)
					//if 2 in a row are found we will check if there is a 3rd spot available
					if(j-1>=0&&i+1<BoardLength&&board.getBoard()[i+1][j-1]==search) {
						if(checkLocation(board,placement,i,j,"upRight")) {
							if(search==placement) {
								return 2;
							}
							return 1;
						}
					}
					if(i+1<BoardLength&&board.getBoard()[i+1][j]==search) {
						if(checkLocation(board,placement,i,j,"right")) {
							if(search==placement) {
								return 2;
							}
							return 1;
						}
					}
					if(j+1<BoardLength&&i+1<BoardLength&&board.getBoard()[i+1][j+1]==search) {
						if(checkLocation(board,placement,i,j,"downRight")) {
							if(search==placement) {
								return 2;
							}
							return 1;
						}
					}
					if(j+1<BoardLength&&i+1<BoardLength&&board.getBoard()[i+1][j+1]==search) {
						if(checkLocation(board,placement,i,j,"down")) {
							if(search==placement) {
								return 2;
							}
							return 1;
						}
					}
				}
			}
		}
		return 0;
	}
	
	public static boolean checkLocation(State board, char placement, int x, int y, String direction) {
		if("upRight".equals(direction)) {
			if(board.markPosition(x-1, y+1, placement)==true) {
				return true;
			}
			else if(board.markPosition(x+2, y-2, placement)==true) {
				return true;
			}
		}
		else if("right".equals(direction)) {
			if(board.markPosition(x-1, y, placement)==true) {
				return true;
			}
			else if(board.markPosition(x+2, y, placement)==true) {
				return true;
			}
		}
		else if("downRight".equals(direction)) {
			if(board.markPosition(x-1, y-1, placement)==true) {
				return true;
			}
			else if(board.markPosition(x+2, y+2, placement)==true) {
				return true;
			}
		}
		else if("down".equals(direction)) {
			if(board.markPosition(x, y-1, placement)==true) {
				return true;
			}
			else if(board.markPosition(x, y+2, placement)==true) {
				return true;
			}
		}
		return false;
	}
	
	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}
	
}
