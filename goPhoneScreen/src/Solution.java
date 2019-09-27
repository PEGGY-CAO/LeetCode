import java.util.Arrays;

public class Solution {

    /**
     * Given a 2D 0-1 matrix, find if there's a path from the first row to the last row
     * @param matrix 2D 0-2 matrix
     * @return boolean
     */
    public boolean findPath(int[][] matrix) {
        if (matrix == null) {
            return false;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        //corner case when there're all 0s in first row
        // if there's a 1, record for its index as start
        boolean flag = false;
        int start = 0;
        for (int i = 0; i < cols; i++) {
            if (matrix[0][i] == 1) {
                flag = true;
                start = i;
            }
        }
        if (!flag) {
            return false;
        }
        // corner case when there're all 0s in the last row
        flag = false;
        for (int i = 0; i < cols; i++) {
            if (matrix[rows - 1][i] == 1) {
                flag = true;
            }
        }
        if (!flag) {
            return false;
        }
        //use a matrix to mark visited or not, initialize as -1 : unvisited
        int[][] visited = new int[rows][cols];
        for (int i = 0; i < rows; i++) Arrays.fill(visited[i], -1);
        //apply dfs to the matrix
        return dfs(matrix, visited, 0, start);
    }

    /**
     * Private helper method for applying dfs
     * @param matrix 2D 0-1 matrix
     * @param visited 2D matrix to mark visited or not
     * @param r row index
     * @param c column index
     * @return boolean
     */
    private boolean dfs(int[][] matrix, int[][] visited, int r, int c) {
        //corner case: index out of bound
        if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length) return false;

        //base case when it's true
        if (r == matrix.length - 1 && matrix[r][c] == 1) {
            return true;
        }

        //recursively dfs on down, right, left, and up direction
        if (matrix[r][c] == 1) {
            boolean down = dfs(matrix, visited, r + 1, c);
            if (down) return true;
            boolean right = dfs(matrix, visited, r, c + 1);
            if (right) return true;
            boolean left = dfs(matrix, visited, r, c - 1);
            if (left) return true;
            boolean up = dfs(matrix, visited, r - 1, c);
            if (up) return true;
//            return right || left || up;
        }
        return false;

    }

    public static void main(String[] str) {

        Solution test = new Solution();
        int[][] test1 = {{0, 0, 1},
                {1, 0, 1},
                {0, 0, 1}};
        System.out.println(test.findPath(test1));
        int[][] test2 = {{0, 0, 0},
                {1, 0, 1},
                {0, 0, 1}};
        System.out.println(test.findPath(test2));
        int[][] test3 = {{0, 0, 1},
                {1, 1, 1},
                {0, 1, 0}};
        System.out.println(test.findPath(test3));
//        int[][] test4 = {{1, 0, 0, 0, 0, 0, 0},
//                {1, 1, 1, 0, 1, 1, 1},
//                {0, 0, 1, 1, 1, 0, 1},
//                {0, 0, 1, 0, 0, 0, 1},
//                {0, 0, 0, 1, 1, 1, 1},
//                {0, 0, 0, 0, 1, 0, 0},
//                {0, 0, 0, 0, 1, 0, 0}};
//        System.out.println(test.findPath(test4));
    }
}
