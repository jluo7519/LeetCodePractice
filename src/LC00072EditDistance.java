public class LC00072EditDistance {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) throw new IllegalArgumentException();
        int len1 = word1.length();
        int len2 = word2.length();
        //1. structure: 2 searching states, i, j --> 2d int[][]
        int[][] dp = new int[len1 + 1][len2 + 1];
        //2. initialization
        dp[0][0] = 0;
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = j;
        }

        //i, j are dp index
        //stringIdx = dpIdx - 1
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                //depends on i-1, j-1
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {//3 choices take min
                    int insert = dp[i][j - 1] + 1;
                    int delete = dp[i - 1][j] + 1;
                    int replace = dp[i - 1][j - 1] + 1;
                    dp[i][j] = Math.min(Math.min(insert, delete), replace);
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String args[]) {
        LC00072EditDistance test = new LC00072EditDistance();
        String word1 = "horse", word2 = "ros";
        System.out.println(test.minDistance(word1, word2));

        String word3 = "intention", word4 = "execution";
        System.out.println(test.minDistance(word3, word4));
    }
}
