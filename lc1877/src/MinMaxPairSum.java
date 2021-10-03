import java.util.Arrays;

public class MinMaxPairSum {

    public static int findMinMaxPairSum(int[] arr) {
        Arrays.sort(arr);

        int result = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length / 2; i++) {
            result = Math.max(result, arr[i] + arr[arr.length - 1 - i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 2, 3};

        System.out.println(findMinMaxPairSum(arr));

    }
}
