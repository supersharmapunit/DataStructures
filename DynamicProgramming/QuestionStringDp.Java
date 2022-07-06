// in most of the string question first think about what will happen if Si == Sj && Si != Sj
// here Si means character at idx i and likewise

public class QuestionStringDp{
    //  lc516 - Longest palindromic Subsequence 
    public int longestPalindromeSubseq(String s) {
        if(s.length() == 1) return 1;
        else {
            int n = s.length();
            int[][] dp = new int[n][n];
            return longestPalindromeSubseq_Memo(s, 0, n-1, dp);
        }
    }
    
    public int longestPalindromeSubseq_Memo(String str, int i, int j, int[][] dp){
        if(i >= j){
            return dp[i][j] = (i == j ? 1 : 0);
        }
        
        if(dp[i][j] != 0) return dp[i][j];
        
        char ch1 = str.charAt(i), ch2 = str.charAt(j);
        
        if(ch1 == ch2){
            return dp[i][j] = longestPalindromeSubseq_Memo(str, i+1, j-1, dp)+2; // if both char are equal so we take both(i.e +2)
            // and make call on rest of the string to give the answer eekske -> memo(eksk)+2e 
        } else {
            return dp[i][j] = Math.max(longestPalindromeSubseq_Memo(str, i+1, j, dp), longestPalindromeSubseq_Memo(str, i, j-1,dp)); // we take max of i+1,j && i, j-1
        }
    }

    public int longestPalindromeSubseq_Tab(String str, int I, int J){
        int n = str.length();
        for(int gap = 0; gap < n; gap++){
            for(int i = 0, j = gap; j < n; i++,j++){
                if(i >= j){
                    dp[i][j] = (i == j ? 1 : 0);
                    continue;
                }
                
                char ch1 = str.charAt(i), ch2 = str.charAt(j);
                
                if(ch1 == ch2){
                    dp[i][j] = dp[i+1][j-1]+2;
                    // return dp[i][j] = longestPalindromeSubseq_Memo(str, i+1, j-1, dp)+2; // if both char are equal so we take both(i.e +2)
                    // and make call on rest of the string to give the answer eekske -> memo(eksk)+2e 
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                    // return dp[i][j] = Math.max(longestPalindromeSubseq_Memo(str, i+1, j, dp), longestPalindromeSubseq_Memo(str, i, j-1,dp)); // we take max of i+1,j && i, j-1
                }
            }
        }
        return dp[I][J];
    }
}