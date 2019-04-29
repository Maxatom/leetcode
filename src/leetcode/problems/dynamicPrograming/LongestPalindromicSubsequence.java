package leetcode.problems.dynamicPrograming;

import java.util.Arrays;

/**
 * @author shibing
 * @since 2019/2/1 0:05
 */
public class LongestPalindromicSubsequence {
    public static void main(String[] args) {
        LongestPalindromicSubsequence palin=new LongestPalindromicSubsequence();
        String s="bbbab";
//        s="cbbd";
//        s="bbb";
//        s="aabaa";
//        s="babaaaaaab";
//        s="aaaabababababbabaabaaa";
//        s="";
//        s=utils.generateString(22,'a', 'f');
        System.out.println(s);
//        System.out.println(palin.longestPalindromeSubseq(s));
//        System.out.println(palin.longestPalindromeSubseq1(s));
//        System.out.println(palin.longestPalindromeSubseq2(s));
        System.out.println(palin.longestPalindromeSubseq3(s));
        System.out.println(palin.longestPalindromeSubseq4(s));
    }

    //brute force O(2^n) space O(N)
    public int longestPalindromeSubseq1(String s) {
        String res= recursive(s, 0,"");
//        System.out.println(res);
        return res.length();
    }
    public String recursive( String s, int i, String palin){
        if(i==s.length()){
            int j=0,k=palin.length()-1;
            while (j<=k && palin.charAt(j)==palin.charAt(k)){j++; k--;};
            return j>k?palin:"";
        }
        String s1=recursive(s, i+1, palin);
        String s2=recursive(s, i+1, palin+s.charAt(i));
        return s1.length()>s2.length()?s1:s2;
    }

    //
    public int longestPalindromeSubseq4(String s) {
        int n=s.length();
        int[] dp=new int[n];
        char[] sc=s.toCharArray();
        Arrays.fill(dp,1);
        for (int i = 1; i < n; i++) {
            int cur=0;
            for (int j = i-1; j >=0 ; j--) {
                int temp=dp[j];
                if(sc[i]==sc[j])
                    dp[j]= cur+2;
                cur=Math.max(temp, cur);
            }
//            PrintUtils.printArray(dp);
        }
        int max=0;
        for(int i:dp) max=i>max?i:max;
        return max;
    }
    //1-D array
    public int longestPalindromeSubseq3(String s) {
        if(s==null || s.length()==0) return 0;
        int n=s.length();
        int[] prev=new int[n];
        int[] cur=new int[n];
        int[] next=new int[n];
        Arrays.fill(cur,1);
        for (int d = 1; d < n; d++) {
//            System.out.print("prev: ");
//            PrintUtils.printArray(prev);
//            System.out.print("cur: "+d+":");
//            PrintUtils.printArray(cur);
            for (int i = 0; i < n-d; i++) {
                int j=i+d;
                next[i]=s.charAt(i)==s.charAt(j)?(prev[i+1]+2):Math.max(cur[i], cur[i+1]);
            }
            //swap
            int[] temp=prev; prev=cur; cur=next; next=temp;
        }
//        PrintUtils.printArray(cur);
        return cur[0];
    }

    //top-down
    public int longestPalindromeSubseq2(String s) {
        int n=s.length();
        Integer[][] memo=new Integer[n][n];
        return topdowndp(s, 0, n-1, memo);
    }
    public int topdowndp(String s, int i, int j, Integer[][]memo){
        if(i>j) return 0;
        if(i==j) return 1;
        if(memo[i][j]!=null) return memo[i][j];
        return memo[i][j]=s.charAt(i)==s.charAt(j)?topdowndp(s, i+1, j-1, memo)+2:
                Math.max(topdowndp(s, i+1, j, memo), topdowndp(s, i, j-1, memo));
    }

    //dp bottom-up
    //p(i,j)= max( p(i+1,j-1)+1 if S(i)==S(j) else 0, p(i+1,j), p(i,j-1));
    public int longestPalindromeSubseq(String s) {
        if(s==null || s.length()==0) return 0;
        int n=s.length();
        int[][] dp=new int[n][n];
        for (int i = 0; i < n; i++)  dp[i][i]=1;
        for (int d = 1; d < n; d++) {
            for (int i = 0; i < n-d; i++) {
                int j=i+d;
                dp[i][j]=Math.max(dp[i+1][j-1]+(s.charAt(i)==s.charAt(j)?2:0), Math.max(dp[i+1][j], dp[i][j-1]));
            }
        }
       return dp[0][n-1];
    }
}
