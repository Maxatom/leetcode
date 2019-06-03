package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shibing
 * @since 2019-05-31 14:49:10
 **/
public class PredicttheWinner {
    public static void main(String[] args) {
        PredicttheWinner winner=new PredicttheWinner();
        int[] nums=new int[]{1, 5, 2};
        nums=new int[]{1, 5, 233, 7};
        nums=new int[]{3};
//        System.out.println(winner.PredictTheWinner(nums));
//        System.out.println(winner.PredictTheWinner1(nums));
//        System.out.println(winner.PredictTheWinner2(nums));
        System.out.println(winner.PredictTheWinner3(nums));
        System.out.println(winner.PredictTheWinner4(nums));
    }

    //1D Array
    public boolean PredictTheWinner4(int[] nums) {
        int n=nums.length;
        if( (n&1)==0) return true;
        int[] dp=new int[n];
        for (int i = n-1; i >= 0; i--) {
            dp[i]=nums[i];
            for (int j = i+1; j <n ; j++)
                dp[j]=Math.max(nums[i] - dp[j], nums[j]-dp[j-1]);
        }
        return dp[n-1]>=0;
    }

    //bottom-up
    //dp(i,j) is the difference between A's and B's score with i~j
    //dp(i,j)= max(nums[i] - dp(i+1,j), nums[j]-dp(i,j-1))
    //dp(i,i)=nums[i]
    public boolean PredictTheWinner2(int[] nums) {
        int n=nums.length;
        if( (n&1)==0) return true;
        int[][] dp=new int[n][n];
        for (int i = n-1; i >= 0; i--) {
            dp[i][i]=nums[i];
            for (int j = i+1; j <n ; j++)
                dp[i][j]=Math.max(nums[i] - dp[i+1][j], nums[j]-dp[i][j-1]);
        }
        return dp[0][n-1]>=0;
    }

    //another implementation
    public boolean PredictTheWinner3(int[] nums) {
        int n=nums.length;
        int[][] dp=new int[n][n];
        for (int t = 0; t < n; t++) {
            for (int i = 0; i+t <n ; i++) {
                if(t==0) { dp[i][i]=nums[i]; continue; }
                int j=i+t;
                dp[i][j]=Math.max(nums[i] - dp[i+1][j], nums[j]-dp[i][j-1]);
            }
        }
//        PrintUtils.print2DIntArray(dp);
        return dp[0][n-1]>=0;
    }


    // whose score exceed the half of total score
    public boolean PredictTheWinner1(int[] nums) {
        int n=nums.length, total=0;
        for(int w:nums) total+=w;
        int res=recursion(nums, 0, n-1, true);
        return res>=(total+1)/2;
    }
    Map<Integer, Integer>  cache=new HashMap<>();
    public int recursion(int[] nums, int left, int right, boolean isMe){
        if(left>right) return 0;
        int key=left*20+right;
        if(cache.containsKey(key)) return cache.get(key);
        int wl=recursion(nums, left+1, right, !isMe);
        int wr=recursion(nums, left, right-1, !isMe);
        if(isMe) {
            wl+=nums[left];
            wr+=nums[right];
        }
        int res=isMe?Math.max(wl, wr):Math.min(wl, wr);
        cache.put(key, res);
        return res;
    }

    // whose score exceed the half of total score
    public boolean PredictTheWinner(int[] nums) {
        int n=nums.length, total=0;
        for(int w:nums) total+=w;
        return recursive(nums, 0, n-1, true, 0, total);
    }
    public boolean recursive(int[] nums, int left, int right, boolean isMe, int w, int total){
        if(left>right) return w>=(total+1)/2 == isMe;
        boolean resl=recursive(nums, left+1, right, !isMe, isMe?w+nums[left]:w, total);
        boolean resr=recursive(nums, left, right-1, !isMe, isMe?w+nums[right]:w, total);
        return !(resl&&resr);
    }
}
