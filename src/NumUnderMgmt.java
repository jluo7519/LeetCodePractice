import java.util.*;

public class NumUnderMgmt {
    public static Map<Character, Integer> numUnderManagement(List<List<Character>> pairs){
        Map<Character, List<Character>> graph = new HashMap<>(); // manager -> employee
        buildGraph(pairs, graph);
        Map<Character, Integer> res = new HashMap<>();
        Set<Character> visiting = new HashSet<>();

        for (List<Character> pair : pairs) {
            res.put(pair.get(0), 0);
            res.put(pair.get(1), 0); // default all zeros
        }
        for (Character person : res.keySet()) {
            int[] num = new int[]{-1}; // -1 to offset self
            if (dfs(person, graph, visiting, num, new HashSet<Character>())) {
                //throw new RuntimeException("circular");
                return new HashMap<>();
            }
            res.put(person, num[0]);
        }
        return res;
    }
    private static boolean dfs(char cur, Map<Character, List<Character>> graph, Set<Character> visiting, int[] num, Set<Character> visited) {
        if (!visiting.add(cur)) {
            return true;
        }
        num[0]++;
        List<Character> empUnderMgmt = graph.get(cur);
        if (empUnderMgmt != null) {
            for (char emp : empUnderMgmt) {
                if (!visited.contains(emp)) {
                    if (dfs(emp, graph, visiting, num, visited)) {
                        return true;
                    }
                }
            }
        }
        visiting.remove(cur);
        visited.add(cur);
        return false;
    }
    private static void buildGraph(List<List<Character>> pairs, Map<Character, List<Character>> graph) {
        // 0 is employee, 1 is manager
        for (List<Character> pair : pairs) {
            char employee = pair.get(0);
            char manager = pair.get(1);
            List<Character> employees = graph.getOrDefault(manager, new ArrayList<>());
            employees.add(employee);
            graph.put(manager, employees);
        }
    }

    public static void main(String args[]) {
        List<List<Character>> input = new ArrayList<>();
        input.add(Arrays.asList('A','F'));
        input.add(Arrays.asList('B','F'));
        input.add(Arrays.asList('C','A'));
        input.add(Arrays.asList('D','A'));
        input.add(Arrays.asList('D','B'));
        input.add(Arrays.asList('Z','B'));
        input.add(Arrays.asList('X','B'));
        input.add(Arrays.asList('E','D'));
        input.add(Arrays.asList('G','D'));
        System.out.println(numUnderManagement(input).toString());
        // expect {A=4, B=5, C=0, D=2, E=0, F=8, G=0, X=0, Z=0}

    }

}
