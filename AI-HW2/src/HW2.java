import java.util.ArrayList;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;

public class HW2 {
	
	
	public static void main(String[] args) {
		
		ArrayList<DirtyIndex> dirty=new ArrayList<DirtyIndex>();
		dirty.add(new DirtyIndex(0,1));
		dirty.add(new DirtyIndex(0,3));
		dirty.add(new DirtyIndex(1,1));
		dirty.add(new DirtyIndex(1,2));
		dirty.add(new DirtyIndex(2,0));
		dirty.add(new DirtyIndex(3,1));
		dirty.add(new DirtyIndex(3,3));
		
		State instance1=new State(4,4,dirty,3,2, "");
		

		
		ArrayList<String> path=IDS(instance1);
		
		System.out.println(instance1.printRooms());
		
		
	}
	
	//could be pointless. Was created before I realized the graph is generated during the search. Derp.
	public static ArrayList<String> IDS(State instance){
		int maxDepth=0;
		ArrayList<String> path=new ArrayList<String>();
		while(true){
			Graph graph = new SingleGraph("Instance 1");
			//TODO Remove before sending, only for visualization
			graph.addAttribute("ui.stylesheet", "url('file:///C:/Users/Koonan/OneDrive/GIT/AI-HW/AI-HW2/lib/style.css')");
			graph.addNode("1");
			Node root=graph.getNode(1);
			root.addAttribute("state", instance);
			path=depthLimitedSearch(root,path,0,maxDepth);
			graph.display();
			//TODO add logic to check if valid
			
			maxDepth++;
		}
		
		
	}
	
	public static ArrayList<String> depthLimitedSearch(Node currNode,ArrayList<String> path,int currDepth,int depthLimit){
		//pull in current node's state
		State currState=(State)currNode.getAttribute("state");
		//check if goal is achieved
		if(currState.getNumDirty()==0){
			//add current action to list, and return. Checks if it's the root first though.
			if(currState.getPredecessorAction()!=""){
				path.add(currState.getPredecessorAction());
			}
			return path;
			//check if depth limit is reached
		}else if(currDepth==depthLimit){
			//returning the same list that was input is regarded as a failure
			return path;
		}
		//evaluate each successor
		
		
		return path;
	}
	
	public static ArrayList<Node> getChildrenNodes(Node parent){
		ArrayList<Node> out=new ArrayList<Node>();
		State currState=(State)parent.getAttribute("state");
		
		//Chunk of choosing logic is in here, if order are wrong then this is the place to look
		if(currState.isActionValid("Up")){
			
		}
		if(currState.isActionValid("Left")){

		}
		if(currState.isActionValid("Suck")){

		}
		if(currState.isActionValid("Down")){

		}
		if(currState.isActionValid("Right")){

		}
	}
	
	


//		ArrayList<Room> ids_result=IDS(Instance1,3,2);
//		if(ids_result==null){
//			System.out.print("No IDS Solution found");
//		}else{
//			for(Room room: ids_result){
//				System.out.println(room.toString());
//			}
//		}
//		printRooms(Instance1);
		
//	}
	
//	public static ArrayList<Room> IDS(Room rooms[][],int x, int y){
//		
//		ArrayList<Room> path=new ArrayList<Room>();
//		int depth=0;
//		
//		//initialize visited tracker array
//		boolean visited[][]=new boolean[instancex][instancey];
//		for(int i=0;i<instancex;i++){
//			for(int j=0;j<instancey;j++){
//				visited[i][j]=false;
//			} 
//		}
//		while(true){
//			System.out.print(depth);
//			path=recursiveDFS(rooms,visited,x,y,0,depth);
//			if(path!=null){
//				return path;
//			}
//			depth++;
//		}
//	}
	
	//TODO needs editing
//	public static ArrayList<Room> recursiveDFS(Room rooms[][],boolean visited[][],int currX,int currY,int currDepth,int max_depth){
//		System.out.println("("+currX+","+currY+")\n");
//		//mark as visited
//		visited[currX][currY]=true;
//		
//		//if current room is dirty, then clean
//		if(!rooms[currX][currY].getClean()){
//			rooms[currX][currY].setClean(true);
//		}
//		
//		//set hold value to find if whole room is clean
//		boolean clean=true;
//		for(Room[] row: rooms){
//			for(Room room: row){
//				if(!room.getClean()){
//					clean=false;
//				}
//			}
//		}
//		//if room is clean, return current node
//		if(clean){
//			ArrayList<Room> hold=new ArrayList<Room>();
//			hold.add(rooms[currX][currY]);
//			return hold;
//		}
//		//check if depth has been reached
//		if(currDepth==max_depth){
//			System.out.print("Max depth");
//			return null;
//		}
//		//find neighbors, and run dfs on each
//		ArrayList<Room> validneighbors=getUnvisitedNeighbors(rooms,visited,currX,currY,instancex,instancey);
//		if(!validneighbors.isEmpty()){
//			System.out.print("Valid Neighbors Exist");
//			System.out.println(validneighbors);
//			for(Room room: validneighbors){
//				ArrayList<Room> hold=recursiveDFS(rooms,visited,room.getX(),room.getY(),currDepth+1,max_depth);
//				if(hold!=null){
//					hold.add(rooms[currX][currY]);
//					return hold;			
//				}
//			}
//		}else{
//			System.out.print("No valid Neighbors");
//		}
//		System.out.print("Failed node");
//		return null;
//	}
//	
//	public static ArrayList<Room> getUnvisitedNeighbors(Room instance[][],boolean visited[][],int x,int y, int xSize, int ySize){
//		ArrayList<Room> out=new ArrayList<Room>();
//			
//		if(x-1>=0 && !visited[x-1][y]){
//			System.out.print("Left");
//			out.add(instance[x-1][y]);
//		}
//		if(y-1>=0 && !visited[x][y-1]){
//			System.out.print("Down");
//			out.add(instance[x][y-1]);
//		}
//		if(x+1<ySize  && !visited[x][y+1]){
//			System.out.print("Up");
//			out.add(instance[x][y+1]);
//		}
//		if(x+1<xSize && !visited[x+1][y]){
//			System.out.print("Right");
//			out.add(instance[x+1][y]);
//		}
//		for(Room room: out){
//			System.out.print(room.toString());
//		}
//		return out;
//	}

	
	

}

