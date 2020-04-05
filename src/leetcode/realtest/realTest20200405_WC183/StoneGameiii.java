package leetcode.realtest.realTest20200405_WC183;

import utils.PrintUtils;

/**
 * @author ShiBing
 * @projectName leetcode
 * @date 2020/4/516:56
 */
public class StoneGameiii {
    public static void main(String[] args) {
        StoneGameiii stoneGameiii=new StoneGameiii();
        int[] values = {1,2,3,7};
        values=new int[]{1,2,3,-9};
        values=new int[]{1,2,3,6};
        values = new int[]{1,2,3,-1,-2,-3,7};
        values = new int[]{-1,-2,-3};
        System.out.println(stoneGameiii.stoneGameIII(values));

    }
    public String stoneGameIII(int[] stoneValue) {
        int n=stoneValue.length;
        int[][] dp=new int[n][3];
        for(int i=n-1; i>=0; i--){
            int sum=0;
            for (int j = 1; j <= 3; j++) {
                sum+=i+j-1>=n?0:stoneValue[i+j-1];
                dp[i][j-1]= i+j>=n?sum:(sum - Math.max(dp[i+j][0], Math.max(dp[i+j][1],dp[i+j][2])));
            }
        }
        int max= Math.max(dp[0][0],Math.max(dp[0][1],dp[0][2]));
        return max>0?"Alice": (max<0?"Bob":"Tie");
    }
}
