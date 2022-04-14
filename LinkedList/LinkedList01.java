// key to solve LL question is to solve it on paper first and do as question tell's don't predict a move ahead just do the dry run

public class LinkedList01 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // LC876
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // on leetcode it consider 2nd mid as mid node in even LL but if we have to find
    // 1st this is the method
    // other than leetcode we'll consider middle a node before the one
    // we find on leetcode(only in even cases so just the condition will change in
    // while loop) we'll use this in most of the cases
    public ListNode middleNode_01(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // LC206
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null, curr = head;

        while (curr != null) {
            ListNode forward = curr.next; // just like temp
            curr.next = prev;
            prev = curr;
            curr = forward;
        }

        return prev;
    }

    // LC234
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        
//         1.we'll find the middleNode
//         2.then we'll reverse the list from the middle
//         3.traverse in both the list if something unequal found then false otherwise true
        
        ListNode mid = middleNode_01(head), newHead = mid.next;
        mid.next = null; // breaking the list so that we have two seperate lists while we do the work
        newHead = reverseList(newHead);
        
        ListNode l1 = head, l2 = newHead;
        boolean res = true;
        while(l2 != null){ // we haven't wrote l1 != null because in the case of breaking of odd sized
        // LL l2 will be the ptr that will qpproach null first and in even case it will approach same
            if(l1.val != l2.val){
                res = false;
                break;
            }
            
            l1 = l1.next;
            l2 = l2.next;
        }
        
//         re-attaching the two LL so it become the original list
        newHead = reverseList(newHead);
        mid.next = newHead;
        
        return res;
    }

    // LC143
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return;
        
        ListNode mid = middleNode_01(head), nHead = mid.next;
        mid.next = null;
        nHead = reverseList(nHead);
        
        ListNode c1 = head, c2 = nHead;
        
        
        while(c2 != null){
            ListNode fwd1 = c1.next, fwd2 = c2.next;
            
            c1.next = c2;
            c2.next = fwd1;
            
            c1 = fwd1;
            c2 = fwd2;
        }
    }

    public int size(ListNode head){
        if(head == null) return 0;
        int count = 0;
        ListNode temp = head;
        while(temp != null) {
            temp = temp.next;
             count++;
        }
        return count;
    }

    public void swap(ListNode l1, ListNode l2){
        ListNode temp = new ListNode();
        temp.val = l1.val;
        temp.next = l1.next;

        l1.val = l2.val;
        l1.next = l2.next;

        l2.val = temp.val;
        l2.next = temp.next;
    }

    public ListNode getNodeAtIdx(ListNode node, int idx){
        ListNode temp = node;
        while(idx-- >= 0){
            temp = temp.next;
        }
        return temp;
    }

    // LC21
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null || list2 == null) return list1 == null ? list2 : list1;

        ListNode dummy = new ListNode(-1), prev = dummy, ptr1 = list1, ptr2 = list2;

        while (ptr1 != null && ptr2 != null){
            if(ptr1.val <= ptr2.val){
                prev.next = ptr1;
                ptr1 = ptr1.next;
            } else {
                prev.next = ptr2;
                ptr2 = ptr2.next;
            }

            prev = prev.next;
        }

        return dummy.next;
    }

}