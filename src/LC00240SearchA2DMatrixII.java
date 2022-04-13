public class LC00240SearchA2DMatrixII {
    // O()
    public boolean searchMatrix(int[][] matrix, int target) {
        // 从右上角出发, 往左下找
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            throw new IllegalArgumentException();
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int i = 0;
        int j = cols - 1;

        while (i < rows && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) { // 大于target, 根据matrix特性 同列下面只会更大 所以可以排除当前列
                j--;
            } else { // 小于target, 只能向右向下, 因为我是从右上开始找过来的，右边的是我已经排除的 所以只能往下
                i++;
            }
        }
        return false;
    }
    public boolean searchMatrixFromBotLeft(int[][] matrix, int target) {
        // 从左下角出发, 往右上找
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            throw new IllegalArgumentException();
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int i = rows - 1;
        int j = 0;

        while (i >= 0 && j < cols) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) { // go right
                j++;
            } else { // > target, go up
                i--;
            }
        }
        return false;
    }
}
