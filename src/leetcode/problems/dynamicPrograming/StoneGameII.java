package leetcode.problems.dynamicPrograming;

import utils.ArrayGenerator;
import utils.PrintUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * No. 1140 stoneGameII
 */
public class StoneGameII {
    public static void main(String[] args) {
        StoneGameII stoneGameII=new StoneGameII();
        int[] piles = {2,7,9,4,4};
        piles=
//                new int[]{54, 30, 47, 17, 27, 16, 82, 62, 50, 3, 66, 4, 96, 89, 99, 69, 1, 56, 89, 40, 54, 79, 12, 70, 73, 9, 73, 21, 60, 74};
//                new int[]{39, 67, 24, 1, 41, 29, 37, 28, 80, 45, 37, 90, 80, 23, 6, 9, 72, 57, 36, 38, 55, 2, 66, 5, 88, 94, 17, 34, 80, 70};
//        new int[]{8270,7145,575,5156,5126,2905,8793,7817,5532,5726,7071,7730,5200,5369,5763,7148,8287,9449,7567,4850,1385,2135,1737,9511,8065,7063,8023,7729,7084,8407};
                new int[]{2,5,3,4,8};
//        ArrayGenerator.intArray(30,1,100);
        System.out.println(Arrays.toString(piles));
        System.out.println(stoneGameII.stoneGameII(piles));
        System.out.println("----------"+stoneGameII.stoneGameII2(piles));

    }


    public int stoneGameII(int[] piles) {
        return stoneGameII(piles,1, 0, 0, true);
    }

    //dp[i][j] is the maximum stones of alex can get when starting at index i with M=j
    //dp[i][j] = max(sufsum[i] - dp[i + X][max(j, X)]) where 1<= X <= 2j;
    public int stoneGameII2(int[] piles){
        int n=piles.length;
        int[] sum=new int[n];
        sum[n-1]=piles[n-1];
        int[][] dp=new int[n+1][n+1];
        for (int i = n-2; i >=0 ; i--) {
            sum[i]=sum[i+1]+piles[i];
        }
        for (int i = 1; i <= n; i++) {
            dp[n-1][i]=piles[n-1];
        }
        for (int i = n-2; i >= 0; i--) {
            for (int j = n-1; j >= 1; j--) {
                if(n-i<=j) dp[i][j]=sum[i];
                else
                    for (int x = 1; x <= 2*j && i+x<= n; x++) {
                        dp[i][j]=Math.max(dp[i][j], sum[i]- dp[i+x][Math.max(j,x)]);
                    }
            }
        }
//        PrintUtils.print2DIntArray(dp);
        return dp[0][1];
    }

    //dfs TLE
    Map<Integer, int[][][]> map=new HashMap<>();
    public int stoneGameII(int[] piles,int M, int start, int alex, boolean turn) {
        if(start>=piles.length) return alex;
//        if(map.containsKey(alex)) {
//            if (map.get(alex)[M][start][turn ? 1 : 0] != 0) return map.get(alex)[M][start][turn ? 1 : 0];
//        }
        int maxa=Integer.MIN_VALUE, mina=Integer.MAX_VALUE;
        for (int i = start; i < start + 2 * M && i < piles.length; i++) {
            if(turn) alex += piles[i];
            int re = stoneGameII(piles, Math.max(M, i-start+1), i+1, alex, !turn);
            maxa=Math.max(maxa, re);
            mina=Math.min(mina, re);
        }
        int res=turn?maxa:mina;
//        if(!map.containsKey(alex)) map.put(alex,new int[piles.length*2+1][piles.length][2]);
//        map.get(alex)[M][start][turn?1:0]=res;
        return res;
    }
}
