package leetcode.realtest.realTest20190120;

import utils.PrintUtils;

import java.util.Arrays;

/**
 * @author shibing
 * @since 2019/1/20 11:44
 */
public class SquaresofaSortedArray {
    public static void main(String[] args) {
        SquaresofaSortedArray array=new SquaresofaSortedArray();
        int[] test=new int[]{-4,-1,0,3,10};
         test=new int[]{-7,-3,2,3,11};
        test=new int[]{1};
        PrintUtils.printIntArray(array.sortedSquares(test));
    }
    //O(NlogN) sort
    public int[] sortedSquares(int[] A) {
        for (int i = 0; i < A.length; i++) {
            A[i]=A[i]*A[i];
        }
        Arrays.sort(A);
        return A;
    }
}
