package leetcode.problems.dynamicPrograming;

import utils.Utils;
import utils.Utils;

/**
 * @author shibing
 * @since 2019/2/2 21:41
 */
public class LongestCommonSubstring {
    public static void main(String[] args) {
        LongestCommonSubstring common=new LongestCommonSubstring();
        String s1=Utils.generateString(10000, 'a', 'b'), s2=Utils.generateString(20000,'a', 'b');
//        s1="abba"; s2="bbab";
//        s1="aaaa"; s2="abbb";
//        s1="" ; s2="";
//        s1=""; s2="abc";
//        s1="a"; s2="a";
//        s1="b"; s2="a";
//        s1="ra"; s2="a";
//        System.out.println("s1="+s1+"\ns2="+s2);
        long start=System.currentTimeMillis();
        System.out.println(common.longestCommonSubstring(s1,s2)+", time="+(System.currentTimeMillis()-start));
        start=System.currentTimeMillis();
        System.out.println(common.longestCommonSubstring1(s1,s2)+", time="+(System.currentTimeMillis()-start));
        start=System.currentTimeMillis();
        System.out.println(common.longestCommonSubstring2(s1,s2)+", time="+(System.currentTimeMillis()-start));
    }

    //brute force O(nm+min(n,m)^2)  space O(1)  faster than dp solution
    public String longestCommonSubstring2(String s1, String s2){
        int start=0, len=0, k=0; String str=s1;
        while (k++<2){
            for(int j=0; j<s2.length() ; j++){
                int i1=0, i2=j;
                int s=i1, l=0;
                while (i1<s1.length() && i2<s2.length() ){
                    if(s1.charAt(i1)==s2.charAt(i2)) l++;
                    else {
                        if(l>len){start=s; len=l; str=s1;}
                        s=i1+1; l=0;
                    }
                    i1++; i2++;
                }
                if(l>len){start=s; len=l; str=s1;}
            }
            //swap string
            String temp=s1; s1=s2; s2=temp;
        }
        return str.substring(start, start+len);
    }

    //dp 1D-array  space O(min(M,N)
    public String longestCommonSubstring1(String s1, String s2) {
        if (s1 == null || s2 == null) return null;
        if (s1.equals("") || s2.equals("")) return "";
        int m=s1.length(), n=s2.length();
        if(m<n) longestCommonSubstring1(s2, s1);
        int[] pre=new int[n];
        int[] cur=new int[n];
        int end = 0, max = 0;
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j))
                    cur[j] = (i > 0 && j > 0 ? pre[j - 1] : 0) + 1;
                else cur[j] = 0;
                if (cur[j] > max) { end = i; max = cur[j]; }
            }
            //swap array
            int[] temp=pre; pre=cur; cur=temp;
        }
//        PrintUtils.printArray(pre);
        return s1.substring(end - max + 1, end + 1);
    }
        //DP  O(NM)  space O(nm)
    // dp(i,j) means the length of common String that from s1 is end with ith and that from s2 is end with jth
    //dp(i,j) = if S1(i)==S2(j): dp(i-1, j-1) + 1 else 0 (S in S1,S2 ... Sn)
    //dp(0,j)=0+1 if S1(0)==S2(j);
    public String longestCommonSubstring(String s1, String s2){
        if(s1==null||s2==null) return null;
        if(s1.equals("")||s2.equals("")) return "";
        int[][] dp=new int[s1.length()][s2.length()];
        int end=0, max=0;
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if(s1.charAt(i)==s2.charAt(j))
                    dp[i][j] = (i > 0 && j > 0 ? dp[i - 1][j - 1] : 0)+1;
                else dp[i][j]=0;
                if(dp[i][j]>max){ end=i; max=dp[i][j]; }
            }
        }
//        PrintUtils.print2DIntArray(dp);
        return s1.substring(end-max+1, end+1);
    }
}
