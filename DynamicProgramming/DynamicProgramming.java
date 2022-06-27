/*
Steps to go from RECURSION -> MEMOIZATION -> TABULATION

1. Faith
2. Tree Diagram
3. Recursive approch
4. Recursion -> Memoization
5. Observation
6. Tabulation
7. Optimization

*/

public class DynamicProgramming {
    public static void display1d(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static  void display2d(int[][] arr) {
        for (int[] ele : arr) {
            display1d(ele);
        }
    }

    public static int fibo(int n) {
        if (n <= 1)
            return n;

        return fibo(n - 1) + fibo(n - 2);
    }

    public static int fiboMemo(int n, int[] dp) {
        // dp will be of size n because for n = 5 we want ans for 5 also so it will be
        // calculated at dp[5];
        if (n <= 1)
            return dp[n] = n;

        if (dp[n] != 0)
            return dp[n];

        return dp[n] = fiboMemo(n - 1, dp) + fiboMemo(n - 2, dp);
    }

    public static  int fiboTab(int N) {
        int[] dp = new int[N + 1];

        for (int n = 0; n <= N; n++) {
            if (n <= 1){
                dp[n] = n;
                continue;
            }

            dp[n] = fiboMemo(n - 1, dp) + fiboMemo(n - 2, dp);
        }

        display1d(dp);
        return dp[N];
    }

    public static void fibTabOpti(int N){
        int a = 0, b = 1;
        for(int n = 0; n < N; n++){
            int sum = a + b;
            System.out.print(" " +a);
            a = b;
            b = sum;
        }
        System.out.println(" "+a);
    }

    public static void fibQuestions(){
        int n = 10;
        int[] dp = new int[n+1];
        fiboMemo(n, dp);
        display1d(dp);
        System.out.println();
        fiboTab(n);
        System.out.println();
        fibTabOpti(n);
    }



    public static void main(String args[]){
        fibQuestions();
    }
}