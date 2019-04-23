package leetcode.realtest.realTest20190421;

/**
 * @author shibing
 * @since 2019/4/21 11:06
 */
public class MaximumSumofTwoNonOverlappingSubarrays {
    public static void main(String[] args) {
        MaximumSumofTwoNonOverlappingSubarrays subarrays=new MaximumSumofTwoNonOverlappingSubarrays();
        int[] A =new int[] {0,6,5,2,2,5,1,9,4}; int L = 1, M = 2;
        A =new int[]{3,8,1,3,2,1,8,9,0}; L = 3; M = 2;
        A = new int[]{2,1,5,6,0,9,5,0,3,8}; L = 4; M = 3;
        A=new int[]{2,3};L=1; M=1;
        System.out.println(subarrays.maxSumTwoNoOverlap(A,L,M));
    }

//    public int maxSumTwoNoOverlap1(int[] A, int L, int M) {
//
//    }

    //brute force O(N^2)
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int[] prefixSum=new int[A.length];
        int[] sumL=new int[A.length-L+1];
        int[] sumM=new int[A.length-M+1];
        for (int i = 0; i < A.length; i++) {
            prefixSum[i]=(i>0?prefixSum[i-1]:0)+A[i];
            if(i>=L-1) sumL[i-L+1]=prefixSum[i]-(i>=L?prefixSum[i-L]:0);
            if(i>=M-1) sumM[i-M+1]=prefixSum[i]-(i>=M?prefixSum[i-M]:0);
        }
        int max=0;
        for (int i = 0; i < sumL.length; i++)
            for (int l = 0; l < sumM.length; l++)
                if (i + L <= l || l + M <= i)
                    max = Math.max(max, sumL[i] + sumM[l]);
        return max;
    }
}
