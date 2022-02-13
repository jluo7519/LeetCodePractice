import java.util.*;

public class LC00218TheSkylineProblem {
    public List<List<Integer>> getSkyline(int[][] buildings) { // O(n log n)
        // c.c.
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < buildings.length; i++) { // O(n)
            int[] b = buildings[i];
            points.add(new Point(b[0], b[2], true, i));
            points.add(new Point(b[1], b[2], false, i));
        }
        Collections.sort(points); // O(n log n)
        List<List<Integer>> result = new ArrayList<>();
        // BST stores index to avoid duplicate, input index
        TreeSet<Integer> maxBST = new TreeSet<>((i1, i2) ->
            buildings[i2][2] != buildings[i1][2] ? buildings[i2][2] - buildings[i1][2] : i1 - i2
        );
        for (Point point : points) { // O(n log n)
            if (point.isStart) {
                if (maxBST.isEmpty() || buildings[maxBST.first()][2] < point.h) {
                    result.add(Arrays.asList(point.x, point.h));
                }
                //add
                maxBST.add(point.idx); // O(log n)
            } else { // isEnd
                maxBST.remove(point.idx); // O(log n)
                if (maxBST.isEmpty()) {
                    result.add(Arrays.asList(point.x, 0));
                } else if (buildings[maxBST.first()][2] < point.h) {
                    result.add(Arrays.asList(point.x, buildings[maxBST.first()][2]));
                }
            }
        }
        return result;
    }
    public static void main(String args[]) {
        LC00218TheSkylineProblem test = new LC00218TheSkylineProblem();
        int[][] buildings1 = new int[][]{{0,2,3},{2,5,3}};
        test.getSkyline(buildings1);
    }
    public List<List<Integer>> getSkylineS1(int[][] buildings) {
        if (buildings == null || buildings.length == 0 || buildings[0] == null || buildings[0].length != 3) {
            throw new IllegalArgumentException();
        }
        // initialize
        List<BuildingPoint> points = new ArrayList<>();
        for (int[] building : buildings) { // O(n)
            points.add(new BuildingPoint(building[0], building[2], true));
            points.add(new BuildingPoint(building[1], building[2], false));
        }
        // sort ascending with tiebreaker
        Collections.sort(points); // O(n log n)
        List<List<Integer>> result = new ArrayList<>();
        Queue<Integer> maxHeap = new PriorityQueue<>((Comparator.reverseOrder()));
        for (BuildingPoint point : points) { // O(n ^ 2)
            if (point.isStart) {
                if (maxHeap.isEmpty() || point.height > maxHeap.peek()) { // O(log n)
                    result.add(Arrays.asList(point.x, point.height));
                }
                maxHeap.offer(point.height);
            } else {
                maxHeap.remove(point.height); // O(n) locate + O(log n) remove
                if (maxHeap.isEmpty()) {
                    result.add(Arrays.asList(point.x, 0));
                } else if (maxHeap.peek() < point.height) {
                    result.add(Arrays.asList(point.x, maxHeap.peek()));
                }
            }
        }
        for (List<Integer> point : result) {
            System.out.print("(" + point.get(0) + "," + point.get(1) + ") ");
        }
        return result;
    }
}
class Point implements Comparable<Point> {
    int x;
    int h;
    int idx;
    boolean isStart;
    public Point(int x, int h, boolean isStart, int idx) {
        this.x = x;
        this.h = h;
        this.isStart = isStart;
        this.idx = idx;
    }
    @Override
    public int compareTo(Point that) {
        if (this.x != that.x) {
            return this.x - that.x;
        }
        // tie breaking
        if (this.isStart && that.isStart) {
            return that.h - this.h;
        } else if (!this.isStart && !that.isStart) {
            return this.h - that.h;
        } else { // one start one end
            // end taller than start: start first
            // start taller than end: start first
            return this.isStart ? -1 : 1;
        }
    }
}
class BuildingPoint implements Comparable<BuildingPoint>{
    int x;
    int height;
    boolean isStart;

    public BuildingPoint(int x, int height, boolean isStart) {
        this.x = x;
        this.height = height;
        this.isStart = isStart;
    }
    @Override
    public int compareTo(BuildingPoint that) {
        if (this.x == that.x) {
            if (this.isStart && that.isStart) {
                return that.height - this.height;
            } else if (!this.isStart && !that.isStart) {
                return this.height - that.height;
            } else {
                return this.isStart ? -1 : 1;
            }
        } else {
            return this.x - that.x;
        }
    }
    @Override
    public String toString() {
        return new String("(" + x + "," + height + "," + isStart + ") ");
    }
}
