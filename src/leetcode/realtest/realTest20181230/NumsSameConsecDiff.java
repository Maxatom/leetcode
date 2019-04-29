package leetcode.realtest.realTest20181230;

import utils.Tuple;
import utils.TwoTuple;
import utils.PrintUtils;

import java.util.*;

/**
 * 967 Numbers With Same Consecutive Differences
 * @author shibing
 * @since 2018/12/30 10:47
 */
public class NumsSameConsecDiff {
    public static void main(String[] args) {
        NumsSameConsecDiff diff=new NumsSameConsecDiff();
        TwoTuple<Integer,Integer> testcase=Tuple.tuple(3,6);
//        testcase=Tuple.tuple(2,1);
        testcase=Tuple.tuple(7,1);
        testcase=Tuple.tuple(9,7);
        int[] result=diff.numsSameConsecDiff1(testcase.first,testcase.second);
        Arrays.sort(result);
        PrintUtils.printArray(result);
        int[] result2=diff.numsSameConsecDiff2(testcase.first,testcase.second);
        Arrays.sort(result2);
        PrintUtils.printArray(result2);
    }

    public int[] numsSameConsecDiff2(int N, int K) {
        if(N==1) return new int[]{0,1,2,3,4,5,6,7,8,9};
        Set<Integer> nums=new HashSet<>();
        for (int i = 1; i < 10; i++) {
            nums.add(i);
        }
        for (int i = 1; i < N; i++) {
            Set<Integer> nums2=new HashSet<>();
            for(int elem: nums){
                int lastBit=elem%10;
                if(lastBit+K<10) nums2.add(elem*10+lastBit+K);
                if(lastBit-K>=0) nums2.add(elem*10+lastBit-K);
            }
            nums=nums2;
        }
        return nums.stream().mapToInt(p->p).toArray();
    }


    public int[] numsSameConsecDiff1(int N, int K) {
        if(N==1) return new int[]{0,1,2,3,4,5,6,7,8,9};
        List<Integer> nums=new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            if(i+K<10 ||i-K>=0)
                recursive(N-1,K,i,nums);
        }
        return nums.stream().mapToInt(p->p).toArray();
    }

    void recursive(int N, int K, int num, List<Integer> nums){
        if(N==0) {nums.add(num); return;}
        int lastbit=num%10;
        if(lastbit+K<10)
            recursive(N-1,K,num*10+lastbit+K,nums);
        if(K!=0&&lastbit-K>=0)
            recursive(N-1,K,num*10+lastbit-K,nums);
    }

}
