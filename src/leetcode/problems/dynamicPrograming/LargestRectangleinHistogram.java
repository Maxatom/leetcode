package leetcode.problems.dynamicPrograming;

/**
 * @author Shibing
 * @since 2019-05-30 17:00:13
 **/
public class LargestRectangleinHistogram {
    public static void main(String[] args) {
        LargestRectangleinHistogram histogram=new LargestRectangleinHistogram();
        int[] heights=new int[]{2,1,5,6,2,3};
        heights=new int[]{6,4,3,2};
        heights=new int[]{2,3,4};
        System.out.println(histogram.largestRectangleArea(heights));
    }

    //,,
    public int largestRectangleArea1(int[] heights) {
        int n=heights.length;
        if(n==0) return 0;
        int[] left=new int[n], right=new int[n];
        int pre=0;
        for (int i = 1; i < n; i++) {
            left[i]= heights[i]<=heights[i-1]? heights[i-1]+1:1;
        }
        return 0;
    }

    //stack  O(N)
    public int largestRectangleArea(int[] heights) {
        int n=heights.length;
        if(n==0) return 0;
        int[] stack=new int[n];
        int max=0, top=-1;
        for (int i = 0; i <= n; i++) {
            while (top>=0 && (i==n || heights[i]<heights[stack[top]])){
                int h=heights[stack[top--]];
                max=Math.max(max, h*(top<0?i:i-stack[top]-1));
            }
            stack[++top]=i;
        }
        return max;
    }
}
