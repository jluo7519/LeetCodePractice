import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LC00056MergeIntervals {
    // 自己的 O(n log n) time
    public int[][] mergeI(int[][] intervals) {
        // cc
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        List<int[]> list = new ArrayList<>();
        list.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] last = list.get(list.size() - 1);
            int[] cur = intervals[i];
            if (cur[0] <= last[1]) { // merge
                last[1] = Math.max(last[1], cur[1]);
            } else { // add new interval
                list.add(cur);
            }
        }
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
    // 算法哥的
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
