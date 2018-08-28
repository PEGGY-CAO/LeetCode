import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        for (int i = 0; i < nums.length; i++) {
            int start = nums[i];

            while (i < nums.length - 1 && nums[i] + 1 == nums[i + 1]) {
                i++;
            }

            if (nums[i] == start) {
                result.add(nums[i] + "");
            } else {
                result.add(start + "->" + nums[i]);
            }

        }
        return result;

    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 7, 9, 10, 11, 12};
        Solution sol = new Solution();
        List<String> result = sol.summaryRanges(nums);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}
