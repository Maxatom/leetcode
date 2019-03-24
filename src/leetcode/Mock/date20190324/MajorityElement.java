package leetcode.Mock.date20190324;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shibing
 * @since 2019/3/24 14:58
 */
public class MajorityElement {
    public static void main(String[] args) {
        MajorityElement element=new MajorityElement();
        int[] nums=new int[]{3,2,3};
        System.out.println(element.majorityElement(nums));
    }

    public List<Integer> majorityElement(int[] nums) {
//        int N=nums.length;
//        int n=N%3==0?N/3:(N/3+1);
//        List<Integer> res=new ArrayList<>();
//        Map<Integer, Integer> map=new HashMap<>();
//        int pos=partition(nums,0, N-1);
//        if(pos>=n)
        return null;
    }
    public int partition(int[] A, int left, int right){
        int x = A[left], i = left, j = right;
        while (i < j) {
            while (i < j && A[j] >= x) j--;
            if (i < j) A[i++] = A[j];
            while (i < j && A[i] < x) i++;
            if (i < j) A[j--] = A[i];
        }
        A[i] = x;
        return i;
    }
}
