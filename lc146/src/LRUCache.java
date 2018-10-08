import java.util.Map;
import java.util.HashMap;

public class LRUCache {
    private int capacity;

    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            prev = null;
            next = null;
        }
    }

    private Map<Integer, Node> mappp = new HashMap<>();
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = null;
        tail = null;
    }

    private void delete(Node cur) {
        Node pre = cur.prev;
        Node ne = cur.next;
        if (ne != null && pre != null) {
            pre.next = ne;
            ne.prev = pre;
        } else if (pre != null) {
            //when cur is tail
            tail = pre;
            pre.next = null;
        } else if (ne == null){
            tail = null;
            head = null;
        } else {
            ne.prev = null;
            head = ne;
        }
        capacity++;
    }

    private void addToHead(Node cur) {
        Node origH = head;
        cur.next = origH;
        cur.prev = null;
        if (origH != null) {
            origH.prev = cur;
        }
        if (tail == null) {
            tail = cur;
        }
        head = cur;
        capacity--;
    }

    public int get(int key) {
        Node cur = mappp.get(key);
        //the key-value pair exists, update value, put it at head
        if (cur != null) {
            int result = cur.value;
            //delete it from original position in Double Linked List
            delete(cur);
            //add to head
            addToHead(cur);

            return result;
        } else {
            return -1;
        }
        //the key-value pair doesn't exist, return -1

    }



    public void put(int key, int value) {
        Node newNode = new Node(key, value);
        Node cur = mappp.get(key);
        if (cur != null) {
            //key value pair has already exist
            delete(cur);

        } else {
            //key value pair hasn't exist
            if (capacity == 0) {
                //delete tail from both orig position and mappp
                Node temp = tail;
                mappp.remove(temp.key);
                delete(temp);

                //add new node to head

            }

        }
        //add new node to head
        addToHead(newNode);
        mappp.put(key, newNode);

    }

    public static void main(String[] str) {
        LRUCache solution = new LRUCache(10);
        System.out.println(solution.capacity);
        solution.put(10, 13);
        System.out.println(solution.capacity);
        solution.put(3, 17);
        System.out.println(solution.capacity);
        solution.put(6, 11);
        solution.put(10, 5);
        solution.put(9, 10);
        solution.get(13);
        solution.put(2, 19);
        solution.get(2);
        solution.get(3);
        solution.put(5, 25);
        solution.get(8);
        solution.put(9, 22);
        solution.put(5, 5);
        solution.put(1, 30);
        solution.get(11);
        solution.put(9, 12);
        solution.get(7);
        solution.get(5);
        solution.get(8);
        solution.get(9);
        solution.put(4, 30);
        solution.put(9, 3);
        solution.get(9);
        solution.get(10);
        solution.put(6, 14);
        solution.put(3, 1);
        solution.get(3);
        solution.put(10, 11);
        solution.get(8);
        solution.put(2, 14);
        solution.get(1);
        solution.get(5);
        solution.get(4);
        solution.put(11, 4);
        solution.put(12, 24);
        solution.put(5, 18);
        System.out.println(solution.capacity);
        solution.get(13);
        solution.put(7, 23);
        System.out.println(solution.capacity);
        //System.out.println(solution.get(9));
        System.out.println(solution.get(8));
        System.out.println(solution.get(12));
        solution.put(3, 27);
        solution.put(2, 12);

        System.out.println(solution.get(5));
        solution.put(2, 9);
        solution.put(13, 4);
        System.out.println(solution.get(6));
    }



}
