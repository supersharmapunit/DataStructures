public class BitManipulation {
    public static void main(String[] args) {

    }

    /*
     * 1. You are given a number n. 2. Print the number produced on setting its i-th
     * bit. 3. Print the number produced on unsetting its j-th bit. 4. Print the
     * number produced on toggling its k-th bit. 5. Also, Check if its m-th bit is
     * on or off. Print 'true' if it is on, otherwise print 'false'.
     * 
     * i/p -> 57 3 3 3 3
     * 
     * o/p -> 57 49 49 true
     */
    public static void basics(int n, int i, int j, int k, int m) {
        int mask1 = (1 << i);
        int mask2 = ~(1 << j);
        int mask3 = (1 << k);
        int mask4 = (1 << m);

        System.out.println(n | mask1);
        System.out.println(n & mask2);
        System.out.println(n ^ mask3);
        System.out.println((n & mask4) == 0 ? "false" : "true");
    }
}