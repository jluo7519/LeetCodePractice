import java.util.*;

public class WordBreakMinCutPaths {
    static final int UNCUTTABLE = Integer.MAX_VALUE;
    public static void main(String args[]) {
        WordBreakMinCutPaths test = new WordBreakMinCutPaths();
        //test 1
        String s1 = "leetcode";
        Set<String> dict1 = new HashSet<>();
        dict1.add("leet");
        dict1.add("code");
        Map<Integer, List<Integer>> edge1 = new HashMap<>();
        test.wordBreakII(s1, dict1, edge1);

        //System.out.println(test.recoverOnePointFive(edge1, s1));
        System.out.println(edge1.toString());

        //test 2
        String s2 = "applepenapple";
        Set<String> dict2 = new HashSet<>();
        dict2.add("apple");
        dict2.add("pen");;
        Map<Integer, List<Integer>> edge2 = new HashMap<>();
        test.wordBreakII(s2, dict2, edge2);
        System.out.println(edge2.toString());
        //System.out.println(test.recoverOnePointFive(edge2, s2));
        //System.out.println(test.wordBreak(s2, dict2));

        String s3 = "catsandog";
        Set<String> dict3 = new HashSet<>();
        dict3.add("cats");
        dict3.add("dog");;
        dict3.add("sand");
        dict3.add("and");
        dict3.add("cat");
        Map<Integer, Integer> edge3 = new HashMap<>();
        //test.wordBreakOnePointFive(s3, dict3, edge3);
        //System.out.println(test.recoverOnePointFive(edge3, s3));
        //System.out.println(test.wordBreak(s3, dict3));


        //test 4
        String s4 = "catsanddog";
        Set<String> dict4 = new HashSet<>();
        dict4.add("cats");
        dict4.add("dog");;
        dict4.add("sand");
        dict4.add("and");
        dict4.add("cat");
        Map<Integer, List<Integer>> edge4 = new HashMap<>();
        System.out.println(test.wordBreakII(s4, dict4, edge4));
        System.out.println(edge4.toString());
    }
    //Q1. return min number of cuts
    public int wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0) return -1;
        int len = s.length();
        int[] dp = new int[len + 1];
        Arrays.fill(dp, UNCUTTABLE);
        dp[0] = 0;
        for (int i = 1; i <= len; i++) {
            int minPrevDp = UNCUTTABLE;
            for (int j = 0; j < i; j++) {
                if (dp[j] != UNCUTTABLE && dict.contains(s.substring(j, i))) {
                    minPrevDp = Math.min(dp[j], minPrevDp);
                }
            }
            if (minPrevDp != UNCUTTABLE) {
                dp[i] = minPrevDp + 1;
            }
        }
        return dp[len] == UNCUTTABLE ? -1 : dp[len] - 1;
    }
    //Q1.5. return any min number cut combinations
    public void wordBreakOnePointFive(String s, Set<String> dict, Map<Integer, Integer> edge) {
        if (s == null || s.length() == 0) return;
        int len = s.length();
        int[] dp = new int[len + 1];
        Arrays.fill(dp, UNCUTTABLE);
        dp[0] = 0;
        for (int i = 1; i <= len; i++) {
            int minPrevDp = UNCUTTABLE;
            for (int j = 0; j < i; j++) {
                if (dp[j] != UNCUTTABLE && dict.contains(s.substring(j, i))) {
                    minPrevDp = Math.min(dp[j], minPrevDp);
                    edge.put(i, j);//反着记边
                    //记边可以记切的位置
                }
            }
            if (minPrevDp != UNCUTTABLE) {
                dp[i] = minPrevDp + 1;
            }
        }
        if (dp[len] == UNCUTTABLE) throw new RuntimeException("uncuttable");
        //return dp[len] == UNCUTABLE ? -1 : dp[len] - 1;
    }
    private List<String> recoverOnePointFive(Map<Integer, Integer> edge, String s) {
        if (edge.isEmpty()) {
            throw new RuntimeException("uncuttable");
        }
        List<String> result = new LinkedList<>();
        int cur = s.length();
        while (edge.get(cur) != null) {
            result.add(0, s.substring(edge.get(cur), cur));
            cur = edge.get(cur);
        }
        return result;
    }
    //Q2. return all possible min cut combinations
    public List<List<String>> wordBreakII(String s, Set<String> dict, Map<Integer, List<Integer>> edges) {
        if (s == null || s.length() == 0) throw new IllegalArgumentException();
        int len = s.length();
        int[] dp = new int[len + 1];
        Arrays.fill(dp, UNCUTTABLE);
        dp[0] = 0;
        for (int i = 1; i <= len; i++) {
            int minPrevDp = UNCUTTABLE;
            for (int j = 0; j < i; j++) {
                if (dp[j] != UNCUTTABLE && dict.contains(s.substring(j, i))) {
                    //分成了三种情况，相等、大于、小于
                    //1. 新来的大于啥也不用干
                    //2. 新来的小于，value里放一组新的list
                    //3. 新来的等于，value的list拿出来加入新边
                    if (dp[j] < minPrevDp) {
                        List<Integer> from = new LinkedList<>();
                        from.add(j);
                        edges.put(i, from);
                    } else if (dp[j] == minPrevDp) {
                        edges.get(i).add(j);
                    }
                    minPrevDp = Math.min(dp[j], minPrevDp);
                }
            }
            if (minPrevDp != UNCUTTABLE) {
                dp[i] = minPrevDp + 1;
            }
        }
        if (dp[len] == UNCUTTABLE) throw new RuntimeException("uncuttable");
        List<List<String>> result = new ArrayList<>();
        recoverII(edges, s, result, new LinkedList<>(), s.length());
        return result;
    }
    private void recoverII(Map<Integer, List<Integer>> edges, String s, List<List<String>> result, List<String> path, int cur) {
        if (cur == 0) {
            result.add(new LinkedList<>(path));
            return;
        }

        List<Integer> branches = edges.get(cur);
        if (branches == null) return;
        for (Integer n : branches) {
            path.add(0, s.substring(n, cur));
            recoverII(edges, s, result, path, n);
            path.remove(0);
        }
    }
}
