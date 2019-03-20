package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;
import utils.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Shibing
 * @since 2018-12-03 17:16:56
 **/
public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        PartitionEqualSubsetSum subsetSum=new PartitionEqualSubsetSum();
        List<String> testCases=new ArrayList<>();
        testCases.add("[2]");
        testCases.add("[2,1]");
        testCases.add("[1,1]");
        testCases.add("[1,2,3,4,5,6,7]");
        testCases.add("[1, 5, 11, 5]");//5
        testCases.add("[1, 2, 3, 5]");
        testCases.add("[1, 3, 5, 4, 1, 8]");
        testCases.add("[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,100]");
        testCases.add("[100,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]");
        int i=0;
        for (String s:testCases) {
//            if(++i>3) continue;
            int[] test= PrintUtils.convertStringToIntArray(s);
            System.out.printf("------------------------------\n cases:");
            PrintUtils.printIntArray(test);
            long start=System.currentTimeMillis();
            boolean result1=subsetSum.canPartition1(test);
            System.out.printf(" time1=%d, result1=%b\n", (System.currentTimeMillis()-start), result1);

           start=System.currentTimeMillis();
            boolean result2=subsetSum.canPartition4(test);
            System.out.printf(" time4=%d, result2=%b\n", (System.currentTimeMillis()-start), result2);
            if(result1!=result2) throw new RuntimeException("Error");
        }
    }

    //optimizing
    public boolean canPartition4(int[] nums) {
        int sum=Arrays.stream(nums).sum();
        if((sum&1)==1||nums.length<2) return false;
        int halfsum=sum>>1;

        boolean[] dp=new boolean[halfsum+1];
        dp[0]=true;
        for (int i = 0; i < nums.length; i++) {
            for (int j = halfsum; j >0; j--) {
                if(j-nums[i]<0) {j=0; continue;}
                dp[j] = dp[j] || dp[j - nums[i]] ;
            }
        }
        return dp[halfsum];
    }

    //DP similar targetSum O(l*n)  l is sum of array
    public boolean canPartition1(int[] nums) {
        int sum=Arrays.stream(nums).sum();
        if((sum&1)==1||nums.length<2) return false;
        int halfsum=sum>>1;

        int[] dpPre=new int[sum+1];
        int[] dp=new int[sum+1];
        dpPre[nums[0]]=1; dpPre[0]=1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < sum+1; j++) {
                if(dpPre[j]!=0){
                    if(j==halfsum) return true;
                    else if(j>halfsum) dp[j]=0;
                    else {
                        dp[j + nums[i]] = 1;
                        dp[j] = 1;
                    }
                }
            }
            int[] temp=dpPre;
            dpPre=dp;
            dp=temp;
            Arrays.fill(dp,0);
        }
        return dpPre[halfsum]!=0;
    }

//    public boolean canPartition3(int[] nums) {
//        if (nums == null || nums.length == 0) {
//            return false;
//        }
//
//        // get sum
//        int sum = 0;
//        for (int n : nums) sum += n;
//        if (sum % 2 == 1) {
//            return false;
//        }
//
//        return dfs(nums, nums.length - 1, sum / 2);
//    }
//
//    private boolean dfs(int[] nums, int idx, int target) {
//        if (target == 0) {
//            return true;
//        }
//        if (idx < 0 || target < 0 || target < nums[idx]) {
//            return false;
//        }
//        return dfs(nums, idx - 1, target - nums[idx]) || dfs(nums, idx - 1, target);
//    }

    //递归，效率问题 TLE
    public boolean canPartition2(int[] nums){
        int sum=Arrays.stream(nums).sum();
        if((sum&1)==1||nums.length<2) return false;
        return recursive(nums,sum>>1,0);
    }
    public boolean recursive(int[] nums, int sum, int i){
        if(sum==0) return true;
        if(sum<0||i>=nums.length) return false;
        return recursive(nums, sum-nums[i], i+1)|| recursive(nums, sum, i+1);
    }

    //O(NlogN) ERROR 失败的尝试
    public boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int sum=0;
        for (int i:nums) sum+=i;
        if((sum&1)==1) return false;
        int halfsum=0;
        for (int i:nums){
            if(halfsum==sum>>1) return true;
            else if(halfsum>sum>>1) return false;
            halfsum+=i;
        }
        return false;
    }
}
