import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SumImbalamce {

    //Similar to leetcode 907
    public static long getTotalImbalance(List<Integer> weight) {
        // Write your code here

        long min = 0;
        long max = 0;

        int size = weight.size();
        int[] left = new int[size];
        int[] right = new int[size];
        int[] leftMin = new int[size];
        int[] rightMin = new int[size];
        Stack<int[]> s1 = new Stack<>(), s2 = new Stack<>(), s3 = new Stack<>(), s4 = new Stack<>();

        for (int i = 0; i < size; i++) {
            int count = 1;
            while(!s1.isEmpty() && s1.peek()[0] > weight.get(i)) {
                count += s1.pop()[1];
            }
            s1.push(new int[]{weight.get(i), count});
            left[i] = count;
        }

        for (int i = 0; i < size; i++) {
            int count = 1;
            while(!s3.isEmpty() && s3.peek()[0] < weight.get(i)) {
                count += s3.pop()[1];
            }
            s3.push(new int[]{weight.get(i), count});
            leftMin[i] = count;
        }
        for (int i = size - 1; i >= 0; i--) {
            int count = 1;
            while(!s2.isEmpty() && s2.peek()[0] >= weight.get(i)) {
                count += s2.pop()[1];
            }
            s2.push(new int[]{weight.get(i), count});
            right[i] = count;
        }
        for (int i = size - 1; i >= 0; i--) {
            int count = 1;
            while(!s4.isEmpty() && s4.peek()[0] <= weight.get(i)) {
                count += s4.pop()[1];
            }
            s4.push(new int[]{weight.get(i), count});
            rightMin[i] = count;
        }
        for (int i = 0; i < size; i++) {
            min += (long)weight.get(i) * left[i] * right[i];
            max += (long)weight.get(i) * leftMin[i] * rightMin[i];
        }
        return max - min;
    }


    public static void main(String[] args) {
        int[] test1 = {4, 4, 4, 4, 4};
        int[] test2 = {3, 2, 3, 4, 6};
        List<Integer> weight1 = new ArrayList<>();
        for (int i : test1) {
            weight1.add(i);
        }
        List<Integer> weight2 = new ArrayList<>();
        for (int i : test2) {
            weight2.add(i);
        }

        System.out.println(getTotalImbalance(weight1));
        System.out.println(getTotalImbalance(weight2));

    }

}
