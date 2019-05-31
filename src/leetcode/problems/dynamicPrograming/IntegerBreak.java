package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shibing
 * @since 2019-05-31 11:20:54
 **/
public class IntegerBreak {
    public static void main(String[] args) {
        IntegerBreak integerBreak = new IntegerBreak();
        int n = 15;
        n = 2;
        n = 100;
//        System.out.println(integerBreak.integerBreak(n));
        System.out.println(integerBreak.integerBreak1(n));
        System.out.println(integerBreak.integerBreak2(n));
    }
    //math
    public int integerBreak2(int n) {
        if(n<4) return n-1;
        if(n==4) return 4;
        int res=1;
        while (n>4){
            n-=3;
            res*=3;
//            System.out.println(res);
        }
        return res*n;
    }
    //dp
    public int integerBreak1(int n) {
        int[] dp=new int[n+1];
        dp[1]=1;
        for (int i = 2; i <= n; i++)
            for (int j = 1; j < i; j++)
                dp[i]=Math.max(dp[i], Math.max(j, dp[j])*Math.max(i-j, dp[i-j]));
//        PrintUtils.printArray(dp);
        return dp[n];
    }

    //top-down dp
    Map<Integer,Integer> map=new HashMap<>();
    public int integerBreak(int n) {
        if(n==1) return 1;
        if(map.containsKey(n)) return map.get(n);
        int max=0;
        for (int i=1; i <= n/2; i++)
            max=Math.max(max, Math.max(i, integerBreak(i)) * Math.max(n-i, integerBreak(n-i)));
        map.put(n, max);
        return max;
    }
}
