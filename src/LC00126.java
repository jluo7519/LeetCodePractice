import java.util.*;

class LC00126 {
    public static void main(String args[]) {
        LC00126 test = new LC00126();

        String beginWord = "a";
        String endWord = "c";
        String[] wordArray = {"a","b","c"};
        List<String> wordList = Arrays.asList(wordArray);
        List<List<String>> result = test.findLadders(beginWord, endWord, wordList);
        for (List<String> list : result) {
            System.out.println(list.toString());
        }
    }
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        //cc
        List<List<String>> result = new ArrayList<>();
        Set<String> dict = new HashSet<>();
        for (String w : wordList) {
            dict.add(w);
        }
        findPaths2(beginWord, endWord, dict, result);
        return result;
    }
    //S2: 反向记边 反向recover
    private void findPaths2(String beginWord, String endWord, Set<String> dict, List<List<String>> result) {
        Queue<String> queue = new LinkedList<>();
        Map<String, List<String>> graph = new HashMap<>();
        queue.offer(beginWord);
        dict.remove(beginWord);
        boolean flag = false;//indicate its last level
        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<String> thisLevelVisited = new HashSet<>();
            while (size-- > 0) {
                String cur = queue.poll();
                //convert
                char[] curArray = cur.toCharArray();
                for (int i = 0; i < curArray.length; i++) {
                    char tmp = curArray[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == tmp) {
                            continue;
                        }
                        curArray[i] = c;
                        String next = new String(curArray);

                        if (dict.contains(next)) {//hasn't been converted
                            if (next.equals(endWord)) {
                                flag = true;
                            }
                            //2 senarios
                            if (!thisLevelVisited.contains(next)) {
                                //mark this level visited
                                //if not visited this level (its new)
                                //add to queue
                                //record edge
                                thisLevelVisited.add(next);
                                queue.offer(next);
                            }   //else already visited this level
                            //record edge, dont add to queue
                            //记边
                            List<String> from = graph.getOrDefault(next, new ArrayList<>());
                            from.add(cur);
                            graph.put(next, from);
                        }
                    }
                    //set back
                    curArray[i] = tmp;
                }
            }
            dict.removeAll(thisLevelVisited);
            if (flag) {
                System.out.println(graph.toString());
                List<String> path = new ArrayList<>();
                path.add(endWord);
                recoverPath2(endWord, beginWord, graph, path, result);
                //path.add(0, beginWord);
                return;
            }
        }
    }
    private void recoverPath2(String beginWord, String endWord, Map<String,
            List<String>> graph, List<String> path, List<List<String>> result) {

        if (beginWord.equals(endWord)) {
            result.add(new LinkedList<>(path));
            return;
        }
        List<String> neis = graph.get(beginWord);
        if (neis == null) return;
        for (String s : neis) {
            path.add(0, s);
            recoverPath2(s, endWord, graph, path, result);
            path.remove(0);
        }
    }

    //S1: 正向记边 正向recover
    private void findPaths1(String beginWord, String endWord, Set<String> dict, List<List<String>> result) {
        Queue<String> queue = new LinkedList<>();
        Map<String, List<String>> graph = new HashMap<>();
        queue.offer(beginWord);
        dict.remove(beginWord);
        boolean flag = false;//indicate its last level
        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<String> thisLevelVisited = new HashSet<>();
            while (size-- > 0) {
                String cur = queue.poll();
                //convert
                char[] curArray = cur.toCharArray();
                for (int i = 0; i < curArray.length; i++) {
                    char tmp = curArray[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == tmp) {
                            continue;
                        }
                        curArray[i] = c;
                        String next = new String(curArray);

                        if (dict.contains(next)) {//hasn't been converted
                            if (next.equals(endWord)) {
                                flag = true;
                            }
                            //2 senarios
                            if (!thisLevelVisited.contains(next)) {
                                //mark this level visited
                                //if not visited this level (its new)
                                //add to queue
                                //record edge
                                thisLevelVisited.add(next);
                                queue.offer(next);
                            }   //else already visited this level
                            //record edge, dont add to queue
                            //记边
                            List<String> neis = graph.getOrDefault(cur, new ArrayList<>());
                            neis.add(next);
                            graph.put(cur, neis);
                        }
                    }
                    //set back
                    curArray[i] = tmp;
                }
            }
            dict.removeAll(thisLevelVisited);
            if (flag) {
                System.out.println(graph.toString());
                List<String> path = new ArrayList<>();
                path.add(beginWord);
                recoverPath1(beginWord, endWord, graph, path, result);
                return;
            }
        }
    }
    private void recoverPath1(String beginWord, String endWord, Map<String,
            List<String>> graph, List<String> path, List<List<String>> result) {

        if (beginWord.equals(endWord)) {
            result.add(new ArrayList<>(path));
            return;
        }
        List<String> neis = graph.get(beginWord);
        if (neis == null) return;
        for (String s : neis) {
            path.add(s);
            recoverPath1(s, endWord, graph, path, result);
            path.remove(path.size() - 1);
        }
    }
}