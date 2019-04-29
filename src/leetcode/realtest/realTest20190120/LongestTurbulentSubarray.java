package leetcode.realtest.realTest20190120;

import utils.PrintUtils;

/**
 * @author shibing
 * @since 2019/1/20 11:52
 */
public class LongestTurbulentSubarray {
    public static void main(String[] args) {
        LongestTurbulentSubarray subarray=new LongestTurbulentSubarray();
        int[] A=new int[]{9,4,2,10,7,8,8,1,9};
//        A=new int[]{4,8,2,16};
//        A=new int[]{100};
//        A=new int[]{100,100,100};
        A=new int[]{ 0,1,1,0,1,0,1,1,0,0};
        System.out.println(subarray.maxTurbulenceSize(A));

        System.out.println(subarray.maxTurbulenceSize1(A));
    }

    //space O(1)
    public int maxTurbulenceSize1(int[] A) {
        int direct=1; //1-odd larger than even, 0-even larger than odd
        int length=1;
        int max=1;
        for (int i = 1; i < A.length; i++) {
            //odd larger than even
            if((i&1)==1&&A[i]>A[i-1] || (i&1)==0&&A[i]<A[i-1]){
                    if(direct==1) length++;
                    else {
                        direct=1;
                        length=2;
                    }
            }
            //0-even larger than odd
            if((i&1)==1&&A[i]<A[i-1] || (i&1)==0&&A[i]>A[i-1]){
                if(direct==0) length++;
                else{
                    direct=0;
                    length=2;
                }
            }
            if(A[i]==A[i-1]) {length=1; direct=1;}
            max=Math.max(max,length);
        }
        return max;
    }

    //O(N) dp  space O(N)
    public int maxTurbulenceSize(int[] A) {
        int[] dp1=new int[A.length]; //odd larger than  even
        int[] dp2=new int[A.length]; //even larger than odd
//        dp1[0]=1; dp2[0]=1;
        int max=0;
        for (int i = 1; i < A.length; i++) {
            if((i&1)==1){
                if(A[i]>A[i-1]) dp1[i]=dp1[i-1]+1;
                if(A[i]<A[i-1]) dp2[i]=dp2[i-1]+1;
            }else if((i&1)==0){
                if(A[i]>A[i-1]) dp2[i]=dp2[i-1]+1;
                if(A[i]<A[i-1]) dp1[i]=dp1[i-1]+1;
            }
            max=Math.max(max,Math.max(dp1[i], dp2[i]));
        }
        PrintUtils.printArray(dp1);
        PrintUtils.printArray(dp2);
        return max+1;
    }
}
