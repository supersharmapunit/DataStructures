// https://leetcode.com/discuss/general-discussion/458695/dynamic-programming-patterns (DP Patterns)
// https://leetcode.com/discuss/general-discussion/662866/Dynamic-Programming-for-Practice-Problems-Patterns-and-Sample-Solutions (Dp pattern wise questions)
// https://seanprashad.com/leetcode-patterns/

_Memo// in most of the string question first think about what will happen if Si == Sj && Si != Sj
// here Si means character at idx i and likewise

// loop patterns =>
        // i == 0, j == 0 || n == N, m == M -> left to right, top to bottom
        // i == 0, j = (n-1) Gap Strategy Loop
        

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

    // lc1143 Longest Common Subsequence
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();

        int[][] dp = new int[n+1][m+1];
        for(int[] ele : dp) Arrays.fill(ele,-1);

        // return longestCommonSubsequence_Memo(text1,text2, n, m, dp);
        return longestCommonSubsequence_Tab(text1,text2, n, m, dp);
    }

    public int longestCommonSubsequence_Memo(String str1, String str2, int n, int m, int[][] dp){
        if(n == 0 || m == 0){
            return dp[n][m] = 0;
        }

        if(dp[n][m] != -1) return dp[n][m];

        char ch1 = str1.charAt(n-1), ch2 = str2.charAt(m-1);

        if(ch1 == ch2){
            return dp[n][m] = longestCommonSubsequence_Memo(str1, str2, n-1,m-1,dp)+1; 
        } else {
            return dp[n][m] = Math.max(longestCommonSubsequence_Memo(str1, str2, n-1,m,dp), longestCommonSubsequence_Memo(str1, str2, n,m-1, dp));
        }
    }

    public int longestCommonSubsequence_Tab(){
        for(int n = 0; n <= N; n++){
            for(int m = 0; m <= M; m++){
                if(n == 0 || m == 0){
                 dp[n][m] = 0;
                    continue;
                }
                char ch1 = str1.charAt(n-1), ch2 = str2.charAt(m-1);

                if(ch1 == ch2){
                    dp[n][m] = dp[n-1][m-1]+1; 
                } else {
                    dp[n][m] = Math.max(dp[n-1][m], dp[n][m-1]);
                }
            }
        }
        return dp[N][M];
    }

    // 115 Distinct Subsequence (just like lcs but with a twist)
    public int numDistinct(String s, String t) {
        int n = s.length(), m = t.length();
        if(n < m) return 0;
        int[][] dp = new int[n+1][m+1];
        for(int[] ele : dp) Arrays.fill(ele, -1);
        return numDist_Memo(s,t,n,m,dp);
    }
    // eg-> geeksforgeeks|gks (recursive tree)
    public int numDist_Memo(String s1, String s2, int n, int m, int[][] dp){
        if(m == 0){
            return dp[n][m] = 1; // geeks|gks
        }
        
        if(n < m){
            return dp[n][m] = 0; // ks|gks
        }
        
        if(dp[n][m] != -1) return dp[n][m];
        
        char ch1 = s1.charAt(n-1), ch2 = s2.charAt(m-1);
        
        // call are same as lcs but written before assigning it to a variable because there's only
        // 2 types of call in this ques and we had to add them so it's better to get the answer first and add later
        int call1 = numDist_Memo(s1,s2,n-1,m-1,dp); // same and picked
        int call2 = numDist_Memo(s1,s2, n-1,m,dp); // distinct

        if(ch1 == ch2){
            return dp[n][m] = call1 + call2;
        } else {
            return dp[n][m] = call2;
        }
    }

    public int numDist_Tab(String s1, String s2, int N, int M, int[][] dp){
        for(int n = 0; n <= N; n++){
            for(int m = 0; m <= M; m++){
                if(m == 0){
                    dp[n][m] = 1; // geeks|gks
                    continue;
                }
                
                if(n < m){
                    dp[n][m] = 0; // ks|gks
                    continue;
                }
                
                char ch1 = s1.charAt(n-1), ch2 = s2.charAt(m-1);
                
                // call are same as lcs but written before assigning it to a variable because there's only
                // 2 types of call in this ques and we had to add them so it's better to get the answer first and add later
                int call1 = dp[n-1][m-1]; //numDist_Memo(s1,s2,n-1,m-1,dp); // same and picked
                int call2 = dp[n-1][m];//numDist_Memo(s1,s2, n-1,m,dp); // distinct
        
                if(ch1 == ch2){
                    dp[n][m] = call1 + call2;
                } else {
                    dp[n][m] = call2;
                }
            }
        }
        return dp[N][M];
    }
}