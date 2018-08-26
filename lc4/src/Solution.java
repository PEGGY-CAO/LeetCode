public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m == 0 && n == 0) {
            throw new IllegalArgumentException("input are not valid");
        }
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int start = 0;
        int end = m;


        while (start <= end) {
            int partition1 = (start + end) / 2;
            int partition2 = (m + n + 1) / 2 - partition1;

            int maxleft1 = partition1 == 0 ? Integer.MIN_VALUE : nums1[partition1 - 1];
            int maxleft2 = partition2 == 0 ? Integer.MIN_VALUE : nums2[partition2 - 1];
            int minright1 = partition1 == m ? Integer.MAX_VALUE : nums1[partition1];
            int minright2 = partition2 == n ? Integer.MAX_VALUE : nums2[partition2];

            if (maxleft1 <= minright2 && maxleft2 <= minright1) {
                //find right partition, then check if the whole number of two array is odd or even
                if ((m + n) % 2 == 0) {
                    return ((double)Math.max(maxleft1, maxleft2) + Math.min(minright1, minright2)) / 2;
                } else {
                    return (double)Math.max(maxleft1, maxleft2);
                }
            } else if (maxleft1 > minright2) {
                end = partition1 - 1;
            } else {
                //maxleft2 > minright1
                start = partition1 + 1;
            }


        }

        throw new IllegalArgumentException("we couldn't find the answer. the input could have something wrong");
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 6, 7, 9};
        int[] nums2 = {2, 5, 8};
        Solution sol = new Solution();
        System.out.println(sol.findMedianSortedArrays(nums1, nums2));
    }
}
