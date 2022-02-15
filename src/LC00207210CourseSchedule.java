import java.util.*;

public class LC00207210CourseSchedule {
    // LC210 taking order
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // c.c.
        Map<Integer, List<Integer>> graph = buildGraph(prerequisites);
        int[] status = new int[numCourses];  //0: initial; 1: visiting; 2: visited
        int[] order = new int[numCourses];
        int[] idx = new int[]{numCourses - 1};
        for (int i = 0; i < numCourses; i++) {
            if (findOrder(i, status, graph, order, idx)) {
                return new int[]{};
            }
        }
        return order;
    }
    // DFS Helper
    private boolean findOrder(int cur, int[] status, Map<Integer, List<Integer>> graph, int[] order, int[] idx) {
        if (status[cur] == 2) return false; // visited no circle
        if (status[cur] == 1) return true; // visiting, circle
        status[cur] = 1; // set to visiting
        List<Integer> nexts = graph.get(cur);
        if (nexts != null) {
            for (int next : nexts) {
                if (findOrder(next, status, graph, order, idx)) {
                    return true;
                }
            }
        }
        status[cur] = 2; // set to visited
        order[idx[0]--] = cur;
        return false;
    }
    private Map<Integer, List<Integer>> buildGraph(int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] p : prerequisites) {
            List<Integer> nexts = graph.getOrDefault(p[1], new ArrayList<>());
            nexts.add(p[0]);
            graph.put(p[1], nexts);
        }
        return graph;
    }
    // LC207 Course Schedule I check circular dependency
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        Map<Integer, List<Integer>> graph = buildGraph(prerequisites);
        int[] status = new int[numCourses];  //0: initial; 1: visiting; 2: visited
        for (int i = 0; i < numCourses; i++) {
            if (circularDependency(i, status, graph)) {
                return false;
            }
        }
        return true;
    }
    private boolean circularDependency(int cur, int[] status, Map<Integer, List<Integer>> graph) {
        if (status[cur] == 2) { // visited
            return false;
        }
        if (status[cur] == 1) { // visiting
            return true;
        }
        status[cur] = 1;
        List<Integer> nexts = graph.get(cur);
        if (nexts != null) {
            for (Integer next : nexts) {
                if (circularDependency(next, status, graph)) {
                    return true;
                }
            }
        }
        status[cur] = 2; // visited
        return false;
    }


    public static void main(String args[]) {
        LC00207210CourseSchedule test = new LC00207210CourseSchedule();
        System.out.println(Arrays.toString(test.findOrder(2, new int[][]{{1,0}})));
        System.out.println(Arrays.toString(test.findOrder(2, new int[][]{{1,0},{0,1}})));
        System.out.println(Arrays.toString(test.findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}})));
    }
}
