import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC00695MaxAreaOfIsland {
    public static void main(String args[]) {
        LC00695MaxAreaOfIsland test = new LC00695MaxAreaOfIsland();
        int[][] grid1 = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        System.out.println(test.maxAreaOfIsland(grid1));
    }
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            throw new IllegalArgumentException();
        }
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int globalMax = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    globalMax = Math.max(globalMax, dfs(grid, i, j, visited));
                }
            }
        }
        return globalMax;
    }

    private int dfs(int[][] grid, int i, int j, boolean[][] visited) {
        int rows = grid.length;
        int cols = grid[0].length;
        //base case
        if (i < 0 || i >= rows || j < 0 || j >= cols || visited[i][j] || grid[i][j] != 1) {
            return 0;
        }
        visited[i][j] = true;
        return dfs(grid, i + 1, j, visited)
                + dfs(grid, i - 1, j, visited)
                + dfs(grid, i, j + 1, visited)
                + dfs(grid, i, j - 1, visited)
                + 1;
    }

    private int bfs(int[][] grid, int i, int j, boolean[][] visited) {
        int rows = grid.length;
        int cols = grid[0].length;
        int cnt = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            List<int[]> nexts = convert(grid, cur, visited);
            queue.addAll(nexts);
            cnt+=nexts.size();
        }
        return cnt;
    }
    private List<int[]> convert(int[][] grid, int[] cur, boolean[][] visited) {
        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int rows = grid.length;
        int cols = grid[0].length;
        List<int[]> nexts = new ArrayList<>();
        for (int[] dir : dirs) {
            int x = cur[0] + dir[0];
            int y = cur[1] + dir[1];
            if (x >= 0 && x < rows && y >= 0 && y < cols && !visited[x][y] && grid[x][y] == 1) {
                nexts.add(new int[] {x, y});
                visited[x][y] = true;
            }
        }
        return nexts;
    }
}
