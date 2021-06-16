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
    public int FindWays(int n, int m, int[][] arr) {
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
    public void display1D(int[] arr) {
        for (int ele : arr)
            System.out.print(ele + " ");

        System.out.println();
    }

    public void display2D(int[][] arr) {
        for (int ele : arr)
            display1D(a);

        System.out.println();
    }

    public int ratInMazeWithJumps(int sr, int sc, int[][] jumpMat, int[][] dir, int[][] ans) {
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

    public int longestPath(int sr, int sc, int[][] arr, String ans, String psf, boolean[][] visited, int[][] dir, String[] dirS){
        int n = vis.length, m = vis[0].length;

        if (sr == n - 1 && sc == m - 1){
            if (psf.size() > ans.size()) ans = psf;
            else continue;
            return 1;
        }

        int count = 0;
        vis[sr][sc] = true;


        for (int d = 0; d < dir.length; d++){
            for (int jumps = 1; jumps < Math.max(m,n); jumps++){
                int r = sr + jumps + dir[d][0];
                int c = sc + jumps + dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m){
                    if (vis[r][c] == false){
                        count += longestPath(r, c, arr, ans, psf + dirS[d] + jumps, visited, dir, dirS);
                    }
                }

            }
        }


        vis[sr][sc] = false;
        return count;
    }

}