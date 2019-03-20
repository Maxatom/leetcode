package leetcode.problems.dynamicPrograming;

/**
 * @author shibing
 * @since 2019/1/26 8:40
 */
public class HouseRobber {
    public static void main(String[] args) {
        HouseRobber robber=new HouseRobber();
        int[] nums=new int[]{1,2,3,1};
//        nums=new int[]{2,7,9,3,1};
//        nums=new int[]{2,1,1,5,1};
//        nums=new int[0];
//        nums=new int[]{2};
//        nums=new int[]{2,3};
        System.out.println(robber.rob(nums));
        System.out.println(robber.rob1(nums));
    }

    //optimizing
    //rob(i)=max(rob(i-1), rob(i-2)+house(i)), rob(i) means the max profit the robber can get from house 0~i
    public int rob1(int[] nums) {
        if(nums.length==0) return 0;
        int pre=0;
        int cur=nums[0];
        int temp;
        for (int i = 1; i < nums.length; i++) {
            temp=cur;
            cur=Math.max(cur,pre+nums[i]);
            pre=temp;
        }
        return cur;
    }

    //dp[i] means if he robber the ith house , the max profit the robber can get from 0~i
    //dp[i]=max(dp[i-2]+nums[i], dp[i-3]+nums[i])
    public int rob(int[] nums) {
        int max=0;
        int[] dp=new int[4];
        for (int i = 0; i < nums.length; i++) {
            dp[3]=Math.max(dp[0], dp[1])+nums[i];
            max=Math.max(dp[3],max);
            dp[0]=dp[1];
            dp[1]=dp[2];
            dp[2]=dp[3];
        }
        return max;
    }
}
