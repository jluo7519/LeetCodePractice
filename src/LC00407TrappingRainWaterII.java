import java.util.PriorityQueue;
import java.util.Queue;

public class LC00407TrappingRainWaterII {
    int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    // inner class
    class Cell implements Comparable<Cell> {
        int x;
        int y;
        int height;
        public Cell(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
        @Override
        public int compareTo(Cell that) {
            return this.height - that.height;
        }
    }
    public int trapRainWater(int[][] heightMap) {
        // c.c.
        if (heightMap == null || heightMap.length == 0 || heightMap[0] == null || heightMap[0].length == 0) {
            throw new IllegalArgumentException();
        }
        int rows = heightMap.length;
        int cols = heightMap[0].length;
        // avoid count twice
        boolean[][] visited = new boolean[rows][cols];
        Queue<Cell> minHeap = new PriorityQueue<>();
        // initiation
        // first and last columns
        // O(n * log n)
        for (int i = 0; i < rows; i++) {
            minHeap.offer(new Cell(i, 0, heightMap[i][0]));
            visited[i][0] = true;
            minHeap.offer(new Cell(i, cols - 1, heightMap[i][cols - 1]));
            visited[i][cols - 1] = true;
        }
        // first and last rows O(m log m)
        for (int j = 0; j < cols; j++) { // j = 1; j < cols - 1 更精准, 但是这里因为有visited所以影响不大
            minHeap.offer(new Cell(0, j, heightMap[0][j]));
            visited[0][j] = true;
            minHeap.offer(new Cell(rows - 1, j, heightMap[rows - 1][j]));
            visited[rows - 1][j] = true;
        }
        // bfs
        int res = 0;
        while (!minHeap.isEmpty()) { // O(mn log mn)
            Cell cur = minHeap.poll();
            for (int[] dir : dirs) {
                int x = cur.x + dir[0];
                int y = cur.y + dir[1];
                if (x >= 0 && x < rows && y >= 0 && y < cols && !visited[x][y]) {
                    visited[x][y] = true;
                    res += Math.max(0, cur.height - heightMap[x][y]); // increment if neighbor is lower
                    minHeap.offer(new Cell(x, y, Math.max(cur.height, heightMap[x][y])));
                }
            }
        }
        return res;
    }
}

