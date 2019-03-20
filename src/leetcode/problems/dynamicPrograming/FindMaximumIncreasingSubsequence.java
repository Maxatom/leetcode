package leetcode.problems.dynamicPrograming;

import utils.RandomGenerator;
import utils.RandomGenerator;
import utils.PrintUtils;
import utils.PrintUtils;
import utils.Utils;
import utils.Utils;

/**
 * @author shibing
 * @since 2018/12/22 12:22
 */
public class FindMaximumIncreasingSubsequence {
    public static void main(String[] args) {
        FindMaximumIncreasingSubsequence subsequence=new FindMaximumIncreasingSubsequence();
        RandomGenerator.String generator=new RandomGenerator.String(10);
        generator.setCharBound('a','x');
        String s=generator.next();
        System.out.println(s+", result=" +subsequence.findMaximumIncreasingSubsequence(s));


    }
    //DP   dp(i)=max(1, Max(dp[j]))  j=(1,i);
    //dp[i] indicates the length of the maximum increasing subsequence  which ends with s[i]
    public int findMaximumIncreasingSubsequence(String s){
        int[] dp=new int[s.length()];
        dp[0]=1;
        for (int i = 1; i <dp.length ; i++) {
            dp[i]=1;
            for (int j = 0; j < i; j++) {
                if(s.charAt(j)<=s.charAt(i)) dp[i]=Math.max(dp[i],dp[j]+1);
            }
        }
        PrintUtils.printIntArray(dp);
        return Utils.max(dp);
    }
}
