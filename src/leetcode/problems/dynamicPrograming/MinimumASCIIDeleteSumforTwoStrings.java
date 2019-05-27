package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

import java.util.Arrays;

/**
 * @author Shibing
 * @since 2019-05-24 16:30:36
 **/
public class MinimumASCIIDeleteSumforTwoStrings {
    public static void main(String[] args) {
        MinimumASCIIDeleteSumforTwoStrings strings=new MinimumASCIIDeleteSumforTwoStrings();
        String s1 = "sea", s2 = "eat";
//        s1 = "seae"; s2 = "aeeat";
//     s1 = "delete"; s2 = "leet";
//     s1="b"; s2="eat";
//        System.out.println(strings.minimumDeleteSum(s1, s2));
        System.out.println(strings.minimumDeleteSum1(s1, s2));
        System.out.println(strings.minimumDeleteSum2(s1, s2));
    }

    public int minimumDeleteSum2(String s1, String s2) {
        int m=s1.length(), n=s2.length();
        int[][] dp=new int[m+1][n+1];
        for (int i = 1; i <= m; i++)
            dp[i][0]=dp[i-1][0]+s1.charAt(i-1);
        for (int i = 1; i <= n; i++)
            dp[0][i]=dp[0][i-1]+s2.charAt(i-1);
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j]=s1.charAt(i-1)==s2.charAt(j-1)? dp[i-1][j-1] : Math.min(dp[i-1][j]+s1.charAt(i-1), dp[i][j-1]+s2.charAt(j-1));
            }
        }
//        PrintUtils.print2DIntArray(dp);
        return dp[m][n];
    }
    //swap Array
    public int minimumDeleteSum1(String s1, String s2) {
        int m=s1.length(), n=s2.length(), sum=0;
        int[] dp=new int[n+1];
        int[] dpn=new int[n+1];
        for (int i = 1; i <= m; i++) {
            sum+=s1.charAt(i-1);
            for (int j = 1; j <= n; j++) {
                dpn[j]=s1.charAt(i-1)==s2.charAt(j-1)? dp[j-1]+2*s1.charAt(i-1) : Math.max(dp[j], dpn[j-1]);
                if(i==1) sum+=s2.charAt(j-1);
            }
            int[] temp=dp; dp=dpn; dpn=temp;
        }
        return sum-dp[n];
    }


    public int minimumDeleteSum(String s1, String s2) {
        int m=s1.length(), n=s2.length(), sum=0;
        int[][] dp=new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            sum+=s1.charAt(i-1);
            for (int j = 1; j <= n; j++) {
                dp[i][j]=s1.charAt(i-1)==s2.charAt(j-1)? dp[i-1][j-1]+2*s1.charAt(i-1) : Math.max(dp[i-1][j], dp[i][j-1]);
                if(i==1) sum+=s2.charAt(j-1);
            }
        }
        return sum-dp[m][n];
    }
}
