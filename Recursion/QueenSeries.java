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
        for(int i = bno; i < n*m; i++){
            int r = i/m, c = i%m;
            if(!boxes[r][c]){
                boxes[r][c] = true;
                count += queenCombination2d(boxes, tnq-1, i+1, ans+"("+r+","+c+") ");
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
        if(qno == tnq){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = 0; i < boxes.length; i++){
            if(!boxes[i]){
                boxes[i] = true;
                count += queenPermutation(boxes, tnq, 0, qno+1, ans+" q"+qno+" b"+i);
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
        for(int i = bno; i < n*m; i++){
            int r = i/m, c = i%m;
            if(isSafe(boxes, r, c)){
                boxes[r][c] = true;
                count += nQueens01Combination(boxes, tnq-1, i+1, ans+"("+r+","+c+") ");
                boxes[r][c] = false;
            }
        }
        return count;
    }

    public static boolean isSafe(boolean[][] boxes, int r, int c){
        for(int row = r-1; row >= 0; row--){
            if(boxes[row][c]) return false;
        }
        for(int col = c-1; col >= 0; col--){
            if(boxes[r][col]) return false;
        }
        for(int col = c-1, row = r-1; col >= 0 && row >= 0; col--, r--){
            if(boxes[row][col]) return false;
        }
        return true;
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
        int count = nQueens01Combination(new boolean[4][4], 4, 0, "");
        System.out.println("count : " + count);
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