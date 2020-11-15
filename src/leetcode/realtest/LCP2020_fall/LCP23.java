package leetcode.realtest.LCP2020_fall;

import utils.ArrayGenerator;
import utils.PrintUtils;

import java.io.IOException;
import java.util.*;

public class LCP23 {
    public static void main(String[] args) throws IOException {
        LCP23 lcp23 = new LCP23();
        int[] nums = {};
//        System.out.println(Arrays.toString(lcp23.numsGame(nums)));

        lcp23.compartor();
    }
    void compartor() throws IOException {
        LCP23 lcp23 = new LCP23();
        for (int i = 0; i < 1; i++) {
            int[] nums =  PrintUtils.readArrayFromFile("E:\\javastudy\\leetcode\\src\\leetcode\\realtest\\LCP2020_fall\\data.txt");
//            int[] nums = ArrayGenerator.intArray(10, -100,100);
            int[] nums1= Arrays.copyOf(nums, nums.length);
            int[] res =  lcp23.numsGame(nums);
            int[] res1 =  lcp23.numsGame1(nums1);
            if(!Arrays.toString(res).equals(Arrays.toString(res1))) {
                System.out.println(Arrays.toString(nums));
                System.out.println(Arrays.toString(res));
                System.out.println(Arrays.toString(res1));
            }
        }

    }
    int M=(int)1e9+7;
    public int[] numsGame(int[] nums) {
        int n= nums.length;
        for(int i=0; i<nums.length; i++){
            nums[i]-=i;
        }
//         System.out.println(Arrays.toString(nums));
        Queue<Integer> bigHeap=new PriorityQueue<>((a, b)->b-a);
        Queue<Integer> smallHeap=new PriorityQueue<>();
        int bigSum=0, smallSum=0;
        int[] res= new int[n];
        for(int i=0; i<n; i++){
            int midian;
            if(i%2==1){
                bigHeap.add(nums[i]);
                midian=bigHeap.poll();
                smallHeap.add(midian);
                bigSum += nums[i] - midian;
                smallSum += midian;
                bigSum%=M;
                smallSum%=M;
            }else{
                if(smallHeap.size()>0 && nums[i]>smallHeap.peek()){
                    bigSum+=smallHeap.peek();
                    smallSum = smallSum-smallHeap.peek()+ nums[i];
                    smallHeap.add(nums[i]);
                    bigHeap.add(smallHeap.poll());
                }else {
                    bigHeap.add(nums[i]);
                    bigSum+=nums[i];
                }
                midian=bigHeap.peek();
                bigSum%=M;
                smallSum%=M;
            }
//             System.out.printf("%d,%d,%d,%d, i=%d, median=%d\n",bigHeap.peek(), smallHeap.peek(), bigSum,smallSum, i%2,midian);
            res[i] = i%2==1 ? smallSum-bigSum : smallSum-bigSum+midian;
            if(res[i]<0) res[i] += M;
        }
        return res;
    }
    public int[] numsGame1(int[] nums) {
        int n=nums.length;
        for(int i=0; i<n; i++){
            nums[i]-=i;
        }
        int[] preSum = new int[n+1];
        for (int i = 0; i <n; i++) {
            preSum[i+1]=preSum[i]+ nums[i];
        }
        int[] res = new int[nums.length];
        List<Integer> list = new ArrayList<>();
        for(int i=0; i< nums.length; i++){
            list.add(nums[i]);
            int sum = findMedian(list);
            res[i]=sum;
        }
        return res;
    }
    int findMedian(List<Integer> list){
        list.sort(Comparator.naturalOrder());
       int median = list.get(list.size()/2);
       int sum=0;
       for(int e:list){
           sum+=Math.abs(e-median);
       }
       return sum;
    }

}