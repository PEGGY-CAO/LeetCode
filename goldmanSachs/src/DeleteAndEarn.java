import java.util.ArrayList;
import java.util.List;


public class DeleteAndEarn {

        /*
         * Complete the 'maxPoints' function below.
         *
         * The function is expected to return a LONG_INTEGER.
         * The function accepts INTEGER_ARRAY elements as parameter.
         */

    public static long maxPoints(List<Integer> elements) {
        // Write your code here
        if (elements == null || elements.size() == 0) {
            return 0;
        }

        //build value dictionary for future lookup
        long[] lookup = new long[100001];
        for (Integer e : elements) {
            int value = e.intValue();
            lookup[value] += value;
        }

        //dynamic programming
        long skip = 0;
        long take = 0;

        for (int i = 0; i < 100001; i++) {
            long takei = skip + lookup[i];
            long skipi = Math.max(take, skip);

            take = takei;
            skip = skipi;

        }

        return Math.max(take, skip);

    }

    public static void main(String[] args) {

        int[] nums = {2, 2, 3, 3, 4, 3};
        List<Integer> eg = new ArrayList<>();
        for (int n : nums) {
            eg.add(n);
        }

        long result = maxPoints(eg);
        System.out.println(result);

    }


}
