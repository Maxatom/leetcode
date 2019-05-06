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
        int[] stones= {1,2,3,7,8,9};
        stones=new int[]{1,3,5,9,13,15,17};
        stones=new int[]{7,4,9};
        stones=new int[]{6,5,4,3,10};
//        stones=new int[]{100,101,104,102,103};
        PrintUtils.printArray(consecutiveII.numMovesStonesII(stones));
    }

    //slide window
    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int empty=0, left=0, cnt=stones.length-1, min=Integer.MAX_VALUE, total=0;
        for (int i = 1; i < stones.length; i++) {
            cnt--;
            int rightInc=stones[i]-stones[i-1]-1;
            empty+=rightInc;
            total+=rightInc;
            if(empty>=cnt){
                do{
                    if(empty==cnt) min=Math.min(min, cnt);
                    if(rightInc>1 && cnt>1){
                        min=Math.min(min, cnt+1);
                    }
                    left++;
                    int leftDec=stones[left]-stones[left-1]-1;
                    empty-=leftDec;
                    cnt++;
                    if(empty<cnt) break;
                }while (left<i);
            }
        }
        return new int[]{min, total-Math.min(stones[1]-stones[0]-1, stones[stones.length-1]-stones[stones.length-2]-1)};
    }
}
