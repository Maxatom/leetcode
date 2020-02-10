package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumNumberofTaps {
    public static void main(String[] args) {
        MinimumNumberofTaps taps=new MinimumNumberofTaps();
        int n = 5; int[] ranges = {3,4,1,1,0,0};
//        n = 3; ranges = new int[]{0,0,0,0};
//        n = 7; ranges = new int[]{1,2,1,0,2,1,0,1};
//        n = 8; ranges = new int[]{4,0,0,0,0,0,0,0,4};
        n = 9; ranges = new int[]{4,0,0,0,0,0,0,0,0,4};
//        n = 8; ranges = new int[]{4,0,0,0,4,0,0,0,4};
//        n=10; ranges = new int[]{4,3,0,1,0,4,0,2,0,4,5};
        System.out.println(taps.minTaps1(n,ranges));
        System.out.println(taps.minTaps2(n,ranges));
    }

    //greedy 2 O(logn)
    public int minTaps2(int n, int[] ranges) {
        int[] rang=new int[n+1];
        for (int i = 0; i <= n; i++) {
            int indx=Math.max(0,i-ranges[i]);
            rang[indx] = Math.max(rang[indx], i+ranges[i]);
        }
        PrintUtils.printArray(rang);
        int cnt=0, start=0, max=rang[0];
        for (int i = 0; i <= n; i++) {
            if(max>=n) break;
            if(rang[i]==0) continue;
            if(i<=start){
                max=Math.max(max, rang[i]);
            }else {
                if(max<i) return -1;
                cnt++;
                start=max;
                max = rang[i];
            }
        }
        return max<n?-1:cnt+1;
    }
    //greedy
    public int minTaps1(int n, int[] ranges) {
        List<int[]> list=new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            if(ranges[i]==0) continue;
            list.add(new int[]{i-ranges[i],i+ranges[i]});
        }
        list.sort((p1,p2)->p1[0]==p2[0] ? p2[1]-p1[1] : p1[0]-p2[0]);
        if(list.isEmpty()) return -1;
        int cnt=0, start=0, max=list.get(0)[1];
        for (int[] rang:list) {
            if(rang[0]<=start){
                max=Math.max(max, rang[1]);
                if(max>=n) break;
            }else{
                if(rang[0]>max) return -1;
                cnt++;
                start=max;
                max=rang[1];
            }
        }
        return max<n?-1:cnt+1;
    }

    public int minTaps(int n, int[] ranges) {
       return dfs(ranges, 0, 0, 0);
    }
    int dfs(int[] ranges, int i, int num, int cur){
        int n=ranges.length-1;
        if(cur>=n) return num;
        if(i>n) return -1;
        int water=-1;
        if(i-ranges[i]<=cur && ranges[i]!=0) {
            water = dfs(ranges, i + 1, num + 1, Math.max(cur, i + ranges[i]));
        }
        int notwater=dfs(ranges, i+1, num, cur);
        if(water==-1 || notwater==-1)
            return Math.max(water,notwater);
        else return Math.min(water,notwater);
    }
}
