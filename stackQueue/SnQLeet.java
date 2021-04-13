import java.util.Stack;

public class SnQLeet{
    
    public static void ngor(int arr[], int ans[]){
        int n = arr.length;
        Arrays.fill(ans, n);

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++){
            while(st.size() != 0 && arr[st.peek()] < arr[i]){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }

    public static void ngol(int arr[], int ans[]){
        int n = arr.length;
        Arrays.fill(ans, -1);

        Stack<Integer> st = new Stack<>();
        for (int i = n-1; i >= 0; i--){
            while(st.size() != 0 && arr[st.peek()] < arr[i]){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }

    public static void nsor(int arr[], int ans[]){
        int n = arr.length;
        Arrays.fill(ans, n);

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++){
            while(st.size() != 0 && arr[st.peek()] > arr[i]){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }

    public static void nsol(int arr[], int ans[]){
        int n = arr.length;
        Arrays.fill(ans, -1);

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++){
            while(st.size() != 0 && arr[st.peek()] < arr[i]){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }

    // Leetcode 503
    public int[] nextGreaterElements(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < 2*n; i++){
            while(st.size() != 0 && arr[st.peek()] < arr[i%n]){
                ans[st.pop()] = arr[i%n];
            }
            if (i < n) st.push(i);
        }
        return ans;
    }

    // https://practice.geeksforgeeks.org/problems/stock-span-problem-1587115621/1
    public static int[] calculateSpan(int price[], int n)
    {
        
    }
    
    public static void main(String abc[]){
        ngor();
    }

}