package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;
import utils.PrintUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shibing
 * @since 2019-01-09 18:39:15
 **/
public class CoinChange {
    public static void main(String[] args) {
        CoinChange change=new CoinChange();
        int[] testArray=PrintUtils.convertStringToIntArray( "[1,6,7,10]") ; int amount=10002;
//        testArray=PrintUtils.convertStringToIntArray("[ 6,7, 10]"); amount=11;
//        testArray=PrintUtils.convertStringToIntArray("[1, 2, 5]"); amount=9;
//        testArray=PrintUtils.convertStringToIntArray("[2]"); amount=11;
//        System.out.println(change.coinChange(testArray, amount));
//        System.out.println(change.coinChange1(testArray, amount)+", times="+change.times);
    }

    //DP bottom-up  O(L*A)
    public int coinChange1(int[] coins, int amount) {
        int[] dp=new int[amount+1];
        for (int i = 1; i <= amount; i++) {
            dp[i]=Integer.MAX_VALUE;
            for(int x:coins)
                if(i-x>=0&&dp[i-x]!=Integer.MAX_VALUE) dp[i] = Math.min(dp[i], 1+dp[i-x]);
        }
        return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];
    }

    //DP top-down
    Map<Integer,Integer> map=new HashMap<>();
    public int coinChange(int[] coins, int amount) {
        if(map.containsKey(amount)) return map.get(amount);
        if(amount==0) return 0;
        if(amount<0) return -1;
        int min=Integer.MAX_VALUE;
        for (int i: coins) {
            int result=coinChange(coins, amount-i);
            if(result!=-1) min=Math.min(min,1+result);
        }
        map.put(amount, min==Integer.MAX_VALUE?-1:min);
        return map.get(amount);
    }
}
