package leetcode.problems;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author ShiBing
 * @projectName leetcode
 * @date 2020/4/519:35
 */
public class LFUCache {
    public static void main(String[] args) {
        LFUCache cache = new LFUCache( 2 /* capacity (缓存容量) */ );
        String[] ops={"LFUCache","put","put","get","get","get","put","put","get","get","get","get"};
        int[][] da={{3},{2,2},{1,1},{2},{1},{2},{3,3},{4,4},{3},{2},{1},{4}};
        ops=new String[]{"LFUCache","put","put","get","put","get","get","put","get","get","get"};
        da=new int[][]{{4},{1,1},{2,2},{1},{3,3},{2},{3},{4,4},{1},{3},{4}};
        ops=new String[]{"LFUCache","put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"};
da=new int[][]{{10},{10,13},{3,17},{6,11},{10,5},{9,10},{13},{2,19},{2},{3},{5,25},{8},{9,22},{5,5},{1,30},{11},{9,12},{7},{5},{8},{9},{4,30},{9,3},{9},{10},{10},{6,14},{3,1},{3},{10,11},{8},{2,14},{1},{5},{4},{11,4},{12,24},{5,18},{13},{7,23},{8},{12},{3,27},{2,12},{5},{2,9},{13,4},{8,18},{1,7},{6},{9,29},{8,21},{5},{6,30},{1,12},{10},{4,15},{7,22},{11,26},{8,17},{9,29},{5},{3,4},{11,30},{12},{4,29},{3},{9},{6},{3,4},{1},{10},{3,29},{10,28},{1,20},{11,13},{3},{3,12},{3,8},{10,9},{3,26},{8},{7},{5},{13,17},{2,27},{11,15},{12},{9,19},{2,15},{3,16},{1},{12,17},{9,1},{6,19},{4},{5},{5},{8,1},{11,7},{5,2},{9,28},{1},{2,2},{7,4},{4,22},{7,24},{9,26},{13,28},{11,26}};
//        for(int i=0;i<ops.length;i++){
//            switch (ops[i]){
//                case "LFUCache": cache=new LFUCache(da[i][0]); break;
//                case "put": cache.put(da[i][0],da[i][1]);
//                    System.out.print("null,"); break;
//                case "get": int re=cache.get(da[i][0]);
//                    System.out.print(re+","); break;
//            }
//        }
    }
    class LinkNode{
        LinkNode prev;
        LinkNode next;
    }
    class Node extends LinkNode{
        int key;
        int value;
        int freq;
        LinkedList list;
        LinkedList prevList, nextList;
        public Node(int key,int value){
            super();
            this.key=key;
            this.value=value;
            this.freq=1;
        }
    }
    Map<Integer, Node> map;
    LinkedList<LinkedList<Node>> freqList;
    final int capacity;
    public LFUCache(int capacity) {
        map=new HashMap<>(capacity);
        freqList=new LinkedList<>();
        this.capacity=capacity;
    }

//    public int get(int key) {
//        if(!map.containsKey(key)) return -1;
//        Node node=map.get(key);
//        node.freq++;
//        LinkLorigLinkList= node.list;
//        LinkList nodeLinkList= node.prevList;
//        if(nodeLinkList==null || nodeLinkList.freq!=node.freq) {
//            nodeLinkList = new LinkList();
//            nodeLinkList.freq=node.freq;
//            //insert list
//            freqList.insertAfter(, nodeLinkList);
//        }
//        //delete
//        if(origLinkList.size==1){
//            freqList.delete(origLinkList);
//        }else
//            origLinkList.delete(node);
//
//        nodeLinkList.insertHead(node);
//        node.list=nodeLinkList;
//        return node.value;
//    }
//
//    public void put(int key, int value) {
//        if(capacity==0) return;
//        if(!map.containsKey(key)){
//            Node node = new Node(key, value);
//            map.put(key, node);
//            LinkList nodeLinkList= (LinkList) freqList.getLast();
//            if(nodeLinkList==null || nodeLinkList.freq!=node.freq) {
//                nodeLinkList=new LinkList();
//                freqList.insertTail(nodeLinkList);
//            }
//            nodeLinkList.insertHead(node);
//            node.list=nodeLinkList;
//            if(map.size() <=capacity) {
//                return;
//            }
//            //delete
//            if(nodeLinkList.size==1){
//                nodeLinkList= freqList.getPrev(nodeLinkList);
//            }
//            map.remove(((Node)nodeLinkList.getLast()).key);
//            nodeLinkList.deleteLast();
//            if(nodeLinkList.size==0) {
//                freqList.delete(nodeLinkList);
//            }
//            return;
//        }
//        map.get(key).value=value;
//        get(key);
//    }
}
