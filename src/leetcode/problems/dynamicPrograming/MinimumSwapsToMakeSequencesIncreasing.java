package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

/**
 * @author Shibing
 * @since 2019-05-16 15:52:55
 **/
public class MinimumSwapsToMakeSequencesIncreasing {
    public static void main(String[] args) {
        MinimumSwapsToMakeSequencesIncreasing swaps=new MinimumSwapsToMakeSequencesIncreasing();
        int[] A = {1,3,5,4}, B = {1,2,3,7};
        A=new int[]{1,2,5,4,6}; B=new int[]{1,2,3,8,9};
//        A=new int[]{1,2,5,3,9}; B=new int[]{1,1,2,8,4};
//        A=new int[]{0,3,5,8,9}; B=new int[] {2,1,4,6,9};
        System.out.println(swaps.minSwap(A, B));
        System.out.println(swaps.minSwap1(A, B));
    }

    //O(1) space
    public int minSwap1(int[] A, int[] B) {
        int n=A.length;
        //state[0] min swaps to make A[0~i] and B[0~i] increasing if do NOT swap A[i] and B[i]
        //state[1] min swaps to make A[0~i] and B[0~i] increasing if do swap A[i] and B[i]
        int[] state=new int[2];
        state[1]=1;
        for (int i = 1; i < n; i++) {
            PrintUtils.printArray(state);
            boolean bothSelfIncreasing=A[i]>A[i-1] && B[i]>B[i-1];
            boolean InterchangeIncreasing=A[i]>B[i-1] && B[i]>A[i-1];
            int temp=state[1];
            if(bothSelfIncreasing && InterchangeIncreasing){
                state[1]=Math.min(state[1], state[0])+1;
                state[0]=Math.min(temp, state[0]);
            }else if(bothSelfIncreasing){
                state[1]++;
            }else if(InterchangeIncreasing){
                state[1]=state[0]+1;
                state[0]=temp;
            }
        }
        PrintUtils.printArray(state);
        return Math.min(state[0],state[1]);
    }


    public int minSwap(int[] A, int[] B) {
        int n=A.length;
        int[] swap=new int[n];
        int[] notSwap=new int[n];
        swap[0]=1;
        for (int i = 1; i < n; i++) {
            boolean bothSelfIncreasing=A[i]>A[i-1] && B[i]>B[i-1];
            boolean InterchangeIncreasing=A[i]>B[i-1] && B[i]>A[i-1];
            if(bothSelfIncreasing && InterchangeIncreasing){
                swap[i]=Math.min(swap[i-1], notSwap[i-1])+1;
                notSwap[i]=Math.min(swap[i-1], notSwap[i-1]);
            }else if(bothSelfIncreasing){
                swap[i]=swap[i-1]+1;
                notSwap[i]=notSwap[i-1];
            }else if(InterchangeIncreasing){
                swap[i]=notSwap[i-1]+1;
                notSwap[i]=swap[i-1];
            }
        }
        PrintUtils.printArray(swap);
        PrintUtils.printArray(notSwap);
        return Math.min(swap[n-1],notSwap[n-1]);
    }
}
