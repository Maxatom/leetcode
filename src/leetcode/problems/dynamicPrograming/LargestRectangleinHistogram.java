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

    //DC
//    public int largestRectangleArea1(int[] heights) {
//        int n=heights.length;
//        if(n==0) return 0;
//        divideAndConquer(heights, 0, n-1);
//        return 0;
//    }
//    //res[0] max
//    int[] divideAndConquer(int[] heights, int L, int R){
//        if(L==R) return new int[]{heights[L], heights[L], 1, heights[R], 1};
//        int mid=(L+R)/2;
//        int[] left=divideAndConquer(heights, L, mid);
//        int[] right=divideAndConquer(heights, mid+1, R);
//        int[] res=new int[5];
//        if(left[2]<mid-L+1)
//
//    }

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
