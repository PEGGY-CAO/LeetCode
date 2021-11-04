
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class DetectLinkedListCycle {

    public static boolean detectCycle(ListNode head) {
        ListNode walker = head;
        if (walker.next == null) return false;
        ListNode runner = head;
        while (walker.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (walker == runner) return true;
        }

        return false;

    }

    public static void main(String[] args) {
        ListNode h1 = new ListNode(0);
        ListNode h2 = new ListNode(1);
        ListNode h3 = new ListNode(2);
        h1.next = h2;
        h2.next = h3;
        h3.next = h2;
        System.out.println(detectCycle(h1));
    }
}
