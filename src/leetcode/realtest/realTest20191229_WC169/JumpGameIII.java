package leetcode.realtest.realTest20191229_WC169;

import utils.ArrayGenerator;
import utils.PrintUtils;

import java.util.LinkedList;
import java.util.Queue;

public class JumpGameIII {
    public static void main(String[] args) {
        JumpGameIII gameIII=new JumpGameIII();
        int[] arr = {4,2,3,0,3,1,2};int start = 5;
//        arr = new int[]{4,2,3,0,3,1,2}; start = 0;
//        arr = new int[]{3,0,2,1,2}; start = 2;
//        arr = ArrayGenerator.intArray(10000,0,10000); start=5;
        System.out.println(gameIII.canReach1(arr, start));
        System.out.println(gameIII.canReach(arr, start));
    }
    //BFS
    public boolean canReach1(int[] arr, int start) {
        int n=arr.length;
        boolean[] visited=new boolean[n];
        Queue<Integer> queue=new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()){
            int cur=queue.poll();
            if(cur<0 || cur>=n || visited[cur]) continue;
            if(arr[cur]==0) return true;
            visited[cur]=true;
            queue.add(cur+arr[cur]);
            queue.add(cur-arr[cur]);
        }
        return false;
    }
    public boolean canReach(int[] arr, int start) {
        int n=arr.length;
        int[] cache=new int[n];
        boolean res=dptd(arr, start, cache, new boolean[n]);
//        PrintUtils.printArray(cache);
        return res;
    }

    boolean dptd(int[] arr, int pos, int[] cache, boolean[] visited){
        if(pos<0 || pos>=arr.length) return false;
        if(arr[pos]==0) return true;
        if(visited[pos]){
             if(cache[pos]!=0) return cache[pos]==1;
             else return false;
        }
        visited[pos]=true;
        boolean left=dptd(arr, pos+arr[pos], cache, visited);
        boolean right=dptd(arr, pos-arr[pos], cache, visited);
        visited[pos]=false;
        boolean res=left||right;
        cache[pos]= res ? 1: -1;
        return res;
    }
}
