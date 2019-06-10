package leetcode.problems;

/**
 * @author Shibing
 * @since 2019-06-10 16:00:56
 **/
public class JumpGameII {
    public static void main(String[] args) {
        JumpGameII gameII=new JumpGameII();
        int[] nums={2,3,1,1,4};
        nums=new int[]{2,3,1,1,4,5,1,1,2,8};
        nums=new int[]{2,3,0,1,4};
        nums=new int[]{2,3,1,0,4,5,1,0,2,8};
        System.out.println(gameII.jump(nums));
    }


    //TLE
    public int jump(int[] nums) {
        return dfs(nums, 0, new int[nums.length]);
    }
    public int dfs(int[] nums, int cur, int[] memo){
        int n=nums.length;
        if(cur>=n-1) return 0;
        if(memo[cur]>0) return memo[cur];
        if(nums[cur]==0) return n;
        int min=n;
        for (int i = 1; i <= nums[cur]; i++) {
            min=Math.min(min, dfs(nums, cur+i, memo));
        }
        return memo[cur]=min+1;
    }
}
