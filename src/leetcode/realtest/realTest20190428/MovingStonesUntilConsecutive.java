package leetcode.realtest.realTest20190428;

import utils.PrintUtils;

import java.util.Arrays;

/**
 * @author Shibing
 * @since 2019-04-28 10:36:12
 **/
public class MovingStonesUntilConsecutive {
    public static void main(String[] args) {
        MovingStonesUntilConsecutive stones=new MovingStonesUntilConsecutive();
        int a = 1, b = 2, c = 5;
//        a = 4; b = 3; c = 2;
        a=3; b= 5; c=1;
        PrintUtils.printArray(stones.numMovesStones(a, b, c));
    }
    public int[] numMovesStones(int a, int b, int c) {
        if(!(a<b && b<c)){
            int[] arr = new int[]{a, b, c};
            Arrays.sort(arr);
            return numMovesStones(arr[0], arr[1], arr[2]);
        }
        int min=Math.min(b-1-a,1)+Math.min(c-b-1,1);
        if(b-a==2|| c-b==2){
            min=1;
        }
        return new int[]{min, b-1-a+c-b-1};
    }
}
