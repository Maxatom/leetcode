package leetcode.realtest.realTest20190505;

import utils.PrintUtils;

import java.util.Arrays;

/**
 * @author Shibing
 * @since 2019-05-06 11:38:56
 **/
public class MovingStonesUntilConsecutiveII {
    public static void main(String[] args) {
        MovingStonesUntilConsecutiveII  consecutiveII=new MovingStonesUntilConsecutiveII();
        int[] stones= {1,2,3,7,8,9}; //[3, 3]
//        stones=new int[]{1,3,5,9,13,15,17}; //[4, 9]
//        stones=new int[]{7,4,9}; //[1,2]
        stones=new int[]{6,5,4,3,10}; //[2,3]
        stones=new int[]{100,101,104,102,103}; //[0, 0]
        PrintUtils.printArray(consecutiveII.numMovesStonesII(stones));
    }

    //slide window
    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int j=0, n=stones.length, min=n;
        for (int i = 0; i < stones.length; i++) {
            while (stones[i]-stones[j]>=n) ++j;
            if(n-(i-j+1)==1 && stones[i]-stones[j]==n-2) min=Math.min(min, 2);
            else min=Math.min(min, n-(i-j+1));
        }
        return new int[]{min, Math.max(stones[n-1]-stones[1]-n+2, stones[n-2]-stones[0]-n+2)};
    }
}
