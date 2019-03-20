package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;
import utils.PrintUtils;
import utils.Utils;
import utils.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 最大子段和
 * @author shibing
 * @since 2018/11/24 16:01
 */
public class MaximumSubarray {
    public static void main(String[] args) {
        String str="[[-2,1,-3,4,-1,2,1,-5,4], [], [-5], [2], [-3,-1], [-1,-4], [-1,4], [1,-4], [1,4], [4,1]" +
                ", [3,-2,1], [3,-100,3], [-3,2,5], [5,-1,-4]";
//        String s="[-2,1,-3,4,-1,2,1,-5,4]";
//        String s="[]";
//        String s="[-5]";
//        String s="[-1,-4]";
//        String s="[1,-4]";
        String s="[3,-1,4]";
//        String s="[-2,-1]";
        int[] input= PrintUtils.convertStringToIntArray(s);
        MaximumSubarray subarray=new MaximumSubarray();
        for (int[] s1: PrintUtils.convertStringTo2DIntArray(str)) {
            System.out.println("input: -----------------------------------------");
            PrintUtils.printIntArray(s1);
            System.out.println("maxSubArray = "+subarray.maxSubArray(s1));
            System.out.println("maxSubArray1 = "+subarray.maxSubArray1(s1));
            System.out.println("maxSubArray3 = "+subarray.maxSubArray3(s1));
            System.out.println("maxSubArray4 = "+subarray.maxSubArray4(s1));

        }
//        System.out.println("maxSubArray2 = "+subarray.maxSubArray2(input));
    }

    //动态规划
    //O(N)  A(1,i)=max( A(1,i-1), maxEndingWithA[i])
    public int maxSubArray3(int[] nums) {
        if(nums==null||nums.length==0) return 0;
        int maxSoFar=nums[0], maxEndingHere=nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxEndingHere=Math.max(nums[i], maxEndingHere+nums[i]);
            maxSoFar=Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }


    //又一失败尝试
    public int maxSubArray2(int[] nums) {
        return maxSubArray2(nums, 0, nums.length-1)[0];
    }
    //A(1,N)=max<x in 1,N-1>(A(1,x), A(x+1,N), A(1,N)) 状态转移方程
    //和分治法类似，只是多了很多重复计算
    Map<String,Integer[]> map=new HashMap<>();
    Integer[] maxSubArray2(int[] nums, int left, int right){
        if(map.get(left+","+right)!=null) return map.get(left+","+right);
        if(left==right) return new Integer[]{nums[left],left,right};
        Integer[] result=new Integer[]{nums[left],left,left};
        for(int x=left; x<right; x++){
            Integer[] leftsum=maxSubArray2(nums, left, x);
            Integer[] rightsum=maxSubArray2(nums, x+1, right);
            if(leftsum[0]>=rightsum[0]){
                result=leftsum;
            }else result=rightsum;
            if(leftsum[2]+1==rightsum[1]&&(leftsum[0]>0&&rightsum[0]>0)) {
                result[0] = leftsum[0]+rightsum[0];
                result[1] = leftsum[1]; result[2]=rightsum[2];
            }
        }
        map.put(left+","+right, result);
        return result;
    }

    //O(N^2) 从左向右依次遍历找最大子段
    public int maxSubArray1(int[] nums) {
        if(nums==null||nums.length==0) return 0;
        int i=0;
        int result=nums[0];
        while (i<nums.length){
            int sum=nums[i];
            int maxSum=sum;
            int end=i;
            //找到序列最大值右界
            for (int j = i+1; j < nums.length; j++) {
                sum+=nums[j];
                if(sum>maxSum) {
                    maxSum=sum;
                    end=j;
                }
            }
            sum=maxSum;
            //找左界
            for(int j=i; j<end; j++){
                sum-=nums[j];
                if(sum>result) result=sum;
            }
            if(maxSum>result) {
                result=maxSum;
            }
            i=end+1;
        }
        return result;
    }


    //O(N)分治算法
    public int maxSubArray4(int[] nums) {
        if(nums==null||nums.length==0) return 0;
        return divideAndConquer1(nums, 0, nums.length-1)[2];
    }

    //O(N) 分治法O(N)时间复杂度算法
    public int[] divideAndConquer1(int[] nums, int left, int right){
        if(left==right) return new int[]{nums[left],nums[left],nums[left],nums[left]};
        int mid=(left+right)/2;
        int[] leftSum=divideAndConquer1(nums, left, mid);
        int[] rightSum=divideAndConquer1(nums, mid+1,right);
        //a is the max continuous sum that includes the first element;
        //b is the max continuous sum that includes the last element;
        //s is the max continuous sum of any sub sequence
        //t is the total sum of all elements
        int a=Math.max(leftSum[0], leftSum[3]+rightSum[0]);
        int b=Math.max(rightSum[1], rightSum[3]+leftSum[1]);
        int s=Utils.max(leftSum[2], rightSum[2], leftSum[1]+rightSum[0]);
        int t=leftSum[3]+rightSum[3];
        return new int[]{a,b,s,t};
    }

    public int maxSubArray(int[] nums) {
        if(nums==null||nums.length==0) return 0;
        return divideAndConquer(nums, 0, nums.length-1);
    }

    /**
     * 分治法， 将数组分成左右两部分，最大字段要么在左边，要么在右边，或者横跨左右各占一部分
     * 算法时间复杂度O(NlogN)
     * @param nums 数组
     * @param left 起始位置
     * @param right 结束位置
     * @return 最大字段和
     */
    public int divideAndConquer(int[] nums, int left, int right){
        if(left==right) return nums[left];
        int mid=(left+right)/2;
        int leftMaxSum=divideAndConquer(nums, left, mid);
        int rightMaxSum=divideAndConquer(nums, mid+1,right);
        int i=mid, j=mid+1;
        int midSum=nums[i]+nums[j];
        int maxMidSum=midSum;
        while (--i>=left ) {
            maxMidSum = Math.max(maxMidSum, midSum += nums[i]);
        }
        midSum=maxMidSum;
        while (++j<=right) {
            maxMidSum = Math.max(maxMidSum, midSum += nums[j]);
        }
        return Math.max(Math.max(leftMaxSum,rightMaxSum),maxMidSum);
    }


}
