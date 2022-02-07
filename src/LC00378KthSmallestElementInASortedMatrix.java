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
}
