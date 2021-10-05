import java.util.*;

public class Questions {
    // everything is same as binary tree except questions
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
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

    public static int size(Node node) {
        if (node == null)
            return 0;
        int tsize = 0;
        tsize += size(node.left);
        tsize += size(node.right);
        return tsize + 1;
    }

    public static int sum(Node node) {
        if (node == null)
            return 0;
        int tsum = 0;
        tsum += sum(node.left);
        tsum += sum(node.right);
        return tsum + node.data;
    }

    // time complexity -> o(height of the tree)
    public static int max(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;
        int myMax = Integer.MIN_VALUE;
        // no need to traverse in left side as all the values in left is less than
        // node.data && node.right
        int rmax = max(node.right);
        if (rmax > myMax)
            myMax = rmax;

        return myMax > node.data ? myMax : node.data;
    }

    // time complexity -> o(height of the tree)
    public static int min(Node node) {
        if (node == null)
            return Integer.MAX_VALUE;
        int myMin = Integer.MAX_VALUE;
        // no need to traverse in right side as all the values in right is more than
        // node.data && node.left
        int lmin = min(node.left);

        if (lmin <= myMin)
            myMin = lmin;

        return myMin < node.data ? myMin : node.data;
    }

    public static boolean find(Node node, int data) {
        if (node == null)
            return false;

        if (node.data == data)
            return true;
        else if (data < node.data)
            return find(node.left, data);
        else
            return find(node.right, data);
    }

    public static Node add(Node node, int data) {
        if (node == null) {
            Node nnode = new Node(data, null, null);
            return nnode;
        }

        if (data < node.data) {
            node.left = add(node.left, data);
        } else if(data > node.data) {
            node.right = add(node.right, data);
        }
        return node;

    }

    public static Node remove(Node node, int data) {
        if (node == null)
            return null;

        if (node.data == data) {
            // check for leaf node, single child node or both child node
            if (node.left == null && node.right == null) {// leaf node
                return null;
            } else if (node.left != null && node.right == null) {
                // single child i.e left
                return node.left;

            } else if (node.left == null && node.right != null) {
                // single child i.e right
                return node.right;
            } else {
                // both children present
                Node lmax = max(node.left, data);
                node = lmax;
                remove(node.left, lmax);
                return node;

            }
        } else if (node.data < data) {
            node.right = remove(node.right, data);
        } else {
            node.left = remove(node.left, data);
        }
        return node;
    }

    // replace with sum of larger
    // Reverse In-order Prefix Sum -> reverse inorder traversal(sum should be maintained)
    static int sum = 0;
    public static void rwsol(Node node) {
        if (node == null)
            return;

        rwsol(node.right);

        sum += node.data;
        node.data = sum - node.data;

        rwsol(node.left);

    }

}