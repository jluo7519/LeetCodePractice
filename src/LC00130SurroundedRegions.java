import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LC00130SurroundedRegions {
    int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;
        int rows = board.length;
        int cols = board[0].length;
        Queue<Integer> queue = new LinkedList<>();
        loadEdgeO(queue, board);
        bfs(queue, board);
        flip(board);
    }

    private void loadEdgeO(Queue<Integer> queue, char[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        // 0th & last row
        for (int i = 0; i < cols; i++) {
            if (board[0][i] == 'O') {
                board[0][i] = 'S';
                queue.offer(0 * cols + i);
            }
            if (board[rows - 1][i] == 'O') {
                board[rows - 1][i] = 'S';
                queue.offer((rows - 1) * cols + i);
            }
        }
        // 0th & last col
        for (int j = 1; j < rows - 1; j++) {
            if (board[j][0] == 'O') {
                board[j][0] = 'S';
                queue.offer(j * cols + 0);
            }
            if (board[j][cols - 1] == 'O') {
                board[j][cols - 1] = 'S';
                queue.offer(j * cols + cols - 1);
            }
        }
    }
    private void bfs(Queue<Integer> queue, char[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int x = cur / cols;
            int y = cur % cols;
            for (int[] dir : dirs) {
                int i = dir[0] + x;
                int j = dir[1] + y;
                if (i >= 0 && i < rows && j >= 0 && j < cols && board[i][j] == 'O') {
                    board[i][j] = 'S';
                    queue.offer(i * cols + j);
                }
            }
        }
    }

    private void flip(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'S') {
                    board[i][j] = 'O';
                }
            }
        }
    }
}
