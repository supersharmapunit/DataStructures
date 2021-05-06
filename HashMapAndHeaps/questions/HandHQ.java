import java.util.PriorityQueue;

public class HandHQ{
    
    public void downHeapify(int arr[], int pi, int li){
    int maxID = pi;
    }

    public void swap(int arr[], int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Leetcode 215 Kth Largest
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> que = new PriorityQueue<>();
        
        for (int ele : nums){
            que.add(ele);
            if (que.size() > k){
                que.remove();
            }
        }
        return que.peek();
    }

    // Leetcode 215 opti
    public int findKthLargestOpti(int[] nums, int k) {
        PriorityQueue<Integer> que = new PriorityQueue<>();
        
    }

    //  https://practice.geeksforgeeks.org/problems/kth-smallest-element5635/1
    public static int kthSmallest(int[] arr, int l, int r, int k) 
    { 
        //Your code here
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        
        for (int ele : arr){
            pq.add(ele);
            
            if (pq.size() > k) pq.remove();
        }
        
        return pq.peek();
    }

    // Leetcode 703
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int k = 0;
    public void KthLargest(int k, int[] nums) {
        this.k = k;
        for (int ele : nums){
            this.pq.add(ele);
            if (this.pq.size() > this.k) this.pq.remove();
        }
    }
    
    public int add(int val) {
        this.pq.add(val);
        if (this.pq.size() > this.k) this.pq.remove();
        
        return this.pq.peek();
    }





}