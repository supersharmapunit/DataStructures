import java.util.ArrayList;
import java.util.List;

public class Basic {
    public static void printIncreasing(int a, int b) {
        if (a > b)
            return;

        System.out.println(a);
        printIncreasing(a + 1, b);
    }

    public static void printDecreasing(int a, int b) {
        if (a > b)
            return;

        printDecreasing(a + 1, b);
        System.out.println(a);
    }

    public static void printIncreasingDecreasing(int a, int b) {
        if (a > b)
            return;

        System.out.println(a);
        printIncreasingDecreasing(a + 1, b);
        System.out.println(a);
    }

    public static void printEvenOdd(int a, int b) {
        if (a > b)
            return;

        if (a % 2 == 0)
            System.out.println(a);
        printEvenOdd(a + 1, b);
        if (a % 2 != 0)
            System.out.println(a);
    }

    public static int fib(int a) {
        if (a <= 1)
            return 1;

        return fib(a - 1) * a;
    }

    public static int maximumInArray(int[] arr, int idx) {
        if (idx == arr.length)
            return -(int) 1e9;

        int maxSf = maximumInArray(arr, idx + 1);

        return Math.max(arr[idx], maxSf);

    }

    public static int minimumInArray(int[] arr, int idx) {
        if (idx == arr.length)
            return (int) 1e9;

        int minSF = minimumInArray(arr, idx + 1);

        return Math.min(arr[idx], minSF);
    }

    public static boolean findInArray(int[] arr, int idx, int data) {
        if (idx == arr.length)
            return false;

        return arr[idx] == data || findInArray(arr, idx + 1, data);

    }

    public static int firstIDX(int[] arr, int idx, int data) {
        if (idx == arr.length)
            return -1;

        return arr[idx] == data ? idx : firstIDX(arr, idx + 1, data);

    }

    public static int lastIDX(int[] arr, int idx, int data) {
        if (idx == arr.length) {
            return -1;
        }

        int smallans = lastIDX(arr, idx + 1, data);

        if (smallans != -1)
            return smallans;

        return arr[idx] == data ? idx : -1;
    }

    public static boolean isSorted(int[] arr, int n) {
        if (n == 1 || n == 0)
            return true;

        return arr[n - 1] < arr[n - 2] ? false : isSorted(arr, n - 1);
    }

    public static int power(int a, int b) {
        if (b < 1)
            return 1;

        return a * power(a, b - 1);
    }

    public static int powerOpti(int a, int b) {
        if (b == 0)
            return 1;
        int rres = power(a, b / 2);
        rres *= rres;
        return b % 2 == 0 ? rres : rres * a;
    }

    public static int numberOfDigits(int n, int count) {
        if (n / 10 < 10)
            return 1;
        numberOfDigits(n % 10, count++);
        return count;
    }

    public static void display(int[] arr, int idx) {
        if (idx == arr.length) {
            return;
        }

        System.out.print(arr[idx] + " ");
        display(arr, idx + 1);
    }

    public static void displayReverse(int[] arr, int idx) {
        if (idx == arr.length) {
            return;
        }

        displayReverse(arr, idx + 1);
        System.out.print(arr[idx] + " ");
    }

    public static int printTreePaths(int n) {
        if (n == 0 || n == 1) {
            System.out.println("base" + n);
            return n;
        }

        int ans = 0;

        System.out.println("Pre: " + n);
        ans += printTreePaths(n - 1);

        System.out.println("In: " + n);
        ;

        ans += printTreePaths(n - 2);
        System.out.println("Post: " + n);

        return ans + 3;

    }

    public static int[] allIndex(int[] arr, int idx, int count, int data) {
        if (idx == arr.length) {
            return new int[count];
        }
        if (arr[idx] == data)
            count++;

        int[] psf = allIndex(arr, idx + 1, count, data);

        if (arr[idx] == data)
            psf[--count] = idx;

        return psf;
    }

    public static ArrayList<String> subsequenceStr_1(String str, int idx) {
        if (idx == str.length()) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> recAns = subsequenceStr_1(str, idx + 1);
        ArrayList<String> myAns = new ArrayList<>(recAns); // automatically adds recAns to the ArrayList
        // in which there are elements without the first character
        // now when that char will be included -->
        for (String ele : recAns)
            myAns.add(str.charAt(idx) + ele);

        return myAns;
    }

    public static int subsequenceStr_2(String str, int idx, String ans, ArrayList<String> res) {
        if (idx == str.length()) {
            res.add(ans);
            System.out.print(" " + ans);
            return 1;
        }

        int count = 0;

        count += subsequenceStr_2(str, idx + 1, ans + str.charAt(idx), res);
        count += subsequenceStr_2(str, idx + 1, ans, res);

        return count;
    }

