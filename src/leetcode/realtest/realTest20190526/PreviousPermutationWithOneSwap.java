package leetcode.realtest.realTest20190526;

import utils.PrintUtils;

import java.util.Arrays;

/**
 * @author shibing
 * @since 2019/5/26 11:49
 */
public class PreviousPermutationWithOneSwap {
    public static void main(String[] args) {
        PreviousPermutationWithOneSwap swap=new PreviousPermutationWithOneSwap();
        int[] A=new int[]{3,2,1};
        A=new int[]{1,1,5};
        A=new int[]{1,9,4,6,7};
        PrintUtils.printArray(swap.prevPermOpt1(A));
    }
    public int[] prevPermOpt1(int[] A) {
        int n=A.length, p=n;
        for (int i = n-1; i >0 ; i--) {
            if(A[i]<A[i-1]) {p=i-1; break;}
        }
        int maxi=p+1;
        for (int i = p+1; i < n; i++) {
            if(A[i]<A[p] && A[maxi]<A[i]) maxi=i;
        }
        if(p<n) {
            int temp = A[p];
            A[p] = A[maxi];
            A[maxi] = temp;
        }
        return A;
    }
}
