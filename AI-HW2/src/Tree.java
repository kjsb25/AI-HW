
import java.util.ArrayList;
import java.util.List;

//TODO figure ALLL this shit out

public class Tree<T> {
	
	private Node<T> root;

	 public Tree(T rootData) {
	        root = new Node<T>(rootData);
	        root.children = new ArrayList<Node<T>>();
	}

    public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}
	
	

	public static class Node<T> {
        private T data;
        private Node<T> parent;
        private List<Node<T>> children;

        public Node(T data) {
            this.data = data;
            this.children = new ArrayList<Node<T>>();
        }

        public Node(T data, Node<T> parent) {
            this.data = data;
            this.parent = parent;
            this.children = new ArrayList<Node<T>>();
        }
        
        public List<Node<T>> getChildren() {
            return children;
        }
        
        
        public void setParent(Node<T> parent) {
            parent.addChild(this);
            this.parent = parent;
        }
        
        public void addChild(T data) {
        	
            Node<T> child = new Node<T>(data,this);
            this.addChild(child);
        }
        
        public void addEdge(T data){
        	boolean alreadyConnected=false;
        	for(Node<T> connected: this.children){
        		//TODO Figure this shit out
        		if((State)(connected.data)==data){
        			alreadyConnected=true;
        		}
        	}
        	if(!alreadyConnected){
        		Node<T> newNode=new Node<T>(data);
        		this.children.add(newNode);
        		newNode.children.add(this);
        	}
        }
        
        public void addEdge(Node<T> node){
        	boolean alreadyConnected=false;
        	for(Node<T> connected: this.children){
        		if(connected.data==node.data){
        			alreadyConnected=true;
        		}
        	}
        	if(!alreadyConnected){
        		this.children.add(node);
        		node.children.add(this);
        	}
        }

        public void addChild(Node<T> child) {
            child.parent=(this);
            this.children.add(child);
        }

        public T getData() {
            return this.data;
        }

        public void setData(T data) {
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
        
        public Node<T> findNode(T data, ArrayList<T> visited){
    		if(data.equals(this.data)){
    			return this;
    		}
    		visited.add(this.data);
    		for(Node<T> child: this.getChildren()){
    			if(!visited.contains(child.data)){
    				visited.add(child.data);
	    			Node<T> hold=child.findNode(data,visited);
	    			if(hold!=null){
	    				System.out.println("Found same node!");
	    				return hold;
	    			}
    			}
    		}
    		return null;
    	}
        

        public void removeParent() {
            this.parent = null;
        }
    }
	
}
