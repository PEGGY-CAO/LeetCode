public class Solution {

    static int findInRotatedArr(int[] arr, int target) {

        //corner case
        if (arr == null || arr.length == 0) return -1;

        //binary search
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > arr[right]) {
                //pivot element is at the right side
                if (target < arr[mid] && target >= arr[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > arr[mid] && target <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }


    public static void main(String[] args) {

        int[] example = {4,5,6,8,1,2};
        int result = findInRotatedArr(example, 1);
        System.out.println(result);


    }
}
