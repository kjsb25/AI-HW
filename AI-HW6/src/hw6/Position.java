package hw6;

import java.util.Comparator;

public class Position implements Comparable<Position> {
	private int x;
	private int y;
	private int mrv=Integer.MAX_VALUE;
	private int degreeH=Integer.MIN_VALUE;

	
	
	public Position(int x, int y,int mrv) {
		super();
		this.x = x;
		this.y = y;
		this.mrv=mrv;
	}
	
	public Position(Position copy) {
		super();
		this.x = copy.getX();
		this.y = copy.getY();
		this.mrv=copy.getMrv();
	}
	
	public void calcDegreeH(State state){
		int tempX;
		int tempY;
		int degree=0;
		for(tempX=1;tempX<=state.getBoardLength();tempX++) {
			if(' '==state.valueAtPos(state.getValid(),tempX,y)){
				degree++;								
			}
		}
		degree--;
		for(tempY=1;tempY<=state.getBoardLength();tempY++) {
			if(' '==state.valueAtPos(state.getValid(),x,tempY)){
				degree++;				
			}
		}
		degree--;
		for(int messy=1;messy<=state.getBoardLength();messy++) {
			if(' '==state.valueAtPos(state.getValid(),x-messy,y-messy)){
				degree++;
			}
			if(' '==state.valueAtPos(state.getValid(),x-messy,y+messy)){
				degree++;
			}
			if(' '==state.valueAtPos(state.getValid(),x+messy,y-messy)){
				degree++;
			}
			if(' '==state.valueAtPos(state.getValid(),x+messy,y+messy)){
				degree++;
			}
		}
		degree+=checkForNewZero(state,x-1,y-1);
		degree+=checkForNewZero(state,x,y-1);
		degree+=checkForNewZero(state,x+1,y-1);
		degree+=checkForNewZero(state,x-1,y);
		degree+=checkForNewZero(state,x+1,y);
		degree+=checkForNewZero(state,x-1,y+1);
		degree+=checkForNewZero(state,x,y+1);
		degree+=checkForNewZero(state,x+1,y+1);
		
		this.degreeH=degree;
	}
	
	public int checkForNewZero(State state,int x,int y) {
		int degree=0;
		char temp = state.valueAtPos(state.getValid() ,x,y);
		if(temp=='1') {
//				System.out.println("TEST\n\n");
			if(' '==state.valueAtPos(state.getValid(),x-1,y-1)){
				if(duplicatePostion(x-1,y-1)==false) {
					degree++;
				}
			}
			if(' '==state.valueAtPos(state.getValid(),x,y-1)){
				if(duplicatePostion(x,y-1)==false) {
					degree++;
				}
			}
			if(' '==state.valueAtPos(state.getValid(),x+1,y-1)){
				if(duplicatePostion(x+1,y-1)==false) {
					degree++;
				}
			}
			if(' '==state.valueAtPos(state.getValid(),x-1,y)){
				if(duplicatePostion(x-1,y)==false) {
					degree++;
				}
			}
			if(' '==state.valueAtPos(state.getValid(),x+1,y)){
				if(duplicatePostion(x+1,y)==false) {
					degree++;
				}
			}
			if(' '==state.valueAtPos(state.getValid(),x-1,y+1)){
				if(duplicatePostion(x-1,y+1)==false) {
					degree++;
				}
			}
			if(' '==state.valueAtPos(state.getValid(),x,y+1)){
				if(duplicatePostion(x,y+1)==false) {
					degree++;
				}
			}
			if(' '==state.valueAtPos(state.getValid(),x+1,y+1)){
				if(duplicatePostion(x+1,y+1)==false) {
					degree++;
				}
			}
		}
		return degree;
	}
	
	public boolean duplicatePostion(int checkX, int checkY) {
		if(this.x==checkX || this.y==checkY) {
			return true;
		}
		for(int messy=1;messy<=2;messy++) {
			if(x-messy==checkX && y-messy==checkY) {
				return true;
			}
			if(x-messy==checkX && y+messy==checkY) {
				return true;
			}
			if(x+messy==checkX && y-messy==checkY) {
				return true;
			}
			if(x+messy==checkX && y+messy==checkY) {
				return true;
			}
		}
		return false;
	}
	
	public static Comparator<Position> mrvSort= new Comparator<Position>(){
		public int compare(Position s1,Position s2){
			int mrv1=s1.getMrv();
			int mrv2=s2.getMrv();
			
			return mrv2-mrv1;
		}
	};
	
	public static Comparator<Position> degreeHSort= new Comparator<Position>(){
		public int compare(Position s1,Position s2){
			int degree1=s1.getDegreeH();
			int degree2=s2.getDegreeH();
			
			return degree1-degree2;
		}
	};
	
	public void print(){
		System.out.print("("+x+","+y+") MRV: "+mrv+" Degree Heuristic: ");
		if(degreeH==Integer.MIN_VALUE){
			System.out.println(" N/A");
		}else{
			System.out.println(degreeH);
		}
	}

	@Override
	public int compareTo(Position o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getMrv() {
		return mrv;
	}

	public void setMrv(int mrv) {
		this.mrv = mrv;
	}

	public int getDegreeH() {
		return degreeH;
	}

	public void setDegreeH(int degreeH) {
		this.degreeH = degreeH;
	}
	
	

}
