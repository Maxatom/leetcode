package leetcode.realtest.realTest20190519;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author shibing
 * @since 2019/5/19 10:43
 */
public class LastStoneWeight {
    public static void main(String[] args) {
        LastStoneWeight weight=new LastStoneWeight();
        int[] stones={2,7,4,1,8,1};
        stones=new int[]{2};
        System.out.println(weight.lastStoneWeight(stones));
    }
    public int lastStoneWeight(int[] stones) {
        Queue<Integer> queue=new PriorityQueue<>(stones.length, Collections.reverseOrder());
        for(int w:stones) queue.add(w);
        while (queue.size()>1) queue.add(queue.remove()-queue.remove());
        return queue.peek();
    }
}
