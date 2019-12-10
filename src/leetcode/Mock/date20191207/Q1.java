package leetcode.Mock.date20191207;

import utils.PrintUtils;

public class Q1 {
    public static void main(String[] args) {
        Q1 q=new Q1();
        int n=100;
        System.out.println(q.canWinNim(n));
    }

    //
    public boolean canWinNim1(int n){
        return n % 4 != 0;
    }

    //state[i] means whether the fist turn people can win when the number of stone is i
    public boolean canWinNim(int n) {
        boolean[]  state=new boolean[n+1];
        state[0]=true; state[1]=true; state[2]=true;state[3]=true;
        for (int i = 4; i <= n; i++) {
            state[i]= !(state[i-1] && state[i-2] && state[i-3]);
        }
        PrintUtils.printArray(state);
        return state[n];
    }

}
