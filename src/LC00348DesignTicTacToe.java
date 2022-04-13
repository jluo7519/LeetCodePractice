public class LC00348DesignTicTacToe {
    private char[][] board;
    private int n;
    // status '\0' = available
    // status 'X' = player 1 occupied
    // status 'O' = player 2 occupied
    private int result;

    public LC00348DesignTicTacToe(int n) {
        this.n = n;
        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][i] = '\0';
            }
        }
        result = 0;
    }

    public int move(int row, int col, int player) { // O(n)
        if (result != 0) {
            throw new RuntimeException("game is over, no more moves allowed");
        }
        if (board[row][col] != '\0') {
            throw new RuntimeException("cannot place on a non-empty block");
        }
        board[row][col] = player == 1 ? 'X' : 'O';
        // O(n)
        if (checkCol(col, player) || checkRow(row, player)) {
            return player;
        }
        // O(n)
        if (col == row && checkAntiDiag(player)) {
            return player;
        }
        // O(n)
        if (col + row == n - 1 && checkDiag(player)) {
            return player;
        }
        return 0;
    }

    private boolean checkRow(int row, int player) {
        for (int i = 0; i < n; i++) {
            if (player == 1 && board[row][i] != 'X') {
                return false;
            } else if (player == 2 && board[row][i] != 'O') {
                return false;
            }
        }
        return true;
    }

    private boolean checkCol(int col, int player) {
        for (int i = 0; i < n; i++) {
            if (player == 1 && board[i][col] != 'X') {
                return false;
            } else if (player == 2 && board[i][col] != 'O') {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiag(int player) {
        for (int i = 0; i < n; i++) {
            if (player == 1 && board[n - 1 - i][i] != 'X') {
                return false;
            } else if (player == 2 && board[n - 1 - i][i] != 'O') {
                return false;
            }
        }
        return true;
    }

    private boolean checkAntiDiag(int player) {
        for (int i = 0; i < n; i++) {
            if (player == 1 && board[i][i] != 'X') {
                return false;
            } else if (player == 2 && board[i][i] != 'O') {
                return false;
            }
        }
        return true;
    }
}
