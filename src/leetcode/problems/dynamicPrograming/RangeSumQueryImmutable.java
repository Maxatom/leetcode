package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

/**
 * @author shibing
 * @since 2019/1/18 22:43
 */
public class RangeSumQueryImmutable {
    int[] sum;
    public static void main(String[] args) {
        int[] test=new int[]{-2, 0, 3, -5, 2, -1};
         test=new int[]{};
        RangeSumQueryImmutable rangeSum=new RangeSumQueryImmutable(test);
        PrintUtils.printArray(rangeSum.sum);
        System.out.println(rangeSum.sumRange(0,0));
    }
    public RangeSumQueryImmutable(int[] nums) {
        sum=new int[nums.length+1];
        for (int i=1;i<=nums.length;i++)
            sum[i]=sum[i-1]+nums[i-1];
    }

    public int sumRange(int i, int j) {
        if(sum.length==1) return 0;
        return sum[j+1]-sum[i];
    }
}
