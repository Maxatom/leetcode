package leetcode.realtest.realTest20190303;


/**
 * @author shibing
 * @since 2019/3/3 14:44
 */
public class MinimumCosttoMergeStones {
    public static void main(String[] args) {
        MinimumCosttoMergeStones mstones=new MinimumCosttoMergeStones();
        int[] stones=new int[]{3,2,4,1}; int K=3; //-1
//         stones=new int[]{3,2,4,1}; K=2; //20
//        stones=new int[]{3,5,1,2,6}; K=3; //25
        stones=new int[]{3}; K=2; //0
        stones=new int[]{3,5}; K=2; //8
        stones=new int[]{4,6,4,7,5}; K= 2; //62
        stones=new int[]{7,7,8,6,5,6,6}; K= 3; //83
        stones=new int[]{36,2,61,30,74,35,65,31,43,92,15,11,22}; K= 5; //902

        System.out.println(mstones.mergeStones1(stones,K));
        System.out.println(mstones.mergeStones2(stones,K));
    }


    //2D Array version
    //dp[i][j][m]=dp[i][t][1]+dp[t+1][j][m-1] (i<=t<j)
    //dp[i][i][1]=0;
//    public int mergeStones3(int[] stones, int K) {
//        int N=stones.length;
//        if(N==1) return 0;
//        if((N-1)%(K-1)!=0) return -1;
//        int[] prefix=new int[N+1];
//        for (int i = 0; i < N; i++) prefix[i+1]=prefix[i]+stones[i];
//        int[][] dp=new int[N][N];
//
//        for (int m = K; m <= N; m++) {
//            for (int i = 0; i+m-1 < N; i++) {
//                int j=i+m-1;
//                    dpnew[i][j] = Integer.MAX_VALUE;
//                    for (int t = i; t < j; t+=K-1) {
//                            dpnew[i][j] = Math.min( dp1[i][t] + dp[t + 1][j], dpnew[i][j]);
//                    }
//                dp1[i][j]=dpnew[i][j]==Integer.MAX_VALUE?Integer.MAX_VALUE:(dpnew[i][j]+prefix[j+1]-prefix[i]);
//            }
//            dp=dpnew;
//        }
//        return dp[0][N-1];
//    }


    //bottom-up dp
//    dp[i][j][m] means the cost of merge stone[i]~stone[j] into m piles
//    dp[i][i][1] =0(already a pile)
//     dp[i][j][m]=min(dp[i][t][m-1]+dp[t+1][j][1]+sum[i][j]) ( i<=t<j )
//    time complexity O(N^3)
    public int mergeStones1(int[] stones, int K) {
        int N=stones.length;
        if(N==1) return 0;
        if((stones.length-1)%(K-1)!=0) return -1;
        int[] prefix=new int[N+1];
        for (int i = 0; i < N; i++) prefix[i+1]=prefix[i]+stones[i];
        int[][][] dp=new int[N][N][K+1];
        //initialize
        for (int i = 0; i < N; i++) {
            for (int j = i; j < i+K && j<N; j++) {
                for (int k = 0; k <= K; k++) {
                    if(j-i+1!=k) dp[i][j][k]=Integer.MAX_VALUE;
                    if(j-i+1==K&&k==1) dp[i][j][k]=prefix[j+1]-prefix[i];
                }
            }
        }
        for (int d = K; d < N; d++) {
            for (int i = 0; i < N-d; i++) {
                int j=i+d;
                for (int k = 2; k <= K; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                    for (int t = i; t < j; t++) {
                        if (dp[i][t][k - 1] != Integer.MAX_VALUE  && dp[t + 1][j][1] != Integer.MAX_VALUE)
                            dp[i][j][k] = Math.min( dp[i][t][k - 1] + dp[t + 1][j][1], dp[i][j][k]);
                    }
                }
                dp[i][j][1]=dp[i][j][K]==Integer.MAX_VALUE?Integer.MAX_VALUE:(dp[i][j][K]+prefix[j+1]-prefix[i]);
            }
        }
        return dp[0][N-1][1];
    }


    //top-down with memory
    public int mergeStones2(int[] stones, int K){
        int N=stones.length;
        if(N==1) return 0;
        if((N-1)%(K-1)!=0 || N<K) return -1;
        int[] prefix=new int[N+1];
        Integer[][][] memo=new Integer[N][N][K+1];
        for (int i = 0; i < N; i++) prefix[i+1]=prefix[i]+stones[i];
        return dp(0, N-1,1, K, prefix, memo);
    }
    public int dp(int i, int j, int k, int K, int[] prefix, Integer[][][] memo){
        if(k<1) return -1;
        int num=j-i+1;
        if(num==K && k==1) return prefix[j+1]-prefix[i];
        if(num<=K) return num==k?0:-1;

        if(memo[i][j][k]!=null) return memo[i][j][k];
        if(k==1) return dp(i, j, K, K, prefix, memo);
        int min=Integer.MAX_VALUE;
        for (int l = i; l < j; l++) {
            int left=dp(i, l, k-1, K, prefix, memo);
            if(left==-1) continue;
            int right=dp(l+1, j, 1, K, prefix , memo);
            if(right==-1) continue;
            min=Math.min(min, left+right);
        }
        if(min==Integer.MAX_VALUE) return memo[i][j][k]=-1;
        else if(k==K) min+=prefix[j+1]-prefix[i];
        return memo[i][j][k]=min;
    }

    //dp[i][j] = sum[i][j] + min(dp[i][k] +dp[k+1][j]) ( i<=k<j)
    //dp[i][i] = 0 (already a pile)
    public int mergeStonesK2(int[] stones){
        int n=stones.length;
        int[] prefix=new int[n+1];
        for (int i = 0; i < n; i++)
            prefix[i+1]=prefix[i]+stones[i];
        int[][] dp=new int[n][n];
        for (int m = 2; m <=n ; m++) {
            for (int i = 0; i <= n-m; i++) {
                int j=i+m-1;
                dp[i][j]=Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    dp[i][j]=prefix[j]-prefix[i-1]+Math.min(dp[i][j], dp[i][k]+dp[k+1][j]);
                }
            }
        }
        return dp[0][n-1];
    }

    //backtrace TLE
    public int mergeStones(int[] stones, int K) {
        if(stones.length==1) return 0;
        if(stones.length<K) return -1;
        int min=Integer.MAX_VALUE;
        boolean flag=false;
        for (int i = 0; i <= stones.length-K; i++) {
            int sum=0;
            int[] next=new int[stones.length-K+1];
            System.arraycopy(stones,0,next,0,i);
            for (int j = i; j < i+K; j++) {
                sum+=stones[j];
            }
            next[i]=sum;
            System.arraycopy(stones,i+K,next,i+1,next.length-i-1);
            int res=mergeStones(next, K);
            if(res!=-1){
                flag=true;
                min=Math.min(min, res+sum);
            }
        }
        return flag?min:-1;
    }
}
