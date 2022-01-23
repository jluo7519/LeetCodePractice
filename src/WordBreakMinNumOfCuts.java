import java.util.*;

public class WordBreakMinNumOfCuts {
    public static void main(String args[]) {
        WordBreakMinNumOfCuts test = new WordBreakMinNumOfCuts();
        //test 1
        String s1 = "leetcode";
        Set<String> dict1 = new HashSet<>();
        dict1.add("leet");
        dict1.add("code");
        System.out.println(test.minNumCutsDFS(s1, dict1));

        //test 2
        String s2 = "applepenapple";
        Set<String> dict2 = new HashSet<>();
        dict2.add("apple");
        dict2.add("pen");;
        System.out.println(test.minNumCutsDFS(s2, dict2));

        String s3 = "catsandog";
        Set<String> dict3 = new HashSet<>();
        dict3.add("cats");
        dict3.add("dog");;
        dict3.add("sand");
        dict3.add("and");
        dict3.add("cat");
        System.out.println(test.minNumCutsDFS(s3, dict3));

        //test 4
        String s4 = "catsanddog";
        Set<String> dict4 = new HashSet<>();
        dict4.add("cats");
        dict4.add("dog");;
        dict4.add("sand");
        dict4.add("and");
        dict4.add("cat");
        System.out.println(test.minNumCutsDFS(s4, dict4));
    }
    //S1: DP
    public int minNumCutsDP(String s, Set<String> dict) {
        if (s == null || s.length() == 0 || dict == null || dict.size() == 0) throw new IllegalArgumentException();
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        //def of dp[i] = min # of parts such that words from [0, i) are all from dict, so min # of cuts is dp[i] - 1
        int len = s.length();
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] != Integer.MAX_VALUE && dict.contains(s.substring(j, i))) {
                        dp[i] = Math.min(dp[j] + 1, dp[i]);
                }
            }
        }
        return dp[len] == Integer.MAX_VALUE ? -1 : dp[len] - 1;
    }
    //S2: dfs
    public int minNumCutsDFS(String s, Set<String> dict) {
        if (s == null || s.length() == 0 || dict == null || dict.size() == 0) throw new IllegalArgumentException();
        int[] mem = new int[s.length() + 1];
        Arrays.fill(mem, Integer.MAX_VALUE);
        //        int res = dfs(s, 0, dict);
        int res = dfsPruning(s, 0, dict, mem);
        return res == Integer.MAX_VALUE ? -1 : res - 1;

    }
    private int dfsPruning(String s, int index, Set<String> dict, int[] mem) {
        int len = s.length();
        if (mem[index] != Integer.MAX_VALUE) return mem[index];
        if (index == len) {
            mem[index] = 0;
            return 0;
        }
        int curMin = Integer.MAX_VALUE;
        for (int i = index; i < len; i++) {
            String substr = s.substring(index, i + 1);
            if (dict.contains(substr)) {
                int ret = dfsPruning(s, i + 1, dict, mem);
                if (ret != Integer.MAX_VALUE) {
                    curMin = Math.min(ret + 1, curMin);
                }
            }
        }
        mem[index] = curMin;
        return curMin;
    }
    private int dfs(String s, int index, Set<String> dict) {
        int len = s.length();
        if (index == len) {
            return 0;
        }
        int curMin = Integer.MAX_VALUE;
        for (int i = index; i < len; i++) {
            String substr = s.substring(index, i + 1);
            if (dict.contains(substr)) {
                int ret = dfs(s, i + 1, dict);
                if (ret != Integer.MAX_VALUE) {
                    curMin = Math.min(ret + 1, curMin);
                }
            }
        }
        return curMin;
    }
}
