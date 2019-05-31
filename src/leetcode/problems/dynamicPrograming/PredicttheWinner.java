package leetcode.problems.dynamicPrograming;

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
        nums=new int[]{3};
        System.out.println(winner.PredictTheWinner(nums));
        System.out.println(winner.PredictTheWinner1(nums));
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
