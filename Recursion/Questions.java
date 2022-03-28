import java.util.*;

public class Questions {
    // https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1

    public static ArrayList<String> findPath(int[][] m, int n) {
        ArrayList<String> res = new ArrayList<>();

        if (m[0][0] == 0 || m[n - 1][n - 1] == 0)
            return res;

        String[] dirS = { "D", "L", "R", "U" };
        int[][] dir = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };

        int moves = fill(0, 0, m, res, "", dir, dirS);
        return res;
    }

    // exact flood fill with minor changes according to ques
    public static int floodfill(int sr, int sc, int[][] vis, ArrayList<String> res, String psf, int[][] dir,
            String[] dirS) {
        int n = vis.length, m = vis[0].length;
        if (sr == n - 1 && sc == m - 1) {
            res.add(psf);
            return 1;
        }

        vis[sr][sc] = 0; // block
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && vis[r][c] == 1) {
                count += fill(r, c, vis, res, psf + dirS[d], dir, dirS);
            }
        }

        vis[sr][sc] = 1; // unblock
        return count;
    }

    // https://practice.geeksforgeeks.org/problems/special-matrix4201/1
    // will give TLE here but it will be solved using DP
    public static int FindWays(int n, int m, int[][] arr) {
        int vis[][] = new int[n + 1][m + 1];
        for (int[] ele : arr)
            vis[ele[0]][ele[1]] = 1;

        if (vis[1][1] == 1 || vis[n][m] == 1)
            return 0;

        int[][] dir = { { 0, 1 }, { 1, 0 } };
        int moves = floodfill(1, 1, vis, dir);
        return moves;
    }

    // https://www.geeksforgeeks.org/rat-in-a-maze-with-multiple-steps-jump-allowed/?ref=rp
    public static void display1D(int[] arr) {
        for (int ele : arr)
            System.out.print(ele + " ");

        System.out.println();
    }

    public static void display2D(int[][] arr) {
        for (int ele : arr)
            display1D(a);

        System.out.println();
    }

    public static int ratInMazeWithJumps(int sr, int sc, int[][] jumpMat, int[][] dir, int[][] ans) {
        int n = jumpMat.length, m = jumpMat[0].length;
        if (sr == n - 1 && sc == m - 1) {
            ans[sr][sc] = 1;
            display2D(ans);
            ans[sr][sc] = 0;
            return 1;
        }

        int jump = jumpMat[sr][sc];
        jumpMat[sr][sc] = 0; // block
        ans[sr][sc] = 1; // psf

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= jump; rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m) {
                    if (jumpMat[r][c] != 0)
                        count += floodFill(r, c, jumpMat, dir, ans);
                } else
                    break;
            }
        }

        jumpMat[sr][sc] = jump; // unblock
        ans[sr][sc] = 0; // psf
        return count;
    }

    public static int longestPath(int sr, int sc, int[][] arr, String ans, String psf, boolean[][] visited, int[][] dir,
            String[] dirS) {
        int n = visited.length, m = visited[0].length;

        if (sr == n - 1 && sc == m - 1) {
            if (psf.length() > ans.length())
                ans = psf;
            
            return 1;
        }

        int count = 0;
        visited[sr][sc] = true;

        for (int d = 0; d < dir.length; d++) {
            for (int jumps = 1; jumps < Math.max(m, n); jumps++) {
                int r = sr + jumps + dir[d][0];
                int c = sc + jumps + dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m) {
                    if (visited[r][c] == false) {
                        count += longestPath(r, c, arr, ans, psf + dirS[d] + jumps, visited, dir, dirS);
                    }
                }

            }
        }

        visited[sr][sc] = false;
        return count;
    }

    public static int equiSet(int[] arr, int idx, int sum1, int sum2, String set1, String set2) {
        if (idx == arr.length) {
            if (sum1 == sum2) {
                System.out.println(set1 + "= " + set2);
                return 1;
            }
            return 0;
        }

        int count = 0;

        count += equiSet(arr, idx + 1, sum1 + arr[idx], sum2, set1 + arr[idx] + " ", set2); // if in set1
        count += equiSet(arr, idx + 1, sum1, sum2 + arr[idx], set1, set2 + arr[idx] + " "); // if in set2

        return count;
    }

    public static int permutationUnique(String str, String ans) { // with boolean array
        // will take more space than other but there is no need to sort the string here
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }
        boolean[] vis = new boolean[26];
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!vis[ch - 'a']) {
                vis[ch - 'a'] = true;
                String ros = str.substring(0, i) + str.substring(i + 1);
                count += permutationUnique(ros, ans + ch);
            }
        }
        return count;
    }

    public static int permutationUnique2(String str, String ans) { // it will be faster but
        // string is needed to be sorted
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }
        char prev = '$'; // to mark prev char
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (prev != ch) {
                String ros = str.substring(0, i) + str.substring(i + 1);
                count += permutationUnique2(ros, ans + ch);
            }
            prev = ch;
        }
        return count;
    }

    public static void equiSetH() {
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80 };
        System.out.println(equiSet(arr, 1, 0 + arr[0], 0, "" + arr[0] + " ", ""));
    }

}