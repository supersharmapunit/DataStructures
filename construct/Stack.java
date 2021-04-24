public class stack {

    private int[] arr;
    private int tos;
    private int noOfElements;
    private int maxCapacity;

    protected void initialize(int size) {
        this.arr = new int[size];
        this.tos = -1;
        this.noOfElements = 0;
        this.maxCapacity = size;
    }

    public stack(){
        initialize(15);
    }

    public stack(int size){
        initialize(size);
    }
    
    public int size() {
        return this.noOfElements;
    }

    protected int Capacity(){
        return this.maxCapacity;
    }

    public boolean isEmpty() {
        return this.noOfElements == 0;
    }

    protected void StackEmptyException() throws Exception {
        if (this.noOfElements == 0) throw new Exception("StackIsEmpty");
    }
    protected void StackOverflowException() throws Exception {
        if (this.noOfElements == this.maxCapacity) throw new Exception("StackOverflow");
    }

    protected void push_(int data) {
        this.tos++;
        this.arr[this.tos] = data;
        this.noOfElements++;
    }

    public void push(int data) throws Exception {
        StackOverflowException();
        push_(data);
    }

    protected int top_() {
        return this.arr[this.tos];
    }

    public int top() throws Exception {
        StackEmptyException();
        return top_();
    }

    protected int  pop_() {
        int rv = this.arr[this.tos];
        this.arr[this.tos] = 0;
        this.tos--;
        this.noOfElements--;
        return rv;
    }

    public int pop() throws Exception {
        StackEmptyException();
        return pop_();
    }
}