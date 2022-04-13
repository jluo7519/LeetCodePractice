import java.util.Arrays;

public class LC00252MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null) {
            throw new IllegalArgumentException();
        }
        if (intervals.length == 0) return true;

        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        int maxRightEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (cur[0] < maxRightEnd) {
                return false;
            }
            maxRightEnd = Math.max(maxRightEnd, cur[1]);
        }
        return true;
    }
}
