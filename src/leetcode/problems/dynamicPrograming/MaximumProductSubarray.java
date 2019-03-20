package leetcode.problems.dynamicPrograming;

/**
 * @author shibing
 * @since 2019/1/26 10:45
 */
public class MaximumProductSubarray {
    public static void main(String[] args) {
        MaximumProductSubarray prod=new MaximumProductSubarray();
        int[] nums=new int[]{2,3,-2,4};
//        nums=new int[]{-2,0,-1};
//        nums=new int[]{-9,-4,-1,-4,-5};
//        nums=new int[]{-9,-4,-1,-4,-5,-2};//1440
//        nums=new int[]{-9,-4,1,-4,-5,-2};//720
//        nums=new int[]{-9,-4,1,0,-1,-4,-5,-2};//40
//        nums=new int[]{9,4,1,4,5};//720
//        nums=new int[]{4,0,4};//4
//        nums=new int[0];
//        nums=new int[]{3};
//        nums=new int[]{-1};
//        nums=new int[]{-1, 2};
//        nums=new int[]{3, -2};
        System.out.println(prod.maxProduct(nums));
        System.out.println(prod.maxProduct1(nums));

    }

    public int maxProduct1(int[] nums) {
        if(nums.length==0) return 0;
        int curmax=nums[0];
        int curmin=nums[0];
        int max=curmax;
        for (int i = 1; i < nums.length; i++) {
            int temp=curmax;
//            curmax=Collections.max(Arrays.asList(curmax*nums[i], curmin*nums[i], nums[i]));
//            curmin=Collections.min(Arrays.asList(temp*nums[i], curmin*nums[i], nums[i]));
            curmax=Math.max(Math.max(curmax*nums[i], curmin*nums[i]), nums[i]);
            curmin=Math.min(Math.min(temp*nums[i], curmin*nums[i]), nums[i]);
            max=Math.max(max, curmax);
        }
        return max;
    }
    //maxProd(i)=max(prod(i))|i=1~n  where
    //prod(i) is the max production of sub array which ends with the ith element
    public int maxProduct(int[] nums) {
        if(nums.length==0) return 0;
        int curmax=1;
        int curmin=1;
        int max=Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]>0) {
                if(curmax<0){
                    curmax=nums[i];
                    curmin*=nums[i];
                }else {
                    curmax  *= nums[i];
                    curmin  *= nums[i];
                }
                max=Math.max(max, curmax);
            }else if(nums[i]<0){
                if(curmax<0){
                    curmax=curmin*nums[i];
                    curmin=nums[i];
                }else{
                    int temp=curmax;
                    curmax=curmin*nums[i];
                    curmin=temp*nums[i];
                }
                max=Math.max(max, curmax);
            }else {
                curmax=1;
                curmin=1;
                max=Math.max(max, 0);
            }
        }
        return max;
    }
}
