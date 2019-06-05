package leetcode.realtest.Top100Interview;

import leetcode.common.ListNode;
import utils.PrintUtils;

/**
 * @author Shibing
 * @since 2019-06-05 11:50:40
 **/
public class AddTwoNumbers {
    public static void main(String[] args) {
        AddTwoNumbers numbers=new AddTwoNumbers();
        ListNode l1=PrintUtils.convertArrayToListNode(new int[]{2 , 4 ,3}), l2=PrintUtils.convertArrayToListNode(new int[]{5,6,4});
        l1=PrintUtils.convertArrayToListNode(new int[]{0}); l2=PrintUtils.convertArrayToListNode(new int[]{0});
        PrintUtils.printListNode(numbers.addTwoNumbers(l1, l2));
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(0);
        ListNode res=head, h1=l1, h2=l2;
        int a,b, p=0;
        while (h1!=null || h2!=null || p!=0){
            if(h1!=null) { a = h1.val; h1=h1.next; } else a=0;
            if(h2!=null) { b = h2.val; h2=h2.next; } else b=0;
            res.next=new ListNode((a+b+p)%10);
            res=res.next;
            p=(a+b+p)/10;
        }
        return head.next;
    }
}
