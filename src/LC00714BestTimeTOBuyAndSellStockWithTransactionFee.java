public class LC00714BestTimeTOBuyAndSellStockWithTransactionFee {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null) throw new IllegalArgumentException();
        int len = prices.length;
        if (len < 2) return 0;
        int buy = -prices[0];
        int sell = 0;

        for (int i = 1; i < len; i++) {
            int newBuy = Math.max(buy, sell - prices[i]);
            int newSell = Math.max(sell, buy + prices[i] - fee);
            buy = newBuy;
            sell = newSell;
        }
        return sell;
    }
/*    public int maxProfit(int[] prices, int fee) {
        if (prices == null) throw new IllegalArgumentException();
        int len = prices.length;
        if (len < 2) return 0;
        int[] buy = new int[len];
        int[] sell = new int[len];
        buy[0] = -prices[0];

        for (int i = 1; i < len; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee);
        }
        return sell[len - 1];
    }*/
}
