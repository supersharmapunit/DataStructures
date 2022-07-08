import java.util.Arrays;

// After DynamicProgramming.java
/*
Steps to go from RECURSION -> MEMOIZATION -> TABULATION

1. Faith
2. Tree Diagram
3. Recursive approch
4. Recursion -> Memoization
5. Observation
6. Tabulation
7. Optimization


// https://leetcode.com/discuss/general-discussion/458695/dynamic-programming-patterns (DP Pattern)
// https://leetcode.com/discuss/general-discussion/662866/Dynamic-Programming-for-Practice-Problems-Patterns-and-Sample-Solutions (Dp pattern wise questions)
// https://seanprashad.com/leetcode-patterns/

*/
public class Questions {

    // Leetcode 1137(Recursive)
    public int tribonacci(int n) {
        if (n <= 2) {
            if (n == 2)
                return 1;
            return n;
        }
        return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
    }

    // 1137 memoization
    public int tribonacciMemo(int n, int dp[]) {
        if (n <= 2) {
            return dp[n] = (n == 0 ? 0 : 1);
        }

        if (dp[n] != 0)
            return dp[n];
        return dp[n] = tribonacciMemo(n - 1, dp) + tribonacciMemo(n - 2, dp) + tribonacciMemo(n - 3, dp);
    }

    // 1137 tabulation
    public int tribonacciTab(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 2) {
                // return dp[n] = 1; remove return and add continue
                dp[n] = (n == 0 ? 0 : 1);
                continue;
            }
            // if(dp[n] != 0) return dp[n]; memoization wali line we will remove it
            // return dp[n] = tribonacciMemo(n-1, dp) + tribonacciMemo(n-2,dp) +
            // tribonacciMemo(n-3, dp);
            // remove return store cal. at dp[n] and remove recursion call and add dp at the
            // place of function name;

