import java.util.ArrayList;
import java.util.List;

public class LC00296BestMeetingPoint {
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            throw new IllegalArgumentException();
        }
        int rows = grid.length;;
        int cols = grid[0].length;
        // both lists needs to be ascending, therefore need two pass
        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    xList.add(i);
                }
            }
        }

        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                if (grid[i][j] == 1) {
                    yList.add(j);
                }
            }
        }
        if (xList.size() == 0) return -1;
        return medianDis(xList) + medianDis(yList);
    }

    private int medianDis(List<Integer> list) {
        int len = list.size();
        int median = list.get(len / 2);

        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += Math.abs(list.get(i) - median);
        }
        return sum;
    }
}
