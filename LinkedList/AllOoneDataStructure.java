import java.util.*;

// Leetcode 432
public class AllOoneDataStructure {
    private class Node {
        Node prev, next;
        int count = 0;
        List<String> list;

        Node(String key, int count) {
            this.count = count;
            list = new ArrayList<>();
            this.list.add(key);
        }
    }

    private HashMap<String, Node> map;
    private Node head = null, tail = null;

    private void addAtHead(Node node) {
        if (this.head == null)
            this.head = this.tail = node;
        else {
            // image list ulti
            // null<->tail<->node<->node<->node<->head->null
            this.head.next = node;
            node.prev = this.head;
            this.head = node;
        }
    }
    
    private void addAtHead(Node node) {
        if (this.head == null)
            this.head = this.tail = node;
        else {
            // image list ulti
            // null<->tail<->node<->node<->node<->head->null
            node.next = this.tail;
            this.tail.prev = node;
            this.tail = node;
        }
    }

    private void addNext(Node currNode, Node node) {
        if (currNode == this.head)
            addAtHead(node);
        else {
            Node nextNode = currNode.next;
            currNode.next = node;
            node.prev = currNode;

            node.next = nextNode;
            nextNode.prev = node;
        }
    }

    public AllOne() {
        this.map = new HashMap<>();
    }

    public void inc(String key) {
        if (map.size() == 0) {
            Node node = new Node(key, 1);
            map.put(key, node);
            this.head = this.tail = node;
        } else if (map.containsKey(key)) {
            Node node = map.get(key);
            Node nextNode = node.next;
            if (nextNode == null) {
                Node nnode = new Node(key, node.count+1)
                addAtHead(nnode);
                map.put(key, node.next);
            } else if (nextNode.count + 1 == node.count + 1) {
                map.put(key, nextNode);
                List<String> list = nextNode.list;
                list.add(key);
            } else {
                Node nnode = new Node(key, node.count+1);
                addNext(node,nnode);
                map.put(key, nnode);
            }

            List<String> list = node.list;
            list.remove(key);
            
        } else {
            // if this will be tail or will get add in tail's list
            if(this.tail.count == 1){
                map.put(key, this.tail);
                List<String> list = node.list;
                list.add(key);
            } else {
                Node nnode = new Node(key, 1);
                addAtTail(nnode);
                map.put(key, nnode);
            }
        }
    }

    public void dec(String key) {

    }

    public String getMinKey() {
        if (this.tail == null)
            return "";
        List<String> list = this.tail.list;
        return list.get(list.size() - 1);
    }

    public String getMaxKey() {
        if (this.head == null)
            return "";
        List<String> list = this.head.list;
        return list.get(0);
    }
}