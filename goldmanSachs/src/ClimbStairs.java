public class ClimbStairs {

    public static int wayOfClimbingNStairs(int n) {
        //corner cases
        if (n < 0) {
            throw new IllegalArgumentException("Input is not valid");
        }
        if (n == 0) return 1;
        if (n == 1) return 1;
        if (n == 2) return 2;

        int[] lookup = new int[n + 1];
        lookup[0] = 1;
        lookup[1] = 1;
        lookup[2] = 2;

        for (int i = 3; i <= n; i++) {
            lookup[i] = lookup[i - 1] + lookup[i - 2] + lookup[i - 3];
        }

        return lookup[n];
    }


    public static void main(String[] args) {
        int n = 4;
        System.out.println(wayOfClimbingNStairs(n));
    }
}
