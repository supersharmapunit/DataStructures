public class Client {

    public static void main(String[] args) throws Exception {
        dynamicStack st = new dynamicStack();
        for (int i = 0; i < 30; i++) {
        st.push(i + 10);
        }

        while (st.size() != 0) {
        System.out.print(st.pop() + " ");
        }
    }
}