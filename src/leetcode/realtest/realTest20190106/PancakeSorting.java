package leetcode.realtest.realTest20190106;

import utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shibing
 * @since 2019/1/6 14:20
 */
public class PancakeSorting {
    public static void main(String[] args) {
        PancakeSorting sorting=new PancakeSorting();
        int[] testcase=PrintUtils.convertStringToIntArray("[3,2,4,1]");
        testcase=PrintUtils.convertStringToIntArray("[1,2,3]");
        testcase=PrintUtils.convertStringToIntArray("[1]");
        testcase=PrintUtils.convertStringToIntArray("[56,90,8,3,1]");
        List<Integer> list=sorting.pancakeSort(testcase);
        PrintUtils.printList(list, p->p+"");
        PrintUtils.printIntArray(testcase);
    }




    //O(N^2)
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> result=new ArrayList<>();
        int max;
        for (int i = A.length-1; i >0 ; i--) {
            max=findMax(A, i);
            if(max==i) continue;
            result.add(max+1);
            result.add(i+1);
            reverse(A, 0, max);
            reverse(A, 0, i);
        }
        return result;
    }
    void reverse(int[] A, int left, int right){
        int temp;
        for (int i = left; i <= (left+right)/2; i++) {
            temp=A[i];
            A[i]=A[right-i];
            A[right-i]=temp;
        }
    }
    int findMax(int[] A, int i){
        int index=i;
        for (int j = i-1; j >=0 ; j--) {
            if(A[j]>A[index]) index=j;
        }
        return index;
    }

//    boolean validation(int[] A, List<Integer> re){
//        for (Integer e:re)
//            reverse(A,0, e-1);
//        PrintUtils.printIntArray(A);
//        for (int i = 0; i < A.length; i++) {
//            if(A[i]>A[i+1]) return false;
//        }
//        return true;
//    }
}
