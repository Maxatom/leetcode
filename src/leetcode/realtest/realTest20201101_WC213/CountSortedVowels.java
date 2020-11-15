package leetcode.realtest.realTest20201101_WC213;

import java.util.Arrays;

public class CountSortedVowels {
    public static void main(String[] args) {
        CountSortedVowels vowels = new CountSortedVowels();
        int n=33;
        System.out.println(vowels.countVowelStrings(n));
    }
    public int countVowelStrings(int n) {
        int[] dp = new int[5];
        Arrays.fill(dp,1);
        for(int i=2; i<=n ;i++){
            int[] next=new int[5];
            for(int j=0; j<5; j++){
                for(int k=j; k<5; k++){
                    next[k]+=dp[j];
                }
            }
            dp=next;
        }
        int res=0;
        for(int j=0; j<5; j++){
            res+=dp[j];
        }
        return res;
    }
}
