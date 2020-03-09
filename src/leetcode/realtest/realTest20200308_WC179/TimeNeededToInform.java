package leetcode.realtest.realTest20200308_WC179;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShiBing
 * @projectName leetcode
 * @description: TODO
 * @date 2020/3/811:15
 */
public class TimeNeededToInform {
    public static void main(String[] args) {
        TimeNeededToInform inform=new TimeNeededToInform();
        int n = 6, headID = 2; int[] manager = {2,2,-1,2,2,2}, informTime = {0,0,1,0,0,0};
        n = 7; headID = 6; manager = new int[]{1,2,3,4,5,6,-1}; informTime = new int[]{0,6,5,4,3,2,1};
        System.out.println(inform.numOfMinutes(n, headID, manager, informTime));
    }
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> map=new HashMap<>();
        for(int i=0; i<n; i++){
            if(map.get(manager[i])==null)
            map.put(manager[i], new ArrayList<>());
            map.get(manager[i]).add(i);
        }
        return dfs(headID, map, manager, informTime);
    }
    int dfs(int cur,Map<Integer, List<Integer>> map,int[] manager ,int[] informTime){
        if(map.get(cur)==null || map.get(cur).isEmpty()) return informTime[manager[cur]];
        int max=0;
        for(Integer em:map.get(cur)){
            max = Math.max(max, dfs(em, map, manager, informTime));
        }
        if(manager[cur]!=-1) max += informTime[manager[cur]];
        return max;
    }
}
