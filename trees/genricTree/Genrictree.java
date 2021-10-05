import java.util.*;

public class Genrictree {

    public static void main(String[] args) {
        int[] arr = { 10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 100, -1, -1,
                -1 };
        Node root = construct(arr);
        display(root);
    }

    public static class Node {
        int data;
        ArrayList<Node> children;

        public Node(int data) {
            this.data = data;
            children = new ArrayList<>(); // beacuse a tree can have many children
        }
    }

    // root of the generic tree
    public static Node construct(int[] arr) {
        Stack<Node> st = new Stack<>();
        Node root = new Node(arr[0]);
        st.push(root);

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == -1) {
                st.pop();
            } else {
                Node n = new Node(arr[i]);
                st.peek().children.add(n);
                st.push(n);
            }
        }
        return root;
    }

    public static void display(Node node) {

        String str = node.data + " -> ";
        for (Node child : node.children) {
            str += child.data + ", ";
        }
        str += '.';
        System.out.println(str);

        for (Node child : node.children) {
            display(child);
        }

        // for(int i = 0 ; i < node.children.size(); i++){
        // Node child = node.children.get(i);
        // disply(child);
        // }
    }

    public static int size(Node node) {
        int s = 0;

        for (Node child : node.children) {
            s += size(child);
        }
        s += 1;

        return s;
    }

    public static int max(Node node) {
        int m = Integer.MIN_VALUE;

        for (Node child : node.children) {
            int cm = max(child); // current max(faith) children's max will get solved
            m = Math.max(m, cm); // overall max
        }
        m = Math.max(m, node.data); // comparing it with starting node

        return m;
    }

    public static int height(Node node) { // height -> Height of a tree is defined as depth of deepest node
        // depth -> Depth of a node is defined as the number of edges it is away from
        // the root (depth of root is 0)
        int h = -1;

        for (Node child : node.children) {
            int ch = height(child); // faith -> children will return their height from the parent's node
            h = Math.max(h, ch);
        }
        h += 1; // to add root node's contribution

        return h;
    }

    public static void traversals(Node node) {
        System.out.println("Node Pre " + node.data); // pre area

        for (Node child : node.children) {
            System.out.println("Edge Pre " + node.data + "--" + child.data); // Edge pre
            traversals(child);
            System.out.println("Edge Post " + node.data + "--" + child.data); // edge post
        }

        System.out.println("Node Post " + node.data); // post area
    }

    public static void mirrorGenricTree(Node node) {
        // faith -> children's children are already mirrored
        // so we just have to reverse our node's children
        for (Node child : node.children) {
            mirrorGenricTree(child);
        }

        int left = 0, right = node.children.size() - 1;

        // pre-order and post order will not matter in this problem
        while (left < right) {
            Node leftNode = node.children.get(left); // getting left node
            Node rightNode = node.children.get(right); // getting right node

            node.children.set(left, rightNode); // setting left -> right
            node.children.set(right, leftNode); // setting right -> left

            left++;
            right--;
        }
    }

    public static void removeLeaves(Node node) {
        // i/p -> 10 20 50 -1 60 -1 -1 30 70 -1 80 110 -1 120 -1 -1 90 -1 -1 40 100 -1
        // -1 -1
        /*
         * o/p -> 10 -> 20, 30, 40, . 20 -> . 30 -> 80, . 80 -> . 40 -> .
         */
        // we also can't use for each loop because if we remove something it will throw
        // concorrunt removal exception
        // concorrunt removal is not allowed in forEach loop
        for (int i = 0; i < node.children.size(); i++) {
            Node child = node.children.get(i);
            if (child.children.size() == 0) {
                node.children.remove(i);
                i--; // because i+1'th node is now at i after removal
            }
        }

        // we'll do our work in pre-order otherwise in post-order it will treat every
        // child as leaf child after removing their child so it will remove all the
        // nodes and return only root node
        for (Node child : node.children) {
            removeLeaves(child);
        }
    }

    static int maxSubTreeSum = Integer.MIN_VALUE;
    static Node maxSubtreeNode;

    public static int sum1(Node node) {
        // approach1
        int sum = node.data;
        for (Node child : node.children) {
            sum += sum1(child);
        }

        if (sum > maxSubTreeSum) {
            maxSubTreeSum = sum;
            maxSubtreeNode = node;
        }
        return sum;
    }

    public static class Pair {
        int sum;
        int mss = Integer.MIN_VALUE;
        Node mssnode;
    }

    public static Pair sum2(Node node) {
        Pair mp = new Pair();
        mp.sum = node.data;

        for (Node child : node.children) {
            Pair cp = sum2(child); // child's Pair
            mp.sum += cp.sum;

            if (cp.mss > mp.mss) {
                mp.mss = cp.mss;
                mp.mssnode = cp.mssnode;
            }
        }
        if (mp.sum > mp.mss) {
            mp.mss = mp.sum;
            mp.mssnode = node;
        }
        return mp;
    }

    // approach 1 with the use of static variables
    static int htn; // height till now

    public static int dia(Node node) {
        htn = height(node);
        for (Node child : node.children) {
            int chtn = height(child);

        }
        return htn + 2;
    }

    public static class htPair {
        int dia;

        int ht;
    }

    public static htPair diameter2(Node node) { // approach 2 with the help of pair
        int maxh = -1;
        int smax = -1;
        int dia = 0;

        for (Node child : node.children) {
            htPair cp = diameter2(child);

            if (cp.ht > maxh) {
                smax = maxh;
                maxh = cp.ht;
            } else if (cp.ht > smax) {
                smax = cp.ht;
            }

            dia = Math.max(dia, cp.dia);
        }

        htPair mp = new htPair();
        mp.ht = maxh + 1;
        mp.dia = Math.max(dia, maxh + smax + 2);
        return mp;
    }

}