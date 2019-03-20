package leetcode.realtest.realTest20181223;

import utils.PrintUtils;

/**
 * @author shibing
 * @since 2018/12/23 10:31
 */
public class NRepeatedElementinSize2NArray {
    public static void main(String[] args) {
        NRepeatedElementinSize2NArray nArray=new NRepeatedElementinSize2NArray();
        int[] testcase=PrintUtils.convertStringToIntArray("[1,2,3,3]");
        testcase=PrintUtils.convertStringToIntArray("[2,1,2,5,3,2]");
        testcase=PrintUtils.convertStringToIntArray("[5,1,5,2,5,3,5,4]");
//        testcase=PrintUtils.convertStringToIntArray("[5,5,5,2,5,3,3,4]");
        System.out.println(nArray.repeatedNTimes(testcase));
        System.out.println(nArray.repeatedNTimes1(testcase));
    }

    //O(1) random algorithm
    public int repeatedNTimes1(int[] A) {
        int i=0, j=0;
        while (i==j || A[i]!=A[j]){
            i=(int)(Math.random()*A.length);
            j=(int)(Math.random()*A.length);
        }
        return A[i];
    }

    //O(N)
    public int repeatedNTimes(int[] A) {
        for (int i = 0; i < 2; i++) {
            for (int j = 2; j < 4; j++) {
                if(A[i]==A[j]) return A[i];
            }
        }
        for (int i = 0; i < A.length; i+=2) {
            if(A[i]==A[i+1]) return A[i];
        }
        return -1;
    }
}
