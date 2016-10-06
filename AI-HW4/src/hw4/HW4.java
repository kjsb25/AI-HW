package hw4;

import java.util.Scanner;

public class HW4 {

	public static void main(String[] args) {
		State currState=new State();
		currState.printBoard();
		char player='X';
		while(!currState.win){
			currState = BeginnerDecision(currState, 'O');
			System.out.println(currState.win);
			currState.printBoard();
			if(currState.win==true)
				break;
			currState=userDecision(currState,player);
			System.out.println(currState.win);
			currState.printBoard();
			if(currState.win==true)
				System.out.println("\n\nConfusion\n\n");
		}
	
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
	
	public static State userDecision(State state,char symbol){
		State newState=new State(state);
		
		//create new reader for input, and testing boolean
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		String input_string;
		boolean input_valid=false;
		
		//while a move hasnt been made
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
//		reader.close();
		return newState;
	}
	

}
