public class LC00309BestTimeToBuyAndSellStockWithCooldown {
    public int maxProfit(int[] prices) {
        if (prices == null) throw new IllegalArgumentException();
        int len = prices.length;
        if (len < 2) return 0;
        int buy = -prices[0];
        int sell = 0;
        int prevSell = 0;

        for (int i = 1; i < len; i++) {
            int newBuy = Math.max(buy, i - 2 < 0 ? -prices[i] : prevSell - prices[i]);
            int newSell = Math.max(sell, buy + prices[i]);
            prevSell = sell;
            sell = newSell;
            buy = newBuy;
        }
        return sell;
    }
/*    public int maxProfit(int[] prices) {
        if (prices == null) throw new IllegalArgumentException();
        int len = prices.length;
        if (len < 2) return 0;

        int[] buy = new int[len];
        int[] sell = new int[len];
        buy[0] = -prices[0];

        for (int i = 1; i < len; i++) {
            buy[i] = Math.max(buy[i - 1], i - 2 < 0 ? -prices[i] : sell[i - 2] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[len - 1];
    }*/

    public static void main(String args[]) {
        LC00309BestTimeToBuyAndSellStockWithCooldown test = new LC00309BestTimeToBuyAndSellStockWithCooldown();
/*        System.out.println(test.maxProfit(new int[] {1, 2, 3, 0, 2}));
        System.out.println(test.maxProfit(new int[] {1}));*/
        System.out.println(test.maxProfit(new int[] {2, 1, 4}));
    }
}
