package leetcode.realtest.realTest20201115_WC215;

import java.util.HashMap;
import java.util.Map;

public class MaximizeGridHappiness {
    public static void main(String[] args) {
        MaximizeGridHappiness grid=new MaximizeGridHappiness();
        int m = 2, n = 3, introvertsCount = 1, extrovertsCount = 2;
//        m = 3; n = 1; introvertsCount = 2; extrovertsCount = 1;
        m = 2; n = 2; introvertsCount = 4; extrovertsCount = 0;
        m = 1; n = 2; introvertsCount = 1; extrovertsCount = 1;
//        m=5; n=5; introvertsCount=6; extrovertsCount=6;
//        m=3; n=2; introvertsCount=1; extrovertsCount=2;
        m=2; n=3; introvertsCount=1; extrovertsCount=2;
        System.out.println(grid.getMaxGridHappiness(m,n,introvertsCount,extrovertsCount));
        System.out.println(grid.map.toString());
    }
    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        String state="";
        for(int i=0;i<n;i++) state = state+'0';
        return dfs(m,n,introvertsCount,extrovertsCount, 0, 0, state);
    }

    Map<String, Integer> map = new HashMap<>();
    int dfs(int m, int n, int iv, int ov, int i, int j, String state){
        if(i>=m) return 0;
        if(iv==0 && ov==0) return 0;
        StringBuilder sb = new StringBuilder(state).append(",").append(i)
                .append(",").append(j).append(",").append(iv).append(",").append(ov);
        String key =sb.toString();
        if(map.containsKey(key)) return map.get(key);
        //内向 1
        int max=0, next=0;
        if(iv>0) {
            int cur = 120;
            if(j>0) {
                cur += state.charAt(n-1) == '1' ? -30 * 2 : (state.charAt(n-1) == '2' ? -10 : 0);
            }
            cur += state.charAt(0) == '1' ? -30 * 2 : (state.charAt(0) == '2' ? -10 : 0);
            if(j<n-1) {
                next = dfs(m, n, iv - 1, ov, i , j+1, state.substring(1)+"1");
            }else
                next = dfs(m, n, iv - 1, ov, i + 1, 0, state.substring(1) + "1");
            max=Math.max(max, next+cur);

        }
        //不放
        if(j<n-1) {
            next = dfs(m, n, iv, ov, i , j+1, state.substring(1)+"0");
        }else{
            next = dfs(m, n, iv, ov, i+1 , 0, state.substring(1)+"0");
        }
        max = Math.max(max, next);
        //外向
        if(ov>0){
            int cur=40;
            if(j>0) {
                cur += state.charAt(n-1) == '1' ? -10 : (state.charAt(n-1) == '2' ? 20*2 : 0);
            }
            cur += state.charAt(0) == '1' ? -10 : (state.charAt(0) == '2' ? 20*2 : 0);
            if(j<n-1)
                next = dfs(m, n, iv, ov-1, i , j+1, state.substring(1)+"2");
            else
                next = dfs(m, n, iv, ov-1, i+1 , 0, state.substring(1)+"2");
            max = Math.max(max, next+cur);
        }
        map.put(key, max);
        return max;
    }
}
