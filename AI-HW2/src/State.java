import java.util.ArrayList;

public class State implements Cloneable{
	private int[][] layout;
	private String predecessorAction;
	private int maxX;
	private int maxY;
	private int vacuumX;
	private int vacuumY;
	

	public State(int maxX, int maxY,ArrayList<DirtyIndex> dirty, int vacuumX, int vacuumY,String predecessorAction) {
		super();
		setLayout(maxX,maxY,dirty);
		this.predecessorAction = predecessorAction;
		this.maxX = maxX;
		this.maxY = maxY;
		this.vacuumX = vacuumX-1;
		this.vacuumY = vacuumY-1;
	}
	
	//Constructor used to duplicate a previous state
	public State(State original){
		this.layout=original.getLayout();
		this.predecessorAction = original.getPredecessorAction();
		this.maxX = original.getMaxX();
		this.maxY = original.getMaxY();
		this.vacuumX = original.getVacuumX();
		this.vacuumY = original.getVacuumY();
	}

	public int[][] getLayout() {
		return layout;
	}

	public void setLayout(int maxX, int maxY,ArrayList<DirtyIndex> dirtyList) {
		int[][] layout=new int[maxX][maxY];
		
		for(DirtyIndex hold: dirtyList){
			if(hold.x-1>=0 && hold.y-1>=0){
				layout[hold.x-1][hold.y-1]=1;
			}
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
		if(action=="Left"){
			if(vacuumY-1>=0){
				return true;
			}else{
				return false;
			}
		}else if(action=="Right"){
			if(vacuumY+1<maxY){
				return true;
			}else
				return false;
		}else if(action=="Down"){
			//TODO is this right?
			if(vacuumX+1<maxX){
				return true;
			}else
				return false;
		}else if(action=="Up"){
			if(vacuumX-1>=0){
				return true;
			}else
				return false;
		}else if(action=="Suck"){
			if(isCurrRoomDirty()==1){
				return true;
			}else
				return false;
		}else{
			System.out.print("incorrect action passed to isActionValid. Passed value is: "+action);
			return false;
		}
	}
	
	public int isCurrRoomDirty(){
		if(vacuumX<0 || vacuumY<0 || vacuumX>=maxX || vacuumY>=maxY){
			System.out.println("Cell out of range ("+vacuumX+","+vacuumY+")");
			return -1;
		}else{
			return (layout[vacuumX][vacuumY]);
		}
	}
	
	public void CleanCurrRoom(){
		layout[vacuumX][vacuumY]=1;
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
	
	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
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
	
	public String getPredecessorAction() {
		return predecessorAction;
	}

	public void setPredecessorAction(String predecessorAction) {
		this.predecessorAction = predecessorAction;
	}
	
}
