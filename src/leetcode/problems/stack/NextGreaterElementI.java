package leetcode.problems.stack;

import utils.PrintUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shibing
 * @since 2019/5/3 15:14
 */
public class NextGreaterElementI {
    public static void main(String[] args) {
        NextGreaterElementI greater=new NextGreaterElementI();
        int[] nums1 = {4,1,2}, nums2 = {1,3,4,2};
        nums1 = new int[]{2,4}; nums2 = new int[]{1,2,3,4};
        nums1 = new int[]{4}; nums2 = new int[]{4};
        nums1 = new int[]{1}; nums2 = new int[]{1,2,3,4};
        PrintUtils.printArray(greater.nextGreaterElement(nums1, nums2));
    }

    //reverse traversal,  straight forward maybe better
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] greater=new int[nums2.length];
        Map<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < nums2.length ; i++) {
            map.put(nums2[i], i);
        }
        for (int j = nums2.length-2; j >= 0; j--) {
            int next=j+1;
            while (greater[next]!=0 && nums2[next]<=nums2[j])
                next=greater[next];
            if(nums2[next]>nums2[j])
                greater[j]=next;
        }
        int[] res=new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int x=greater[map.get(nums1[i])];
            res[i]= x==0?-1:nums2[x];
        }
        return res;
    }
}
