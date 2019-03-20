package leetcode.problems.segmentTree;

import utils.Tuple;
import utils.Tuple;
import utils.TwoTuple;
import utils.TwoTuple;

/**
 * @author shibing
 * @since 2019/1/18 23:48
 */
public class RangeSumQueryMutable {

    public static void main(String[] args) {
        int[] test=new int[]{1, 3, 5};
        test=new int[]{2,4,5,7,8,9};
        test=new int[]{4,5,7,8,9};
        TwoTuple<Integer,Integer> bound=Tuple.tuple(3, 1000);
        test=new int[]{};
//        test=ArrayGenerator.intArray(100000000, 0, 20);
        long start=System.currentTimeMillis();
        //array tree
        ArraySegTree arraySegTree=new ArraySegTree(test);
        System.out.println(arraySegTree.sumRange(bound.first,bound.second)+", time="+(System.currentTimeMillis()-start));
        arraySegTree.update(2,5);
        arraySegTree.update(10,23);
        start=System.currentTimeMillis();
        System.out.println(arraySegTree.sumRange(bound.first, bound.second)+", time="+(System.currentTimeMillis()-start));

//        System.out.println("---------------------------------");
//        //link node
//        SegTree segTree=new SegTree(test);
//        System.out.println(segTree.sumRange(1,3));
//        segTree.update(2,5);
//        segTree.update(1,2);
//        System.out.println(segTree.sumRange(1,3));

        System.out.println("---------------------------------");
        start=System.currentTimeMillis();
        //BIT
        BinaryIndexedTree binaryTree=new BinaryIndexedTree(test);
        System.out.println(binaryTree.sumRange(bound.first, bound.second)+", time="+(System.currentTimeMillis()-start));
        binaryTree.update(2,5);
        binaryTree.update(10,23);
        start=System.currentTimeMillis();
        System.out.println(binaryTree.sumRange(bound.first, bound.second)+", time="+(System.currentTimeMillis()-start));


//        for (int i = 0; i < 30; i++)
//            System.out.println(Integer.toBinaryString(i)+",  "+Integer.toBinaryString(-i)+", "+Integer.toBinaryString(i&-i));

    }

}

class ArraySegTree{
    int[] segmentTree;
    int N;
    public ArraySegTree(int[] nums) {
        N=nums.length;
        segmentTree=new int[N<<1];
        for (int i = N, j=0; i < segmentTree.length; i++)
            segmentTree[i]=nums[j++];
        for (int i = N-1; i > 0; i--)
            segmentTree[i]=segmentTree[i<<1]+segmentTree[(i<<1)+1];
    }
    public void update(int i, int val) {
        int j=N+i;
        segmentTree[j]=val;
        while ((j>>=1)>0)
            segmentTree[j]=segmentTree[j<<1]+segmentTree[(j<<1)+1];
    }

    public int sumRange(int i, int j) {
        int L = N + i, R = N + j;
        int sum = 0;
        while (L <= R) {
            if ((L & 1) == 1) {
                sum += segmentTree[L];
                L++;
            }
            if ((R & 1) == 0) {
                sum += segmentTree[R];
                R--;
            }
            L >>= 1;
            R >>= 1;
        }
        return sum;
    }
}

class SegTree{
    SegTreeNode root;

    public SegTree(int[] nums) {
        if(nums.length!=0)
//        buildTree(nums,0,nums.length-1, root);
            root=buildTree(nums, 0,nums.length-1 );
    }

    private int buildTree(int[] nums, int start, int end, SegTreeNode node){
        if(start==end){
            node=new SegTreeNode(start, end, nums[start]);
            return node.sum;
        }
        int mid=(start+end)>>1;
        node=new SegTreeNode(start, end, nums[start]);
        int leftsum=buildTree(nums, start, mid, node.left);
        int rightsum=buildTree(nums, mid+1, end, node.right);
        node.sum=leftsum+rightsum;
        return node.sum;
    }

    private SegTreeNode buildTree(int[] nums, int start, int end){
        if(start==end)
            return new SegTreeNode(start, end, nums[start]);
        SegTreeNode node=new SegTreeNode(start, end, nums[start]);
        int mid=(start+end)>>1;
        SegTreeNode left=buildTree(nums, start, mid);
        SegTreeNode right=buildTree(nums, mid+1, end);
        node.left=left; node.right=right;
        node.sum=left.sum+right.sum;
        return node;
    }

    public void update(int i, int val) {
        update(i, val, root);
    }
    public void update(int i, int val, SegTreeNode node){
        if(node.start ==node.end) {
            node.sum=val;
            return;
        }
        int mid=(node.start +node.end)>>1;
        if(i<=mid) update(i, val, node.left);
        if(i>mid) update(i, val, node.right);
        node.sum=node.left.sum+node.right.sum;
    }

    public int sumRange(int i, int j){
        return sumRange(i, j, root);
    }
    private int sumRange(int i, int j, SegTreeNode node){
        if(node.start==i && node.end==j) return node.sum;
        int mid=(node.start +node.end)>>1;
        if(i>mid) return sumRange(i, j, node.right);
        else if(j<=mid) return sumRange(i, j, node.left);
        else return sumRange(i, mid, node.left)+sumRange(mid+1, j, node.right);
    }
    class SegTreeNode{
        int start, end;
        SegTreeNode left, right;
        int sum;

        public SegTreeNode(int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }

    }
}

class BinaryIndexedTree{
    int[] nums;
    int[] BIT;

    public BinaryIndexedTree(int[] nums) {
        int n=nums.length;
        this.nums = nums;
        BIT=new int[n+1];
        System.arraycopy(nums, 0, BIT, 1, n);
        for (int i = 1; i < BIT.length; i++) {
            int j=i+(i&-i);
            if(j<BIT.length) BIT[j]+=BIT[i];
        }
//        PrintUtils.printIntArray(BIT);
    }

    public void update(int i, int val){
        int delta=val-nums[i];
        nums[i]=val;
        i++;
        while (i<BIT.length){
            BIT[i]+=delta;
            i=i+(i&-i);
        }
    }

    public int prefixSum(int i){
        int sum=0;
        i++;
        while (i>0){
            sum+=BIT[i];
            i-=(i&-i);
        }
//        System.out.println("prefix:"+sum);
        return sum;
    }
    public int sumRange(int i, int j){
        return prefixSum(j)-prefixSum(i-1);
    }
}
