package leetcode.TopInterview;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shibing
 * @since 2019-06-05 15:04:16
 **/
public class Permutations {
    public static void main(String[] args) {
        Permutations permutations=new Permutations();
        int[] nums={1,2,3};
//        nums=new int[]{1,2,3,4,5,6,7};
        System.out.println(permutations.permute(nums));
        System.out.println(permutations.permute1(nums));
    }
    //optimization
    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();
        recursion(nums, list,  0);
        return list;
    }
    public void recursion(int[] nums, List<List<Integer>> list, int begin){
        if(begin>=nums.length){
            List<Integer> curlist=new ArrayList<>();
            for(int num:nums) curlist.add(num);
            list.add(curlist);
        }
        for (int i = begin; i < nums.length; i++) {
            int temp=nums[begin]; nums[begin]=nums[i]; nums[i]=temp;
            recursion(nums, list, begin+1);
            temp=nums[i]; nums[i]=nums[begin]; nums[begin]=temp;
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();
        recursion(nums, list ,new ArrayList<>(), 0);
        return list;
    }
    public void recursion(int[] nums, List<List<Integer>> list, List<Integer> cur, int visited){
        if(visited==(1<<nums.length)-1){
            list.add(cur); return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(((1<<i) & visited) !=0) continue;
            List<Integer> next=new ArrayList<>(cur);
            next.add(nums[i]);
            recursion(nums, list, next, (1<<i) | visited);
        }
        return;
    }
}
