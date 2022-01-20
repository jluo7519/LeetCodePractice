import javax.sound.midi.SysexMessage;
import java.util.*;

public class WordLadderIII {
    public static void main(String args[]) {
        String begin = "a";
        String end = "c";
        String[] wordArray = {"a","b","c"};
        Set<String> dict = new HashSet<>();
        for (String s: wordArray) {
            dict.add(s);
        }
        WordLadderIII test = new WordLadderIII();
        List<String> result = test.findShortestPath(begin, end, dict);
        System.out.println(result.toString());
    }
    public List<String> findShortestPath(String beginWord, String endWord, Set<String> dict) {
        //cc
        List<String> result = new LinkedList<>();
        Map<String, String> graph = new HashMap<>();
        findEdges(beginWord, endWord, dict, graph);
        System.out.println(graph.toString());
        String cur = endWord;
        while (cur != null) {
            result.add(0, cur);
            cur = graph.get(cur);
        }
        return result;
    }
    private void findEdges(String beginWord, String endWord, Set<String> dict, Map<String, String> graph) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        dict.remove(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                char[] curArray = cur.toCharArray();
                for (int i = 0; i < curArray.length; i++) {
                    char tmp = curArray[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == tmp) continue;
                        curArray[i] = c;
                        String next = String.valueOf(curArray);
                        if (dict.contains(next)) {
                            queue.offer(next);
                            dict.remove(next);
                            //记边 只记一条就够
                            graph.put(next, cur);
                            if (next.equals(endWord)) {
                                return;
                            }
                        }
                    }
                    //set back
                    curArray[i] = tmp;
                }
            }
        }
        //TODO: exception
    }
}
