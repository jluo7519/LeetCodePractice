public class LC00312BurstBalloons {
    public int maxCoins(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len];

        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                for (int k = i; k <= j; k++) {
                    //edge case i <= 0, j >= len - 1
                    //i == j: initialization
                    int left = i == k ? 0 : dp[i][k - 1]; //注意 j == k 意思是左半端没得打了
                    int right = j == k ?  0 : dp[k + 1][j]; //注意 j == k 意思是右半段没得打了
                    int costK = nums[k] * (i > 0 ? nums[i - 1] : 1) * (j < len - 1 ? nums[j + 1] : 1);
                    dp[i][j] = Math.max(dp[i][j], left + right + costK);
                }
            }
        }
        return dp[0][len - 1];
    }

    public static void main(String args[]) {
        LC00312BurstBalloons test = new LC00312BurstBalloons();
        System.out.println("test 1 {3, 1, 5, 8}: " + test.maxCoins(new int[]{3, 1, 5, 8}));
        System.out.println("test 2 {1, 5}: " + test.maxCoins(new int[]{1, 5}));
    }
}
