package leetcode.realtest.realTest20190526;

import java.util.Arrays;

/**
 * @author shibing
 * @since 2019/5/26 10:34
 */
public class HeightChecker {
    public static void main(String[] args) {
        HeightChecker checker=new HeightChecker();

    }
    public int heightChecker(int[] heights) {
        int[] orig=Arrays.copyOf(heights, heights.length);
        Arrays.sort(heights);
        int cnt=0;
        for (int i = 0; i < heights.length; i++) {
            cnt+=orig[i]!=heights[i]?1:0;
        }
        return cnt;
    }
}
