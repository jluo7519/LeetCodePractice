import java.util.Arrays;

public class IndexBestTimeToBuyAndSellStockFollowUp {
    public int[] oneTransaction(int[] prices) {
        int len = prices.length;
//        int[] buy = new int[len];
//        int[] sell = new int[len];
        int buy = -prices[0];
        int sell = 0;

        int globalSell = 0;
        int globalBuy = 0;
        int buyIdx = 0;
        int newBuy = buy;
        int newSell = 0;
//        buy[0] = -prices[0];
        for (int i = 1; i < len; i++) {
            //sell
//            if (prices[i] + buy[i - 1] > sell[i - 1]) {
            if (prices[i] + buy > sell) {
                //better sell
//                sell[i] = buy[i - 1] + prices[i];
                newSell = buy + prices[i];
                globalBuy = buyIdx;
                globalSell = i;
            }
//            else {
////                sell[i] = sell[i - 1];
//                newSell = sell;
//            }

            //buy
//            if (-prices[i] > buy[i - 1]) {
            if (-prices[i] > buy) {
//                buy[i] = -prices[i];
                newBuy = -prices[i];
                buyIdx = i;
            }
//            else {
////                buy[i] = buy[i - 1];
//                newBuy = buy;
//            }

            buy = newBuy;
            sell = newSell;
        }
        return new int[] {globalBuy, globalSell};
    }

/*    public int[][] twoTransactions(int[] prices) {

    }*/

    public static void main(String args[]) {
        IndexBestTimeToBuyAndSellStockFollowUp test = new IndexBestTimeToBuyAndSellStockFollowUp();
        int[] test1 = test.oneTransaction(new int[] {2, 4, 5, 1, 6, 7});
        System.out.println("" + test1[0] + ", " + test1[1]);
        int[] test2 = test.oneTransaction(new int[] {2, 8, 5, 6, 6, 2});
        System.out.println("" + test2[0] + ", " + test2[1]);
        int[] test3 = test.oneTransaction(new int[] {2, 8, 5, 6, 6, 105});
        System.out.println("" + test3[0] + ", " + test3[1]);
    }
}
