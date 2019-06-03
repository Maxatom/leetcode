package leetcode.problems.array;

import utils.PrintUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shibing
 * @since 2019-06-03 18:16:50
 **/
public class SubarraySumEqualsK {
    public static void main(String[] args) {
        SubarraySumEqualsK equalsK=new SubarraySumEqualsK();
        int[] nums=new int[]{1,1,1}; int K=2;
        nums=new int[]{1,-1,0,1,-1}; K=0;
//        System.out.println(equalsK.subarraySum(nums, K));
        System.out.println(equalsK.subarraySum1(nums, K));
        System.out.println(equalsK.subarraySum2(nums, K));
    }

    //O(N)
    public int subarraySum2(int[] nums, int k) {
        Map<Integer, Integer> map=new HashMap<>();
        int sum=0, cnt=0;
        map.put(0,1);
        for (int i = 0; i < nums.length; i++) {
            sum+=nums[i];
            if(map.containsKey(sum-k)) cnt+=map.get(sum-k);
            map.put(sum, map.getOrDefault(sum,0)+1);
        }
        System.out.println(map);
        return cnt;
    }

    public int subarraySum1(int[] nums, int k) {
        int cnt=0;
        for (int i = 0; i < nums.length; i++) {
            int sum=0;
            for (int j = i; j < nums.length; j++) {
                sum+=nums[j];
               if(sum==k) cnt++;
            }
        }
        return cnt;
    }

    //TLE
    public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> map=new HashMap<>();
        int cnt=0;
        for (int i = 0; i < nums.length; i++) {
            Map<Integer,Integer> next=new HashMap<>();
            if(nums[i]==k) cnt++;
            next.put(nums[i], 1);
            for(Map.Entry<Integer, Integer> e:map.entrySet()){
                int key=nums[i]+e.getKey();
                next.put(key, next.getOrDefault(key,0)+e.getValue());
                if(key==k) cnt+=e.getValue();
            }
            map=next;
        }
        return cnt;
    }
}
