import java.util.Arrays;

public class PrisonBreak {

    public static int findBiggestHole(int n, int m, int[] h, int[] v) {

        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("initially bar number must be positive");
        }

        Arrays.sort(h);
        Arrays.sort(v);

        //find eligible consecutive horizontal bars
        int countH = 0;
        int maxConsecutiveH = 0;

        for (int i = 0; i < h.length; i++) {
            if (i == 0) {
                countH ++;
                maxConsecutiveH = countH;
            } else {
                if (h[i] - h[i - 1] == 1) {
                    countH ++;
                } else {
                    maxConsecutiveH = countH > maxConsecutiveH ? countH : maxConsecutiveH;
                    countH = 1;
                }
            }
        }

        //find eligible consecutive vertical bars
        int countV = 0;
        int maxConsecutiveV = 0;

        for (int i = 0; i < v.length; i++) {
            if (i == 0) {
                countV ++;
                maxConsecutiveV = countV;
            } else {
                if (v[i] - v[i - 1] == 1) {
                    countV ++;
                } else {
                    maxConsecutiveV = countV > maxConsecutiveV ? countV : maxConsecutiveV;
                    countV = 1;
                }
            }
        }

        return (maxConsecutiveH + 1) * (maxConsecutiveV + 1);

    }

    public static void main(String[] args) {

        int n = 6; //the number of horizontal bars initially
        int m = 6; //the number of vertical bars initially

        int[] h = {4}; //the horizontal bars to remove
        int[] v = {2}; //the vertical bars to remove

        int area = findBiggestHole(n, m, h, v);
        System.out.println(area);
    }
}
