package leetcode.realtest.realTest20190721;

import utils.PrintUtils;

/**
 * @author shibing
 * @since 2019/7/21 10:36
 */
public class NumberofEquivalentDominoPairs {
    public static void main(String[] args) {
        NumberofEquivalentDominoPairs pairs=new NumberofEquivalentDominoPairs();
        int[][] dominoes = {{1,2},{2,1},{3,4},{5,6}};
        dominoes=new int[][]{{1,2},{1,2},{1,1},{1,2},{2,2}};
        dominoes=new int[][]{{1,1},{1,1},{1,1},{2,2}};
        System.out.println(pairs.numEquivDominoPairs(dominoes));
    }
    public int numEquivDominoPairs(int[][] dominoes) {
        int[][] cnt=new int[10][10];
        for(int[] domi:dominoes){
            cnt[domi[0]][domi[1]]++;
            if(domi[0]!=domi[1])
                cnt[domi[1]][domi[0]]++;
        }
        PrintUtils.print2DIntArray(cnt);
        int res=0;
        for (int i = 1; i < 10; i++) {
            for (int j = i; j < 10; j++) {
                if(cnt[i][j]>1) res+=count(cnt[i][j]);
            }
        }
        return res;
    }
    public int count(int n){
        return n*(n-1)/2;
    }
}
