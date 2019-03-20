package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;
import utils.PrintUtils;

/**
 * difficulty: EASY
 * @author shibing
 * @since 2018/11/26 21:58
 */
public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        BestTimeToBuyAndSellStock stock=new BestTimeToBuyAndSellStock();
        String s="[[7,1,5,3,6,4], [7,6,4,3,1],[],[4], [1,3], [5,4]]";
        int[][] testCases= PrintUtils.convertStringTo2DIntArray(s);
        for (int[] testCase:testCases){
            System.out.println("input: ----------------");
            PrintUtils.printIntArray(testCase);
            System.out.println(stock.maxProfit(testCase));
        }

    }
    //A(1,i) = max(A(1, i-1) A[i]-min(A(1,i-1))
    public int maxProfit(int[] prices) {
        if(prices==null||prices.length<2) return 0;
        int min=prices[0];
        int maxProfit=0;
        for (int i = 1; i < prices.length; i++) {
            if(prices[i]-min>maxProfit) maxProfit=prices[i]-min;
            else min=Math.min(min,prices[i]);
        }
        return maxProfit;
    }
}
