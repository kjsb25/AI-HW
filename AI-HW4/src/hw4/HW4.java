package hw4;

import java.util.ArrayList;
import java.util.Scanner;

public class HW4 {
	//counter for number of expanded nodes for print out
	static int expanded=0;
	
	public static void main(String[] args) {
		//create new state and print its board. Show that it is empty
		State currState=new State();
		currState.printBoard();
		//set human player character to 'X'
		char player='X';
		//we will loop while the game is not over. This currState win check is not actually used. We break out of the while loop with a different check
		while(!currState.win){
			System.out.println("Beginner Turn:");
			//Call beginner AI to make a move
			currState = BeginnerDecision(currState, 'O');
			currState.printBoard();
			//check to see if begginer has one
			if(currState.checkforWin('O')){
				System.out.print("Beginner wins!\n");
				break;
			}
			System.out.println("User Turn:");
			//allow user to make a move
			currState=userDecision(currState,player);
			currState.printBoard();
			//check to see if user has won
			if(currState.checkforWin('X')){
				System.out.print("User wins!\n");
				break;
			}
		}
		
		//repeat process except that now the AI's will play each other
		currState=new State();
		while(!currState.win){
			System.out.println("Beginner Turn:");
			currState = BeginnerDecision(currState, 'O');
			currState.printBoard();
			if(currState.checkforWin('O')){
				System.out.print("Beginner wins!\n");
				break;
			}
			expanded=0;
			System.out.println("Advanced Turn:");
			long startTime = System.nanoTime();
			currState=AdvancedDecision(currState,'X');
			long endTime = System.nanoTime();
			System.out.println("Expanded:"+expanded+"\nTime:"+(endTime - startTime)/1000+" us");
			currState.printBoard();
			if(currState.checkforWin('X')){
				System.out.print("Advanced wins!\n");
				break;
			}
		}
		currState=new State();
		while(!currState.win){
			System.out.println("Advanced Turn:");
			currState =AdvancedDecision(currState, 'O');
			currState.printBoard();
			if(currState.checkforWin('O')){
				System.out.print("Advanced wins!\n");
				break;
			}
			expanded=0;
			System.out.println("Beginner Turn:");
			long startTime = System.nanoTime();
			currState=BeginnerDecision(currState,'X');
			long endTime = System.nanoTime();
			System.out.println("Expanded:"+expanded+"\nTime:"+(endTime - startTime)/1000+" us");
			currState.printBoard();
			if(currState.checkforWin('X')){
				System.out.print("Beginner wins!\n");
				break;
			}
		}
		
		currState=new State();
		while(!currState.win){
			expanded=0;
			System.out.println("Advanced Turn:");
			long startTime = System.nanoTime();
			currState = AdvancedDecision(currState, 'O');
			long endTime = System.nanoTime();
			System.out.println("Expanded:"+expanded+"\nTime:"+(endTime - startTime)/1000+" us");
			currState.printBoard();
			if(currState.checkforWin('O')){
				System.out.print("Advanced wins!\n");
				break;
			}
			expanded=0;
			System.out.println("Master Turn:");
			startTime = System.nanoTime();
			currState=MasterDecision(currState,'X');
			endTime = System.nanoTime();
			System.out.println("Expanded:"+expanded+"\nTime:"+(endTime - startTime)/1000+" us");
			currState.printBoard();
			if(currState.checkforWin('X')){
				System.out.print("Master wins!\n");
				break;
			}
		}
		
		currState=new State();
		while(!currState.win){
			expanded=0;
			System.out.println("Master Turn:");
			long startTime = System.nanoTime();
			currState = MasterDecision(currState, 'O');
			long endTime = System.nanoTime();
			System.out.println("Expanded:"+expanded+"\nTime:"+(endTime - startTime)/1000+" us");
			currState.printBoard();
			if(currState.checkforWin('O')){
				System.out.print("Master wins!\n");
				break;
			}
			expanded=0;
			System.out.println("Advanced Turn:");
			startTime = System.nanoTime();
			currState=AdvancedDecision(currState,'X');
			endTime = System.nanoTime();
			System.out.println("Expanded:"+expanded+"\nTime:"+(endTime - startTime)/1000+" us");
			currState.printBoard();
			if(currState.checkforWin('X')){
				System.out.print("Advanced wins!\n");
				break;
			}
		}
		//End of program
		System.out.print("GAME OVER\n");

	
	}
	
