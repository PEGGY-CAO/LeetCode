public class Solution {
    // Given a rotated sorted array, find the pivot index
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] arg) {
        int[] test = {5, 6, 7, 1, 3, 4};
        Solution t = new Solution();
        int result = t.findMin(test);
        System.out.println(result);
    }
}
