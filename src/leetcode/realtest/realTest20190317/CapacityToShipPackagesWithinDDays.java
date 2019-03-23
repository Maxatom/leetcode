package leetcode.realtest.realTest20190317;

import utils.PrintUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shibing
 * @since 2019/3/17 11:28
 */
public class CapacityToShipPackagesWithinDDays {
    public static void main(String[] args) {
        CapacityToShipPackagesWithinDDays capacity=new CapacityToShipPackagesWithinDDays();
        int[] weights = new int[]{1,2,3,4,5,6,7,8,9,10}; int D = 5; //15
//        weights = new int[]{3,2,2,4,1,4}; D = 3; //6
//        weights = new int[]{1,2,3,1,1}; D = 4; //3
//        weights = new int[]{3}; D = 1;
//        weights = new int[]{3,8}; D = 1; //11
//        weights = new int[]{3,8}; D = 2; //8
//        weights=new int[]{180,373,75,82,497,23,303,299,53,426,152,314,206,433,283,370,179,254,265,431,453,17,189,224}; D= 12; //631
        System.out.println(capacity.shipWithinDays(weights, D));
        System.out.println(capacity.shipWithinDays1(weights, D));
    }

    public int shipWithinDays1(int[] weights, int D) {
        int left=0, right=0;
        for(int w:weights){
            left=Math.max(left, w);
            right+=w;
        }
        while (left<right){
            int mid=(left+right)/2, days=1, pack=0;
            for(int w:weights){
                pack+=w;
                if(pack>mid){
                    pack=w;
                    days++;
                }
            }
            if(days<=D) right=mid;
            else left=mid+1;
        }
        return left;
    }



    //O(2^N) dfs  TLE
    public int shipWithinDays(int[] weights, int D) {
        return dfs(weights, D, 0, 0);
    }
    public int dfs(int[] weights, int D, int index, int pre){
        if(D==0)  return index==weights.length?pre:Integer.MAX_VALUE;
        if(D>weights.length-index) return Integer.MAX_VALUE;

        int L=dfs(weights, D, index+1, pre+weights[index]);
        int R=dfs(weights, D-1, index+1, 0);
        int dw=Math.max(pre+weights[index], R);
        return  Math.min(L, dw);
    }

}
