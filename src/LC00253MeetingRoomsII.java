import java.util.*;
public class LC00253MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0] == null || intervals[0].length != 2) {
            throw new IllegalArgumentException();
        }
        List<MeetingTime> time = new ArrayList<>();
        for (int [] interval : intervals) {
            time.add(new MeetingTime(false, interval[0]));
            time.add(new MeetingTime(true, interval[1]));
        }
        Collections.sort(time);
        int max = 0;
        int rooms = 0;
        for (MeetingTime t : time) {
            if (!t.isEnd) {
                rooms++;
            } else {
                rooms--;
            }
            max = Math.max(rooms, max);
        }
        return max;
    }

    public static void main(String args[]) {
        LC00253MeetingRoomsII test = new LC00253MeetingRoomsII();
        System.out.println(test.minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}}));
        System.out.println(test.minMeetingRooms(new int[][]{{10,20},{0,10},{0,15}}));
    }
}

class MeetingTime implements Comparable<MeetingTime>{
    boolean isEnd;
    int time;
    public MeetingTime(boolean isEnd, int time) {
        this.isEnd = isEnd;
        this.time = time;
    }
    @Override
    public int compareTo(MeetingTime that) {
        if (this.time < that.time) {
            return -1;
        } else if (this.time > that.time) {
            return 1;
        } else if (this.isEnd && !that.isEnd) {
            return -1;
        } else if (!this.isEnd && that.isEnd) {
            return 1;
        } else {
            return 0;
        }
    }
}