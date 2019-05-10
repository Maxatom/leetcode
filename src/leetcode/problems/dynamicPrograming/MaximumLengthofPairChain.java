package leetcode.problems.dynamicPrograming;

import leetcode.problems.utils.Utils;
import utils.PrintUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Shibing
 * @since 2019-05-10 15:00:54
 **/
public class MaximumLengthofPairChain {
    public static void main(String[] args) {
        MaximumLengthofPairChain chain=new MaximumLengthofPairChain();
        int[][] pairs= {{1,2}, {2,3}, {3,4}};
//        pairs=new int[][]{{9,10},{-4,9},{-5,6},{-5,9},{8,9}};
//        pairs=new int[][]{{0,1},{0,2},{0,3},{1,3},{1,2},{2,3},{2,4},{3,4},{6,7},{8,9},{6,10}};
        System.out.println(chain.findLongestChain(pairs));
    }
    //greedy
    public int findLongestChain(int[][] pairs) {
        if(pairs.length==0) return 0;
//        Arrays.sort(pairs, Comparator.comparingInt(p->p[1]));
        Utils.quickSort(pairs, Comparator.comparing(p->p[1]));
        int max=1;
        for (int i = 0; i < pairs.length; i++) {
            int j=i+1;
            while (j<pairs.length && pairs[j][0]<=pairs[i][1]) j++;
            if(j==pairs.length) break;
            max++;
            i=j-1;
        }
        return max;
    }
}
