import java.util.Arrays;

public class FindDuplicate {

    public int findDup(int[] arr) {
        //sort it first
        Arrays.sort(arr);

        //binary search
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int midIndex = left + (right - left) / 2;
            int mid = arr[midIndex];
            if (mid <= midIndex) {
                right = midIndex - 1;
            } else {
                left = midIndex + 1;
            }
        }

        return arr[left];
    }
}
