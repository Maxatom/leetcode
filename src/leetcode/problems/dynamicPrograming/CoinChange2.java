package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

import java.util.Arrays;

/**
 * 背包问题
 * @author Shibing
 * @since 2019-01-10 09:47:48
 **/
public class CoinChange2 {
    public static void main(String[] args) {
        CoinChange2 coin2=new CoinChange2();
        int[] testArray=PrintUtils.convertStringToIntArray( "[1,19,3,6,7,10,11,13,15]") ; int amount=130;
//        testArray=PrintUtils.convertStringToIntArray( "[6,7,10]") ; amount=10;
        testArray=PrintUtils.convertStringToIntArray("[1, 2, 5]"); amount=7;
//        testArray=PrintUtils.convertStringToIntArray("[10]"); amount=9;
//        testArray=PrintUtils.convertStringToIntArray("[]"); amount=3;
//        testArray=PrintUtils.convertStringToIntArray("[99,1]"); amount=101;
//        System.out.println(coin2.change(amount, testArray));
        System.out.println(coin2.change1(amount, testArray));
        System.out.println(coin2.change2(amount, testArray));
    }

    //optimizing space 1D array
    public int change2(int amount, int[] coins) {
        int[] dp=new int[amount+1];
        dp[0]=1;
        for (int coin: coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j]=dp[j]+dp[j-coin];
            }
        }
        PrintUtils.printArray(dp);
        return dp[amount];
    }

    //O(A*L)
    //dp(i,j) means the number of combinations to make up amount j by using the first i coins
    //dp(i, j)=dp(i-1,j) + dp(i, j-coins(i-1)), dp(i,0)=1; coins starts from 0
    public int change1(int amount, int[] coins) {
        int[][] dp=new int[coins.length+1][amount+1];
        for (int i = 0; i <= coins.length; i++)  dp[i][0]=1;
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j]=dp[i-1][j]+(j<coins[i-1]?0:dp[i][j-coins[i-1]]);
            }
        }
        PrintUtils.print2DIntArray(dp);
        return dp[coins.length][amount];
    }


    //O(A*L^2)  DP  bottom-up
    //dp(i,j) means the number of combinations whose max denomination coin is j
    //dp(i,j) = dp(i-x, j)|( x in coins, j<=x)
    //eg: coins=[1,2,5] , amount=8; dp(8,8)= dp(8-5,j)|(j=1,2,5)+dp(8-2,j)|(j=1,2)+dp(8-1,j)|(j=1)
    public int change(int amount, int[] coins) {
        if(amount==0)  return 1;
        if(coins==null||coins.length<1) return 0;
        int[][] dp=new int[amount+1][coins.length];
        for (int i = 0; i < coins.length&&coins[i]<=amount; i++) dp[coins[i]][i]=1;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if(i-coins[j]>=0)
                    for (int k = 0; k < coins.length; k++) {
                        if(coins[k]<=coins[j])
                            dp[i][j]+=dp[i-coins[j]][k];
                    }
            }
        }
//        PrintUtils.print2DIntArray(dp);
        return Arrays.stream(dp[amount]).sum();
    }
}
