package leetcode.realtest.realTest20201011_WC210;

import utils.PrintUtils;

import java.util.*;

public class CountSubTrees {
    public static void main(String[] args) {
        CountSubTrees subTrees = new CountSubTrees();
        int n = 4; int[][] edges = {{1,2},{2,3},{2,4}};
        n = 2; edges = new int[][]{{1,2}};
        n = 3; edges = new int[][]{{1,2},{2,3}};
        System.out.println(Arrays.toString(subTrees.countSubgraphsForEachDiameter(n, edges)));

    }
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        int[][] dis = new int[n+1][n+1];
        for(int i=1; i<=n; i++) Arrays.fill(dis[i],-1);
        for(int i=0; i<edges.length; i++){
            int[] e = edges[i];
            dis[e[0]][e[1]]=1;
            dis[e[1]][e[0]]=1;
        }
        for(int i=1; i<=n; i++){
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{i,0});
            boolean[] seen = new boolean[n+1];
            while(!queue.isEmpty()){
                int[] p = queue.poll();
                seen[p[0]]=true;
                dis[p[0]][i]=dis[i][p[0]]=p[1];
                for(int j=1; j<=n; j++){
                    if(dis[p[0]][j]==1 && !seen[j]) {
                        queue.add(new int[]{j, p[1] + 1});
                    }
                }
            }
        }
        int state = 1<<n;
        int[] res = new int[n-1];
        for(int i=3; i<state; i++){
            int d = check(i, dis);
            if(d<1) continue;
            res[d-1]++;
        }
        return res;
    }
    int check(int state, int[][] dis){
        List<Integer> list = new ArrayList<>();
        int i=0;
        while (i<dis.length-1){
            if((state&(1<<i))>0)
                list.add(i+1); i++;
        }
        Queue<Integer> queue=new LinkedList<>();
        queue.add(list.get(0));
        int cnt=0;
        Set<Integer> set =new HashSet<>();
        while (!queue.isEmpty()){
            int p = queue.poll();
            if(set.contains(p)) continue;
            set.add(p);
            cnt++;
            for(i=0; i<list.size(); i++){
                if(dis[list.get(i)][p]==1){
                    queue.add(list.get(i));
                }
            }
        }
        if(cnt!=list.size()) return -1;
        int max=0;
        for(i=0; i<list.size(); i++){
            int x=list.get(i);
            for (int j = i+1; j < list.size(); j++) {
                int y=list.get(j);
                if(dis[x][y]==-1) return -1;
                max =Math.max(max, dis[x][y]);
            }
        }
        return max;
    }
//
//    dfs(int n, int i, List<Integer>){
//
//    }
}
