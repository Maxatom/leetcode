package leetcode.TopInterview;

/**
 * @author Shibing
 * @since 2019-06-05 14:12:01
 **/
public class JumpGame {
    public static void main(String[] args) {
        JumpGame game=new JumpGame();
        int[] nums=new int[]{2,3,1,1,4};
        nums=new int[]{3,2,3,1,0,1,4};
        System.out.println(game.canJump(nums));
    }
    public boolean canJump(int[] nums) {
        int n=nums.length, cur=n-1;
        for (int i=cur-1; i >= 0; i-- )
            if (nums[i] >= cur - i) cur=i;
        return cur==0;
    }
}
