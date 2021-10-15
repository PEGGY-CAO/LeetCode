public class ProductCalculation {
//    Given an integer array nums, return an array such that answer[i] is equal to the product of all the elements of nums except nums[i].
//
//    Input: nums = [1,2,3,4]
//    Output: [24,12,8,6]

    public static int[] solution(int[] nums) {
        //corner case
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input array is not valid");
        }

        long x = 1;
        int countZero = 0;
        int firstZero = 0;

        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            if (current == 0 && countZero == 0) {
                firstZero = i;
            }
            if (current != 0) {
                x *= current;
            } else {
                countZero++;
            }
        }

        if (countZero > 1) return new int[nums.length];

        int[] result = new int[nums.length];

        if (countZero == 1) {
            result[firstZero] = (int) x;
            return result;
        }

        for (int i = 0; i < nums.length; i++) {
            long temp = x / nums[i];
            result[i] = (int) temp;
        }

        return result;

    }


    public static void main(String[] args) {
        int[] tc1 = {2, 5, 6, 8, 10};
        int[] tc2 = {0, 0, 0, 0};
        int[] tc3 = {4, 0, 8, 9};
        int[] tc4 = {5, 7, 0, 9, 0};
        int[][] testCases = {tc1, tc2, tc3, tc4};
        for (int i = 0; i < testCases.length; i++) {
            int[] result = solution(testCases[i]);
            for (int integer : result) {
                System.out.print(integer + " ");
            }
            System.out.println("       ");
        }
    }
}
