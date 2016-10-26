package hw6;

public class HW6 {
	
	public static void main(String[] args) {
		
		
		State example=new State(5);
		example.markPosition(example.getBoard(), 2, 5, '1');
		example.markPosition(example.getBoard(), 4, 1, '2');
		example.initializeValid();
		
		State prob1=new State(5);
		prob1.markPosition(prob1.getBoard(), 1, 5, '0');
		prob1.markPosition(prob1.getBoard(), 3, 4, '1');
		prob1.initializeValid();
		
		State prob2=new State(6);
		prob2.markPosition(prob2.getBoard(), 2, 6, '0');
		prob2.markPosition(prob2.getBoard(), 4, 5, '2');
		prob2.initializeValid();
		
		State prob3=new State(6);
		prob3.markPosition(prob3.getBoard(), 1, 6, '1');
		prob3.markPosition(prob3.getBoard(), 4, 3, '1');
		prob3.markPosition(prob3.getBoard(), 6, 4, '2');
		prob3.initializeValid();
		
		State prob4=new State(7);
		prob4.markPosition(prob4.getBoard(), 1, 7, '1');
		prob4.markPosition(prob4.getBoard(), 4, 6, '0');
		prob4.markPosition(prob4.getBoard(), 5, 1, '2');
		prob4.initializeValid();
		
		example.printBoard();
		example.printValidBoard();
		prob1.printBoard();
		prob1.printValidBoard();
		prob2.printBoard();
		prob2.printValidBoard();
		prob3.printBoard();
		prob3.printValidBoard();
		prob4.printBoard();
		prob4.printValidBoard();
		
		//TESTING
		prob1.printValidBoard();
		prob1.markPosition(prob1.valid, 3, 3, 'X');
		prob1.updateValid(3, 3);
		prob1.printValidBoard();
		
		System.out.println("\n\n");
		prob2.printValidBoard();
		prob2.markPosition(prob2.valid, 4, 6, 'X');
		if(prob2.updateValid(4, 6)==false)
			System.out.println("Board invalid!");
		prob2.printValidBoard();
		prob2.markPosition(prob2.valid, 3, 4, 'X');
		if(prob2.updateValid(3, 4)==false)
			System.out.println("Board invalid!");
		prob2.printValidBoard();
		prob2.markPosition(prob2.valid, 2, 2, 'X');
		if(prob2.updateValid(2, 2)==false)
			System.out.println("Board invalid!");
		prob2.printValidBoard();
	}

}
