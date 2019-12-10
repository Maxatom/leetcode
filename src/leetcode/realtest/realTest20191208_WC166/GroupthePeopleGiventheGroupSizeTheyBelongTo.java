package leetcode.realtest.realTest20191208_WC166;

import utils.PrintUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupthePeopleGiventheGroupSizeTheyBelongTo {
    public static void main(String[] args) {
        GroupthePeopleGiventheGroupSizeTheyBelongTo belongTo=new GroupthePeopleGiventheGroupSizeTheyBelongTo();
        int[] groupSizes = {3,3,3,3,3,1,3};
        groupSizes = new int[]{2,1,3,3,3,2};
        PrintUtils.printList(belongTo.groupThePeople(groupSizes), l->l.toString());
    }
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        int n=groupSizes.length;
        Map<Integer,List<Integer>> map=new HashMap<>();
        List<List<Integer>> list=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int groupNum=groupSizes[i];
            map.putIfAbsent(groupNum, new ArrayList<>());
            if(map.get(groupNum).size()>= groupNum){
                list.add(map.get(groupNum));
                map.put(groupNum, new ArrayList<>());
            }
            map.get(groupNum).add(i);
        }
        for (Map.Entry<Integer,List<Integer>> entry:map.entrySet()){
            List<Integer> l2= entry.getValue();
            if(l2.size()>0){
                list.add(l2);
            }
        }
        return list;
    }
}
