import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class MinAmplitude {

    public static int solution(int[] A) {

        //System.err.println("Tip: Use System.err.println() to write debug messages on the output tab.");
        //corner case
        if (A.length <= 4) {
            return 0;
        }

        PriorityQueue<Integer> maxQ = new PriorityQueue<>();
        PriorityQueue<Integer> minQ = new PriorityQueue<>(Collections.reverseOrder());

        for (int eachInteger : A) {
            maxQ.add(eachInteger);
            if (maxQ.size() > 4) {
                maxQ.poll();
            }
            minQ.add(eachInteger);
            if (minQ.size() > 4) {
                minQ.poll();
            }
        }

        List<Integer> minList = new ArrayList<>();
        while (minQ.size() > 0) {
            minList.add(0, minQ.poll());
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            int diff = maxQ.poll() - minList.get(i);
            ans = diff < ans ? diff : ans;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] A = {9, 2, 3};
        System.out.println(solution(A));

        int[] A1 = {9, 2, 3, -5, 8};
        System.out.println(solution(A1));
    }
}
