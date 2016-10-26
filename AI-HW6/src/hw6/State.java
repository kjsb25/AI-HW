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
	public char valueAtPos(char[][] array, int x, int y){
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
	
	public boolean updateValid(int x, int y) {
		int tempX;
		int tempY;
		for(tempX=0;tempX<BoardLength;tempX++) {
			markPosition(valid,tempX,y,'O');
		}
		for(tempY=0;tempY<BoardLength;tempY++) {
			markPosition(valid,x,tempY,'O');
		}
		for(int messy=1;messy<=BoardLength;messy++) {
			markPosition(valid,x-messy,y-messy,'O');
			markPosition(valid,x-messy,y+messy,'O');
			markPosition(valid,x+messy,y-messy,'O');
			markPosition(valid,x+messy,y+messy,'O');
		}
		checkForNewZero(x-1,y-1);
		checkForNewZero(x,y-1);
		checkForNewZero(x+1,y-1);
		checkForNewZero(x-1,y);
		checkForNewZero(x+1,y);
		checkForNewZero(x-1,y+1);
		checkForNewZero(x,y+1);
		checkForNewZero(x+1,y+1);
		return checkValid();
	}
	
	public void checkForNewZero(int x,int y) {
		if(Character.isDigit(valueAtPos(valid,x,y))) {
			int j = valueAtPos(valid,x,y);
			markPosition(valid,x,y, Character.forDigit(j-1, 10));
			if(j-1==0) {
				markPosition(valid,x-1,y-1,'O');
				markPosition(valid,x,y-1,'O');
				markPosition(valid,x+1,y-1,'O');
				markPosition(valid,x-1,y,'O');
				markPosition(valid,x+1,y,'O');
				markPosition(valid,x-1,y+1,'O');
				markPosition(valid,x,y+1,'O');
				markPosition(valid,x+1,y+1,'O');
			}
		}
	}
	
	public boolean checkValid() {
		boolean checkRow = false;
		boolean checkColumn = false;
		for(int i=0;i<BoardLength;i++) {
			checkRow = false;
			checkColumn = false;
			for(int j=0;j<BoardLength;j++) {
				char temp = valueAtPos(valid,i,j);
				if(temp=='X'||temp==' ')
					checkRow=true;
				char temp2 = valueAtPos(valid,j,i);
				if(temp2=='X'||temp2==' ')
					checkColumn=true;
			}
			if(checkRow==false||checkColumn==false)
				return false;
		}
		return true;
	}

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	
}
