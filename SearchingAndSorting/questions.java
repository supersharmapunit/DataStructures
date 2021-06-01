public class Questions{
    
    public int binarySearch(int[] arr, int data){
        int n = arr.length,  si = 0, ei = n -1;

        while(si <= ei){
            int mid = (ei - si)/2;

            if (arr[mid] == data) return mid;
            else if(arr[mid] < data) si = mid + 1;
            else  ei = mid - 1; // arr[mid] > data
        }

        return -1;
    }

    public int firstIndex(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return (si <= n -1 && arr[si] == data) ? si : -1;
    }

    public int firstIndex01(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data) {
                if (mid - 1 >= 0 && arr[mid - 1] == data)
                    ei = mid - 1;
                else
                    return mid;
            } else if (arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return -1;
    }


    public int lastIndex(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;
            while (si <= ei) {
                int mid = (si + ei) / 2;
                if (arr[mid] <= data)
                    si = mid + 1;
                else
                    ei = mid - 1;
            }
            si--;
    
            return (si >= 0 && arr[si] == data) ? si : -1;
    }
        

    public int lastIndex01(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;
            while (si <= ei) {
                int mid = (si + ei) / 2;
                if (arr[mid] == data) {
                    if (mid + 1 < n && arr[mid + 1] == data)
                        si = mid + 1;
                    else
                        return mid;
                } else if (arr[mid] < data)
                    si = mid + 1;
                else
                    ei = mid - 1;
            }
    
            return -1;
        }

    // Leetcode 34 - https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
    public int[] searchRange(int[] nums, int target) {
        return new int[] {firstIndex(nums,target),lastIndex(nums,target)};
    }

    // saerch Or Insert With Unique Elements - Leetcode 35 - https://leetcode.com/problems/search-insert-position/
    public int saerchOrInsertWithUniqueElements(int[] arr, int data){
        int n = arr.length, si = 0, ei = n -1;

        while(si <= ei){
            int mid = (si+ei)/2;

            if (arr[mid] < data) si = mid + 1;
            else ei = mid - 1;
        }
        
        return si;
    }

    // search or insert with duplicate elements
    public int searchOrInsertWithDuplicateElements(int[] arr, int data){
        int n = arr.length, si = 0, ei = n - 1;
        
        while(si <= ei){
            int mid = (si + ei)/2;

            if (arr[mid] < data) si = mid + 1;
            else ei = mid - 1;
        }

        return (si - 1 >= 0 && arr[si - 1] == data) ? si - 1 : si;
    }

    // nearest element
    public int nearestElement(int[] arr, int data){
        if (arr.length == 0) return 0;

        int n = arr.length;
        if (arr[0] >= data || arr[n-1] <= data) return arr[0] >= data ? 0 : arr[n - 1];

        int ei = n - 1, si = 0;
        while(si <= ei){
            int mid = (si+ei)/2;

            if (arr[mid] <= data) si = mid + 1;
            else ei = mid - 1;
        }

        return data - arr[ei] < arr[si] - data ? arr[ei] : arr[si];
        // return data - arr[ei] < arr[si] - data ? ei : si; if index is needed
    }

    // Leetcode 74 - https://leetcode.com/problems/search-a-2d-matrix/
    public boolean searchMatrix(int[][] matrix, int target) {
     int n = matrix.length, m = matrix[0].length, si = 0, ei = (n*m) - 1;
     
     while(si <= ei){
         int mid = (si+ei)/2;

         int r = mid/m, c = mid %m;

         if (matrix[r][c] == target) return true;
         else if (matrix[r][c] < target) si = mid + 1;
         else ei = mid - 1;
     }

     return false;
    }

    // Leetcode 33 - https://leetcode.com/problems/search-in-rotated-sorted-array/
    public int search(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;
        
        while(si <= ei){
            int mid = (si+ei)/2;
            
            if (arr[mid] == data) return mid;
            else if(arr[si] <= arr[mid]){
                if (arr[si] <= data && data < arr[mid]) ei = mid - 1;
                else si = mid +1;
            } else {
                if (arr[mid] < data && data <= arr[ei]) si = mid +1;
                else ei = mid -1;
            }
        }
        return -1;
    }

    // Leetcode 81 - https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
    public boolean searchWithDuplicates(int[] nums, int target) {
        int n = arr.length, si = 0, ei = n - 1;

        while(si <= ei){
            int mid = (si+ei)/2;

            if (arr[mid] == data || arr[si] == data) return true;
            else if(arr[si] < arr[mid]){
                if (arr[si] <= data && data < arr[mid]) ei = mid - 1;
                else si = mid +1;
            } else if (arr[mid] < arr[ei]) {
                if (arr[mid] < data && data <= arr[ei]) si = mid +1;
                else ei = mid -1;
            } else 
                si++;

        }
        return false;
    }

    // Leetcode 167 - https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
    public int[] twoSum(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n -1;
        
        while(si < ei){
            int sum = arr[si] + arr[ei];
            
            if (sum == data) return new int[] {si +1, ei +1};
            else if (sum < data) si++;
            else ei--;
        }
        
        return new int[0];
    }

    //  all possible pairs in two sum
    public List<List<Integer>> twoSum(int[] arr, int target, int si, int ei) {
        List<List<Integer>> ans = new ArrayList<>();
        while (si < ei) {
            int sum = arr[si] + arr[ei];
            if (sum == target) {
                ArrayList<Integer> smallAns = new ArrayList<>();
                smallAns.add(arr[si]);
                smallAns.add(arr[ei]);
                ans.add(smallAns);
                si++;
                ei--;
                // to iterate through duplicate elements/ to find new unique element
                while (si < ei && arr[si] == arr[si - 1])
                    si++;
                while (si < ei && arr[ei] == arr[ei + 1])
                    ei--;
            } else if (sum < target)
                si++;
            else
                ei--;
        }

        return ans;
    }

    // count Inversions - https://practice.geeksforgeeks.org/problems/inversion-of-array-1587115620/1
    public static long inversionCount(long arr[], long N) {
        if (N == 0)
            return 0;

        long[] sortedArray = new long[(int)N]; // type casted n because you can't create an array of length in long
        return inversionCount(arr, 0, (int) N - 1, sortedArray);
    }

    public static long inversionCount(long[] arr, int si, int ei, long[] sortedArray) {
        if (si >= ei)
            return 0;

        int mid = (si + ei) / 2;
        long ICL = inversionCount(arr, si, mid, sortedArray); // IC : Inversion Count, L = left , R = Right
        long ICR = inversionCount(arr, mid + 1, ei, sortedArray);
        
        // return total inversion count
        return (ICL + ICR + InversionAcrossArray(arr, si, ei, mid, sortedArray));
    }

    public static long InversionAcrossArray(long[] arr, int l, int r, int mid, long[] sortedArray) {
        int lsi = l, lei = mid; // l : left, R : right, si = starting IDX, ei = ending IDX
        int rsi = mid + 1, rei = r;

        // long[] sortedArray = new long[r - l + 1]; // instead of creating again and again we'll send this as argument
        int k = 0;
        long count = 0; // to count inversions


        while (lsi <= lei && rsi <= rei) {
            if (arr[lsi] > arr[rsi]) {
                count += (lei - lsi + 1);
                sortedArray[k++] = arr[rsi++];
            } else
                sortedArray[k++] = arr[lsi++];
        }
        
        // if items are still remaining in any of the arrays
        while (lsi <= lei)
            sortedArray[k++] = arr[lsi++];
        while (rsi <= rei)
            sortedArray[k++] = arr[rsi++];
        // order should be strictly LSI then RSI for remaining items
        
        // copy elements sorted array --> arr
        k = 0;
        for (int i = l; i <= r; i++)
            arr[i] = sortedArray[k++];
        // another way of writing samee loop
        // for (k = 0; k < sortedArray.length; k++)
        // arr[k + l] = sortedArray[k];

        return count;
    }

}