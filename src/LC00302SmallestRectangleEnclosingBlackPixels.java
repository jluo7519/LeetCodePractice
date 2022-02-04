public class LC00302SmallestRectangleEnclosingBlackPixels {
    public int minArea(char[][] image, int x, int y) {
        if (image == null) throw new IllegalArgumentException();
        int left = findLeftBoundary(image, y);
        int right = findRightBoundray(image, y);
        int top = findTopBoundary(image, x);
        int bottom = findBottomBoundary(image, x);
        return (right - left + 1) * (bottom - top + 1);
    }

    private int findLeftBoundary(char[][] image, int y) {
        int left = 0;
        int right = y;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (columnAllZeros(image, mid)) { // go right
                left = mid + 1;
            } else { // go left
                right = mid - 1;
            }
        }
        return left;
    }

    private int findRightBoundray(char[][] image, int y) {
        int left = y;
        int right = image[0].length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (columnAllZeros(image, mid)) { // go left
                right = mid - 1;
            } else { // go right
                left = mid + 1;
            }
        }
        return right;
    }

    private int findTopBoundary(char[][] image, int x) {
        int left = 0;
        int right = x;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (rowAllZeros(image, mid)) { // go down
                left = mid + 1;
            } else { // go up
                right = mid - 1;
            }
        }
        return left;
    }

    private int findBottomBoundary(char[][] image, int x) {
        int left = x;
        int right = image.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (rowAllZeros(image, mid)) { // go top
                right = mid - 1;
            } else { // go dowm
                left = mid + 1;
            }
        }
        return right;
    }

    private boolean columnAllZeros(char[][] image, int col) {
        for (int i = 0; i < image.length; i++) {
            if (image[i][col] == '1') {
                return false;
            }
        }
        return true;
    }
    private boolean rowAllZeros(char[][] image, int row) {
        for (int i = 0; i < image[0].length; i++) {
            if (image[row][i] == '1') {
                return false;
            }
        }
        return true;
    }
}
