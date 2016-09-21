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
//		System.out.println("Starting IDS:");
//		long startTimeIDS1=System.nanoTime();
//		ArrayList<String> path1=IDS(instance1);
//		long endTimeIDS1=System.nanoTime();
//
//		System.out.println("IDS Instance 1 time: "+((endTimeIDS1-startTimeIDS1)/1000000)+" ms");
//		
//		
//		long startTimeIDS2=System.nanoTime();
//		ArrayList<String> path2=IDS(instance2);
//		long endTimeIDS2=System.nanoTime();
		
//		System.out.println("IDS Instance 2 time: "+((endTimeIDS2-startTimeIDS2)/1000000)+" ms");
		
				
		State instance1_DFGS=new State(4,4,dirty1,3,2, "");
			
				
		State instance2_DFGS=new State(5,6,dirty2,3,2, "");
		
		System.out.println("Starting DFGS:");
		
		long startTimeDFGS1=System.nanoTime();
		ArrayList<String> DFGSpath1=DFGS(instance1_DFGS);
		long endTimeDFGS1=System.nanoTime();

		System.out.println("DFGS Instance 1 time: "+((endTimeDFGS1-startTimeDFGS1)/1000000)+" ms");
		
	}
	
	public static ArrayList<String> DFGS(State instance){
		ArrayList<String> path=new ArrayList<String>();
		ArrayList<State> expanded=new ArrayList<State>();
		Tree<State> graph=new Tree<State>(instance);
		expanded.add(instance);
		path=depthSearch(graph.getRoot(),path,expanded);
		if(!expanded.isEmpty()){
			System.out.println("Expanded:");
			for(int i=0;i<10;i++){
				System.out.println("	"+expanded.get(i));
			}
		}
		if(!path.isEmpty()){
			System.out.println("Path:");
			for(String s: path){
				System.out.println(s);
			}
			//put step cost here if needed
		}else{
			System.out.println("No Path Found : ");
		}
		return path;
	}
	
	public static ArrayList<String> depthSearch(Tree.Node<State> currNode,ArrayList<String> path,ArrayList<State> expanded){
		//pull in current node's state
		State currState=currNode.getData();
		System.out.println(currState.getVacuumX()+","+currState.getVacuumY());
		System.out.println(currState.printRooms());
		//check if goal is achieved
		if(currState.getNumDirty()==0){
			//add current action to list, and return. Checks if it's the root first though.
			if(currState.getPredecessorAction()!=""){
				path.add(currState.getPredecessorAction());
			}
			return path;
			//check if depth limit is reached
		}
		int pathLength=path.size();
		//get all children nodes
		getChildrenNodesDFGS(currNode,expanded);
		for(Tree.Node<State> hold: currNode.getChildren()){
			boolean isExpanded=false;
			for(State hold2: expanded){
				if(hold.getData().compare((State)hold2)){
					isExpanded=true;
				}
			}
			if(!isExpanded){
				expanded.add(hold.getData());
				//System.out.println(expanded.toString());
				System.out.println(expanded.size());
				path=depthSearch(hold, path, expanded);
				if(pathLength!=path.size()){
					return path;
				}
			}
			System.out.println(expanded.toString());
			System.out.println("test");
		}
		return path;
	}
	
	public static ArrayList<String> IDS(State instance){
		int maxDepth=1;
		int programLimit=10;

		
		ArrayList<String> path=new ArrayList<String>();
		boolean done=false;
		ArrayList<String> expanded=new ArrayList<String>();
		while(maxDepth<programLimit && !done){
			expanded.clear();

			Tree<State> graph=new Tree<State>(instance);
			path=depthLimitedSearch(graph.getRoot(),path,expanded,0,maxDepth);

			if(path.isEmpty()){
				System.out.println("Depth of "+maxDepth+" failed");
				maxDepth++;
			}else{
				done=true;
			}
		}
		if(!expanded.isEmpty()){
			System.out.println("Expanded:");
			for(int i=0;i<10;i++){
				System.out.println("	"+expanded.get(i));
			}
		}
		if(!path.isEmpty()){
			System.out.println("Path:");
			for(String s: path){
				System.out.println(s);
			}
			//put step cost here if needed
		}else{
			System.out.println("No Path Found : ");
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
		//save path size
		int pathLength=path.size();
		//find all children nodes
		getChildrenNodesIDS(currNode,expanded);
		for(Tree.Node<State> hold: currNode.getChildren()){
			path=depthLimitedSearch(hold, path, expanded,currDepth+1, depthLimit);
			if(pathLength!=path.size()){
				return path;
			}
		}
		return path;
	}
	
	public static void getChildrenNodesIDS(Tree.Node<State> parent,ArrayList<String> expanded){
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
	
	public static void getChildrenNodesDFGS(Tree.Node<State> parent,ArrayList<State> expanded){
		State currState=parent.getData();
		//Chunk of choosing logic is in here, if order are wrong then this is the place to look
		if(currState.isActionValid("Up")){
			//create new state to save change, and apply change
			State UpState=new State(currState);
			UpState.setVacuumX(currState.getVacuumX()-1);
			
			//check if state has already been expanded
			if(!expanded.contains(UpState)) {
				//Check if node already exists
				Tree.Node<State> hold=parent.findNode(UpState,new ArrayList<State>());
				if(hold!=null){
					hold.addEdge(parent);		
				}else{
					//add node with state to graph
					parent.addEdge(UpState);
				}
			}
//			expanded.add(UpState);
		}
		if(currState.isActionValid("Left")){
			//create new state to save change, and apply change
			State LeftState=new State(currState);
			LeftState.setVacuumY(currState.getVacuumY()-1);
			//check if state has already been expanded
			if(!expanded.contains(LeftState)) {
				//Check if node already exists
				Tree.Node<State> hold=parent.findNode(LeftState,new ArrayList<State>());
				if(hold!=null){
					hold.addEdge(parent);			
				}else{
					//add node with state to graph
					parent.addEdge(LeftState);
				}
			}
//			expanded.add(LeftState);
		}
		System.out.print(currState.isActionValid("Suck"));
		if(currState.isActionValid("Suck")){
			
			//create new state to save changes, and change
			State SuckState=new State(currState);
			SuckState.CleanCurrRoom();
			//check if state has already been expanded
			if(!expanded.contains(SuckState)) {
				//Check if node already exists
				Tree.Node<State> hold=parent.findNode(SuckState,new ArrayList<State>());
				if(hold!=null){
					hold.addEdge(parent);		
				}else{
					//add node with state to graph
					parent.addEdge(SuckState);
				}
			}
//			expanded.add(SuckState);
		}
		if(currState.isActionValid("Right")){
			//create new state to save changes, and change
			State RightState=new State(currState);
			RightState.setVacuumY(currState.getVacuumY()+1);
			//check if state has already been expanded
			if(!expanded.contains(RightState)) {
				//Check if node already exists
				Tree.Node<State> hold=parent.findNode(RightState,new ArrayList<State>());
				if(hold!=null){
					hold.addEdge(parent);		
				}else{
					//add node with state to graph
					parent.addEdge(RightState);
				}
			}
//			expanded.add(RightState);
		}
		if(currState.isActionValid("Down")){
			//create new state to save changes, and change
			State DownState=new State(currState);
			DownState.setVacuumX(currState.getVacuumX()+1);
			//check if state has already been expanded
			if(!expanded.contains(DownState)) {
				//Check if node already exists
				Tree.Node<State> hold=parent.findNode(DownState,new ArrayList<State>());
				if(hold!=null){
					hold.addEdge(parent);		
				}else{
					//add node with state to graph
					parent.addEdge(DownState);
				}
			}
			//expanded.add(DownState);
		}
	}
}

