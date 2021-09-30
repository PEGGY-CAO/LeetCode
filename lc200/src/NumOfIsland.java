public class NumOfIsland {

    public static int numberOfIsland(int[][] graph) {

        int n = graph.length;
        if (n == 0) return 0;

        int m = graph[0].length;

        int result = 0;

        for (int row = 0; row < n; row++) {
            for (int column = 0; column < m; column++) {
                if (graph[row][column] == 1) {
                    dfs(graph, row, column);
                    result++;
                }
            }
        }

        return result;
    }

    public static void dfs(int[][] graph, int row, int column) {

        if (row < 0 || row >= graph.length || column < 0 || column >= graph[0].length || graph[row][column] != 1) return;

        graph[row][column] = 0;
        dfs(graph, row - 1, column);
        dfs(graph, row + 1, column);
        dfs(graph, row, column + 1);
        dfs(graph, row, column - 1);

    }

    public static void main(String[] args) {

        int[][] graph = {
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
        };

        int result = numberOfIsland(graph);
        System.out.println(result);
    }
}
