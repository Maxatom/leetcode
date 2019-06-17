package leetcode.TopInterview;

/**
 * @author Shibing
 * @since 2019-06-14 11:22:01
 **/
public class TrappingRainWater {
    public static void main(String[] args) {
        TrappingRainWater water=new TrappingRainWater();
        int[] height={0,2,5,3,4,5,6,2,3,1,0,0,3};
//        height= new int[]{0,2,1,0,0,3};
        height=new int[]{1,0};
        System.out.println(water.trap(height));
    }

    //two pointers
    public int trap(int[] height) {
        int i=0, j=1, sum=0, cur=0;
        while (j<height.length) {
            if(height[j]>=height[i]){
                i=j;
                sum+=cur;
                cur=0;
            }
            cur+=height[i]-height[j];
            j++;
        }
        int maxi=i;
        i=height.length-1; j=i-1;
        while (j>maxi){
            if(height[j]>=height[i]) i=j;
            sum+=height[i]-height[j];
            j--;
        }
        return sum;
    }
}
