package leetcode.problems.dynamicPrograming;

import utils.ArrayGenerator;
import utils.ArrayGenerator;
import utils.PrintUtils;
import utils.PrintUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * https://www.cnblogs.com/xym4869/p/8513801.html
 * @author Shibing
 * @since 2018-12-14 14:24:46
 **/
public class Knapsack01 {
    public static void main(String[] args) {
        Knapsack01 knapsack01=new Knapsack01();
//        for (int i = 0; i < 1000; i++) {
            int[] val = ArrayGenerator.intArray(15, 1, 6);
            int[] wt = ArrayGenerator.intArray(15, 5, 11);
//            val=PrintUtils.convertStringToIntArray("[3, 4, 3, 1, 5, 1, 2, 1, 5, 5, 1, 5, 2, 5, 1]");
//            wt=PrintUtils.convertStringToIntArray("[7, 5, 7, 6, 7, 10, 10, 5, 8, 7, 8, 10, 6, 8, 10]");
            PrintUtils.printIntArray(val);
            PrintUtils.printIntArray(wt);
//            int result1=knapsack01.recursive(val, wt, 0, 15, 0);
//            System.out.println("result1="+result1 + ", times=" + knapsack01.times);

            int result2=knapsack01.maxValutDP(val, wt, 15);
            System.out.println("result2="+result2);
//            if(result1!=result2) {
//                System.out.println("ERROR!--------------------------");
//                break;
//            }
//        }
    }


    //DP O(V*L)
    int maxValutDP(int val[], int wt[], int volumn){
        int[][] value=new int[val.length+1][volumn+1];
        for (int i = 1; i <= val.length; i++) {
            for (int j = 1; j <= volumn; j++) {
                if(j>=wt[i-1])
                    value[i][j]=Math.max(value[i-1][j-wt[i-1]]+val[i-1], value[i-1][j]);
                else
                    value[i][j]=value[i-1][j];
            }
        }
        PrintUtils.print2DIntArray(value);
        return value[val.length][volumn];
    }

    //O(N^2) 失败的算法
    //失败案例： volumn=15;
    // val=[3, 5, 4, 5, 5]
    //wt=[7, 9, 7, 9, 10]
   int maxValue(int val[], int wt[], int volumn){
       int totalValue=0;
       List<Integer> inBag=new LinkedList<>();
       for (int i = 0; i < val.length; i++) {
           if(volumn-wt[i]>=0){
               totalValue+=val[i];
               volumn-=wt[i];
               inBag.add(i);
           }else {
               Integer rep=-1;
               int pv=0;
               int pw=0;
                for (Integer item:inBag){
                    if(volumn+wt[item]-wt[i]>=0){
                        int x=val[i]-val[item];
                        if(x>pv){ //价值差最大的
                            pv=x;
                            rep=item;
                        }else if(x==pv){ //价值差相同时选重量差最大的
                            if(wt[item]-wt[i]>pw) {
                                pw=wt[item]-wt[i];
                                rep=item;
                            }
                        }
                    }
                }
                if(rep!=-1){
                    inBag.remove(rep);
                    inBag.add(i);
                    totalValue+=val[i]-val[rep];
                    volumn-=wt[i]-wt[rep];
                }
           }
       }
       for (Integer i:inBag){
           System.out.print("["+val[i]+","+wt[i]+"]");
       }
       return totalValue;
   }

    //暴力求解
    //O(2^N)
    int times=0;
    int recursive(int val[], int wt[], int totalValue, int volumn, int id){
        times++;
        if(id==val.length) return totalValue;
        if(volumn-wt[id]>=0){
            int val1= recursive(val, wt, totalValue+val[id],volumn-wt[id], id+1);
            int val2= recursive(val, wt, totalValue,volumn, id+1);
            return Math.max(val1, val2);
        }else return recursive(val, wt, totalValue, volumn, id+1);
    }

}
