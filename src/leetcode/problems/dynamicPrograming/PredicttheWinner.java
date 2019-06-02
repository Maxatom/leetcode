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
//        nums=new int[]{1, 5, 233, 7};
//        nums=new int[]{3};
//        System.out.println(winner.PredictTheWinner(nums));
        System.out.println(winner.PredictTheWinner1(nums));
        System.out.println(winner.PredictTheWinner2(nums));
    }

    public boolean PredictTheWinner2(int[] nums) {
        int n=nums.length, total=0, max=0;
        for(int w:nums) total+=w;
        int[][] dp=new int[n+1][n+1];
        for (int t = 1; t <= n; t++) {
            for (int l = 0; l <= t; l++) {
                int r=n+l-1-t;
                if(l==0) {
                    dp[t][l] = dp[t-1][l] + ((t&1)==1 ? nums[r+1] : 0);
                }else if(l==t) {
                    dp[t][l] = dp[t-1][l-1] + ((t&1)==1 ? nums[l-1] : 0);
                }else {
                    if ((t & 1) == 1) dp[t][l] = Math.max(dp[t - 1][l] + nums[r + 1], dp[t - 1][l - 1] + nums[l]);
                    else dp[t][l] = Math.min(dp[t - 1][l], dp[t - 1][l - 1]);
                }
                if(t==n) max=Math.max(max, dp[t][l]);
            }
        }
        PrintUtils.print2DIntArray(dp);
        System.out.println(max);
        return max>=(total+1)/2;
    }



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
