import java.util.ArrayList;
import java.util.List;

public class LC00057InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || newInterval == null || newInterval.length != 2) {
            throw new IllegalArgumentException();
        }
        List<int[]> res = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[1] < newInterval[0]) {
                res.add(interval);
            } else if (newInterval[1] < interval[0]) {
                res.add(newInterval);
                newInterval = interval;
            } else { //  (interval[1] < newInterval[0] || newInterval[1] > interval[0] )
                int[] newItv= new int[]{Math.min(interval[0], newInterval[0]), Math.max(interval[1], newInterval[1])};
                newInterval = newItv;
            }
        }
        res.add(newInterval);
        return res.toArray(new int[res.size()][2]);
    }
}
