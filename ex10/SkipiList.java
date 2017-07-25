/**
 * This class represents a special kind of a doubly-linked list of Strings, called a 
 * SkipiList. A SkipiList is composed of Nodes (a nested class of SkipiList). In addition 
 * to the data (String), each Node has one pointer to the next Node in the list, and 
 * another pointer to the prev-prev Node in the list (hence the name ''skipi''). The 
 * only data members the class contains are the head and the tail of the list.
 */
public class SkipiList {
	
	/**
	 * This class represents the node of the SkipiList. The Node contains the data (of 
	 * type String), the next element and the previous-previous element (both of type 
	 * Node).
	 */
	public static class Node {
		private String data;
		private Node next;
		private Node skipBack;

		/**
		 * Constructor for Node.
		 * @param data the data the Node holds
		 * @param next the next Node in the list
		 * @param skipBack the skipBack (prev-prev) Node in the list
		 * @throws java.lang.NullPointerException - if data is null
		 */
		public Node(java.lang.String data, SkipiList.Node next, SkipiList.Node skipBack) {
			if (data == null) throw new NullPointerException("data");
			this.data = data;
			this.next = next;
			this.skipBack = skipBack;
		}

        /**
         * Returns the data of this Node.
         * @return The data
         */
        public Node getNext() {
            return next;
        }
        /**
         * Returns Returns the next Node.
         * @return The next Node
         */
        public String getData() {
            return data;
        }
        /**
         * Set the Node that will follow this Node to the one given in the parameter.
         * @param next the Node that will now follow this Node
         */
        public void setNext(SkipiList.Node next) {
            this.next = next;
        }
        /**
         * Returns the prev-prev (skipBack) Node.
         * @return The prev-prev (skipBack) Node
         */
        public SkipiList.Node getSkipBack() {
        	return skipBack;
        }
        /**
         * Sets the Node that will be the prev-prev (skipBack) Node of this Node.
         * @param s the prev-prev (skipBack) Node
         */
        public void setSkipBack(SkipiList.Node s) {
        	this.skipBack = s;
        }
	}
	/**
	 * Link to the head.
	 */
    protected Node head;
	/**
	 * Link to the tail.
	 */    
    protected Node tail;
    
    /**
     * Default constructor. Constructs an empty SkipiList.
     */
    public SkipiList(){
        head = null;
        tail = null;
    }
    
    /**
     * Adds an item to the beginning of a list.
     * @param data the item to add
     * @throws java.lang.NullPointerException - if data is null
     */
    public void addFirst(java.lang.String data) {
    	if(data == null) throw new NullPointerException("data");
    	if(head == null) {
    		head = new Node(data, null, null);
    		tail = head;
    		return;
    	}
    	if(head.next != null) {
    		SkipiList.Node node = new Node(data, head, null);
    		head.getNext().setSkipBack(node);
    		head = node;
    		return;
    	}
    	head = new Node(data, head, null);
        
    }
    /**
     * Removes the first Node from the list and return its data.
     * @return The data of the removed node
     */
    public java.lang.String removeFirst() {
        if (head == null){
            return null;
        }
    	String temp = head.getData();
    	SkipiList.Node tmp = head;
    	arrangeList(tmp);
    	tmp = null;
    	return temp;
    }
    /**
     * Adds an item to the end of a list.
     * @param data the item to add
     * @throws java.lang.NullPointerException - if data is null
     */
    public void addLast(java.lang.String data) {
    	if(data == null) throw new NullPointerException("data");
    	if(head == null) {
        	tail = new Node(data, null, null); 
        	head = tail;
        	return;
    	}
    	if(head != null && head.next == null) {
    		SkipiList.Node node = new Node(data, null, null);
    		head.setNext(node);
    		tail = node;
    		return;
    	}
    	if(head.next != null && head.next.next == null) {
    		SkipiList.Node node = new Node(data, null, head);
    		head.next.setNext(node);
    		tail = node;
    		return;
    	}
    	SkipiList.Node node = new Node(data, null, tail.skipBack.next);
    	tail.setNext(node);
    	tail = node;
    }
    /**
     * Removes the last Node from the list and return its data.
     * @return The data of the removed node
     */
    public java.lang.String removeLast() {
        if (tail == null){
            return null;
        }
    	String temp = tail.getData();
    	SkipiList.Node tmp = tail;
    	arrangeList(tmp);
    	tmp = null;
    	return temp;
    }
    /**
     * Returns a string representation of the list. The representation should be the list 
     * elements between "[]", separated by commas. For example, if the list contains the 
     * elements "Dan", "Ran", "Ann", and "Zan", the String representation would be 
     * "[Dan,Ran,Ann,Zan]".
     * @return A string representation of the list
     */
    public java.lang.String toString(){
        StringBuilder ans = new StringBuilder("[");
        Node tmp = head;
        while (tmp != null){
            ans.append(tmp.data);
            tmp = tmp.next;
            if (tmp != null)
                ans.append(",");
        }
        ans.append("]");
        return ans.toString();
    }
    
