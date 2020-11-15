package leetcode.realtest.realTest20200919_WCJS;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        Test1 test1 = new Test1();
        int[] target = new int[]{5,4,3,2,1};
        System.out.println(test1.isMagic(target));
    }
    public boolean isMagic(int[] target) {
        List<Integer> list = new ArrayList<>();
        int n= target.length;
        for(int i=2; i<=n; i+=2) list.add(i);
        for(int i=1; i<=n; i+=2) list.add(i);
        int j=0, k=0;
        while (list.size()>0) {
            int cur = j;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == target[j]) {
                    j++;
                } else break;
            }
            if(j==n) return true;
            if(k==0) k=j;
            else if(j-cur!=k) return false;
            if(j==0) return false;
            List<Integer> next = new ArrayList<>();
            for (int i = k+1; i < list.size(); i+=2) {
              next.add(list.get(i));
            }
            for (int i = k; i < list.size(); i+=2) {
                next.add(list.get(i));
            }
            list=next;
        }
        return true;
    }
}
