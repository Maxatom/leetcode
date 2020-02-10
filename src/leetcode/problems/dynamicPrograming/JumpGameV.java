package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

public class JumpGameV {
    public static void main(String[] args) {
        JumpGameV jumpGameV=new JumpGameV();
        int [] arr = {6,4,14,6,8,13,9,7,10,6,12};int d = 8;
//        arr =new int[]{83,11,83,70,75,45,96,11,80,75,67,83,6,51,71,64,64,42,70,23,11,24,95,65,1,54,31,50,18,16,11,86,2,48,37,34,65,67,4,17,33,70,16,73,57,96,30,26,56,1,16,74,82,77,82,62,32,90,94,33,58,23,23,65,70,12,85,27,38,100,93,49,96,96,77,37,69,71,62,34,4,14,25,37,70,3,67,88,20,30};
//        d=29;
//        arr = new int[]{7,6,4,4,9,2,3,3,4,6,32,5,6,4}; d=3;
        System.out.println(jumpGameV.maxJumps(arr, d));

    }
    public int maxJumps(int[] arr, int d) {
        cache = new int[arr.length];
        int max=0;
        for(int i=0; i<arr.length; i++){
            max=Math.max(max, dfs(arr, d, i));
        }
        PrintUtils.printArray(cache);
        return max;
    }
    int[] cache;
    int dfs(int[] arr, int d, int cur){
        if(cache[cur]>0) return cache[cur];
        for(int i=cur-1; i>=cur-d&&i>=0&&arr[i]<arr[cur]; i--){
            cache[cur]=Math.max(cache[cur], dfs(arr, d, i));
        }
        for(int i=cur+1; i<=cur+d&&i<arr.length&&arr[i]<arr[cur]; i++){
            cache[cur]=Math.max(cache[cur], dfs(arr, d, i));
        }
        return ++cache[cur];
    }
}
