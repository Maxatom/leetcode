package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shibing
 * @since 2018/12/1 18:40
 */
public class BestTimeToBuyAndSellStockIII {
    public static void main(String[] args) {
        BestTimeToBuyAndSellStockIII stockIII=new BestTimeToBuyAndSellStockIII();
        List<String> testCases=new ArrayList<>();
        testCases.add("");
        testCases.add("[]");
        testCases.add("[8]");
        testCases.add("[8,2]");
        testCases.add("[0,2]");
        testCases.add("[0,2,4]"); //:5
        testCases.add("[9,2,4]");
        testCases.add("[9,7,4]");
        testCases.add("[7,7,7]");
        testCases.add("[0,2,4, 6]");
        testCases.add("[7,2,4 , 19]");//:10
        testCases.add("[3,3,5,0,0,3,1,4]");
        testCases.add("[1,2,3,4,5]");
        testCases.add("[7,6,4,3,1]");

        int i=0;
        for(String s: testCases){
//            if(++i>=21) break;
            System.out.printf("------------------------------------\n cases:");
            int[] testArray= PrintUtils.convertStringToIntArray(s);
//            int[] testArray=ArrayGenerator.intArray(i+14, 0,20);
            PrintUtils.printArray(testArray);
            int result=stockIII.maxProfit(testArray);
            int result1=stockIII.maxProfit1(testArray);
            int result2=stockIII.maxProfit2(testArray);
            int result3=stockIII.maxProfit3(testArray);
            System.out.printf("resut=%d , result1=%d, result2=%d, result3=%d\n", result, result1, result2, result3);
            if(result!=result1||result1!=result2||result2!=result3) throw new RuntimeException("Error Cases="+s);
        }

    }

    /**
     * dp[i][j] 表示，进行 i次交易， 从0-j 天范围内，可以取得的最大的收益。
     * 那么，
     * dp[i][j] 可能有两种取值。
     * 第一， prices[j] 在整个买卖策略中，被卖掉了
     * 那么，一定是 [0, p] 完成了 i - 1次交易，然后在 p~j 天之间买入prices[x]  x in [p, j], 然后再在第 j 天 卖掉prices[j], 完成了 i 次交易。
     * p << [0, j - 1]
     *
     * 第二， prices[j] 在整个买卖策略中，并没有被卖掉。
     * 此时最大值是， dp[i][j - 1]
     *
     * 于是：
     * dp[i][j] = Math.max(dp[i][j - 1], maxProfit + prices[j]);
     * maxProfit 不断地记录， dp[i - 1][p] - prices[p] 的最大值
     *
     * @param prices 股票价格数组
     * @return 最大收益
     */
    public int maxProfit(int[] prices) {
        if(prices.length<2) return 0;
        int[][] trans=new int[3][prices.length];
        for (int i = 1; i <= 2; i++) {
            int maxProfitTemp = -prices[0];
            for (int j = 1; j < prices.length; j++) {
                trans[i][j] = Math.max(trans[i][j - 1], maxProfitTemp + prices[j]);
                maxProfitTemp = Math.max(maxProfitTemp, trans[i-1][j-1]-prices[j]);
            }
        }
//        PrintUtils.print2DIntArray(trans);
        return trans[2][prices.length-1];
    }

    public int maxProfit1(int[] prices) {
        if(prices.length<2) return 0;
        int[] transPre=new int[prices.length];
        for (int i = 1; i <= 2; i++) {
            int[] trans=new int[prices.length];
            int maxProfitTemp = -prices[0];
            for (int j = 1; j < prices.length; j++) {
                trans[j] = Math.max(trans[j - 1], maxProfitTemp + prices[j]);
                maxProfitTemp = Math.max(maxProfitTemp, transPre[j-1]-prices[j]);
//                System.out.print(maxProfitTemp+", ");
            }
            transPre=trans;
//            PrintUtils.printIntArrayWithIndex(trans);
        }
        return transPre[prices.length-1];
    }

    public int maxProfit2(int[] prices) {
        if(prices.length<2) return 0;
        int[][] trans=new int[prices.length][3];
        int[] maxProfitMid=new int[3];
        maxProfitMid[1]=-prices[0]; maxProfitMid[2]=-prices[0];
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= 2; j++) {
                trans[i][j] = Math.max(trans[i-1][j], maxProfitMid[j] + prices[i]);
                maxProfitMid[j] = Math.max(maxProfitMid[j], trans[i-1][j-1]-prices[i]);
            }
        }
//        PrintUtils.print2DIntArray(trans);
        return trans[prices.length-1][2];
    }

    public int maxProfit3(int[] prices) {
        if(prices.length<2) return 0;
        int[] transPre=new int[3];
        int[] maxProfitMid=new int[3];
        for (int i = 1; i <=2; i++) {
            maxProfitMid[i]=-prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            int[] trans=new int[3];
            for (int j = 1; j <= 2; j++) {
                trans[j] = Math.max(transPre[j], maxProfitMid[j] + prices[i]);
                maxProfitMid[j] = Math.max(maxProfitMid[j], transPre[j-1]-prices[i]);
            }
            transPre=trans;
//            PrintUtils.printIntArrayWithIndex(trans);
        }
        return transPre[2];
    }
}
