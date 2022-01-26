import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC00139WordBreakI {
    //S1 DP
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) throw new IllegalArgumentException();
        Set<String> dict = new HashSet<>(wordDict);
        int len = s.length();
        //initialization
        boolean[] dp = new boolean[len + 1];//多开一位因为""
        dp[0] = true;
        //从后往前填array idx和string idx同步了
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= i; j++) {
                //i, j are dp index
                //string index = dp index - 1
                String word = s.substring(j - 1, i);//[j - 1, i - 1]
                if (dict.contains(word) && dp[j - 1]) {
                    dp[i] = true;
                }
            }
        }
        return dp[len];
    }
/*    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) throw new IllegalArgumentException();
        Set<String> dict = new HashSet<>(wordDict);
        int len = s.length();
        //initialization
        boolean[] dp = new boolean[len + 1];//多开一位因为""
        dp[len] = true;
        //从后往前填array idx和string idx同步了
        for (int i = len - 1; i >= 0; i--) {
            for (int j = len - 1; j >= i; j--) {
                String word = s.substring(i, j + 1);//[i, j]
                if (dict.contains(word) && dp[j + 1]) {
                    dp[i] = true;
                }
            }
        }
        return dp[0];
    }*/
    //S2 DFS
    public boolean wordBreakDFS(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return false;
        Boolean[] mem = new Boolean[s.length() + 1];
        Set<String> dict = new HashSet<>(wordDict);
        return dfs(s, 0, dict, mem);
    }

    private boolean dfs(String s, int index, Set<String> dict, Boolean[] mem) {
        int len = s.length();
        if (mem[index] != null) return mem[index];
        //success
        if (index == len) {
            return true;
        }
        //failure? no

        for (int i = index; i < len; i++) {
            String substr = s.substring(index, i + 1);
            if (dict.contains(substr) && (dfs(s, i + 1, dict, mem))){
                mem[index] = true;
                return true;
            }
        }
        mem[index] = false;
        return false;
    }

    public static void main(String args[]) {
        LC00139WordBreakI test = new LC00139WordBreakI();
        String s = "leetcode";

        String[] wordsArr = {"leet","code"};
        List<String> wordDict = Arrays.asList(wordsArr);
        System.out.println(test.wordBreak(s, wordDict));

        String s2 = "a";

        String[] wordsArr2 = {"a"};
        List<String> wordDict2 = Arrays.asList(wordsArr2);
        System.out.println(test.wordBreak(s2, wordDict2));
    }
}
