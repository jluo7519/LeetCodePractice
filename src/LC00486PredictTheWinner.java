public class LC00486PredictTheWinner {
    public boolean predictTheWinner(int[] nums) {
        if (nums == null || nums.length == 0) throw new IllegalArgumentException();
        int len = nums.length;
        int[][] dp = new int[len][len];
        int sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            sum += nums[i];
            for (int j = i; j < len; j++) {
                if (i == j) {
                    dp[i][j] = nums[i];
                    continue;
                }
                //edge case是处理错位情况，i + 2 <= j 是可以取到正常dp值的
                int takeI = Math.min(i + 2 <= j ? dp[i + 2][j] : 0,
                        i + 1 <= j - 1 ? dp[i + 1][j - 1] : 0) + nums[i];
                int takeJ = Math.min(i + 1 <= j - 1 ? dp[i + 1][j - 1] : 0,
                        i <= j - 2 ? dp[i][j - 2] : 0) + nums[j];
                dp[i][j] = Math.max(takeI, takeJ);
            }
        }
        //System.out.println(dp[0][len - 1]);
        return dp[0][len - 1] >= sum - dp[0][len - 1];
    }
/*    public boolean predictTheWinner(int[] nums) {
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
                //sum[i][j] = sum[i][j - 1] + nums[j];

                dp[i][j] = Math.max(sum[i + 1][j] - dp[i + 1][j] + nums[i],
                        sum[i][j - 1] - dp[i][j - 1] + nums[j]);
            }
        }
        return dp[0][len - 1] >= sum[0][len - 1] - dp[0][len - 1];
    }*/
    public boolean firstWillWin(int[] values) {
        if (values == null || values.length == 0) throw new IllegalArgumentException();
        int len = values.length;
        int[] dp = new int[len];
        dp[len - 1] = values[len - 1];
        int sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            sum += values[i];
            int take1 = Math.min(i + 2 < len ? dp[i + 2] : 0,
                    i + 3 < len ? dp[i + 3] : 0) + values[i];
            int take2 = Math.min(i + 3 < len ? dp[i + 3] : 0,
                    i + 4 < len ? dp[i + 4] : 0) + values[i]
                    + ((i + 1 < len) ? values[i + 1] : 0);
            dp[i] = Math.max(take1, take2);
        }
        return dp[0] >= sum - dp[0];
    }

    public static void main(String args[]) {
        LC00486PredictTheWinner test = new LC00486PredictTheWinner();
        System.out.println(test.predictTheWinner(new int[]{1, 5, 2}));
        System.out.println(test.predictTheWinner(new int[]{1, 5, 233, 7}));
        System.out.println(test.predictTheWinner(new int[]{1,1,1}));
        System.out.println(test.firstWillWin(new int[]{1, 5, 2}));
        System.out.println(test.firstWillWin(new int[]{1, 5, 233, 7}));
        System.out.println(test.firstWillWin(new int[]{1,1,1}));
    }
}