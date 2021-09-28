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
        if (node == null) {
            if (sum >= lo && sum <= hi) {
                System.out.println(path);
            }
            return;
        }

        pathToLeafFromRoot(node.left, path + " " + node.data, sum + node.data, lo, hi);
        pathToLeafFromRoot(node.right, path + " " + node.data, sum + node.data, lo, hi);

    }

    public static void createLeftCloneTree(Node node) {
        if (node == null)
            return;

        createLeftCloneTree(node.left);
        createLeftCloneTree(node.right);

        Node clone = new Node(node.data, node.left, null);

        node.left = clone;
    }

    public static void transBackFromLeftClonedTree(Node node){
        if(node == null) return;

        transBackFromLeftClonedTree(node.left.left);
        transBackFromLeftClonedTree(node.right);

        node.left = node.left.left;
    }
}