package leetcode.realtest.BiWeeklyTest20190601;

import utils.PrintUtils;
import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author shibing
 * @since 2019/6/1 22:54
 */
public class IndexPairsofaString {
    public static void main(String[] args) {
        IndexPairsofaString string=new IndexPairsofaString();
        String text= "thestoryofleetcodeandme";String[] words = {"story","fleet","leetcode"};
//        text = "ababa"; words = new String[]{"aba","ab"};
//        text = "a"; words = new String[]{"a","ab"};
        PrintUtils.print2DIntArray(string.indexPairs(text,words));
    }
    public int[][] indexPairs(String text, String[] words) {
        List<int[]> list=new ArrayList<>();
        for(String word:words) {
            List<Integer> occurs=Utils.allOccurence(text, word);
            for(Integer i : occurs)
                list.add(new int[]{i, i+word.length()-1});
        }
        int[][] res=new int[list.size()][2];
        int i=0;
        for(int[] arr:list)
            res[i++]=arr;
        Arrays.sort(res, (a, b)->a[0]-b[0]!=0? a[0]-b[0] : a[1]-b[1]);
        return res;
    }
}
