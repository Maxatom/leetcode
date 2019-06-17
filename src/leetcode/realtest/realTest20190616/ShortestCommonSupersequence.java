package leetcode.realtest.realTest20190616;

import utils.PrintUtils;

/**
 * @author shibing
 * @since 2019/6/16 11:08
 */
public class ShortestCommonSupersequence {
    public static void main(String[] args) {
        ShortestCommonSupersequence sequence=new ShortestCommonSupersequence();
        String str1 = "abac", str2 = "cab";
        str1="bbbaaaba"; str2= "bbababbb";
        str1="aabbabaa"; str2= "aabbbbbbaa";;
        System.out.println(sequence.shortestCommonSupersequence(str1, str2));
    }
    public String shortestCommonSupersequence(String str1, String str2) {
        int n1=str1.length(), n2=str2.length();
        int[] arr1=new int[n1], arr2=new int[n2];
        int[][] dp=new int[n1][n2];
        int i1=-1, j1=-1;
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if(str1.charAt(i)==str2.charAt(j))  {
                    if(i>i1 && j>j1) {
                        arr1[i]=1; arr2[j]=1;
                        i1=i; j1=j;
                    }
                    dp[i][j]=(i>0&&j>0?dp[i-1][j-1]:0)+1;
                }
                else dp[i][j]=Math.max(i>0?dp[i-1][j]:0, j>0 ? dp[i][j-1] : 0);
            }
        }

       PrintUtils.printArray(arr1);
        PrintUtils.printArray(arr2);
        StringBuilder sb=new StringBuilder();
        int i=0, j=0;
        while (i<n1 || j<n2){
            if(i<n1 && j<n2 && arr1[i]==1 && arr2[j]==1){
                sb.append(str1.charAt(i));
                i++; j++;
            }
            if(i<n1 && arr1[i]==0){
                sb.append(str1.charAt(i)); i++;
            }
            if(j<n2 && arr2[j]==0){
                sb.append(str2.charAt(j)); j++;
            }
        }

        return sb.toString();
    }
}
