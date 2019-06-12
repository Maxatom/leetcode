package leetcode.TopInterview;

import utils.PrintUtils;

import java.util.*;

/**
 * @author Shibing
 * @since 2019-06-12 16:44:13
 **/
public class Subsets {
    public static void main(String[] args) {
        Subsets subsets=new Subsets();
        int[] nums={1,2,3};
//        PrintUtils.printList(subsets.subsets(nums), l->l.toString());
        PrintUtils.printList(subsets.subsets1(nums), l->l.toString());
        PrintUtils.printList(subsets.subsets2(nums), l->l.toString());
    }

    //Bit manipulation
    public List<List<Integer>> subsets2(int[] nums) {
        int n=nums.length, p=1<<n;
        List<List<Integer>> res=new ArrayList<>(p);
        for (int i = 0; i < p; i++) res.add(new ArrayList<>());
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < n; j++) {
                if((i>>j & 1) ==1)
                    res.get(i).add(nums[j]);
            }
        }
        return res;
    }

    //Iterative
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        res.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> temp=new ArrayList<>();
            for(List<Integer> list:res){
                List<Integer> listn=new ArrayList<>(list);
                listn.add(nums[i]);
                temp.add(listn);
            }
            res.addAll(temp);
        }
        return res;
    }

    //back tracing
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        recursion(nums, res, 0, new ArrayList<>());
        return res;
    }
    public void recursion(int[] nums, List<List<Integer>> list, int idx, List<Integer> cur){
        list.add(new ArrayList<>(cur));
        for (int i = idx; i < nums.length; i++) {
            cur.add(nums[i]);
            recursion(nums, list, i+1, cur);
            cur.remove(cur.size()-1);
        }
    }
}
