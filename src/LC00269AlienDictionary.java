import java.util.*;

public class LC00269AlienDictionary {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            throw new IllegalArgumentException();
        }
        int[] status = new int[26];
        StringBuilder order = new StringBuilder();
        Map<Character, Set<Character>> graph = new HashMap<>();
        if (buildGraph(words, graph)) {
            return "";
        }
        for (char c : graph.keySet()) {
            if (dfs(c, graph, status, order)) {
                return "";
            }
        }
        return order.reverse().toString();
    }
    private boolean dfs(char c, Map<Character, Set<Character>> graph, int[] status, StringBuilder order) {
        int idx = c - 'a';
        if (status[idx] == 1) return true; // circle
        if (status[idx] == 2) return false; // no circle here
        status[idx] = 1; // visiting
        Set<Character> nexts = graph.get(c);
        if (nexts != null) {
            for (char ch : nexts) {
                if (dfs(ch, graph, status, order)) {
                    return true;
                }
            }
        }
        status[idx] = 2; // set to visited
        order.append(c);
        return false;
    }
    private boolean buildGraph(String[] words, Map<Character, Set<Character>> graph) {
        for (String word : words) { // to make sure every char in words is included in graph
            int len = word.length();
            for (int i = 0; i < len; i++) {
                char c = word.charAt(i);
                if (!graph.containsKey(c)) {
                    graph.put(c, new HashSet<>());
                }
            }
        }
        String curWord = words[0];
        for (int i = 1; i < words.length; i++) {
            String nextWord = words[i];
            if (curWord.length() > nextWord.length() && curWord.startsWith(nextWord)) {
                return true;
            }
            int shorter = Math.min(curWord.length(), nextWord.length());
            for (int j = 0; j < shorter; j++) {
                char cCur = curWord.charAt(j);
                char cNext = nextWord.charAt(j);
                if (cCur != cNext) {
                    graph.get(cCur).add(cNext);
                    break;
                }
            }
            curWord = nextWord;
        }
        return false;
    }

    public static void main(String args[]) {
        LC00269AlienDictionary test = new LC00269AlienDictionary();
        System.out.println(test.alienOrder(new String[]{"wrt","wrf","er","ett","rftt"}));
        System.out.println(test.alienOrder(new String[]{"z","x"}));
        System.out.println(test.alienOrder(new String[]{"z","z"}));
    }
}
