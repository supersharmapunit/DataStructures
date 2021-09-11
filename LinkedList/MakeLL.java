public class MakeLL {
    public static void main(String args[]) {

    }

    public static class Node {
        int data;
        Node next;
    }

    public static class LinkedList {
        Node head;
        Node tail;
        int size;

        void addLast(int data) {
            Node n = new Node();
            n.data = data;

            if (size == 0) {
                head = tail = n;
            } else {
                tail.next = n;
                tail = n;
            }
            size++;
        }

        int size() {
            return this.size;
        }

        void display(){
            Node temp = head;
            while(temp.next == null){
                System.out.print(node.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }

        void removeFirst() {
            if (size == 0)
                System.out.println("List is empty");
            else if (size == 1) {
                head = tail = null;
                size--;
            } else {
                head = head.next;
                size--;
            }
        }

        int getFirst() {
            if (size == 0) {
                System.out.println("List is empty");
                return -1;
            }
            return this.head.data;
        }

        int getLast() {
            if (size == 0) {
                System.out.println("List is empty");
                return -1;
            }
            return this.tail.data;
        }

        int getAt(int idx) {
            if (idx < 0 || idx >= this.size) {
                System.out.println("Invalid Arguments");
                return -1;
            } else if (size == 0) {
                System.out.println("List is empty");
                return -1;
            } else if (idx == 0)
                return getFirst();
            else if (idx == this.size - 1)
                return getLast();
            else {
                Node temp = this.head;
                for (int i = 0; i < idx; i++) {
                    temp = temp.next;
                }
                return temp.data;
            }
        }

        void addFirst(int val) {
            if (size == 0)
                addLast(val);
            else {
                Node n = new Node();
                n.data = val;
                n.next = head;
                head = n;
                size++;
            }
        }

        void addAt(int idx, int val) {
            if (idx < 0 || idx > this.size) {
                System.out.println("Invalid arguments");
            } else if (idx == 0)
                addFirst(val);
            else if (idx == this.size)
                addLast(val);
            else {
                Node tempm1 = head; // to traverse till idx - 1

                for (int i = 0; i < idx - 1; i++) {
                    tempm1 = tempm1.next;
                }
                Node n = new Node();
                n.data = val;
                n.next = tempm1.next; // new node.next -> old node.next
                tempm1.next = n; // old node.next -> our node

                size++;
            }
        }

        void removeLast() {
            if (size == 0)
                System.out.println("List is empty");
            else if (size == 1) {
                head = tail = null;
                size--;
            } else {
                Node temp = head;
                for (int i = 0; i < this.size - 2; i++) {
                    temp = temp.next;
                }
                temp.next = null;
                tail = temp;
                size--;
            }
        }

        void removeAt(int idx) {
            if (idx < 0 || idx >= this.size)
                System.out.println("Invalid arguments");
            else if (idx == 0)
                removeFirst();
            else if (idx == this.size - 1)
                removeLast();
            else {
                size--;
                Node temp = head;
                for (int i = 0; i < idx - 1; i++) {
                    temp = temp.next;
                }
                temp.next = temp.next.next;

            }
        }

        void reverseDI() { // Data iteratively
            int si = 0, ei = this.size - 1;
            while (si < ei) {
                Node siNode = getNodeAt(si);
                Node eiNode = getNodeAt(ei);

                // swap
                int temp = siNode.data;
                siNode.data = eiNode.data;
                eiNode.data = temp;

                si++;
                ei--;
            }
        }

        void reversePI() { // Pointer iterative
            Node curr = head;
            Node prev = null;

            while (curr != null) {
                Node temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            Node t = head;
            head = tail;
            tail = t;
        }

        public Node getNodeAt(int idx) {
            Node temp = head;
            for (int i = 0; i < idx; i++) {
                temp = temp.next;
            }
            return temp;
        }

        // Linked List to Stack Adapter
        public LLToStackAdapter() {
            list = new LinkedList<>();
          }

        int sizeStack() {
            return list.size();
        }

        void push(int val) {
            list.addFirst(val);
        }

        int pop() {
            return list.removeFirst();
        }

        int top() {
            return list.getFirst();
        }

        // Linked List to Queue Adapter
        public LLToQueueAdapter() {
            list = new LinkedList<>();
          }

        int sizeQueue() {
            return list.size();
        }

        void add(int val) {
            list.addFirst(val);
        }

        int remove() {
            if (list.size() == 0) {
                System.out.println("Queue underflow");
                return -1;
            }
            return list.removeLast();
        }

        int peek() {
            if (list.size() == 0) {
                System.out.println("Queue underflow");
                return -1;
            }
            return list.getLast();
        }

        // kth node from end of LL
        int kthFromLast(int k) {
            Node fast = head;
            Node slow = head;

            for (int i = 0; i < k; i++) {
                fast = fast.next;
            }

            while (fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }

            return slow.data;
        }

        // mid of LL
        int mid() {
            Node slow = head;
            Node fast = head;

            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }

            return slow.data;
        }

        // Merge two sorted LL
        LinkedList mergeTwoSortedLists(LinkedList l1, LinkedList l2) {
            LinkedList ans = new LinkedList();
            Node ptr1 = l1.head;
            Node ptr2 = l2.head;

            while (ptr1 != null && ptr2 != null) {
                if (ptr1.data <= ptr2.data) {
                    ans.addLast(ptr1.data);
                    ptr1 = ptr1.next;
                } else {
                    ans.addLast(ptr2.data);
                    ptr2 = ptr2.next;
                }
            }

            if (ptr1 != null) {
                while (ptr1 != null) {
                    ans.addLast(ptr1.data);
                    ptr1 = ptr1.next;
                }
            }

            if (ptr2 != null) {
                while (ptr2 != null) {
                    ans.addLast(ptr2.data);
                    ptr2 = ptr2.next;
                }
            }

            return ans;
        }

        // Merge Sort LL
        Node midNode(Node head, Node tail) {
            Node f = head;
            Node s = head;

            while (f != tail && f.next != tail) {
                f = f.next.next;
                s = s.next;
            }

            return s;
        }

        LinkedList mergeSort(Node head, Node tail) {
            if (head == tail) {
                LinkedList base = new LinkedList();
                base.addLast(head.data);
                return base;
            }

            Node mid = midNode(head, tail);

            LinkedList fsh = mergeSort(head, mid); // first sorted half
            LinkedList ssh = mergeSort(mid.next, tail); // second sorted half

            LinkedList ans = mergeTwoSortedLists(fsh, ssh);

            return ans;

        }

        // Remove duplicates
        void removeDuplicates1() { // through abstraction method 1
            LinkedList ans = new LinkedList();
            ans.addLast(head.data);
            this.removeFirst();

            while (this.size > 0) {
                if (ans.getLast() == this.getFirst()) {
                    this.removeFirst();
                } else {
                    ans.addLast(head.data);
                    this.removeFirst();
                }
            }

            this.head = ans.head;
            this.tail = ans.tail;
            this.size = ans.size;
        }

        void removeDuplicates2() { // by making changes in the same LL
            Node prev = head, curr = prev.next;

            while (curr != null) {
                if (prev.data != curr.data) {
                    prev.next = curr;
                    prev = curr;
                    curr = curr.next;
                } else {
                    curr = curr.next;
                    this.size--;
                }
            }

            tail = prev;
            prev.next = null;
        }

        // Odd-Even LL
        void oddEven() {
            Node ptr = head;
            LinkedList even = new LinkedList();
            LinkedList odd = new LinkedList();

            while (ptr != null) {
                if (ptr.data % 2 == 0)
                    even.addLast(ptr.data);
                else
                    odd.addLast(ptr.data);

                ptr = ptr.next;
            }

            if (even.size() == 0) {
                // all odds
                this.head = odd.head;
                this.tail = odd.tail;
                this.size = odd.size;
            } else if (odd.size() == 0) {
                // all are evens
                this.head = even.head;
                this.tail = even.tail;
                this.size = even.size;
            } else {
                odd.tail.next = even.head;
                this.head = odd.head;
                this.tail = even.tail;
                this.size = odd.size() + even.size();
            }
        }

        // K-Reverse LL
        void kReverse(int k) {
            LinkedList ans = new LinkedList();
            LinkedList l1 = new LinkedList();

            while (this.size() != 0) {

                if (this.size() >= k) {
                    for (int i = 1; i <= k; i++) {
                        l1.addFirst(this.getFirst());
                        this.removeFirst();
                    }
                } else {
                    l1.addLast(this.getFirst());
                    this.removeFirst();
                }

                if (ans.size() == 0) {
                    ans = l1;
                    l1 = new LinkedList();
                } else {
                    ans.tail.next = l1.head;
                    ans.tail = l1.tail;
                    ans.size += l1.size();
                    l1 = new LinkedList();

                }
            }

            this.head = ans.head;
            this.tail = ans.tail;
            this.size = ans.size;
        }
    }

}