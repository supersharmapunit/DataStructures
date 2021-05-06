import java.util.ArrayDequeue;

public class StackUsingQueue{
    private ArrayDequeue<Integer> stack = new ArrayDequeue<>();
    private ArrayDequeue<Integer> helper = new ArrayDequeue<>();
    
    
    /** Push element x onto stack. */
     public void push(int x) {
        stack.addFirst(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if (stack.size() == 0) return 0;
        return stack.removeLast();
    }
    
    /** Get the top element. */
    public int top() {
        return stack.getLast();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return if(stack.size() == 0);
    }
}