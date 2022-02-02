import java.util.Arrays;

public class LC00096UniqueBinarySearchTrees {
    public int numTrees(int n) {
        if (n < 0) throw new IllegalArgumentException();
        if (n <= 2) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1; //dp[0] will never be touched
        for (int size = 2; size <= n; size++) {
            for (int k = 1; k <= size; k++) {
                int lefts = (k == 1 ? 1 : dp[k - 1]);
                int rights = (size == k ? 1 : dp[size - k]);
                dp[size] += lefts * rights;
            }
        }
        return dp[n];
    }
/*
    public int numTrees(int n) {
        if (n < 0) throw new IllegalArgumentException();
        if (n <= 2) return n;

        int[][] dp = new int[n + 1][n + 1];

        //注意大小依赖关系
        for (int start = n; start >= 1; start--) {
            for (int end = start; end <= n; end++) {
                if (start == end) {
                    dp[start][end] = 1;
                    continue;
                }
                for (int k = start; k <= end; k++) {
                    int lefts = (k - 1 > start ? dp[start][k - 1] : 1);
                    int rights = (k + 1 < end ? dp[k + 1][end] : 1);
                    dp[start][end] += lefts * rights;
                }
            }
        }
        return dp[1][n];
    }
*/

    public static void main(String args[]) {
        LC00096UniqueBinarySearchTrees test = new LC00096UniqueBinarySearchTrees();
        System.out.println(test.numTrees(3));
        System.out.println(test.numTrees(1));
    }
}
