import java.util.ArrayList;

public class State {
	private static int[][] layout;
	private static int maxX;
	private static int maxY;
	private static int vacuumX;
	private static int vacuumY;
	
	
	public State(int maxX,int maxY,ArrayList<DirtyIndex> dirty, int vacuumX, int vacuumY) {
		super();
		setLayout(maxX,maxY,dirty);
		this.maxX=maxX;
		this.maxY=maxY;
		this.vacuumX = vacuumX;
		this.vacuumY = vacuumY;
	}
	
	public int[][] getLayout() {
		return layout;
	}

	public void setLayout(int maxX, int maxY,ArrayList<DirtyIndex> dirtyList) {
		int[][] layout=new int[maxX][maxY];
		
		for(DirtyIndex hold: dirtyList){
			layout[hold.x][hold.y]=1;
		}
		this.layout = layout;
	}
	
	public void printRooms(){
		System.out.println("Room:");
		for(int i=0;i<maxX;i++){
			for(int j=0;j<maxY;j++){
				if(layout[i][j]==0){
					System.out.print("[ ]");
				}else{
					System.out.print("[*]");
				}
			}
			System.out.print("\n");
		}
	}

	public int getVacuumX() {
		return vacuumX;
	}
	public void setVacuumX(int vacuumX) {
		this.vacuumX = vacuumX;
	}
	public int getVacuumY() {
		return vacuumY;
	}
	public void setVacuumY(int vacuumY) {
		this.vacuumY = vacuumY;
	}
	
	
}
