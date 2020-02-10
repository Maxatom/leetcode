package leetcode.problems.dynamicPrograming;


public class MinimumFalling {
    public static void main(String[] args) {
        MinimumFalling mfall=new MinimumFalling();
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(mfall.minFallingPathSum(arr));
    }
    public int minFallingPathSum(int[][] arr) {
        int n=arr.length, minidx=0;
        for (int i = 0; i < n; i++) {
            if(arr[0][minidx] > arr[0][i])
                minidx=i;
        }
        if(n==1) return arr[0][0];
        int secdidx= minidx==0?1:0;
        for (int i = 0; i < n; i++) {
            if(arr[0][secdidx] > arr[0][i] && i!= minidx)
                secdidx=i;
        }
        if(n==1) return arr[0][n-1];
        for (int i = 1; i < n; i++) {
            int nminidx=0;
            for (int j = 0; j < n; j++) {
                arr[i][j] += arr[i-1][j!=minidx ? minidx : secdidx ];
                if(arr[i][nminidx] > arr[i][j])
                    nminidx=j;
            }
            int nsecdidx= nminidx==0?1:0;
            for (int j = 0; j < n; j++) {
                if(arr[i][nsecdidx] > arr[i][j] && j!=nminidx)
                    nsecdidx=j;
            }
            minidx=nminidx;
            secdidx=nsecdidx;
        }
        return arr[n-1][minidx];
    }

}
