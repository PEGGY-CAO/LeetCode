public class MaxStockProfit {

    public static int maxProfit(int[] prices) {
        int maxProfit = 0;

        int buy = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < buy) {
                buy = prices[i];
            } else {
                maxProfit = prices[i] - buy > maxProfit ? prices[i] - buy : maxProfit;
            }
        }

        return maxProfit;

    }

    public static void main(String[] args) {
        int[] prices = {25, 5, 13, 1, 6, 10};
        System.out.println(maxProfit(prices));
    }
}

