import java.util.ArrayList;


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
		
				
//		State instance1_DFGS=new State(4,4,dirty1,3,2, "");
//			
//				
//		State instance2_DFGS=new State(5,6,dirty2,3,2, "");
//		
//		System.out.println("Starting DFGS:");
//		
//		long startTimeDFGS1=System.nanoTime();
//		ArrayList<String> DFGSpath1=DFGS(instance1_DFGS);
//		long endTimeDFGS1=System.nanoTime();
//
//		System.out.println("DFGS Instance 1 time: "+((endTimeDFGS1-startTimeDFGS1)/1000000)+" ms");
//		
//		long startTimeDFGS2=System.nanoTime();
//		ArrayList<String> DFGSpath2=DFGS(instance2_DFGS);
//		long endTimeDFGS2=System.nanoTime();
//
//		System.out.println("DFGS Instance 2 time: "+((endTimeDFGS2-startTimeDFGS2)/1000000)+" ms");
		System.out.println("Starting APrime:");
		long startTimeA=System.nanoTime();
		ArrayList<String> path1=APrime(instance1);
		long endTimeA=System.nanoTime();
		System.out.println("IDS Instance 2 time: "+((endTimeA-startTimeA)/1000000)+" ms");
	}
 
	public static ArrayList<String> DFGS(State instance){
		ArrayList<String> path=new ArrayList<String>();
		ArrayList<State> expanded=new ArrayList<State>();
		Tree<State> graph=new Tree<State>(instance);
		expanded.add(instance);
		int results=depthSearch(graph.getRoot(),path,expanded);
		if(!expanded.isEmpty()){
			System.out.println("Expanded:");
			for(int i=0;i<10;i++){
				System.out.println(expanded.get(i).printRooms());
			}
		}
		if(!path.isEmpty()){
			System.out.println("Path:");
			for(String s: path){
				System.out.println(s);
			}
			int cost=0;
			for(String s: path){
				if(s.equals("Left") || s.equals("Right")){
					cost++;
				}else if(s.equals("Up") || s.equals("Down")){
					cost+=1.3;
				}
			}
			System.out.println("Step Cost: "+cost);
		}else{
			System.out.println("No Path Found : ");
		}
		return path;
	}
	
	public static int depthSearch(Tree.Node<State> currNode,ArrayList<String> path,ArrayList<State> expanded){
		//pull in current node's state
		State currState=currNode.getData();
//		System.out.println(currState.getVacuumX()+","+currState.getVacuumY());
//		System.out.println(currState.printRooms());
		//check if goal is achieved
		if(currState.getNumDirty()==0){
			return 1;
		}
		//get all children nodes
		ArrayList<String> actions=getChildrenNodesDFGS(currNode,expanded);
		int i=0;
//		System.out.println("List of actions:");
//		for(String hold1: actions){
//			System.out.println(hold1);
//		}
//		System.out.println("End List");
//		System.out.println("List of nodes:");
//		for(Tree.Node<State> hold1: currNode.getChildren()){
//			System.out.println("	"+hold1.getData().printRooms());
//		}
//		System.out.println("End List");
		for(Tree.Node<State> hold: currNode.getChildren()){
//			System.out.print(currNode.getChildren().size()+" ");
//			System.out.println(actions.size());

			boolean isExpanded=false;
			for(State hold2: expanded){
				if(hold.getData().equals(hold2)){
					isExpanded=true;
				}
			}
			if(!isExpanded){
				expanded.add(hold.getData());
				//System.out.println(expanded.toString());
//				System.out.println(expanded.size());
				path.add(actions.get(i));
				int result=depthSearch(hold, path, expanded);
				if(result==0){
					path.remove(path.size()-1);
				}else{
					return 1;
				}
				i++;
			}
			
		}
		return 0;
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
			int cost=0;
			for(String s: path){
				if(s.equals("Left") || s.equals("Right")){
					cost++;
				}else if(s.equals("Up") || s.equals("Down")){
					cost+=1.3;
				}
			}
			System.out.println("Step Cost: "+cost);
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
	
	public static ArrayList<String> getChildrenNodesDFGS(Tree.Node<State> parent,ArrayList<State> expanded){
		ArrayList<String> actions=new ArrayList<String>();
		State currState=parent.getData();
		System.out.println(currState.printRooms());
		//Chunk of choosing logic is in here, if order are wrong then this is the place to look
		if(currState.isActionValid("Up")){
			//create new state to save change, and apply change
			State UpState=new State(currState);
			UpState.setVacuumX(currState.getVacuumX()-1);
			
			//check if state has already been expanded
			if(!expanded.contains(UpState)) {
				//Check if node already exists
				Tree.Node<State> hold=parent.findNode(UpState,new ArrayList<State>());
				if(hold!=null && !hold.getChildren().contains(parent)){
					hold.addEdge(parent);		
				}else{
					//add node with state to graph
					parent.addEdge(UpState);
				}
				actions.add("Up");
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
				if(hold!=null && !hold.getChildren().contains(parent)){
					hold.addEdge(parent);			
				}else{
					//add node with state to graph
					parent.addEdge(LeftState);
				}
				actions.add("Left");
			}
//			expanded.add(LeftState);
		}
		if(currState.isActionValid("Suck")){
			
			//create new state to save changes, and change
			State SuckState=new State(currState);
			SuckState.CleanCurrRoom();
			//check if state has already been expanded
			if(!expanded.contains(SuckState)) {
				//Check if node already exists
				Tree.Node<State> hold=parent.findNode(SuckState,new ArrayList<State>());
				if(hold!=null && !hold.getChildren().contains(parent)){
					hold.addEdge(parent);		
				}else{
					//add node with state to graph
					parent.addEdge(SuckState);
				}
				actions.add("Suck");
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
				if(hold!=null && !hold.getChildren().contains(parent)){
					hold.addEdge(parent);		
				}else{
					//add node with state to graph
					parent.addEdge(RightState);
				}
				actions.add("Right");
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
				if(hold!=null && !hold.getChildren().contains(parent)){
//					System.out.println("Hold was indeed null");
					hold.addEdge(parent);		
				}else{
					//add node with state to graph
					parent.addEdge(DownState);
				}
				actions.add("Down");
			}
			//expanded.add(DownState);
		}
		return actions;
	}
	
	public static ArrayList<String> ASearchChildren(Tree.Node<State> parent){
		ArrayList<String> actions=new ArrayList<String>();
		State currState=parent.getData();
		System.out.println(currState.printRooms());
		//Chunk of choosing logic is in here, if order are wrong then this is the place to look
		if(currState.isActionValid("Up")){
			//create new state to save change, and apply change
			State UpState=new State(currState);
			UpState.setVacuumX(currState.getVacuumX()-1);
			
			//check if state has already been expanded
				//Check if node already exists
				Tree.Node<State> hold=parent.findNode(UpState,new ArrayList<State>());
				if(hold!=null && !hold.getChildren().contains(parent)){
					hold.addEdge(parent);		
				}else{
					//add node with state to graph
					Tree.Node<State> newNode = new Tree.Node<State>(UpState,(parent.getgCost()+1.3),(parent.getfCost()+1.3));
					parent.addEdge(newNode);
				}
				actions.add("Up");
//			expanded.add(UpState);
		}
		if(currState.isActionValid("Left")){
			//create new state to save change, and apply change
			State LeftState=new State(currState);
			LeftState.setVacuumY(currState.getVacuumY()-1);
				//Check if node already exists
				Tree.Node<State> hold=parent.findNode(LeftState,new ArrayList<State>());
				if(hold!=null && !hold.getChildren().contains(parent)){
					hold.addEdge(parent);			
				}else{
					//add node with state to graph
					//add node with state to graph
					Tree.Node<State> newNode = new Tree.Node<State>(LeftState,(parent.getgCost()+1),(parent.getfCost()+1));
					parent.addEdge(newNode);
				}
				actions.add("Left");
//			expanded.add(LeftState);
		}
		if(currState.isActionValid("Suck")){
			
			//create new state to save changes, and change
			State SuckState=new State(currState);
			SuckState.CleanCurrRoom();
				//Check if node already exists
				Tree.Node<State> hold=parent.findNode(SuckState,new ArrayList<State>());
				if(hold!=null && !hold.getChildren().contains(parent)){
					hold.addEdge(parent);		
				}else{
					//add node with state to graph
					Tree.Node<State> newNode = new Tree.Node<State>(SuckState,parent.getgCost(),(parent.getgCost()+SuckState.getNumDirty()));
					parent.addEdge(newNode);
				}
				actions.add("Suck");
//			expanded.add(SuckState);
		}
		if(currState.isActionValid("Right")){
			//create new state to save changes, and change
			State RightState=new State(currState);
			RightState.setVacuumY(currState.getVacuumY()+1);
				//Check if node already exists
				Tree.Node<State> hold=parent.findNode(RightState,new ArrayList<State>());
				if(hold!=null && !hold.getChildren().contains(parent)){
					hold.addEdge(parent);		
				}else{
					//add node with state to graph
					//add node with state to graph
					Tree.Node<State> newNode = new Tree.Node<State>(RightState,(parent.getgCost()+1),(parent.getfCost()+1));
					parent.addEdge(newNode);
				}
				actions.add("Right");
//			expanded.add(RightState);
		}
		if(currState.isActionValid("Down")){
			//create new state to save changes, and change
			State DownState=new State(currState);
			DownState.setVacuumX(currState.getVacuumX()+1);
				//Check if node already exists
				Tree.Node<State> hold=parent.findNode(DownState,new ArrayList<State>());
				if(hold!=null && !hold.getChildren().contains(parent)){
//					System.out.println("Hold was indeed null");
					hold.addEdge(parent);		
				}else{
					//add node with state to graph
					Tree.Node<State> newNode = new Tree.Node<State>(DownState,(parent.getgCost()+1.3),(parent.getfCost()+1.3));
					parent.addEdge(newNode);
				}
				actions.add("Down");
			//expanded.add(DownState);
		}
		return actions;
	}
	
	public static ArrayList<String> APrime(State start) {
		//holds path
		ArrayList<String> path = new ArrayList<String>();
		//open nodes
		ArrayList<Tree.Node<State>> open = new ArrayList<Tree.Node<State>>();
		//closed nodes
		ArrayList<Tree.Node<State>> closed = new ArrayList<Tree.Node<State>>();
		//initialize root node
		Tree.Node<State> root = new Tree.Node<State>(start,0,start.getNumDirty());
		//start recursive Aprime search
		int result = Asearch(root,open,closed);
		//return path
		return path;
	}
	
	public static int Asearch(Tree.Node<State> currNode,ArrayList<Tree.Node<State>> open,ArrayList<Tree.Node<State>> closed) {
		//get children of current node and actions taken to get there
		ArrayList<String> actions=ASearchChildren(currNode);
		//loop through children
		for(Tree.Node<State> hold : currNode.getChildren()) {
			//bools to be used later
			boolean check1=false;
			boolean check2=false;
			//check to see if all the roooms are clean
			if(hold.getData().getNumDirty()==0) {
				return 1;
			}
			//loop thorugh open
			for(Tree.Node<State> temp : open) {
				//if child exists in open, compare f scores and update if necessary
				if(hold.getData().equals(temp.getData())&&hold.getfCost()<temp.getfCost()) { 
					temp.setfCost(hold.getfCost());
					temp.setgCost(hold.getgCost());
					check1=true;
				}
			}
			//if child exists in closed, compare f scores and update if necessary
			for(Tree.Node<State> temp : closed) {
				if(hold.getData().equals(temp.getData())&&hold.getfCost()<temp.getfCost()) { 
					temp.setfCost(hold.getfCost());
					temp.setgCost(hold.getgCost());
					check2=true;
				}
			}
			//if child was not in open or closed add it to open
			if(check1==false&&check2==false) {
				open.add(hold);
			}
		}
		//add current node to closed
		closed.add(currNode);
		//if open is empty return failure
		if(open.size()==0) {
			return 0;
		}
		//pop off next node from open
		Tree.Node<State> nextNode = open.get(open.size()-1);
		open.remove(open.size()-1);
		//recursive call
		if(Asearch(nextNode,open,closed)==0) {
			return 0;
		}
		else
			return 1;
	}
}

