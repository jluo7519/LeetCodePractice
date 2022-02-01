import java.util.Arrays;

public class LC00123BestTimeToBuyAndSellStockIII {
/*    public int maxProfit(int[] prices) {
        if (prices == null) throw new IllegalArgumentException();
        int len = prices.length;
        if (len < 2) return 0;

        int[][] buy = new int[3][len];
        int[][] sell = new int[3][len];
        for (int k = 1; k <= 2; k++) {
            buy[k][0] = -prices[0];
            for (int i = 1; i < len; i++) {
                buy[k][i] = Math.max(buy[k][i - 1], sell[k - 1][i - 1] - prices[i]);
                sell[k][i] = Math.max(sell[k][i - 1], buy[k][i - 1] + prices[i]);
            }
        }
        return sell[2][len - 1];
    }
    */
    public int maxProfit(int[] prices) {
        if (prices == null) throw new IllegalArgumentException();
        int len = prices.length;
        if (len < 2) return 0;
        int buy1 = -prices[0];
        int buy2 = -prices[0];
        int sell1 = 0;
        int sell2 = 0;

        for (int i = 1; i <len; i++) {
            int newBuy1 = Math.max(buy1, -prices[i]);
            int newSell1 = Math.max(sell1, buy1 + prices[i]);

            int newBuy2 = Math.max(buy2, sell1 - prices[i]);
            int newSell2 = Math.max(sell2, buy2 + prices[i]);
            buy1 = newBuy1;
            buy2 = newBuy2;
            sell1 = newSell1;
            sell2 = newSell2;
        }
        return sell2;
    }

    public static void main(String args[]) {
        LC00123BestTimeToBuyAndSellStockIII test = new LC00123BestTimeToBuyAndSellStockIII();
        System.out.println(test.maxProfit(new int[]{3,3,5,0,0,3,1,4}));
        System.out.println(test.maxProfit(new int[]{1,2,3,4,5}));
        System.out.println(test.maxProfit(new int[]{7,6,4,3,1}));
    }
}
