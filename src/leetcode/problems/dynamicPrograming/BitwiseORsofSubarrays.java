package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Shibing
 * @since 2019-05-28 13:37:48
 **/
public class BitwiseORsofSubarrays {
    public static void main(String[] args) {
        BitwiseORsofSubarrays bitwise=new BitwiseORsofSubarrays();
        int[] A={0};
        A=new int[]{1,1,2};
//        A=new int[]{1,2,4};
//        A=new int[]{1,3,4,5,6,4};
//        A=new int[]{1,11,6,11};
        System.out.println(bitwise.subarrayBitwiseORs(A));
        System.out.println(bitwise.subarrayBitwiseORs1(A));
    }

    //just two hashset
    public int subarrayBitwiseORs1(int[] A) {
        int n=A.length;
        Set<Integer> set=new HashSet<>(), pre=new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(A[i]);
            Set<Integer> cur=new HashSet<>();
            if(A[i]==A[i-1]) continue;
            cur.add(A[i]);
            for(Integer num:pre) {
                cur.add(A[i] | num);
            }
            set.addAll(cur);
            pre=cur;
        }
        return set.size();
    }


    //brute force
    public int subarrayBitwiseORs(int[] A) {
        int n=A.length;
        Set<Integer> set=new HashSet<>();
        Set<Integer>[] sets=new Set[n];
        set.add(A[0]); sets[0]=new HashSet<>(); sets[0].add(A[0]);
        for (int i = 1; i < n; i++) {
            set.add(A[i]);
            sets[i]=new HashSet<>();
            sets[i].add(A[i]);
            if(A[i]==A[i-1]){
                sets[i]=sets[i-1]; continue;
            }
            for(Integer num:sets[i-1]){
                int temp=A[i] | num;
                sets[i].add(temp);
                set.add(temp);
            }
        }
        return set.size();
    }
}
