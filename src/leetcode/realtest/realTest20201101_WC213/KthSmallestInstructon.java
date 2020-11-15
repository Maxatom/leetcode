package leetcode.realtest.realTest20201101_WC213;

import java.util.Arrays;

public class KthSmallestInstructon {
    public static void main(String[] args) {
        KthSmallestInstructon kth = new KthSmallestInstructon();
        int[] destination = {2,3}; int k = 1;
        destination = new int[]{2,3}; k = 2;
        destination = new int[]{2,3}; k = 3;
        destination = new int[]{1,1}; k = 2;
        destination = new int[]{15,15}; k= 155117520;
        System.out.println(kth.kthSmallestPath(destination, k));
    }
    public String kthSmallestPath(int[] d, int k) {
        dp=new int[d[0]+1][d[1]+1];
        dfs(d, k, new int[]{0,0}, new StringBuilder());
        return res;
    }
    int cnt=0;
    String res=null;
    int[][] dp;
    int dfs(int[] d, int k, int[] c,StringBuilder sb){
        if(c[0]<0  || c[0]>d[0] || c[1]<0 || c[1]>d[1]){
            return 0;
        }
        if(c[0]==d[0] && c[1]==d[1]){
            if(cnt==k-1) {
                res=sb.toString();
            }else{
                cnt++;
            }
            return 1;
        }

        if(dp[c[0]][c[1]]>0 && dp[c[0]][c[1]]+cnt<k){
            cnt+=dp[c[0]][c[1]];
            return dp[c[0]][c[1]];
        }
        int pathc=0;
        pathc += dfs(d, k, new int[]{c[0], c[1]+1}, sb.append('H'));
        sb.deleteCharAt(sb.length()-1);
        if(res==null){
            pathc+=dfs(d, k, new int[]{c[0]+1, c[1]}, sb.append('V'));
            sb.deleteCharAt(sb.length()-1);
        }
        return dp[c[0]][c[1]]=pathc;
    }
}
