package leetcode.problems.backtrace;

import leetcode.realtest.Top100Interview.Permutations;
import utils.PrintUtils;

import java.util.*;

/**
 * @author Shibing
 * @since 2019-06-05 16:04:47
 **/
public class PermutationsII {
    public static void main(String[] args) {
        PermutationsII permutationsII=new PermutationsII();
        int[] nums=new int[]{1,2,3,3};
        System.out.println(permutationsII.permuteUnique(nums));
        System.out.println(permutationsII.permuteUnique1(nums));
    }

    public List<List<Integer>> permuteUnique1(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();
        Arrays.sort(nums);
        recursion(nums, list, new ArrayList<>(), new boolean[nums.length]);
        return list;
    }
    public void recursion(int[] nums, List<List<Integer>> list, List<Integer> cur, boolean[] used){
        if(cur.size()==nums.length){
            list.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(used[i] || i>0 && nums[i]==nums[i-1] && !used[i-1]) continue;
            used[i]=true;
            cur.add(nums[i]);
            recursion(nums, list, cur, used);
            cur.remove(cur.size()-1);
            used[i]=false;
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list=new ArrayList<>();
        recursion(nums, list, 0);
        return list;
    }
//    Set<String> set=new HashSet<>();
    public void recursion(int[] nums, List<List<Integer>> list, int begin){
        if(begin>=nums.length){
            List<Integer> curlist=new ArrayList<>();
            for(int n:nums) curlist.add(n);
//            if(set.contains(curlist.toString())) return;
//            else set.add(curlist.toString());
            list.add(curlist);
        }
        for (int i = begin; i < nums.length; i++) {
            if(i!=begin && nums[begin]==nums[i]) continue;
            int temp=nums[begin]; nums[begin]=nums[i]; nums[i]=temp;
            recursion(nums, list, begin+1);
//            temp=nums[i]; nums[i]=nums[begin]; nums[begin]=temp;
        }
    }
}
