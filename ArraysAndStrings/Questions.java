import java.util.Arrays;

public class Questions {

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void reverse(int[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i++, j--);
        }
    }

    public void kRotate(int[] arr, int k) {
        // complexity -> o(n)

        if (arr.length <= 1 || k == arr.length)
            return;

        int n = arr.length;

        if (k < 0)
            k += n; // making it +ve if -ve

        k %= n; // absolute value

        // above 2 steps can also be written as
        // k = (k % n + n) % n

        reverse(arr, 0, n - k - 1);
        reverse(arr, n - k, n - 1);
        reverse(arr, 0, n - 1);
    }

    // segregate +ve's and -ve's in o(n) which should be inplace with TC O(n)
    public void segrePandN(int[] arr){
        if (arr.length <= 1) return;

        int n = arr.length, pt = -1, itr = 0;

        while(itr < n){
            if (arr[itr] < 0){
                swap(arr, ++pt, itr);
            }
            itr++;
        }
    }

    // segregate 0's and 1's in o(n) which should be inplace with TC O(n)
    public void segre0and1(int[] arr) {
        if (arr.length <= 1) return;

        int n = arr.length, pt = -1, itr = 0;

        while(itr < n){
            if (arr[itr] == 0){
                swap(arr, ++pt, itr);
            }
            itr++;
        }
    }

    // segregate 0's, 1's and 2's in o(n) which should be inplace with TC O(n)
    public void segre0_1and2(int[] arr) {
        if (arr.length <= 1) return;

        int n = arr.length, ptr1 = -1, itr = 0, ptr2 = n - 1;

        while(itr < n){
            if (arr[itr] == 0){
                swap(arr, ++ptr1, itr++);
            } else if (arr[itr] == 2){
                swap(arr, ptr2--, itr);
            } else {
                itr++;
            }
        }
    }

    // Leetcode 75
    public void sortColors(int[] arr) {
        if (arr.length <= 1) return;

        segre0_1and2(arr);
    }
    

    // gfg = https://practice.geeksforgeeks.org/problems/max-sum-in-the-configuration/1
    public int max_sum(int arr[], int n)
    {
	int sum = 0, sumWithIdx = 0;

    for (int i = 0 ; i < n; i++){
        sum += arr[i];
        sumWithIdx += arr[i] * i;
    }

    int maxSum = sumWithIdx;

    for(int i = 1; i < n; i++){
        sumWithIdx = sumWithIdx - sum + arr[i - 1] * n;
        maxSum = Math.max(maxSum, sumWithIdx);
    }

    return maxSum;
    }

    // Leetcode 1
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length;i++){
            for (int j = i+1; j < nums.length;j++){
                int sum = nums[i] + nums[j];
                if (sum == target){
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }

    // Leetcode 11 
    public int maxArea(int[] arr){
        int n = arr.length, maxArea = 0, i = 0, j = n-i;
        
        while(i < j){
            int width = j - 1;
            maxArea = arr[i] < arr[j] ? Math.max(maxArea, arr[i++] * width) : Math.max(maxArea, arr[j--] * width);
        }
        
        return maxArea;
    }

    
    // Leetcode 3
    public int lengthOfLongestSubstring(String str) {
        if (str.length() <= 1) return str.length();
        int si = 0, ei = 0, count = 0, len = 0, n = str.length();
        int[] freq = new int[128];

        while(ei < n){
            if (freq[str.charAt(ei++)]++ > 0) count++;

            while(count > 0){
                if (freq[str.charAt(si++)]-- > 1) count--;
            }

            len = Math.max(len, ei - si);
        }

        return len;
    }

    
    // leetcode 3 modified to give the substring instead of its length
     public String stringOfLongestSubstring(String str){
        if (str.length() <= 1) return str;
        int si = 0, ei = 0, count = 0, len = 0, n = str.length(), gsi = 0, gei = 0;
        int[] freq = new int[128];

        while(ei < n){
            if (freq[str.charAt(ei++)]++ > 0) count++;

            while(count > 0){
                if (freq[str.charAt(si++)]-- > 1) count--;
            }

            if (ei - si > len){
                len = ei - si;
                gei = ei;
                gsi = si;
            }
        }
        return str.substring(gsi,gei);
     }

    
    //  Leetcode 159
    public int lengthOfLongestSubstringWithAtmostTwoDistinctCharcter(String str) {
        if (str.length() <= 2) return str.length();

        int si = 0, ei = 0, n = str.length(), count = 0, len = 0;
        int[] freq = new int[128];

        while(ei < n){
            if (freq[str.charAt(ei++)]++ == 0) count++;

            while(count > 2){
                if (freq[str.charAt(si++)]-- == 1) count--;
            }

            len = Math.max(len, ei - si);
        }
        return len;

    }

    // Leetcode 159 modified to give the substring instead of its length
    public String stringOfLongestSubstringWithAtmostTwoDistinctCharcter(String str){
        if (str.length() <= 1) return str;
        int si = 0, ei = 0, count = 0, len = 0, n = str.length(), gsi = 0, gei = 0;
        int[] freq = new int[128];

        while(ei < n){
            if (freq[str.charAt(ei++)]++ == 0) count++;

            while(count > 2){
                if (freq[str.charAt(si++)]-- == 1) count--;
            }

            if (ei - si > len){
                len = ei - si;
                gei = ei;
                gsi = si;
            }
        }
        return str.substring(gsi,gei);
     }

     

}