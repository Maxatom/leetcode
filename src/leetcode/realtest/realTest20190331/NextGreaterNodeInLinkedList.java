package leetcode.realtest.realTest20190331;

import leetcode.common.ListNode;
import utils.PrintUtils;

import java.util.Stack;

/**
 * @author shibing
 * @since 2019/3/31 13:05
 */
public class NextGreaterNodeInLinkedList {
    public static void main(String[] args) {
        NextGreaterNodeInLinkedList linkedList=new NextGreaterNodeInLinkedList();
        ListNode head=PrintUtils.convertArrayToListNode(new int[]{2,1,5});
        head=PrintUtils.convertArrayToListNode(new int[]{2,7,4,3,5});
        head=PrintUtils.convertArrayToListNode(new int[]{1,7,5,1,9,2,5,1});
        PrintUtils.printIntArray(linkedList.nextLargerNodes(head));
        PrintUtils.printIntArray(linkedList.nextLargerNodes1(head));
    }

    //using stack  O(N)
    public int[] nextLargerNodes1(ListNode head) {
        Stack<Integer> stack=new Stack<>();
        ListNode node=head;
        int cnt=0;
        while (node!=null){
            node=node.next;
            cnt++;
        }
        int[] arr=new int[cnt];
        int i1=0;
        node=head;
        while(node!=null){
            arr[i1++]=node.val;
            node=node.next;
        }
        int[] res=new int[cnt];
        for (int j = 0; j <arr.length ; j++) {
            while (!stack.empty() && arr[stack.peek()]<arr[j]){
                res[stack.pop()]=arr[j];
            }
            stack.push(j);
        }
        return res;
    }

    //brute force
    public int[] nextLargerNodes(ListNode head) {
        if(head==null) return new int[]{};
        ListNode node=head;
        int cnt=0;
        while (node!=null){
            node=node.next;
            cnt++;
        }
        int[] arr=new int[cnt];
        node=head;
        int i1=0;
        while(node!=null){
            arr[i1++]=node.val;
            node=node.next;
        }
        int[] res=new int[cnt];
        for (int i = 0; i < cnt; i++) {
            for (int j = i+1; j < cnt; j++) {
                if(arr[j]>arr[i]) {
                    res[i]=arr[j];
                    break;
                }
            }
        }
        return res;
    }
}
