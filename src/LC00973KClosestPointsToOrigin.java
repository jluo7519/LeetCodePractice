import java.util.Random;

public class LC00973KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int k) {
        if (points == null || points.length == 0 || points[0] == null || points[0].length != 2 || k < 0) {
            throw new IllegalArgumentException();
        }
        Point[] pointArr = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            int[] point = points[i];
            pointArr[i] = new Point(point[0], point[1]);
        }
        int[][] res = new int[k][2];
        quickSelect(pointArr, 0, pointArr.length - 1, k);
        for (int i = 0; i < k; i++) {
            res[i][0] = pointArr[i].x;
            res[i][1] = pointArr[i].y;
        }
        return res;
    }

    class Point implements Comparable<Point> {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Point that) {
            return this.x * this.x + this.y * this.y - that.x * that.x - that.y * that.y;
        }
    }

    private Point quickSelect(Point[] points, int start, int end, int k) {
        if (start == end) {
            return points[start];
        }
        int pivotIdx = partition(points, start, end);
        int rank = pivotIdx - start + 1;
        if (rank == k) {
            return points[pivotIdx];
        } else if (rank < k) {
            return quickSelect(points, pivotIdx + 1, end, k - rank);
        } else {
            return quickSelect(points, start, pivotIdx - 1, k);
        }
    }
    private int partition(Point[] points, int start, int end) {
        int pivotIdx = new Random().nextInt(end - start + 1) + start;
        Point pivotPoint = points[pivotIdx];
        swap(points, pivotIdx, end);

        int left = start - 1;
        for (int i = start; i < end; i++) {
            if (points[i].compareTo(pivotPoint) < 0) {
                swap(points, ++left, i);
            }
        }
        swap(points, ++left, end);
        return left;
    }
    private void swap(Point[] points, int a, int b) {
        Point tmp = points[a];
        points[a] = points[b];
        points[b] = tmp;
    }
}
