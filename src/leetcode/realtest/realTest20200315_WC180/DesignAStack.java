package leetcode.realtest.realTest20200315_WC180;

import utils.PrintUtils;

/**
 * @author ShiBing
 * @projectName leetcode
 * @date 2020/3/1510:56
 */
public class DesignAStack {
    public static void main(String[] args) {
        String[] oper={"CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"};
        int[][] data={{3},{1},{2},{},{2},{3},{4},{5,100},{2,100},{},{},{},{}};
//        oper=new String[]{"CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"};
//        data=new int[][]{{3},{1},{2},{},{2},{3},{4},{5,100},{2,100},{},{},{},{}};
        CustomStack stack=new CustomStack(data[0][0]);;
        for(int i=1; i<oper.length; i++){
            if(oper[i].equals("push")) stack.push(data[i][0]);
            if(oper[i].equals("pop")) {
                System.out.print(oper[i]+"  "+stack.pop());
            }
            if(oper[i].equals("increment")) stack.increment(data[i][0],data[i][1]);
            System.out.print("  top="+stack.top+"  ");
            PrintUtils.printArray(stack.stack);
        }
    }
}
class CustomStack {
    int[] stack;
    int top;
    public CustomStack(int maxSize) {
        stack=new int[maxSize];
        top=-1;
    }

    public void push(int x) {
        if(top<stack.length-1)
            stack[++top]=x;
    }

    public int pop() {
        if(top>-1)
            return stack[top--];
        else return -1;
    }

    public void increment(int k, int val) {
        for(int i=Math.min(k-1,top); i>=0; i--){
            stack[i]+=val;
        }
    }
}
