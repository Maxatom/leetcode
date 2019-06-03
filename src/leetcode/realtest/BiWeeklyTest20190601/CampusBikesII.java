package leetcode.realtest.BiWeeklyTest20190601;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shibing
 * @since 2019/6/1 23:18
 */
public class CampusBikesII {
    public static void main(String[] args) {
        CampusBikesII bikesII=new CampusBikesII();
        int[][] workers = {{0,0},{1,1},{2,0}}, bikes = {{1,0},{2,2},{2,1}};
        workers = new int[][]{{0,0},{2,1}}; bikes = new int[][]{{1,2},{3,3}} ;
        System.out.println(bikesII.assignBikes(workers, bikes));
    }
    public int assignBikes(int[][] workers, int[][] bikes) {
        return recursion(workers, 0, bikes, 0);
    }
    Map<Integer, Integer> cache=new HashMap<>();
    public int recursion(int[][] workers, int idx, int[][] bikes, int visited){
        if(idx==workers.length) return 0;
        int key=visited*100+idx;
        if(cache.containsKey(key)) return cache.get(key);
        int min=Integer.MAX_VALUE;
        for (int i = 0; i < bikes.length; i++) {
            if(((1<<i) & visited) !=0) continue;
            int res=recursion(workers, idx+1, bikes, (1<<i) | visited);
            min=Math.min(min, res+manhatan(workers[idx], bikes[i]));
        }
        cache.put(key, min);
        return min;
    }
    public int manhatan(int[] p1, int[] p2){
        return Math.abs(p1[0]-p2[0])+Math.abs(p1[1]-p2[1]);
    }
}
