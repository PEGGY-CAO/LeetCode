import java.util.Arrays;

public class Solution {
    public int missingNumber(int[] nums) {

        Arrays.sort(nums);

        int total = nums.length;
        //corner case
        if (nums[total - 1] != total) {
            return total;
        }
        if (nums[0] != 0) {
            return 0;
        }

        //binary search

        int left = 0;
        int right = total - 1;
        while (left <= right) {
            int midIndex = left + (right - left) / 2;
            int midNum = nums[midIndex];

            if (midIndex == midNum) {
                left = midIndex + 1;
            } else {
                int pred = (midIndex - 1) >= 0 ? nums[midIndex - 1] : midNum;
                int succ = (midIndex + 1) < total ? nums[midIndex + 1] : midNum;

                if (midNum - pred == 2) {
                    return midNum - 1;
                }
                if (succ - midNum == 2) {
                    return midNum + 1;
                }
                right = midIndex - 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] test1 = {3, 0, 1};
        int answer = s.missingNumber(test1);
        System.out.println(answer);
        int[] test2 = {9,6,4,2,3,5,7,0,1};
        System.out.println(s.missingNumber(test2));
    }
}
