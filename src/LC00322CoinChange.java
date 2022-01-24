import java.util.Arrays;

public class LC00322CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -1);
        memo[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0 && memo[i - coin] != -1) {
                    if (memo[i] == -1) {
                        memo[i] = memo[i - coin] + 1;
                    } else {
                        memo[i] = Math.min(memo[i], memo[i - coin] + 1);
                    }
                }
            }
        }
        return memo[amount];
    }
    public static void main(String args[]) {
        int[] coins = {2,5,10,1};
        LC00322CoinChange test = new LC00322CoinChange();
        System.out.println(test.coinChange(coins, 27));

    }
}
