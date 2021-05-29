public class QuickSort{
    
    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp; 
    }

    public static int segregateDataInRange(int[] arr, int si, int ei, int pivot) {
        swap(arr, pivot, ei);

        int p = si -1, itr = si;
        
        while(itr <= ei){
            if (arr[itr] <= arr[ei]) 
                swap(arr, ++p, itr);
            itr++;    
        }

        return p;
    }

    public static void quickSort(int[] arr, int si, int ei) {
        if (si > ei) return;

        int pivot = ei; //done on the bases of ending IDX
        // int pivot = si; if want to do on the basis of starting IDX
        // int pivot = (si+ei)/2; mid i.e it can work from any IDX in the gievn array
        int pIdx = segregateDataInRange(arr, si, ei, pivot);

        quickSort(arr, si, pIdx - 1);
        quickSort(arr, pIdx+1, ei);
    }
    
    public static void main(String[] args) {
        int[] arr = {-12,2,7,34,23,0,1,-1,-50,16,23,7,4,2,3};
        System.out.println("Before sorting:");
        for (int ele : arr){
            System.out.print(ele + " ");
        }
        
        quickSort(arr, 0, arr.length - 1);

        System.out.println();
        System.out.println("After sorting:");
        for (int ele : arr){
            System.out.print(ele + " ");
        }
    }
}