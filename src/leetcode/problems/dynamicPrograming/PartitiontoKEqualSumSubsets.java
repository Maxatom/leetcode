package leetcode.problems.dynamicPrograming;

/**
 * @author shibing
 * @since 2019/5/11 13:49
 */
public class PartitiontoKEqualSumSubsets {
    public static void main(String[] args) {

    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum=0;
        for (int i = 0; i < nums.length; i++) {
            sum+=nums[i];
        }
        if(sum%k!=0) return false;
        int sumsub=sum/k;
        for (int i = 0; i < nums.length; i++) {

        }
        return false;
    }

}
