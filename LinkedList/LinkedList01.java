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
        if (head == null || head.next == null)
            return true;

        // 1.we'll find the middleNode
        // 2.then we'll reverse the list from the middle
        // 3.traverse in both the list if something unequal found then false otherwise
        // true

        ListNode mid = middleNode_01(head), newHead = mid.next;
        mid.next = null; // breaking the list so that we have two seperate lists while we do the work
        newHead = reverseList(newHead);

        ListNode l1 = head, l2 = newHead;
        boolean res = true;
        while (l2 != null) { // we haven't wrote l1 != null because in the case of breaking of odd sized
            // LL l2 will be the ptr that will qpproach null first and in even case it will
            // approach same
            if (l1.val != l2.val) {
                res = false;
                break;
            }

            l1 = l1.next;
            l2 = l2.next;
        }

        // re-attaching the two LL so it become the original list
        newHead = reverseList(newHead);
        mid.next = newHead;

        return res;
    }

    // LC143
    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode mid = middleNode_01(head), nHead = mid.next;
        mid.next = null;
        nHead = reverseList(nHead);

        ListNode c1 = head, c2 = nHead;

        while (c2 != null) {
            ListNode fwd1 = c1.next, fwd2 = c2.next;

            c1.next = c2;
            c2.next = fwd1;

            c1 = fwd1;
            c2 = fwd2;
        }
    }

    public int size(ListNode head) {
        if (head == null)
            return 0;
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            count++;
        }
        return count;
    }

    public void swap(ListNode l1, ListNode l2) {
        ListNode temp = new ListNode();
        temp.val = l1.val;
        temp.next = l1.next;

        l1.val = l2.val;
        l1.next = l2.next;

        l2.val = temp.val;
        l2.next = temp.next;
    }

    public ListNode getNodeAtIdx(ListNode node, int idx) {
        ListNode temp = node;
        while (idx-- >= 0) {
            temp = temp.next;
        }
        return temp;
    }

    // LC21
    public ListNode mergeTwoSortedLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null)
            return list1 == null ? list2 : list1;

        ListNode dummy = new ListNode(-1), prev = dummy, ptr1 = list1, ptr2 = list2;

        while (ptr1 != null && ptr2 != null) {
            if (ptr1.val <= ptr2.val) {
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

    // unfold a List opposite of reorder list
    public void unfold(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode h1 = head, h2 = head.next, c1 = h1, c2 = h2;

        while (c2 != null && c2.next != null) {
            ListNode f = c2.next;

            c1.next = f;
            c2.next = f.next;

            c1 = c1.next;
            c2 = c2.next;
        }

        h2 = reverseList(h2);
        c1.next = h2;
    }

    // Leetcode 19
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n > 32)
            return null;

        ListNode slow = head, fast = head;
        while (n-- > 0)
            fast = fast.next;

        if (fast == null) {
            ListNode removeNode = head;
            head = head.next;
            removeNode.next = null;
            return head;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        ListNode removeNode = slow.next;
        slow.next = removeNode.next;
        removeNode.next = null;
        return head;
    }

    // Leetcode 2 -> list given is already reversed
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return (l1 == null) ? l2 : l1;

        ListNode dummy = new ListNode(-1), prev = dummy, ptr1 = l1, ptr2 = l2;
        int carry = 0;
        while (ptr1 != null || ptr2 != null || carry != 0) {
            int sum = carry + (ptr1 != null ? ptr1.val : 0) + (ptr2 != null ? ptr2.val : 0);
            carry = sum / 10;
            prev.next = new ListNode(sum % 10);
            prev = prev.next;

            if (ptr1 != null)
                ptr1 = ptr1.next;
            if (ptr2 != null)
                ptr2 = ptr2.next;
        }

        ListNode head = dummy.next;
        dummy.next = null;
        return head;
    }

    // Leetcode 445 addTwoNumber2 -> list given is not in reverse order
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return (l1 == null) ? l2 : l1;

        l1 = reverseList(l1);
        l2 = reverseList(l2);

        ListNode dummy = new ListNode(-1), prev = dummy, ptr1 = l1, ptr2 = l2;
        int carry = 0;
        while (ptr1 != null || ptr2 != null || carry != 0) {
            int sum = carry + (ptr1 != null ? ptr1.val : 0) + (ptr2 != null ? ptr2.val : 0);
            carry = sum / 10;
            prev.next = new ListNode(sum % 10);
            prev = prev.next;

            if (ptr1 != null)
                ptr1 = ptr1.next;
            if (ptr2 != null)
                ptr2 = ptr2.next;
        }

        l1 = reverseList(l1);
        l2 = reverseList(l2);

        ListNode head = dummy.next;
        dummy.next = null;
        head = reverseList(head);
        return head;
    }

    // gfg subtraction between two LL
    public int isBiggerList(ListNode l1, ListNode l2) {
        int len1 = size(l1), len2 = size(l2);

        if (len1 == len2) {
            ListNode ptr1 = l1, ptr2 = l2;
            while (ptr1 != null) {
                if (ptr1.val != ptr2.val)
                    return ptr1.val - ptr2.val;
                ptr1 = ptr1.next;
                ptr2 = ptr2.next;
            }
        }

        return len1 - len2; // +ve l1 is big otherwise opposite
    }

    public ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        if (isBiggerList(l1, l2) < 0) {
            ListNode temp = l1;
            l1 = l2;
            l2 = temp;
        }

        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode dummy = new ListNode(-1), prev = dummy, ptr1 = l1, ptr2 = l2;

        int borrow = 0;
        while (ptr1 != null || ptr2 != null) {
            int val = borrow + (ptr1 != null ? ptr1.val : 0) - (ptr2 != null ? ptr2.val : 0);

            // updating borrow
            if (val < 0) {
                val += 10;
                borrow = -1;
            } else {
                borrow = 0;
            }

            // adding it to the ans list
            prev.next = new ListNode(val);
            prev = prev.next;

            if (ptr1 != null)
                ptr1 = ptr1.next;
            if (ptr2 != null)
                ptr2 = ptr2.next;
        }

        l1 = reverseList(l1);
        l2 = reverseList(l2);

        ListNode head = dummy.next;
        dummy.next = null;

        head = reverseList(head);
        ptr1 = head;

        while (ptr1.next != null) {
            if (ptr1.val != 0) {
                break;
            }
            ptr1 = ptr1.next;
        }

        head = ptr1;
        return head;
    }

    // remove duplicate-
    // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/linked-list/remove-duplicate-from-sorted-linkedlist/ojquestion
    public ListNode removeDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(-1), prev = dummy, ptr1 = head;

        while (ptr1 != null) {
            if (ptr1.val != prev.val) {
                prev.next = new ListNode(ptr1.val);
                prev = prev.next;
            }
            ptr1 = ptr1.next;
        }

        head = dummy.next;
        dummy.next = null;
        return head;
    }

    // remove all duplicates
    // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/linked-list/remove-all-duplicates-from-sorted-linkedlist/ojquestion
    public ListNode removeAllDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(-1), prev = dummy, curr = head.next;

        prev.next = head;
        // prev.next = head beacuse we're considering it as potential ele

        while (curr != null) {
            boolean isSequence = false;
            while (curr != null && prev.next.val == curr.val) {
                curr = curr.next;
                isSequence = true;
            }

            if (isSequence) {
                prev.next = curr; // now at the end of prev seq. we're making curr ele as potential unique ele
            } else {
                // element is unique
                prev = prev.next;
            }

            if (curr != null)
                curr = curr.next;
        }

        return dummy.next;
    }

}