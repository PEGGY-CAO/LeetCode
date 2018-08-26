import java.util.Map;
import java.util.HashMap;

public class Solution {

    /**
     * time complexity: O(n)
     * @throws IllegalArgumentException if we cannot find the two integers
     * @param nums An int array
     * @param target target sum
     * @return an int array stores the two elements' indices
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> backing = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (backing.containsKey(complement)) {
                return new int[]{backing.get(complement), i};
            }
            backing.put(nums[i], i);
        }
        throw new IllegalArgumentException("no such result");

    }

    public static void main(String[] args) {
        int[] nums = {7, 8, 5, 9};
        Solution rel = new Solution();
        int[] result = rel.twoSum(nums, 13);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

}
