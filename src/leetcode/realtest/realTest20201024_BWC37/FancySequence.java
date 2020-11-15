package leetcode.realtest.realTest20201024_BWC37;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FancySequence {
    public static void main(String[] args) {
        String[] op ={"Fancy","append","addAll","append","multAll","getIndex","addAll","append","multAll","getIndex","getIndex","getIndex"};
        int[][] data = {{},{2},{3},{7},{2},{0},{3},{10},{2},{0},{1},{2}};
        Fancy fancy=null;
        for (int i = 0; i < op.length; i++) {
            Integer result=null;
            switch (op[i]){
               case  "Fancy":  fancy = new Fancy(); break;
                case "append": fancy.append(data[i][0]); break;
                case "addAll": fancy.addAll(data[i][0]); break;
                case "multAll": fancy.multAll(data[i][0]); break;
                case "getIndex": result=fancy.getIndex(data[i][0]); break;
            }
            System.out.print(result);
            System.out.print(",");
        }
    }

}
class Fancy {
    List<Integer> list;
    Stack<int[]> stack;

    public Fancy() {
        list=new ArrayList<>();
        stack=new Stack<>();
        stack.push(new int[]{0,0,0});
    }

    public void append(int val) {
        list.add(val);
    }

    public void addAll(int inc) {
        stack.push(new int[]{list.size(), inc, 0});
    }

    public void multAll(int m) {
        stack.push(new int[]{list.size(), m, 1});
    }

    public int getIndex(int idx) {
        int adder = 0;
        int multer = 1;
        while(stack.peek()[0]>idx){
            int[] op = stack.pop();
            if(op[2]==1) multer *= op[1];
            else adder+=multer*op[1];
            while(stack.peek()[0]==op[0]){
                op=stack.pop();
                if(op[2]==1) multer *= op[2];
                else adder+=multer*op[1];
            }
            int i=op[0]-1;
            while(i>=stack.peek()[0] && i>=idx){
                list.set(i, list.get(i)*multer+adder);
                i--;
            }
            if(i==idx-1){
                stack.push(new int[]{i+1, multer, 1});
                stack.push(new int[]{i+1, adder, 0});
                return list.get(idx);
            }

        }
        return 0;
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */
