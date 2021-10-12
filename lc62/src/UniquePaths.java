public class UniquePaths {

    public static int possibleUniquePaths(int rows, int columns) {

        int[][] dp = new int[rows][columns];
        dp[0][0] = 0;
        for (int c = 1; c < columns; c++) {
            dp[0][c] = 1;
        }
        for (int r = 1; r < rows; r++) {
            dp[r][0] = 1;
        }

        for (int row = 1; row < rows; row++) {
            for (int column = 1; column < columns; column++) {
                dp[row][column] = dp[row - 1][column] + dp[row][column - 1];
            }
        }

        return dp[rows - 1][columns - 1];
    }


    public static void main(String[] args) {
        int rows = 3;
        int columns = 7;
        System.out.println(possibleUniquePaths(rows, columns));
    }
}
