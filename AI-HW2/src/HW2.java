import java.util.ArrayList;

public class HW2 {
	
	static int instancex;
	static int instancey;

	public static void main(String[] args) {
		
		ArrayList<DirtyIndex> dirty=new ArrayList<DirtyIndex>();
		dirty.add(new DirtyIndex(0,1));
		dirty.add(new DirtyIndex(0,3));
		dirty.add(new DirtyIndex(1,1));
		dirty.add(new DirtyIndex(1,2));
		dirty.add(new DirtyIndex(2,0));
		dirty.add(new DirtyIndex(3,1));
		dirty.add(new DirtyIndex(3,3));
		
		State temp=new State(4,4,dirty,3,2);
		
		temp.printRooms();


//		ArrayList<Room> ids_result=IDS(Instance1,3,2);
//		if(ids_result==null){
//			System.out.print("No IDS Solution found");
//		}else{
//			for(Room room: ids_result){
//				System.out.println(room.toString());
//			}
//		}
//		printRooms(Instance1);
		
	}
	
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

