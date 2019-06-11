package leetcode.TopInterview;

import utils.PrintUtils;

/**
 * @author Shibing
 * @since 2019-06-11 16:20:28
 **/
public class SortColors {
    public static void main(String[] args) {
        SortColors colors=new SortColors();
        int[] nums={2,0,2,1,1,0};
//        nums=new int[]{2,0,2,1,1,0,1,1,1,2,1,2,0};
        nums=new int[]{2,0,1};
//        nums=new int[]{1,0,1};
        nums=new int[]{1,0};
        colors.sortColors1(nums);
        PrintUtils.printArray(nums);
    }
    public void sortColors1(int[] nums) {
        int zero=0, two=nums.length-1;
        for (int i = 0; i <= two; i++) {
            if(nums[i]==0){
                int temp=nums[i]; nums[i]=nums[zero]; nums[zero]=temp;
                zero++;
            }else if(nums[i]==2){
                int temp=nums[i]; nums[i]=nums[two]; nums[two]=temp;
                i--; two--;
            }
        }
    }
    //Wrong
    public void sortColors(int[] nums) {
        if(nums.length<2) return;
        int i=0, j=nums.length-1, temp=nums[j], k=1;
        while (k<3) {
            while (i < j) {
                while (i < j && nums[i] < k) i++;
                if (i < j) nums[j--] = nums[i];
                while (i < j && nums[j] > k) j--;
                if (i < j) nums[i++] = nums[j];
            }
            if(i==j) nums[i]=temp;
//            PrintUtils.printArray(nums);
//            System.out.println(i);
            i = i>k?i:i+1;
            j=nums.length-1;
            k++;
            temp=nums[j];
        }
    }
}

