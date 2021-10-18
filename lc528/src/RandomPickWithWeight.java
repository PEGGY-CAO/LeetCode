import java.util.Random;

public class RandomPickWithWeight {

    public static int pickIndex(int[] w) {
        int[] wsum = new int[w.length];

        for (int i = 0; i < w.length; i++) {
            if (i == 0) {
                wsum[i] = w[i];
            } else {
                wsum[i] = wsum[i - 1] + w[i];
            }
        }

        Random r = new Random();
        int x = r.nextInt(wsum[w.length - 1]) + 1; // random integer x's range: [1, wsum[w.length - 1]]
        //binary search for x in the wsum array
        //following codes are similar to leetcode 35, search insert position
        int left = 0;
        int right = w.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (wsum[mid] == x) {
                return mid;
            } else if (wsum[mid] > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;

    }

    public static void main(String[] args) {
        int[] weights = {2,5,3,4};
        System.out.println(pickIndex(weights));
    }
}
