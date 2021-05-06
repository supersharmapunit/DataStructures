public class queue{
    private class ListNode {
        int data = 0;
        ListNode next = null;

        public ListNode(int data){
            this.data = data;

        }
    }

    private ListNode head = null;
    private ListNode tail = null;
    private int noOfElements = 0;

    private void addLast(ListNode node){
        if (this.head == this.tail){
            this.head = null;
            this.tail =  null;
        } else {
            this.tail.next = node;
            this.tail = node;
        }
    }

    private ListNode removeFirst(){
        ListNode rn = this.head;
        if (this.head == this.tail){
            this.head = null;
            this.tail =  null;
        } else {
            this.head = rn.next;

        }
        rn.next = null;
    }

    public int size() {
        return this.noOfElements;
    }

    public boolean isEmpty() {
        return this.noOfElements == 0;
    }

    protected void QueueEmptyException() throws Exception {
        if (this.noOfElements == 0) throw new Exception("QueueIsEmpty");
    }

    public void push(int data){
        ListNode node = new ListNode(data);
        addLast(node);
        this.noOfElements++;
    }

    protected int top_(){
        
        return  this.head.data;
    }

    public int top(){
        QueueEmptyException();
        return top_();
    }

    protected int  pop_() {
        ListNode node = removeFirst();
        this.noOfElements--;
        return node.data;
    }

    public int pop() throws Exception {
        QueueEmptyException();
        return pop_();
    }

}