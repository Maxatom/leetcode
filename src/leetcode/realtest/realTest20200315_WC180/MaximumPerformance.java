package leetcode.realtest.realTest20200315_WC180;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ShiBing
 * @projectName leetcode
 * @date 2020/3/1511:36
 */
public class MaximumPerformance {
    public static void main(String[] args) {
        int n = 6, k = 5; int[] speed = {2,10,3,1,5,8}, efficiency = {5,4,3,9,7,2};
        MaximumPerformance pref=new MaximumPerformance();
        System.out.println(pref.maxPerformance(n, speed, efficiency, k));
        System.out.println(pref.maxPerformance1(n, speed, efficiency, k));
    }

    //Error
    public int maxPerformance1(int n, int[] speed, int[] efficiency, int k) {
        int M=1000000007;
        int[][] res=new int[n][2];
        for (int i = 0; i < n; i++) {
            res[i][0]=speed[i];
            res[i][1]=efficiency[i];
        }
        Arrays.sort(res, (a,b)->b[1]-a[1]);
        PriorityQueue<int[]> queue=new PriorityQueue<>(k,Comparator.comparingInt(a -> a[0]));
        queue.add(res[0]);
        long result=res[0][0]*res[0][1], fac=res[0][0], fac1=res[0][1];
        for (int i = 1; i < n; i++) {
            if(queue.size()<k){
                if((fac+res[i][0])*res[i][1]>result){
                    queue.add(res[i]);
                    result=(fac+res[i][0])*res[i][1];
                    fac = fac + res[i][0];
                }
            }else {
                int[] n0=queue.peek();
                if( res[i][0]>n0[0] && (fac-n0[0]+res[i][0])*res[i][1] > result){
                    queue.poll();
                    queue.add(res[i]);
                    result=(fac-n0[0]+res[i][0])*res[i][1];
                    fac=fac - n0[0]+res[i][0];
                }
            }
        }
        return (int)(result%M);
    }

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        return dfs(speed,efficiency,k,0,new int[]{0,Integer.MAX_VALUE});
    }

    int dfs(int[] speed, int[] ef, int k, int c, int[] rec){
        if(k==0 || c>=speed.length) return rec[0]*rec[1];
        int useCur=dfs(speed,ef,k-1, c+1, new int[]{rec[0]+speed[c], Math.min(rec[1],ef[c])});
        int unUseCur=dfs(speed,ef,k, c+1, rec);
        return Math.max(useCur,unUseCur);
    }
}
