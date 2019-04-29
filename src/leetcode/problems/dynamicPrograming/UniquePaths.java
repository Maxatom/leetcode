package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

/**
 * @author shibing
 * @since 2018/12/16 8:52
 */
public class UniquePaths {
    public static void main(String[] args) throws Exception {
        UniquePaths paths=new UniquePaths();
//        int m=7,n=3;
        int m=51,n=5;
//        int m=30,n=30;

        long start=System.currentTimeMillis();
//        System.out.println(paths.uniquePaths(m,n)+", time="+(System.currentTimeMillis()-start));

       start=System.currentTimeMillis();
        System.out.println(paths.uniquePaths1(m,n)+", time="+(System.currentTimeMillis()-start));

        start=System.currentTimeMillis();
        System.out.println(paths.uniquePaths2(m,n)+", time="+(System.currentTimeMillis()-start));
    }


    //math solution,  permutation problem，combinatorial probelm
    //C(m+n, m)=  (m+n)!/(m!*n!)
    //C(m+n,m)=C(m+n-1,m-1)+C(m+n-1,m)  => C(x,y)=C(x-1,y-1)+C(x-1,y)
    //Formula 3: C(m+n,m)= (n+1)/m * C(m+n, m-1) ;  C(x,y)=(x-y+1)/y*C(x,y-1)
    //implements formula 3
    public int uniquePaths2(int m, int n) throws Exception {
        if(m==1||n==1) return 1;
        if(m>n) return uniquePaths2(n,m);
        m--;n--;
        int result=1;
        for (int i = 1; i <= m; i++) {
            if(result*(n+i)<result) throw new Exception("overflow");
            result*=(n+i);
            result/=i;
        }
        return result;
    }

    //optimize space
    public int uniquePaths1(int m, int n) {
        if(m>n) return uniquePaths1(n,m);
        int[] path = new int[m+1];
        path[1]=1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                path[j]+=path[j-1];
            }
            PrintUtils.printArray(path);
        }
        return path[m];
    }

        //DP O(m*n) space O(m*n)
    //P(m,n)=P(m-1, n)+P(m,n-1) , P(1,1)=1
    public int uniquePaths(int m, int n) {
        int[][] path=new int[m+1][n+1];
        for (int i = 1; i <= n; i++) {
            path[1][i]=1;
        }
        for (int i = 1; i <= m; i++) {
            path[i][1]=1;
        }
        for (int i = 2; i <= m; i++) {
            for (int j = 2; j <= n; j++) {
                path[i][j]=path[i-1][j]+path[i][j-1];
            }
        }
//        PrintUtils.print2DIntArray(path);
        return path[m][n];
    }
}
