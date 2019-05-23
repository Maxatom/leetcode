package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

/**
 * @author Shibing
 * @since 2019-05-22 18:56:54
 **/
public class PerfectSquares {
    public static void main(String[] args) {
        PerfectSquares squares=new PerfectSquares();
        int n=12;
        n=13;
        n=10000;
        System.out.println(squares.numSquares(n));
        System.out.println(squares.numSquares1(n));
    }
    boolean isSquare(int n){
        int x=(int)Math.sqrt(n);
        return x*x==n;
    }
    //math
    public int numSquares1(int n) {
        if(isSquare(n)) return 1;
        //4^k*(8m+7) check
        //Lagrange for square theorem
        while ((n&3)==0) n>>=2; //n%4==0
        if ((n&7)==7) return 4; //n%7=0;
        for (int i = 1; i*i <= n; i++) {
            if(isSquare(n-i*i)) return 2;
        }
        return 3;
    }

    public int numSquares(int n) {
        int[] dp=new int[n+1];
        for (int i = 1; i <= n; i++) {
            dp[i]=i;
            for (int j = 1; j*j <= i; j++)
                dp[i]=Math.min(dp[i], dp[i-j*j]+1);
        }
        return dp[n];
    }
}