            dp[n] = dp[n - 1] + dp[n - 2] + dp[n - 3];
        }

        return dp[N];
    }

    // 1137 optimized
    public int tribonacciOpti(int N) {
        // after obeservation from tabulation writing this optimized code
        int a = 0, b = 1, c = 1; // we only need 3 ptrs no need to make array it will need more space
        for (int n = 0; n <= N; n++) {
            int sum = a + b + c;
            a = b;
            b = c;
            c = sum;
        }
        return a;
    }

    // Leetcode 70
    public int climbStairs(int n) {
        // return climbStairsRecursive(n);
        // return climbStairsMemoized(n, new int[n+1]);
        // return climbStairTabulation(n, new int[n+1]);
        return climbStairsOpti(n);
    }

    // 70 recursive
    public int climbStairsRecursive(int n) {
        if (n == 0)
            return 1;
        int count = 0;
        if (n - 1 >= 0)
            count += climbStairsRecursive(n - 1);
        if (n - 2 >= 0)
            count += climbStairsRecursive(n - 2);
        return count;
    }

    // 70 memoized
    public int climbStairsMemoized(int n, int[] dp) {
        if (n == 0)
            return dp[n] = 1;
        if (dp[n] != 0)
            return dp[n];
        int count = 0;
        if (n - 1 >= 0)
            count += climbStairsMemoized(n - 1, dp);
        if (n - 2 >= 0)
            count += climbStairsMemoized(n - 2, dp);
        return dp[n] = count;
    }

    // 70 tabulation
    public int climbStairTabulation(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n == 0) {
                dp[n] = 1;
                continue;
            }
            if (n - 1 >= 0)
                dp[n] += dp[n - 1];
            if (n - 2 >= 0)
                dp[n] += dp[n - 2];
        }
        return dp[N];
    }

    // 70 optimized(2ptr)
    public int climbStairsOpti(int N) {
        int a = 0, b = 1;
        for (int n = 0; n <= N; n++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return a;
    }

    // maze Path
    public int mazePath_memo(int er, int ec, int[][] dp, int[][] dir) {
        if (er == 0 && ec == 0) {
            return dp[er][ec] = 1;
        }

        if (dp[er][ec] != 0)
            return dp[er][ec];

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = er + dir[d][0];
            int c = ec + dir[d][1];
            if (r >= 0 && c >= 0 && r < dp.length && c < dp.length) {
                count += mazePath_memo(r, c, dp, dir);
            }
        }

        return dp[er][ec] = count;
    }

    public int mazePath_tabu(int ER, int EC, int[][] dp, int[][] dir) {
        for (int er = 0; er <= ER; er++) {
            for (int ec = 0; ec <= EC; ec++) {
                if (er == 0 && ec == 0) {
                    dp[er][ec] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = er + dir[d][0];
                    int c = ec + dir[d][1];
                    if (r >= 0 && c >= 0 && r < dp.length && c < dp.length) {
                        count += dp[r][c];
                    }
                }
                dp[er][ec] = count;
            }
        }

        return dp[ER][EC];
    }

    public int mazePathJump_memo(int er, int ec, int[][] dp, int[][] dir) {
        if (er == 0 && ec == 0) {
            return dp[er][ec] = 1;
        }

        if (dp[er][ec] != 0)
            return dp[er][ec];

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = er + dir[d][0];
            int c = ec + dir[d][1];
            while (r >= 0 && c >= 0 && r < dp.length && c < dp.length) {
                count += mazePathJump_memo(r, c, dp, dir);
                r += dir[d][0];
                c += dir[d][1];
            }
        }

        return dp[er][ec] = count;
    }

    public int mazePathJump_tabu(int ER, int EC, int[][] dp, int[][] dir) {
        for (int er = 0; er <= ER; er++) {
            for (int ec = 0; ec <= EC; ec++) {
                if (er == 0 && ec == 0) {
                    dp[er][ec] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = er + dir[d][0];
                    int c = ec + dir[d][1];
                    while (r >= 0 && c >= 0 && r < dp.length && c < dp.length) {
                        count += mazePathJump_memo(r, c, dp, dir);
                        r += dir[d][0];
                        c += dir[d][1];
                    }
                }

                dp[er][ec] = count;
            }
        }

        return dp[ER][EC];
    }

    public void mazePath() {
        int n = 3, m = 3;
        int er = n - 1, ec = m - 1;
        int[][] dp = new int[n][m];
        int[][] dir = { { -1, 0 }, { 0, -1 }, { -1, -1 } };

        int ans = mazePathJump_memo(er, ec, dp, dir);

        System.out.println(ans);
    }

    // Leetcode 62 copy paste from maze path but changes are only that in this ques
    // matrix is not sq.
    public int uniquePaths(int n, int m) {
        int[][] dir = { { -1, 0 }, { 0, -1 } };
        int dp[][] = new int[n][m];
        // return uniquePathsMemo(n-1,m-1,dir,dp);
        return uniquePathsTab(n - 1, m - 1, dir, dp);
    }

    public int uniquePathsMemo(int er, int ec, int[][] dir, int[][] dp) {
        if (er == 0 && ec == 0) {
            return dp[er][ec] = 1;
        }

        if (dp[er][ec] != 0)
            return dp[er][ec];

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = er + dir[d][0];
            int c = ec + dir[d][1];
            if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length) {
                count += uniquePathsMemo(r, c, dir, dp);
            }
        }

        return dp[er][ec] = count;
    }

    public int uniquePathsTab(int ER, int EC, int[][] dir, int[][] dp) {

        for (int er = 0; er <= ER; er++) {
            for (int ec = 0; ec <= EC; ec++) {
                if (er == 0 && ec == 0) {
                    dp[er][ec] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = er + dir[d][0];
                    int c = ec + dir[d][1];
                    if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length) {
                        count += dp[r][c];
                    }
                }

                dp[er][ec] = count;
            }
        }

        return dp[ER][EC];
    }

    // Leetcode 63 same as 62 but with obstacles but first fill dp with -1 because
    // it can be our potential ans
    public int uniquePathsWithObstacleMemo(int er, int ec, int[][] dir, int[][] dp, int[][] obstacleGrid) {
        if (obstacleGrid[er][ec] == 1 || obstacleGrid[0][0] == 1)
            return 0;

        if (er == 0 && ec == 0) {
            return dp[er][ec] = 1;
        }

        if (dp[er][ec] != -1)
            return dp[er][ec];

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = er + dir[d][0];
            int c = ec + dir[d][1];
            if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length && obstacleGrid[r][c] == 0) {
                count += uniquePathsWithObstacleMemo(r, c, dir, dp, obstacleGrid);
            }
        }

        return dp[er][ec] = count;

    }

    public int uniquePathsWithObstacleTab(int ER, int EC, int[][] dir, int[][] dp, int[][] obstacleGrid) {
        if (obstacleGrid[ER][EC] == 1 || obstacleGrid[0][0] == 1)
            return 0;
        for (int er = 0; er <= ER; er++) {
            for (int ec = 0; ec <= EC; ec++) {
                if (er == 0 && ec == 0) {
                    dp[er][ec] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = er + dir[d][0];
                    int c = ec + dir[d][1];
                    if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length && obstacleGrid[r][c] == 0) {
                        // just an extra check
                        count += dp[r][c];
                    }
                }

                dp[er][ec] = count;
            }
        }

        return dp[ER][EC];
    }

    // leetcode 396 not a dp problem but its important asked many times
    public int maxRotateFunction(int[] nums) {
        int n = nums.length, sum = 0, sumsf = 0, maxSum = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            sumsf += i * nums[i];
        }
        maxSum = sumsf;
        for (int i = 0; i < n; i++) {
            sumsf = sumsf - sum + n * nums[i];
            maxSum = Math.max(maxSum, sumsf);
        }
        return maxSum;
    }

    // leetcode 64 Min Path
    // same grid vala question but this time minimum dena h
    public int minPathSum(int[][] grid) {
        int n = grid.length, m = grid[0].length, ER = n - 1, EC = m - 1;
        int[][] dp = new int[n][m];
        int[][] dir = { { -1, 0 }, { 0, -1 } };
        return minPathSumTab(grid, dp, dir, ER, EC);
        // return minPathSumMemo(grid, dp, dir, ER, EC);
    }

    public int minPathSumMemo(int[][] grid, int[][] dp, int[][] dir, int er, int ec) {

        if (er == 0 && ec == 0) {
            return dp[er][ec] = grid[er][ec];
        }

        if (dp[er][ec] != 0)
            return dp[er][ec];

        int min = (int) 10e9;
        for (int[] d : dir) {
            int r = er + d[0];
            int c = ec + d[1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                min = Math.min(min, minPathSumMemo(grid, dp, dir, r, c));
            }
        }
        return dp[er][ec] = min + grid[er][ec];
    }

    public int minPathSumTab(int[][] grid, int[][] dp, int[][] dir, int ER, int EC) {
        for (int er = 0; er <= ER; er++) {
            for (int ec = 0; ec <= EC; ec++) {
                if (er == 0 && ec == 0) {
                    dp[er][ec] = grid[er][ec];
                    continue;
                }

                int min = (int) 10e9;
                for (int[] d : dir) {
                    int r = er + d[0];
                    int c = ec + d[1];

                    if (r >= 0 && c >= 0 && r <= ER && c <= EC) {
                        min = Math.min(min, dp[r][c]);
                    }
                }
                dp[er][ec] = min + grid[er][ec];
            }
        }
        return dp[ER][EC];
    }

    public int boardPath() {
        // starting point -> ending point (All ways) using step found by rolling dice
        int sp = 0, ep = 10;
        int[] dp = new int[ep + 1];
        int count = boardPahtMemo(sp, ep, dp);
        return count;
    }

    public int boardPahtMemo(int sp, int ep, int[] dp) {
        if (sp == ep)
            return dp[sp] = 1;
        if (dp[sp] != 0)
            return dp[sp];

        int count = 0;
        for (int dice = 1; dice <= 6 && sp + dice < dp.length; dice++) {
            count += boardPahtMemo(sp + dice, ep, dp);
        }

        return dp[sp] = count;
    }

    public int boardPahtTab(int SP, int ep, int[] dp) {
        for (int sp = ep; sp >= SP; sp--) {
            if (sp == ep) {
                dp[sp] = 1;
                continue;
            }

            int count = 0;
            for (int dice = 1; dice <= 6 && sp + dice < dp.length; dice++) {
                count += dp[sp + dice];
            }

            dp[sp] = count;

        }
        return dp[SP];
    }


    // gfg- Goldmine Problem
    public int maxGold(int n, int m, int M[][]) {
        int[][] dir = { { 1, 1 }, { 0, 1 }, { -1, 1 } };
        int[][] dp = new int[n][m];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, goldMineMemo(i, 0, M, dir, dp));
        }
        return max;
    }

    public static int goldMineMemo(int sr, int sc, int[][] arr, int[][] dir, int dp[][]) {
        int n = arr.length, m = arr[0].length;
        if (sc == m - 1)
            return dp[sr][sc] = arr[sr][sc];
        if (dp[sr][sc] != -1)
            return dp[sr][sc];
        int max = 0;

        for (int[] d : dir) {
            int r = sr + d[0], c = sc + d[1];

            if (r >= 0 && c >= 0 && r < n && c < m) {
                max = Math.max(max, goldMineMemo(r, c, arr, dir, dp) + arr[sr][sc]);
            }
        }
        return dp[sr][sc] = max;
    }

    public static int goldMineTab(int SR, int SC, int[][] arr, int[][] dp, int[][] dir) {
        int n = arr.length, m = arr[0].length;
        for (int sc = m - 1; sc >= SC; sc--) {
            for (int sr = n - 1; sr >= SR; sr--) {
                if (sc == m - 1) {
                    dp[sr][sc] = arr[sr][sc];
                    continue;
                }
                int max = 0;

                for (int[] d : dir) {
                    int r = sr + d[0], c = sc + d[1];

                    if (r >= 0 && c >= 0 && r < n && c < m) {
                        max = Math.max(max, dp[r][c] + arr[sr][sc]);
                    }
                }
                dp[sr][sc] = max;
            }
        }

        return dp[SR][SC];
    }

    // STRINGS==========================================================================================================


    // LC91-Decode ways
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        // return numDecodingMemo(0, s, dp);
        return numDecodingTab(0, s, dp);
    }

    public int numDecodingMemo(int idx, String s, int[] dp) {
        if (idx == s.length())
            return dp[idx] = 1;
        if (dp[idx] != -1)
            return dp[idx];

        int count = 0;

        // for single char
        int ch1 = s.charAt(idx) - '0';
        if (ch1 == 0)
            return dp[idx] = 0;
        count += numDecodingMemo(idx + 1, s, dp);

        // for double char
        if (idx < s.length() - 1) {
            int ch2 = s.charAt(idx + 1) - '0';
            int num = (ch1 * 10) + ch2;
            if (num <= 26)
                count += numDecodingMemo(idx + 2, s, dp);
        }

        return dp[idx] = count;
    }

    public int numDecodingTab(int IDX, String s, int[] dp) {
        for (int idx = s.length(); idx >= IDX; idx--) {
            if (idx == s.length()) {
                dp[idx] = 1;
                continue;
            }

            int count = 0;

            // for single char
            int ch1 = s.charAt(idx) - '0';
            if (ch1 == 0) {
                dp[idx] = 0;
                continue;
            }
            count += dp[idx + 1];

            // for double char
            if (idx < s.length() - 1) {
                int ch2 = s.charAt(idx + 1) - '0';
                int num = (ch1 * 10) + ch2;
                if (num <= 26)
                    count += dp[idx + 2];
            }

            dp[idx] = count;
        }
        return dp[IDX];
    }

    public int numDecodingOpti(String str) {
        int a = 1, b = 0; // here a-> last char of str and b = 0 beacuse empty char cannot decode to
                          // something
        for (int idx = str.length() - 1; idx >= 0; idx--) {
            int sum = 0;
            int num1 = str.charAt(idx) - '0';
            if (num1 != 0) { // to skip continue vala check we've made it !=
                sum = a;
                if (idx < str.length() - 1) {
                    int num2 = str.charAt(idx + 1) - '0';
                    num2 = (num1 * 10) + num2;
                    if (num2 <= 26)
                        sum += b; // only be included in certain conditions that's why
                }
            }
            // increment opreations
            b = a;
            a = sum;
        }
        return a;
    }

    // Leetcode 639
    private int mod = (int) 1e9 + 7;// important value to remember

    public long numDecoding2Memo(String str, int idx, long dp[]) {
        int n = str.length();
        if (idx == n)
            return dp[idx] = 1;

        if (dp[idx] != -1)
            return dp[idx];

        char ch1 = str.charAt(idx);
        if (ch1 == '0')
            return dp[idx] = 0; // str = 029*13 i.e ch1 = 0-> represents no decoding

        long count = 0;

        if (ch1 == '*') { // str == *1349 i.e ch1 = *
            count = (count + 9 * numDecoding2Memo(str, idx + 1, dp)) % mod; // range 1-9 i.e *9 because all will give
                                                                            // the same number of answer

            // for second ch
            if (idx < n - 1) {
                char ch2 = str.charAt(idx + 1);

                if (ch2 >= '0' && ch2 <= '6') { // ch1+ch2 can form only numbers between 10-16 and 20-26 i.e *2
                    count = (count + (2 * numDecoding2Memo(str, idx + 2, dp))) % mod;
                } else if (ch2 >= '7' && ch2 <= '9') { // ch1+ch2 can form only numbers between 17-19
                    count = (count + numDecoding2Memo(str, idx + 2, dp)) % mod;
                } else { // i.e its a star therfore the number which can be formed is in the range 11-26
                    count = (count + (15 * numDecoding2Memo(str, idx + 2, dp))) % mod;
                }
            }
        } else { // ch1 == normal ch encoding from 1-9
            count = (count + numDecoding2Memo(str, idx + 1, dp)) % mod; // range 1-9 i.e

            if (idx < n - 1) {
                char ch2 = str.charAt(idx + 1);

                if (ch2 == '*' && ch1 == '1') { // ch1+ch2 will be in the range 11-19
                    count = (count + numDecoding2Memo(str, idx + 2, dp) * 9) % mod;
                } else if (ch2 == '*' && ch1 == '2') { // ch1+ch2 will be in the range 21-26
                    count = (count + (numDecoding2Memo(str, idx + 2, dp) * 6)) % mod;
                } else if (ch2 != '*') {
                    int num = (ch1 - '0') * 10 + (ch2 - '0');
                    if (num <= 26)
                        count = (count + numDecoding2Memo(str, idx + 2, dp)) % mod;
                }
            }
        }

        return dp[idx] = count;
    }

    public long numDecoding2Tab(String str, int IDX, long[] dp) {
        int n = str.length(), mod = (int) 1e9 + 7;
        for (int idx = n; idx >= IDX; idx--) {
            if (idx == n) {
                dp[idx] = 1;
                continue;
            }

            char ch1 = str.charAt(idx);
            if (ch1 == '0') {
                dp[idx] = 0; // str = 029*13 i.e ch1 = 0-> represents no decoding
                continue;
            }

            long count = 0;

            if (ch1 == '*') { // str == *1349 i.e ch1 = *
                count = (count + 9 * dp[idx + 1]) % mod; // range 1-9 i.e *9 because all will give
                                                         // the same number of answer

                // for second ch
                if (idx < n - 1) {
                    char ch2 = str.charAt(idx + 1);

                    if (ch2 >= '0' && ch2 <= '6') { // ch1+ch2 can form only numbers between 10-16 and 20-26 i.e *2
                        count = (count + (2 * dp[idx + 2])) % mod;
                    } else if (ch2 >= '7' && ch2 <= '9') { // ch1+ch2 can form only numbers between 17-19
                        count = (count + dp[idx + 2]) % mod;
                    } else { // i.e its a star therfore the number which can be formed is in the range 11-26
                        count = (count + (15 * dp[idx + 2])) % mod;
                    }
                }
            } else { // ch1 == normal ch encoding from 1-9
                count = (count + dp[idx + 1]) % mod; // range 1-9 i.e

                if (idx < n - 1) {
                    char ch2 = str.charAt(idx + 1);

                    if (ch2 == '*' && ch1 == '1') { // ch1+ch2 will be in the range 11-19
                        count = (count + dp[idx + 2] * 9) % mod;
                    } else if (ch2 == '*' && ch1 == '2') { // ch1+ch2 will be in the range 21-26
                        count = (count + (dp[idx + 2] * 6)) % mod;
                    } else if (ch2 != '*') {
                        int num = (ch1 - '0') * 10 + (ch2 - '0');
                        if (num <= 26)
                            count = (count + dp[idx + 2]) % mod;
                    }
                }
            }

            dp[idx] = count;
        }
        return dp[IDX];
    }
    ToDivideNinK
    // HW :
    // https://practice.geeksforgeeks.org/problems/friends-pairing-problem5425/1
    // imaging like people sitting in a bus on pair seat and single seat
    public long countFriendsPairingsRecursive(int n) {
        if (n == 0)
            return 1; // we've made everybody sit down in pairs and singles as they want

        long single = countFriendsPairingsRecursive(n - 1); // call on remaining people

        // call on remaining people(i.e.n-2) * (this person want to pair up and he can
        // pair up with anybody except himself(i.e.n-1))
        long pair = n - 2 >= 0 ? (countFriendsPairingsRecursive(n - 2) * (n - 1)) % mod : 0;

        return (single + pair) % mod;

    }

    public long countFriendsPairingsMemo(int n, long[] dp) {
        if (n == 0)
            return dp[n] = 1;
        if (dp[n] != -1)
            return dp[n];

        long single = countFriendsPairingsMemo(n - 1, dp);
        long pair = n - 2 >= 0 ? (countFriendsPairingsMemo(n - 2, dp) * (n - 1)) % mod : 0;

        return dp[n] = (single + pair) % mod;
    }

    public long countFriendsPairingsTab(int N, long[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n == 0) {
                dp[n] = 1;
                continue;
            }

            long single = dp[n - 1];
            long pair = n - 2 >= 0 ? dp[n - 2] * (n - 1) % mod : 0;

            dp[n] = (single + pair) % mod;
        }
        return dp[N];
    }

    public long countFriendsPairingsTab(int n) {
        // return cfp(n);
        if (n == 0)
            return 0;
        long dp[] = new long[n + 1];
        Arrays.fill(dp, -1);
        // return cfpMemo(n, dp);
        return countFriendsPairingsTab(n, dp);
    }

    public long countFriendsPairingsOpti(int n) {
        long a = 1, b = 1;
        for (int i = 2; i <= n; i++) {
            long sum = b + (a * (i - 1)) % mod; // b-> single, a -> pairup *(i-1)
            a = b;
            b = sum % mod;
        }
        return b;
    }

    // https://www.geeksforgeeks.org/count-the-number-of-ways-to-divide-n-in-k-groups-incrementally/
    public static int numberOfWaysToDivideNinK_MEMO(int n, int k, int[][] dp) {
        if (n == k || k == 1) // n == k everybody has to form their own group to satisfy the condition
        // k == 1 every will have to form a group because group size is 1
            return dp[n][k] = 1;
        
        if (dp[n][k] != 0)
            return dp[n][k];

        int selfGroup = numberOfWaysToDivideNinK_MEMO(n - 1, k - 1, dp); // leader
        int partOfSomeonesGroup = numberOfWaysToDivideNinK_MEMO(n - 1, k, dp) * k; // part of someone elses group so that the combination
        // can be formed with anyone in the group i.e we multiply it by k

        return dp[n][k] = selfGroup + partOfSomeonesGroup;
    }

    public static int numberOfWaysToDivideNinK_Tab(int N, int K, int[][] dp){
    	for(int k = 1; k <= K; k++){
    		for(int n = k; n <= N; n++){
    			if (n == k || k == 1){
    				dp[n][k] = 1;
    				continue;
    			}
             

        		int selfGroup = dp[n-1][k-1];
        		int partOfSomeonesGroup = dp[n-1][k] * k; // part of someone elses group so that the combination
        		// can be formed with anyone in the group i.e we multiply it by k

        		dp[n][k] = selfGroup + partOfSomeonesGroup;
    		}
    	}
    	return dp[N][K];
    }
}