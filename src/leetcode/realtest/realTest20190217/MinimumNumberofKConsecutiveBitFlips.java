package leetcode.realtest.realTest20190217;

import java.util.Arrays;

/**
 * @author Shibing
 * @since 2019-02-19 09:45:28
 **/
public class MinimumNumberofKConsecutiveBitFlips {
    public static void main(String[] args) {
        MinimumNumberofKConsecutiveBitFlips flips=new MinimumNumberofKConsecutiveBitFlips();
        int[] A=new int[]{0,1,0}; int K=1;
//        A=new int[]{1,1,0}; K=2;
//        A=new int[]{1}; K=1;
//        A=new int[]{0}; K=1;
//        A=new int[]{0,0,0,1,0,1,1,0}; K=3;
        System.out.println(flips.minKBitFlips(Arrays.copyOf(A,A.length), K));
        System.out.println(flips.minKBitFlips1(A, K));
    }

    //O(N)
    public int minKBitFlips1(int[] A, int K) {
        int cur=0, res=0;
        for (int i = 0; i < A.length; i++) {
            if(i>=K && A[i-K]==2) cur--;
            if((cur&1)==A[i]) {
                if(i+K>A.length) return -1;
                cur++;
                A[i]=2;
                res++;
            }
        }
        return res;
    }
    //O(NK)
    public int minKBitFlips(int[] A, int K) {
        int count=0, N=A.length;
        for (int i = 0; i <= N-K; i++)
            if(A[i]==0) {
                for (int j = i; j <i+K; j++)
                    A[j]=A[j]==0?1:0;
                count++;
            }
        for (int i = N-K; i <N; i++)
            if(A[i]==0) return -1;
        return count;
    }
}
