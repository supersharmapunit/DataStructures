import java.util.*;

public class DPLEET {

    public static void print1D(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void print2D(int[][] arr) {
        for (int[] ar : arr) {
            print1D(ar);
        }
    }

    public static int fib_memo(int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = n;
        }

        if (dp[n] != -1)
            return dp[n];

        int ans = fib_memo(n - 1, dp) + fib_memo(n - 2, dp);
        return dp[n] = ans;
    }

    public static int fib_DP(int N, int[] dp) {

        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = n;
                continue;
            }

            int ans = dp[n - 1] + dp[n - 2];// fib_01(n - 1, dp) + fib_01(n - 2, dp);

            dp[n] = ans;
        }

        return dp[N];
    }

    public static int fib_twoPointer(int N) {
        int a = 0, b = 1;
        for (int n = 0; n < N; n++) {
            // System.out.print(a + " ");

            int sum = a + b;
            a = b;
            b = sum;
        }


        return a;
    }

    public static void fibo() {
        int n = 8;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        System.out.println(fib_memo(n, dp));
        System.out.println(fib_DP(n, dp));
        System.out.println(fib_twoPointer(n));

        print1D(dp);
    }

    public static int mazePathMemo(int sr, int sc, int er, int ec, int[][] dp){
        if (sr == er && sc == ec) return dp[sr][sc] = 1;
        if (dp[sr][sc] != 0) return dp[sr][sc];

        int count = 0;

        if (sr+1 <= er)
            count += mazePathMemo(sr+1, sc, er, ec, dp);
        
        if (sc+1 <= ec)
            count += mazePathMemo(sr, sc+1, er, ec, dp);
        
        if (sr+1 <= er && sc+1 <= ec)
            count += mazePathMemo(sr+1, sc+1, er, ec, dp);
        
        return dp[sr][sc] = count;
    }

    public static int mazePathMemo_infiniteJumps(int sr, int sc, int er, int ec, int[][] dp){
        if (sr == er && sc == ec) return dp[sr][sc] = 1;
        if (dp[sr][sc] != 0) return dp[sr][sc];

        int count = 0;

        for (int jump = 1; sr+jump <= er; jump++)
            count += mazePathMemo_infiniteJumps(sr+jump, sc, er, ec, dp);
        
        for (int jump = 1; sc+jump <= ec; jump++)
            count += mazePathMemo_infiniteJumps(sr, sc+jump, er, ec, dp);
        
        for (int jump = 1; jump + sr <= er && sc+jump <= ec; jump++)
            count += mazePathMemo_infiniteJumps(sr+jump, sc+jump, er, ec, dp);
        
        return dp[sr][sc] = count;
    }

    public static int mazePathTab(int sr, int sc, int er, int ec, int[][] dp){
        for (sr = er; sr >= 0; sr--){
            for (sc = ec; sc >= 0; sc--){
                if (sr == er && sc == ec){
                    dp[sr][sc] = 1;
                    continue;
                }
                
                int count = 0;
        
                if (sr+1 <= er)
                    count += dp[sr+1][sc];
                
                if (sc+1 <= ec)
                    count += dp[sr][sc+1];
                
                if (sr+1 <= er && sc+1 <= ec)
                    count += dp[sr+1][sc+1];
                
                dp[sr][sc] = count;
            }
        }
        return dp[er][ec];
        
    }

    public static int mazePathTab_infiniteJumps(int sr, int sc, int er, int ec, int[][] dp){
        for (sr = er; sr >= 0; sr--){
            for (sc = ec; sc >= 0; sc--){
        if (sr == er && sc == ec){
            dp[sr][sc] = 1;
            continue;
        }
        
        int count = 0;

        for (int jump = 1; sr+jump <= er; jump++)
            count += dp[sr+jump][sc];
        
        for (int jump = 1; sc+jump <= ec; jump++)
            count += dp[sr][sc+jump];
        
        for (int jump = 1; jump + sr <= er && sc+jump <= ec; jump++)
            count += dp[sr+jump][sc+jump];
        
        dp[sr][sc] = count;
            }
        }
        return dp[er][ec];
    }

    // Leetcode 70
    public static int climbStairsMemo(int n, int[] dp){
        if (n <= 1){
            return dp[n] = 1;
        }

        if (dp[n] != -1){
            return dp[n];
        }

        return dp[n] = climbStairsMemo(n-1, dp) + climbStairsMemo(n-2, dp);


    }

    public int climbStairs(int N) {
        int dp[] = new int[N+1];
        
        for (int n = 0; n <= N; n++){
            if (n <= 1){
                dp[n] = 1;
                continue;
            }
            
            dp[n] = dp[n - 1] + dp[n - 2];
        }
        
        return dp[N];
    }

    public static void cS(){
        int n = 3;
        int dp[] = new int[n+1];
        for (int i : dp){
            Arrays.fill(dp, -1);
        }
        System.out.println(climbStairsMemo(n, dp));
    }

    public static void mp(){
        int n = 5;
        int m = 5;
        int dp[][] = new int[n][m];
        // System.out.println(mazePathMemo(0, 0, dp.length-1, dp[0].length-1, dp));
        //System.out.println(mazePathTab(0, 0, dp.length-1, dp[0].length-1, dp));
        //System.out.println(mazePathMemo_infiniteJumps(0, 0, dp.length-1, dp[0].length-1, dp));
        System.out.println(mazePathTab_infiniteJumps(0, 0, dp.length-1, dp[0].length-1, dp));
        print2D(dp);
    }

    // LeetCode 746
    public int minCostClimbingStairs(int[] cost) {
       int n = cost.length;
        int dp[] = new int[n + 1 ];
       Arrays.fill(dp, -1);
       return minCostClimbingStairsMemo(n, cost, dp);
    }

    public static int minCostClimbingStairsMemo(int n, int cost[], int dp[]){
        if (n <= 1) return dp[n] = cost[n];
        if (dp[n] != -1){
            return dp[n];
        }

        int minCost = Math.min(minCostClimbingStairsMemo(n-1,cost,dp),minCostClimbingStairsMemo(n-2, cost, dp));
        return dp[n] = minCost + (n == cost.length ? 0 : cost[n]);
    }

    public static int minCostClimbingStairsTab(int N, int cost[], int dp[]){
        for (int n = 0; n <= N; n++){
            if (n <= 1){
                dp[n] = cost[n];
                continue;
            }

        int minCost = Math.min(dp[n-1],dp[n-2]);
        dp[n] = minCost + (n == cost.length ? 0 : cost[n]);
        }
        return dp[N];
    }

    public static int minCostClimbingStairsOpti(int N, int cost[]){
        int a = cost[0];
        int b = cost[1];
        for (int n = 2; n <= N; n++){
            int minCost = Math.min(a,b) + (n == cost.length ? 0 : cost[n]);
            a = b;
            b = minCost;
        }
        
        return Math.min(a,b);

    }

    public static int boardPathMemo(int sp, int ep, int dp[]){
        if (sp == ep) return dp[sp] = 1;

        if (dp[sp] != 0){
            return dp[sp];
        }

        int count = 0;
        for (int dice = 1; dice <= 6 && sp + dice <= ep; dice++){
            count += boardPathMemo(sp + dice, ep, dp);
        }

        return dp[sp] = count;

    }
 
    public static int boardPathTab(int SP, int ep, int dp[]){
        for (int sp = ep; sp >= SP; sp--){
            if (sp == ep){
                dp[sp] = 1;
                continue;
            }
        int count = 0;
        for (int dice = 1; dice <= 6 && sp + dice <= ep; dice++){
            count += dp[sp + dice];
        }

        dp[sp] = count;
        }
        return dp[SP];

    }

    public static int boardPathOpti(int sp, int ep){
        LinkedList<Integer> list = new LinkedList<>();

        for (sp = ep; sp >= 0; sp--){
            if (sp >= ep - 1){
                list.addFirst(1);
            }
            else {
                if (list.size() <= 6){
                    list.addFirst(list.getFirst() * 2);
                } else {
                    list.addFirst(list.getFirst() * 2 - list.removeLast());
                }
            }
        }
        return list.getFirst();
    }

    public static void bp(){
        int n = 10;
        int[] dp = new int[n + 1]; 

        // boardPathMemo(0, n, dp);
        boardPathTab(0, n, dp);
        System.out.println(boardPathOpti(0, n));
        print1D(dp);
    }

    
    

    public static void main(String[] args) {
        // fibo();
        // cS();
        // mp();
        // bp();
        
    }

}