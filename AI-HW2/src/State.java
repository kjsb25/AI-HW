import java.util.ArrayList;

public class State {
	private int[][] layout;
	private String predecessorAction;
	private int maxX;
	private int maxY;
	private int vacuumX;
	private int vacuumY;
	
	
	public String getPredecessorAction() {
		return predecessorAction;
	}

	public void setPredecessorAction(String predecessorAction) {
		this.predecessorAction = predecessorAction;
	}

	public State(int maxX, int maxY,ArrayList<DirtyIndex> dirty, int vacuumX, int vacuumY,String predecessorAction) {
		super();
		setLayout(maxX,maxY,dirty);
		this.predecessorAction = predecessorAction;
		this.maxX = maxX;
		this.maxY = maxY;
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
	
	public String printRooms(){
		StringBuilder out=new StringBuilder();
		
		out.append("Room:\n");
		for(int i=0;i<maxX;i++){
			for(int j=0;j<maxY;j++){
				if(layout[i][j]==0){
					out.append("[ ]");
				}else{
					out.append("[*]");
				}
			}
			out.append("\n");
		}
		return out.toString();
	}
	
	//TODO RECHECK ALL OF THIS, THIS SHIT IS IMPORTANT. 
	public boolean isActionValid(String action){
		if(action=="left"){
			if(vacuumX-1<=0){
				return true;
			}else
				return false;
		}else if(action=="Right"){
			if(vacuumX+1>=maxX){
				return true;
			}else
				return false;
		}else if(action=="Down"){
			//TODO is this right?
			if(vacuumY+1>=maxY){
				return true;
			}else
				return false;
		}else if(action=="Up"){
			if(vacuumY-1<=0){
				return true;
			}else
				return false;
		}else if(action=="Suck"){
			if(isCurrRoomDirty()){
				return true;
			}else
				return false;
		}else{
			System.out.print("incorrect action passed to isActionValid. Passed value is: "+action);
			return false;
		}
	}
	
	public boolean isCurrRoomDirty(){
		return (layout[vacuumX][vacuumY]==1);
	}
	
	public int getNumDirty(){
		int numDirty=0;
		for(int i=0;i<maxX;i++){
			for(int j=0;j<maxY;j++){
				if(layout[i][j]==1){
					numDirty++;
				}
			}
		}
		return numDirty;
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
