
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}


public class DetectLinkedCycle {

    public static ListNode findCycleStart(ListNode head) {
        ListNode walker = head;
        ListNode runner = head;
        while (walker.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (walker == runner) {
                ListNode countFromHead = head;
                while (countFromHead != walker) {
                    countFromHead = countFromHead.next;
                    walker = walker.next;
                }
                return countFromHead;
            }
        }
        return null;

    }

    public static void main(String[] args) {
        ListNode h1 = new ListNode(0);
        ListNode h2 = new ListNode(1);
        ListNode h3 = new ListNode(2);
        h1.next = h2;
        h2.next = h3;
        h3.next = h2;
        ListNode cycleStartPoint = findCycleStart(h1);
        System.out.println(cycleStartPoint.val);
    }
}
