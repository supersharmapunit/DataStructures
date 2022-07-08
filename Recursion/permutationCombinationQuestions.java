import java.util.*;

public class permutationCombinationQuestions {
    // lc17
    private int letterCombinations(String digits, int idx, String[] codes, List<String> ans, String psf) {
        if (idx == digits.length()) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        int key = digits.charAt(idx) - '0';
        String keyPadVal = codes[key];
        for (int i = 0; i < keyPadVal.length(); i++) {
            count += letterCombinations(digits, idx + 1, codes, ans, psf + keyPadVal.charAt(i));
        }
        return count;
    }

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0)
            return new ArrayList<>();

        String[] codes = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        List<String> ans = new ArrayList<>();
        letterCombinations(digits, 0, codes, ans, "");

        return ans;
    }

    // lc 322
    public int coinChange(int[] coins, int tar) {
        if (tar == 0)
            return 0;

        int minCoins = (int) 1e9;
        for (int coin : coins) {
            if (tar - coin >= 0)
                minCoins = Math.min(minCoins, coinChange(coins, tar - coin));
        }
        return minCoins;
    }

    // lc 46 Permutations
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> asf = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        int count = permuteHelper(nums, ans, asf, 0);
        return ans;
    }

    private int permuteHelper(int[] nums, List<List<Integer>> ans, List<Integer> asf, int ElementsPicked) {
        int n = nums.length;
        if (ElementsPicked == n) {
            List<Integer> base = new ArrayList<>(asf);
            ans.add(base);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > -11) {
                int val = nums[i];
                asf.add(val);
                nums[i] = -11;
                count += permuteHelper(nums, ans, asf, ElementsPicked + 1);
                nums[i] = val;
                asf.remove(asf.size() - 1);
            }
        }
        return count;
    }

    // lc47 permutation with only unique elements
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();
        Arrays.sort(nums);
        int count = permute(nums, nums.length, smallAns, ans);
        return ans;

    }

    public int permute(int[] arr, int totalElements, List<Integer> smallAns, List<List<Integer>> ans) {
        if (totalElements == 0) {
            List<Integer> base = new ArrayList<>(smallAns);
            ans.add(base);
            return 1;
        }

        int count = 0, prev = -12; // to maintain uniqueness will maintain prev ptr
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > -11 && prev != arr[i]) {
                int val = arr[i];
                arr[i] = -11;
                smallAns.add(val);
                count += permute(arr, totalElements - 1, smallAns, ans);
                arr[i] = val;
                smallAns.remove(smallAns.size() - 1);
                prev = arr[i];
            }
        }
        return count;
    }

    // lc39 Combination Sum
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> asf = new ArrayList<>();
        int count = csHelp(candidates, target, asf, ans, 0);
        return ans;
    }

    private int csHelp(int[] candidates, int tar, List<Integer> asf, List<List<Integer>> ans, int idx) {
        if (tar == 0) {
            List<Integer> base = new ArrayList<>(asf);
            ans.add(base);
            return 1;
        }

        int count = 0, n = candidates.length;
        for (int i = idx; i < n; i++) {
            if (tar - candidates[i] >= 0) {
                asf.add(candidates[i]);
                count += csHelp(candidates, tar - candidates[i], asf, ans, i);
                asf.remove(asf.size() - 1);
            }
        }
        return count;
    }

    // lc40
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> asf = new ArrayList<>();
        int count = cs2Help(candidates, target, asf, ans, 0);
        return ans;
    }

    private int cs2Help(int[] candidates, int tar, List<Integer> asf, List<List<Integer>> ans, int idx) {
        if (tar == 0) {
            List<Integer> base = new ArrayList<>(asf);
            ans.add(base);
            return 1;
        }

        int count = 0, n = candidates.length, prev = -1;
        for (int i = idx; i < n; i++) {
            if (prev != candidates[i] && tar - candidates[i] >= 0) {
                asf.add(candidates[i]);
                count += cs2Help(candidates, tar - candidates[i], asf, ans, i + 1);
                asf.remove(asf.size() - 1);
                prev = candidates[i];
            }
        }
        return count;
    }
}