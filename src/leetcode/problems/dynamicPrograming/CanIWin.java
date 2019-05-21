package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

/**
 * @author Shibing
 * @since 2019-05-21 14:56:39
 **/
public class CanIWin {
    public static void main(String[] args) {
        CanIWin win=new CanIWin();
        int maxChoosableInteger = 10, desiredTotal = 11;
        maxChoosableInteger = 1; desiredTotal = 2;
        maxChoosableInteger=10; desiredTotal= 40;
        System.out.println(win.canIWin(maxChoosableInteger, desiredTotal));
        System.out.println(win.canIWin1(maxChoosableInteger, desiredTotal));
    }
    public boolean canIWin1(int maxChoosableInteger, int desiredTotal) {
        int[][] dp=new int[desiredTotal+1][2];
        for (int i = 1; i <= maxChoosableInteger; i++) {
            dp[i][1]=1;
        }
        for (int i = maxChoosableInteger+1; i <= desiredTotal; i++) {
            dp[i][0]=1;
        }
        for (int i = 1; i <= maxChoosableInteger; i++) {
            for (int j = maxChoosableInteger+1; j <= desiredTotal; j++) {
                dp[j][1] |= dp[j-i][0];
                dp[j][0] &= dp[j-i][1];
            }
        }
        PrintUtils.print2DIntArray(dp);
        return dp[desiredTotal][1]==1;
    }
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int[][] memo=new int[desiredTotal+1][2];
        boolean res=recursive(maxChoosableInteger, desiredTotal, 0, true, memo);
        PrintUtils.print2DIntArray(memo );
        return res;
    }
    boolean recursive(int maxChoosableInteger, int desiredTotal, int visited, boolean turn, int[][] memo){
        if(desiredTotal<=0) return turn;
        if(visited==(1<<maxChoosableInteger)-1) return !turn;
        if(memo[desiredTotal][turn?1:0]>0) return memo[desiredTotal][turn?1:0]==1?true:false;
        boolean res=true;
        for (int i = maxChoosableInteger; i > 0; i--) {
            if(((1<<(i-1))&visited)==0){
                boolean temp=recursive(maxChoosableInteger, desiredTotal-i, (1<<(i-1))|visited, !turn, memo);
                if(turn && !temp) return true;
                res &=temp;
            }
        }
        if(turn){
            memo[desiredTotal][turn?1:0] = true?1:2;
            return false;
        } else{
            memo[desiredTotal][turn?1:0] = res?1:2;
            return res;
        }
    }
}
