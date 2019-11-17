package leetcode.realtest.realTest20190630;

import utils.PrintUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author shibing
 * @since 2019/6/30 10:42
 */
public class PathInZigzagLabelledBinaryTree {
    public static void main(String[] args) {
        PathInZigzagLabelledBinaryTree tree=new PathInZigzagLabelledBinaryTree();
        int lable= 14;
        lable=26;
//        lable=5;
        System.out.println(tree.pathInZigZagTree(lable));
    }
    public List<Integer> pathInZigZagTree(int label) {
        int max=1, layer=1;
        while (label>max){
            layer++;
            max=(1<<layer) -1;
//            System.out.println("layer="+layer+",max="+max);
        }
//        System.out.println(layer);
        Integer[] path=new Integer[layer];
        path[--layer]=label;
        while (layer>0){
            label=label/2;
            label=(1<<layer)-1-label+(1<<layer-1);
            path[--layer]=label;
        }
//        PrintUtils.printArray(path);
        return Arrays.asList(path);
    }
}
