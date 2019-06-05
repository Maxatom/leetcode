package leetcode.realtest.realTest20190602;

import utils.PrintUtils;

import java.util.Arrays;

/**
 * @author Shibing
 * @since 2019-06-04 16:12:42
 **/
public class AddingTwoNegabinaryNumbers {
    public static void main(String[] args) {
        AddingTwoNegabinaryNumbers numbers=new AddingTwoNegabinaryNumbers();
        int[] arr1 = {1,1,1,1,1}, arr2 = {1,0,1};
//         arr1 = new int[]{1,1}; arr2 = new int[]{1,1};
//        arr1 = new int[]{1,1,1}; arr2 = new int[]{1,1,1};
//         arr1 = new int[]{1,0}; arr2 = new int[]{1,0};
//        arr1=new int[]{1,1,1,0,1,1}; arr2= new int[]{1,0,1,0};
//        arr1=new int[]{1,0,1}; arr2= new int[]{1,0,1};
//        arr1=new int[]{1}; arr2=new int[]{1,1};
        PrintUtils.printArray(numbers.addNegabinary(arr1, arr2));
        PrintUtils.printArray(numbers.addNegabinary1(arr1, arr2));
    }
    public int[] addNegabinary1(int[] arr1, int[] arr2) {
        int n1=arr1.length, n2=arr2.length;
        int[] res=new int[Math.max(n1, n2)+2];
        int a,b, k=0;
        for (int i=n1-1, j=n2-1; i>=0 || j>=0; i--, j--){
            a=i>=0?arr1[i]:0; b=j>=0?arr2[j]:0;
            res[k++]=a+b;
        }
        for (int i = 0; i < k; i++) {
            if(res[i]>=2 && res[i+1]>0){
                res[i+1]--;
                res[i]-=2;
            }
            if(res[i]>=2 && res[i+1]==0){
                res[i]-=2;
                res[i+1]++;
                res[i+2]++;
            }
        }
        k=res.length-1;
        while (k>0 && res[k]==0) k--;
        int[] ans=new int[k+1];
        for (int l = 0; l <= k; l++)
            ans[l]=res[k-l];
        return ans;

    }
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int n1=arr1.length, n2=arr2.length;
        int[] res=new int[Math.max(n1,n2)+2];
        int i=n1-1, j=n2-1, k=0, p=0, a, b;
        while (i>=0 || j>=0 || p!=0){
            a=i>=0?arr1[i]:0; b=j>=0?arr2[j]:0;
            res[k]=a+b+p;
            if(res[k]==2){
                res[k]=0; p=-1;
            }else if(res[k]==-1){
                res[k]=1; p=1;
            }else if(res[k]==3){
                res[k]=1; p=-1;
            } else p=0;
            i--; j--; k++;
        }
        while (k>1 && res[k-1]==0) k--;
        int[] ans=new int[k];
        for (int l = 0; l < k; l++)
            ans[l]=res[k-l-1];
        return ans;
    }
}
