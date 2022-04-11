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

    public static int leftShift(int num, int bitsToBeShifted) {
        return num << bitsToBeShifted;
    }

    public static int rightShift(int num, int bitsToBeShifted) {
        return num >> bitsToBeShifted;
    }

    public static void rightbitSetMask(int n) {
        int ans = n & ((~n) + 1);
        // int ans = n & (-n);
        // n & ((~n) + 1) === n & (-n);
        System.out.println(Integer.toBinaryString(ans));
    }

    public static int setTrue(int num, int nthOfBit) {
        int mask = 1 << nthOfBit;
        return num | mask;
    }

    public static int setFalse(int num, int nthOfBit) {
        int mask = ~(1 << nthOfBit);
        return num & mask;
    }

    public static void oddOrEven(int num) { // we'll just need to check for the LSB
        if ((num & 1) == 0)
            System.out.println("Even");
        else
            System.out.println("Odd");
    }

    public static int multiplyBy2(int num, int power) {
        return (num << power); // 2*num == num*(2^power)
    }

    public static int divideBy2(int num, int power) {
        return (num >> power); // lsb will be dropped == pow
    }

    // LC 231
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    // LC 342
    public boolean isPowerOfFour(int n) {
        if (!isPowerOfTwo(n))
            return false; // if not powerOf2 then it cannot be powerOf4

        // powerOf4 = All even powerOf2

        int count = 0; // will count if right shifted number is even or not

        while (n != 0) {
            if ((n & 1) == 0)
                count++;
            n >>>= 1; // this will add 0 everytime at MSB
        }
        return (count & 1) == 0 ? true : false; // checking if count is even or not
    }

    // LC136
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int ele : nums)
            ans ^= ele;
        return ans;
    }
    // https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.researchgate.net%2Ffigure%2FTruth-table-for-the-XOR-operation-of-a-bit_tbl1_307733859&psig=AOvVaw2RMb2ri2AiAuSrpgVI9zei&ust=1649747359112000&source=images&cd=vfe&ved=0CAoQjRxqFwoTCJDZ0oS6i_cCFQAAAAAdAAAAABAD
    /*
     * ans = 0 num = [2,2,1]
     * ele : 2 ans = 0 -> ans^ele = 2
     * ele : 2 ans = 2-> ans^ele = 0
     * ele : 1ans = 0 -> ans^ele = 1
     */

    // LC268
    public int missingNumber(int[] nums) {
        int n = nums.length, i = 0, ans = n;

        while (i < n) {
            ans ^= nums[i] ^ (i++);
        }

        return ans;
    }
    /*
     * https://www.researchgate.net/publication/307733859/figure/tbl1/AS:
     * 670704135598102@1536919735979/Truth-table-for-the-XOR-operation-of-a-bit.png
     * we just need to xor it with every number in nums(i.e nums[idx]) and every
     * possible number that should be present in nums (i.e idx)
     */

    // LC191
    // https://leetcode.com/problems/number-of-1-bits/discuss/1935570/java-multiple-approaches
    public int hammingWeight(int n) {
        // using hamming distance method
        int count = 0;
        while (n != 0) {
            count++;
            n = (n & (n - 1));
        }
        return count;
    }

    public static void main(String[] args) {
        // System.out.println(leftShift(5, 2));
        // System.out.println(rightShift(40, 2));
        // oddOrEven(8);
        System.out.println(divideBy2(40, 3));
    }
}