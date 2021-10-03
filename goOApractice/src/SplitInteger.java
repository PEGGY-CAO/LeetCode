public class SplitInteger {

    //split integer N from any digit into two integers, then find the minimum absolute difference among those split
    //N in range [10, 1000000000]


    public static int findMinDiffSplit(int N) {
        //corner case
        if (N < 10) {
            throw new IllegalArgumentException("input is invalid");
        }

        int answer = Integer.MAX_VALUE;

        int dividen = 10;
        while (N / dividen > 0) {
            int right = N % dividen;
            int left = N / dividen;
            int absDiff = Math.abs(left - right);
            answer = absDiff < answer ? absDiff : answer;
            dividen *= 10;

        }
        
        return answer;
    }

    public static void main(String[] args) {

        int N = 12001;
        System.out.println(findMinDiffSplit(N));
    }
}
