package leetcode.realtest.realTest20200308_WC179;

import utils.PrintUtils;

import java.util.*;

/**
 * @author ShiBing
 * @projectName leetcode
 * @description: TODO
 * @date 2020/3/811:49
 */
public class FrogPosition {
    public static void main(String[] args) {
        FrogPosition frog=new FrogPosition();
        int n = 7; int[][] edges = new int[][]{{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}};  int t = 2, target = 4;
//        n = 7; edges = new int[][]{{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}}; t = 20; target = 6;
//        n=3; edges= new int[][]{{2,1},{3,2}}; t= 1; target=2;
//        n=10; edges=new int[][]{{2,1},{3,2},{4,2},{5,2},{6,5},{7,1},{8,3},{9,1},{10,1}}; t= 1; target= 9;
        System.out.println(frog.frogPosition(n, edges, t, target));

    }
    public double frogPosition(int n, int[][] edges, int t, int target) {

        int[] par=new int[n+1], cnt=new int[n+1];
        Set<Integer> set=new HashSet<>();
        set.add(1);
        while (!set.isEmpty()) {
            Set<Integer> nset=new HashSet<>();
            for (int i = 0; i < edges.length; i++) {
                if(edges[i]==null) continue;
                if(set.contains(edges[i][0])) {
                    par[edges[i][1]]=edges[i][0];
                    cnt[edges[i][0]]++;
                    nset.add(edges[i][1]);
                    edges[i]=null;
                } else if(set.contains(edges[i][1])) {
                    par[edges[i][0]]=edges[i][1];
                    cnt[edges[i][1]]++;
                    nset.add(edges[i][0]);
                    edges[i]=null;
                }
            }
            set=nset;
        }
        int cur=target, sec=0;
        double res=1f;
        while (cur!=1){
            res *= 1/(double)cnt[par[cur]];
            sec++;
            cur=par[cur];
        }
        if(t<sec || t>sec && cnt[target]!=0) return 0;
        else return res;
    }

}
