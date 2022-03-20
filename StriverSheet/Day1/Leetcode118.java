package Day1;

import java.util.*;

public class Leetcode118 {
    public static void main(String[] args) {
        int n = 5;
        System.out.println(generate(n));
    }
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> row,pre = null;

        for(int i = 0; i < numRows; i++){
            row = new ArrayList<Integer>();

            for(int j = 0; j <= i; j++){
                if(j == 0 || j == i) row.add(1); //first and last idx is always 1
                else {
                    row.add(pre.get(j-1) + pre.get(j)); // previous rows j + j-1
                }
            }
            pre = row;
            res.add(row);
        }
        return res;
        
    }
}
