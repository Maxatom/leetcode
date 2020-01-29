package leetcode.realtest.realTest20200112_WC171;

import java.util.*;

public class NumberofOperations {
    public static void main(String[] args) {
        NumberofOperations opers=new NumberofOperations();
        int n=11; int[][] connections= {{1,4},{0,3},{1,3},{3,7},{2,7},{0,1},{2,4},{3,6},{5,6},{6,7},{4,7},{0,7},{5,7}};
        System.out.println(opers.makeConnected(n,connections));
    }
    public int makeConnected(int n, int[][] connections) {
        if(connections.length<n-1) return -1;
        List<Integer>[] graph=new List[n];
        for(int[] cn:connections){
            if(graph[cn[0]]==null) graph[cn[0]]=new ArrayList<>();
            graph[cn[0]].add(cn[1]);
            if(graph[cn[1]]==null) graph[cn[1]]=new ArrayList<>();
            graph[cn[1]].add(cn[0]);
        }
        Set<Integer> set=new HashSet<>();
        for(int i=0;i<n;i++) set.add(i);
        Queue<Integer> queue=new LinkedList<>();
        int count=0;
        while(!set.isEmpty()){
            System.out.println(set.toString());
            for(Integer i:set){
                queue.add(i);
                set.remove(i);
                break;
            }
            count++;
            while(!queue.isEmpty()){
                int cur=queue.poll();
                if(graph[cur]==null) continue;
                for(int nd:graph[cur]){
                    if(set.contains(nd)) {
                        set.remove(nd);
                        queue.add(nd);
                    }
                }
            }
        }
        return count-1;
    }
}
