package leetcode.realtest.realTest20190714;

import utils.PrintUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shibing
 * @since 2019/7/14 10:48
 */
public class RelativeSortArray1122 {
    public static void main(String[] args) {
        RelativeSortArray1122 sort=new RelativeSortArray1122();
        int[]  arr1 = {2,3,1,3,2,4,6,7,9,2,19}, arr2 = {2,1,4,3,9,6};
          arr1 = new int[]{2,3,1,3,2,6,4,89,26,7,9,2,19}; arr2 = new int[]{1,4,3,9,6};
          arr1=new int[0]; arr2=new int[0];
          arr1=new int[]{2,3,5}; arr2=new int[]{3,2};
        PrintUtils.printArray(sort.relativeSortArray(arr1, arr2));
    }
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int m=arr1.length, n=arr2.length;
        int[] arr2cnt=new int[1001];
        for (int i = 0; i < n; i++) {
            arr2cnt[arr2[i]]=1;
        }
        for (int i = 0; i < m; i++) {
            if(arr2cnt[arr1[i]]>0)
                arr2cnt[arr1[i]]++;
        }
        int[] res=new int[m];
        int k=0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < arr2cnt[arr2[i]]; j++) {
                res[k++]=arr2[i];
            }
        }
        int s=k;
        for (int i = 0; i < m; i++) {
            if(arr2cnt[arr1[i]]==0)
                res[k++]=arr1[i];
        }
        Arrays.sort(res, s, res.length);
        return res;
    }
}
