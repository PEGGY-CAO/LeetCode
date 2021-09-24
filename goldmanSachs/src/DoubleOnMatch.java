import java.util.Arrays;

public class DoubleOnMatch {

    public static long doubleSize(long[] arr, long num) {

        Arrays.sort(arr);
        long result = num;
//      After sort 1, 2, 4, 8, 11, 12
        //binary search
        int left = 0;
        int right = arr.length - 1;
        int mid = left + (right - left) / 2;

        while (mid >= left && left < arr.length && right >= 0) {
//            System.out.println("mid in while: " + mid);
//            System.out.println("arr[mid] in while: " + arr[mid]);
            if (arr[mid] == num) {
//                System.out.println("mid in if: " + mid);
                break;
            } else if (arr[mid] < num) {
                left = mid + 1;//1
            } else {
                right = mid - 1;//1
//                System.out.println("right: " + right);
            }
            mid = left + (right - left) / 2;//0, 1

        }
//        System.out.println("mid: " + mid);
//        System.out.println("arr[mid]: " + arr[mid]);

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
