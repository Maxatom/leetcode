package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

import java.util.*;

/**
 * @author ShiBing
 * @projectName leetcode
 * @date 2020/3/2920:08
 */
public class TallestBillboard {
    public static void main(String[] args) {
        TallestBillboard board=new TallestBillboard();
        int[] rods={1,2,3,6};
        rods=new int[]{1,2,3,4,5,6};
//        rods=new int[]{1,2};
//        rods=new int[0];
//        rods=new int[]{1,2,4,8,16,32,64,128,256,512,50,50,50,150,150,150,100,100,100,123};
//        rods=new int[]{140,138,133,162,145,164,145,166,145,154,158};
        System.out.println(board.tallestBillboard1(rods));
//        System.out.println(board.tallestBillboard(rods));
    }

    //dp(i,j) = dp(i-1,k)+ R(i)
    public int tallestBillboard1(int[] rods) {
        int n=rods.length;
        if(n==0) return 0;
        int[][] dp=new int[n][5001];
        for (int i = 0; i < n; i++)
            Arrays.fill(dp[i], -1);
        int max=0, sum=rods[0];
        dp[0][rods[0]]=rods[0];
        dp[0][0]=0;
        for(int i=1; i<n; i++){
            for(int j=0; j<=Math.min(sum, 5000); j++){
                if(dp[i-1][j]==-1) continue;
                dp[i][Math.abs(j-rods[i])]=Math.max(dp[i][Math.abs(j-rods[i])],dp[i-1][j]+rods[i]);
                dp[i][j+rods[i]]=Math.max(dp[i][j+rods[i]], dp[i-1][j]+rods[i]);
                dp[i][j]= Math.max(dp[i][j], dp[i-1][j]);
            }
            sum+=rods[i];
            max=Math.max(max, dp[i][0]/2);
        }
//        PrintUtils.print2DIntArray(dp);
        return max;
    }



//    public int tallestBillboard(int[] rods) {
//        int n=rods.length;
//        int[] dpl=new int[5000], dpr=new int[5000];
//        Arrays.fill(dpl,-1);
//        Arrays.fill(dpr,-1);
//        dpl[0]=0; dpr[0]=0;
//        dfs(rods, 0, n/2,0, dpl);
//        dfs(rods, n/2+1, n-1,0, dpr);
//        int max=0;
//        for (int i = 0; i < dpl.length; i++) {
//            if(dpl[i]==-1 || dpr[i]==-1) continue;
//            max=Math.max(max, (dpl[i]+dpr[i])/2);
//        }
//        PrintUtils.printArray(dpl);
//        PrintUtils.printArray(dpr);
//        return max;
//    }
//    void dfs(int[] rods,int cur, int end, int diff, int[] dp){
//        if(cur>end) return;
//        int add=diff+rods[cur], minu=Math.abs(diff-rods[cur]);
//        dp[add]=Math.max(dp[add], dp[diff]+rods[cur]);
//        dp[minu]=Math.max(dp[minu], dp[diff]+rods[cur]);
//        dfs(rods,cur+1, end, add, dp);
//        dfs(rods,cur+1, end, minu, dp);
//        dfs(rods,cur+1, end, diff, dp);
//    }
}
