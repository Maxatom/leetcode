package leetcode.realtest.realTest20191201_WC165;

import utils.PrintUtils;

import java.util.Arrays;

public class PalindromePartitioningIII {
    public static void main(String[] args) {
        PalindromePartitioningIII pgIII=new PalindromePartitioningIII();
        String s = "abc"; int  k = 2;
        s = "aabbc"; k = 3;
        s = "leetcode"; k = 2;
        System.out.println(pgIII.palindromePartition(s,k));

    }
    //dp(0,n-1,k)=dp(0,i,1)+dp(i,n-1,k-1) i=(0,n-k+1)
    public int palindromePartition(String s, int k) {
        int n=s.length();
        int[][] dp=new int[k+1][n];
        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], -1);
        }
        int res=dp(s,0,k,dp);
        PrintUtils.print2DIntArray(dp);
        return  res;
    }
    public int dp(String s, int i, int k, int[][] dp){
        if(dp[k][i]!=-1) return dp[k][i];
        int n=s.length();
        if(k==1) return changes(s,i,n-1);
        if(n-i==k) return 0;
        int min=Integer.MAX_VALUE;
        for (int l = i; l <= n-k; l++) {
            min=Math.min(min,changes(s,i,l)+dp(s,l+1,k-1,dp));
        }
        return dp[k][i]=min;
    }
    int changes(String s, int i, int j){
       int c=0;
       while (i<j){
           if(s.charAt(i)!=s.charAt(j)) c++;
           i++;j--;
       }
       return c;
    }
}
