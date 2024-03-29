import java.util.*;

// this is a system design problem
// leetcode 146
public class LRUcache {
    
    private HashMap<Integer, Node> map;
    private Node head = null, tail = null;
    private int capacity = 0;// number of apps that can be stored in recentApps
    // tab without hindering its state
    private int linkedListSize = 0;

    
    private void addFirst(Node node) {
        if (this.head == null)
            this.head = this.tail = node;
        else {
            this.head.next = node;
            node.prev = this.head;
            this.head = node;
        }
        this.linkedListSize++;
    }

    
    private void removeNode(Node node) {
        if (this.linkedListSize == 1)
            this.head = this.tail = null;
        else if (this.head == node) {
            Node prevNode = node.prev;
            prevNode.next = node.prev = null;
            this.head = prevNode;
        } else if (this.tail == node) {
            Node nextNode = node.next;
            nextNode.prev = node.next = null;
            this.tail = nextNode;
        } else {
            Node prevNode = node.prev;
            Node nextNode = node.next;

            prevNode.next = nextNode;
            nextNode.prev = prevNode;

            node.prev = node.next = null;
        }

        this.linkedListSize--;
    }
    
    private class Node {
        int key = 0;
        int value = 0; // state/value(what's running in that app like netflix naruto s1e2 19:00:000)

        Node prev = null, next = null;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }


    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
    }


    private void makeRecentApp(Node node) {
        if (node == this.head)
            return;// already a recent app

        // remove that node add then addFirst it to make it head(recent)

        removeNode(node);
        addFirst(node);
    }

    private Node fetchNode(int key) {
        Node node = map.get(key);
        makeRecentApp(node);
        return node;
    }

    // make it recent app and return its state
    public int get(int key) {
        // search in recent app
        if (!map.containsKey(key))
            return -1;
        // if present then make it recent app and return its state(what's running in
        // that app like netflix naruto s1e2 19:00:000)
        return fetchNode(key).value;
    }

    // key : appName, value : stateOfApp
    public void put(int key, int value) {
        // present already -> update state && make it recent app otherwise make new app
        // for recent apps
        if (map.containsKey(key)) {
            Node node = fetchNode(key);
            node.value = value;
        } else {
            Node node = new Node(key, value);
            addFirst(node);
            map.put(key, node);
            if (map.size() > this.capacity) {
                Node tail = this.tail;
                removeNode(tail);
                map.remove(tail.key);
            }
        }

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */