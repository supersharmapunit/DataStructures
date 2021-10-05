import java.util.*;

public class Questions {
    private static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    protected static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void iterativeTraversal(Node node) {
        Stack<Pair> st = new Stack<>();
        Pair root = new Pair(node, 0);
        st.push(root);

        String pre = "";
        String in = "";
        String post = "";

        while (st.size() != 0) {
            Pair tos = st.peek();

            if (tos.state == 0) { // parent node
                pre += tos.node.data + " ";
                tos.state++;
                if (tos.node.left != null)
                    st.push(new Pair(tos.node.left, 0)); // left child node
            } else if (tos.state == 1) {
                in += tos.node.data + " ";
                tos.state++;
                if (tos.node.right != null)
                    st.push(new Pair(tos.node.right, 0)); // right child node
            } else { // tos.state > 1-> pre, in, post done i.e pop
                post += tos.node.data + " ";

                st.pop();

            }
        }
        System.out.println(pre);
        System.out.println(in);
        System.out.println(post);
    }

    // Node to root path(AL of Integers)
    public static ArrayList<Integer> ntrp(Node node, int data) {
        if (node == null)
            return new ArrayList<>();

        if (node.data == data) {
            ArrayList<Integer> base = new ArrayList<>();
            base.add(node.data);
            return base;
        }

        ArrayList<Integer> lans = ntrp(node.left, data);
        if (lans.size() > 0) {// size > 0 i.e element found
            lans.add(node.data); // add root
            return lans;
        }

        ArrayList<Integer> rans = ntrp(node.right, data);
        if (rans.size() > 0) { // size > 0 i.e element found
            rans.add(node.data); // add root
            return rans;
        }

        return new ArrayList<>(); // if not found anywhere so return empty AL
    }

    // print k levels down
    public static void printKlevel(Node node, int k) {
        if (node == null)
            return;

        if (k == 0) {
            System.out.println(node.data);
            return;
        }

        printKlevel(node.left, k - 1);
        printKlevel(node.right, k - 1);
    }

    // same as printklevel it just include a blocker node parameter
    // just to remove duplicacy we'll not go to the node which we've travelled
    // earlier
    public static void printKdistAwayHelper(Node node, int k, Node blocker) {
        if (node == null || node == blocker)
            return;

        if (k == 0) {
            System.out.println(node.data);
            return;
        }

        printKdistAwayHelper(node.left, k - 1, blocker);
        printKdistAwayHelper(node.right, k - 1, blocker);
    }

    // node to root path AL(nodes)
    public static ArrayList<Node> ntrpNodes(Node node, int data) {
        if (node == null)
            return new ArrayList<>();

        if (node.data == data) {
            ArrayList<Node> base = new ArrayList<>();
            base.add(node);
            return base;
        }

        ArrayList<Node> lans = ntrp(node.left, data);
        if (lans.size() > 0) {// size > 0 i.e element found
            lans.add(node); // add root
            return lans;
        }

        ArrayList<Node> rans = ntrp(node.right, data);
        if (rans.size() > 0) { // size > 0 i.e element found
            rans.add(node); // add root
            return rans;
        }

        return new ArrayList<>(); // if not found anywhere so return empty AL
    }

    public static void printKdistAway(Node node, int data, int k) {
        ArrayList<Node> ntp = ntrpNodes(node, data);
        // eg. [30k,37k,25k,50k]

        for (int i = 0; i < ntp.size(); i++) {
            Node cr = ntp.get(i);
            Node blocker = i == 0 ? null : ntp.get(i - 1);
            // printKdistHelper(ntp.get(i), k-i, i == 0 ? null : ntp.get(i-1));
            printKdistHelper(cr, k - i, blocker);
        }
    }

    // print all paths from root to leaves which have sum of nodes in range from lo
    // to hi (both inclusive).
    // The elements in path should be separated by spaces. Each path should be in a
    // separate line
    public static void pathToLeafFromRoot(Node node, String path, int sum, int lo, int hi) {
        if (node == null)
            return;
        if (node.left == null && node.right == null) { // leaf node check
            sum += node.data;
            if (sum >= lo && sum <= hi) {
                System.out.println(path + node.data);
            }
            return;
        }

        pathToLeafFromRoot(node.left, path + node.data + " ", sum + node.data, lo, hi);
        pathToLeafFromRoot(node.right, path + node.data + " ", sum + node.data, lo, hi);

    }

    public static void createLeftCloneTree(Node node) {
        if (node == null)
            return;

        createLeftCloneTree(node.left);
        createLeftCloneTree(node.right);

        Node clone = new Node(node.data, node.left, null);

        node.left = clone;
    }

    public static void transBackFromLeftClonedTree(Node node) {
        if (node == null)
            return;

        transBackFromLeftClonedTree(node.left.left);
        transBackFromLeftClonedTree(node.right);

        node.left = node.left.left;
    }

