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
        PrintUtils.printList(subsetsII.subsetsWithDup1(nums), l->l.toString());
    }

    //count the appearing times of each element, and see them as special element that you can either put one of which into a list
    //or two, or three... etc.
    public List<List<Integer>> subsetsWithDup1(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < nums.length; i++)
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        List<List<Integer>> res=new ArrayList<>();
        res.add(new ArrayList<>());
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            List<List<Integer>> cur=new ArrayList<>();
            for(List<Integer> list:res){
                List<Integer> newlist=new ArrayList<>(list);
                cur.add(new ArrayList<>(list));
                for (int i = 0; i < entry.getValue(); i++) {
                    newlist.add(entry.getKey());
                    cur.add(new ArrayList<>(newlist));
                }
            }
            res.addAll(cur);
        }
        return res;
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
