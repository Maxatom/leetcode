package leetcode.Mock.date20191207;

public class Q2_ITW2 {
    public static void main(String[] args) {

    }
    public Node connect(Node root) {
        if(root==null) return null;
        Node left=root.left, right=root.right;
        while (left!=null && right!=null){
            left.next=right;
            left=left.right;
            right=right.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }
}
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
