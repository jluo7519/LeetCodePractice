import java.util.*;

public class LC00694NumberOfDistinctIslands {
    public static void main(String args[]) {
        LC00694NumberOfDistinctIslands test = new LC00694NumberOfDistinctIslands();

        int[][] grid1 = {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1}};
        System.out.println(test.numDistinctIslands(grid1));

        int[][] grid2 = {
                {1,1,0,1,1},
                {1,0,0,0,0},
                {0,0,0,0,1},
                {1,1,0,1,1}};
        System.out.println(test.numDistinctIslands(grid2));

        int[][] grid3 = {
                {1,1,0},
                {0,1,1},
                {0,0,0},
                {1,1,1},
                {0,1,0}};
        System.out.println(test.numDistinctIslands(grid3));

    }

    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            throw new IllegalArgumentException();
        }
        int rows = grid.length;
        int cols = grid[0].length;
        Set<String> result = new HashSet<>();
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    StringBuilder path = new StringBuilder();
                    //dfs(path, i, j, grid, visited, 's');
                    bfs(path, i, j, grid, visited);
                    result.add(path.toString());
                }
            }
        }
        return result.size();
    }

    private void dfs(StringBuilder path, int i, int j, int[][] grid, boolean[][] visited, char dir) {
        int rows = grid.length;
        int cols = grid[0].length;
        //base cases
        if (i < 0 || i >= rows || j < 0 || j >= cols || visited[i][j] || grid[i][j] != 1) {
            return;
        }
        visited[i][j] = true;
        path.append(dir);
        dfs(path, i - 1, j, grid, visited, 'u');
        dfs(path, i + 1, j, grid, visited, 'd');
        dfs(path, i, j - 1, grid, visited, 'l');
        dfs(path, i, j + 1, grid, visited, 'r');

        //mark backtracking
        path.append('b');
    }

    private void bfs(StringBuilder path, int i, int j, int[][] grid, boolean[][] visited) {
        //int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i, j});
        visited[i][j] = true;
        path.append('s');
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            if (validPoint(grid, x - 1, y) && !visited[x - 1][y]) {
                queue.offer(new int[] {x - 1, y});
                path.append('u');
                visited[x - 1][y] = true;
            }

            if (validPoint(grid, x + 1, y) && !visited[x + 1][y]) {
                queue.offer(new int[] {x + 1, y});
                path.append('d');
                visited[x + 1][y] = true;
            }

            if (validPoint(grid, x, y - 1) && !visited[x][y - 1]) {
                queue.offer(new int[] {x, y - 1});
                path.append('l');
                visited[x][y - 1] = true;
            }

            if (validPoint(grid, x, y + 1) && !visited[x][y + 1]) {
                queue.offer(new int[] {x, y + 1});
                path.append('r');
                visited[x][y + 1] = true;
            }

            //finished four direction
            path.append('f');
        }
    }
    private boolean validPoint(int[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;
        return i >= 0 && i < rows && j >= 0 && j < cols && grid[i][j] == 1;
    }
}