    /**
     * Returns a string representation of the list, from the end to the beginning. The 
     * representation should be the list element between "[]", separated by commas, in 
     * a reversed order. For example, if the list contains the elements "Dan", "Ran", 
     * "Ann", and "Zan", the String representation would be "[Zan,Ann,Ran,Dan]". You are 
     * not allowed to use the toString() method in implementing this method
     * @return A reverse string representation of the list
     */
    public java.lang.String backString() {
        StringBuilder ans = new StringBuilder("[");
        Node tmp = tail;
        while (tmp != null){
            ans.append(tmp.data);
        	if (tmp == head) {
        		break;
        	}
            if (tmp.skipBack == null) {
            	tmp = head;
            }
            else {
            	tmp = tmp.skipBack.next;
            }
        	ans.append(",");
        }
        ans.append("]");
        return ans.toString();   	
    }
    
    /**
     * Removes from the list the first Node whose data equals the given parameter.
     * @param data
     * @return true iff the data was found and removed
     */
    public boolean removeElement(java.lang.String data) {
    	Node tmp = head;
    	if (data == null) {
    		return false;
    	}
    	while (tmp != null) {
    		if (tmp.data.equals(data)) {
    			arrangeList(tmp);
    			tmp = null; 
    			return true;
    		}
    		tmp = tmp.next;
    	}
    	return false;
    }
    
    //a method that receives the node that needs to be removed from the list and arranges 
    //the list's pointers before removing it.
    private void arrangeList(SkipiList.Node tmp) {
    	//ifs are all possible base cases.
    	//head and tail are being updated aswell.
    	if (tmp.next == null) {
    		if (this.head == tail) {
    			head = null;
    			tail = null;
    			return;
    		}
    		if (tmp.skipBack == null) {
    			head.setNext(null);
    			tail = head;
    			return;
    		}
    		this.tail = tmp.skipBack.next;
    		tmp.skipBack.next.setNext(tmp.next);
    		return;
    	}
    	if (tmp.next.next == null) {
    		if (tail.skipBack == null) {
    			head = tail;
    			return;
    		}
    		if (tmp.skipBack == null) {
    			head.setNext(tail);
    			tail.setSkipBack(null);
    			return;
    		}
    		tail.skipBack.setNext(tail);
    		tail.setSkipBack(tmp.skipBack);
    		return;
    	}
    	if (tmp.next.skipBack == null) {
    		head = tmp.next;
    		tmp.next.next.setSkipBack(null);
    		return;
    	}
    	if (tmp.skipBack == null) {
    		head.setNext(tmp.next);
    		tmp.next.next.setSkipBack(head);
    		tmp.next.setSkipBack(null);
    		return;
    	}
		tmp.skipBack.next.setNext(tmp.next);
		tmp.next.next.setSkipBack(tmp.skipBack.next);
		tmp.next.setSkipBack(tmp.skipBack);
		return;
	}	
  
    /**
     * Removes a given Node from the list, and returns its data. Assumes the given node 
     * is in the list. Runs in O(1).
     * @param n the Node to be removed
     * @return the removed Node's data
     */
    public java.lang.String removeElement(SkipiList.Node n) {
    	Node tmp = head;
    	while (tmp != null) {
    		if (tmp == n) {
    			String temp = tmp.data;
    			arrangeList(tmp);  
    			tmp = null;
    			return temp;
    		}
    		tmp = tmp.next;
    	}
    	return null;
    }
}