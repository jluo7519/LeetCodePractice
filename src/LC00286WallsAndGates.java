import java.util.LinkedList;
import java.util.Queue;

public class LC00286WallsAndGates {
    private final int GATE = 0;
    private final int EMPTY = Integer.MAX_VALUE;
    public void wallsAndGates(int[][] rooms) {
        //cc
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) return;

        int rows = rooms.length;
        int cols = rooms[0].length;
        Queue<int[]> queue = loadGates(rooms);

        //开始bfs
        //因为load过程中已经offer过了于是无需再offer, 只是需要记录一个长度
        int level = 0;

        while (!queue.isEmpty()) {
            //记录该层的长度
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];

                //top
                if (x - 1 >= 0 && rooms[x - 1][y] == EMPTY) {
                    rooms[x - 1][y] = level + 1;
                    queue.offer(new int[] {x - 1, y});
                }

                //bottom
                if (x + 1 < rows && rooms[x + 1][y] == EMPTY) {
                    rooms[x + 1][y] = level + 1;
                    queue.offer(new int[] {x + 1, y});
                }
                //left
                if (y - 1 >= 0 && rooms[x][y - 1] == EMPTY) {
                    rooms[x][y - 1] = level + 1;
                    queue.offer(new int[] {x, y - 1});
                }
                //right
                if (y + 1 < cols && rooms[x][y + 1] == EMPTY) {
                    rooms[x][y + 1] = level + 1;
                    queue.offer(new int[] {x, y + 1});
                }
            }
            level++;
        }
    }

    private Queue<int[]> loadGates(int[][] rooms) {
        int rows = rooms.length;
        int cols = rooms[0].length;

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rooms[i][j] == GATE) {
                    queue.offer(new int[] {i, j});
                }
            }
        }
        return queue;
    }

}
