import java.util.Arrays;

public class LC00132PalindromePartitioningII {
    public int minCut(String s) {
        if (s == null || s.length() <= 0) throw new IllegalArgumentException();
        int len = s.length();
        //dp structure
        int[] dp = new int[len + 1];
        //initialization
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        //dp[i]: min # strings cut from [0, i)
        //i and j are dp index, not string index
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= i; j++) {
                //string idx = dpIdx - 1
                if (isPalindrome(s, j - 1, i - 1)) {
                    if (dp[j - 1] != Integer.MAX_VALUE) {
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    }
                }
            }
        }
        return dp[len] - 1;
    }

    public int optimizedMinCut(String s) {
        if (s == null || s.length() <= 0) throw new IllegalArgumentException();
        int len = s.length();
        //dp structure
        int[] dp = new int[len + 1];
//        Arrays.fill(dp, Integer.MAX_VALUE);
//        dp[len] = 0;
        boolean[][] isP= new boolean[len][len];//s[i, j] is palindrome
        for (int i = len - 1; i >= 0; i--) {//i, j string index
            dp[i] = len - i;//初始化就可以
            for (int j = i; j < len; j++) {
                if (i == j || (i + 1 == j && s.charAt(i) == s.charAt(j))) {
                    isP[i][j] = true;
                } else {
                    isP[i][j] = isP[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                }
                if (isP[i][j]) { //&& dp[j + 1] != Integer.MAX_VALUE
                    dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                }
            }
        }
        return dp[0] - 1;
    }
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

/*    private boolean[][] fillP(String s) {
        int len = s.length();
        boolean[][] isP= new boolean[len][len];//s[i, j] is palindrome
        for (int i = len - 1; i >= 0; i--) {//i, j string index
            for (int j = i; j < len; j++) {
                if (i == j || (i + 1 == j && s.charAt(i) == s.charAt(j))) {
                    isP[i][j] = true;
                } else {
                    isP[i][j] = isP[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                }
            }
        }
        return isP;
    }*/

    public static void main(String args[]) {
        LC00132PalindromePartitioningII test = new LC00132PalindromePartitioningII();
        System.out.println(test.optimizedMinCut("aab"));
        System.out.println(test.optimizedMinCut("a"));
        System.out.println(test.optimizedMinCut("ab"));
/*
        boolean[][] isP1 = test.fillP("aab");
        for (boolean[] row : isP1) {
            System.out.println(Arrays.toString(row));
        }
        boolean[][] isP2 = test.fillP("ab");
        for (boolean[] row : isP2) {
            System.out.println(Arrays.toString(row));
        }*/
    }
}
