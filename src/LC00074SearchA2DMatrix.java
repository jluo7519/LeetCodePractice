public class LC00074SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) { // O(log mn)
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            throw new IllegalArgumentException();
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0;
        int right = (rows - 1) * cols + cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int i = mid / cols;
            int j = mid % cols;
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) { // go right
                left = mid + 1;
            } else { // go left
                right = mid - 1;
            }
        }
        return false;
    }
}
