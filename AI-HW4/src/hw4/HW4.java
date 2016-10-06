package hw4;

import java.util.Scanner;

public class HW4 {

	public static void main(String[] args) {
		State startingState=new State();
		startingState.printBoard();
		char player='X';
		userDecision(startingState,player);
		
		startingState.printBoard();
	}
	
	//may just implement players here, while they are technically objects it just seems easier to implement them as functions....
	
	public static State BeginnerDecision(State state){
		State newState=new State(state.getBoard());
		
		return newState;
	}
	
	public static boolean userDecision(State state,char symbol){
		//input chekcing
		if(symbol!='O' && symbol!='X'){
			return false;
		}
		//create new reader for input, and testing boolean
		Scanner reader = new Scanner(System.in);
		boolean input_valid=false;
		
		//while a move hasnt been made
		while(!input_valid){
			//print prompt and scan input
			System.out.print("Select x and y coordinates:");
			String input_string=reader.nextLine();
			Scanner input=new Scanner(input_string);
			
			//check for validity
			if(input.hasNextInt()){
				int x=input.nextInt();
				if(input.hasNextInt()){
					int y=input.nextInt();
					//attempt to set that position
					if(state.markPosition(x, y, symbol)){
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
		reader.close();
		return true;
	}
	

}
