package leetcode.realtest.Top100Interview;

import utils.PrintUtils;

import java.util.Arrays;

/**
 * @author shibing
 * @since 2019/6/7 21:48
 */
public class WiggleSortII {
    public static void main(String[] args) {
        WiggleSortII sortII=new WiggleSortII();
        int[] nums={1, 5, 1, 1, 6, 4};
        nums=new int[]{1, 3, 2, 2, 3, 1};
        nums=new int[]{};
        sortII.wiggleSort(nums);
        PrintUtils.printArray(nums);
    }
    public void wiggleSort(int[] nums) {
        int n=nums.length;
        Arrays.sort(nums);
        int[] res=new int[nums.length];
        int j=(n+1)/2, i=0;
        for (int k = 0; k < n; k++) {
            res[k]=((k&1)==1)?nums[j++]: nums[i++];
        }
        PrintUtils.printArray(res);
        System.arraycopy(res, 0, nums, 0, n);
    }
}
