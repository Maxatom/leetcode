package leetcode.problems.array;

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
        System.out.println(equalsK.subarraySum(nums, K));
    }
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