    public static void printSingleChildNodes(Node node, Node parent) {
        // to print only that nodes which has only one child

        if (node == null)
            return;
        if (parent != null && parent.left != null && parent.right == null)
            System.out.println(node.data);
        else if (parent != null && parent.right != null && parent.left == null)
            System.out.println(node.data);

        printSingleChildNodes(node.left, node);
        printSingleChildNodes(node.right, node);
    }

    public static Node removeLeaves(Node node) {
        if (node == null)
            return null;
        if (node.left == null && node.right == null)
            return null;

        node.left = removeLeaves(node.left);
        node.right = removeLeaves(node.right);
        return node;
    }

    // The function is expected to set the value of data member "tilt".
    // "tilt" of a node is the absolute value of difference between sum of nodes in
    // it's left subtree and right subtree.
    // "tilt" of the whole tree is represented as the sum of "tilt"s of all it's
    // nodes.
    static int tilt = 0;

    public static int tilt(Node node) {
        if (node == null)
            return 0;

        // getting ans from left and right
        int lans = tilt(node.left);
        int rans = tilt(node.right);

        // updating tilt
        tilt += Math.abs(lans - rans);

        // tilt of the whole tree
        return node.data + lans + rans;
    }

    // same as the diameter of gt -> max ht. + second max ht. + 2
    public static class HDpair { // height,dia Pair
        int ht;
        int dia;
    }

    public static HDpair diameter1(Node node) {
        if (node == null) {
            HDpair base = new HDpair();
            base.ht = -1; // because leaf node has ht. = 0
            // and we're one step ahead of leaf node i.e -1
            base.dia = 0;
            return base;
        }

        HDpair lp = diameter1(node.left);
        HDpair rp = diameter1(node.right);

        HDpair mp = new HDpair();
        mp.ht = Math.max(lp.ht, rp.ht) + 1;
        mp.dia = Math.max(Math.max(lp.dia, rp.dia), lp.ht + rp.ht + 2);
        return mp;
    }

    // is binary tree balanced?
    public static class Bpair {
        boolean isBal;
        int ht;
    }

    public static Bpair isBalanced(Node node) {
        if (node == null) {
            Bpair base = new Bpair();
            base.ht = -1;
            base.isBal = true;
            return base;
        }

        Bpair lp = isBalanced(node.left);
        Bpair rp = isBalanced(node.right);

        Bpair mp = new Bpair();
        mp.ht = Math.max(lp.ht, rp.ht) + 1;
        if (lp.isBal == true && rp.isBal == true && Math.abs(lp.ht - rp.ht) <= 1)
            mp.isBal = true;
        else
            mp.isBal = false;
        return mp;
    }

    // is BST
    public static class BSTpair {
        int min, max;
        boolean bst;
        Node lbstNode; // largest bst Node
        int lbstSize; // Largest bst size
    }

    public static BSTpair isBST(Node node) {
        if (node == null) {
            BSTpair base = new BSTpair();
            base.min = 0;
            base.max = 0;
            base.bst = true;
            return base;
        }

        BSTpair lp = isBST(node.left);
        BSTpair rp = isBST(node.right);

        BSTpair mp = new BSTpair();
        mp.min = Math.max(node.data, Math.min(lp.min, rp.min));
        mp.max = Math.max(node.data, Math.max(lp.min, rp.min));

        if (lp.bst == true && rp.bst == true && lp.max < node.data && node.data < rp.min) {
            mp.bst = true;
        } else {
            mp.bst = false;
        }

        return mp;

    }

    // Largest bst subtree in a binary tree
    public static BSTpair lbstSubtree(Node node) {
        if (node == null) {
            BSTpair base = new BSTpair();
            base.min = Integer.MAX_VALUE;
            base.max = Integer.MIN_VALUE;
            base.bst = true;
            // base.lbstSize = 0;
            // base.lbstNode = null;
            return base;
        }
        BSTpair lp = lbstSubtree(node.left);
        BSTpair rp = lbstSubtree(node.right);

        BSTpair mp = new BSTpair();
        mp.min = Math.min(Math.min(lp.min, rp.min), node.data);
        mp.max = Math.max(Math.max(lp.max, rp.max), node.data);
        if (lp.bst == true && rp.bst == true && lp.max < node.data && node.data < rp.min) {
            mp.bst = true;
            mp.lbstNode = node;
            if (lp.lbstSize >= rp.lbstSize)
                mp.lbstSize = lp.lbstSize + 1;
            else
                mp.lbstSize = rp.lbstSize + 1;
        } else {
            mp.bst = false;
            if (lp.lbstSize >= rp.lbstSize) {
                mp.lbstNode = lp.lbstNode;
                mp.lbstSize = lp.lbstSize;
            } else {
                mp.lbstNode = rp.lbstNode;
                mp.lbstSize = rp.lbstSize;
            }
        }
        return mp;
    }
}