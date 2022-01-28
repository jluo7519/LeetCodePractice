public class LC00121BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if (prices == null) throw new IllegalArgumentException();
        int len = prices.length;
//        int[] buy = new int[len];
//        int[] sell = new int[len];
        //buy[0] = -prices[0];
        int buy = -prices[0];
        int sell = 0;
        for (int i = 1; i < len; i++) {
//            buy[i] = Math.max(buy[i - 1], -prices[i]);
//            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
            int newBuy = Math.max(buy, -prices[i]);
            int newSell = Math.max(sell, buy + prices[i]);
            buy = newBuy;
            sell = newSell;
        }
        return sell;
    }
}
