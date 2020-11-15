package leetcode.realtest.realTest20201004_WC209;

import leetcode.common.TreeNode;
import utils.PrintUtils;

import java.util.LinkedList;
import java.util.Queue;

public class EvenOddTree {
    public static void main(String[] args) {
        EvenOddTree tree = new EvenOddTree();
        TreeNode root = PrintUtils.convertStringToBinaryTree(
        "[23,174,170,97,101,103,107,174,170,168,166,164,160,158,156,83,null,85,89,91,93,95,99,101,103,107,111,113,115,117,119,182,null,180,176,172,168,164,160,158,156,152,150,148,144,142,138,134,132,130,null,126,null,124,null,122,null,118,116,112,null,null,null,81,85,87,91,93,95,99,103,107,109,111,115,117,119,123,127,131,null,133,null,137,141,143,147,151,153,157,159,161,163,167,171,null,null,173,177,179,183,187,null,191,195,null,null,199,201,170,null,168,166,162,158,156,152,150,148,144,142,138,136,134,132,128,124,120,118,116,112,110,106,104,null,102,null,100,96,94,90,null,null,86,null,82,null,80,78,74,72,68,64,62,60,58,56,54,50,48,44,40,38,36,34,32,30,null,null,28,null,24,null,20,null,null,null,16,null,12,null,10,6,2,null,null,null,7,null,null,null,9,11,null,null,null,null,null,null,null,null,13,15,null,null,17,19,21,23,25,29,33,35,null,null,37,null,41,45,null,null,49,51,55,57,61,65,67,71,null,null,75,79,83,null,87,89,null,null,93,97,99,101,103,105,107,null,111,113,null,null,117,121,123,125,null,null,129,133,137,null,141,143,null,null,null,null,145,147,149,null,153,157,159,161,163,165,167,169,173,177,179,183,null,null,187,191,195,199,null,null,null,null,null,null,null,null,null,null,201,205,209,211,null,null,226,null,null,null,224,220,null,null,218,214,210,null,null,null,null,null,208,204,200,198,null,null,196,192,188,184,182,178,176,174,null,null,172,170,null,null,166,164,162,null,null,null,null,null,null,null,158,154,152,148,146,null,null,null,142,null,138,null,134,130,128,126,122,null,120,null,118,null,null,null,null,null,116,114,112,110,106,null,104,null,100,96,null,null,94,92,88,84,82,78,76,null,72,null,null,null,null,null,68,66,64,62,60,58,54,null,null,null,50,null,48,null,44,42,null,null,40,38,null,null,36,32,30,null,28,null,24,20,18,14,10,8,null,null,null,null,4,null,2,2,43,null,47,51,53,null,57,null,null,null,null,null,59,null,61,null,65,67,null,null,69,null,73,null,77,79,null,null,81,null,85,null,null,null,null,null,87,null,89,91,null,null,null,null,95,99,null,null,103,null,105,null,107,111,null,null,115,null,119,123,127,null,null,null,null,null,null,null,131,133,135,null,139,null,null,null,null,null,143,null,null,null,null,null,null,null,null,null,145,147,151,155,null,null,159,163,165,169,171,175,179,null,183,null,187,191,null,null,null,null,195,null,197,201,205,null,207,211,null,null,215,null,null,null,217,219,223,null,null,null,225,229,null,null,233,null,null,null,null,null,null,null,null,null,null,null,235,239,241,243,245,249,null,null,251,255,257,null,176,null,null,null,null,null,null,null,null,null,172,null,null,null,null,null,null,null,null,null,null,null,170,166,162,null,null,null,160,null,null,null,null,null,158,null,null,null,156,null,154,null,null,null,152,148,null,null,null,null,null,null,146,142,null,null,140,null,136,134,null,null,130,null,null,null,128,null,126,null,124,null,120,null,116,112,110,108,106,null,104,100,98,94,null,null,null,null,92,88,84,null,82,null,80,78,74,null,70,68,null,null,64,62,60,null,null,null,58,56,54,null,50,48,46,42,null,null,null,null,40,null,null,null,null,null,36,32,null,null,null,null,30,null,26,null,24,22,null,null,null,null,null,null,null,null,59,null,null,null,null,null,null,null,61,null,63,null,null,null,null,null,null,null,67,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,71,null,null,null,null,null,73,null,75,null,null,null,null,null,null,null,null,null,null,null,79,83,85,null,89,91,93,95,97,null,99,103,null,null,107,111,115,119,121,null,null,null,125,129,null,null,null,null,133,null,null,null,null,null,135,137,null,null,141,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,162,160,156,null,152,148,null,null,146,null,null,null,null,null,null,null,null,null,null,null,null,null,142,null,138,null,134,null,132,130,128,null,126,null,null,null,122,null,null,null,null,null,null,null,null,null,null,null,63,null,65,null,null,null,null,null,null,null,null,null,null,null,69,71,73,null,null,null,null,null,null,null,null,null,null,null,176,174,170,null,null,null,null,null,147]");
        System.out.println(tree.isEvenOddTree(root));

    }
    public boolean isEvenOddTree(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        int rem=1;
        while(list.size()>0){
            LinkedList<TreeNode> next = new LinkedList<>();
            while(list.size()>0){
                TreeNode node1 = list.remove(0);
                if(node1.val%2!=rem) return false;
                if(node1.left!=null) next.add(node1.left);
                if(node1.right!=null) next.add(node1.right);
                if(list.size()>0){
                    TreeNode node2=list.get(0);
                    if(node2.val%2!=rem) return false;
                    if(rem==1 && node1.val>=node2.val || rem==0 && node1.val<=node2.val) return false;
                }
            }
            list=next;
            rem=1-rem;
        }
        return true;
    }

    public boolean isEvenOddTree1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int rem=1;
        while(queue.size()>0){
            Queue<TreeNode> next = new LinkedList<>();
            while(queue.size()>0){
                TreeNode node1 = queue.poll();
                if(node1.val%2!=rem) return false;
                if(node1.left!=null) next.add(node1.left);
                if(node1.right!=null) next.add(node1.right);
                if(queue.size()>0){
                    TreeNode node2=queue.poll();
                    if(node2.val%2!=rem) return false;
                    if(rem==1 && node1.val>=node2.val || rem==0 && node1.val<=node2.val) return false;
                    if(node2.left!=null) next.add(node2.left);
                    if(node2.right!=null) next.add(node2.right);
                }
            }
            queue=next;
            rem=1-rem;
        }
        return true;
    }
}
