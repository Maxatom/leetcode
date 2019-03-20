package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;
import utils.PrintUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author shibing
 * @since 2019/1/7 21:22
 */
public class StoneGame {
    public static void main(String[] args) {
        StoneGame game=new StoneGame();
         int[] array=PrintUtils.convertStringToIntArray("[5,3,4,5]");
//         array=PrintUtils.convertStringToIntArray("[5,4]");
//        array=PrintUtils.convertStringToIntArray("[5,4,8,6]");
        array=PrintUtils.convertStringToIntArray("[5,4,1,3,8,6]");
//        array=PrintUtils.convertStringToIntArray("[1, 3, 2]");
        array=PrintUtils.convertStringToIntArray("[1, 3, 1]");
//        array=PrintUtils.convertStringToIntArray("[7,7,12,16,41,48,41,48,11,9,34,2,44,30,27,12,11,39,31,8,23,11,47,25,15,23,4,17,11,50,16,50,38,34,48,27,16,24,22,48,50,10,26,27,9,43,13,42,46,24]");
//        array=game.generate(6);
        PrintUtils.printIntArray(array);
        System.out.println(game.stoneGame3(array));
        System.out.println(game.stoneGame5(array));
    }


    //leetcode, awice's algorithm, my code
    //dp(i,j) means the largest score alex can score
    //the same as  biggest number of stones you can get "more than opponent" picking piles in piles[i]~piles[j]
    public boolean stoneGame5(int[] piles) {
        int[][] dp=new int[piles.length][piles.length];
        for (int size = 1; size <= piles.length; size++)
            for (int i = 0; i <=piles.length-size; i++) {
                int j=i+size-1;
                if(i==j){
                    if (((i + piles.length - j) & 1) == 1) // alex's turn
                        dp[i][j] = piles[i];
                        //lee's turn
                    else dp[i][j] = -piles[i];
                }else {
                    if (((i + piles.length - j) & 1) == 1) // alex's turn
                        dp[i][j] = Math.max(piles[i] + dp[i + 1][j], piles[j] + dp[i][j - 1]);
                        //lee's turn
                    else dp[i][j] = Math.min(-piles[i] + dp[i + 1][j], -piles[j] + dp[i][j - 1]);
                }
            }
        PrintUtils.print2DIntArray(dp);
        return dp[0][piles.length-1]>0;
    }

    //lee's code
    public boolean stoneGame4(int[] piles) {
        int[][] dp=new int[piles.length][piles.length];
        for (int j = 0; j < piles.length; j++) dp[j][j]=piles[j];
        for (int d = 1; d < piles.length; d++)
            for (int i = 0; i <piles.length-d; i++) {
                dp[i][i+d]=Math.max(piles[i]-dp[i+1][i+d], piles[i+d]-dp[i][i+d-1]);
            }
        PrintUtils.print2DIntArray(dp);
        return dp[0][piles.length-1]>0;
    }

    //My code, simplify 1D Array
    //O(N^2)  lee's algorithm
    //dp(i,j) means the biggest number of stones you can get "more than opponent" picking piles in piles[i]~piles[j]
    public boolean stoneGame3_1(int[] piles) {
        int[] dp=new int[piles.length];
        for (int j = 0; j < piles.length; j++)
            for (int i = j; i >= 0; i--) {
                if(i==j) dp[i]=piles[i];
                else dp[i]=Math.max(piles[i]-dp[i+1], piles[j]-dp[i]);
            }
        PrintUtils.printIntArray(dp);
        return dp[0]>0;
    }

    //O(N^2)  lee's algorithm , my code
    //dp(i,j) means the biggest number of stones you can get "more than opponent" picking piles in piles[i]~piles[j]
    public boolean stoneGame3(int[] piles) {
        int[][] dp=new int[piles.length][piles.length];
        for (int j = 0; j < piles.length; j++)
            for (int i = j; i >= 0; i--) {
                if(i==j) dp[i][j]=piles[i];
                else dp[i][j]=Math.max(piles[i]-dp[i+1][j], piles[j]-dp[i][j-1]);
            }
        PrintUtils.print2DIntArray(dp);
        return dp[0][piles.length-1]>0;
    }

