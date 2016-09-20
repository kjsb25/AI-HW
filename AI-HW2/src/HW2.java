import java.util.ArrayList;
import java.util.Random;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;

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
		if(!path1.isEmpty()){
			System.out.println("Path:");
			for(String s: path1){
				System.out.println(s);
			}
		}else{
			System.out.println("No Path Found");
		}
		System.out.println("IDS Instance 1 time: "+((endTimeIDS1-startTimeIDS1)/1000000)+" ms");
		
		
		long startTimeIDS2=System.nanoTime();
		ArrayList<String> path2=IDS(instance2);
		long endTimeIDS2=System.nanoTime();
		if(!path2.isEmpty()){
			System.out.println("Path:");
			for(String s: path2){
				System.out.println(s);
			}
		}else{
			System.out.println("No Path Found");
		}
		System.out.println("IDS Instance 2 time: "+((endTimeIDS2-startTimeIDS2)/1000000)+" ms");
		
		
	}
	
	public static ArrayList<String> IDS(State instance){
		int maxDepth=0;
		int programLimit=20;
		
		ArrayList<String> path=new ArrayList<String>();
		boolean done=false;
		while(maxDepth<programLimit && !done){
			ArrayList<String> expanded=new ArrayList<String>();
			Graph graph = new SingleGraph("Instance 1");
			//TODO Remove before sending, only for visualization
			graph.addAttribute("ui.stylesheet", "url('file:///C:/Users/Koonan/OneDrive/GIT/AI-HW/AI-HW2/lib/style.css')");
			graph.addNode("1");
			Node root=graph.getNode("1");
			root.addAttribute("state", instance);
			// root.addAttribute("ui.label", ("Root "+instance.getVacuumX()+","+instance.getVacuumY()));
			root.addAttribute("ui.class", "root");
			path=depthLimitedSearch(root,path,expanded,0,maxDepth);
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
		return path;
		
		
	}
	
	public static ArrayList<String> depthLimitedSearch(Node currNode,ArrayList<String> path,ArrayList<String> expanded,int currDepth,int depthLimit){
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
		int pathLength=path.size();
		for(Node hold: getChildrenNodes(currNode,expanded)){
			path=depthLimitedSearch(hold, path, expanded,currDepth+1, depthLimit);
			if(pathLength!=path.size()){
				return path;
			}
		}
		return path;
	}
	
	public static ArrayList<Node> getChildrenNodes(Node parent,ArrayList<String> expanded){
		ArrayList<Node> out=new ArrayList<Node>();
		State currState=(State)parent.getAttribute("state");
		Random randgen=new Random();
		//Chunk of choosing logic is in here, if order are wrong then this is the place to look
		//Also, hurray for this terrible block of code that should be split up and put elsewhere
		if(currState.isActionValid("Up")){
			//create new state to save change, and apply change
			State UpState=new State(currState);
			UpState.setVacuumX(currState.getVacuumX()-1);
			
			//add node with state to graph
			Node UpNode=parent.getGraph().addNode(UpState.toString()+randgen.nextInt());
			UpNode.addAttribute("state", UpState);
			// UpNode.addAttribute("ui.label", ("UP "+UpState.getVacuumX()+","+UpState.getVacuumY()));
			// parent.getGraph().addEdge(parent.getId()+"->"+UpNode.getId(), parent, UpNode);
			
			//add node to output
			out.add(UpNode);
			
			expanded.add("UP "+UpState.getVacuumX()+","+UpState.getVacuumY());
		}
		if(currState.isActionValid("Left")){
			//create new state to save change, and apply change
			State LeftState=new State(currState);
			LeftState.setVacuumY(currState.getVacuumY()-1);
			
			//add node with state to graph
			Node LeftNode=parent.getGraph().addNode(LeftState.toString()+randgen.nextInt());
			LeftNode.addAttribute("state", LeftState);
			// LeftNode.addAttribute("ui.label", ("Left "+LeftState.getVacuumX()+","+LeftState.getVacuumY()));
			// parent.getGraph().addEdge(parent.getId()+"->"+LeftNode.getId(), parent, LeftNode);
			
			//add node to output
			out.add(LeftNode);
			
			expanded.add("Left "+LeftState.getVacuumX()+","+LeftState.getVacuumY());
		}
		if(currState.isActionValid("Suck")){
			State SuckState=new State(currState);
			SuckState.CleanCurrRoom();
			Node SuckNode=parent.getGraph().addNode(SuckState.toString()+randgen.nextInt());
			SuckNode.addAttribute("state", SuckState);
			// SuckNode.addAttribute("ui.label", ("Suck "+SuckState.getVacuumX()+","+SuckState.getVacuumY()));
			// parent.getGraph().addEdge(parent.getId()+"->"+SuckNode.getId(), parent, SuckNode);
			out.add(SuckNode);
			
			expanded.add("Suck "+SuckState.getVacuumX()+","+SuckState.getVacuumY());
		}
		if(currState.isActionValid("Right")){
			State RightState=new State(currState);
			RightState.setVacuumY(currState.getVacuumY()+1);
			Node RightNode=parent.getGraph().addNode(RightState.toString()+randgen.nextInt());
			RightNode.addAttribute("state", RightState);
			// RightNode.addAttribute("ui.label", ("Right "+RightState.getVacuumX()+","+RightState.getVacuumY()));
			// parent.getGraph().addEdge(parent.getId()+"->"+RightNode.getId(), parent, RightNode);
			out.add(RightNode);
			
			expanded.add("Right "+RightState.getVacuumX()+","+RightState.getVacuumY());
		}
		if(currState.isActionValid("Down")){
			State DownState=new State(currState);
			DownState.setVacuumX(currState.getVacuumX()+1);
			Node DownNode=parent.getGraph().addNode(DownState.toString()+randgen.nextInt());
			DownNode.addAttribute("state", DownState);
			// DownNode.addAttribute("ui.label", ("Down "+DownState.getVacuumX()+","+DownState.getVacuumY()));
			// parent.getGraph().addEdge(parent.getId()+"->"+DownNode.getId(), parent, DownNode);
			out.add(DownNode);
			
			expanded.add("Down "+DownState.getVacuumX()+","+DownState.getVacuumY());
		}
		return out;
	}
}

