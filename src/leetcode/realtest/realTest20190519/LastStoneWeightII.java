package leetcode.realtest.realTest20190519;

import utils.PrintUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shibing
 * @since 2019/5/19 11:38
 */
public class LastStoneWeightII {
    public static void main(String[] args) {
        LastStoneWeightII weightII=new LastStoneWeightII();
        int[] stones=new int[]{2,7,4,1,8,1};
        stones=new int[]{31,26,33,21,40};
//        stones=new int[]{5,5,5,5,5,90};
//        stones=new int[] {2,3,4,2,3,4,3,4,54,2,34,2};
//        stones=new int[]{21,16,23,32,25,13,20,18,22,21,84,35,33,17,27,24,10,19,31,26,94,37,31,25,24,25,15,23,17,13};
//        System.out.println(weightII.lastStoneWeightII(stones));
        System.out.println(weightII.lastStoneWeightII1(stones));
    }

    //TLE O(T*n)
    public int lastStoneWeightII1(int[] stones) {
        int n=stones.length, total=0;
        for (int i = 0; i < n; i++)  total+=stones[i];
        boolean[] hasSum=new boolean[total+1];
        int[] visited=new int[total+1];
        hasSum[0]=true;
        int min=Integer.MAX_VALUE;
        for(int sum=1; sum<=total; sum++) {
            for (int i = 0; i < n; i++) {
                int r=sum-stones[i];
                if(r>=0 && (visited[r] & (1<<(n-i-1))) == 0 && hasSum[r]){
                    hasSum[sum] = true;
                    visited[sum]= visited[r] | (1<<(n-i-1));
                    break;
                }
            }
            if(hasSum[sum]) {
                min=Math.min(min, Math.abs(total-2*sum));
//                System.out.println("min="+min+", sum="+sum +" , total-2*sum="+ (total-2*sum));
            }
        }
        return min;
    }

    //TLE O(2^N)
    public int lastStoneWeightII(int[] stones) {
        int n=stones.length, total=0;
        for (int i = 0; i < n; i++)  total+=stones[i];
        recursive(stones, total, 0, 0);
        return min;
    }
    Map<Integer, Integer> cache=new HashMap<>();
    int min=Integer.MAX_VALUE;
    public void recursive(int[] stones, int total, int sum, int idx){
        int n=stones.length;
        if(idx == n) {
            min=Math.min(min, Math.abs(total-2*sum));
            return;
        }
        sum+=stones[idx];
        if(cache.containsKey(sum)) {
            min=Math.min(min, cache.get(sum));
            return;
        }
        min=Math.min(min , Math.abs(total-2*sum));
        if(sum<total/2)
            recursive(stones, total, sum, idx+1);
        sum-=stones[idx];
        recursive(stones, total, sum, idx+1);
    }
}
