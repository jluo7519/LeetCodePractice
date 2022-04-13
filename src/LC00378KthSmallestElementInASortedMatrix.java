import java.util.*;

public class LC00378KthSmallestElementInASortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            throw new IllegalArgumentException();
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        Queue<Integer> minHeap = new PriorityQueue<>(rows + cols, new Comparator<Integer>(){
            @Override
            public int compare(Integer i1, Integer i2) {
                return matrix[i1 / cols][i1 % cols] - matrix[i2 / cols][i2 % cols];
            }
        });
        Set<Integer> visited = new HashSet<>();
        minHeap.offer(0);

        while (k-- > 0) {
            Integer cur = minHeap.poll();
            if (cur == null) throw new IllegalArgumentException("less than k elements in matrix");
            //convert
            int x = cur / cols;
            int y = cur % cols;
            if (k == 0) {
                return matrix[x][y];
            }
            if (x + 1 < rows) {
                int nei1 = (x + 1) * cols + y;
                if (!visited.contains(nei1)) {
                    visited.add(nei1);
                    minHeap.offer(nei1);
                }
            }
            if (y + 1 < cols) {
                int nei2 = x * cols + y + 1;
                if (!visited.contains(nei2)) {
                    visited.add(nei2);
                    minHeap.offer(nei2);
                }
            }
        }
        throw new RuntimeException();
    }

    public int kthSmallestS2(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            throw new IllegalArgumentException();
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        // dijkstra
        Queue<Cell> minHeap = new PriorityQueue<>();
        minHeap.offer(new Cell(0, 0, matrix[0][0]));
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        while (k-- > 0) {
            Cell cur = minHeap.poll();
            if (k == 0) return cur.val;
            int i = cur.x;
            int j = cur.y;

            // right
            if (j + 1 < cols && visited.add(i * cols + j + 1)) {
                minHeap.offer(new Cell(i, j + 1, matrix[i][j + 1]));
            }
            // down
            if (i + 1 < rows && visited.add((i + 1) * cols + j)) {
                minHeap.offer(new Cell(i + 1, j, matrix[i + 1][j]));
            }
        }
        throw new RuntimeException();
    }
    class Cell implements Comparable<Cell>{
        int x;
        int y;
        int val;
        public Cell(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
        @Override
        public int compareTo(Cell that) {
            return this.val - that.val;
        }
    }
}
