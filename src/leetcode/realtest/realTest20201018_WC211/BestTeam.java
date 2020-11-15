package leetcode.realtest.realTest20201018_WC211;

import utils.PrintUtils;

import java.util.Arrays;

public class BestTeam {
    public static void main(String[] args) {
        BestTeam bestTeam = new BestTeam();
int[] scores = {596,277,897,622,500,299,34,536,797,32,264,948,645,537,83,589,770}, ages = {18,52,60,79,72,28,81,33,96,15,18,5,17,96,57,72,72};
//scores = new int[]{4,5,6,5}; ages = new int[]{2,1,2,1};
        System.out.println(bestTeam.bestTeamScore(scores,ages));
        System.out.println(bestTeam.bestTeamScore1(scores,ages));

    }
    public int bestTeamScore1(int[] scores, int[] ages) {
        int n=scores.length;
        if(n==1) return scores[0];
        int[][] data = new int[n][2];
        int maxage=0;
        for(int i=0; i< n; i++){
            data[i][0] = scores[i];
            data[i][1] = ages[i];
            maxage = Math.max(maxage, ages[i]);
        }
        int[][] dp=new int[n][maxage+1];
        Arrays.sort(data, (a, b)-> a[0]==b[0]?b[1]-a[1]:b[0]-a[0]);
        for(int i=n-1; i>=0; i--){
            for(int j=1; j<=maxage; j++){
                if(j>=data[i][1])
                    dp[i][j] = Math.max( Math.max((i<n-1 ? dp[i+1][data[i][1]] : 0) + data[i][0], i<n-1? dp[i+1][j] : 0), dp[i][j]);
                else {
                    dp[i][j] = Math.max((i<n-1 ? dp[i+1][j] : 0), dp[i][j]);
                }
            }
        }
//        PrintUtils.print2DIntArray(dp);
        return dp[0][maxage];
    }
    public int bestTeamScore(int[] scores, int[] ages) {
        int n=scores.length;
        if(n==1) return scores[0];
        int[][] data = new int[n][2];
        dp=new int[n][1001];
        for(int i=0; i< n; i++){
            data[i][0] = scores[i];
            data[i][1] = ages[i];
        }
        Arrays.sort(data, (a, b)-> a[0]==b[0]?b[1]-a[1]:b[0]-a[0]);
        dfs(data, 0, 1000);
        return dp[0][1000];
    }
    int[][] dp ;
    int dfs(int[][] data, int i, int m){
        if(i>= data.length) return 0;
        if(dp[i][m]>0) return dp[i][m];
        int max=0;
        for(int j=i;j< data.length; j++){
            if(data[j][1]<= m){
               max = Math.max(max, data[j][0]+ dfs(data, j+1, data[j][1]));
            }
        }
        return dp[i][m]=max;
    }
}
