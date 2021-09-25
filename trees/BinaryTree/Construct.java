public class Construct {
    private class Node {
        int data;
        Node right;
        Node left;

        void Node(int data) {
            this.data = data;
        }
    }

    protected class Pair {
        Node node;
        int state;

        void Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = { 10, 20, 40, null, null, 50, 60, null, null, null, 30, 70, null, 80, null, null, 90, null,
                null };
        Node root = construct(arr);
        disply(root);
    }

    public static void construct(Integer[] arr) {
        Stack<Pair> st = new stack<>();
        Node root = new Node(arr[0]);
        st.push(new Pair(root, 0));

        for (int i = 1; i < arr.length; i++) {
            Pair tos = st.peek();

            if (arr[i] == null) {
                tos.state++;
                if (tos.state == 2) {
                    st.pop();
                }
            } else {
                Node n = new Node(arr[i]);

                if (tos.state == 0) {
                    tos.state++;
                    tos.node.left = n;
                } else if (tos.state == 1) {
                    // tos.state++; no need to do this as we need to pop because it'll be equal to 2
                    // instead we'll pop
                    tos.node.right = n;
                    st.pop();
                } else {
                    tos.node.left = null;
                }
                tos.state++;
            }
        }
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
            return Integer.MIN git push -u origin master_VALUE;

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