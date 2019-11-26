package leetcode.realtest.realTest20191124_WC164;

import utils.PrintUtils;

import java.util.Arrays;

public class NumberofWaystoStayintheSamePlaceAfterSomeSteps {
    public static void main(String[] args) {
        NumberofWaystoStayintheSamePlaceAfterSomeSteps numsteps=new NumberofWaystoStayintheSamePlaceAfterSomeSteps();
        int steps = 3, arrLen = 2;
        steps = 2; arrLen = 4;
        steps = 4; arrLen = 2;
        steps = 27; arrLen=7;
        System.out.println(numsteps.numWays(steps,arrLen));
        System.out.println(numsteps.numWays1(steps,arrLen));

    }

    //bottom-up
    //dp(s,j)=dp(s+1,j)+ dp(s+1,j-1)+dp(s+1,j+1)
    //Or dp(s,j)=dp(s-1,j-1)+dp(s-1,j)+dp(s-1,j+1)
    public int numWays1(int steps, int arrLen) {
        int[][] dp=new int[steps+1][steps+1];
        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j <= i && j < arrLen; j++) {
                dp[i][j]=1;
            }
        }
        for (int i = 2; i <= steps; i++) {
            for (int j = 0; j <= i && j<arrLen; j++) {
                int left=j==0?0:dp[i-1][j-1];
                int res=(left+dp[i-1][j])%M;
                dp[i][j]=(res+(j>=i||j>=arrLen?0:dp[i-1][j+1]))%M;
            }
        }
//        PrintUtils.print2DIntArray(dp);
        return dp[steps][0];
    }


    public int numWays(int steps, int arrLen) {
        int[][] cache=new int[steps+1][steps];
        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i],-1);
        }
        int res= dfs(steps, arrLen, 0, cache);
        return res;
    }
    int M=(int)Math.pow(10,9) + 7;
    int dfs(int steps,int arrLen, int len, int[][] cache){
        if(len<0 || len>=arrLen || len>steps) return 0;
        if(len<0 || len>=arrLen ) return 0;
        if(cache[steps][len]>=0) return cache[steps][len];
        if(steps==0 && len==0) return 1;
        int right= dfs(steps-1,arrLen,len+1,cache);
        int stay=dfs(steps-1, arrLen, len,cache);
        int res=(right+stay)%M;
        int left=dfs(steps-1, arrLen, len-1,cache);
        return cache[steps][len]=(res+left)%M;
    }
}
