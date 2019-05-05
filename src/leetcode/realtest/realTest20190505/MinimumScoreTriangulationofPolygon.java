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
//        A=new int[]{3,4,5,2,2,4};
//        A=new int[]{38,76,69,32,24,35,82,30,86,77,92,3,35,20,84,67,23,58,94,10};
        System.out.println(polygon.minScoreTriangulation(A));
        System.out.println(polygon.minScoreTriangulation1(A));
        System.out.println(polygon.cache.toString());
    }


    Map<Integer, Integer> cache=new HashMap<>();
    public int minScoreTriangulation1(int[] A) {
        if(A.length==3) return A[0]*A[1]*A[2];
        return recursive(A,0,A.length-1);
    }

    int recursive(int[] A, int L, int R){
        int N=A.length+1;
        if((R-L+1+A.length)%A.length!=0 && (R-L+1+A.length)%A.length<3) return 0;
        if(L!=R && (R-L+1+A.length)%A.length==3) return A[L]*A[(L+1)%A.length]*A[R];
        if(cache.containsKey(L*100+R)) return cache.get(L*100+R);
        int min=Integer.MAX_VALUE;
        for (int i = L; i <= (R-2+A.length)%A.length; i=(i+1)%(A.length+1)) {
            for (int j = i+1; j <=(R-1+A.length)%A.length ; j=(j+1)%(A.length+1)) {
                for (int k = j+1; k <= R; k=(k+1)%(A.length+1)) {
                    int temp=A[i]*A[j]*A[k]+recursive(A, i,j)+recursive(A, j,k)+recursive(A,k ,i);
                    min=Math.min(min, temp);
                }
            }
        }
        cache.put(L*100+R, min);
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
