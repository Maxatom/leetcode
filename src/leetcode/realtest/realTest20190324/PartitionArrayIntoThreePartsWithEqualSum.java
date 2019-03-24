package leetcode.realtest.realTest20190324;

import java.util.Arrays;

/**
 * @author shibing
 * @since 2019/3/24 10:34
 */
public class PartitionArrayIntoThreePartsWithEqualSum {
    public static void main(String[] args) {
        PartitionArrayIntoThreePartsWithEqualSum sum=new PartitionArrayIntoThreePartsWithEqualSum();
        int[] A=new int[]{0,2,1,-6,6,-7,9,1,2,0,1};
        A=new int[]{0,2,1,-6,6,7,9,-1,2,0,1};
        A=new int[]{3,3,6,5,-2,2,5,1,-9,4};
        A=new int[]{1,1,1};
        A=new int[]{2,1,1};
        A=new int[]{2,3,1};
        A=new int[]{1, -1, 1, -1, 1, -1, 1, -1};
        System.out.println(sum.canThreePartsEqualSum(A));

    }
    public boolean canThreePartsEqualSum(int[] A) {
        int sum=Arrays.stream(A).sum();
        if(sum%3!=0) return false;
        int seg=0, avg=sum/3, cnt=0;
        for (int i = 0; i < A.length; i++) {
            seg+=A[i];
            if(seg==avg) {
                cnt++;
                seg=0;
            }
        }
        return cnt==3;
    }
}
