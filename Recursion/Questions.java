public class Questions{
    // https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1

    public static ArrayList<String> findPath(int[][] m, int n) {
        ArrayList<String> res = new ArrayList<>();
        
        if (m[0][0] == 0 || m[n - 1][n - 1] == 0) return res;
        
        String[] dirS = {"D","L","R", "U"};
        int[][] dir = {{1,0},{0,-1},{0,1},{-1,0}};
        
        int moves = fill(0,0,m,res,"",dir,dirS);
        return res;
    }

    // exact flood fill with minor changes according to ques
    public static int fill(int sr, int sc, int[][] vis, ArrayList<String> res, String psf, int[][] dir, String[] dirS){
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
                count += fill(r, c, vis, res,psf + dirS[d], dir, dirS);
            }
        }

        vis[sr][sc] = 1; // unblock
        return count;
    }


    // https://practice.geeksforgeeks.org/problems/special-matrix4201/1
    // https://www.geeksforgeeks.org/rat-in-a-maze-with-multiple-steps-jump-allowed/?ref=rp

}