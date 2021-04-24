import java.util.ArrayDeque;
import java.util.Stack;

public class SnQLeet{
    
     // N : Next, G = greater, S : Smaller, L : Left, R : Right
     public static void NGOR(int[] arr, int[] ans) {
        int n = arr.length;
        Arrays.fill(ans, n); // Java : Arrays.fill(ans,n);

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (st.size() != 0 && arr[st.peek()] < arr[i]) {
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }

    public static void NGOL(int[] arr, int[] ans) {
        int n = arr.length;
        Arrays.fill(ans, -1); // Java : Arrays.fill(ans,-1);

        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (st.size() != 0 && arr[st.peek()] < arr[i]) {
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }

    public static void NSOR(int[] arr, int[] ans) {
        int n = arr.length;
        Arrays.fill(ans, n); // Java : Arrays.fill(ans,n);

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (st.size() != 0 && arr[st.peek()] > arr[i]) {
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }

    public static void NSOL(int[] arr, int[] ans) {
        int n = arr.length;
        Arrays.fill(ans, -1); // Java : Arrays.fill(ans,-1);

        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (st.size() != 0 && arr[st.peek()] > arr[i]) {
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

    // Leetcode 20
    public boolean isValid(String s) {
        if (str.length == 0) return true;
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(ch == '(' || ch == '{' || ch == '['){
                st.push(ch);
            } else {
                if(st.size() == 0){
                    return false;
                }  else if (st.peek() == '(' && ch != ')'){
                    return false;
                } else if (st.peek() == '{' && ch != '}'){
                    return false;
                } else if (st.peek() == '[' && ch != ']'){
                    return false;
                } else {
                    st.pop();
                }
            }
        }

        return st.isEmpty();
    }

    // Leetcode 946
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> st = new Stack<>();
        int i = 0, n = popped.length;
        for (int ele : pushed){
            st.push(ele);
            while (st.size() != 0 && st.peek() == popped[i]){
                st.pop();
                i++;
            }
        }
        return st.isEmpty();
    }

    // Leetcode 1249
    public String minRemoveToMakeValid(String s) {
        ArrayDeque<Integer> st = new ArrayDeque<>();
        int n = s.length();
        for (int i = 0; i < n; i++){
            char ch = s.charAt(i);
            if (ch == '('){
                st.addFirst(i);
            } else if (ch == ')'){
                if (st.size() != 0 && s.charAt(st.getFirst()) == '('){
                    st.removeFirst();
                } else {
                    st.addFirst(i);
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++){
            if(st.size() != 0 && st.getLast() == i){
                st.removeLast();
                continue;
            }

            ans.append(s.charAt(i));
        }

        return ans.toString();

    }

    // Leetcode 32
    public int longestValidParentheses(String s) {
        
    }

    // Leetcode 735
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        for (int ele : asteroids){
            if (ele > 0){
                st.push(ele);
                continue;
            } 

            while (st.size() != 0 && st.peek() > 0 && st.peek() < -ele) st.pop();

            if (st.size() != 0 && st.peek() == -ele) st.pop();
            else if(st.size() == 0 || st.peek() < 0) st.push(ele);
            else{

            }
        }

        int s = st.size();
        int[] ans = new int[s];

        while (s != 0){
            ans[--s] = st.pop();
        }

        return ans;
    }

    // Leetcode 84
    public int largestRectangleArea01(int[] heights) {
        int n = heights.length;
        int[] nsol = new int[n];
        int[] nsor = new int[n];
        NSOL(heights, nsol);
        NSOR(heights, nsor);

        int maxArea = 0;
        for (int i = 0; i < n; i++){
            int h = heights[i];
            int w = nsor[i] - nsol[i] - 1;
            
            maxArea = Math.max(maxArea, h * w);
        }

        return maxArea;
        
    }

    public int largestRectangleArea02(int[] heights) {
        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int maxArea = 0;

        int i = 0;
        while(i < n){
            while(st.peek() != -1 && heights[st.peek()] >= heights[i]){
                int idx = st.peek();
                st.pop();

                int h = heights[idx];
                int w = i - st.peek() - 1;

                maxArea = Math.max(maxArea, h * w);
            }
                st.push(i++);
        }

        while (st.peek() != -1) {
            int idx = st.peek();
            st.pop();

            int h = heights[idx];
            int w = n - st.peek() - 1;
            maxArea = Math.max(maxArea, h * w);
        }

        return maxArea;
        
    }

    // Leetcode 85
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int maxRec = 0;
        int height[] = new int[m];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                char ch = matrix[i][j];
                height[j] = ch == '1' ? height[j] + 1 : 0;
            }

            maxRec = Math.max(maxRec, largestRectangleArea(height));
        }

        return maxRec;
    }

    // Leetcode 316
    public String removeDuplicateLetters(String s) {
        StringBuilder st = new StringBuilder();
        int n = s.length();
        int[] freq = new int[26];
        boolean visited[] = new boolean[26];

        for (int i = 0; i < n; i++) freq[s.charAt(i) - 'a']++;

        for (int i = 0 ; i < n; i++){
            char ch = s.charAt(i);
            freq[ch - 'a']--;

            if (visited[ch - 'a']) continue;

            while (st.length() != 0 && st.charAt(st.length() - 1) > ch && freq[st.charAt(st.length() - 1) - 'a'] > 0){
                char rch = st.charAt(st.length() - 1);
                visited[rch - 'a'] = false;
                st.deleteCharAt(st.length() - 1); // st.pop
            }

            visited[ch - 'a'] = true;
            st.append(ch); //st.push
        }

        return st.toString();
    }

    // Leetcode 42
    public int trap(int[] height) {
        
    }

    // Leetcode925
    public boolean isLongPressedName(String name, String typed) {
        if (name.equals(typed)) return true;
        if (name.length() > typed.length()) return false;
        
        int i = 0;
        int j = 0;
        
        while (j < typed.length()){
            if (i < name.length() && name.charAt(i) == typed.charAt(j)){
                i++;
            } else if(j == 0 || typed.charAt(j) != typed.charAt(j - 1)){
                return false;
            }
            j++;
        }
        
        return i == name.length();
    }
    
    public static void main(String abc[]){
        // ngor();
    }

}