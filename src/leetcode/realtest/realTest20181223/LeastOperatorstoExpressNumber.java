package leetcode.realtest.realTest20181223;

import utils.Tuple;
import utils.TwoTuple;

/**
 * @author shibing
 * @since 2018/12/23 11:36
 */
public class LeastOperatorstoExpressNumber {
    public static void main(String[] args) {
        LeastOperatorstoExpressNumber number=new LeastOperatorstoExpressNumber();
        TwoTuple<Integer,Integer> testcases=Tuple.tuple(5,501);
//        TwoTuple<Integer,Integer> testcases=Tuple.tuple(3,19);
//        TwoTuple<Integer,Integer> testcases=Tuple.tuple(100,100000000);
//        TwoTuple<Integer,Integer> testcases=Tuple.tuple(5,625);
//        TwoTuple<Integer,Integer> testcases=Tuple.tuple(5,624);
//        TwoTuple<Integer,Integer> testcases=Tuple.tuple(5,376);
//        TwoTuple<Integer,Integer> testcases=Tuple.tuple(5,123);
        testcases=Tuple.tuple(2, 151250060);
        testcases=Tuple.tuple(100 ,200000000);
        System.out.println("result1="+number.leastOpsExpressTarget1(testcases.first,testcases.second));
        System.out.println("result3="+number.leastOpsExpressTarget3(testcases.first,testcases.second));
//        System.out.println("result2="+number.leastOpsExpressTarget2(testcases.first,testcases.second));

    }


    public int leastOpsExpressTarget3(int x, int target) {
        int pos=0, neg=0, remain, k=0;
        while (target>0){
            remain=target%x;
            target=target/x;
            if(k>0){
                int pos2=Math.min(remain*k+pos, (remain+1)*k+neg);
                int neg2=Math.min((x-remain)*k+pos, k*(x-remain-1)+neg);
                pos=pos2;
                neg=neg2;
            }else {
                pos=2*remain;
                neg=2*(x-remain);
            }
            k++;
        }
        return Math.min(pos,k+neg)-1;
    }

    //TLE
    //DP  F(x,t) = max( F(x, t-x^n) + n, F(x, x^(n+1) - t) +n+1 )  x^n<t<x^(n+1)
    // if t<=x/2  F(x,t)= 2t-1  ,   x/2<t<=x  F(x,t)=2(x-t)
    public int leastOpsExpressTarget2(int x, int target) {
        int[] result=getN(x,target);
        if(result[0]==target) return result[1];
        int[] ops=new int[result[0]+1];
        for (int i = 1; i <= x; i++) {
            if(i<=x/2) ops[i]=2*i-1;
            else ops[i]=2*(x-i);
        }
        for (int i = x+1; i <=target ; i++) {
            int[] temp=getN(x, i);
            if(temp[0]==i) ops[i]=temp[1];
            else if(temp[0]-i<i)
                ops[i]=Math.min(ops[i-temp[0]/x]+temp[1], ops[temp[0]-i] + temp[1]+1);
            else ops[i]=ops[i-temp[0]/x]+temp[1];
        }
//        PrintUtils.printIntArray(ops);
        return ops[target];
    }
    int[] getN(int x, int target){
        int result=x;
        int count1=0;
        while (result < target) {
            result *= x;
            count1++;
        }
        return new int[]{result, count1};
    }


    //O(logY) DP 自顶向下
    public int leastOpsExpressTarget1(int x, int target) {
        if(target<=x) return lessThan(x, target);
        int result=x;
        int count1=0;
        int last=result;
        while (result < target) {
            last=result;
            result *= x;
            count1++;
        }
        if(result==target) return count1;
        int left=count1+leastOpsExpressTarget1(x, target-last);
        int right=Integer.MAX_VALUE;
        if(result-target<target) right=count1+1+leastOpsExpressTarget1(x, result-target);
        return Math.min(left,right);
    }

    //失败的算法
    public int leastOpsExpressTarget(int x, int target) {
        if(target<=x) return lessThan(x,target);
        int count=0;
        while (x<=target) {
            int result=x;
            int count1=0;
            while (result < target) {
                result *= x;
                count1++;
             }
             if(result==target) return count1;
             count1--;
             result/=x;
             int quotient=target/result;
             target=target%result;
             //we chose the less one from result*quotient And result*x-result*(x-quotient))
             count1=Math.min((count1+1)*quotient-1, (x-quotient+1)*(count1+1));
             count+=count1;
         }
         return count+lessThan(x,target);
    }
    public int lessThan(int x, int target){
        if(target<=x/2) return 2*target-1;
        else return 2*(x-target);
    }
}
