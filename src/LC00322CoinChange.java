import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC00322CoinChange {
    // DP
    public int coinChange(int[] coins, int amount) {
        // c.c.
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String args[]) {
        int[] coins = {2,5,10,1};
        LC00322CoinChange test = new LC00322CoinChange();
        System.out.println(test.coinChange(coins, 27));
    }
    // DFS with pruning using array
    public int coinChangeBETTER(int[] coins, int amount) {
        // c.c.
        Integer[] mem = new Integer[amount + 1];
        int result = dfsBETTER(coins, amount, mem);
        return  result == Integer.MAX_VALUE ? -1 : result;
    }
    private int dfsBETTER(int[] coins, int amount, Integer[] mem) {
        if (mem[amount] != null) {
            return mem[amount];
        }
        if (amount == 0) {
            mem[amount] = 0;
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int nextAmt = amount - coin;
            if (nextAmt < 0) continue;
            min = Math.min(dfsBETTER(coins, nextAmt, mem), min);
        }
        mem[amount] = min == Integer.MAX_VALUE ? Integer.MAX_VALUE : min + 1;
        return mem[amount];
    }
    // DFS with Pruning using HashMap
    public int coinChangeDFS(int[] coins, int amount) {
        // c.c
        Map<Integer, Integer> mem = new HashMap<>();
        int res = dfs(coins, amount, mem);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    private int dfs(int[] coins, int amount, Map<Integer, Integer> mem) {
        if (mem.containsKey(amount)) {
            return mem.get(amount);
        }
        // success
        if (amount == 0) {
            mem.put(amount, 0);
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int nextAmt = amount - coin;
            if (nextAmt < 0) continue;
            int ret = dfs(coins, nextAmt, mem);
            min = Math.min(ret, min);
        }
        mem.put(amount, min == Integer.MAX_VALUE ? Integer.MAX_VALUE : min + 1);
        return mem.get(amount);
    }
}
