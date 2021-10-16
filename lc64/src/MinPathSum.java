public class MinPathSum {

    public static int minimizePathSum(int[][] grid) {
//        for (int row = 1; row < grid.length; row++) {
//            grid[row][0] += grid[row - 1][0];
//        }
//        for (int col = 1; col < grid[0].length; col++) {
//            grid[0][col] += grid[0][col - 1];
//        }

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                //first row and first column
                if (row == 0 && col == 0) {
                    continue;
                }
                if (row == 0){
                    grid[row][col] += grid[row][col - 1];
                } else if (col == 0) {
                    grid[row][col] += grid[row - 1][col];
                } else {
                    grid[row][col] += Math.min(grid[row - 1][col], grid[row][col - 1]);
                }
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }


    public static void main(String[] args) {
        int[][] eg = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(minimizePathSum(eg));
    }
}
