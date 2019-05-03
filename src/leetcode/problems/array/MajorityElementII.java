package leetcode.problems.array;

import utils.PrintUtils;

import java.util.*;

/**
 * @author shibing
 * @since 2019/5/3 22:36
 */
public class MajorityElementII {
    public static void main(String[] args) {
        MajorityElementII  majority=new MajorityElementII();
        int[] nums=new int[]{3,2,3};
        nums=new int[]{1,1,1,3,3,2,2,2};
//        nums=new int[]{};
//        nums=new int[]{1};
        PrintUtils.printList(majority.majorityElement(nums), p->p+" ");
    }
    //Random Solution
    public List<Integer> majorityElement(int[] nums) {
        if(nums.length<1) return new ArrayList<>();
        Map<Integer,Integer> map=new HashMap<>();
        Random rand=new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
//            System.out.println(rand.nextInt(2));
            int it=rand.nextInt(nums.length);
            map.put(nums[it], 0);
        }
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i]))
            map.put(nums[i], map.get(nums[i])+1);
        }
        List<Integer> list=new ArrayList<>();
        int times=(int)Math.floor(nums.length/3);
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            if(entry.getValue()>times) list.add(entry.getKey());
        }
        return list;
    }
}
