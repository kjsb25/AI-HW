import java.util.ArrayList;
import java.util.Random;

public class HW2 {
	
	
	public static void main(String[] args) {
		
		//Create Instance 1 Initial State
		ArrayList<DirtyIndex> dirty1=new ArrayList<DirtyIndex>();
		dirty1.add(new DirtyIndex(1,2));
		dirty1.add(new DirtyIndex(1,4));
		dirty1.add(new DirtyIndex(2,2));
		dirty1.add(new DirtyIndex(2,3));
		dirty1.add(new DirtyIndex(3,1));
		dirty1.add(new DirtyIndex(4,2));
		dirty1.add(new DirtyIndex(4,4));
		
		State instance1=new State(4,4,dirty1,3,2, "");

		//Create Instance 2 Initial State
		ArrayList<DirtyIndex> dirty2=new ArrayList<DirtyIndex>();
		dirty2.add(new DirtyIndex(1,2));
		dirty2.add(new DirtyIndex(1,4));
		dirty2.add(new DirtyIndex(1,6));
		dirty2.add(new DirtyIndex(2,1));
		dirty2.add(new DirtyIndex(2,3));
		dirty2.add(new DirtyIndex(2,4));
		dirty2.add(new DirtyIndex(3,1));
		dirty2.add(new DirtyIndex(3,5));
		dirty2.add(new DirtyIndex(3,6));
		dirty2.add(new DirtyIndex(4,2));
		dirty2.add(new DirtyIndex(4,4));
		dirty2.add(new DirtyIndex(5,3));
		dirty2.add(new DirtyIndex(5,4));
		dirty2.add(new DirtyIndex(5,6));
		
		State instance2=new State(5,6,dirty2,3,2, "");

		long startTimeIDS1=System.nanoTime();
		ArrayList<String> path1=IDS(instance1);
		long endTimeIDS1=System.nanoTime();

		System.out.println("IDS Instance 1 time: "+((endTimeIDS1-startTimeIDS1)/1000000)+" ms");
		
		
		long startTimeIDS2=System.nanoTime();
		ArrayList<String> path2=IDS(instance2);
		long endTimeIDS2=System.nanoTime();
		
		System.out.println("IDS Instance 2 time: "+((endTimeIDS2-startTimeIDS2)/1000000)+" ms");
		
		
	}
	
	public static ArrayList<String> IDS(State instance){
		int maxDepth=1;
		int programLimit=10;

		
		ArrayList<String> path=new ArrayList<String>();
		boolean done=false;
		while(maxDepth<programLimit && !done){
			ArrayList<String> expanded=new ArrayList<String>();

			Tree<State> graph=new Tree<State>(instance);
			path=depthLimitedSearch(graph.getRoot(),path,expanded,0,maxDepth);

			if(path.isEmpty()){
				System.out.println("Depth of "+maxDepth+" failed");
				maxDepth++;
			}else{
				done=true;
			}
			if(maxDepth==programLimit && !done){
				System.out.println("Limit Reached");
				if(!expanded.isEmpty()){
					System.out.println("Expanded:");
					for(int i=0;i<10;i++){
						System.out.println("	"+expanded.get(i));
					}
				}
			}
		}
		if(!path.isEmpty()){
			System.out.println("Path:");
			for(String s: path){
				System.out.println(s);
			}
		}else{
			System.out.println("No Path Found");
		}
		return path;
		
		
	}
	
	public static ArrayList<String> depthLimitedSearch(Tree.Node<State> currNode,ArrayList<String> path,ArrayList<String> expanded,int currDepth,int depthLimit){
		//pull in current node's state
		State currState=currNode.getData();
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
		int pathLength=path.size();
		getChildrenNodes(currNode,expanded);
		for(Tree.Node<State> hold: currNode.getChildren()){
			path=depthLimitedSearch(hold, path, expanded,currDepth+1, depthLimit);
			if(pathLength!=path.size()){
				return path;
			}
		}
		return path;
	}
	
	public static void getChildrenNodes(Tree.Node<State> parent,ArrayList<String> expanded){
		State currState=parent.getData();
		//Chunk of choosing logic is in here, if order are wrong then this is the place to look
		if(currState.isActionValid("Up")){
			//create new state to save change, and apply change
			State UpState=new State(currState);
			UpState.setVacuumX(currState.getVacuumX()-1);
			
			//add node with state to graph
			parent.addChild(UpState);

			expanded.add("UP "+UpState.getVacuumX()+","+UpState.getVacuumY());
		}
		if(currState.isActionValid("Left")){
			//create new state to save change, and apply change
			State LeftState=new State(currState);
			LeftState.setVacuumY(currState.getVacuumY()-1);
			
			//add node with state to graph
			parent.addChild(LeftState);
			
			expanded.add("Left "+LeftState.getVacuumX()+","+LeftState.getVacuumY());
		}
		if(currState.isActionValid("Suck")){
			//create new state to save changes, and change
			State SuckState=new State(currState);
			SuckState.CleanCurrRoom();
			
			//add node with state to the graph
			parent.addChild(SuckState);
			
			expanded.add("Suck "+SuckState.getVacuumX()+","+SuckState.getVacuumY());
		}
		if(currState.isActionValid("Right")){
			//create new state to save changes, and change
			State RightState=new State(currState);
			RightState.setVacuumY(currState.getVacuumY()+1);
			
			//add node with state to the graph
			parent.addChild(RightState);
			
			expanded.add("Right "+RightState.getVacuumX()+","+RightState.getVacuumY());
		}
		if(currState.isActionValid("Down")){
			//create new state to save changes, and change
			State DownState=new State(currState);
			DownState.setVacuumX(currState.getVacuumX()+1);
			
			//add node with state to the graph
			parent.addChild(DownState);
			
			expanded.add("Down "+DownState.getVacuumX()+","+DownState.getVacuumY());
		}
	}
}

