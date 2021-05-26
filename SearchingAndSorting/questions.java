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

    // saerch Or Insert With Unique Elements
    public int saerchOrInsertWithUniqueElements(int[] arr, int data){
        int n = arr.length, si = 0, ei = n -1;

        while(si <= ei){
            int mid = (si+ei)/2;

            if (arr[mid] < data) si = mid + 1;
            else ei = mid - 1;
        }
        
        return si;
    }
}