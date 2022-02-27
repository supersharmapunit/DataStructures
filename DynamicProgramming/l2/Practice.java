public class Practice{
    // Largest Square Sub-matrix With All 1's
    public static int solution(int[][] arr) {
        int n = arr.length, m = arr[0].length, max = 0;
        int[][] dp = new int[n][m];
        
        for(int i = n-1; i >= 0; i--){
            for(int j = m-1; j >= 0; j--){
                if(arr[i][j] == 0) dp[i][j] = 0;
                else if(i == n-1 || j == m-1) dp[i][j] = 1;
                else dp[i][j] = Math.min(Math.min(dp[i+1][j], dp[i+1][j+1]) ,dp[i][j+1]) + 1;
                
                max = Math.max(dp[i][j], max);
            }
            
        }
        return max;
      }
}