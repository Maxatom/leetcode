package leetcode.problems.dynamicPrograming;

import utils.ArrayGenerator;
import utils.ArrayGenerator;
import utils.Tuple;
import utils.Tuple;
import utils.TwoTuple;
import utils.TwoTuple;
import utils.PrintUtils;
import utils.PrintUtils;

import java.util.*;

/**
 * @author shibing
 * @since 2018/12/2 17:50
 */
public class TargetSum {
    public static void main(String[] args) {
        TargetSum sum=new TargetSum();
        List<TwoTuple<String,Integer>> testCases = new ArrayList<>();
//        testCases.add(Tuple.tuple("[1, 1, 1, 1, 1]", 3));
        testCases.add(Tuple.tuple("[1]", 1));
        testCases.add(Tuple.tuple("[1]", 3));
        testCases.add(Tuple.tuple("[1,2,1,2,1,2]", 3));
        testCases.add(Tuple.tuple("[2,3,4]", 3));
        testCases.add(Tuple.tuple("[2,2,2,2,2,2,2,2,2]", 2));
        for (TwoTuple<String,Integer> test:testCases){
            int[] arr= PrintUtils.convertStringToIntArray(test.first);
            System.out.println("---------------------------------");
            System.out.println(test);
            System.out.println(sum.findTargetSumWays(arr,test.second));
        }

//        int[] testCase=new int[]{1,3,6,2,4,5};
        int[] testCase=new int[]{1,1,1,1,1};
//        Arrays.fill(testCase,1 );
        testCase=ArrayGenerator.intArray(20, 0, 10);
        long start=System.currentTimeMillis();
        System.out.println(sum.findTargetSumWays(testCase, 3)+" ,time="+(System.currentTimeMillis()-start));
        System.out.printf(" times=%d , curtimes=%d\n", sum.times,sum.curtimes);

        start=System.currentTimeMillis();
        System.out.println(sum.findTargetSumWays1(testCase, 3)+" ,time="+(System.currentTimeMillis()-start));

//       start=System.currentTimeMillis();targetSum.times=0;targetSum.curtimes=0;
//        System.out.println(targetSum.findTargetSumWays2(testCase, -1)+" ,time="+(System.currentTimeMillis()-start));
//        System.out.printf(" times=%d , curtimes=%d\n", targetSum.times,targetSum.curtimes);
    }

    //DP  O(l*n)
    //dp[i][sum+num[j]]=dp[i][sum+num[j]]+dp[i-1][sum]  sum is sum upto ith element.
    public int findTargetSumWays1(int[] nums, int S) {
        if(nums.length<1) return 0;
        int sum=0;
        for (int i:nums) sum+=i;
        if(S>sum) return 0;
        int[] dpPre=new int[2*sum+1];
        dpPre[nums[0]+sum]=1; dpPre[-nums[0]+sum]+=1;
        int[] dp=new int[2*sum+1];
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < 2*sum+1; j++) {
                if (dpPre[j]!=0){
                    dp[j+nums[i]]+=dpPre[j];
                    dp[j-nums[i]]+=dpPre[j];
                }
            }
            int[] dptemp=dpPre;
            dpPre=dp;
            dp=dptemp;
            Arrays.fill(dp, 0);
        }

        return dpPre[S+sum];
    }

    public int findTargetSumWays(int[] nums, int S) {
        if(nums.length<1) return 0;
        return recursive(nums,S,nums.length);
    }


    int times=0;
    int curtimes=0;
    Map<Integer,Integer> cache=new HashMap<>();
    public int  recursive(int[] nums, int S, int length){
        times++;
        int key=S*100+length;
        if(cache.containsKey(key)) return cache.get(key);
        curtimes++;
        if(length==1) {
            if(S==0&&nums[0]==S) return 2;
            else if(nums[0]==S||-nums[0]==S) return 1;
            else return 0;
        }

        int result;
        if(nums[length-1]==0) {
            result=recursive(nums, S ,length-1)*2;
        }else{
            int left= recursive(nums, S-nums[length-1], length-1);
            int right= recursive(nums, S+nums[length-1], length-1);
            result= left+right;
        }
        cache.put(key,result);
        return result;
    }

    //leetCode示例代码
        int count = 0;
        public int findTargetSumWays2(int[] nums, int S) {
            int[][] memo = new int[nums.length][2001];
            for (int[] row: memo)
                Arrays.fill(row, Integer.MIN_VALUE);
            return calculate(nums, 0, 0, S, memo);
        }
        public int calculate(int[] nums, int i, int sum, int S, int[][] memo) {
            times++;
            if (i == nums.length) {
                if (sum == S)
                    return 1;
                else
                    return 0;
            } else {
                if (memo[i][sum + 1000] != Integer.MIN_VALUE) {
                    return memo[i][sum + 1000];
                }
                curtimes++;
                int add = calculate(nums, i + 1, sum + nums[i], S, memo);
                int subtract = calculate(nums, i + 1, sum - nums[i], S, memo);
                memo[i][sum + 1000] = add + subtract;
                return memo[i][sum + 1000];
            }
        }
}
