
import java.util.ArrayList;
import java.util.List;

public class Tree<State> {
	
	private Node<State> root;

	 public Tree(State rootData) {
	        root = new Node<State>();
	        root.data = rootData;
	        root.children = new ArrayList<Tree<State>>();
	}

    public static class Node<State> {
        private State data;
        private Tree<State> parent;
        private List<Tree<State>> children;
    }
	
}
