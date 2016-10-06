package hw4;


public class State {
	public char[][] board;
	static final int BoardLength=4;
	public boolean win=false;

	public State() {
		super();
		this.board = buildStartBoard();
	}
	
	
	public State(State copy) {
//		super();
		char[][] newBoard=new char[BoardLength][BoardLength];
		for(int i=0;i<BoardLength;i++){
			for(int j=0;j<BoardLength;j++){
//				System.out.println(i+","+j);
				newBoard[i][j]=copy.board[i][j];
			}
		}
		this.board=newBoard;
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

	public boolean markPosition(int x, int y,char symbol){
		if(x>BoardLength || x<=0 || y>BoardLength || y<=0){
			return false;
		}
		if(symbol!='X' && symbol!='O'){
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
	 * @param search is the symbol we are looking for
	 * @param placement is the symbol we will place
	 * @param board is the board
	 * @return false for no piece placed and true for piece placed
	 */
	public boolean isTwoInARowOpen(char search, char placement, State board){
		//loop through board
		for(int i=0;i<BoardLength;i++){
			for(int j=0;j<BoardLength;j++){
				//find the symbol we are searching for
				if(board.getBoard()[i][j]==search) {
					//check to see if symbol has a match in any possible direction
					//(downleft,left,upleft, and up are not possible because of how we are searching)
					//if 2 in a row are found we will check if there is a 3rd spot available
					if(j+1<BoardLength&&i-1>=0&&board.getBoard()[i-1][j+1]==search) {
						if(checkLocation(board,placement,i+1,j+1,"upRight")) {
							return true;
						}
					}
					if(j+1<BoardLength&&board.getBoard()[i][j+1]==search) {
						if(checkLocation(board,placement,i+1,j+1,"right")) {
							return true;
						}
					}
					if(j+1<BoardLength&&i+1<BoardLength&&board.getBoard()[i+1][j+1]==search) {
						if(checkLocation(board,placement,i+1,j+1,"downRight")) {
							return true;
						}
					}
					if(i+1<BoardLength&&board.getBoard()[i+1][j]==search) {
						if(checkLocation(board,placement,i+1,j+1,"down")) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean firstOpen(char symbol, State state) {
		for(int j=0;j<BoardLength;j++){
			for(int i=0;i<BoardLength;i++){
				if(state.valueAtPos(i+1,j+1)==' ') {
					state.markPosition(i+1,j+1,symbol);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkforWin(char symbol){
		for(int i=0;i<BoardLength;i++){
			for(int j=0;j<BoardLength;j++){
				if(board[i][j]==symbol){
					if(checkNeighbors(i,j,symbol)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean checkNeighbors(int x, int y,char symbol){
		boolean result=true;
		//checks vertical row
		for(int i=(x-1);i<=x+1 && result;i++){
			if(i>=0 && i<BoardLength){
				if(board[i][y]!=symbol){
					result=false;
				}
			}else{
				result=false;
			}
		}
		if(result){
			return true;
		}
		//checks horizontal row
		result=true;
		for(int j=(y-1);j<=y+1 && result;j++){
			if(j>=0 && j<BoardLength){
				if(board[x][j]!=symbol){
					result=false;
				}
			}else{
				result=false;
			}
		}
		if(result){
			return true;
		}
		//checks diagonal
		result=true;
		for(int i=-1;i<=1 && result;i++){
			if((x+i)>=0 && (y+i)>=0 && (x+i)<(BoardLength) && (y+i)<(BoardLength)){
				if(board[x+i][y+i]!=symbol){
					result=false;
				}
			}else{
				result=false;
			}
		}
		if(result){
			return true;
		}
		//checks diagonal
		result=true;
		for(int i=-1;i<=1 && result;i++){
			if((x+i)>=0 && (y-i)>=0 && (x+i)<(BoardLength) && (y-i)<(BoardLength)){
				if(board[x+i][y-i]!=symbol){
					result=false;
				}
			}else{
				result=false;
			}
		}
		return result;
	}
	
	public static boolean checkLocation(State board, char placement, int x, int y, String direction) {
		if("upRight".equals(direction)) {
			if(board.markPosition(x+1, y-1, placement)==true) {
				return true;
			}
			else if(board.markPosition(x-2, y+2, placement)==true) {
				return true;
			}
		}
		else if("right".equals(direction)) {
			if(board.markPosition(x, y-1, placement)==true) {
				return true;
			}
			else if(board.markPosition(x, y+2, placement)==true) {
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
			if(board.markPosition(x-1, y, placement)==true) {
				return true;
			}
			else if(board.markPosition(x+2, y, placement)==true) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isBoardFull(){
		for(int i=0;i<BoardLength;i++){
			for(int j=0;j<BoardLength;j++){
				if(board[i][j]==' '){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Will search for open two in a rows of type search and will return that number
	 * @param search
	 * @param board
	 * @return the number of open two in a row
	 */
	public int countTwoInARow(char search, State board){
		//loop through board
		int count = 0;
		for(int i=0;i<BoardLength;i++){
			for(int j=0;j<BoardLength;j++){
				//find the symbol we are searching for
				if(board.getBoard()[i][j]==search) {
					//check to see if symbol has a match in any possible direction
					//(downleft,left,upleft, and up are not possible because of how we are searching)
					//if 2 in a row are found we will check if there is a 3rd spot available
					if(j+1<BoardLength&&i-1>=0&&board.getBoard()[i-1][j+1]==search) {
						if(board.valueAtPos(i+1+1, j-1+1)==' ')
							count++;
						if(board.valueAtPos(i-2+1, j+2+1)==' ')
							count++;
					}
					if(j+1<BoardLength&&board.getBoard()[i][j+1]==search) {
						if(board.valueAtPos(i+1, j-1+1)==' ')
							count++;
						if(board.valueAtPos(i+1, j+2+1)==' ')
							count++;
					}
					if(j+1<BoardLength&&i+1<BoardLength&&board.getBoard()[i+1][j+1]==search) {
						if(board.valueAtPos(i-1+1, j-1+1)==' ')
							count++;
						if(board.valueAtPos(i+2+1, j+2+1)==' ')
							count++;
					}
					if(i+1<BoardLength&&board.getBoard()[i+1][j]==search) {
						if(board.valueAtPos(i-1+1, j+1)==' ')
							count++;
						if(board.valueAtPos(i+2+1, j+1)==' ')
							count++;
					}
				}
			}
		}
		return count;
	}
	
	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}


	public boolean isWin() {
		return win;
	}


	public void setWin(boolean win) {
		this.win = win;
	}
	
}
