import java.util.*;

public class LC00218TheSkylineProblem {
    public List<List<Integer>> getSkyline(int[][] buildings) {
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

    public static void main(String args[]) {
        LC00218TheSkylineProblem test = new LC00218TheSkylineProblem();
        int[][] buildings1 = new int[][]{{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        test.getSkyline(buildings1);
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