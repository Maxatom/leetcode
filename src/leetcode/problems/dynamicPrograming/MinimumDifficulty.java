package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

public class MinimumDifficulty {
    public static void main(String[] args) {
        MinimumDifficulty mdiff=new MinimumDifficulty();
        int[] jobDifficulty = {6,5,4,3,2,1}; int d = 2;
        jobDifficulty = new int[]{9,9,9}; d = 4;
        jobDifficulty = new int[]{1,1,1}; d = 3;
        jobDifficulty = new int[]{7,1,7,1,7,1}; d = 3;
        jobDifficulty = new int[]{6,5,4,11,2,21,3,4,2,3,4,1}; d=1;
        System.out.println(mdiff.minDifficulty(jobDifficulty,d));
        System.out.println(mdiff.minDifficulty2(jobDifficulty,d));
    }

    // dp O(n*n*k)
    public int minDifficulty2(int[] jobDifficulty, int d) {
        if(jobDifficulty.length<d) return -1;
        int n=jobDifficulty.length;
        int[][] dp=new int[n][d+1];
        dp[n-1][1]=jobDifficulty[n-1];
        for (int i = n-2; i >= 0; i--) {
            dp[i][1]=Math.max(dp[i+1][1], jobDifficulty[i]);
        }
        for (int i = n-2; i >= 0; i--) {
            for (int j = 2; j <= d && j<=n-i; j++) {
                int max=jobDifficulty[i];
                dp[i][j]=Integer.MAX_VALUE;
                for (int k = i+1; k < n && n-k>=j-1; k++) { // n-k>=j-1
                    max=Math.max(max, jobDifficulty[k-1]);
                    dp[i][j] = Math.min(dp[i][j], dp[k][j-1] + max);
                }
            }
        }
        PrintUtils.print2DIntArray(dp);
        return dp[0][d];
    }

    // bottom-up dp
    public int minDifficulty1(int[] jobDifficulty, int d) {
        if(jobDifficulty.length<d) return -1;
        int n=jobDifficulty.length;
        int[][][] dp=new int[n][n][d+1];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[i][j][1]=Math.max(j==0?0:dp[i][j-1][1], jobDifficulty[j]);
            }
        }
        for(int k=2; k<=d; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    dp[i][j][k]=Integer.MAX_VALUE;
                    for (int l = i; l+k-1 <= j; l++) {
                        dp[i][j][k] =Math.min(dp[i][j][k], dp[i][l][1] + dp[l+1][j][k-1]);
                    }
                }
            }
        }
        return dp[0][n-1][d];
    }


    public int minDifficulty(int[] jobDifficulty, int d) {
        cache=new int[jobDifficulty.length][d+1];
        return dfs(jobDifficulty, d, 0);
    }
    int[][] cache;
    int dfs(int[] jobdty, int d, int cur){
        if(jobdty.length-cur<d) return -1;
        if(d==1){
            int max=0;
            for (int i = cur; i < jobdty.length; i++) {
                max=Math.max(max, jobdty[i]);
            }
            return max;
        }
        if(cache[cur][d]>0) return cache[cur][d];
        int max=0, min=Integer.MAX_VALUE;
        for (int i = cur; i + d <= jobdty.length ; i++) {
            max=Math.max(max, jobdty[i]);
            min= Math.min(min, max+dfs(jobdty, d-1, i+1));
        }
        return cache[cur][d]=min;
    }
}
