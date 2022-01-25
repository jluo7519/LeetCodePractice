import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.util.*;

public class TaskOrder {
    private static List<String> getTaskWithDependencies(List<Task> tasks, String dependsOn) {
        if (tasks == null || tasks.isEmpty() || dependsOn == null || dependsOn.length() == 0) {
            throw new IllegalArgumentException("Invalid Input");
        }
        List<String> results = new ArrayList<>();
        //construct graph
        Map<String, List<String>> graph = new HashMap<>();
        Set<String> dedup = new HashSet<>();
        for (Task task : tasks) {
            graph.put(task.getName(), task.getDependsOn());
        }
        Set<String> nonCircular = noCircularDependency(graph);

        getDependencies(tasks, dependsOn, results, dedup, nonCircular);
        return results;
    }

    private static Set<String> noCircularDependency(Map<String, List<String>> graph) {
        int numTasks = graph.size();
        Map<String, Integer> incomingEdges = new HashMap<>();
        //find # of incoming edges for each vertex
        for (String task : graph.keySet()) {
            incomingEdges.put(task, 0);
        }
        for (String task : graph.keySet()) {
            for (String dep : graph.get(task)) {
                int cnt = incomingEdges.get(dep);
                incomingEdges.put(dep, cnt + 1);
            }
        }

        Queue<String> queue = new LinkedList<>();
        for (String dep : incomingEdges.keySet()) {
            if (incomingEdges.get(dep) == 0) {
                queue.offer(dep);
            }
        }

        Set<String> nonCircular = new HashSet<>();
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            nonCircular.add(cur);
            for (String nei : graph.get(cur)) {
                int cntEdges = incomingEdges.get(nei);
                if (--cntEdges == 0) {
                    queue.offer(nei);
                }
                incomingEdges.put(nei, cntEdges);
            }
        }
        return nonCircular;
    }

    private static void getDependencies(List<Task> tasks, String dependsOn, List<String> results,
                                        Set<String> dedup, Set<String> nonCircular) {
        //if current dependsOn has been installed before
        //we don't need to go deep since we know all related dependencies has been installed
        if (dedup.contains(dependsOn)) {
            return;
        }
        //edge case: circular dependency
        if (!nonCircular.contains(dependsOn)) {
            throw new RuntimeException("there is Circular Dependency");
        }
        Task curTask = null;

        //find dependency task based on string, must assume no tasks have same name
        for (Task task : tasks) {
            String name = task.getName();
            if (name.equals(dependsOn)) {
                curTask = task;
            }
        }
        //no matching
        if (curTask == null) {
            return;
        }
        //base case
        List<String> dependencies = curTask.getDependsOn();
        if (dependencies == null || dependencies.isEmpty()) {
            dedup.add(dependsOn);
            results.add(dependsOn);
            return;
        }
        // go deep, and add to result
        for (String dependency : dependencies) {
            getDependencies(tasks, dependency, results, dedup, nonCircular);
        }
        results.add(dependsOn);
        dedup.add(dependsOn);
    }

    public static void main(String[] args) {
        JUnitCore.main("TaskOrder");
    }

    @Test
    public void testGetTaskDependenciesForApplicationA() {
        Assert.assertEquals(
                Arrays.asList(
                        "storage",
                        "mongo",
                        "application A"
                ),
                getTaskWithDependencies(TaskList.getTasks(), "application A")
        );
        Assert.assertEquals(
                Arrays.asList(
                        "memcache",
                        "application B"
                ),
                getTaskWithDependencies(TaskList.getTasks(), "application B")
        );
    }
}/**
 * Definition of a Task
 */
class Task {
    private final String name;
    private final List<String> dependsOn;

    Task(String name) {
        this(name, new ArrayList<>());
    }

    Task(String name, List<String> dependsOn) {
        this.name = name;
        this.dependsOn = dependsOn;
    }

    public String getName() { return this.name; }
    public List<String> getDependsOn() { return this.dependsOn; }
}

class TaskList {
    public static List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();

        tasks.add(new Task("application A", Arrays.asList("mongo")));
        tasks.add(new Task("storage"));
        tasks.add(new Task("mongo", Arrays.asList("storage")));
        tasks.add(new Task("memcache"));
        tasks.add(new Task("application B", Arrays.asList("memcache")));
        return tasks;
    }
}
//                                application A
//                            /              \
//                          mongo                application B
//                        /       \            /      \
//                      storage   xxx                (mongo)
//                                                        \
//                  storage.getDependsOn is empty        (storage)
