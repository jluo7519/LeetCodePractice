import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC00417PacificAtlanticWaterFlow {
    int[][] directions = new int[][]{{-1,0},{0,-1},{1,0},{0,1}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;
        List<List<Integer>> res = new ArrayList<>();
        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];
        //initiate bfs start point
        for (int i = 0; i < rows; i++) {
            dfs(heights, i, 0, -1, pacific);
            dfs(heights, i, cols - 1, -1, atlantic);
        }
        for (int j = 0; j < cols; j++) {
            dfs(heights, 0, j, -1, pacific);
            dfs(heights, rows - 1, j, -1, atlantic);
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }
    private void dfs(int[][] heights, int i, int j, int prevH ,boolean[][] visited) {
        int rows = heights.length;
        int cols = heights[0].length;
        if (i < 0 || i >= rows || j < 0 || j >= cols || visited[i][j] || heights[i][j] < prevH) {
            return;
        }
        visited[i][j] = true;
        for (int[] dir : directions) {
            int x = dir[0] + i;
            int y = dir[1] + j;
            dfs(heights, x, y, heights[i][j], visited);
        }
    }
}
