package leetcode.realtest.realTest20190414;


import utils.PrintUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shibing
 * @since 2019/4/14 11:23
 */
public class LongestArithmeticSequence {
    public static void main(String[] args) {
        LongestArithmeticSequence sequence=new LongestArithmeticSequence();
        int[] A=new int[]{3,6,9,12};
//        A=new int[]{9,4,7,2,10};
//        A=new int[]{20,1,15,3,10,5,8};
//        A=new int[]{2,4};
//        A=new int[]{22,8,57,41,36,46,42,28,42,14,9,43,27,51,0,0,38,50,31,60,29,31,20,23,37,53,27,1,47,42,28,31,10,35,39,12,15,6,35,31,45,21,30,19,5,5,4,18,38,51,10,7,20,38,28,53,15,55,60,56,43,48,34,53,54,55,14,9,56,52};
        System.out.println(sequence.longestArithSeqLength(A));
        System.out.println(sequence.longestArithSeqLength1(A));
    }


    //use hash map
    public int longestArithSeqLength1(int[] A) {
        int N=A.length;
        List<Map<Integer,Integer>> list=new ArrayList<>(N);
        for (int i = 0; i < N; i++)
            list.add(new HashMap<>());
        int max=2;
        for (int i = 1; i < N; i++) {
            for (int j = i-1; j >=0 ; j--) {
                int dis=A[i]-A[j];
                int num=Math.max(list.get(i).getOrDefault(dis, 0), list.get(j).getOrDefault(dis, 1)+1);
                max=Math.max(max, num);
                list.get(i).put(dis, num);
            }
        }
        return max;
    }


    //O(N^2)
    public int longestArithSeqLength(int[] A) {
        int maxlen=10000;
        int[][] res=new int[A.length][maxlen*2+1];
        int max=2;
        for (int i = 1; i < A.length; i++) {
            for (int j = i-1; j >= 0; j--) {
                int temp=A[i]-A[j]+maxlen;
                if(res[j][temp]==0) res[j][temp]=1;
                res[i][temp]=Math.max(res[i][temp], res[j][temp]+1);
                max=Math.max(max, res[i][temp]);
            }
        }
        return max;
    }
}
