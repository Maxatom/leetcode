package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;
import utils.PrintUtils;
import utils.Utils;
import utils.Utils;

/**
 * @author shibing
 * @since 2019/2/1 0:27
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        LongestPalindromicSubstring palin=new LongestPalindromicSubstring();
        String s="bbbab";
//        s="cbbd";
//        s="babad";
//        s="a";
//        s="";
        s= Utils.generateString(1000, 'a', (char)('a'+1));
        long start=System.currentTimeMillis();
        System.out.println(palin.longestPalindrome(s) + ", time="+(System.currentTimeMillis()-start));
//        System.out.println(palin.longestPalindrome1(s));
        start=System.currentTimeMillis();
        System.out.println(palin.longestPalindrome2(s) + ", time="+(System.currentTimeMillis()-start));
    }

    //longest common substring
//    public String longestPalindrome3(String s) {
//
//    }
    //Manacher's algorithm O(N)
    public String longestPalindrome2(String s) {
        if(s.length()==0) return s;
        StringBuilder sNew=new StringBuilder("$#");
        for (int i = 0; i < s.length(); i++)
            sNew.append(s.charAt(i)).append("#");
        int id=0, mx=0;
        int start=0, end=0, max=0;
        int[] p=new int[sNew.length()];
        for (int i = 0; i < sNew.length(); i++) {
            if(i<mx) p[i]=Math.min(p[2*id-i], mx-i);
             else p[i]=1;
             while (i-p[i]>=0&&i+p[i]<sNew.length()&&sNew.charAt(i-p[i])==sNew.charAt(i+p[i])) p[i]++;
             if(mx<i+p[i]){
                 id=i;
                 mx=i+p[i];
             }
             if(max<p[i]){
                 max=p[i];
                 start=(i-p[i])>>1;
                 end=start+p[i]-1;
             }
        }
//        PrintUtils.printArray(p);
        return s.substring(start,end);
    }
    //DP  O(N^2)
    //P(i,j)= P(i+1,j-1) and S(i)=S(j) is palindromic ,
    //P(i,i)=true   P(i,i+1)=  S(i)==S(i+1)
    public String longestPalindrome1(String s) {
        if(s.length()==0) return s;
        int n=s.length();
        boolean[][] dp=new boolean[n][n];
        int start=0, end=0;
        for (int i = 0; i < n; i++) {
            dp[i][i]=true;
            if(i<n-1&&s.charAt(i)==s.charAt(i+1)) {
                dp[i][i+1]=true;
                start=i; end=i+1;
            }
        }
        for (int d = 3; d <= n; d++) {
            for (int j = 0; j <= n-d; j++) {
                int i=j+d-1;
                dp[j][i]= dp[j+1][i-1]&&s.charAt(j)==s.charAt(i);
                if(dp[j][i]&& d>end-start+1){ start=j; end=i; }
            }
        }
        PrintUtils.print2DArray(dp);
        return s.substring(start, end+1);
    }

    //expand around center O(N^2)
    public String longestPalindrome(String s) {
        int start=0, end=0, max=0;
        for (int i = 0; i < s.length(); i++) {
            int even=palindromicLength(s, i, true);
            int odd=palindromicLength(s, i, false);
            if(even>=odd&&even>max){
                max=even;
                 start=i-even/2+1;
                 end=start+even;
            }
            if(odd>even && odd>max){
                max=odd;
                start = i-odd/2;
                end = start+odd;
            }
        }
        return s.substring(start, end);
    }
    int palindromicLength(String s, int i, boolean o){
        int l=i, r=i, len=1;
        if(o){ l=i+1; r=i; len=0; }
        while (--l>=0&&++r<s.length() && s.charAt(l)==s.charAt(r)) len+=2;
        return len;
    }
}
