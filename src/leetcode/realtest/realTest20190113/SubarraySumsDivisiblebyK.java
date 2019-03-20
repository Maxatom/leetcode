package leetcode.realtest.realTest20190113;

import utils.PrintUtils;

import java.io.IOException;

/**
 * @author shibing
 * @since 2019/1/13 11:16
 */
public class SubarraySumsDivisiblebyK {
    public static void main(String[] args) throws IOException {
        SubarraySumsDivisiblebyK divisiblebyK=new SubarraySumsDivisiblebyK();
        int[] A = new int[]{4,5,0,-2,-3,1}; int K = 5;
//         A = new int[]{-2,-3}; K = 5;
//        A = new int[]{1,2,1,1,-3,2,1}; K = 5;
        A = new int[]{5}; K = 5;
         A = new int[]{3}; K = 5;
        A = new int[]{-10000,10000,3,6,4,9,-10}; K = 5;
        A=PrintUtils.readArrayFromFile("E:\\javastudy\\multimodule\\testlearning\\src\\main\\java\\leetcode\\realTest20190113\\arrayData.txt");
//        K=297;
        System.out.println(divisiblebyK.subarraysDivByK(A,K));
        System.out.println(divisiblebyK.subarraysDivByK2(A,K));
    }

    //Prefix sum and counting  O(N)  space O(K)
    public int subarraysDivByK2(int[] A, int K) {
        int[] prefix=new int[K];
        int sum=0;
        for (int a:A){
            sum+=a;
            prefix[(sum%K+K)%K]++; //plus K to deal with negative num
        }
        int count=0;
        count+=(prefix[0]+1)*prefix[0]/2;
        for (int i = 1; i < K; i++) {
            count+=prefix[i]*(prefix[i]-1)/2;
        }
        return count;
    }

    public int subarraysDivByK1(int[] A, int K) {
        return divideAndConquer(A,K,0,  A.length-1)[0];
    }
    //Failed ERROR
    int[] divideAndConquer(int[] A, int K, int left, int right){
        if(left==right){
            if(A[left]%K==0) return new int[]{1,1,1};
            else return new int[]{0,0,0};
        }

        int mid=(left+right)/2;
        int[] leftResult=divideAndConquer(A, K, left, mid);
        int[] righrResult=divideAndConquer(A, K, mid+1, right);
        //total numbers of subarrays
        int total=leftResult[0]+righrResult[0]+leftResult[2]*righrResult[1];
        //subarrays begin with A[left]
        int L=leftResult[1];
        //subarrays end with A[right]
        int R=righrResult[2];

        return new int[]{total,L,R};
    }

    //brute force O(N^2)
    public int subarraysDivByK(int[] A, int K) {
        int[] count=new int[A.length];
        if(A[0]%K==0) count[0]=1;
        for (int i = 1; i < A.length; i++) {
            int sum=0;
            for (int j = i; j >=0 ; j--) {
                sum+=A[j];
                if(sum%K==0) count[i]++;
            }
            count[i]+=count[i-1];
        }
        PrintUtils.printIntArray(count);
        return count[A.length-1];
    }
}
