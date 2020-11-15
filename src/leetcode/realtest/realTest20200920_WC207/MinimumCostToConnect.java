package leetcode.realtest.realTest20200920_WC207;

import utils.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumCostToConnect {
    public static void main(String[] args) {
        MinimumCostToConnect connect = new MinimumCostToConnect();
        List<List<Integer>> cost = arrayToList(new int[][]{{93,56,92},{53,44,18},{86,44,69},{54,60,30}});
        System.out.println(connect.connectTwoGroups(cost));
    }
    static List<List<Integer>> arrayToList(int[][] arr){
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0; i<arr.length; i++){
            List<Integer> list1 = new ArrayList<>();
            for(int j=0; j<arr[i].length; j++){
                list1.add(arr[i][j]);
            }
            list.add(list1);
        }
        return list;
    }
    public int connectTwoGroups(List<List<Integer>> cost) {
        int n1 =cost.size(), n2=cost.get(0).size();
        int[][] lines = new int[n1*n2][2];
        for(int i=0; i<n1; i++){
            for(int j=0; j<n2; j++){
                // System.out.printf("%d,%d,%d,%d\n",i,j,n1,(i*n1+j));
                lines[i*n2+j]= new int[]{i,j};
            }
        }
        int[] point1=new int[n1], point2=new int[n2];
        Arrays.fill(point1, n2);
        Arrays.fill(point2, n1);
        Arrays.sort(lines, (l1,l2)->cost.get(l2[0]).get(l2[1])-cost.get(l1[0]).get(l1[1]));
        int res=0;
        for(int[] ln:lines){
            if(point1[ln[0]]<=1 || point2[ln[1]]<=1){
                res+=cost.get(ln[0]).get(ln[1]);
                System.out.println(Arrays.toString(ln));
            }else{
                point1[ln[0]]--;
                point2[ln[1]]--;
            }
        }
        return res;
    }
}
