package hw6;


public class State{
	private char[][] board;
	private char[][] valid;
	private int BoardLength;
	


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
		this.valid=newValidBoard;
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
		return array[y][x];
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
		//TODO if there are errors, look here
		if(array==this.board){
			markPosition(valid,x,y,symbol);
		}
		//decrement to match array indexes
		x--;
		y--;
		if(Character.isDigit(array[y][x])&&Character.isDigit(symbol)) {
			array[y][x]=symbol;
			return true;
		}
		if(' '!=array[y][x]){
			return false;
		}
		array[y][x]=symbol;
		if(symbol=='X'){
			updateValid(x+1,y+1);
		}
		return true;
	}
	
	/**
	 * Will update the valid board based on the move made. Sets appropriate tiles to invalid(O)
	 * @param x coordinate
	 * @param y coordinate
	 * @return false if board becomes invalid
	 */
	public boolean updateValid(int x, int y) {
		int tempX;
		int tempY;
		for(tempX=1;tempX<=BoardLength;tempX++) {
			markPosition(valid,tempX,y,'O');
		}
		for(tempY=1;tempY<=BoardLength;tempY++) {
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
	
	/**
	 * Will check if passed location is numerical and will decrement it. 
	 * If it is decremented to a zero, all surrounding tiles will be set to invalid(O)
	 * @param x coordinate
	 * @param y coordinate
	 */
	public void checkForNewZero(int x,int y) {
		char temp = valueAtPos(valid,x,y);
		if(temp=='1'||temp=='2') {
			int j = Character.getNumericValue(temp)-1;
			char placement = (char)(j+48);
			markPosition(valid,x,y, placement);
			if(j==0) {
				System.out.println("TEST\n\n");
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
	
	/**
	 * Will check to see if the current board is invalid
	 * @return false if invalid
	 */
	public boolean checkValid() {
		boolean checkRow = false;
		boolean checkColumn = false;
		for(int i=0;i<BoardLength;i++) {
			checkRow = false;
			checkColumn = false;
			for(int j=0;j<BoardLength;j++) {
				char temp = valueAtPos(valid,i,j);
				if(temp=='X'||temp==' ') {
					System.out.println("TEST");
					checkRow=true;
				}
				char temp2 = valueAtPos(valid,j,i);
				if(temp2=='X'||temp2==' '){
					System.out.println("TEST");
					checkColumn=true;
				}
			}
			if(checkRow==false||checkColumn==false)
				return false;
		}
		return true;
	}
	
	/**
	 * will scan the original board for 0's and set the tiles around them to invalid (O)
	 */
	public void initializeValid() {
		for(int x=1;x<=BoardLength;x++) {
			for(int y=1;y<=BoardLength;y++) {
				if(valueAtPos(valid,x,y)=='0') {
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
	}

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	public char[][] getValid() {
		return valid;
	}

	public void setValid(char[][] valid) {
		this.valid = valid;
	}

	public int getBoardLength() {
		return BoardLength;
	}

	public void setBoardLength(int boardLength) {
		BoardLength = boardLength;
	}
	
	
	
}
