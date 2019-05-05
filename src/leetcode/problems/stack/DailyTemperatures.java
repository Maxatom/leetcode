package leetcode.problems.stack;

import utils.PrintUtils;

/**
 * @author shibing
 * @since 2019/5/3 12:45
 */
public class DailyTemperatures {
    public static void main(String[] args) {
        DailyTemperatures temper=new DailyTemperatures();
        int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
//        T=new int[]{1};
//        T=new int[]{8,6,4,2};
//        T=new int[]{8,6,4,2, 7};
//        T=new int[]{1,3,5,6};
        PrintUtils.printArray(temper.dailyTemperatures(T ));
        PrintUtils.printArray(temper.dailyTemperatures1(T ));
    }

    //O(1) space,
    public int[] dailyTemperatures1(int[] T) {
        int[] res=new int[T.length];
        for (int i = T.length-2; i >=0 ; i--) {
            int nextWarmer=i+1;
            while (res[nextWarmer]!=0 && T[nextWarmer]<=T[i]){
                nextWarmer+=res[nextWarmer];
            }
            if(T[nextWarmer]>T[i])
                res[i]=nextWarmer-i;
        }
        return res;
    }

    //stack
    public int[] dailyTemperatures(int[] T) {
        int[] stack=new int[T.length];
        int[] res=new int[T.length];
        int top=-1;
        for (int i = 0; i < T.length; i++) {
            while (top>=0 && T[stack[top]]<T[i]){
                res[stack[top]]=i-stack[top];
                top--;
            }
            stack[++top]=i;
        }
        return res;
    }
}
