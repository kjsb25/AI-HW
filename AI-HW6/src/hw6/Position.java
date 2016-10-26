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
		System.out.println("("+x+","+y+") MRV: "+mrv);
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
