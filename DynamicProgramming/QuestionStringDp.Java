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
                    // and make call on rest of the string to  give the answer eekske -> memo(eksk)+2e 
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

    // LC 72
    // do it with str1->saturday, str2-> sunday example for dry run
    public int editDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        
        int[][] dp = new int[n+1][m+1];
        for(int[] ele : dp){
            Arrays.fill(ele, -1);
        }
        
        // return editDistance_Memo(word1, word2, n, m, dp);
        return editDistance_Tab(word1, word2, n, m, dp);
    }
    
    public int editDistance_Memo(String str1, String str2, int n, int m, int[][] dp){
        if(n == 0 || m == 0) return dp[n][m] = (n == 0 ? m : n);
        if(dp[n][m] != -1) return dp[n][m];
        
        int insert = editDistance_Memo(str1, str2, n, m-1, dp);
        int delete = editDistance_Memo(str1, str2, n-1, m, dp);
        int replace = editDistance_Memo(str1, str2, n-1, m-1, dp);
        
        if(str1.charAt(n-1) == str2.charAt(m-1))
            return dp[n][m] = replace;
        else
            return dp[n][m] = Math.min(replace, Math.min(insert, delete)) +1;
    }
    
    public int editDistance_Tab(String str1, String str2, int N, int M, int[][] dp){
        for(int n = 0; n <= N; n++){
            for(int m = 0; m <= M; m++){
                if(n == 0 || m == 0){
                    dp[n][m] = (n == 0 ? m : n);
                    continue;
                }
        
                int insert = dp[n][m-1]; //editDistance_Memo(str1, str2, n, m-1, dp);
                int delete = dp[n-1][m]; //editDistance_Memo(str1, str2, n-1, m, dp);
                int replace = dp[n-1][m-1]; //editDistance_Memo(str1, str2, n-1, m-1, dp);
        
            if(str1.charAt(n-1) == str2.charAt(m-1))
                dp[n][m] = replace;
            else
                dp[n][m] = Math.min(replace, Math.min(insert, delete)) +1;
            }
        }
        return dp[N][M];
    }

    // follow-up-1 Every operation has some cost attached to it
    // cost = {a = insert, b = replace, c = delete};
    public int editDistanceFollowUp1_Memo(String str1, String str, int n, int m, int[] cost, int[][] dp) {
        if(n == 0 || m == 0)
            return dp[n][m] = (n == 0 ? m*cost[0] : n*cost[2]);
        
        if(dp[n][m] != -1) return dp[n][m];

        int insert = editDistanceFollowUp1_Memo(str1, str2, n, m-1, cost, dp);
        int delete = editDistanceFollowUp1_Memo(str1, str2, n-1, m, cost, dp);
        int replace = editDistanceFollowUp1_Memo(str1, str2, n-1, m-1, cost, dp);

        if(str1.charAt(n-1) == str2.charAt(m-1))
            return dp[n][m] = replace;
        else
            return dp[n][m] = Math.min(replace+cost[1], Math.min(insert+cost[0], delete+cost[2]));
    }

    // follow-up-2 Every character has cost of every operations

    // LC44 - Wildcard Matching
    public String removeStars(String str){
        int n = str.length();
        if(n == 0)return str;

        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));

        int i = 1;
        while(i < n){
            while(i < n && sb.charAt(sb.length()-1) == '*' && str.charAt(i) == '*')
                i++;
            
            if(i < n) sb.append(str.charAt(i));

            i++;
        }

        return sb.toString();
    }

    public int isMatch(String str, String pattern, int n, int m, int[][] dp){
        if(n == 0 || m == 0){
            if(n == 0 && m == 0) return dp[n][m] = 1;
            else if(m == 1 && pattern.charAt(m-1) == '*') return dp[n][m] = 1; // because only * can map empty string
            else return dp[n][m] = 0;
        }

        if(dp[n][m] != -1) return dp[n][m];

        char ch1 = str.charAt(n-1), ch2 = pattern.charAt(m-1);

        if(ch1 == ch2 || ch2 == '?')
            return dp[n][m] = isMatch(str, pattern, n-1,m-1,dp);
        else if(ch2 == '*'){
            boolean res = false;
            res = res || isMatch(str, pattern, n-1, m, dp) == 1; //sequence of characters
            res = res || isMatch(str, pattern, n, m-1, dp) == 1; // * mapped as empty string

            return dp[n][m] = res ? 1 : 0;
        } else {
            // chars not matching i.e not possible
            return dp[n][m] = 0;
        }
    }

    public int isMatchTab(String str, String pattern, int N, int M, int[][] dp){
        for(int n = 0; n <= N; n++){
            for(int m = 0; m <= M; m++){
                if(n == 0 || m == 0){
            if(n == 0 && m == 0) {
                dp[n][m] = 1;
                continue;
            }
            else if(m == 1 && pattern.charAt(m-1) == '*'){
                dp[n][m] = 1; // because only * can map empty string
                continue;
            }else{
                dp[n][m] = 0;
                continue;
            }
        }

        char ch1 = str.charAt(n-1), ch2 = pattern.charAt(m-1);

        if(ch1 == ch2 || ch2 == '?')
            dp[n][m] = dp[n-1][m-1];//isMatch(str, pattern, n-1,m-1,dp);
        else if(ch2 == '*'){
            boolean res = false;
            res = res || dp[n-1][m] == 1; //isMatch(str, pattern, n-1, m, dp) == 1; //sequence of characters
            res = res || dp[n][m-1] == 1; //isMatch(str, pattern, n, m-1, dp) == 1; // * mapped as empty string

            dp[n][m] = res ? 1 : 0;
        } else {
            // chars not matching i.e not possible
            dp[n][m] = 0;
        }
            }
        }
        return dp[N][M];
    }

    public boolean isMatch(String s, String p) {
        p = removeStars(p);
        int n = s.length(), m = p.length();
        int[][] dp = new int[n+1][m+1];

        for(int[] ele : dp) Arrays.fill(ele, -1);

        int ans = isMatch(s, p, n, m, dp);
        return ans == 1;
    }

    // Leetcode 1035(exactly like LCS just copy pasted the code)
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;

        int[][] dp = new int[n+1][m+1];
        for(int[] ele : dp) Arrays.fill(ele,-1);

        return maxUncrossedMemo(nums1,nums2, n, m, dp);
    }
    
    public int maxUncrossedMemo(int[] num1, int[] num2, int n, int m, int[][] dp){
        if(n == 0 || m == 0){
            return dp[n][m] = 0;
        }

        if(dp[n][m] != -1) return dp[n][m];
        
        if(num1[n-1] == num2[m-1]){
            return dp[n][m] = maxUncrossedMemo(num1, num2, n-1, m-1, dp)+1;
        } else {
            return dp[n][m] = Math.max(maxUncrossedMemo(num1, num2, n-1, m, dp), maxUncrossedMemo(num1, num2, n, m-1, dp));
        }
    }

    // Leetcode 1458 (Max dot product of two subsequences)
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[][] dp = new int[n+1][m+1];
        for(int[] d : dp) //can skip this step in case of tabulation
            Arrays.fill(d, -(int)1e9);
        return maxDotProduct_Memo(nums1, nums2, n, m, dp);
    }

    public int maxDotProduct_Memo(int[] nums1, int[] nums2, int n, int m, int[][] dp) {
        if(n == 0 || m == 0)
            return dp[n][m] = -(int)1e8; // to differentiate it from initial dp value we put -1e8 instead of -1e9
        
        if(dp[n][m] != -(int)1e9) return dp[n][m];

        int val = nums1[n-1] * nums2[m-1]; // all the numbers will give -ve ans instead of these two indices eg. [20,-3,-4,-5] && [3,2,1]
        int acceptedBothNumbers = maxDotProduct_Memo(nums1, nums2, n-1, m-1, dp) + val; // we may expect a positive number from other numbers too and also we got +ve ans from first indices of both the arr
        int a = maxDotProduct_Memo(nums1, nums2, n-1, m, dp); // 1st idx from nums1 is giving -ve ans so we drop it
        int b = maxDotProduct_Memo(nums1, nums2, n, m-1, dp); // 1st idx from nums2 is giving -ve ans so we drop it

        return dp[n][m] = getMax(val, acceptedBothNumbers, a, b); // max of above 4 conditions
    }

    public int maxDotProduct_Tab(int[] nums1, int[] nums2, int N, int M, int[][] dp){
        for(int n = 0; n <= N; n++){
            for(int m = 0; m <= M; m++){
                if(n == 0 || m == 0){
                    dp[n][m] = -(int)1e8;
                    continue;
                }
                int val = nums1[n-1] * nums2[m-1];
                int acceptedBothNumbers = dp[n-1][m-1] + val;
                int a = dp[n-1][m];
                int b = dp[n][m-1];

                dp[n][m] = getMax(val, acceptedBothNumbers, a, b); // max of above 4 conditions
            }
        }
        return dp[N][M];
    }

    public int getMax(int... arr){
        int max = arr[0];
        for(int ele : arr) max = Math.max(ele, max);
        return max;
    }

    // Leetcode 5 Longest Palindromic SUBSTRING
    public void longestPalindromicSubstringBooleanMatrixFill(String str){
        int n = str.length();
        boolean[][] dp = new boolean[n][n];

        for(int gap = 0; gap < n; gap++){
            for(int i = 0, j = gap; j < n; i++, j++){
                if(gap == 0) dp[i][j] = true;
                else if(gap == 1 && str.charAt(i) == str.charAt(j)) dp[i][j] = true;
                else dp[i][j] = str.charAt(i) == str.charAt(j) && dp[i+1][j-1];
            }
        }
    }

    // longest palindromic substring follow ups
    // followup1 -> Total nuber of palindromic string
    // followup2 -> Max Length of palindromic string
    // followup3 -> return the substring that has max len palindromic string
    public String lpsLeetcodeWithFollowups(String str){
        int n = str.length(), count = 0, maxLen = 0, si = 0;
        boolean[][] dp = new boolean[n][n];

        for(int gap = 0; gap < n; gap++){
            for(int i = 0, j = gap; j < n; i++, j++){
                if(gap == 0) dp[i][j] = true;
                else if(gap == 1 && str.charAt(i) == str.charAt(j)) dp[i][j] = true;
                else dp[i][j] = str.charAt(i) == str.charAt(j) && dp[i+1][j-1];

                if(dp[i][j]){
                    count++; // followup1
                    if(j-i+1 > maxLen){
                        maxLen = j-i+1; // followup2
                        si = i; // follow up 3 becoz if we have si and maxLen we can return that string
                    }
                }
            }
        }

        return str.substring(si, si+maxLen);
    }
    
}