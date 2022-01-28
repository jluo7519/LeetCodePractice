public class LC00188BestTimeToBuyAndSellStockIV {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || k < 0) throw new IllegalArgumentException();
        int len = prices.length;
        if (len == 0 || k == 0) return 0;
        int[][] buy = new int[k + 1][len];
        int[][] sell = new int[k + 1][len];
        //buy[1][0] = -prices[0];
        for (int kk = 1; kk <= k; kk++) {
            buy[kk][0] = -prices[0]; //kk = 1就是第一天刚开始所以只看第一天; kk > 1就相当于前几次买卖同一个价第一天都做了 所以买还是第一天价
            for (int i = 1; i < len; i++) {
                sell[kk][i] = Math.max(sell[kk][i - 1], buy[kk][i - 1] + prices[i]);
                buy[kk][i] = Math.max(buy[kk][i - 1], sell[kk - 1][i - 1] - prices[i]);
            }
        }
        return sell[k][len - 1];
    }
    public static void main(String args[]) {
        LC00188BestTimeToBuyAndSellStockIV test = new LC00188BestTimeToBuyAndSellStockIV();
        System.out.println(test.maxProfit(2, new int[] {2,4,1}));
        System.out.println(test.maxProfit(2, new int[] {3, 2, 6, 5, 0, 3}));
    }
}
