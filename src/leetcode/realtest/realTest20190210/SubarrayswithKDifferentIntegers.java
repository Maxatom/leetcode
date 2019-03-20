package leetcode.realtest.realTest20190210;

import utils.ArrayGenerator;
import utils.ArrayGenerator;

import java.util.*;

/**
 * @author Shibing
 * @since 2019-02-13 10:24:33
 **/
public class SubarrayswithKDifferentIntegers {
    public static void main(String[] args) {
        SubarrayswithKDifferentIntegers kdiff=new SubarrayswithKDifferentIntegers();
        int[] A=new int[]{1,2,1,2,3}; int K=2;
//        A=new int[]{1,2,1,3,4}; K=3;
//        A=new int[]{1,1,1}; K=1;
//        A=ArrayGenerator.intArray(100000, 10, 5000); K=1000;
        long start=System.currentTimeMillis();
//        System.out.println(kdiff.subarraysWithKDistinct(A, K)+" , time="+(System.currentTimeMillis()-start));
        start=System.currentTimeMillis();
        System.out.println(kdiff.subarraysWithKDistinct1(A, K)+" , time="+(System.currentTimeMillis()-start));
        start=System.currentTimeMillis();
        System.out.println(kdiff.subarraysWithKDistinct2(A, K)+" , time="+(System.currentTimeMillis()-start));
    }

    //Two pointers  or sliding windows
    public int subarraysWithKDistinct1(int[] A, int K) {
        return atMostK(A, K)-atMostK(A, K-1);
    }

    public int atMostK(int[] A, int K){
        int i=0, j=0, res=0;
        Map<Integer, Integer> map=new HashMap<>();
        while (j<A.length){
            if(map.getOrDefault(A[j], 0)==0) K--;
            map.put(A[j], map.getOrDefault(A[j], 0)+1);
            while (K<0){
                map.put(A[i], map.get(A[i])-1);
                if(map.get(A[i])==0) K++;
                i++;
            }
            res+=j-i+1;
            j++;
        }
        return res;
    }
    public int subarraysWithKDistinct2(int[] A, int K) {
        return atMostK1(A, K)-atMostK1(A, K-1);
    }
    //use array  optimizing time
    public int atMostK1(int[] A, int K){
        int i=0, j=0, res=0;
        int[] count=new int[A.length+1];
        while (j<A.length){
            if(count[A[j]]==0) K--;
            count[A[j]]++;
            while (K<0){
                count[A[i]]--;
                if(count[A[i]]==0) K++;
                i++;
            }
            res+=j-i+1;
            j++;
        }
        return res;
    }
        //brute force  O(N^2)
    public int subarraysWithKDistinct(int[] A, int K) {
        int res=0;
        for (int i = 0; i < A.length; i++) {
            Set<Integer> set=new HashSet<>();
            for (int k = i; k <A.length ; k++) {
                set.add(A[k]);
                if(set.size()==K) res++;
                else if(set.size()>K) break ;
            }
        }
        return res;
    }
}
