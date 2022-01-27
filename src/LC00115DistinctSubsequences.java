public class LC00115DistinctSubsequences {
    public int numDistinct(String s, String t) {
        if (s == null || t == null) throw new IllegalArgumentException();
        int sLen = s.length();
        int tLen = t.length();
        int[][] dp = new int[tLen + 1][sLen + 1];
        //initialization: s空, t不为空就都是0
        //t为空, s不为空, 1 我们认为每个string有且只有一个empty string就是我们开区间的地方
        for (int j = 0; j <= sLen; j++) {
            dp[0][j] = 1;
        }
        for (int i = 0; i < tLen; i++) {//i, j string idx
            for (int j = 0; j < sLen; j++) {
                char chT = t.charAt(i);
                char chS = s.charAt(j);
                if (chT != chS) {
                    dp[i + 1][j + 1] = dp[i + 1][j];
                } else {
                    dp[i + 1][j + 1] = dp[i + 1][j] + dp[i][j];
                }
            }
        }
        return dp[tLen][sLen];
    }

    public static void main(String args[]) {
        LC00115DistinctSubsequences test = new LC00115DistinctSubsequences();
        System.out.println(test.numDistinct("rabbbit", "rabbit"));
        System.out.println(test.numDistinct("babgbag", "bag"));
    }
}
