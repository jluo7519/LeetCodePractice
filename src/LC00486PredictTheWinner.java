public class LC00486PredictTheWinner {
    public boolean PredictTheWinner(int[] nums) {
        if (nums == null || nums.length == 0) throw new IllegalArgumentException();
        int len = nums.length;
        int[][] dp = new int[len][len];
        int[][] sum = new int[len][len];
        //dp[i][j] 当前玩家[i, j]上能取到的max value
        //initialization: 不用，初始两个人都是0
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                //考虑edge case i == j 也是初始化
                if (i == j) {
                    dp[i][j] = nums[i]; //这样不会touch到后面出界的case
                    sum[i][j] = nums[i];
                    continue;
                }
                sum[i][j] = sum[i + 1][j] + nums[i];
                dp[i][j] = Math.max(sum[i + 1][j] - dp[i + 1][j] + nums[i],
                        sum[i][j - 1] - dp[i][j - 1] + nums[j]);
            }
        }
        return dp[0][len - 1] >= sum[0][len - 1] - dp[0][len - 1];
    }
    public static void main(String args[]) {
        LC00486PredictTheWinner test = new LC00486PredictTheWinner();
        System.out.println(test.PredictTheWinner(new int[]{1, 5, 2}));
        System.out.println(test.PredictTheWinner(new int[]{1, 5, 233, 7}));
    }
}