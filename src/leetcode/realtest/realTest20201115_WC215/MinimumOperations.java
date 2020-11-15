package leetcode.realtest.realTest20201115_WC215;

public class MinimumOperations {
    public static void main(String[] args) {
        MinimumOperations oper= new MinimumOperations();
        int[] nums = new int[]{1,1,4,2,3};int  x = 5;
        nums = new int[]{5,6,7,8,9}; x = 4;
        nums = new int[]{3,2,20,1,1,3}; x = 10;
        nums = new int[]{3,2,21,1,1,3}; x = 10;
//        nums = new int[]{3,2,1,1,3}; x = 10;
//        nums = new int[]{3,2,20,1,1,3}; x = 10;
//        nums = new int[] {1,1}; x=3;
        System.out.println(oper.minOperations(nums,x));
    }
    public int minOperations(int[] nums, int x) {
        int sum=0, n=nums.length;
        for(int num:nums) sum+=num;
        if(sum<x) return -1;
        if(sum==x) return n;
        int l=0, r=0, rem=sum-x, sum2=0, max=0;
        while(l<n && r<n){
            while(r<n && sum2<=rem){
                sum2+=nums[r];
                r++;
            }
            if(sum2-nums[r-1]==rem){
                max=Math.max(max, r-1-l);
            }
            while(l<r && sum2>rem){
                sum2-=nums[l];
                l++;
            }
            if(sum2==rem){
                max=Math.max(max, r-l);
            }
        }
        return max==0?-1:n-max;
    }
}
