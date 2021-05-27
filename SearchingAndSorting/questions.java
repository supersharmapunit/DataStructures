public class questions{
    
    public int binarySearch(int[] arr, int data){
        int n = arr.length, int si = 0, ei = n -1;

        while(si <= ei){
            int mid = (ei - si)/2;

            if (arr[mid] == data) return mid;
            else if(arr[mid] < data) ei = mid - 1;
            else  si = mid + 1; // arr[mid] > data
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

    // Leetcode 34
    public int[] searchRange(int[] nums, int target) {
        return new int[] {firstIndex(nums,target),lastIndex(nums,target)};
    }

    // saerch Or Insert With Unique Elements - Leetcode 35
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

    // Leetcode 74
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
}