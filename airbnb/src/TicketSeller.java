import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.TreeMap;
import java.util.Map;

public class TicketSeller {


    public static long maximumAmount(List<Integer> a, long k) {
        // Write your code here
        TreeMap<Integer, Integer> pq = new TreeMap<>(Collections.reverseOrder());
        for (int element : a) {
            if (pq.containsKey(element)) {
                pq.put(element, pq.get(element) + 1);
            } else {
                pq.put(element, 1);
            }
        }

        long i = k;
        long profit = 0;

        while (i > 0 && !pq.isEmpty()) {
            Map.Entry<Integer, Integer> first = pq.pollFirstEntry();

            int price = first.getKey();
            int count = first.getValue();
            if (i - count > 0) {
                profit += ((long) price * (long) count);
                i -= count;
                price--;
                if (pq.containsKey(price)) {
                    pq.put(price, pq.get(price) + count);
                } else {
                    pq.put(price, count);
                }


            } else {
                //i <= count
                profit += (price * i);
                i -= count;
            }

        }



//        PriorityQueue<Integer> pq = new PriorityQueue<>(a.size(), Collections.reverseOrder());
//        pq.addAll(a);
//        int i = 0;
//        long profit = 0;
//
//        while (i < k && pq.peek() > 0) {
//
//            int temp = pq.poll();
//            profit += temp;
//            temp--;
//
//            System.out.println("i: " + i);
//            System.out.println("temp: " + temp);
//            if (temp > 0) {
//                pq.add(temp);
//            }
//            i++;
//        }

        return profit;
    }


    public static void main(String[] args) {
        TicketSeller res = new TicketSeller();
        int[] array = {2,
                8,
                4,
                10,
                6};
        List<Integer> aList = new ArrayList<>();
        //aList.addAll(Arrays.asList(array));
        for (int i : array) {
            aList.add(i);
        }
        System.out.println(res.maximumAmount(aList, 20));
    }
}
