import java.util.Arrays;

public class DoubleOnMatch {

    public static long doubleSize(long[] arr, long num) {

        Arrays.sort(arr);
        long result = num;

        //binary search
        int left = 0;
        int right = arr.length - 1;

        int mid = 0;

        while (left <= right) {
            mid = left + (right - left) / 2;

            if (arr[mid] == num) {
                break;
            } else if (arr[mid] < num) {
                left = mid + 1;
            } else {
                right = mid - 1;//1
            }
        }


        if (arr[mid] != num) {
            return num;
        } else {
            while (mid < arr.length) {
                if (arr[mid] == num) {
                    result *= 2;
                    num = result;
                }
                mid ++;
            }
        }
        return result;

    }


    public static void main(String[] args) {
        long[] arr = {1, 2, 4, 11, 12, 8};
        long num = 2;
        long result = doubleSize(arr, num);
        System.out.println(result);
    }
}
