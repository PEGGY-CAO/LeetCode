public class KnightProbOnChess {

    public double findKnightProbibility(int N, int K, int startR, int startC) {
        int[][] dir = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};

        //recursively calculate
        return find(N, K, startR, startC, dir);
    }

    public double find(int N, int K, int r, int c, int[][] dir) {
        if (r < 0 || r > N - 1 || c < 0 || c > N - 1) return 0;
        if (K == 0) return 1;
        double rate = 0;
        for (int i = 0; i < 8; i++) {
//            System.out.println(i);
            rate += 0.125 * (find(N, K - 1, r + dir[i][0], c + dir[i][1], dir));
        }
        return rate;
    }

    public static void main(String[] args) {
        int N = 3;
        int K = 2;
        int startRow = 0;
        int startColumn = 0;
        KnightProbOnChess solution = new KnightProbOnChess();
        System.out.println(solution.findKnightProbibility(N, K, startRow, startColumn));
    }
}
