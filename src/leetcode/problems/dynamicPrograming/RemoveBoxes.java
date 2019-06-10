package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

import java.util.Arrays;

/**
 * @author Shibing
 * @since 2019-02-20 10:26:06
 **/
public class RemoveBoxes {
    public static void main(String[] args) {
        RemoveBoxes box=new RemoveBoxes();
        int[] boxes=new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1};
//        boxes=new int[]{1,1,1};
//        boxes=new int[]{1,3,1,3,1};
        System.out.println(box.removeBoxes(boxes));
        PrintUtils.printArray(boxes);
        System.out.println(box.removeBoxes1(boxes));
    }

    //dp(i, j, k) denotes the maximum points by removing the boxes of subarray boxes[i~j] with k boxes of the same color as boxes[i] attached to its left
    public int removeBoxes1(int[] boxes) {
        int n=boxes.length;
        int[][][] memo=new int[n][n][n];
        return recursion(boxes, 0, n-1, 0, memo);
    }
    public int recursion(int[] boxes, int left, int right, int k, int[][][] memo){
        if(left>right) return 0;
        if(memo[left][right][k]>0) return memo[left][right][k];
        while (left+1<=right && boxes[left+1]==boxes[left]) {
            left++; k++;
        }
        int max=(k+1)*(k+1)+recursion(boxes, left+1, right, 0, memo);
        for (int i = left+2; i <= right; i++) {
            if(boxes[i]==boxes[left])
                max=Math.max(max, recursion(boxes, left+1, i-1, 0, memo) + recursion(boxes, i, right, k+1, memo));
        }
        memo[left][right][k]=max;
        return max;
    }

    //TLE  backtrace
    public int removeBoxes(int[] boxes) {
        return recursive(boxes, 0, new boolean[boxes.length], boxes.length);
    }
    public int recursive(int[] boxes, int sum, boolean[] used, int n) {
        if(n==0) return sum;
        int max=0;
        for (int i = 0; i < boxes.length; i++) {
            if(used[i]) continue;
            boolean[] usedcur=Arrays.copyOf(used, used.length);
            int j=i, count=0;
            while (j<boxes.length){
                if(!usedcur[j] && boxes[j]!=boxes[i]) break;
                if(!usedcur[j]){
                    count++;
                    usedcur[j]=true;
                }
                j++;
            }
            max=Math.max(max,recursive(boxes, sum+count*count, usedcur, n-count));
            i=j-1;
        }
        return max;
    }
}
