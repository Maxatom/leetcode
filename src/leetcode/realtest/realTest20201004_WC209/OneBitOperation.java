package leetcode.realtest.realTest20201004_WC209;

import java.util.HashMap;
import java.util.Map;

public class OneBitOperation {
    public static void main(String[] args) {
        OneBitOperation one = new OneBitOperation();
        int n=0; //0
        n=1; //1
        n=2; //3
        n=3; //2
        n=4; //7
        n=5; //6
        n=6; //5
        n=9;
        n=333;
//        n=1_000_000_000;
        System.out.println(one.minimumOneBitOperations(n));
        System.out.println(one.minimumOneBitOperations1(n));
    }

    public int minimumOneBitOperations1(int n) {
        return toZero(n);
    }
    int toZero(int n){
        if(n<=1) return n;
        int i=30;
        while (i>=0 && (n&(1<<i))==0){
            i--;
        }
        return toPower2(n&((1<<i)-1), i-1)+ (1<<i);
    }

    int toPower2(int n, int t){
        if(t==0) return n==0?1:0;
        if((n&(1<<t))>0){
            return toZero(n&((1<<t)-1));
        }else {
            return (1<<t) + toPower2(n&((1<<t)-1), t-1) ;
        }
    }

    public int minimumOneBitOperations(int n) {
        return dfs(n,0);
    }
    int dfs(int n, int target){
        int i=31;
        while (i>=0){
            if((n&(1<<i)) != (target&(1<<i))){
                break;
            }
            i--;
        }
        if(i<=0) return i+1;
        return (1<<i) + dfs(n&((1<<i)-1), 1<<(i-1));
    }
}
