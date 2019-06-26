package leetcode.realtest.realTest20190616;

import utils.PrintUtils;

import java.util.Arrays;

/**
 * @author shibing
 * @since 2019/6/16 11:08
 */
public class ShortestCommonSupersequence {
    public static void main(String[] args) {
        ShortestCommonSupersequence sequence=new ShortestCommonSupersequence();
        String str1 = "abac", str2 = "cab";
//        str1="bbbaaaba"; str2= "bbababbb";
//        str1="aabbabaa"; str2= "aabbbbbbaa";
        System.out.println(sequence.shortestCommonSupersequence(str1, str2));
        System.out.println(sequence.shortestCommonSupersequence1(str1, str2));
    }

    public String shortestCommonSupersequence1(String str1, String str2) {
        int n1=str1.length(), n2=str2.length();
        int[][] dp=new int[n1+1][n2+1];
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if(i==0) dp[i+1][j+1]=j;
                else if(j==0) dp[i+1][j+1]=i;
                else if(str1.charAt(i)==str2.charAt(j)) dp[i+1][j+1]=dp[i][j]+1;
                else dp[i+1][j+1]=1+Math.min(dp[i+1][j],dp[i][j+1]);
            }
        }
        PrintUtils.print2DIntArray(dp);
        StringBuilder sb=new StringBuilder();
        int i=0, j=0;
        return null;
    }

    public String shortestCommonSupersequence(String str1, String str2) {
        int n1=str1.length(), n2=str2.length();
        String[][] dp=new String[n1+1][n2+1];
        for (int i = 0; i <= n1; i++) Arrays.fill(dp[i], "");
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if(str1.charAt(i)==str2.charAt(j)) dp[i+1][j+1] = dp[i][j] + str1.charAt(i);
                else dp[i+1][j+1] = dp[i][j+1].length() > dp[i+1][j].length() ? dp[i][j+1] : dp[i+1][j];
            }
        }
//        System.out.println(dp[n1][n2]);
        StringBuilder sb=new StringBuilder();
        int i=0, j=0;
        for (char c: dp[n1][n2].toCharArray()) {
            while (i<n1 && str1.charAt(i)!=c) { sb.append(str1.charAt(i)); i++; }
            while (j<n2 && str2.charAt(j)!=c) { sb.append(str2.charAt(j)); j++; }
            sb.append(c); i++; j++;
        }
        return sb.toString()+str1.substring(i)+str2.substring(j);
    }
}
