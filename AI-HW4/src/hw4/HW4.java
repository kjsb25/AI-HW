package hw4;

import java.util.Scanner;

public class HW4 {

	public static void main(String[] args) {
		State currState=new State();
		currState.printBoard();
		char player='O';
		while(!currState.win){
//			currState = BeginnerDecision(currState, 'O');
//			currState.printBoard();
			currState=userDecision(currState,player);
			currState.printBoard();
			System.out.print(terminalTest(currState));
		}
	
	}
	
	//may just implement players here, while they are technically objects it just seems easier to implement them as functions....
	
	public static State BeginnerDecision(State state,char symbol){
		State newState=new State(state);
		if(newState.isTwoInARowOpen(symbol,symbol,newState)) {
			newState.win = true;
		}
		else if(newState.isTwoInARowOpen('X','O',newState)) {
			return newState;
		}
		else
			//newState.firstOpen('O',newState);
			return newState;
		return newState;
	}
	
	public static State AdvancedDecision(State state,char symbol){
		State newState=new State(state);
		if(state.isTwoInARowOpen(symbol, symbol, newState)){
			return newState;
		}
//		MiniMaxDecision(newState,2);
		
		return newState;
	}
	
	public static void MinMaxDecision(State state, int ply){
		
		
	}
	
	/**
	 * Checks if any players won, or no nodes are free
	 * 
	 * @param state
	 * @return symbol of winning player, F if no one is winning, and D if the board is full
	 */
	public static char terminalTest(State state){
		if(state.checkforWin('X')){
			return 'X';
		}else if(state.checkforWin('O')){
			return 'O';
		}else if(state.isBoardFull()){
			return 'D';
		}else{
			return 'F';
		}
	}
	
	public static State userDecision(State state,char symbol){
		State newState=new State(state);
		
		//create new reader for input, and testing boolean
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		String input_string;
		boolean input_valid=false;
		
		//while a move hasn't been made
		while(!input_valid){
			//print prompt and scan input
			System.out.print("Select x and y coordinates:");
			input_string=reader.nextLine();
			Scanner input=new Scanner(input_string);
			
			//check for validity
			if(input.hasNextInt()){
				int x=input.nextInt();
				if(input.hasNextInt()){
					int y=input.nextInt();
					//attempt to set that position
					if(newState.markPosition(x, y, symbol)){
						input_valid=true;
					}else{
						System.out.println("Input was outside of board. Try again.");
					}
				}else{
					System.out.println("Y is not a number. Try again.");
				}
			}else{
				System.out.println("X is not a number. Try again.");
			}
			input.close();
		}
		return newState;
	}
	

}
