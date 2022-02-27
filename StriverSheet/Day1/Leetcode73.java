package Day1;

public class Leetcode73 {
    // BruteForce-> if i found zero at some point i'll mark it as -1 at them time
    // otherwise it'll mess up with the process
    // and then in second iteration i'll just find for -1 and change it 0
    // but this apprach will not work if values inside array can be -ve
    public static void setZeroes(int[][] arr) {
        int n = arr.length, m = arr[0].length;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                int val = arr[r][c];
                if (val == 0) {
                    makeRowColZero(arr, r, c);
                }
                continue;

            }
        }
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                arr[r][c] = arr[r][c] == -1 ? 0 : arr[r][c];
                // System.out.print(arr[r][c] + " ");
            }
            // System.out.println();
        }

    }

    public static void makeRowColZero(int[][] arr, int r, int c) {
        int n = arr.length, m = arr[0].length;

        for (int col = 0; col < m; col++) {
            arr[r][col] = arr[r][col] == 0 ? 0 : -1;
        }
        for (int row = 0; row < n; row++) {
            arr[row][c] = arr[row][c] == 0 ? 0 : -1;
        }
    }

    // for this optimized approach
    // i'll maintain 2 different arrays if i find 0 at some point i'll store -1 at
    // that idx in row, col arrays
    // then in second iteration i'll just find where there is -1 at row *OR* col
    // array i'll make that idx 0 in 2d array
    public static void setZeroesOpti(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        int[] row = new int[n];
        int[] col = new int[m];

        checkAndMakeZero(arr, row, col);
    }

    public static void checkAndMakeZero(int[][] arr, int[] row, int[] col) {
        int n = arr.length, m = arr[0].length;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (arr[r][c] == 0) {
                    row[r] = -1;
                    col[c] = -1;
                }
            }
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (row[r] == -1 || col[c] == -1) {
                    arr[r][c] = 0;
                }
            }
        }

    }

    // Optimized approach of optimized approach
    //  in this we just haven't made those two array rather we've made changes in the same array
    // by assigning a boolean type value at the point 
    public static void setZeroesOptiOpti(int[][] arr){
        int col0 = 1, rows = arr.length, cols = arr[0].length;

        for (int i = 0; i < rows; i++) {
            if (arr[i][0] == 0) col0 = 0;
            for (int j = 1; j < cols; j++)
                if (arr[i][j] == 0)
                    arr[i][0] = arr[0][j] = 0;
        }

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 1; j--)
                if (arr[i][0] == 0 || arr[0][j] == 0)
                    arr[i][j] = 0;
            if (col0 == 0) arr[i][0] = 0;
        }
        display2d(arr);
    }

    public static void display1d(int[] arr){
        int n = arr.length;
        for(int i = 0; i < n; i++){
            System.out.print(arr[i] + " ");
        }
    }

    public static void display2d(int[][] arr){
        int n = arr.length;
        for(int i=0; i < n; i++){
            display1d(arr[i]);
            System.out.println();
        }
    }

    public static void main(String args[]) {
        int[][] arr = { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };
        setZeroesOptiOpti(arr);
    }
}
