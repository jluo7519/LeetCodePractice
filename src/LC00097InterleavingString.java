import java.util.Arrays;

public class LC00097InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            throw new IllegalArgumentException();
        }
        int len1 = s1.length();
        int len2 = s2.length();
        if (s3.length() != len1 + len2) {
            return false;
        }
        //dp[i][j] means s1[0, i] s1[0, j] are interleaving substring of s3
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        //initialize
        dp[0][0] = true;
        for (int i = 0; i <= len1; i++) dp[i][0] = s1.substring(0, i).equals(s3.substring(0, i));
        for (int j = 0; j <= len2; j++) dp[0][j] = s2.substring(0, j).equals(s3.substring(0, j));
        for (int i = 1; i <= len1; i++) {//i, j are dp index
            //initialize
            for (int j = 1; j <= len2; j++) {// strIdx = dpIdx - 1
                char chK = s3.charAt(i + j - 1);
                char chI = s1.charAt(i - 1);
                char chJ = s2.charAt(j - 1);
                if (chI == chK) {
                    dp[i][j] = dp[i - 1][j] || dp[i][j];
                }
                if (chJ == chK) {
                    dp[i][j] = dp[i][j - 1] || dp[i][j];
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String args[]) {
        LC00097InterleavingString test = new LC00097InterleavingString();
        System.out.println(test.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(test.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        System.out.println(test.isInterleave("", "", ""));
    }
}
