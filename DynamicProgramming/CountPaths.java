import java.io.*;
import java.util.*;

public class CountPaths {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(countPathsm(n, new int[n+1]));
        System.out.println(countPathst(n));
    }

    public static int countPathsm(int n, int[] qb){
        if (n == 0) return 1;
        if (n < 0) return 0;
        if (qb[n] != 0) return qb[n];

        int nm1 = countPathsm(n-1, qb);
        int nm2 = countPathsm(n-2, qb);
        int nm3 = countPathsm(n-3, qb);
        int cp = nm1 + nm2 + nm3;
        qb[n] = cp;
        
        return cp;
    }

    public static int countPathst(int n){
        int[] dp = new int[n+1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++){
            if (i == 1){
                dp[i] = dp[i-1];
            } else if (i == 2){
                dp[i] = dp[i-1] + dp[i-2];
            } else {
                dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
            }
        }

        return dp[n];
    }

}