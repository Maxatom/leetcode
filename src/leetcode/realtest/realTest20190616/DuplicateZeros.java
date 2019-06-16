package leetcode.realtest.realTest20190616;

import utils.PrintUtils;

/**
 * @author shibing
 * @since 2019/6/16 10:35
 */
public class DuplicateZeros {
    public static void main(String[] args) {
        DuplicateZeros duplicateZeros=new DuplicateZeros();
        int[] arr=new int[]{1,0,2,3,0,4,5,0};
        arr=new int[]{1,2,3};
        arr=new int[]{1,0,2, 0,3};
        duplicateZeros.duplicateZeros(arr);
        PrintUtils.printArray(arr);
    }
    public void duplicateZeros(int[] arr) {
        int n=arr.length, zeros=0;
        for (int i = 0; i < n; i++) {
            if(arr[i]==0) zeros++;
        }
        int pre=n;
        for (int i = n-1; i >= 0 ; i--) {
            if(arr[i]==0){
                int j=pre+1+zeros;
                System.out.println("j="+j+", i="+i+", zeros="+zeros);
                for (int k = 0; k < pre+1-i; k++) {
                    if(j<n)
                        arr[j]=arr[j-zeros];
                    j--;
                }
                if(i+1<n) arr[i+1]=0;
                pre=i;
                zeros--;
            }
        }
    }
//    public void shift(int[] arr, int idx, int zeros){
//
//    }
}
