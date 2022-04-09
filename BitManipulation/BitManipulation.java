public class BitManipulation {
    
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

    public static int leftShift(int num, int bitsToBeShifted){
        return num << bitsToBeShifted;
    }

    public static int rightShift(int num, int bitsToBeShifted){
        return num >> bitsToBeShifted;
    }

    public static void rightbitSetMask(int n){
        int ans = n & ((~n) + 1);
        // int ans = n & (-n);
        // n & ((~n) + 1) === n & (-n);
        System.out.println(Integer.toBinaryString(ans));
    }

    public static int setTrue(int num, int nthOfBit){
        int mask = 1 << nthOfBit;
        return num | mask;
    }

    public static int setFalse(int num, int nthOfBit){
        int mask = ~(1 << nthOfBit);
        return num & mask;
    }
    public static void main(String[] args) {
        System.out.println(leftShift(5, 2));
        System.out.println(rightShift(40, 2));
    }
}