
import java.util.ArrayList;
import java.util.List;

public class Tree<State> {
	
	private Node<State> root;

	 public Tree(State rootData) {
	        root = new Node<State>(rootData);
	        root.children = new ArrayList<Node<State>>();
	}

    public Node<State> getRoot() {
		return root;
	}

	public void setRoot(Node<State> root) {
		this.root = root;
	}

	public static class Node<State> {
        private State data;
        private Node<State> parent;
        private List<Node<State>> children;

        /*IMPORTANT: READ IF DEBUGGING
         * ALL FUNCTIONS UNDER THIS POINT ARE CURRENTLY UNTESTED AND WERE QUICK HASHUPS
         */
        public Node(State data) {
            this.data = data;
            this.children = new ArrayList<Node<State>>();
        }

        public Node(State data, Node<State> parent) {
            this.data = data;
            this.parent = parent;
            this.children = new ArrayList<Node<State>>();
        }
        
        public List<Node<State>> getChildren() {
            return children;
        }
        
        public void setParent(Node<State> parent) {
            parent.addChild(this);
            this.parent = parent;
        }
        
        public void addChild(State data) {
        	
            Node<State> child = new Node<State>(data,this);
            this.addChild(child);
        }

        public void addChild(Node<State> child) {
            child.parent=(this);
            this.children.add(child);
        }

        public State getData() {
            return this.data;
        }

        public void setData(State data) {
            this.data = data;
        }

        public boolean isRoot() {
            return (this.parent == null);
        }

        public boolean isLeaf() {
            if(this.children.size() == 0) 
                return true;
            else 
                return false;
        }

        public void removeParent() {
            this.parent = null;
        }
    }
	
}
