
public class Client {

    public static void main(String[] args) throws Exception {
        dynamicStack dst = new dynamicStack();
        for (int i = 0; i < 40; i++) {
        dst.push(i + 10);
        }

        while (dst.size() != 0) {
        System.out.print(dst.pop() + " ");
        }
    }
}