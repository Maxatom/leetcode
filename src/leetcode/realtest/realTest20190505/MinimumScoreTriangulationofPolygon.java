package leetcode.realtest.realTest20190505;

import utils.PrintUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shibing
 * @since 2019-05-05 10:58:19
 **/
public class MinimumScoreTriangulationofPolygon {
    public static void main(String[] args) {
        MinimumScoreTriangulationofPolygon polygon=new MinimumScoreTriangulationofPolygon();
        int[] A={1,2,3};
        A=new int[]{3,7,4,5};
        A=new int[]{1,3,1,4,1,5};
        A=new int[]{3,4,5,2,2,4};
        A=new int[]{38,76,69,32,24,35,82,30,86,77,92,3,35,20,84,67,23,58,94,10};
//        System.out.println(polygon.minScoreTriangulation(A));
        System.out.println(polygon.minScoreTriangulation1(A));
        System.out.println(polygon.minScoreTriangulation2(A));
    }

    //another dp
    public int minScoreTriangulation2(int[] A) {
        int n=A.length;
        int[][] dp=new int[n][n];
        for(int d=2;d<n; d++){
            for (int i = 0; i +d < n; i++) {
                int j=i+d;
                dp[i][j]=Integer.MAX_VALUE;
                for (int k = i+1; k < j; k++) {
                    dp[i][j]=Math.min(dp[i][j], dp[i][k]+dp[k][j]+A[i]*A[k]*A[j]);
                }
            }
        }
//        PrintUtils.print2DIntArray(dp);
        return dp[0][n-1];
    }


    //dp
    public int minScoreTriangulation1(int[] A) {
        int min=Integer.MAX_VALUE;
        int[][] dp=new int[A.length][A.length];
        for(int d=3; d<=A.length; d++) {
            for (int i = 0; i < A.length; i++) {
                int ji=i+d-1, j=ji%A.length;
                dp[i][j]=Integer.MAX_VALUE;
                for (int k = i+1; k < ji ; k++) {
                    int ki=k%A.length;
                    dp[i][j] = Math.min(dp[i][j], A[i] * A[ki] * A[j] + dp[i][ki] + dp[ki][j]);
                }
            }
        }
//        PrintUtils.print2DIntArray(dp);
        for (int i = 0; i < dp.length-1; i++)
            for (int j = i+1; j < dp.length; j++)
                min=Math.min(dp[i][j]+dp[j][i], min);
        return min;
    }


    //TLE BackTrace
    public int minScoreTriangulation(int[] A) {
        long visited=0l;
        for (int i = 0; i < A.length; i++) {
            visited=(visited<<1) | 1;
        }
        return recursive(A, visited);
    }
    Map<Long, Integer> map=new HashMap<>();
    public int recursive(int[] A, long visited) {
        if(visited==0l) return 0;
        if(map.containsKey(visited)) return map.get(visited);
        int min=Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            if(A[i]==0) continue;
            int j=i-1, left, right;
            while (A[(j+A.length)%A.length]==0) j--;
            j=(j+A.length)%A.length;
            left=A[j];
            int k=i+1;
            while (A[k%A.length]==0) k++;
            k=k%A.length;
            right=A[k];
            if(j==k) return 0;
            int temp=left*A[i]*right;
            int v=A[i];
            A[i]=0;
           int res=recursive(A, visited & ~(1<<(long)(A.length-i-1)));
           A[i]=v;
           min=Math.min(min, res+temp);
        }
        map.put(visited, min);
        return min;
    }
}
