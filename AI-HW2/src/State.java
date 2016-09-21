import java.util.ArrayList;
import java.util.Arrays;

/**
 * Here is the State Class that I made
 * @author Keenan Shumard
 *
 */
public class State{
	private int[][] layout;
	private String predecessorAction;
	private int maxX;
	private int maxY;
	private int vacuumX;
	private int vacuumY;
	
	public State(){
		super();
	}
	
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
		System.out.println(original.getMaxX()+","+original.getMaxY());
		this.layout=new int[original.getMaxX()][];
		for(int i = 0; i < original.maxY; i++){
			this.layout[i]=new int[original.getMaxY()];
		    this.layout[i] = Arrays.copyOf(original.layout[i],original.maxY);
		}
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
	
	@Override
	public boolean equals(Object other){
		State otherState=(State)other;
		boolean check1 = true;
		for (int i = 0; check1 && i < otherState.maxX; ++i) {
		    check1 = Arrays.equals(this.layout[i], otherState.layout[i]);
		}
		if(
		check1 &&
		this.predecessorAction == otherState.getPredecessorAction() &&
		this.maxX == otherState.getMaxX() &&
		this.maxY == otherState.getMaxY() &&
		this.vacuumX == otherState.getVacuumX() &&
		this.vacuumY == otherState.getVacuumY()){
			return true;
		}else{
			return false;
		}
	}
	
	public String printRooms(){
		StringBuilder out=new StringBuilder();
		
		out.append("Vacuum: ("+vacuumX+","+vacuumY+")\n");
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
			if(this.vacuumY-1>=0){
				return true;
			}else{
				return false;
			}
		}else if(action=="Right"){
			if(this.vacuumY+1<this.maxY){
				return true;
			}else
				return false;
		}else if(action=="Down"){
			//TODO is this right?
			if(this.vacuumX+1<this.maxX){
				return true;
			}else
				return false;
		}else if(action=="Up"){
			if(this.vacuumX-1>=0){
				return true;
			}else
				return false;
		}else if(action=="Suck"){
			if(this.isCurrRoomDirty()==1){
				return true;
			}else
				return false;
		}else{
			System.out.print("incorrect action passed to isActionValid. Passed value is: "+action);
			return false;
		}
	}
	
	public int isCurrRoomDirty(){
		if(this.vacuumX<0 || this.vacuumY<0 || this.vacuumX>=maxX || this.vacuumY>=maxY){
			System.out.println("Cell out of range ("+this.vacuumX+","+this.vacuumY+")");
			return -1;
		}else{
			return (this.layout[vacuumX][vacuumY]);
		}
	}
	
	public void CleanCurrRoom(){
		this.layout[vacuumX][vacuumY]=0;
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
