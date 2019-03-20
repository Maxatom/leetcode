package leetcode.problems;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Shibing
 * @since 2018-11-16 13:56:56
 **/
public class MergeKSortedLists {
    public static void main(String[] args) {
        MergeKSortedLists merge=new MergeKSortedLists();

        ListNode h1=new ListNode(1),h2=new ListNode(2);
        ListNode t1=h1, t2=h2;
        for (int i = 1; i < 4; i++) {
            t1.next = new ListNode(i*3);
            t1=t1.next;
        }
        for (int i = 2; i < 4; i++) {
            t2.next = new ListNode(i*2);
            t2=t2.next;
        }

        int size=3;
        ListNode[] lists=new ListNode[size];
        Random rand=new Random();
        for (int i = 0; i < size; i++) {
            int length=rand.nextInt(10)+1;
            lists[i]=generateList(length);
//            printList(lists[i]);
        }


      String s="[[-2,-1,-1,-1],[6]]";
        lists=generatedByString(s);


//        lists=new ListNode[]{null,lists[0]};
//        printList(merge.mergeKLists(lists));
//        printList(merge.mergeKLists2(lists));
        printList(merge.mergeKLists5(lists));
    }

    //optimizing priority queue
    public ListNode mergeKLists5(ListNode[] lists){
        Queue<ListNode> queue=new PriorityQueue<>(Comparator.comparingInt(x->x.val));
        for(ListNode node:lists){
            if(node!=null) queue.add(node);
        }
        if(queue.size()==0) return null;
        ListNode h, t;
        h=t=queue.remove();
        if(t.next!=null) queue.add(t.next);
        while (queue.size()>0){
            ListNode temp=queue.remove();
            t.next=temp;
            t=t.next;
            if(temp.next!=null) queue.add(temp.next);
        }
        t.next=null;
        return h;
    }

    //Using priority queue
    public ListNode mergeKLists4(ListNode[] lists){
        Queue<ListNode> queue=new PriorityQueue<>(Comparator.comparingInt(x->x.val));
        for(ListNode node:lists){
            if(node!=null) queue.add(node);
//            while (node!=null){
//                queue.add(node);
//                node=node.next;
//            }
        }
        if(queue.size()==0) return null;
        ListNode h=null, t=null;
        while (queue.size()>0){
            if(h==null){
                h=t=queue.remove();
            }else {
                t.next=queue.remove();
                t=t.next;
            }
        }
        t.next=null;
        return h;
    }

    //Using Tree , need to chang the definition of ListNode
    public ListNode mergeKLists3(ListNode[] lists){
        Set<ListNodeExtend> tree=new TreeSet<>();
        for(ListNode node:lists){
            while (node!=null){
                tree.add(new ListNodeExtend(node.val));
                node=node.next;
            }
        }
        if(tree.size()==0) return null;
        ListNode h=null, t=null;
        for (ListNodeExtend node:tree){
            if(h==null){
                h=t=new ListNode(node.val);
            }else {
                t.next=new ListNode(node.val);
                t=t.next;
            }
        }
        t.next=null;
        return h;
    }
    //复制到一个数组排序  O(NlogN)
    public ListNode mergeKLists2(ListNode[] lists){
        List<Integer> vals=new ArrayList<>();
        for(ListNode node:lists){
            if(node!=null){
                while (node!=null){
                    vals.add(node.val);
                    node=node.next;
                }
            }
        }
        if(vals.size()==0) return null;
        vals.sort(Integer::compareTo);
        return generatedByList(vals);
    }
    //O(NlogK)
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null || lists.length==0) return null;
        Queue<ListNode> queue=new LinkedList<>();
        for(ListNode node:lists) {
            if(node!=null) queue.add(node);
        }
        if(queue.size()==0) return null;
//        System.out.println(" ------------------------- ");
        while (queue.size()>1) {
//            ListNode temp1=queue.remove();
//            ListNode temp2=queue.remove();
//            printList(temp1);
//            printList(temp2);
            queue.add(merge2Lists(queue.remove(), queue.remove()));
        }
        return queue.element();
    }

    ListNode merge2Lists(ListNode l1, ListNode l2) {
        ListNode h=null, t=null;
        //处理头结点
        if(l1 !=null && l2!=null){
            if(l1.val<=l2.val) {
                h=t=l1;
                l1=l1.next;
                t.next=null;
            }else {
                h=t=l2;
                l2=l2.next;
                t.next=null;
            }
        }
        while (l1 !=null && l2!=null){
            if(l1.val<=l2.val) {
                t.next=l1;
                l1=l1.next;
            }else {
                t.next=l2;
                l2=l2.next;
            }
            t=t.next;
            t.next=null;
        }
        if(l1!=null) {
            t.next=l1;
        }else if(l2!=null){
            t.next=l2;
        }
        return h;
    }
    static void printList(ListNode h){
        while (h!=null){
            System.out.print(h.val);
            if(h.next!=null) System.out.print(" -> ");
            h=h.next;
        }
        System.out.println();
    }

    static ListNode generateList(int size){
        if(size<1) return null;
        ListNode h,t;
        Random rand=new Random();
        int bound=10;
        h = t = new ListNode(rand.nextInt(bound));
        for(int i = 1; i < size; i++) {
            t.next= new ListNode(t.val+rand.nextInt(bound));
            t=t.next;
        }
        return h;
    }

    static ListNode generatedByArray(int[] a){
        if(a.length<1) return null;
        ListNode h,t;
        h = t = new ListNode(a[0]);
        for(int i = 1; i < a.length; i++) {
            t.next= new ListNode(a[i]);
            t=t.next;
        }
        return h;
    }
    static ListNode generatedByList(List<Integer> a){
        if(a.size()<1) return null;
        ListNode h,t;
        h = t = new ListNode(a.get(0));
        for(int i = 1; i < a.size(); i++) {
            t.next= new ListNode(a.get(i));
            t=t.next;
        }
        return h;
    }

    static ListNode[] generatedByString(String s){
        ListNode[] lists=null;
        Pattern pattern = Pattern.compile("(?<=\\[).*(?=])");
        Matcher matcher = pattern.matcher(s);
        while(matcher.find()){
            lists=new ListNode[s.split("],").length];
            int i=0;
            Pattern pattern1 = Pattern.compile("(?<=\\[).*?(?=])");
            Matcher matcher1 =pattern1.matcher(matcher.group());
            while (matcher1.find()){
                String[] strs=matcher1.group().split(",");
                if(strs.length==1&&strs[0].equals("")) lists[i++]=null;
                else {
                    List<Integer> list=Stream.of(strs).map(Integer::valueOf).collect(Collectors.toList());
                    lists[i++]=generatedByList(list);
                }
//                printList(lists[i-1]);
            }
        }
        return lists;
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class ListNodeExtend implements Comparable<ListNodeExtend> {
    private static int count=0;
    private int id=count++;
    int val;
    ListNodeExtend(int x) { val = x; }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ListNodeExtend)) return false;
        return id==((ListNodeExtend) obj).id;
    }

    @Override
    public int compareTo(ListNodeExtend o) {
        return (val-o.val)!=0?val-o.val:id-o.id;
    }
}
