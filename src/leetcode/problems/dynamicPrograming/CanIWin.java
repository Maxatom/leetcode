package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shibing
 * @since 2019-05-21 14:56:39
 **/
public class CanIWin {
    public static void main(String[] args) {
        CanIWin win=new CanIWin();
        int maxChoosableInteger = 10, desiredTotal = 11;
//        maxChoosableInteger = 1; desiredTotal = 2;
//        maxChoosableInteger=10; desiredTotal= 40;
        maxChoosableInteger=3; desiredTotal= 6;
        System.out.println(win.canIWin(maxChoosableInteger, desiredTotal));
    }

    //top-down dp
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if((1+maxChoosableInteger)*maxChoosableInteger/2<desiredTotal) return false;
        return recursive(maxChoosableInteger, desiredTotal, 0);
    }
    Map<Integer,Boolean> cache=new HashMap<>();
    boolean recursive(int maxChoosableInteger, int desiredTotal, int visited){
        int key=visited;
        if(cache.containsKey(key)) return cache.get(key);
        boolean res=false;
        for (int i = maxChoosableInteger; i > 0; i--) {
            if(((1<<(i-1))&visited)==0){
                if(desiredTotal<=i ||
                !recursive(maxChoosableInteger, desiredTotal-i, (1<<(i-1))|visited)){
                    res=true; break;
                }
            }
        }
        cache.put(key, res);
        return res;
    }
}
