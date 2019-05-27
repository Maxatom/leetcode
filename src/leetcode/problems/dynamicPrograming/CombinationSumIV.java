package leetcode.problems.dynamicPrograming;

/**
 * @author Shibing
 * @since 2019-05-24 16:37:43
 **/
public class CombinationSumIV {
    public static void main(String[] args) {
        CombinationSumIV sumIV=new CombinationSumIV();
        int[] nums = {1, 2, 3}; int target = 4;
        System.out.println(sumIV.combinationSum4(nums, target));
    }
    public int combinationSum4(int[] nums, int target) {
        int[] dp=new int[target+1];
        dp[0]=1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j <nums.length ; j++) {
                dp[i]+=i>=nums[j]?dp[i-nums[j]]:0;
            }
        }
        return dp[target];
    }
}
