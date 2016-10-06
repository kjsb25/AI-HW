package hw4;

import java.util.ArrayList;
import java.util.Scanner;

public class HW4 {

	public static void main(String[] args) {
		State currState=new State();
		currState.printBoard();
		char player='X';
		int fuckit =0;
//		while(!currState.win){
//			currState = BeginnerDecision(currState, 'O');
//			currState.printBoard();
//			fuckit = currState.countTwoInARow('O', currState);
//			System.out.println(fuckit);
//			if(currState.win==true){
//				System.out.print("Begginer AI wins!\n");
//				break;
//			}
//			currState=userDecision(currState,player);
//			currState.printBoard();
//		}
		while(!currState.win){
			currState = BeginnerDecision(currState, 'O');
			currState.printBoard();
			if(currState.win==true){
				System.out.print("Beginner wins!\n");
				break;
			}
			currState=AdvancedDecision(currState,'X');
			if(currState.win==true){
				System.out.print("Advanced wins!\n");
				break;
			}
			currState.printBoard();
		}
		
		System.out.print("GAME OVER\n");

	
	}
	
	//may just implement players here, while they are technically objects it just seems easier to implement them as functions....
	
	public static State BeginnerDecision(State state,char symbol){
		State newState=new State(state);
		if(newState.isTwoInARowOpen(symbol,symbol,newState)) {
			System.out.println("Made it\n\n\n");
			newState.setWin(true);
			return newState;
		}
		else if(newState.isTwoInARowOpen('X','O',newState)) {
			System.out.println("Made it to check nutsack\n\n\n");
			return newState;
		}
		else {
			newState.firstOpen(symbol,newState);
		}
		return newState;
	}
	
	public static State AdvancedDecision(State state,char symbol){
		System.out.println("herhe");
		State newState=new State(state);
		char oppSymbol=' ';
		if(symbol=='X'){
			oppSymbol='O';
		}else if(symbol=='O'){
			oppSymbol='X';
		}
		if(state.isTwoInARowOpen(symbol, symbol, newState)){
			System.out.println("tried to win");
			return newState;
		}else if(newState.isTwoInARowOpen(oppSymbol,symbol,newState)) {
			System.out.println("tried to block");
			return newState;
		}
		newState=MiniMaxDecision(newState,symbol,2);
		
		return newState;
	}
	
	public static State MasterDecision(State state,char symbol){
		System.out.println("herhe");
		State newState=new State(state);
		char oppSymbol=' ';
		if(symbol=='X'){
			oppSymbol='O';
		}else if(symbol=='O'){
			oppSymbol='X';
		}
		if(state.isTwoInARowOpen(symbol, symbol, newState)){
			System.out.println("tried to win");
			return newState;
		}else if(newState.isTwoInARowOpen(oppSymbol,symbol,newState)) {
			System.out.println("tried to block");
			return newState;
		}
		newState=MiniMaxDecision(newState,symbol,4);
		
		return newState;
	}
	
	public static State MiniMaxDecision(State state, char symbol, int ply){
		State newState=new State(state);
		char oppSymbol=' ';
		if(symbol=='X'){
			oppSymbol='O';
		}else if(symbol=='O'){
			oppSymbol='X';
		}
		ArrayList<State> children=getChildrenStates(state,symbol);
		int max=Integer.MIN_VALUE;
		int hold;
		for(State child: children){
			hold=minValue(child,ply-1,symbol,oppSymbol);
			System.out.println(hold);
			if(hold>max){
				max=hold;
				newState=child;
			}
		}
		System.out.println(max);
		return newState;
	}
	
	public static int minValue(State state,int depth,char symbol,char oppSymbol){
		if(terminalTest(state) || depth==0){
			state.calcHeuristic(symbol);
//			state.printBoard();
//			System.out.println(state.heuristic);
			return state.heuristic;
		}
		
		int min=Integer.MAX_VALUE;
		ArrayList<State> children=getChildrenStates(state,oppSymbol);
		for(State child: children){
			min=Integer.min(min, maxValue(child,depth-1,symbol,oppSymbol));
		}
		return min;
	}
	
	public static int maxValue(State state,int depth,char symbol,char oppSymbol){
		if(terminalTest(state) || depth==0){
			state.calcHeuristic(symbol);
//			state.printBoard();
//			System.out.println(state.heuristic);
			return state.heuristic;
		}
		int v=Integer.MIN_VALUE;
		ArrayList<State> children=getChildrenStates(state,symbol);
		for(State child: children){
			v=Integer.max(v, minValue(child,depth-1,symbol,oppSymbol));
		}
		return v;
	}
	
	/**
	 * Returns an arraylist of states that are all the possible moves that could be made from the passed in state
	 * 
	 * @param starting
	 * @param symbol
	 * @return arraylist, but a blank one if a failure occured
	 */
	public static ArrayList<State> getChildrenStates(State starting, char symbol){
		ArrayList<State> children=new ArrayList<State>();
		
		for(int i=1;i<=State.BoardLength;i++){
			for(int j=1;j<=State.BoardLength;j++){
				if(starting.valueAtPos(i,j)==' '){
					State child=new State(starting);
					if(child.markPosition(i, j, symbol)){
						children.add(child);
					}else{
						System.out.print("Error in getChildrenStates");
						return new ArrayList<State>();
					}
				}
			}
		}
		return children;
	}
	
	/**
	 * Checks if any players won, or no nodes are free
	 * 
	 * @param state
	 * @return symbol of winning player, F if no one is winning, and D if the board is full
	 */
	public static boolean terminalTest(State state){
		if(state.isBoardFull()){
			return true;
		}else if(state.checkforWin('X')){
			return true;
		}else if(state.checkforWin('O')){
			return true;
		}else{
			return false;
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
