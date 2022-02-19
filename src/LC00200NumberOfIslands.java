import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC00200NumberOfIslands {
    public int numIslands(char[][] grid) {
        //cc
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            throw new IllegalArgumentException();
        }

        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int nIslands = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(grid, i, j, visited);
                    nIslands++;
                }

            }
        }
        return nIslands;
    }
    //DFS
    private void dfs(char[][] grid, int i, int j, boolean[][] visited) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (i < 0 || i >= rows || j < 0 || j >= cols || visited[i][j] || grid[i][j] != '1') {
            return;
        }
        visited[i][j] = true;

        dfs(grid, i - 1, j, visited);
        dfs(grid, i + 1, j, visited);
        dfs(grid, i, j - 1, visited);
        dfs(grid, i, j + 1, visited);
    }

    //BFS
    private void bfs(char[][] grid, int i, int j, boolean[][] visited) {
        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i, j});
        visited[i][j] = true;
        //grid[i][j] = '2';

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            List<int[]> nexts = convert(grid, cur, dirs);
            for (int[] next : nexts) {
                int x = next[0];
                int y = next[1];
                if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == '1' && !visited[x][y]) {
                    queue.offer(next);
                    visited[x][y] = true;
                    //grid[x][y] = '2';//color with 2
                }
            }
        }
    }
    private List<int[]> convert(char[][] grid, int[] cur, int[][] dirs) {
        List<int[]> nexts = new ArrayList<>();
        int rows = grid.length;
        int cols = grid[0].length;
        for (int[] dir : dirs) {
            nexts.add(new int[]{cur[0] + dir[0], cur[1] + dir[1]});
        }
        return nexts;
    }

    public static void main(String args[]) {
        LC00200NumberOfIslands test = new LC00200NumberOfIslands();
        char[][] grid1 = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}};
        System.out.println(test.numIslands(grid1));

        char[][] grid2 = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}};
        System.out.println(test.numIslands(grid2));
    }
    // Union Find Solution: O(M*N*log(M*N))
    static final int[][] DIRS = new int[][]{{-1,0},{0,-1},{1,0},{0,1}};
    public int numIslandsUF(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            throw new IllegalArgumentException();
        }
        int rows = grid.length;
        int cols = grid[0].length;
        NumIslandUF uf = new NumIslandUF(grid, rows, cols);
        for (int i = 0; i < rows; i++) { // O(M*N)
            for (int j = 0; j < cols; j++) {
                int p = uf.idxToP(i, j, cols);
                if (uf.isIsland(p)) {
                    List<int[]> neighbors = getNeighbors(grid, i, j, rows, cols, uf);
                    for (int[] nei : neighbors) {
                        int q = uf.idxToP(nei[0], nei[1], cols);
                        if (uf.isIsland(q) && !uf.find(p, q)) { // O(log(M*N))
                            uf.union(p, q); // O(1)
                        }
                    }
                }
                System.out.println("i " + i + " j " + j + " num: " + uf.numOfIsland);
            }
        }
        return uf.numOfIsland;
    }
    private List<int[]> getNeighbors(char[][] grid, int i, int j, int rows, int cols, NumIslandUF uf) {
        List<int[]> neis = new ArrayList<>();
        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < rows && y >= 0 && y < cols && uf.isIsland(uf.idxToP(x, y, cols))) {
                neis.add(new int[]{x,y});
            }
        }
        return neis;
    }
}
class NumIslandUF {
    int[] size;
    int[] parent;
    int numOfIsland;
    public NumIslandUF(char[][] grid, int rows, int cols) {
        this.size = new int[rows * cols + 1];
        this.parent = new int[rows * cols + 1];
        this.numOfIsland = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    addIsland(idxToP(i, j, grid[0].length));
                }
            }
        }
    }
    private int getRoot(int p) {
        int cur = p;
        while (parent[cur] != cur) {
            parent[cur] = parent[parent[cur]];
            cur = parent[cur];
        }
        parent[p] = cur;
        return cur;
    }
    public boolean find(int p, int q) {
        return getRoot(p) == getRoot(q);
    }
    public void union(int p, int q) {
        int rootP = getRoot(p);
        int rootQ = getRoot(q);
        if (size[rootP] >= size[rootQ]) { // rootQ -> rootP
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
            this.numOfIsland--;
        } else {
            union(q, p);
        }
    }
    public void addIsland(int p) {
        size[p] = 1;
        parent[p] = p;
        this.numOfIsland++;
    }
    public boolean isIsland(int p) {
        return parent[p] > 0;
    }
    public int idxToP(int i, int j, int cols) {
        return i * cols + j + 1;
    }
}