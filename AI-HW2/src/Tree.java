
import java.util.ArrayList;
import java.util.List;

public class Tree<State> {
	
	private Node<State> root;

	 public Tree(State rootData) {
	        root = new Node<State>(rootData);
	        root.children = new ArrayList<Tree<State>>();
	}

    public static class Node<State> {
        private State data;
        private Tree<State> parent;
        private List<Tree<State>> children;

        /*IMPORTANT: READ IF DEBUGGING
         * ALL FUNCTIONS UNDER THIS POINT ARE CURRENTLY UNTESTED AND WERE QUICK HASHUPS
         */
        public Node(State data) {
            this.data = data;
        }

        public Node(State data, Node<State> parent) {
            this.data = data;
            this.parent = parent;
        }
        
        public List<Node<State>> getChildren() {
            return children;
        }
        
        public void setParent(Node<State> parent) {
            parent.addChild(this);
            this.parent = parent;
        }
        
        public void addChild(State data) {
            Node<T> child = new Node<State>(data);
            child.setParent(this);
            this.children.add(child);
        }

        public void addChild(Node<State> child) {
            child.setParent(this);
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
