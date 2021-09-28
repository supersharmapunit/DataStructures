import java.util.*;

public class Construct {
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

    public static void display(Node node) {
        if (node == null) {
            return;
        }

        String str = "";
        str += node.left == null ? "." : node.left.data + "";
        str += " <- " + node.data + " -> ";
        str += node.right == null ? "." : node.right.data + "";
        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    protected static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = { 10, 20, 40, null, null, 50, 60, null, null, null, 30, 70, null, 80, null, null, 90, null,
                null };
        Node root = construct(arr);
        display(root);
    }

    public static Node construct(Integer[] arr) {
        Node root = new Node(arr[0], null, null);
        Pair rtp = new Pair(root, 1);

        Stack<Pair> st = new Stack<>();
        st.push(rtp);

        int idx = 0;
        while (st.size() > 0) {
            Pair top = st.peek();
            if (top.state == 1) {
                idx++;
                if (arr[idx] != null) {
                    top.node.left = new Node(arr[idx], null, null);
                    Pair lp = new Pair(top.node.left, 1);
                    st.push(lp);
                } else {
                    top.node.left = null;
                }

                top.state++;
            } else if (top.state == 2) {
                idx++;
                if (arr[idx] != null) {
                    top.node.right = new Node(arr[idx], null, null);
                    Pair rp = new Pair(top.node.right, 1);
                    st.push(rp);
                } else {
                    top.node.right = null;
                }

                top.state++;
            } else {
                st.pop();
            }
        }

        return root;
    }

    public static int size(Node node) {
        if (node == null) {
            return 0;
        }
        int tsize = 1;
        int lsize = size(node.left);
        int rsize = size(node.right);
        return tsize += lsize + rsize;
    }

    public static int sum(Node node) {
        if (node == null) {
            return 0;
        }

        int lsum = sum(node.left);
        int rsum = sum(node.right);
        int tsum = rsum + lsum + node.data;
        return tsum;
    }

    public static int max(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;

        int lmax = max(node.left);
        int rmax = max(node.right);
        int fmax = Math.max(node.data, Math.max(rmax, lmax));
        return fmax;
    }

    public static int height(Node node) {
        if (node == null)
            return -1;
        int rht = height(node.right);
        int lht = height(node.left);
        int tht = Math.max(lht, rht);

        return tht + 1;
    }
}