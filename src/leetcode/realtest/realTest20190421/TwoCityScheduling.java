package leetcode.realtest.realTest20190421;

import utils.PrintUtils;
import utils.Utils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author shibing
 * @since 2019/4/21 11:05
 */
public class TwoCityScheduling {
    public static void main(String[] args) {
        TwoCityScheduling city=new TwoCityScheduling();
        int[][] costs=new int[][]{{10,20},{30,200},{400,50},{30,20}};
         costs=new int[][]{{10,20},{30,200}};
         costs=new int[][]{{515,563},{451,713},{537,709},{343,819},{855,779},{457,60},{650,359},{631,42}};
         costs=new int[][]{{841,185},{295,946},{928,450},{55,806},{714,89},{787,380},{663,323},{814,265},{581,581},{850,486},{390,491},{560,678},{311,283},{145,772},{987,471},{465,611},{926,367},{782,532},{299,317},{605,260},{751,735},{614,746},{747,904},{267,653}};
         costs=new int[][]{{841,185},{295,946},{928,450},{55,806},{714,89},{787,380},{663,323},{814,265},{581,581},{850,486}};
        System.out.println(city.twoCitySchedCost(costs));
        System.out.println(city.twoCitySchedCost1(costs));
    }

    //sort greedy
    public int twoCitySchedCost1(int[][] costs) {
        Arrays.sort(costs, Comparator.comparingInt(p->p[0]-p[1]));
        int res=0;
        for (int i = 0; i < costs.length/2; i++)
            res+= costs[i][0] + costs[i+costs.length/2][1];
        return res;
    }


    //O(N^2)  greedy
    public int twoCitySchedCost(int[][] costs) {
        int mincost = 0, numA = 0, numB=0;
        boolean[] toA = new boolean[costs.length];
        for (int i = 0; i < costs.length; i++) {
            int min= Math.min(costs[i][1], costs[i][0]) , index=0;
            if(numA > numB) min=costs[i][1];
            if(numA < numB) min=costs[i][0];
            int start=min;
            for (int j = i - 1; j >= 0; j--) {
                if ( numA >= numB && toA[j] && costs[i][0] - costs[j][0] + costs[j][1] < min) {
                    min=costs[i][0] - costs[j][0] + costs[j][1];
                    index=j;
                }
                if ( numA <= numB && !toA[j] && costs[i][1] - costs[j][1] + costs[j][0] < min) {
                    min = costs[i][1] - costs[j][1] + costs[j][0];
                    index = j;
                }
            }
            if (min<start) {
                mincost += min;
                if(toA[index]){
                    toA[i] = true;
                    toA[index]=false;
                    numB++;
                }else {
                    toA[i]=false;
                    toA[index]=true;
                    numA++;
                }
            } else {
                if( numA < numB || costs[i][0]<costs[i][1]){
                    mincost += costs[i][0];
                    toA[i]=true;
                    numA++;
                } else if (numA> numB || costs[i][0]>costs[i][1]){
                    mincost += costs[i][1];
                    numB++;
                }
            }
        }
        return mincost;
    }

}
