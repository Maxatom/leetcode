package leetcode.TopInterview;

import utils.PrintUtils;

import java.util.*;

/**
 * @author Shibing
 * @since 2019-06-12 18:58:00
 **/
public class SubsetsII {
    public static void main(String[] args) {
        SubsetsII subsetsII=new SubsetsII();
        int[] nums={1,2,2};
        PrintUtils.printList(subsetsII.subsetsWithDup(nums), l->l.toString());
    }
    //back tracing
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res=new ArrayList<>();
        recursion(nums, res, 0, new ArrayList<>(), new boolean[nums.length]);
        return res;
    }
    public void recursion(int[] nums, List<List<Integer>> list, int idx, List<Integer> cur, boolean[] used){
        list.add(new ArrayList<>(cur));
        for (int i = idx; i < nums.length; i++) {
            if(i>0 && nums[i]==nums[i-1] && !used[i-1]) continue;
            cur.add(nums[i]); used[i]=true;
            recursion(nums, list, i+1, cur, used);
            cur.remove(cur.size()-1); used[i]=false;
        }
    }
}
