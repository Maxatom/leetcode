package leetcode.problems.dynamicPrograming;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shibing
 * @since 2019/1/26 19:21
 */
public class HouseRobberII {
    public static void main(String[] args) {
        HouseRobberII robberII=new HouseRobberII();
        int[] nums=new int[]{2,3,2};
        nums=new int[]{1,2,3,1};
        nums=new int[]{1,2};
        nums=new int[]{2};
        nums=new int[]{1,2,3,1,6};
        nums=new int[]{226,174,214,16,218,48,153,131,128,17,157,142,88,43,37,157,43,221,191,68,206,23,225,82,54,118,111,46,80,49,245,63,25,194,72,80,143,55,209,18,55,122,65,66,177,101,63,201,172,130,103,225,142,46,86,185,62,138,212,192,125,77,223,188,99,228,90,25,193,211,84,239,119,234,85,83,123,120,131,203,219,10,82,35,120,180,249,106,37,169,225,54,103,55,166,124};
        nums=new int[]{226,174,214,16,218,48,153,131,128,17,157,142,88,43,37,157,43,221,191,68,206,23,225,82,54,118,111,46,80,49,245,63,25,194,72,80,143,55,209,18,55,122,65,66,177,101,63,201,172,130,103,225,142,46,86,185,62,138,212,192,125,77,223,188,99,228,90,25,193};
        nums=new int[]{6,6,4,8,4,3,3,10};
        System.out.println(robberII.rob(nums));
        System.out.println(robberII.rob1(nums));
    }


    //dp bottom-up
    public int rob1(int[] nums) {
        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];
        int max=0;

        for (int j = 0; j <2 ; j++) {
            int[] dp=new int[4];
            for (int i = j; i < nums.length-1+j; i++) {
                dp[3] = Math.max(dp[0], dp[1]) + nums[i];
                max = Math.max(dp[3], max);
                dp[0] = dp[1];
                dp[1] = dp[2];
                dp[2] = dp[3];
            }
        }
        return max;
    }

    public int rob(int[] nums) {
        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];
        int max=0;
        for (int i = 0; i < 3; i++)
            max=Math.max(max,recursive(nums,i,i));
//        map.forEach((k,v)->System.out.println(k+"->"+v));
        return max;
    }
    //dp top-down
    Map<Integer,Integer> map=new HashMap<>();
    public int recursive(int[] nums, int i, int first){
        if((i+1)%nums.length==first) return -1;
        else if(i>=nums.length) return 0;
        int key=i*10+first;
        if(map.containsKey(key)) return map.get(key);
        int am1=recursive(nums, i+2, first);
        int am2=recursive(nums, i+3, first);
        int result;
        if(am1==-1&&am2==-1) result= -1;
        else result= Math.max(am1, am2)+nums[i];
        map.put(key, result);
        return result;
    }
}
