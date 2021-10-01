import java.util.HashMap;
import java.util.Map;

public class MaxDis {

    //we are supposed to find maximum value of |i - j| = |A[i] - A[j]|
    //two cases
    //case1, i - j = A[i] - A[j] => i - A[i] = j - A[j]
    //case2, i - j = A[j] - A[i] => i + A[i] = j + A[j]

    public static int findMaxDistance(int[] A) {
        if (A.length == 0) {
            throw new IllegalArgumentException("input array is invalid");
        }

        if (A.length == 1) return 0;

        int ans = 0;

        Map<Integer, Integer> lookupDiff = new HashMap<>();
        Map<Integer, Integer> lookupSum = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            int diff = i - A[i];
            int sum = i + A[i];
//            System.out.println("i: " + i + ", diff: " + diff + ", sum: " + sum);
            if (lookupDiff.containsKey(diff)) {
                ans = Math.max(ans, i - lookupDiff.get(diff));
            } else {
                lookupDiff.put(diff, i);
            }

            if (lookupSum.containsKey(sum)) {
                ans = Math.max(ans, i - lookupSum.get(sum));
            } else {
                lookupSum.put(sum, i);
            }
        }
        return ans;

    }


    public static void main(String[] args) {
        int[] A = {2, 2, 2, 1};
        int[] B = {2, 4, 6, 7, 4, 7, 2};
        int[] C = {100, 100, 100};
        int[] D = {10000};

        System.out.println(findMaxDistance(D));
    }
}
