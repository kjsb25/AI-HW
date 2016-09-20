import java.util.ArrayList;
import java.util.Random;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.layout.Layouts;

public class HW2 {
	
	
	public static void main(String[] args) {
		
		ArrayList<DirtyIndex> dirty=new ArrayList<DirtyIndex>();
		dirty.add(new DirtyIndex(1,2));
		dirty.add(new DirtyIndex(1,4));
		dirty.add(new DirtyIndex(2,2));
		dirty.add(new DirtyIndex(2,3));
		dirty.add(new DirtyIndex(3,1));
		dirty.add(new DirtyIndex(4,2));
		dirty.add(new DirtyIndex(4,4));
		
		State instance1=new State(4,4,dirty,3,2, "");

		ArrayList<String> path=IDS(instance1);
		


		
		
	}
	
	//could be pointless. Was created before I realized the graph is generated during the search. Derp.
	public static ArrayList<String> IDS(State instance){
		int maxDepth=0;
		ArrayList<String> path=new ArrayList<String>();
		while(maxDepth<5){
			Graph graph = new SingleGraph("Instance 1");
			//TODO Remove before sending, only for visualization
			graph.addAttribute("ui.stylesheet", "url('file:///C:/Users/Koonan/OneDrive/GIT/AI-HW/AI-HW2/lib/style.css')");
			graph.addNode("1");
			Node root=graph.getNode("1");
			root.addAttribute("state", instance);
			root.addAttribute("ui.label", ("Root "+instance.getVacuumX()+","+instance.getVacuumY()));
			root.addAttribute("ui.class", "root");
			path=depthLimitedSearch(root,path,0,maxDepth);
			
			//TODO add logic to check if valid
			
			maxDepth++;
			if(maxDepth==5){
				Viewer viewer=graph.display(false);
				HierarchicalLayout h1=new HierarchicalLayout();
			}
		}
		return path;
		
		
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
		int pathLength=path.size();
		for(Node hold: getChildrenNodes(currNode)){
			path=depthLimitedSearch(hold, path, currDepth+1, depthLimit);
			if(pathLength!=path.size()){
				return path;
			}
		}
		
		
		
		return path;
	}
	
	public static ArrayList<Node> getChildrenNodes(Node parent){
		ArrayList<Node> out=new ArrayList<Node>();
		State currState=(State)parent.getAttribute("state");
		Random randgen=new Random();
		//Chunk of choosing logic is in here, if order are wrong then this is the place to look
		//Also, hurray for this terrible block of code that should be split up and put elsewhere
		if(currState.isActionValid("Up")){
			State UpState=new State(currState);
			UpState.setVacuumX(currState.getVacuumX()-1);
			//may be id overlap here.
			Node UpNode=parent.getGraph().addNode(UpState.toString()+randgen.nextInt());
			UpNode.addAttribute("state", UpState);
			UpNode.addAttribute("ui.label", ("UP "+UpState.getVacuumX()+","+UpState.getVacuumY()));
			parent.getGraph().addEdge(parent.getId()+"->"+UpNode.getId(), parent, UpNode);
			out.add(UpNode);
		}
		if(currState.isActionValid("Left")){
			State LeftState=new State(currState);
			LeftState.setVacuumY(currState.getVacuumY()-1);
			//may be id overlap here.
			Node LeftNode=parent.getGraph().addNode(LeftState.toString()+randgen.nextInt());
			LeftNode.addAttribute("state", LeftState);
			LeftNode.addAttribute("ui.label", ("Left "+LeftState.getVacuumX()+","+LeftState.getVacuumY()));
			parent.getGraph().addEdge(parent.getId()+"->"+LeftNode.getId(), parent, LeftNode);
			out.add(LeftNode);
		}
		if(currState.isActionValid("Suck")){
			State SuckState=new State(currState);
			SuckState.CleanCurrRoom();
			//may be id overlap here.
			Node SuckNode=parent.getGraph().addNode(SuckState.toString()+randgen.nextInt());
			SuckNode.addAttribute("state", SuckState);
			SuckNode.addAttribute("ui.label", ("Suck "+SuckState.getVacuumX()+","+SuckState.getVacuumY()));
			parent.getGraph().addEdge(parent.getId()+"->"+SuckNode.getId(), parent, SuckNode);
			out.add(SuckNode);
		}
		if(currState.isActionValid("Right")){
			State RightState=new State(currState);
			RightState.setVacuumY(currState.getVacuumY()+1);
			//may be id overlap here.
			Node RightNode=parent.getGraph().addNode(RightState.toString()+randgen.nextInt());
			RightNode.addAttribute("state", RightState);
			RightNode.addAttribute("ui.label", ("Right "+RightState.getVacuumX()+","+RightState.getVacuumY()));
			parent.getGraph().addEdge(parent.getId()+"->"+RightNode.getId(), parent, RightNode);
			out.add(RightNode);
		}
		if(currState.isActionValid("Down")){
			State DownState=new State(currState);
			DownState.setVacuumX(currState.getVacuumX()+1);
			//may be id overlap here.
			Node DownNode=parent.getGraph().addNode(DownState.toString()+randgen.nextInt());
			DownNode.addAttribute("state", DownState);
			DownNode.addAttribute("ui.label", ("Down "+DownState.getVacuumX()+","+DownState.getVacuumY()));
			parent.getGraph().addEdge(parent.getId()+"->"+DownNode.getId(), parent, DownNode);
			out.add(DownNode);
		}
		return out;
	}
}

