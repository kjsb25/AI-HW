import org.w3c.dom.Node;
import java.util.ArrayList;
import java.util.List;

public class Tree<State> {
	
	private Node<State> root;
	private State state;
	
	public Tree(State rootData) {
        root = new Node<State>();
        root.data = rootData;
        root.children = new ArrayList<Node<State>>();
    }

    public static class Node<State> {
        private State data;
        private Node<State> parent;
        private List<Node<State>> children;
    }
	
}