    // Failed ERROR
    // O(N^2) bottom-up
    //DP dp(i, j) means the biggest num of stones you can get picking piles in piles[i]~piles[j]
    //dp(i, j)=max(p(i)+dp(i+1, j), dp(i, j-1)+p(j)), dp(i,i)=p(i)
    public boolean stoneGame2(int[] piles) {
        int halftotal=Arrays.stream(piles).sum()>>2;
        int[][] dp=new int[piles.length][piles.length];
        int[][] dp1=new int[piles.length][piles.length];
        for (int j = 0; j < piles.length; j++) {
            for (int i = j; i >= 0; i--) {
                if(i==j) dp[i][j]=piles[i];
                else{
                    dp1[i][j]=Math.max(dp[i+1][j], dp[i][j-1]);
                    dp[i][j]=Math.max(piles[i]+dp1[i+1][j], piles[j]+dp1[i][j-1]);
                }
            }
        }
        PrintUtils.print2DIntArray(dp1);
        PrintUtils.print2DIntArray(dp);
        return dp[0][piles.length-1]>halftotal;
    }


    public boolean stoneGame1(int[] piles) {
        int halftotal=Arrays.stream(piles).sum()>>2;
        int alex=recursive1(piles, -1, piles.length, 0, halftotal, true);
        if(alex>halftotal) return true;
        else return false;
    }

    //ERROR , this idea is wrong
    //DP top-down
    Map<Integer, Integer> map=new HashMap<>();
    int recursive1(int[] piles, int left, int right, int alex, int halftotal, boolean isalex){
        if(left+1==right||alex>halftotal) return alex;
        Integer key=left*1000+right;
        if(map.containsKey(key)) return map.get(key);
        int alex1, alex2;
        if(isalex){
            alex1 = recursive1(piles, left+1, right, alex+piles[left+1], halftotal, false);
            if(alex1>halftotal) return alex1;
            alex2 = recursive1(piles, left, right-1, alex+piles[right-1], halftotal, false);
        }else {
            alex1=recursive1(piles, left+1, right, alex, halftotal, true);
            if(alex1>halftotal) return alex1;
            alex2=recursive1(piles, left, right-1, alex, halftotal, true);
        }
        map.put(key, Math.max(alex1, alex2));
        return map.get(key);
    }



    public boolean stoneGame(int[] piles) {
        int halftotal=Arrays.stream(piles).sum()/2;
        return recursive(piles, -1, piles.length, 0, halftotal, true);
    }

    //ERROR , this solution is wrong
    //O(2^N) TLE
    boolean recursive(int[] piles, int left, int right, int alex, int halftotal, boolean isalex){
        if(left+1==right) return alex>halftotal?true:false;
        boolean choice1,choice2;
        if(isalex) {
                alex += piles[++left];
                if(alex>halftotal) return true;
                choice1 = recursive(piles, left, right, alex, halftotal, false);
                alex -= piles[left--];
                alex += piles[--right];
                if(alex>halftotal) return true;
                choice2 = recursive(piles, left, right, alex, halftotal, false);
        }else {
            choice1=recursive(piles, ++left, right, alex, halftotal, true);
            left--;
            choice2=recursive(piles, left, --right, alex, halftotal, true);
        }
        if(choice1||choice2) return true;
        else return false;
    }

    int[] generate(int length){
        int[] A=new int[length];
        Random rand=new Random(System.currentTimeMillis());
        int sum=0;
        for (int i = 0; i < length; i++) {
            A[i]=rand.nextInt(10);
            sum+=A[i];
        }
        if((sum&1)==0) A[length-1]++;
        return A;
    }
}
