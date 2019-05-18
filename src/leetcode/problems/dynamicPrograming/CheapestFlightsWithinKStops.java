package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

import java.util.*;

/**
 * @author Shibing
 * @since 2019-05-15 18:51:42
 **/
public class CheapestFlightsWithinKStops {
    public static void main(String[] args) {
        CheapestFlightsWithinKStops stops=new CheapestFlightsWithinKStops();
        int[][] edges = {{0,1,100},{1,2,100},{0,2,500}}; int n = 3, src = 0, dst = 2, k = 1;
//         edges = new int[][]{{0,1,100},{1,2,100},{0,2,500}}; n = 3; src = 0; dst = 2; k = 0;
        edges=new int[][]{{0,1,2},{1,2,1},{2,0,10}}; n= 3; src= 1; dst= 2; k= 1;
        System.out.println(stops.findCheapestPrice(n, edges, src, dst, k));
        System.out.println(stops.findCheapestPrice1(n, edges, src, dst, k));
    }

    //Dijkstra
    public int findCheapestPrice1(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> edges=new HashMap<>();
        for (int[] e:flights){
            if(!edges.containsKey(e[0])) edges.put(e[0], new ArrayList<>());
            edges.get(e[0]).add(new int[]{e[1],e[2]});
        }
        boolean[] visited=new boolean[n];
        Queue<int[]> queue=new PriorityQueue<>(Comparator.comparingInt(p->p[1]));
        //a[0]:city, a[1]:distance, a[2]: stops
        queue.add(new int[]{src,0,-1});
        int[] city;
        while ((city=queue.poll())!=null){
            if(city[0]==dst) return city[1];
            visited[city[0]]=true;
            if(city[2]<K) {
                for (int[] c : edges.getOrDefault(city[0], new ArrayList<>())) {
                    if (!visited[c[0]])
                        queue.add(new int[]{c[0], city[1] + c[1], city[2] + 1});
                }
            }
        }
        return -1;
    }

    //Bellman-Ford
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] dist=new int[n];
        int INF=(int)1e9;
        Arrays.fill(dist, INF);
        dist[src]=0;
        for (int i = 0; i <= K; i++) {
            int[] distCur=Arrays.copyOf(dist, n);
            for (int[] e : flights)
                    distCur[e[1]]=Math.min(distCur[e[1]], dist[e[0]]+e[2]);
            dist=distCur;
//            PrintUtils.printArray(dist);
        }
        return dist[dst]>=INF?-1:dist[dst];
    }
}
