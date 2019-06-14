package leetcode.TopInterview;

/**
 * @author Shibing
 * @since 2019-06-13 13:33:37
 **/
public class ContainerWithMostWater {
    public static void main(String[] args) {
        ContainerWithMostWater withMostWater=new ContainerWithMostWater();
        int[] height={1,8,6,2,5,4,8,3,7};
        height=new int[]{1,4};
//        System.out.println(withMostWater.maxArea(height));
        System.out.println(withMostWater.maxArea1(height));
        System.out.println(withMostWater.maxArea2(height));
    }

    //two pointers , optimization
    public int maxArea2(int[] height) {
        int i=0, j=height.length-1, max=0;
        while (i<j){
            max=Math.max(max, Math.min(height[i], height[j])*(j-i));
            if(height[i]<height[j])
                i++;
            else
                j--;
        }
        return max;
    }
    //two pointers
    public int maxArea1(int[] height) {
        int i=0, j=height.length-1, max=0;
        while (i<j){
            while (i<j){
                max=Math.max(max, Math.min(height[i], height[j])*(j-i));
                if(height[j]>=height[i]) break;
                j--;
            }
            int s=i;
            while (i<j && height[i]<=height[s]) i++;
        }
        return max;
    }

    //straight forward
    public int maxArea(int[] height) {
        int max=0;
        for (int i = 0; i < height.length; i++)
            for (int j = 0; j < i; j++)
                max=Math.max(max, (i-j)*Math.min(height[i],height[j]));
        return max;
    }
}
