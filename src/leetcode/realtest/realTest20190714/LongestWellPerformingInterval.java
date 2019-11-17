package leetcode.realtest.realTest20190714;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shibing
 * @since 2019/7/14 12:02
 */
public class LongestWellPerformingInterval {
    public static void main(String[] args) {
        LongestWellPerformingInterval interval=new LongestWellPerformingInterval();
        int[] hours = {9,9,6,0,6,6,9};
         hours = new int[]{4,3,9,9,6,0,6,6,9};
        System.out.println(interval.longestWPI(hours));
    }
    public int longestWPI(int[] hours) {
        Map<Integer,Integer> prefixSum=new HashMap<>();
        prefixSum.put(0, -1);
        int res=0, score=0;
        for (int i = 0; i < hours.length; i++) {
//            System.out.println(score);
            score+=hours[i]>8?1:-1;
            if(score>0)
                res=i+1;
            else {
                prefixSum.putIfAbsent(score, i);
                if(prefixSum.containsKey(score-1))
                    res=Math.max(res, i-prefixSum.get(score-1));
            }
        }
        return res;
    }
}
