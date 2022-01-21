import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC00139WordBreakI {

    public static void main(String args[]) {
        LC00139WordBreakI test = new LC00139WordBreakI();
        String s = "leetcode";

        String[] wordsArr = {"leet","code"};
        List<String> wordDict = Arrays.asList(wordsArr);
        System.out.println(test.wordBreak(s, wordDict));

    }
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
}
