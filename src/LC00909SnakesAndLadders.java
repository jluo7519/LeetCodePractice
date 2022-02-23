import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC00909SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        int[] array = boardToArray(board);
        boolean[] visited = new boolean[array.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                List<Integer> nexts = convert(cur, array);
                for (Integer next : nexts) {
                    if (next == rows * cols) {
                        return steps + 1;
                    }
                    if (!visited[next]) {
                        queue.offer(next);
                        visited[next] = true;
                    }
                }
            }
            steps++;
        }
        return -1;
    }
    private List<Integer> convert(int cur, int[] array) {
        List<Integer> nexts = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            int tmp = cur + i;
            if (tmp >= array.length) {
                break;
            } if (array[tmp] == -1) {
                nexts.add(tmp);
            } else {
                nexts.add(array[tmp]);
            }
        }
        return nexts;
    }
    private int[] boardToArray(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        int[] array = new int[rows * cols + 1]; // idx 0 is not valid
        boolean flag = false;
        int idx = 1;
        for (int i = rows - 1; i >= 0; i--) {
            if (!flag) {
                for (int j = 0; j < cols; j++) {
                    array[idx++] = board[i][j];
                }
            } else {
                for (int j = cols - 1; j >= 0; j--) {
                    array[idx++] = board[i][j];
                }
            }
            flag = !flag;
        }
        return array;
    }
}
