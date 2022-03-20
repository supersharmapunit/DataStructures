package Recursion;

public class RecursionPractice {
    public static void main(String[] args) {
        int n = 5;
        System.out.println(powerLog(n, 1));
    }

    public static void printIncreasing(int a, int b) {
        if (a > b)
            return;

        System.out.println(a);
        printIncreasing(a + 1, b);
    }

    public static void printDecreasing(int a, int b) {
        if (a > b)
            return;

        printDecreasing(a + 1, b);
        System.out.println(a);
    }

    public static void printIncreasingDecreasing(int a, int b) {
        if (a > b)
            return;

        System.out.println(a);
        printIncreasingDecreasing(a + 1, b);
        System.out.println(a);
    }

    public static int fact(int n) {
        return n == 0 ? 1 : fact(n - 1) * n;
    }

    public static int powerLog(int n, int x) {
        if (x == 1)
            return n;
        int ans = powerLog(n, x / 2);
        return x % 2 == 0 ? ans * ans : ans * ans * n;
    }
}
