


public class Solution {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * You are given two non-empty linked lists representing two non-negative integers.
     * The digits are stored in reverse order and each of their nodes contain a single digit.
     * Add the two numbers and return it as a linked list.
     *
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     *
     * Example:
     *
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     * Explanation: 342 + 465 = 807.
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = addTwoNumbers(l1, l2, carry);
        return head;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        int value = carry;
        if (l1 != null) {
            value += l1.val;
        }
        if (l2 != null) {
            value += l2.val;
        }
        ListNode result = new ListNode(value % 10);
        if (l1 != null || l2 != null) {
            result.next = addTwoNumbers(l1 == null ? null : l1.next, l2 == null ? null : l2.next, value / 10);
        }
        return result;


    }

    public static void main(String[] args) {
        ListNode num1 = new ListNode(3);
        ListNode num11 = new ListNode(4);
        num1.next = num11;

        ListNode num2 = new ListNode(9);
        ListNode num22 = new ListNode(5);
        num2.next = num22;
        //43 + 59 = 102
        Solution sol = new Solution();
        ListNode result = sol.addTwoNumbers(num1, num2);
        while (result.next != null) {
            System.out.println(result.val);
            result = result.next;
        }
        System.out.println(result.val);

    }
}
