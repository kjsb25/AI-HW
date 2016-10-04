package hw4;

public class HW4 {

	public static void main(String[] args) {
		State startingState=new State();
		startingState.printBoard();
		startingState.markPosition(2, 4, 'X');
		startingState.markPosition(1, 1, 'O');
		startingState.printBoard();
	}
	
	//may just implement players here, while they are technically objects it just seems easier to implement them as functions....
	
	public static State BeginnerDecision(State state){
		State newState=new State(state.getBoard());
		
		return newState;
	}
	
	/**
	 * Find if there is an open end for two in a row for the symbol, and returns coordinates of that position
	 * 
	 * @param symbol
	 * @param board
	 */
	public static void isTwoInARowOpen(char symbol, State board){
		
	}

}
