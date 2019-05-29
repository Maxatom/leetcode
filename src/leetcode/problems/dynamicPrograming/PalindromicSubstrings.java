package leetcode.problems.dynamicPrograming;

/**
 * @author Shibing
 * @since 2019-05-28 11:29:24
 **/
public class PalindromicSubstrings {
    public static void main(String[] args) {
        PalindromicSubstrings substrings=new PalindromicSubstrings();
        String s="abc";
//        s="aaa";
        System.out.println(substrings.countSubstrings(s));
    }
    //dp(i,j)=dp(i+1, j-1)
    public int countSubstrings(String s) {
        int n=s.length(), cnt=0;
        boolean[][] dp=new boolean[n][n];
        for (int d = 0; d < n; d++) {
            for (int i = 0; i + d < n; i++) {
                int j=i+d;
                if(i==j) dp[i][j]=true;
                else if(i+1==j) dp[i][j]=s.charAt(i)==s.charAt(j);
                else dp[i][j] |= dp[i+1][j-1] && s.charAt(i)==s.charAt(j);
                if(dp[i][j]) cnt++;
            }
        }
        return cnt;
    }
}
