import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC00139WordBreakI {
    //S1 dp
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return false;
        Set<String> dict = new HashSet<>();
        for (String word: wordDict) {
            dict.add(word);
        }
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }
        return dp[len];
    }
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
        System.out.println(test.wordBreakDFS(s, wordDict));

    }
}
