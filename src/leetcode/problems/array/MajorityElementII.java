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
//        nums=new int[]{1,1,1,3,3,2,2,2};
//        nums=new int[]{};
//        nums=new int[]{1};
//        nums=new int[]{1,2};
        PrintUtils.printList(majority.majorityElement(nums), p->p+" ");
        PrintUtils.printList(majority.majorityElement1(nums), p->p+" ");
    }

    public List<Integer> majorityElement1(int[] nums) {
        if(nums.length<1) return new ArrayList<>();
        int cand1=nums[0], cand2=nums[0], count1=0, count2=0;
        for(int n:nums){
            if(n==cand1) count1++;
            else if(n==cand2) count2++;
            else if(count1==0) {
                cand1 = n;
                count1++;
            } else if(count2==0) {
                cand2 = n;
                count2++;
            } else {
                count1--; count2--;
            }
        }
        count1=0; count2=0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]==cand1) count1++;
            else if(nums[i]==cand2) count2++;
        }
        List<Integer> list=new ArrayList<>();
        int times=(int)Math.floor(nums.length/3);
        if(count1>times) list.add(cand1);
        if(count2>times) list.add(cand2);
        return list;
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
