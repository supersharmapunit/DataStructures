public class queue{
    private int[] arr;
    private int noOfElements;
    private int maxCapacity;
    private int front;
    private int back;
    
    protected void initialize(int size){
        int n = size;
        this.arr = new int[n];
        this.noOfElements = 0;
        this.maxCapacity = n;
        this.front = 0;
        this.back = 0;
    }

    public queue(){
        initialize(15);
    }

    public queue(int size){
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

    protected void QueueEmptyException() throws Exception {
        if (this.noOfElements == 0) throw new Exception("QueueIsEmpty");
    }
    protected void QueueOverflowException() throws Exception {
        if (this.noOfElements == this.maxCapacity) throw new Exception("QueueOverflow");
    }

    public void display(){
        for (int i = 0; i < this.maxCapacity; i++){
            int idx = (this.front + i) % this.maxCapacity;
            System.out.print(this.arr[idx] + " ");
        }
    }

    protected void push_(int data) {
        this.arr[this.back] = data;
        this.back = (++this.back % this.maxCapacity);
        this.noOfElements++;
    }

    public void push(int data) throws Exception {
        QueueOverflowException();
        push_(data);
    }

    protected int front_() {
        return this.arr[this.front];
    }
    
    public int front() throws Exception {
        QueueEmptyException();
        return front_();
    }

    protected int  pop_() {
        int rv = this.arr[this.front];
        this.arr[this.front] = 0;
        this.front = (++this.front % this.maxCapacity);
        this.noOfElements--;
        
        return rv;
    }

    public int pop() throws Exception {
        QueueEmptyException();
        return pop_();
    }
}