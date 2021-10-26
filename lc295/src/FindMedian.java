import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

class MedianFinder{

    private Queue<Integer> small;
    private Queue<Integer> large;
    private boolean even = true;

    public MedianFinder() {
        small = new PriorityQueue<>(Collections.reverseOrder());
        large = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (even) {
            small.offer(num);
            large.offer(small.poll());
        } else {
            large.offer(num);
            small.offer(large.poll());
        }
        even = !even;
    }

    public double findMedian() {
        if (even) {
            return (small.peek() + large.peek()) / 2.0;
        } else {
            return large.peek();
        }
    }
}


public class FindMedian {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);    // arr = [1]
        medianFinder.addNum(2);    // arr = [1, 2]
        System.out.println(medianFinder.findMedian()); // return 1.5 (i.e., (1 + 2) / 2)
        medianFinder.addNum(3);    // arr[1, 2, 3]
        System.out.println(medianFinder.findMedian()); // return 2.0
    }
}
