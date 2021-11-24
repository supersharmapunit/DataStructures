public class Question1 {
    public static void main(String[] args) {

    }

    // [level -> items, options -> choices] TC->O(n^r)
    // n boxes me i items in which i < n so some places will be empty
    public static void permutations(int[] boxes, int ci, int ti) {
        if (ci > ti) {
            for (int i = 0; i < boxes.length; i++) {
                System.out.print(boxes[i]);
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < boxes.length; i++) {
            if (boxes[i] == 0) {
                boxes[i] = ci;
                permutations(boxes, ci + 1, ti);
                boxes[i] = 0;
            }
        }
    }

    // yes or no calls [level -> boxes, option -> items] TC->O((r+1)^n)
    public static void permutations2(int cb, int tb, int[] items, int ssf, int ts, String asf) {
        if (cb > tb) {
            if (ssf == ts) {
                System.out.println(asf);
            }
            return;
        }

        // yes call -> multiple
        for (int i = 0; i < items.length; i++) {
            if (items[i] == 0) {
                items[i] = cb;
                permutations2(cb + 1, tb, items, ssf + 1, ts, asf + (i + 1));
                items[i] = 0;
            }
        }

        // no call-> single
        permutations2(cb + 1, tb, items, ssf, ts, asf + "0");
    }

    // with repetition (nPr = nCr x r!) [levels -> boxes] TC->O(2^n)
    // given a number of boxes (nboxes) and number of identical items (ritems)
    // required to place the items in those boxes and print all such configurations
    // possible
    // nCr -> with repetition of items unlike permutation, cb-> curr box, tb->total
    // box
    // ssf-> selection so far, ts-> total selection
    public static void combinations(int cb, int tb, int ssf, int ts, String asf) {
        if (cb > tb) {
            if (ssf == ts) {
                System.out.println(asf);
            }
            return;
        }

        combinations(cb + 1, tb, ssf + 1, ts, asf + "i");
        combinations(cb + 1, tb, ssf, ts, asf + "-");
    }

    // without repetition (nCr = nPr/r!) [level -> items] TC->O(n^r)
    // given a number of boxes (nboxes) and number of identical items (ritems).
    // required to place the items in those boxes and print all such configurations
    // possible
    public static void combinations2(int[] boxes, int ci, int ti, int lbui) {
        if (ci > ti) {
            for (int i = 0; i < boxes.length; i++) {
                if (boxes[i] != 0)
                    System.out.print("i");
                else
                    System.out.print("-");
            }
            System.out.println();
            return;
        }

        for (int i = lbui + 1; i < boxes.length; i++) {
            boxes[i] = ci;
            combinations2(boxes, ci + 1, ti, i);
            boxes[i] = 0;
        }
    }

    /*
     * given a number n, representing the size of a n * n chess board. required to
     * calculate and print the permutations in which n queens can be placed on the n
     * * n chess-board.(2d as 2d) Queen chooses level -> queen
     */
    public static void queensPermutationsQueenChooses(int qpsf, int tq, int[][] chess) {
        if (qpsf == tq) {
            for (int i = 0; i < chess.length; i++) {
                for (int j = 0; j < chess.length; j++) {
                    if (chess[i][j] != 0)
                        System.out.print("q" + chess[i][j] + "\t");
                    else
                        System.out.print("-\t");
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[0].length; j++) {
                if (chess[i][j] == 0) {
                    chess[i][j] = qpsf + 1;
                    queensPermutationsQueenChooses(qpsf + 1, tq, chess);
                    chess[i][j] = 0;
                }
            }
        }
    }

    /*
     * given a number n, representing the size of a n * n chess board. required to
     * calculate&print the combinations in which n queens can be placed on the n * n
     * chess-board. (2d as 2d)-> box chooses(yes/no calls)
     */
    public static void queensCombinationsBoxChooses(int qpsf, int tq, int row, int col, String asf) {
        if (col == tq) {
            row++;
            col = 0;
            asf += "\n";
        }

        if (row == tq) {
            if (qpsf == tq)
                System.out.println(asf);
            return;
        }

        queensCombinationsBoxChooses(qpsf + 1, tq, row, col + 1, asf + "q"); // yes
        queensCombinationsBoxChooses(qpsf, tq, row, col + 1, asf + "-"); // no
    }

}