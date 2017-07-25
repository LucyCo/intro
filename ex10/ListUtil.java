/**
 * This class contains static methods that operate on single-linked lists.
 */
public class ListUtil {
    /**
     * Merges two sorted (in ascending order) lists into one new sorted list in an ascending 
     * order. The resulting new list is created using new nodes (copies of the nodes of the 
     * given lists). Assumes both lists are sorted in ascending order. The original lists 
     * should not be modified.
     * @param first the first list to merge
     * @param second the second list to merge
     * @return the merged list
     * @throws java.lang.NullPointerException - if first or second are null
     */
    public static List mergeLists(List first, List second) {
        if (first == null || second == null) throw new NullPointerException("list is null");
        if (first.head == null) return second;
        if (second.head == null) return first;      
        List.Node node = new List(first).head;
        List.Node node2 = new List(second).head;
        return merge(node, node2);
    }
    //private merge method that receives two lists and merges them without making a copy
    //returns a merged list that has the same nodes as the original lists.
    private static List mergeSorted(List first, List second) {
        List.Node node = first.head;
        List.Node node2 = second.head;
        return merge(node, node2);
    }
    //merges two lists into one starting with the given nodes.
    private static List merge(List.Node first, List.Node second) {
        List.Node node = first;
        List.Node node2 = second;  	
        List.Node startNode;       
        List.Node node3;
        List newList = new List();
        //using comparable - if the first string is smaller or equal to the other then 
        //the result equals or smaller than 0.
        if (node.getData().compareTo(node2.getData()) <= 0){
            startNode=node;
            node=node.getNext();
        }
        else {
            startNode=node2;
            node2=node2.getNext();
        }
        node3=startNode;
        
        while (node != null  &&  node2 != null) {
            if (node.getData().compareTo(node2.getData()) <= 0) {
            	node3.setNext(node);
            	node=node.getNext();
            }
            else {
            	node3.setNext(node2);
            	node2=node2.getNext();
            }
            node3 = node3.getNext();
        }
        if (node == null) {
            node3.setNext(node2);
        }
        if (node2 == null) {
            node3.setNext(node);
        }
        newList.head = startNode;
        return newList;
    }

    /**
     * Checks if the given list contains a cycle. A list contains a cycle if at some 
     * point a Node in the list points to a Node that already appeared in the list. 
     * Note that the cycle does not necessarily contain all the nodes in the list. 
     * The original list should not be modified.
     * @param list the list
     * @return true iff the list contains a cycle
     * @throws java.lang.NullPointerException - if list is null
     */
    public static boolean containsCycle (List list) {
        boolean cyclic = false;
      if (list == null) {
      throw new NullPointerException("list is null");
      }
        List.Node node = list.head;
        List.Node node2 = list.head;
        if (node == null) {
            return cyclic;
        }
        else {
            while (!cyclic) {
                if (node.getNext() == null) {
                    break;
                }
                node = node.getNext().getNext();
                node2 = node2.getNext();
                if (node == null || node2 == null) {
                    break;
                }
                if (node.equals(node2)) {
                    cyclic = true;
                }
            }
        }
        return cyclic;
    }

    /**
     * Reverses the given list (so the head becomes the last element, and every element 
     * points to the element that was previously before it). Runs in O(n). No new object 
     * is created.
     * @param list the list
     * @throws java.lang.NullPointerException - if list is null
     */
    public static void reverse(List list){
    	if (list == null) {
    		throw new NullPointerException("list is null");
    	}
        if (list.head != null) {
            List.Node node = list.head;
            List.Node node2 = node.getNext();
            List.Node node3 = node;

            while (node2 != null) {
                node3 = node2;
                node2 = node2.getNext();
                node3.setNext(node);
                node = node3;
            }
            list.head.setNext(null);
            list.removeFirst();
            list.head = node3;
        }
    }

    /**
     * Checks if the given list is a palindrome. A list is a palindrome if for j=1...n/2 
     * (where n is the number of elements in the list) the element in location j equals 
     * to the element in location n-j. Note that you should compare the data stored in 
     * the nodes and not the node objects themselves. The original list should not be 
     * modified.
     * @param list the list
     * @return true iff the list is a palindrome
     * @throws java.lang.NullPointerException - if list is null
     */
    public static boolean isPalindrome(List list) {
        if (list == null) {
        	throw new NullPointerException("list is null");
        }
        else {
            List list1 = new List(list);
            reverse(list1);
            return isEqual(list, list1);
        }
    }

    //checks whether two lists are equal returns true if they are equal and false if not.
    private static boolean isEqual(List list1, List list2) {
        List.Node node = list1.head;
        List.Node node2 = list2.head;
        boolean isEqual = true;

        while (node != null || node2 != null) {
            if (node.getData() == null || node2.getData() == null) {
                if (node.getData() != null || node2.getData() != null) {
                	return false;
                }
                node = node.getNext();
                node2 = node2.getNext();
                break;
            }
            if (node.getData().compareTo(node2.getData()) != 0) {
                isEqual = false;
                break;              
            }
            node = node.getNext();
            node2 = node2.getNext();
        }
        return isEqual;
    }

    /**
     * Checks if the two given lists intersect. Two lists intersect if at some point they 
     * start to share nodes. Once two lists intersect they become one list from that 
     * point on and can no longer split apart. Assumes that both lists does not contain 
     * cycles. Note that two lists might intersect even if their lengths are not equal. 
     * No new object is created, and neither list is modified.
     * @param first the first list
     * @param second The second list
     * @return true iff the lists intersect
     * @throws java.lang.NullPointerException - if first or second are null
     */
    public static boolean haveIntersection(List first, List second){
        if (first == null || second == null) throw new NullPointerException("list is null");
        if (first.head == null || second.head == null) {
        	return false;
        }
        //the last node in each list
        List.Node node = first.head;
        List.Node node2 = second.head;
        while (node.getNext() != null) {
            node = node.getNext();
        }
        while (node2.getNext() != null) {
            node2 = node2.getNext();       	
        }
        return node.equals(node2);
    }
    
    //receives the first node in the list returns the middle node
    private static List.Node middle(List.Node node) {
        List.Node node2 = node;
        while (node2.getNext() != null && node.getNext() != null) {
        	List.Node node3 = node2.getNext().getNext();
            if (node3 != null) {
                node2 = node3;
                node = node.getNext();
            }
            else {
                break;
            }
        }
        return node;
    }
    
    /**
     * Sorts the given list using the merge-sort algorithm. Resulting list should be 
     * sorted in ascending order. Resulting list should contain the same node objects 
     * it did originally, and should be stable, i.e., nodes with equal data should be in 
     * the same order they were in in the original list. You may create a constant number 
     * of new to help sorting.
     * @param list the list
     * @throws java.lang.NullPointerException - if list is null
     */
    public static void mergeSort (List list) {
        if (list == null) throw new NullPointerException("list is null");
        if (list.head == null || list.head.getNext() == null) {
            return;
        }
        List.Node node = list.head;        
        List.Node firstNode = list.head;
        node = middle(node);
        List.Node middle = node.getNext();
        node.setNext(null);
        
        List list1 = new List();
        list1.head = firstNode;
        List list2 = new List();
        list2.head = middle;

        mergeSort(list1); mergeSort(list2);
        list.head = mergeSorted(list1, list2).head;
    }
}