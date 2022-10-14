import java.util.*;

public class QueenSeries {

    // tnb -> total no of boxes, tnq -> total no. of queens, bno -> box no., qno ->
    // queen no.
    public static int queenCombination(int tnb, int tnq, int bno, int qno, String ans) {
        if (tnq == qno) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = bno; i < tnb; i++) {
            count += queenCombination(tnb, tnq, i + 1, qno + 1, ans + "q" + qno + "b" + i + " ");
        }
        return count;
    }

    public static int queenCombination2d(boolean[][] boxes, int tnq, int bno, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0, n = boxes.length, m = boxes[0].length;
        for (int i = bno; i < n * m; i++) {
            int r = i / m, c = i % m;
            if (!boxes[r][c]) {
                boxes[r][c] = true;
                count += queenCombination2d(boxes, tnq - 1, i + 1, ans + "(" + r + "," + c + ") ");
                boxes[r][c] = false;
            }
        }
        return count;
    }

    public static int queenCombination_sub(int tnb, int tnq, int bno, int qno, String ans) {
        if (tnq == qno || bno == tnb) {
            if (tnq == qno) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;

        if (tnq - 1 >= 0)
            count += queenCombination(tnb, tnq, bno + 1, qno + 1, ans + "b" + bno + "q" + qno + " ");

        count += queenCombination(tnb, tnq, bno + 1, qno, ans);

        return count;
    }

    public static int queenPermutation(boolean[] boxes, int tnq, int bno, int qno, String ans) {
        if (qno == tnq) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < boxes.length; i++) {
            if (!boxes[i]) {
                boxes[i] = true;
                count += queenPermutation(boxes, tnq, 0, qno + 1, ans + " q" + qno + " b" + i);
                boxes[i] = false;
            }
        }
        return count;
    }

    public static int queenPermutation_sub(boolean[] boxes, int tnq, int bno, int qno, String ans) {
        if (tnq == qno || bno == boxes.length) {
            if (tnq == qno) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;

        if (!boxes[bno] && tnq - 1 >= 0) {
            boxes[bno] = true;
            count += queenPermutation_sub(boxes, tnq, 0, qno + 1, ans + "b" + bno + "q" + qno + " ");
            boxes[bno] = false;
        }

        count += queenPermutation_sub(boxes, tnq, bno + 1, qno, ans);

        return count;
    }

    public static int queenPermutation2d(boolean[][] boxes, int tnq, int bno, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0, n = boxes.length, m = boxes[0].length;

        for (int i = bno; i < n * m; i++) {
            int r = i / m, c = i % m;
            if (!boxes[r][c] && tnq - 1 >= 0) {
                boxes[r][c] = true;
                count += queenPermutation2d(boxes, tnq - 1, 0, ans + "(b" + r + "q" + c + ") ");
                boxes[r][c] = false;
            }
        }
        return count;
    }

    // ===============================================================================================================

    public static int nQueens01Combination(boolean[][] boxes, int tnq, int bno, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0, n = boxes.length, m = boxes[0].length;
        for (int i = bno; i < n * m; i++) {
            int r = i / m, c = i % m;
            if (isSafe(boxes, r, c)) {
                boxes[r][c] = true;
                count += nQueens01Combination(boxes, tnq - 1, i + 1, ans + "(" + r + "," + c + ") ");
                boxes[r][c] = false;
            }
        }
        return count;
    }

    public static int nQueens01CombinationSubSequenceMethod(boolean[][] boxes, int tnq, int idx, String ans) {
        int count = 0, n = boxes.length, m = boxes[0].length;
        if (tnq == 0 || idx == n * m) {
            if (tnq == 0)
                System.out.println(ans);
            return tnq == 0 ? 1 : 0;
        }

        int r = idx / m, c = idx % m;
        if (isSafe(boxes, r, c)) {
            boxes[r][c] = true;
            count += nQueens01CombinationSubSequenceMethod(boxes, tnq - 1, idx + 1, ans + "(" + r + "," + c + ") ");
            boxes[r][c] = false;
        }
        count += nQueens01CombinationSubSequenceMethod(boxes, tnq, idx + 1, ans);
        return count;
    }

    public static boolean isSafe(boolean chess[][], int row, int col) {
        int n = chess.length, m = chess[0].length;

        int dir[][] = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } }; // will
                                                                                                                // be
                                                                                                                // required
                                                                                                                // for
                                                                                                                // nqueenpermutation
        // int dir[][] = {{-1,0},{-1,1}, {0,-1}, {-1,-1}}; // there are 8 directions but
        // other than these 4 dirs board is yet to be explored
        // so the previously placed queens are the only obstacles which we need to check
        // ** just for nqueen combination **

        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= n; rad++) {
                int r = row + rad * dir[d][0];
                int c = col + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < n) {
                    if (chess[r][c])
                        return false;
                } else
                    break;
            }
        }
        return true;

    }

    public static int nQueenPermutation(boolean chess[][], int tnq, String ans) {
        if (0 == tnq) {
            System.out.println(ans);
            return 1;
        }
        int count = 0, n = chess.length;
        for (int idx = 0; idx < n * n; idx++) {
            int r = idx / n, c = idx % n;
            if (!chess[r][c] && isSafe(chess, r, c)) {
                chess[r][c] = true;
                count += nQueenPermutation(chess, tnq - 1, ans + "(R" + r + ",C" + c + ") ");
                chess[r][c] = false;
            }
        }
        return count;
    }

    public static int nQueens01PermutationSubSequenceMethod(boolean[][] boxes, int tnq, int idx, String ans) {
        int count = 0, n = boxes.length, m = boxes[0].length;
        if (tnq == 0 || idx == n * m) {
            if (tnq == 0)
                System.out.println(ans);
            return tnq == 0 ? 1 : 0;
        }

        int r = idx / m, c = idx % m;
        if (isSafe(boxes, r, c) && !boxes[r][c]) {
            boxes[r][c] = true;
            count += nQueens01PermutationSubSequenceMethod(boxes, tnq - 1, 0, ans + "(" + r + "," + c + ") ");
            boxes[r][c] = false;
        }
        count += nQueens01PermutationSubSequenceMethod(boxes, tnq, idx + 1, ans);
        return count;
    }

    // optimizing nQueen through optimizing it's isSafe fn through
    // Shadow/BranchAndBound Technique
    // gfg submission -
    // https://practice.geeksforgeeks.org/problems/n-queen-problem0315/0
    private static boolean[] row;
    private static boolean[] col;
    private static boolean[] diagonal;
    private static boolean[] aDiagonal;

    public ArrayList<ArrayList<Integer>> nQueenisSafeOpti(int n) {
        int m = n;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> asf = new ArrayList<>();

        row = new boolean[n];
        col = new boolean[m];
        diagonal = new boolean[n + m - 1];
        aDiagonal = new boolean[n + m - 1];

        nQueenCombinationisSafeOpti(n, m, n, 0, ans, asf);

        return ans;
    }

    private int nQueenCombinationisSafeOpti(int n, int m, int tnq, int idx, ArrayList<ArrayList<Integer>> ans,
            ArrayList<Integer> asf) {
        if (tnq == 0) {
            ArrayList<Integer> base = new ArrayList<>(asf);
            ans.add(base);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < n * m; i++) {
            int r = i / m, c = i % m;

            if (!row[r] && !col[c] && !diagonal[r + c] && !aDiagonal[r - c + m - 1]) {
                row[r] = col[c] = diagonal[r + c] = aDiagonal[r - c + m - 1] = true;
                asf.add(c + 1);
                count += nQueenCombinationisSafeOpti(n, m, tnq - 1, i + 1, ans, asf);
                asf.remove(asf.size() - 1);
                row[r] = col[c] = diagonal[r + c] = aDiagonal[r - c + m - 1] = false;
            }
        }
        return count;
    }

    private int nQueenCombinationSubseQuenceisSafeOpti(int n, int m, int tnq, int idx,
            ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> asf) {
        int count = 0;
        if (tnq == 0 || idx == n * m) {
            if (tnq == 0) {
                ArrayList<Integer> base = new ArrayList<>(asf);
                ans.add(base);
                return 1;
            }
            return 0;
        }

        int r = idx / m, c = idx % m;

        if (!row[r] && !col[c] && !diagonal[r + c] && !aDiagonal[r - c + m - 1]) {
            row[r] = col[c] = diagonal[r + c] = aDiagonal[r - c + m - 1] = true;
            asf.add(c + 1);
            count += nQueenCombinationSubseQuenceisSafeOpti(n, m, tnq - 1, idx + 1, ans, asf);
            asf.remove(asf.size() - 1);
            row[r] = col[c] = diagonal[r + c] = aDiagonal[r - c + m - 1] = false;
        }
        count += nQueenCombinationSubseQuenceisSafeOpti(n, m, tnq, idx + 1, ans, asf);

        return count;
    }

    privte int nQueenPermutationIsSafeOpti(int n, int m, int tnq, int idx,
            ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> asf){
            if (tnq == 0) {
                ArrayList<Integer> base = new ArrayList<>(asf);
                ans.add(base);
                return 1;
        }

        int count = 0;
        for (int i = idx; i < n * m; i++) {
            int r = i / m, c = i % m;

            if (!row[r] && !col[c] && !diagonal[r + c] && !aDiagonal[r - c + m - 1]) {
                row[r] = col[c] = diagonal[r + c] = aDiagonal[r - c + m - 1] = true;
                asf.add(c + 1);
                count += nQueenPermutationIsSafeOpti(n, m, tnq - 1, 0, ans, asf); // just changed idx here with 0
                asf.remove(asf.size() - 1);
                row[r] = col[c] = diagonal[r + c] = aDiagonal[r - c + m - 1] = false;
            }
        }
        return count;
            }

    private int nQueenPermutationSubsequenceIsSafeOpti(int n, int m, int tnq, int idx,
            ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> asf){
        int count = 0;
        if (tnq == 0 || idx == n * m) {
            if (tnq == 0) {
                ArrayList<Integer> base = new ArrayList<>(asf);
                ans.add(base);
                return 1;
            }
            return 0;
        }

        int r = idx / m, c = idx % m;

        if (!row[r] && !col[c] && !diagonal[r + c] && !aDiagonal[r - c + m - 1]) {
            row[r] = col[c] = diagonal[r + c] = aDiagonal[r - c + m - 1] = true;
            asf.add(c + 1);
            count += nQueenPermutationSubsequenceIsSafeOpti(n, m, tnq - 1, 0, ans, asf); //just changed here idx to = 0
            asf.remove(asf.size() - 1);
            row[r] = col[c] = diagonal[r + c] = aDiagonal[r - c + m - 1] = false;
        }
        count += nQueenPermutationSubsequenceIsSafeOpti(n, m, tnq, idx + 1, ans, asf);

        return count;
    }

    // lc37 suduko solver(this is not optimized we can optimize it by using bits)
    public void solveSudoku(char[][] board) {
        int m = board.length; // m*m board
        ArrayList<Integer> al = new ArrayList<>(); // to store blank spaces so that we can optimize calls
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < m; c++) {
                if (board[r][c] == '.')
                    al.add(r * m + c);
            }
        }
        sudukoSolver(board, al, 0);
    }

    // think of true false as 0 and 1 to relate with previously solved questions
    public boolean sudukoSolver(char[][] board, ArrayList<Integer> list, int idx) {
        if (idx == list.size())
            return true;

        // decoding the encoded ind
        int r = list.get(idx) / 9;
        int c = list.get(idx) % 9; // 9 beacuse sududo has 9 length always

        // option for each empty cell is 1-9
        for (int num = 1; num <= 9; num++) {
            if (isSafeToPlaceNumber(board, r, c, num)) {
                board[r][c] = (char) ('0' + num); // will act as visited
                if (sudukoSolver(board, list, idx + 1))
                    return true; // if recieved true from base case there's
                // no need to execute code further i.e we've found the right choice so return
                // true
                board[r][c] = '.'; // will act as marking unvisited
            }
        }

        return false;
    }

    public boolean isSafeToPlaceNumber(char board[][], int r, int c, int num) {
        // row
        for (int i = 0; i < 9; i++) {
            if (board[i][c] - '0' == num)
                return false;
        }

        // col
        for (int i = 0; i < 9; i++) {
            if (board[r][i] - '0' == num)
                return false;
        }

        // subMatrix - to find starting idx of each matrix we'll do compression and
        // decompression of that index
        // we'll divide it by 3 to compress and multiply it by 3 to decompress beacuse
        // we'll loose some value in
        // division we'll get the startin index

        // suppose for r = 7, c = 3 we've to find this submatrix starting idx
        // then r/3 = 2, c = 3/3 = 1 then we'll decompress by multiplying
        // r*3 = 6, c*3 = 3 so the starting idx for submatrix of (7,3) is (6,3)

        r = (r / 3) * 3;
        c = (c / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[r + i][c + j] - '0' == num)
                    return false;
            }
        }
        return true;
    }

    // lc139 word break will give TLE here because it's a DP problem
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> wd = new HashSet<>();
        for (String str : wordDict)
            wd.add(str);
        return wordBreakSolver(s, "", wd);
    }

    public boolean wordBreakSolver(String str, String asf, HashSet<String> wordDict) {
        // eg-> str = ilovemangoandsamsung
        // wordDict = ["i", "ilove", "love", "man", "mango","go","and","sam", "sung",
        // "samsung"]
        int n = str.length();
        if (n == 0)
            return true;

        for (int len = 1; len <= n; len++) {
            String smallStr = str.substring(0, len);

            if (wordDict.contains(smallStr)) { // contains ilove so we'll make a call
                if (wordBreakSolver(str.substring(len), asf + smallStr + " ", wordDict)) {
                    // call for str.substring(len) ilovemangoandsamsung -> mangoandsamsung
                    return true;
                }
            }
        }
        return false;
    }

    // cryptAirthematic eg -> send+more = money
    // https://see.stanford.edu/materials/icspacs106b/H19-RecBacktrackExamples.pdf
    static String str1 = "send", str2 = "more", str3 = "money";
    static boolean[] isNumUsed = new boolean[10];
    static int[] mapping = new int[26];

    public static void crypto() {
        String str = str1 + str2 + str3;
        int[] freq = new int[26];
        for (int i = 0; i < str.length(); i++) {
            freq[str.charAt(i) - 'a']++;
        }

        str = "";
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0)
                str += (char) (i + 'a');
        }

        if (str.length() > 10)
            return;

        System.out.println(crypto(str, 0));
    }

    private static int stringToNumber(String str) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            res = res * 10 + mapping[ch - 'a'];
        }

        return res;
    }

    private static boolean isValidMapping() {
        if (mapping[str1.charAt(0) - 'a'] == 0 || mapping[str2.charAt(0) - 'a'] == 0
                || mapping[str3.charAt(0) - 'a'] == 0)
            return false;

        int num1 = stringToNumber(str1);
        int num2 = stringToNumber(str2);
        int num3 = stringToNumber(str3);

        return num1 + num2 == num3;
    }

    public static int crypto(String str, int idx) {
        if (idx == str.length()) {
            if (isValidMapping()) {
                for (int i = 0; i < str.length(); i++) {
                    char ch = str.charAt(i);
                    System.out.print(ch + "=" + mapping[ch - 'a'] + ", ");
                }
                System.out.println();
                return 1;
            }

            return 0;
        }

        int count = 0;
        char ch = str.charAt(idx);
        for (int num = 0; num <= 9; num++) {
            if (!isNumUsed[num]) {
                isNumUsed[num] = true;
                mapping[ch - 'a'] = num;

                count += crypto(str, idx + 1);

                mapping[ch - 'a'] = 0;
                isNumUsed[num] = false;
            }

        }

        return count;
    }

    public static void main(String[] args) {
        // int count = queenCombination(6, 3, 0, 0, "");
        // System.out.println("count : " + count);
        // System.out.println("------------------------------------------");
        // int countSub = queenCombination_sub(5, 3, 0, 0,"");
        // System.out.println("count : "+ countSub);
        // int countPer = queenPermutation(new boolean[6], 3, 0, 0, "");
        // System.out.println("count : " + countPer);
        // int countPersub = queenPermutation_sub(new boolean[5], 3, 0, 0, "");
        // System.out.println("count : " + countPersub);
        // int count = queenPermutation2d(new boolean[4][4], 4, 0, "");
        // System.out.println("count : " + count);
        // int count = queenCombination2d(new boolean[4][4], 4, 0, "");
        // System.out.println("count : " + count);
        // int count = nQueens01Combination(new boolean[4][4], 4, 0, "");
        // System.out.println("count : " + count);
        crypto();
    }

    public static void display1D(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
    }

    public static void display2d(int[][] arr) {
        for (int[] ele : arr) {
            display1D(ele);
            System.out.println();
        }
    }
}