import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LC00056MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0] == null || intervals[0].length == 0) {
            throw new IllegalArgumentException();
        }
        //sort by left end
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> res = new ArrayList<>();
        int[] prev = intervals[0];
        for (int[] cur : intervals) {
            if (prev[1] < cur[0]) {
                res.add(prev);
                prev = cur;
            } else {
                prev[1] = Math.max(prev[1], cur[1]);
            }
        }
        res.add(prev);
        return res.toArray(new int[res.size()][2]);
    }

    public static void main(String args[]) {
        LC00056MergeIntervals test = new LC00056MergeIntervals();
        int[][] test1 = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        int[][] res1 = test.merge(test1);
        for (int[] i : res1) {
            System.out.print(" " + Arrays.toString(i));
        }
    }
}
