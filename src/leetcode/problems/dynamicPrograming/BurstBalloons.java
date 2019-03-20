package leetcode.problems.dynamicPrograming;

import utils.ArrayGenerator;
import utils.ArrayGenerator;
import utils.PrintUtils;
import utils.PrintUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shibing
 * @since 2019/1/11 23:48
 */
public class BurstBalloons {
    public static void main(String[] args) {
        BurstBalloons balloons=new BurstBalloons();
        int[] test=PrintUtils.convertStringToIntArray("[3,1,5,8]"); //167
//        test=new int[]{2,8,2};  //40
//        test=new int[]{1,8,2};  //32
//        test=new int[]{1,8,1}; //24
//        test=new int[0];
//        test=new int[]{9,1,7,9,3,5,6,1}; //1593
//        test=new int[]{7,9,8,0,7,1,3,5,5,2,3,3};  //1717
        test=ArrayGenerator.intArray(1000,1,20);
//        PrintUtils.printIntArray(test);
        long start=System.currentTimeMillis();
//        System.out.println(balloons.maxCoins(test));
//        System.out.println(balloons.maxCoins1(test)+", time="+(System.currentTimeMillis()-start)+", times="+balloons.times+", map.size="+balloons.map.size());
//        start=System.currentTimeMillis();
//        System.out.println(balloons.maxCoins2(test)+", time="+(System.currentTimeMillis()-start)+", times2="+balloons.times2+", map.size="+balloons.cache.size());
        start=System.currentTimeMillis();
        System.out.println(balloons.maxCoins2_1(test)+", time2_1="+(System.currentTimeMillis()-start));//+", times2="+balloons.times2+", map.size="+balloons.cache.size());
        start=System.currentTimeMillis();
        System.out.println(balloons.maxCoins3(test)+", time3="+(System.currentTimeMillis()-start));//+", times2="+balloons.times2+", map.size="+balloons.cache.size());
    }

    //mem[k][i] means the max sum of subarray that starts with ith and whose length is k;
    //O(N^3) DP bottom up
    public int maxCoins3(int[] nums) {
        if(nums.length==0) return 0;
        int N=nums.length;
        int[][] mem=new int[N+1][N];
        for(int k=1; k<=N; k++){
            for (int i = 0; i <=N-k ; i++) {
                mem[k][i]=0;
                int r=i+k;
                for (int j = i; j <r; j++) {
                    mem[k][i]=Math.max(mem[k][i], mem[j-i][i]
                            +(i<1?1:nums[i-1])*nums[j]*(r>=N?1:nums[r])
                            +(j==r-1?0:mem[r-j-1][j+1]));
                }
            }
        }
//        PrintUtils.print2DIntArray(mem, 5);
        return mem[N][0];
    }



    //using arrays memorization
    public int maxCoins2_1(int[] nums) {
        if(nums.length==0) return 0;
        int[][] mem=new int[nums.length+2][nums.length+2];
        int[] inums=new int[nums.length+2];
        int n=1;
        for (int i :nums) if(i>0) inums[n++]=i;
        inums[0]=inums[n]=1;

        return divideAndConquer1(mem, inums, 1, n-1);
    }
    // mem[left][right] means sub array = nums[left, right]
    //O(A(N,logN))  top-down using memorization
    int divideAndConquer1(int[][] mem, int[] nums, int left, int right){
        if(left>right) return 0;
        if(mem[left][right]>0) return mem[left][right];
        if(left==right) return nums[left-1]*nums[left]*nums[right+1];
        int max=0;
        for (int i = left; i <= right; i++)
            max=Math.max(max, divideAndConquer1(mem, nums, left, i-1)
                    +nums[left-1]*nums[i]*nums[right+1]
                    +divideAndConquer1(mem, nums, i+1, right));
        mem[left][right]=max;
        return max;
    }

    public int maxCoins2(int[] nums) {
        return divideAndConquer(nums, 0, nums.length-1);
    }
    int times2=0;
    Map<Integer,Integer> cache=new HashMap<>();
    //O(A(N,logN))  top-down using memorization
    int divideAndConquer(int[] nums, int left, int right){
        times2++;
        Integer key=left*1000+right;
        if(cache.containsKey(key)) return cache.get(key);
        if(left>right) return 0;
        if(left==right) return (left<1?1:nums[left-1])*nums[left]*(right>=nums.length-1?1:nums[right+1]);
        int max=0;
        for (int i = left; i <= right; i++)
            max=Math.max(max, divideAndConquer(nums, left, i-1)
                    +(left<1?1:nums[left-1])*nums[i]*(right>=nums.length-1?1:nums[right+1])
                    +divideAndConquer(nums, i+1, right));
        cache.put(key, max);
        return max;
    }

    //optimizing by cache, DP to-down solution
    Map<String, Integer> map=new HashMap<>();
    public int maxCoins1(int[] nums) {
        return recursive1(nums);
    }
    int times=0;
    int recursive1(int[] nums){
        times++;
        if(nums.length==1) return nums[0];
        String key=Arrays.toString(nums);
        if(map.containsKey(key)) return map.get(key);
        int max=0;
        for (int i = 0; i < nums.length; i++) {
            int[] subArray=removeElem(nums,i);
            int temp=(i==0?1:nums[i-1])*nums[i]*(i==nums.length-1?1:nums[i+1]);
            max=Math.max(max,temp+recursive1(subArray));
        }
        map.put(key, max);
        return max;
    }

    //brute force  O(N!)
    public int maxCoins(int[] nums) {
        return recursive(nums,0);
    }
    int recursive(int[] nums, int sum){
        if(nums.length==1) return sum+nums[0];
        int max=0;
        for (int i = 0; i < nums.length; i++) {
            int[] subArray=removeElem(nums,i);
            int temp=sum+(i==0?1:nums[i-1])*nums[i]*(i==nums.length-1?1:nums[i+1]);
            max=Math.max(max,recursive(subArray,temp));
        }
        return max;
    }
    int[] removeElem(int[] nums, int i){
        int[] newArray=new int[nums.length-1];
        int k=0;
        for (int j = 0; j < nums.length; j++) {
            if(j!=i) newArray[k++]=nums[j];
        }
        return newArray;
    }
}
