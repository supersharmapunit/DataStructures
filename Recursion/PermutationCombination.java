import java.lang.reflect.Array;
import java.util.*;
// Leetcode article - shorturl.at/gxCWY
// CN article - https://www.codingninjas.com/blog/2021/09/18/subsequence-vs-substring/
// permutation -> arrangements
// Draw tree diagram for better understanding


// Any code can be used as template to solve any permutation combination question
public class PermutationCombination {

    // through coin Change problem
    // Type1 coin can be used infinite times with arrangements
    public static int permutationInfi(int[] coins, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int coin : coins) {
            if (tar - coin >= 0)
                count += permutationInfi(coins, tar - coin, ans + coin);
        }
        return count;
    }

    // Type 2
    // combination infinite coins but no arrangements
    public static int combinationInfi(int[] coins, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;

        for (int i = idx; i < coins.length; i++) {
            int val = coins[i];
            if (tar - val >= 0) {
                count += combinationInfi(coins, tar - val, i, ans + val);
            }
        }

        return count;
    }

    // Type 3
    // combination single coin no arrangements
    public static int combiantionSingleCoin(int[] coins, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;

        for (int i = idx; i < coins.length; i++) {
            int val = coins[i];
            if (tar - val >= 0) {
                count += combiantionSingleCoin(coins, tar - val, i + 1, ans + val);
            }
        }

        return count;
    }

    // Type 3
    // permutation single coin
    public static int permutationSingleCoin(int[] coins, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;

        for (int i = 0; i < coins.length; i++) {
            if (coins[i] >= 0 && tar - coins[i] >= 0) {
                int val = coins[i];
                coins[i] = -val;
                count += permutationSingleCoin(coins, tar - val, ans + val);
                coins[i] = val;
            }
        }

        return count;
    }

    // ==================================================================================================================================
    
    // Type 5
    public static int combinationSingleCoin_sub(int[] coins, int tar, int idx, String ans) {
        if (tar == 0 || idx == coins.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;

        if (tar - coins[idx] >= 0) {
            count += combinationSingleCoin_sub(coins, tar - coins[idx], idx + 1, ans + coins[idx]);
        }
        count += combinationSingleCoin_sub(coins, tar, idx + 1, ans);

        return count;
    }

    // Type 6
    public static int combinationInfiCoins_sub(int[] coins, int tar, int idx, String ans) {
        if (tar == 0 || idx == coins.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;

        if (tar - coins[idx] >= 0) {
            count += combinationInfiCoins_sub(coins, tar - coins[idx], idx, ans + coins[idx]);
        }
        count += combinationInfiCoins_sub(coins, tar, idx + 1, ans);

        return count;
    }

    // Type 7
    public static int permutationInfiCoins_sub(int[] coins, int tar, int idx, String ans){
        if (tar == 0 || idx == coins.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;

        if (tar - coins[idx] >= 0) {
            count += permutationInfiCoins_sub(coins, tar - coins[idx], 0, ans + coins[idx]);
        }
        count += permutationInfiCoins_sub(coins, tar, idx + 1, ans);

        return count;
    }

    // Type 8
    public static int permutationSingleCoin_sub(int[] coins, int tar, int idx, String ans){
        if(tar == 0 || idx >= coins.length){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;

        if(coins[idx] > 0 && tar - coins[idx] >= 0){
            int val = coins[idx];
            coins[idx] = -val;
            count += permutationSingleCoin_sub(coins, tar-val, 0, ans+val);
            coins[idx] = val;
        }
        count += permutationSingleCoin_sub(coins, tar, idx+1, ans);
        
        return count;
    }



    public static void subsequences(int[] coins, int tar) {
        int count = combinationSingleCoin_sub(coins, tar, 0, "");
        System.out.println("Combination Single coin count : " + count);
        int count1 = combinationInfiCoins_sub(coins, tar, 0, "");
        System.out.println("Combination Infi Coins count : " + count1);
        int percount = permutationInfiCoins_sub(coins, tar, 0, "");
        System.out.println("permutation Infi Coins count : " + percount);
        int percount1 = permutationSingleCoin_sub(coins, tar, 0, "");
        System.out.println("permutation Single Coins count : " + percount1);
    }
    // ==================================================================================================================================

    public static void main(String[] args) {
        // int[] coins = { 2, 3, 5, 7 };
        // int tar = 10;
        // int count = permutationInfi(coins, tar, "");
        // int count = combinationSingleCoin_sub(coins, tar, 0, "");
        // System.out.println("count : " + count);
        // subsequences(coins, tar);
        int[] arr = {1,2,1};
        oneSubsequence(0, 0, new ArrayList<Integer>(), 2, arr, arr.length);
    }

    // how to print only one subsequence-> this is the technique
    public static boolean oneSubsequence(int idx, int ssf, ArrayList<Integer> ans, int tar, int arr[], int n) {
        if(idx == n){
            // condition satisfied
            if(ssf == tar){
                for(int ele : ans) System.out.print(ele+" ,");
                System.out.println();
                return true;
            }
            // condition not satisfied
            return false;
        }

        // if we pick the element
        ans.add(arr[idx]);
        ssf += arr[idx];
        if(oneSubsequence(idx+1, ssf, ans, tar, arr, n)) return true;
        
        // if we do not pick the element
        ans.remove(ans.size()-1);
        ssf -= arr[idx];
        if(oneSubsequence(idx+1, ssf, ans, tar, arr, n)) return true;

        return false;


    }

    // how to print subsequence with sum == k
    /*
    int f(){
        base case->
            return 1 (if condition satisfied)
            return 0 (if not)
        
        call left recursive subtree
        l = f();
        call right recursive subtree
        r = f();

        return l + r;
        
        
        what if there are n recurion calls then
        count = 0
        for(int i -> n) s += f();
        return count;

    }

    */
}