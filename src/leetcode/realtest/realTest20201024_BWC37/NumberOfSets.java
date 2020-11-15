package leetcode.realtest.realTest20201024_BWC37;

import utils.PrintUtils;

public class NumberOfSets {
    public static void main(String[] args) {
        NumberOfSets sets = new NumberOfSets();
        int n = 4, k = 2;
//        n=3; k=2;
        n=30; k=7;
//        n=4; k=1;
        System.out.println(sets.numberOfSets(n, k));
    }
    public long numberOfSets(int n, int k) {
        long[][] dp = new long[n+1][k+1];
        dp[2][1]=1; dp[2][0]=1; dp[1][0]=1;
        for(int i=3; i<=n; i++){
            dp[i][0]=1;
            for(int j=1; j<=i-1 && j<=k; j++){
                for(int m=1; m<=i-j; m++){
                    dp[i][j] += dp[i-m][j-1];
                }
                dp[i][j] += dp[i-1][j];
            }
        }
//        PrintUtils.print2DIntArray(dp);
//        for(int i=0; )
        return dp[n][k];
    }
}
