import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class LC00759EmployeeFreeTime {
    class Interval {
        public int start;
        public int end;
        public Interval() {}
        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        if (schedule == null || schedule.size() == 0) throw new IllegalArgumentException();
        PriorityQueue<Interval> minHeap = new PriorityQueue<>((a, b) -> (a.start - b.start));

        for (List<Interval> list : schedule) {
            for (Interval itv : list) {
                minHeap.offer(itv);
            }
        }
        List<Interval> res = new ArrayList<>();
        int maxEnd = 0;
        while (minHeap.size() > 1) {
            Interval cur = minHeap.poll();
            Interval next = minHeap.peek();
            maxEnd = Math.max(maxEnd, cur.end);
            if (next.start > maxEnd) {
                Interval newItv = new Interval(maxEnd, next.start);
                res.add(newItv);
            }
        }
        return res;
    }
}
