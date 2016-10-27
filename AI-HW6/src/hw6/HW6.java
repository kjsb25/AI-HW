package hw6;

import java.util.ArrayList;
import java.util.Iterator;

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
		
		State result = null;
		result = backtracking(prob3);
		if(result==null) {
			System.out.println("No solution found!");
		}
		else {
			System.out.println("SOLUTION");
			result.printBoard();
			result.printValidBoard();
		}
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
		//check columns
		for(int x=1;x<=length;x++){
			int mrv=0;
			
			for(int j=1;j<=length;j++){
				if(' '==state.valueAtPos(state.getValid(),x, j)){
					mrv++;
				}
			}
			for(int j=1;j<=length;j++){
				if(' '==state.valueAtPos(state.getValid(),x, j)){
					moves.add(new Position(x,j,mrv));
				}
			}
		}
		//check rows
		for(int y=1;y<=length;y++){
			int mrv=0;
			for(int j=1;j<=length;j++){
				if(' '==state.valueAtPos(state.getValid(),j, y)){
					mrv++;
				}
			}
			for(int j=1;j<=length;j++){
				//TODO There might be a problem here, fix if needed
				if(' '==state.valueAtPos(state.getValid(),j, y)){
					Iterator<Position> iter=moves.iterator();
					boolean done=false;
					while(iter.hasNext() && done==false){
						Position curr=iter.next();
						if(curr.getX()==j && curr.getY()==y){
							if(curr.getMrv()>mrv){
								moves.remove(curr);
								moves.add(new Position(j,y,mrv));
							}
							done=true;
						}
					}
				}
			}
			
		}
		moves.sort(Position.mrvSort);
		return moves;
	}

	/**
	 * Runs backtracking algorithm on the given state recursively until a solution is found
	 * @param state
	 * @return the solution
	 */
	public static State backtracking (State state) {
		ArrayList<Position> children= getAllMoves(state);
		ArrayList<Position> degrees = new ArrayList<Position> ();
		State newState = new State(state,state.getBoardLength());
		while(children.size()!=0){
			newState = new State(state,state.getBoardLength());
			degrees.clear();
			int val = children.get(children.size()-1).getMrv();
			while(children.size()!=0 && children.get(children.size()-1).getMrv()==val) {
				degrees.add(children.get(children.size()-1));
				children.remove(children.size()-1);
			}

			for(Position child: degrees) {
				child.calcDegreeH(newState);
			}
			
			if(degrees.size()!=1) {
				degrees.sort(Position.degreeHSort);
			}
			for(Position child: degrees) {
				System.out.println("Testing");
				child.print();
			}
			for(Position child: degrees) {
				newState.printBoard();
				newState = new State(state,state.getBoardLength());
				newState.printBoard();
				if(newState.markPosition(newState.getBoard(), child.getX(), child.getY(), 'X')==true){
					if(newState.isGameWon()) {
						return newState;
					}
					State result = backtracking(newState);
					if(result!=null) {
						return result;
					}
				}
			}
		}
		return null;
	}
}
