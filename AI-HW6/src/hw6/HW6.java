package hw6;

import java.util.ArrayList;

public class HW6 {
	
	public static void main(String[] args) {
		
		
		State example=new State(5);
		example.markPosition(example.getBoard(), 2, 5, '1');
		example.markPosition(example.getBoard(), 4, 1, '2');
//		State example_sol=backtracking(example);
		ArrayList<Position> positions =getAllMoves(example);
		example.printBoard();
		for(Position curr: positions){
			curr.print();
		}
		
		
		State prob1=new State(5);
		prob1.markPosition(prob1.getBoard(), 1, 5, '0');
		prob1.markPosition(prob1.getBoard(), 3, 4, '1');
		
		State prob2=new State(6);
		prob2.markPosition(prob2.getBoard(), 2, 6, '0');
		prob2.markPosition(prob2.getBoard(), 4, 5, '2');
		
		State prob3=new State(6);
		prob3.markPosition(prob3.getBoard(), 1, 6, '1');
		prob3.markPosition(prob3.getBoard(), 4, 3, '1');
		prob3.markPosition(prob3.getBoard(), 6, 4, '2');
		
		State prob4=new State(7);
		prob4.markPosition(prob4.getBoard(), 1, 7, '1');
		prob4.markPosition(prob4.getBoard(), 4, 6, '0');
		prob4.markPosition(prob4.getBoard(), 5, 1, '2');
		
		example.printBoard();
//		example.printValidBoard();
//		prob1.printBoard();
//		prob1.printValidBoard();
//		prob2.printBoard();
//		prob2.printValidBoard();
//		prob3.printBoard();
//		prob3.printValidBoard();
//		prob4.printBoard();
//		prob4.printValidBoard();
	}
	
	/**
	 * Returns a list of all possible moves. Degree Heuristic is NOT calculated yet.
	 * 
	 * @param state
	 * @return
	 */
	public static ArrayList<Position> getAllMoves(State state){
		ArrayList<Position> moves=new ArrayList<Position>();
		int length=state.getBoardLength();
		//check rows
		for(int x=1;x<=length;x++){
			int mrv=0;
			for(int j=1;j<=length;j++){
				if(Character.compare(' ',state.valueAtPos(x, j))==0){
					mrv++;
				}
			}
			for(int j=1;j<=length;j++){
				moves.add(new Position(j,x,mrv));
			}
		}
		//check columns
		for(int x=1;x<=length;x++){
			int mrv=0;
			for(int j=1;j<=length;j++){
				if(Character.compare(' ',(state.valueAtPos(j, x)))==0){
					mrv++;
				}
			}
			for(int j=1;j<=length;j++){
				//TODO There might be a problem here, fix if needed
				if(mrv<moves.get(j).getMrv()){
					moves.remove(j);
					moves.add(new Position(x,j,mrv));
				}
			}
			
		}
		return moves;
	}
	

}
