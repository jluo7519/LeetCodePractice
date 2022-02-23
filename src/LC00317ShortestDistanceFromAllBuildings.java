import java.util.LinkedList;
import java.util.Queue;

public class LC00317ShortestDistanceFromAllBuildings {
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int minCost = Integer.MAX_VALUE;
        int[][] cost = new int[rows][cols];

        //for every building as a starting point do bfs
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    bfs(grid, i, j, cost);
                }
            }
        }
        //post process
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    minCost = Math.min(minCost, cost[i][j]);
                }
            }
        }
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }

    private void bfs(int[][] grid, int i, int j, int[][] cost) {
        Queue<int[]> queue = new LinkedList<>();
        int rows = grid.length;
        int cols = grid[0].length;
        final int[][] DIR = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        boolean[][] visited = new boolean[rows][cols];
        queue.offer(new int[] {i, j});
        visited[i][j] = true;
        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                for (int[] dir : DIR) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if (validPoint(x, y, rows, cols, grid, visited)) {
                        visited[x][y] = true;
                        queue.offer(new int[] {x, y});
                        cost[x][y] += distance + 1;
                    }
                }
            }
            distance++;
        }
        //如果某个点从building走不到的话，直接踢掉
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0 && !visited[r][c]) {
                    grid[r][c] = Integer.MAX_VALUE;
                }
            }
        }
    }

    private boolean validPoint(int x, int y, int rows, int cols, int[][] grid, boolean[][] visited) {
        return (x >= 0 && x < rows && y >= 0 && y < cols && !visited[x][y] && grid[x][y] == 0);
    }
}
