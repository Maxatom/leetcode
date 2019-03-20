package leetcode.problems.backtrace;

import java.util.*;

/**
 * @author Shibing
 * @since 2019-03-18 18:23:47
 **/
public class NQueens {
    public static void main(String[] args) {
        NQueens queens=new NQueens();
        int n=4;
        n=1;
        n=2;
        n=3;
        n=8;
//        PrintUtils.printList(queens.solveNQueens(n), p->"["+String.join(",\n", p)+"]\n");
        System.out.println(queens.solveNQueens(n).size());
    }
    public List<List<String>> solveNQueens(int n) {
        List<Map<Integer,Integer>> list=new ArrayList<>(3);
        for (int i = 0; i < 3; i++) list.add(new HashMap<>());
        int[] pos=new int[n];
        Arrays.fill(pos, -1);
        List<List<String>> res=new ArrayList<>();
        recursive(res, 0, n, list, pos);
        return res;
    }
    public void recursive(List<List<String>> res, int id, int n, List<Map<Integer,Integer>> list, int[] pos){
        if(id==n){
            res.add(fillrow(pos));
            return;
        }
        Map<Integer,Integer> cols=list.get(0);
        Map<Integer,Integer> diag=list.get(1);
        Map<Integer,Integer> antidiag=list.get(2);
        for (int i = 0; i < n; i++) {
            if(cols.containsKey(i) || diag.containsKey(id+i) || antidiag.containsKey(id-i)) continue;
            cols.put(i, 0); diag.put(id+i, 0); antidiag.put(id-i, 0);
            pos[id]=i;
            recursive(res, id+1, n, list, pos);
            cols.remove(i); diag.remove(id+i); antidiag.remove(id-i);
            pos[id]=-1;
        }
    }
    public List<String> fillrow(int[] pos){
        List<String> res = new ArrayList<>();
        for (int i = 0; i < pos.length; i++) {
            StringBuilder sb=new StringBuilder();
            for (int j = 0; j < pos.length; j++) {
                if(j==pos[i]) sb.append("Q");
                else sb.append(".");
            }
            res.add(sb.toString());
        }
        return res;
    }
}