    // GET KEYPAD PATTERNS
    static String keypad[] = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz", " " };

    public static ArrayList<String> getKPC(String ques) {
        if (ques.length() == 0) {
            ArrayList<String> base = new ArrayList<String>();
            base.add("");
            return base;
        }
        char ch = ques.charAt(0);
        String roq = ques.substring(1); // roq = rest of the ques

        ArrayList<String> rres = getKPC(roq);

        int idx = ch - '0';
        String word = keypad[idx];
        ArrayList<String> mylist = new ArrayList<String>();

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            for (String s : rres) {
                mylist.add(c + s);
            }
        }
        return mylist;
    }

    // top to bottom
    public static ArrayList<String> mazePath_HVD(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myAns = new ArrayList<>();
        if (sr + 1 <= er) {
            ArrayList<String> Vertical = mazePath_HVD(sr + 1, sc, er, ec);
            for (String s : Vertical) {
                myAns.add("V" + s);
            }
        }

        if (sc + 1 <= ec && sr + 1 <= er) {
            ArrayList<String> Diagonal = mazePath_HVD(sr + 1, sc + 1, er, ec);
            for (String s : Diagonal) {
                myAns.add("D" + s);
            }

        }

        if (sc + 1 <= ec) {
            ArrayList<String> Horizontal = mazePath_HVD(sr, sc + 1, er, ec);
            for (String s : Horizontal) {
                myAns.add("H" + s);
            }
        }

        return myAns;
    }

    public static ArrayList<String> mazePathHVDmulti(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myans = new ArrayList<>();

        for (int jumps = 1; sc + jumps <= ec; jumps++) {
            ArrayList<String> horizontal = mazePathHVDmulti(sr, sc + jumps, er, ec);
            for (String ele : horizontal)
                myans.add(ele + "h" + jumps);
        }

        for (int jumps = 1; sr + jumps <= er; jumps++) {
            ArrayList<String> vertical = mazePathHVDmulti(sr + jumps, sc, er, ec);
            for (String ele : vertical)
                myans.add(ele + "v" + jumps);
        }

        for (int jumps = 1; sr + jumps <= er && sc + jumps <= ec; jumps++) {
            ArrayList<String> diagonal = mazePathHVDmulti(sr + jumps, sc + jumps, er, ec);
            for (String ele : diagonal)
                myans.add(ele + "d" + jumps);
        }

        return myans;
    }

    // bottom to top
    public static int mazePathHVD2(int sr, int sc, int er, int ec, String psf, ArrayList<String> ans) {
        if (sr == ec && sc == ec) {
            ans.add(psf);
            return 1;
        }
        int count = 0;
        if (sc + 1 <= ec) {
            count += mazePathHVD2(sr, sc + 1, er, ec, psf + "h", ans);
        }
        if (sr + 1 <= er) {
            count += mazePathHVD2(sr + 1, sc, er, ec, psf + "v", ans);
        }
        if (sr + 1 <= er && sc + 1 <= ec) {
            count += mazePathHVD2(sr + 1, sc + 1, er, ec, psf + "d", ans);
        }

        return count;

    }

    public static int mazePathHVD2multi(int sr, int sc, int er, int ec, String psf, ArrayList<String> ans) {
        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1;
        }

        int count = 0;

        for (int jumps = 1; sc + jumps <= ec; jumps++) {
            count += mazePathHVD2multi(sr, sc + jumps, er, ec, psf + "h" + jumps, ans);
        }
        for (int jumps = 1; sr + jumps <= er; jumps++) {
            count += mazePathHVD2multi(sr + jumps, sc, er, ec, psf + "v" + jumps, ans);
        }
        for (int jumps = 1; sc + jumps <= ec && sr + jumps <= er; jumps++) {
            count += mazePathHVD2multi(sr + jumps, sc + jumps, er, ec, psf + "d" + jumps, ans);
        }

        return count;
    }

    public static int mazePathHVD2DirOptimized(int sr, int sc, int er, int ec, int[][] dir, String[] dirS,
            ArrayList<String> ans, String psf) {
        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int x = sr + dir[d][0];
            int y = sc + dir[d][1];

            if (x >= 0 && y >= 0 && x <= er && y <= ec)
                count += mazePathHVD2DirOptimized(x, y, er, ec, dir, dirS, ans, psf + dirS[d]);
        }

        return count;
    }

    public static void mazePathsAll() {
        ArrayList<String> ans = new ArrayList<>();
        ArrayList<String> ans1 = new ArrayList<>();

        int[][] dir = { { 0, 1 }, { 1, 0 }, { 1, 1 } };
        String[] dirS = { "h", "v", "d" };

        System.out.println(mazePathHVD2DirOptimized(0, 0, 2, 2, dir, dirS, ans, ""));
        System.out.println(mazePathHVD2(0, 0, 2, 2, "", ans1));
        System.out.println(ans);
        System.out.println(ans1);
    }

    public static void floodfillAlgorithm() {
        int sr = 0, sc = 0, n = 3, m = 3;
        boolean[][] vis = new boolean[n][m];
        // int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1
        // }, { 0, -1 }, { -1, -1 } };
        // String[] dirS = { "U", "E", "L", "S", "D", "N", "R", "W" };

        int[][] dir = { { 1, 0 }, { 0, 1 }, { 1, 1 } };
        String[] dirS = { "V", "H", "D" };

        ArrayList<String> ans = new ArrayList<>();

        System.out.println(floodFill_multi2dirInRadLINEAR(sr, sc, vis, dir, dirS, ans, ""));

        System.out.println(ans);
    }

    public static int floodFill(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS, ArrayList<String> ans,
            String psf) {
        int n = vis.length, m = vis[0].length;

        if (sr == n - 1 && sc == m - 1) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        vis[sr][sc] = true;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c])
                count += floodFill(r, c, vis, dir, dirS, ans, psf + dirS[d]);

        }

        vis[sr][sc] = false;
        return count;
    }

    public static int floodFill_multi2dirInRadLINEAR(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS,
            ArrayList<String> ans, String psf) {
        int n = vis.length, m = vis[0].length;

        if (sr == n - 1 && sc == m - 1) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        vis[sr][sc] = true;

        for (int d = 0; d < dir.length; d++)
            for (int rad = 1; rad <= Math.max(n, m); rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m) {
                    if (!vis[r][c])
                        count += floodFill_multi2dirInRadLINEAR(r, c, vis, dir, dirS, ans, psf + dirS[d] + rad);
                } else
                    break;
            }

        vis[sr][sc] = false;
        return count;
    }

    public static int floodFill_multiCircular(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS,
            ArrayList<String> ans, String psf) {
        int n = vis.length, m = vis[0].length;

        if (sr == n - 1 && sc == m - 1) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        vis[sr][sc] = true;

        for (int rad = 1; rad <= Math.max(n, m); rad++) {
            for (int d = 0; d < dir.length; d++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                    count += floodFill_multiCircular(r, c, vis, dir, dirS, ans, psf + dirS[d] + rad);
                }
            }
        }

        vis[sr][sc] = false;
        return count;
    }

    public static class pair {
        String psf = "";
        int len = 0;

        pair(String psf, int len) {
            this.len = len;
            this.psf = psf;
        }
    }

    public static pair longestPath(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS) {
        int n = vis.length, m = vis[0].length;
        if (sr == n - 1 && sc == m - 1) {
            return new pair("", 0);
        }

        vis[sr][sc] = true; // blocked
        pair myAns = new pair("", -1);
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m) {
                if (!vis[r][c]) {
                    pair recAns = longestPath(r, c, vis, dir, dirS);
                    if (recAns.len != -1 && recAns.len + 1 > myAns.len) {
                        myAns.len = recAns.len + 1;
                        myAns.psf = dirS[d] + recAns.psf;
                    }
                }
            }
        }

        vis[sr][sc] = false; // unblocked
        return myAns;
    }

    public static pair shortestPath(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS) {
        int n = vis.length, m = vis[0].length;
        if (sr == n - 1 && sc == m - 1) {
            return new pair("", 0);
        }

        vis[sr][sc] = true; // blocked
        pair myAns = new pair("", (int) 1e9);
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m) {
                if (!vis[r][c]) {
                    pair recAns = shortestPath(r, c, vis, dir, dirS);
                    if (recAns.len != (int) 1e9 && recAns.len + 1 < myAns.len) {
                        myAns.len = recAns.len + 1;
                        myAns.psf = dirS[d] + recAns.psf;
                    }
                }
            }
        }

        vis[sr][sc] = false; // unblocked
        return myAns;
    }

    public static void longestShortestPath() {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        String[] dirS = { "D", "R", "U", "L" };

        boolean[][] vis = new boolean[3][3];
        // vis[1][1] = vis[1][2] = vis[2][1] = true;

        pair ans = longestPath(0, 0, vis, dir, dirS);
        System.out.println(ans.psf + " @ " + ans.len);
    }

    public static void main(String args[]) {
        floodfillAlgorithm();

    }
}