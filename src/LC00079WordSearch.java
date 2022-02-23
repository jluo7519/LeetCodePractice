public class LC00079WordSearch {
    private static final int[][] DIRS = new int[][]{{-1,0},{0,-1},{1,0},{0,1}};
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, int i, int j, String word, int idx) {
        int rows = board.length;
        int cols = board[0].length;
        // success
        if (idx == word.length()) return true;
        // failure
        if (i < 0 || i >= rows || j < 0 || j >= cols) {
            return false;
        }
        char tmp = board[i][j];
        // failure
        if (tmp != word.charAt(idx)) {
            return false;
        }
        // mark visited
        board[i][j] = '.';
        for (int[] dir : DIRS) {
            if (dfs(board, i + dir[0], j + dir[1], word, idx + 1)) {
                return true;
            }
        }
        board[i][j] = tmp;
        return false;
    }
}
