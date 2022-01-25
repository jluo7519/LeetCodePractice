public class LC00329LongestIncreasingPathInAMatrix {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            throw new IllegalArgumentException();
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int globalMax = 0;
        int[][] mem = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                globalMax = Math.max(globalMax, dfs(matrix, i, j, null, mem));
            }
        }
        return globalMax;
    }

    private int dfs(int[][] matrix, int i, int j, Integer lastVal, int[][] mem) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        //failure
        if (i < 0 || i >= rows || j < 0 || j >= cols || (lastVal != null && matrix[i][j] <= lastVal)) {
            return 0;
        }
        if (mem[i][j] > 0) {
            return mem[i][j];
        }
        int val = matrix[i][j];

        int ret = Math.max(Math.max(dfs(matrix, i + 1, j, val, mem), dfs(matrix, i - 1, j, val, mem)),
        Math.max(dfs(matrix, i, j + 1, val, mem), dfs(matrix, i, j - 1, val, mem))) + 1;
        mem[i][j] = ret;
        return ret;
    }

    public static void main(String args[]) {
        LC00329LongestIncreasingPathInAMatrix test = new LC00329LongestIncreasingPathInAMatrix();
        int[][] matrix1 = {{9,9,4},{6,6,8},{2,1,1}};
        System.out.println("test1: " + test.longestIncreasingPath(matrix1));
        int[][] matrix2 = {{3,4,5},{3,2,6},{2,2,1}};
        System.out.println("test2: " + test.longestIncreasingPath(matrix2));
    }
}