	//may just implement players here, while they are technically objects it just seems easier to implement them as functions....
	
	public static State BeginnerDecision(State state,char symbol){
		//create new state that copies old state
		State newState=new State(state);
		//check to see if player can win this turn
		if(newState.isTwoInARowOpen(symbol,symbol,newState)) {
			newState.setWin(true);
			return newState;
		}
		//check to see if player must block in ordr to not lose
		else if(newState.isTwoInARowOpen('X','O',newState)) {
			return newState;
		}
		else {
			//find first open slot and play there
			newState.firstOpen(symbol,newState);
		}
		//return current state
		return newState;
	}
	
	public static State AdvancedDecision(State state,char symbol){
		//create new state from old state
		State newState=new State(state);
		//set oppponent symbol
		char oppSymbol=' ';
		if(symbol=='X'){
			oppSymbol='O';
		}else if(symbol=='O'){
			oppSymbol='X';
		}
		//check to see if we can win
		if(state.isTwoInARowOpen(symbol, symbol, newState)){
			return newState;
			//check to see if we must block
		}else if(newState.isTwoInARowOpen(oppSymbol,symbol,newState)) {
			return newState;
		}
		//run minimax with a depth of 2
		newState=MiniMaxDecision(newState,symbol,2);
		//return current state
		return newState;
	}
	
	public static State MasterDecision(State state,char symbol){
		//the exact same as advanced but minimax is run with a depth of 4
		State newState=new State(state);
		char oppSymbol=' ';
		if(symbol=='X'){
			oppSymbol='O';
		}else if(symbol=='O'){
			oppSymbol='X';
		}
		if(state.isTwoInARowOpen(symbol, symbol, newState)){
			return newState;
		}else if(newState.isTwoInARowOpen(oppSymbol,symbol,newState)) {
			return newState;
		}
		newState=MiniMaxDecision(newState,symbol,4);
		
		return newState;
	}
	
	public static State MiniMaxDecision(State state, char symbol, int ply){
		//create new state from old state
		State newState=new State(state);
		//get opponent symbol
		char oppSymbol=' ';
		if(symbol=='X'){
			oppSymbol='O';
		}else if(symbol=='O'){
			oppSymbol='X';
		}
		//get children of current state
		ArrayList<State> children=getChildrenStates(state,symbol);
		//set max to smallest possible value
		int max=Integer.MIN_VALUE;
		//hold int
		int hold;
		//loop through children to find max value of minValue call
		for(State child: children){
			hold=minValue(child,ply-1,symbol,oppSymbol);
			//update max as we go
			if(hold>max){
				max=hold;
				newState=child;
			}
		}
		//return current state
		return newState;
	}
	
	public static int minValue(State state,int depth,char symbol,char oppSymbol){
		//check to see if we are at a terminal state or if depth is 0
		if(terminalTest(state) || depth==0){
			//calculate the heuristic of the state
			state.calcHeuristic(symbol);
			//return heuristic value
			return state.heuristic;
		}
		//set min to largest possible value
		int min=Integer.MAX_VALUE;
		//get children
		ArrayList<State> children=getChildrenStates(state,oppSymbol);
		//loop through children and find min of maxValue call
		for(State child: children){
			//update min as we go
			expanded++;
			min=Integer.min(min, maxValue(child,depth-1,symbol,oppSymbol));
		}
		//return min
		return min;
	}
	
	public static int maxValue(State state,int depth,char symbol,char oppSymbol){
		//nearly the same as minValue, except that now we will find the max of the returned values from minValue
		if(terminalTest(state) || depth==0){
			state.calcHeuristic(symbol);
			return state.heuristic;
		}
		int v=Integer.MIN_VALUE;
		ArrayList<State> children=getChildrenStates(state,symbol);
		for(State child: children){
			expanded++;
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
		//create array list to hold our children states
		ArrayList<State> children=new ArrayList<State>();
		//get children states for each available position on board
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
		//return children
		return children;
	}
	
	/**
	 * Checks if any players won, or no nodes are free
	 * 
	 * @param state
	 * @return symbol of winning player, F if no one is winning, and D if the board is full
	 */
	public static boolean terminalTest(State state){
		//check if baord is full
		if(state.isBoardFull()){
			return true;
			//check if X won
		}else if(state.checkforWin('X')){
			return true;
			//check if O won
		}else if(state.checkforWin('O')){
			return true;
		}else{
			//return false for not a terminal state
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
