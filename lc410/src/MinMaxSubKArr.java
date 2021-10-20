public class MinMaxSubKArr {

    public static int minimizedMaxSumSubKarray(int[] nums, int k) {
        long maxLowerBound = Integer.MIN_VALUE;
        long maxUpperBound = 0;
        for (int num : nums) {
            maxLowerBound = num > maxLowerBound ? num : maxLowerBound;
            maxUpperBound += num;
        }

        //binary search between maxLowerBound and sum
        while (maxLowerBound <= maxUpperBound) {
            long mid = maxLowerBound + (maxUpperBound - maxLowerBound) / 2;
            int pieces = split(nums, mid);
            if (pieces > k) {
                maxLowerBound = mid + 1;
            } else {
                maxUpperBound = mid - 1;
            }
        }
        return (int)maxLowerBound;
    }

    public static int split(int[] nums, long targetSum) {
        long target = 0;
        int pieces = 1;
        for (int num : nums) {
            if (target + num > targetSum) {
                pieces ++;
                target = num;
            } else {
                target += num;
            }
        }
        return pieces;
    }

    public static void main(String[] args) {
        int[] eg = {7,2,5,10,8};
        int k = 2;
        System.out.println(minimizedMaxSumSubKarray(eg, k));
    }
}
