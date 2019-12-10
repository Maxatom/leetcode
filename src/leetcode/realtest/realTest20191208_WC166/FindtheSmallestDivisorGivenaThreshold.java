package leetcode.realtest.realTest20191208_WC166;

public class FindtheSmallestDivisorGivenaThreshold {
    public static void main(String[] args) {
        FindtheSmallestDivisorGivenaThreshold findthreshold=new FindtheSmallestDivisorGivenaThreshold();
        int[] nums = {19}; int threshold = 5;
        nums = new int[]{2,3,5,7,11}; threshold = 5;
        nums = new int[]{1,2,5,9}; threshold = 6;
        nums=new int[]{81488,78770,69737,14847,36203,85812,62771,25584,40434,55397,95643,48474,63802,63203,69258,16397,62134,68311,48931,4317,488,76613,70301,24052,67069,8018,61725,98992,86206,54407,33175,65597,5215,11866,9420,93851,66858,78097,70255,92021,50353,91451,52417,411,81332,27928,51842,83525,74458,10995,18792,14219,31190,86907,412,45731,9730,1258,94589,84673,20469,479,71501,68751,18230,77410,41662,9005,72532,36514,5203,528,59746,45580,72611,30386,40609,30783,70776,78765,97187,19997,16257,86367,80891,43219,55020,4753,2785,70141,86103,25092,50595,73151,69139,92893,18011,94848,65111,5657};
        threshold=988;
        System.out.println(findthreshold.smallestDivisor(nums,threshold));
    }

    public int smallestDivisor(int[] nums, int threshold) {
        int min=1, max=nums.length, n=nums.length, sum=0;
        for (int i = 0; i < n ; i++) {
            max=Math.max(max, nums[i]);
            sum+=nums[i];
        }
        if(threshold == sum) return 1;
        if(threshold == n) return max;
        return binaryTry(nums,min, max, threshold);
    }

    int binaryTry(int[] nums, int le, int ri, int threshold){
        int mid=(le+ri)/2, sum=0;
        while (le<ri) {
            mid=(le+ri)/2;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i] / mid + (nums[i] % mid > 0 ? 1 : 0);
            }
            if(sum <= threshold) ri=mid;
            else le=mid+1;
            sum=0;
        }
        return le;
    }
}
