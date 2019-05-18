package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

/**
 * @author Shibing
 * @since 2019-05-17 13:31:19
 **/
public class LargestSumofAverages {
    public static void main(String[] args) {
        LargestSumofAverages averages=new LargestSumofAverages();
        int[] A = {9,1,2,3,9}; int K = 3;
//        System.out.println(averages.largestSumOfAverages(A, K));
        System.out.println(averages.largestSumOfAverages1(A, K));
        System.out.println(averages.largestSumOfAverages2(A, K));
    }

    // dp(i, k) is the largest score of k+1 groups between A[i:]
    //dp(i,k)= max(sum(i,i1)/(i-i1)+ dp(i1,k-1))
    //top-down
    public double largestSumOfAverages2(int[] A, int K) {
        int n=A.length;
        int[] preSum=new int[n+1];
        for (int i = 0; i < n; i++)
            preSum[i+1]=preSum[i]+A[i];
        return recursive(A, preSum, 0, K, new double[n][K+1]);
    }
    public double recursive(int[] A, int[] preSum, int i, int K, double[][] memo){
        int n=A.length;
        if(n-i<K) return 0;
        if(memo[i][K]>0) return memo[i][K];
        if(K==1) return (double)(preSum[n]-preSum[i])/(n-i);
        double max=0;
        for (int j = i; j+K-1 <= n; j++) {
            max=Math.max(max, (double)(preSum[j+1]-preSum[i])/(j-i+1)+recursive(A, preSum, j+1, K-1, memo));
        }
        return memo[i][K]=max;
    }
    //O(KN^2)
    // dp(i, k) is the largest score of k+1 groups between A[0~i]
    //dp(i,k)=max(dp(i1,k-1)+dp(i1+1,1))  0<=i1<=i
    //dp(i,1) = sum(0~i)/i+1
    public double largestSumOfAverages1(int[] A, int K) {
        int n=A.length;
        int[] preSum=new int[n+1];
        for (int i = 0; i < n; i++)
            preSum[i+1]=preSum[i]+A[i];
        double[] dp0=new double[n];
        double[] dp=new double[n];
        for (int i = 0; i < n; i++)
            dp[i]=dp0[i] = (double) preSum[i + 1] / (i + 1);
        double[] dpnew=new double[n];
        for (int k = 1; k < K; k++) {
            for (int i = k; i < n; i++) {
                    dpnew[i]=dp[i];
                    for (int l = k-1; l < i; l++) {
                        dpnew[i]=Math.max(dpnew[i], dp[l]+(double)(preSum[i+1]-preSum[l+1])/(i-l));
                    }
                }
            double[] temp=dp;  dp=dpnew; dpnew=temp;
        }
        return dp[n-1];
    }

    //O(KN^3)
    // dp(i,j,k) is largest score of k+1 groups between A[i~j]
    //dp(i,j,k) = max(dp(i,k1,1)+dp(k1,j,k-1))  (i<=k1<=j)
    //dp(i,j,1) = sum(i~j)/j-i+1
    public double largestSumOfAverages(int[] A, int K) {
        int n=A.length;
        int[] preSum=new int[n+1];
        for (int i = 0; i < n; i++)
            preSum[i+1]=preSum[i]+A[i];
        double[][] dp0=new double[n][n];
        double[][] dp=new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++) {
                dp0[i][j] = (double) (preSum[j + 1] - preSum[i]) / (j - i + 1);
                dp[i][j]=dp0[i][j];
            }
//        PrintUtils.print2DDoubleArray(dp);
        double[][] dpnew=new double[n][n];
        for (int k = 1; k < K; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    dpnew[i][j]=dp[i][j];
                    for (int l = i; l+k <= j; l++) {
                        dpnew[i][j]=Math.max(dpnew[i][j], dp0[i][l]+dp[l+1][j]);
                    }
                }
            }
            double[][] temp=dp;  dp=dpnew; dpnew=temp;
//            System.out.println("-----------------------");
//            PrintUtils.print2DDoubleArray(dp);
        }
        return dp[0][n-1];
    }
}
