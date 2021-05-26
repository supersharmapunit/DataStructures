// improt java.util.*;

public class basics{
    public static void main(String[] args){
        // findFreq();
        // spanOfArray();
        // findEleInArray();
        printbar();
    }

    public static void printbar(){
        int[] arr = {1,2,5,3,7};
        printBarchart(arr);
    }

    public static void printBarchart(int[] arr) {
        int n = arr.length;
        int max = findMax(arr);

        for (int floor = max ; floor >= 1; floor--){
            for (int i = 0; i < n; i++){
                if (arr[i] >= floor) System.out.print(" *");
                else System.out.print("  ");
            }
            System.out.println();
        }
    }

    public static int findMax(int[] arr) {
        int maxn = Integer.MIN_VALUE;
        for (int ele : arr){
            if (ele >= maxn) maxn = ele;
        }

        return maxn;
    }

    public static int findMin(int[] arr) {
        int minn = Integer.MAX_VALUE;
        for (int ele : arr){
            if (ele >= minn) minn = ele;
        }

        return minn;
    }

    public static void findEleInArray(){
        int[] arr = {1,2,3,4,4,4,8,8,8,8,8,8,8,8,8,8,8,5};
        int fn = 32;

        System.out.println(findEle(arr,fn));
    }

    public static int findEle(int[] arr, int fn){
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == fn) return i;
        }
        return -1;
    }
    
    public static void findFreq(){
        int[] arr = {1,2,3,4,4,4,8,8,8,8,8,8,8,8,8,8,8,5};
        int n = 3;

        System.out.println(digitFreq(arr,n));
    }

    public static int digitFreq(int[] arr , int n){
        int count = 0;
        for (int ele : arr){
            if (ele == n) count++;
        }
        return count;
    }

    public static void spanOfArray() {
        int[] arr = {15,30,40,4,11,9};
        System.out.println(spanCal(arr));
    }

    public static int spanCal(int[] arr){
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        for (int ele : arr){
            if (ele >= max) max = ele;
            if(ele <= min) min = ele;
        }

        return max - min;
    }
}