import java.util.LinkedList;
public class StackUsingLL {
    private LinkedList<Integer> ll = new LinkedList();

    public void push(int data){
        ll.addFirst(data);
    }

    public int top(){
        return ll.getFirst();
    }

    public int pop(){
        return ll.removeFirst();
    }
}